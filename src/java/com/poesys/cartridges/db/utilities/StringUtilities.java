/*
 * Copyright (c) 2008 Poesys Associates. All rights reserved.
 */
package com.poesys.cartridges.db.utilities;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.andromda.core.metafacade.MetafacadeConstants;
import org.andromda.metafacades.uml.TypeMappings;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.uml2.Association;
import org.eclipse.uml2.DataType;
import org.eclipse.uml2.Stereotype;
import org.eclipse.uml2.Type;

import com.poesys.cartridges.db.metafacades.PersistentPropertyFacadeLogicImpl;
import com.poesys.cartridges.db.profile.PoesysDbProfile;
import com.poesys.cartridges.db.psm.db.Dto;
import com.poesys.cartridges.db.psm.db.Property;
import com.poesys.cartridges.db.psm.db.PropertyImpl;
import com.poesys.cartridges.db.psm.db.PropertyPrefix;


/**
 * String utilities not found in velocity StringUtils.
 * 
 * @author Robert J. Muller
 */
public class StringUtilities {
  /** Logger for this class */
  private static final Logger logger = Logger.getLogger(StringUtilities.class);
  /** Persistent stereotype name */
  private static final String PERSISTENT =
    PoesysDbProfile.NAMESPACE_PERSISTENCE
        + PoesysDbProfile.STEREOTYPE_PERSISTENT;
  /** BooleanString stereotype name */
  private static final String BOOLEAN_STRING =
    PoesysDbProfile.NAMESPACE_PERSISTENCE
        + PoesysDbProfile.STEREOTYPE_BOOLEAN_STRING;
  /** AssociationKey stereotype name */
  private static final String ASSOCIATION =
    PoesysDbProfile.NAMESPACE_KEY + PoesysDbProfile.STEREOTYPE_ASSOCIATION_KEY;
  /** SequenceKey stereotype name */
  private static final String SEQUENCE =
    PoesysDbProfile.NAMESPACE_KEY + PoesysDbProfile.STEREOTYPE_SEQUENCE_KEY;
  /** Constant representing the separator for Java names (".")  */
  private static final String JAVA_NAME_SEPARATOR = ".";
  private static final String BOOLEAN_EXCEPTION =
    "Expected 2 strings for boolean but got ";
  private static final String INVALID_BOOLEAN_STRING =
    "Null or empty string for boolean value";

  /**
   * <p>
   * Build a variable name from a name and a prefix. The prefix is optional. If
   * you have a name and no prefix, the variable name is the name with the first
   * character in lower case. If you have a prefix and a name, the variable name
   * is the prefix with the first character in lower case concatenated to the
   * name with the first character in upper case. If you have a suffix, the
   * variable name is the prefixed variable name concatenated to the suffix with
   * its first character in upper case. If you supply a blank or null main name,
   * the method returns just the indicated prefix with the first letter in lower
   * case.
   * </p>
   * <p>
   * If the name has an underscore in it, use the same convention and append the
   * prefix and suffix with an underscore separator.
   * </p>
   * 
   * @param name the base name for the variable (optional)
   * @param prefix the prefix to which to append the base name (optional)
   * @param suffix the suffix to append to the prefixed base name (optional)
   * @return a variable name with appropriate case
   */
  public static String buildVariableName(String name, String prefix,
                                         String suffix) {
    String sep = "";
    if (name.contains("_")) {
      sep = "_";
    }
    StringBuilder builder = new StringBuilder("");
    if (prefix == null || prefix.length() == 0) {
      if (name == null || name.length() == 0) {
        throw new RuntimeException("Cannot build variable name using name \""
                                   + name + "\", prefix \"" + prefix
                                   + "\", and suffix \"" + suffix + "\"");
      }
      builder.append(name.substring(0, 1).toLowerCase());
      builder.append(name.substring(1));
    } else {
      builder.append(prefix.substring(0, 1).toLowerCase());
      builder.append(prefix.substring(1));
      if (name != null && name.length() > 0 && !name.contains("_")) {
        builder.append(org.apache.velocity.util.StringUtils.capitalizeFirstLetter(name));
      } else if (name != null && name.length() > 0) {
        builder.append(sep);
        builder.append(name);
      }
    }

    if (suffix != null && !suffix.equals("") && !name.contains("_")) {
      builder.append(org.apache.velocity.util.StringUtils.capitalizeFirstLetter(suffix));
    } else if (suffix != null && !suffix.equals("")) {
      builder.append(sep);
      builder.append(suffix);
    }
    return builder.toString();
  }

  /**
   * <p>
   * Get a unique property prefix for a property that represents one side of a
   * many-to-many association, usually a recursive one, that has a duplicate
   * name considering only the source class's key names. Produce a unique prefix
   * with (1) the Persistent name from the end OR, if that tagged value is not
   * present for the end, (2) the end name. The designer can thus choose a
   * disambiguating name different from the end name by putting a name tag on
   * the Persistent stereotype on the end.
   * </p>
   * <p>
   * Note that in the metafacade system for UML2, there is an interface called
   * AssociationEnd that extends Property; to get a prefix for an
   * AssociationEnd, cast it to the type org.eclipse.uml2.Property.
   * </p>
   * 
   * @param end the association end for the many-to-many association
   * @return a unique name for the property based on the key name and a prefix
   */
  public static String xxxgetUniquePropertyPrefix(org.eclipse.uml2.Property end) {
    String prefix = null;
    Stereotype stereotype = end.getAppliedStereotype(PERSISTENT);
    if (stereotype != null) {
      // Get the list of strings from the tagged value.
      EDataTypeUniqueEList taggedValueList =
        (EDataTypeUniqueEList)end.getValue(stereotype,
                                           PoesysDbProfile.TAGGEDVALUE_NAME);
      if (taggedValueList != null && taggedValueList.size() > 0
          && ((String)taggedValueList.get(0)).length() > 0) {
        // At least one string, use it as the prefix.
        prefix = (String)taggedValueList.get(0);
      }
    }

    if (prefix == null) {
      prefix = end.getName();
    }

    return prefix;
  }

  /**
   * A static map of all the property maps indexed by DTO name; used to cache
   * the property maps for name disambiguation as a performance improvement. The
   * map is public for sharing with various PSM classes that use it.
   */
  public static final Map<String, Map<String, List<Property>>> dtoMap =
    new ConcurrentHashMap<String, Map<String, List<Property>>>();

  /**
   * Map all the data members and inherited properties in the shared map of
   * properties keyed on name. The getUniqueName() and getSqlColumnName()
   * methods use this map to generate unique names on demand. The method indexes
   * both the property name and the explicit name (tagged value name) for all
   * properties, since SQL column names may be explicit.
   * 
   * @param ownerName the name of the DTO that owns the properties
   * @param owner the DTO that owns the property
   * 
   * @see #getUniqueName(PropertyImpl, boolean)
   */
  @SuppressWarnings("unchecked")
  public static void mapAllProperties(String ownerName, Dto owner) {
    // Create the property map for the current DTO for name disambiguation.
    if (ownerName != null && owner != null) {
      // Check the cache just to make sure it's not already there.
      if (StringUtilities.dtoMap.get(ownerName) == null) {
        // Create the new property map.
        Map<String, List<Property>> propertyNames =
          new HashMap<String, List<Property>>();
        // Create a collection of all properties of the DTO.
        Collection<Property> allProperties = owner.getAllDtoProperties();

        // Proceed only if there are properties to map
        if (allProperties != null) {
          for (Property p : allProperties) {
            addNameToMap(propertyNames, p, p.getName());
            /*
             * Add explicit name to the name map if different, excluding
             * association object key attributes which are already separate as
             * the explicit names for the object properties.
             */
            if (!p.getName().equals(p.getExplicitName())
                && !p.getSource().contains("AddAssociationKeyAttributeProperties")) {
              addNameToMap(propertyNames, p, p.getExplicitName());
            }
            // Add object name to the name map if different.
            if (!p.getName().equals(p.getObjectName())) {
              addNameToMap(propertyNames, p, p.getObjectName());
            }
          }
          // Put the map into the DTO map.
          StringUtilities.dtoMap.put(ownerName, propertyNames);
        }
      }
    }
  }

  /**
   * Given a map of property lists keyed on name, add a property to the map
   * keying on the specified name either by adding to an existing list for the
   * name or by creating a new list if the name isn't already in the map keys.
   * 
   * @param propertyNames a map of property lists keyed on name
   * @param p a property
   * @param name a name on which to key the property
   */
  private static void addNameToMap(Map<String, List<Property>> propertyNames,
                                   Property p, String name) {
    List<Property> list = propertyNames.get(name);
    if (list != null) {
      // Already there, add this property to the list
      list.add(p);
    } else {
      // Not there, create a new entry.
      list = new ArrayList<Property>(1);
      list.add(p);
      propertyNames.put(name, list);
    }
  }

  /**
   * Get the unique name for a property. This method uses a standard set of name
   * resolution rules to get a unique name after transforming the UML2 property
   * input into a PSM property. If you already have a PSM Property, call the
   * alternative version of getUniqueName.
   * 
   * @param property a UML2 Property; cast AssociationEnd to
   *          org.eclipse.uml2.Property if you have an object of that type
   * @return a unique name for the property
   * @see StringUtilities#getUniqueName(PropertyImpl, boolean)
   */
  public static String getUniqueName(org.eclipse.uml2.Property property) {
    // Transform the property into a PSM Property.
    PersistentPropertyFacadeLogicImpl facade =
      new PersistentPropertyFacadeLogicImpl(property,
                                            StringUtilities.class.getName());
    PropertyImpl p = (PropertyImpl)facade.transformToProperty();
    // This is not a query name, so set the query name flag to false.
    return getUniqueName(p, false);
  }

  /**
   * <p>
   * Get the unique name for a property to use as a query name. Query names need
   * to prioritize the association end name over the explicit object name in the
   * tagged values. This results in classes named with the plural version of the
   * name instead of the singular, object-related name.
   * </p>
   * <p>
   * This method uses a standard set of name resolution rules to get a unique
   * name after transforming the UML2 property input into a PSM property. If you
   * already have a PSM Property, call the alternative version of getUniqueName.
   * The method uses the same getUniqueName method as the alternative version,
   * but it tells the method to use query name resolution.
   * </p>
   * 
   * @param property a UML2 Property; cast AssociationEnd to
   *          org.eclipse.uml2.Property if you have an object of that type
   * @return a unique name for the property
   * @see StringUtilities#getUniqueName(org.eclipse.uml2.Property)
   * @see StringUtilities#getUniqueName(PropertyImpl, boolean)
   */
  public static String getUniqueQueryName(org.eclipse.uml2.Property property) {
    // Transform the property into a PSM Property.
    PersistentPropertyFacadeLogicImpl facade =
      new PersistentPropertyFacadeLogicImpl(property,
                                            StringUtilities.class.getName());
    PropertyImpl p = (PropertyImpl)facade.transformToProperty();
    return getUniqueName(p, true);
  }

  /**
   * Get the unique name for a PSM property. This method uses a standard set of
   * name resolution rules to get a unique name, accessing the appropriate
   * methods in the Property PSM object. Call this method directly if you
   * already have a PSM Property; if you have a UML2 Property, call the
   * alternative version of getUniqueName.
   * 
   * @param property a UML2 property
   * @param isQuery whether the name is a query name
   * @return a unique name for the property
   * @see StringUtilities#getUniqueName(org.eclipse.uml2.Property)
   */
  @SuppressWarnings("unchecked")
  public static String getUniqueName(PropertyImpl property, boolean isQuery) {

    String uniqueName = property.getName();

    try {
      String ownerName = property.getOwnerName();
      if (ownerName == null) {
        // Try the best we can, log the failure.
        logger.error("Property "
                     + uniqueName
                     + " has no owner information to enable testing for duplicate names, source "
                     + property.getSource());
        if (property.getExplicitName() != null) {
          logger.error("Property " + uniqueName + " using explicit name "
                       + property.getExplicitName());
          uniqueName = property.getExplicitName();
        } else {
          // If not, just use the original name.
          logger.error("Property " + uniqueName + " using original name "
                       + property.getName());
        }
      } else {
        String ownerKeyType = property.getOwnerKeyType();
        if (ownerName != null) {
          // Map the properties if needed.
          Map<String, List<Property>> propertyNames = dtoMap.get(ownerName);
          if (propertyNames == null) {
            mapAllProperties(ownerName, property.getOwner());
            propertyNames = dtoMap.get(ownerName);
          }

          // Set the name to the explicit name for association class collections
          // or the object name if there is one for other properties.
          if (property.getSource().equals("AddAssociationClassCollectionProperties")
              && property.getExplicitName() != null
              && property.getExplicitName().length() > 0) {
            uniqueName = property.getExplicitName();
          } else {
            // Use unique name by preference for everything except query
            if (property.getObjectName() != null && !isQuery) {
              uniqueName = property.getObjectName();
            }
          }

          if (propertyNames != null
              && (property.isAssociationEnd() || property.isAssociationAttribute())) {
            // Eliminate duplicate names for all association end variables and
            // to-one association attributes (foreign key attributes).
            if (propertyNames.get(uniqueName) != null) {
              int count = propertyNames.get(uniqueName).size();

              if (count > 1 && property.getExplicitName() != null) {
                // More than one, use the explicit name if not attribute.
                if (!property.isAssociationAttribute()) {
                  uniqueName = property.getExplicitName();
                }
                // If this is an association class object, there will be 2 of
                // the
                // names and explicit name will be the same for both. If not,
                // then
                // check the count to see if the explicit name is unique.
                if (!property.getSource().equals("AddAssociationClassCollectionProperties")
                    && propertyNames.get(uniqueName) == null) {
                  // Not in map, so it's unique. Decrease the count.
                  count--;
                  // Add the property to the list and to the map.
                  List<Property> list = new ArrayList<Property>(1);
                  list.add(property);
                  propertyNames.put(uniqueName, list);
                } else if (!property.getSource().equals("AddAssociationClassCollectionProperties")) {
                  // Not association class object, get the new count.
                  count = propertyNames.get(uniqueName).size();
                }
              }

              if (count > 1) {
                // No explicit name or it's not unique, use the prefix.
                uniqueName =
                  StringUtilities.buildVariableName(uniqueName,
                                                    property.getPrefix(),
                                                    null);
              }
            } else {
              logger.error("Property " + ownerName + JAVA_NAME_SEPARATOR + uniqueName
                           + " is not in property array" + ", source "
                           + property.getSource());
              for (String name : propertyNames.keySet()) {
                logger.info("\t" + name);
              }
            }
          } else if (propertyNames != null && !property.isAssociationEnd()
                     && ownerKeyType.equals("AssociationKey")
                     && property.isKey()) {
            // Eliminate duplicate names for generated association key
            // properties
            if (propertyNames.get(uniqueName) != null) {
              if (propertyNames.get(uniqueName).size() > 1) {
                // Duplicate, use the prefix or prefix list.
                if (property.getPrefixes() != null
                    && property.getPrefixes().size() > 0) {
                  // List is there, use all the prefixes in it.
                  List<PropertyPrefix> prefixList = property.getPrefixes();
                  for (PropertyPrefix prefix : prefixList) {
                    uniqueName =
                      StringUtilities.buildVariableName(uniqueName,
                                                        prefix.getPrefix(),
                                                        null);
                  }
                } else {
                  // No prefix list, just use the single prefix.
                  uniqueName =
                    StringUtilities.buildVariableName(uniqueName,
                                                      property.getPrefix(),
                                                      null);
                }
              }
            } else {
              logger.error("Property " + property.getDto().getName() + JAVA_NAME_SEPARATOR
                           + property.getName() + " is not in property array"
                           + ", source " + property.getSource());
              logger.error("Property " + uniqueName + " using original name "
                           + property.getName());
            }
          }
        }
      }
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new RuntimeException(e.getMessage(), e);
    }

    return uniqueName;
  }

  /**
   * Generate a random string.
   * 
   * @return the random string
   */
  public static String generateString() {
    Random r = new Random();
    return Long.toString(Math.abs(r.nextLong()), 36);
  }

  /**
   * Generate a random string of a maximum length. For strings of length 1,
   * generate a random lowercase letter a-z.
   * 
   * @param maxlength the maximum length of the string to generate
   * 
   * @return the random string
   */
  public static String generateString(int maxlength) {
    String s = null;
    Random r = new Random();
    if (maxlength == 1) {
      // Generate a random number between 97 and 122, inclusive.
      int n = 97 + r.nextInt(26);
      // Convert the number to a lowercase Unicode letter (x61-x7A).
      char c = (char)n;
      s = Character.toString(c);
    } else {
      s = Long.toString(Math.abs(r.nextLong()), 36);
      // Truncate the string to the maximum length if necessary.
      if (s.length() > maxlength) {
        s = s.substring(0, maxlength);
      }
    }
    return s;
  }

  /**
   * Prefix a type name within a fully qualified name with an interface prefix
   * if the useInterfaceForAssociation property is configured to be true. You
   * pass in the configured property from the calling metafacde; you use the
   * metafacade method getConfiguredProperty with the appropriate property name
   * to get the property object. The property must be registered under the
   * metafacade in metafacades.xml.
   * 
   * @param type the UML data type
   * @param name the fully qualified name using package separators
   * @param p the metafacade configured property useInterfaceForAssociation
   * @return the fully qualified name with the type replaced with an
   *         interface-prefixed type if the property is true
   */
  public static String useInterfacePrefix(Type type, String name, Object p) {
    Boolean useInterface = BooleanUtils.toBoolean(ObjectUtils.toString(p));
    String prefix = "";
    if (useInterface) {
      prefix = "I";
    }
    name = name.replace(type.getName(), prefix + type.getName());
    return name;
  }

  /**
   * Get the fully qualified type name based on a specified data type. If the
   * data type is persistent and is not a primitive type and the
   * useInterfaceForAssocation flag is set on, return the interface name rather
   * than just the type name (the type name prefixed with an "I"). The calling
   * metafacade must supply the namespace separator property (
   * <code>getConfiguredProperty(UMLMetafacadeProperties.NAMESPACE_SEPARATOR)</code>
   * ), the language mappings object (
   * <code>getLanguageMappings()<\code>), and the
   * useInterfaceForAssociation property
   * (<code>getConfiguredProperty("useInterfaceForAssociation")</code>),
   * 
   * @param type the UML type
   * @param sepProperty metafacade-supplied NAMESPACE_SEPARATOR property
   * @param languageMappings metafacade-supplied language mappings object
   * @param interfaceProperty metafacade-supplied useInterfaceForAssociation
   *          property
   * @return the fully qualified language-specific type
   */
  public static java.lang.String getQualifiedExtendedTypeName(Type type,
                                                              Object sepProperty,
                                                              TypeMappings languageMappings,
                                                              Object interfaceProperty) {
    String scopeOp = MetafacadeConstants.NAMESPACE_SCOPE_OPERATOR;
    String packageSep = String.valueOf(sepProperty);
    String name = null;
    // If this is a DataType, it's a primitive, not an associated object type.
    Boolean isPrimitive = type instanceof DataType;
    // If the Persistent stereotype exists, the type is persistent.
    boolean isPersistent = type.getAppliedStereotype(PERSISTENT) != null;

    if (type != null) {
      // Get the fully qualified UML type name.
      name = type.getQualifiedName();
      // Strip off any "Data::" from MagicDraw
      name = StringUtils.stripStart(name, "Data" + scopeOp);

      // If there are language mappings, use them to get the specific type.
      if (languageMappings != null) {
        // Look up the language-specific name.
        name = StringUtils.trimToEmpty(languageMappings.getTo(name));

        // Replace scope operators with separators
        name = StringUtils.replace(name, scopeOp, packageSep);
      }

      // If the type is not a primitive, it's an association, so check whether
      // to use the type interface instead of the type and use the prefix.

      if (!isPrimitive && isPersistent) {
        name = useInterfacePrefix(type, name, interfaceProperty);
      }
    }

    return name;
  }

  /**
   * Get the fully qualified type name from a type, excluding any Model name
   * from MagicDraw and returning the rest. This will usually be either a name
   * like datatype::String (from the AndroMDA datatype namespace) or
   * java.lang.<name> or java.math.<name> from the java namespace.
   * 
   * @param type the UML type
   * @return the fully qualified type name
   */
  public static String getQualifiedType(Type type) {
    String scopeOp = MetafacadeConstants.NAMESPACE_SCOPE_OPERATOR;
    // Get the qualified UML type name.
    String dataType = type.getQualifiedName();
    // Strip off any "Data::" model name from MagicDraw
    return StringUtils.stripStart(dataType, "Data" + scopeOp);
  }

  /**
   * Return a Java fully qualified name in the format package.package.class
   * given a UML type.
   * 
   * @param type the UML type
   * @return a fully qualified Java class name
   */
  public static String getQualifiedJavaType(Type type) {
    // Replace all the scope operators with dots.
    return StringUtils.replace(getQualifiedType(type),
                               MetafacadeConstants.NAMESPACE_SCOPE_OPERATOR,
                               JAVA_NAME_SEPARATOR);
  }

  /**
   * Reformat a UML fully qualified name, possibly starting with "Data::", into
   * a Java name (replacing the UML scope operator with a dot).
   * 
   * @param umlName a full qualified UML name
   * @return a fully qualified Java name
   */
  public static String getQualifiedJavaName(String umlName) {
    String name =
      StringUtils.stripStart(umlName,
                             "Data"
                                 + MetafacadeConstants.NAMESPACE_SCOPE_OPERATOR);
    return StringUtils.replace(name,
                               MetafacadeConstants.NAMESPACE_SCOPE_OPERATOR,
                               JAVA_NAME_SEPARATOR);
  }

  /**
   * Get a standard business-layer type name from the UML type. The UML type
   * should be an object type from the system, not a primitive type. It can be
   * either a persistent type or a transient type. If the type is transient, the
   * output type is the same as the input type.
   * 
   * @param type the object type from which to build the business-layer type
   * @param packageName the name of the business-layer subsystem package that
   *          contains the business-layer DTO
   * @return a business-layer DTO type
   */
  public static String getBsTypeName(Type type, String packageName) {
    StringBuilder bsTypeName = new StringBuilder(packageName);
    if (bsTypeName.toString().equals("Default")) {
      // This is a passthrough transient object type, not a persistent type
      bsTypeName = new StringBuilder(getQualifiedJavaType(type));
    } else {
      bsTypeName.append(".Bs");
      bsTypeName.append(type.getName());
    }
    return bsTypeName.toString();
  }

  /**
   * Get an explicit property name for a UML property. This is the first value
   * in a list of strings in the "name" tagged value on the
   * &laquo;Persistent&raquo; stereotype.
   * 
   * @param property the UML property for which to extract the name
   * @return the explicit name, or null if there is none
   */
  public static String getExplicitPropertyName(org.eclipse.uml2.Property property) {
    return getExplicitPropertyName(property, 0);
  }

  /**
   * Get a specific explicit property name for a UML property identified by an
   * integer index. This index is the position in the array of explicit names in
   * the "name" tagged value on the &laquo;Persistent&raquo; stereotype.
   * 
   * @param property the UML2 property with the optional tagged value
   * @param index the index into the array of names
   * @return the indexed name or null if there is none at the index
   */
  public static String getExplicitPropertyName(org.eclipse.uml2.Property property,
                                               int index) {
    String name = null;
    List<String> list = getExplicitPropertyNames(property);
    // Check list nullity and size, then return indexed value.
    if (list != null && list.size() > index) {
      name = list.get(index);
    }
    return name;
  }

  /**
   * Get the explicit sequence name defined in the "sequenceName" tagged value
   * from the &laquo;SequenceKey&raquo; stereotype on a class.
   * 
   * @param classObject the UML class object
   * @return the tagged value or null if there is none
   */
  public static String getExplicitSequenceName(org.eclipse.uml2.Class classObject) {
    String nameTaggedValue = null;
    Stereotype s = classObject.getAppliedStereotype(SEQUENCE);
    // Inherited key may not be SequenceKey
    if (s != null) {
      // Is SequenceKey, get the sequence-key tagged value "sequenceName".
      nameTaggedValue =
        (String)classObject.getValue(s,
                                     PoesysDbProfile.TAGGEDVALUE_SEQUENCE_NAME);
    }
    return nameTaggedValue;
  }

  /**
   * Get the explicit association name defined in the "name" tagged value from
   * the &laquo;AssociationKey&raquo; stereotype on an association.
   * 
   * @param association the UML association object
   * @return the tagged value or null if there is none
   */
  public static String getExplicitAssociationName(Association association) {
    String nameTaggedValue = null;
    Stereotype s = association.getAppliedStereotype(ASSOCIATION);
    // Inherited key may not be AssociationKey
    if (s != null) {
      // Is AssociationKey, get the association tagged value "name".
      nameTaggedValue =
        (String)association.getValue(s, PoesysDbProfile.TAGGEDVALUE_NAME);
    }
    return nameTaggedValue;
  }

  /**
   * Get the optional explicit name for a generalization foreign key (the
   * foreign key for the generated association from a subclass to a superclass),
   * which comes from the "generated_name" tagged value on the
   * &laquo;Identity&raquo;, &laquo;Sequence&raquo;, or &laquo;Guid&raquo;
   * stereotypes in the superclass.
   * 
   * @param stereotype the specific stereotype to look up
   * @param classObject the UML2 class object with the stereotype and value
   * @return the optional tagged value
   */
  public static String getExplicitGeneralizationName(Stereotype stereotype,
                                                     org.eclipse.uml2.Class classObject) {
    String columnName = null;
    if (stereotype != null) {
      String tagValue =
        (String)classObject.getValue(stereotype,
                                     PoesysDbProfile.TAGGEDVALUE_GENERATED_NAME);
      if (tagValue != null && tagValue.length() > 0) {
        columnName = tagValue;
      }
    }
    return columnName;
  }

  /**
   * Get any explicit SQL column names from the "name" tagged value associated
   * with the &laquo;Persistent&raquo; stereotype on the property.
   * 
   * @param property the UML2 association-end property
   * @return a list of String values from the "name" tagged value on the
   *         &laquo;Persistent&raquo; stereotype on the property
   */
  public static List<String> getExplicitPropertyNames(org.eclipse.uml2.Property property) {
    List<String> names = null;

    // Get the property stereotype.
    Stereotype s = property.getAppliedStereotype(PERSISTENT);
    if (s != null) {
      // Stereotype exists, get the values.
      EDataTypeUniqueEList nameList =
        (EDataTypeUniqueEList)property.getValue(s,
                                                PoesysDbProfile.TAGGEDVALUE_NAME);
      if (nameList != null && nameList.size() > 0) {
        names = new ArrayList<String>(nameList.size());
        for (Object o : nameList) {
          names.add((String)o);
        }
      }
    }
    return names;
  }

  /**
   * Get any explicit object name from the "objectName" tagged value associated
   * with the &laquo;Persistent&raquo; stereotype on the property. If the
   * property is an association end on the associated-class side of an
   * association to an association class, this name represents the name of the
   * PSM object class that represents the object in the PSM association class;
   * it allows specifying a singular name for that object and a plural
   * association end name for the collection of objects in associated classes;
   * it also appears as the foreign key constraint name for the constraint that
   * corresponds to the property.
   * 
   * @param property the property for which to get the object name
   * @return the object name
   */
  public static String getExplicitObjectName(org.eclipse.uml2.Property property) {
    String name = null;
    // Get the property stereotype.
    Stereotype stereotype = property.getAppliedStereotype(PERSISTENT);
    if (stereotype != null) {
      String tagValue =
        (String)property.getValue(stereotype,
                                  PoesysDbProfile.TAGGEDVALUE_OBJECT_NAME);
      if (tagValue != null && tagValue.length() > 0) {
        name = tagValue;
      }
    }

    return name;
  }

  /**
   * Get any explicit boolean string representation array from the
   * "representation" tagged value associated with the
   * &laquo;BooleanString&raquo; stereotype on the property.
   * 
   * @param property the UML2 association-end property
   * @return a list of String values from the "name" tagged value on the
   *         &laquo;Persistent&raquo; stereotype on the property
   */
  public static List<String> getBooleanRepresentation(org.eclipse.uml2.Property property) {
    List<String> representation = null;

    // Get the property stereotype.
    Stereotype s = property.getAppliedStereotype(BOOLEAN_STRING);
    if (s != null) {
      // Stereotype exists, get the values.
      EDataTypeUniqueEList nameList =
        (EDataTypeUniqueEList)property.getValue(s,
                                                PoesysDbProfile.TAGGEDVALUE_REP);
      if (nameList != null && nameList.size() > 0) {
        representation = new ArrayList<String>(2);
        for (Object o : nameList) {
          representation.add((String)o);
        }
      }
    }

    // Check for two values only
    if (representation.size() != 2) {
      throw new RuntimeException(BOOLEAN_EXCEPTION + representation.size()
                                 + " for property "
                                 + property.getQualifiedName());
    }

    return representation;
  }

  /**
   * Convert a boolean value into a string representation based on the string
   * representation in a UML2 property (a property with a boolean data type and
   * the &laquo;BooleanString&raquo; stereotype).
   * 
   * @param property the UML2 property with the representation to use
   * @param value the value to convert
   * @return the proper string representation
   */
  public static String getBooleanStringValue(org.eclipse.uml2.Property property,
                                             Boolean value) {
    String booleanValue = null;
    List<String> rep = getBooleanRepresentation(property);
    if (value != null && value) {
      booleanValue = rep.get(1); // true
    } else if (value != null) {
      booleanValue = rep.get(0); // false
    }
    return booleanValue;
  }

  /**
   * Convert a String value into a Boolean representation based on the String
   * representation in a UML2 property (a property with a boolean data type and
   * the &laquo;BooleanString&raquo; stereotype). The string must not be null or
   * empty.
   * 
   * @param property the UML23 property with the representation to use
   * @param value the value to convert
   * @return the proper Boolean value based on the string input
   */
  public static Boolean getBooleanFromString(org.eclipse.uml2.Property property,
                                             String value) {
    Boolean booleanValue = null;
    List<String> rep = getBooleanRepresentation(property);
    if (value == null || value.length() == 0) {
      throw new RuntimeException(INVALID_BOOLEAN_STRING);
    }

    if (value.equalsIgnoreCase(rep.get(0))) {
      booleanValue = true;
    } else {
      booleanValue = false;
    }

    return booleanValue;
  }
}
