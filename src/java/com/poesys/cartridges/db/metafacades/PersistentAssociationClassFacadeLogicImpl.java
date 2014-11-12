package com.poesys.cartridges.db.metafacades;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.andromda.metafacades.uml.AssociationEndFacade;
import org.andromda.metafacades.uml.UMLMetafacadeProperties;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.uml2.Association;
import org.eclipse.uml2.AssociationClass;
import org.eclipse.uml2.Stereotype;
import org.eclipse.uml2.Type;

import com.poesys.cartridges.db.InvalidParametersException;
import com.poesys.cartridges.db.profile.PoesysDbProfile;
import com.poesys.cartridges.db.psm.db.AssociatedKey;
import com.poesys.cartridges.db.psm.db.AssociatedKeyComparator;
import com.poesys.cartridges.db.psm.db.AssociatedKeyImpl;
import com.poesys.cartridges.db.psm.db.Dto;
import com.poesys.cartridges.db.psm.db.DtoImpl;
import com.poesys.cartridges.db.psm.db.KeyType;
import com.poesys.cartridges.db.psm.db.Property;
import com.poesys.cartridges.db.psm.db.PropertyImpl;
import com.poesys.cartridges.db.psm.db.PropertyPrefix;
import com.poesys.cartridges.db.psm.db.Subsystem;
import com.poesys.cartridges.db.utilities.KeyPropertyComparator;
import com.poesys.cartridges.db.utilities.StringUtilities;


/**
 * MetafacadeLogic implementation for
 * com.poesys.cartridges.db.metafacades.PersistentAssociationClassFacade.
 * 
 * @see com.poesys.cartridges.db.metafacades.PersistentAssociationClassFacade
 */
public class PersistentAssociationClassFacadeLogicImpl extends
    PersistentAssociationClassFacadeLogic {
  /** Logger for this class */
  Logger logger =
    Logger.getLogger(PersistentAssociationClassFacadeLogicImpl.class);

  /** PersistentClass metafacade with which to share code */
  private PersistentClassFacadeLogicImpl facade = null;

  private static final String ASSOCIATED_OBJECT_COMMENT =
    "   * <p>\n   * Associated object\n   * </p>";

  private static final String ASSOCIATED_OBJECT_TAG = "Associated object";

  /** The comment text for an association key attribute */
  static final String ASSOCIATION_KEY_COMMENT =
    "   * <p>\n   * Attribute that is part of the association key\n   * </p>";

  /** The parameter tag text for an association key attribute */
  static final String ASSOCIATION_KEY_TAG =
    "Attribute that is part of the association key";

  /** The comment text for an association key attribute */
  static final String ASSOCIATION_COMMENT =
    "   * <p>\n   * Attribute from key of associated object (not part of primary key of association)\n   * </p>";

  /** The parameter tag text for an association key attribute */
  static final String ASSOCIATION_TAG =
    "Attribute that is part of the key of an associated object (not part of primary key of association)";

  /** Persistent stereotype name */
  private static final String PERSISTENT =
    PoesysDbProfile.NAMESPACE_PERSISTENCE
        + PoesysDbProfile.STEREOTYPE_PERSISTENT;

  /** Lazy stereotype name */
  private static final String LAZY = PoesysDbProfile.NAMESPACE_PERSISTENCE
                                     + PoesysDbProfile.STEREOTYPE_LAZY;

  /** AssociationKey stereotype name */
  private static final String ASSOCIATION =
    PoesysDbProfile.NAMESPACE_KEY + PoesysDbProfile.STEREOTYPE_ASSOCIATION_KEY;

  /** PK property stereotype name */
  private static final String PK = PoesysDbProfile.NAMESPACE_KEY
                                   + PoesysDbProfile.STEREOTYPE_PK;

  /** The metafacade context for instantiated metafacades */
  private static final String CONTEXT =
    "com.poesys.cartridges.db.metafacades.PersistentAssociationClassFacadeLogicImpl";

  /** Property indicating whether to use an interface for an association type */
  private static final String INTERFACE = "useInterfaceForAssociation";

  /**
   * Add the association-key-attribute properties to an property list. The
   * association key attributes are the foreign key attributes of the associated
   * objects that comprise the primary key of the class. If there are duplicate
   * names because of a recursive association or simply identically named keys
   * in the associated classes, the strategy prefixes the key names with the
   * property name (tagged value name or the name itself if there is no tagged
   * value). The strategy only produces properties if the association class has
   * the AssociationKey stereotype. A persistent association class may also
   * inherit a primary key, in which case it has association attribute
   * properties that are not primary key properties. The method also takes a
   * list of explicit key attribute names into account, if one exists on the
   * association end. The method sorts the keys in case-insensitive,
   * alphabetical order before adding them to the list; this is the same order
   * used by the associatedKeys association, which is used to produce lists of
   * keys in a given order in various templates.
   */
  private class AddAssociationKeyAttributeProperties implements
      IAddPropertyStrategy {

    @SuppressWarnings("unchecked")
    public void addProperties(List<Property> list) {
      String tableName = facade.getTableName(metaObject);
      // Create a buffer for the properties so they can be sorted before adding.
      List<Property> tempList = new ArrayList<Property>();

      // Must have AssociationKey stereotype to generate key properties
      if (facade.hasKeyType(KeyType.ASSOCIATION)) {
        // Iterate over the association-end properties.
        for (PersistentPropertyFacadeLogicImpl propertyFacade : getAssociatedProperties()) {
          org.eclipse.uml2.Property umlProperty =
            (org.eclipse.uml2.Property)propertyFacade.getMetaObject();

          // Get any explicit SQL column names from the property.
          int sqlNamesCounter = 0;

          for (Property keyProperty : facade.getAssociatedClassKeyProperties(umlProperty)) {

            // Copy the property so as not to overwrite the original.
            Property keyPropertyCopy = null;
            try {
              keyPropertyCopy = new PropertyImpl(keyProperty);
            } catch (InvalidParametersException e) {
              // Throw a runtime exception; keyProperty is null but can't be
              throw new RuntimeException("Null key property from class "
                                         + umlProperty.getName());
            }

            // Get any explicit name for this property.
            keyPropertyCopy.setExplicitName(getExplicitName(umlProperty,
                                                            sqlNamesCounter,
                                                            keyPropertyCopy));

            // Update documentation.
            facade.updateProperty(keyPropertyCopy,
                                  ASSOCIATION_KEY_COMMENT,
                                  ASSOCIATION_KEY_TAG,
                                  tableName);

            keyPropertyCopy.setSource(keyPropertyCopy.getSource()
                                      + " + AddAssociationKeyAttributeProperties");
            // Mark the property as a primary key property.
            keyPropertyCopy.setKey(true);

            /*
             * Set the prefix list if associated class is an association. The
             * prefix list allows for nested, recursive associations that
             * "inherit" the same primary key names through multiple layers,
             * resulting in groups of four or more names that are the same in
             * the leaf association class. The prefix list includes the prefixes
             * from the higher-level association classes, which permits the
             * current leaf class to add them all to disambiguate the names,
             * including the current property name as the last prefix.
             */
            if (umlProperty.getAssociation() != null) {
              List<PropertyPrefix> prefixes = new ArrayList<PropertyPrefix>();
              List<PropertyPrefix> priorPrefixes =
                keyPropertyCopy.getPrefixes();
              // If the prefixes list is not empty, copy it into the new list.
              if (priorPrefixes != null && priorPrefixes.size() > 0) {
                prefixes.addAll(priorPrefixes);
              }
              // Add the property name as the last prefix.
              prefixes.add(new PropertyPrefix(umlProperty.getName()));
              // Set the property's prefixes.
              keyPropertyCopy.setPrefixes(prefixes);
            }
            // Set the prefix to the property name.
            keyPropertyCopy.setPrefix(umlProperty.getName());
            // Set the property UML type to be the property's UML type.
            ((PropertyImpl)keyPropertyCopy).setPropertyType(umlProperty.getType());
            // Set the owning UML type to be the current DTO type.
            ((PropertyImpl)keyPropertyCopy).setOwnerType(metaObject);

            tempList.add(keyPropertyCopy);
          }
          // Increment the index into the explicit names list.
          sqlNamesCounter++;
        }
      }
      // Sort the keys and add them to the input list.
      Collections.sort(tempList, new KeyPropertyComparator());
      list.addAll(tempList);
    }

    /**
     * Extract the optional explicit name, if present. This name is the explicit
     * name on the property, or if that isn't present, the explicit name already
     * set on the column, such as for an inherited column or a generated key
     * column with an explicit name.
     * 
     * @param property a UML2 property
     * @param sqlNamesCounter the current index into the list of names in the
     *          property
     * @param keyPropertyCopy the current PSM property copy corresponding to the
     *          UML2 property
     * @return the optional explicit name (or null if there is none)
     */
    private String getExplicitName(org.eclipse.uml2.Property property,
                                   int sqlNamesCounter, Property keyPropertyCopy) {
      // Get an explicit name at the index, if any.
      String explicitName =
        StringUtilities.getExplicitPropertyName(property, sqlNamesCounter);

      if (explicitName == null && keyPropertyCopy.getExplicitName() != null) {
        // No overriding explicit name, but explicit name is already set in the
        // copy, so it must be coming from another property.
        explicitName = keyPropertyCopy.getExplicitName();
      }

      return explicitName;
    }
  }

  /**
   * Add the association-key-object properties to a property list. The
   * association-key-objects are the objects of the classes at the ends of the
   * association. The associated objects are always single objects, not
   * collections, and because they make up the primary key, they are by
   * definition read only.
   */
  private class AddAssociationKeyObjectProperties implements
      IAddPropertyStrategy {

    public void addProperties(List<Property> list) {
      // Must have AssociationKey stereotype to generate key properties
      if (facade.hasKeyType(KeyType.ASSOCIATION)) {
        // Loop through the properties for the associated classes.
        for (PersistentPropertyFacadeLogicImpl propertyFacade : getAssociatedProperties()) {
          org.eclipse.uml2.Property umlProperty =
            (org.eclipse.uml2.Property)propertyFacade.getMetaObject();
          Type t = umlProperty.getType();
          String className =
            StringUtilities.getQualifiedExtendedTypeName(t,
                                                         getConfiguredProperty(UMLMetafacadeProperties.NAMESPACE_SEPARATOR),
                                                         getLanguageMappings(),
                                                         getConfiguredProperty(INTERFACE));

          Boolean isLazy =
            umlProperty.getAppliedStereotype(LAZY) != null ? Boolean.TRUE
                : Boolean.FALSE;
          String fullName = getFullName(t);
          Subsystem subsystem =
            facade.getSubsystem(facade.findTypeSubsystemPackage(t));

          // Get any explicit name from the property (association end).
          String explicitName =
            StringUtilities.getExplicitPropertyName(propertyFacade.metaObject);
          // Set the prefix to the property name.
          String prefix = umlProperty.getName();
          // Set the name to be a variable derived from the class name.
          String name =
            StringUtilities.buildVariableName(t.getName(), null, null);
          String objectName =
            StringUtilities.getExplicitObjectName(umlProperty);

          // Original name same as name
          String originalName = name;

          PropertyImpl property =
            new PropertyImpl(name,
                             null,
                             null,
                             null,
                             null,
                             false,
                             true,
                             fullName,
                             ASSOCIATED_OBJECT_COMMENT,
                             "public",
                             false,
                             true,
                             umlProperty.getDefault(),
                             ASSOCIATED_OBJECT_TAG,
                             null,
                             null,
                             subsystem.getName(),
                             true,
                             "AddAssociationKeyObjectProperties",
                             getTableName(),
                             isLazy,
                             false,
                             className,
                             true,
                             false,
                             facade.getKeyType(t).toString(),
                             false,
                             originalName,
                             10,
                             100,
                             StringUtilities.getBsTypeName(t,
                                                           subsystem.getBusinessPackageName()),
                             null,
                             null,
                             null,
                             null,
                             false,
                             explicitName,
                             prefix,
                             false,
                             null,
                             null,
                             objectName);
          // Set the owner type to be the current UML type.
          property.setOwnerType(metaObject);
          // Set the property type to be the type of the associated object.
          property.setPropertyType(umlProperty.getType());
          list.add(property);
        }
      }
    }
  }

  /**
   * Add the association-attribute properties to an property list. The
   * association attributes are the foreign key attributes of the associated
   * objects. If there are duplicate names because of a recursive association or
   * simply identically named keys in the associated classes, the strategy
   * prefixes the key names with the property name. The strategy only produces
   * properties if the association class does not have the AssociationKey
   * stereotype but rather inherits a primary key, has a natural key, or
   * generates a key (sequence or identity). The attributes themselves cannot be
   * part of the primary key.
   */
  private class AddAssociationAttributeProperties implements
      IAddPropertyStrategy {

    public void addProperties(List<Property> list) {
      String tableName = facade.getTableName(metaObject);

      // Must not have AssociationKey stereotype to generate key properties
      if (!facade.hasKeyType(KeyType.ASSOCIATION)) {
        // Get the association-end properties.
        for (PersistentPropertyFacadeLogicImpl propertyFacade : getAssociatedProperties()) {
          org.eclipse.uml2.Property umlProperty =
            (org.eclipse.uml2.Property)propertyFacade.getMetaObject();

          // Get any explicit SQL column names from the property.
          List<String> sqlNames =
            StringUtilities.getExplicitPropertyNames(umlProperty);
          int sqlNamesCounter = 0;

          for (Property keyProperty : facade.getAssociatedClassKeyProperties(umlProperty)) {

            // Copy the property so as not to overwrite the original.
            Property keyPropertyCopy = null;
            try {
              keyPropertyCopy = new PropertyImpl(keyProperty);
            } catch (InvalidParametersException e) {
              // Throw a runtime exception; keyProperty is null but can't be
              throw new RuntimeException("Null key property from class "
                                         + umlProperty.getName());
            }

            if (sqlNames != null && sqlNames.size() > sqlNamesCounter) {
            }

            facade.updateProperty(keyPropertyCopy,
                                  ASSOCIATION_COMMENT,
                                  ASSOCIATION_TAG,
                                  tableName);
            // Make the property copy not be a primary key.
            keyPropertyCopy.setKey(false);
            // Replace the associated table name with the linking table name.
            keyPropertyCopy.setTableName(facade.getTableName(metaObject));
            // Designate the current class as the source of the attribute.
            keyPropertyCopy.setSource(keyPropertyCopy.getSource()
                                      + " + AddAssociationAttributeProperties");
            list.add(keyPropertyCopy);
            // Set the owning UML type to be the current DTO type.
            ((PropertyImpl)keyPropertyCopy).setOwnerType(metaObject);
            // Set the property type to the UML property's type.
            ((PropertyImpl)keyPropertyCopy).setPropertyType(umlProperty.getType());
            // Increment the index into the explicit names list.
            sqlNamesCounter++;
          }
        }
      }
    }
  }

  /**
   * Add the association-object properties to a property list. The
   * association-objects are the objects of the classes at the ends of the
   * association that are not part of the primary key. The associated objects
   * are always single objects, not collections.
   */
  private class AddAssociationObjectProperties implements IAddPropertyStrategy {

    public void addProperties(List<Property> list) {
      // Must not have AssociationKey stereotype to generate key properties
      if (!facade.hasKeyType(KeyType.ASSOCIATION)) {
        // Loop through the properties for the associated classes.
        for (PersistentPropertyFacadeLogicImpl propertyFacade : getAssociatedProperties()) {
          org.eclipse.uml2.Property umlProperty =
            (org.eclipse.uml2.Property)propertyFacade.getMetaObject();
          Type t = umlProperty.getType();
          String className =
            StringUtilities.getQualifiedExtendedTypeName(t,
                                                         getConfiguredProperty(UMLMetafacadeProperties.NAMESPACE_SEPARATOR),
                                                         getLanguageMappings(),
                                                         getConfiguredProperty(INTERFACE));
          String fullName = getFullName(t);
          // Create the appropriate name taking duplication into account.
          String name = null;
          String explicitName =
            StringUtilities.getExplicitPropertyName(umlProperty);
          String prefix = umlProperty.getName();

          // Get the subsystem.
          Subsystem subsystem =
            facade.getSubsystem(facade.findSubsystemPackage());

          // Get the property type subsystem.
          Subsystem propertySubsystem =
            facade.getSubsystem(facade.findTypeSubsystemPackage(t));

          name = StringUtilities.buildVariableName(t.getName(), null, null);
          String objectName =
            StringUtilities.getExplicitObjectName(umlProperty);

          PropertyImpl property =
            new PropertyImpl(name,
                             null,
                             null,
                             null,
                             null,
                             true,
                             false,
                             fullName,
                             ASSOCIATED_OBJECT_COMMENT,
                             "public",
                             false,
                             true,
                             umlProperty.getDefault(),
                             ASSOCIATED_OBJECT_TAG,
                             null,
                             null,
                             subsystem.getName(),
                             true,
                             "AddAssociationObjectProperties",
                             getTableName(),
                             isLazy(t),
                             false,
                             className,
                             true,
                             false,
                             facade.getKeyType(t).toString(),
                             false,
                             umlProperty.getName(),
                             10,
                             100,
                             StringUtilities.getBsTypeName(t,
                                                           propertySubsystem.getBusinessPackageName()),
                             null,
                             null,
                             null,
                             null,
                             false,
                             explicitName,
                             prefix,
                             false,
                             null,
                             null,
                             objectName);
          // Set the property type to be the type of the associated object.
          property.setPropertyType(t);
          // Set the owner type to be the current UML type.
          property.setOwnerType(metaObject);

          list.add(property);
        }
      }
    }
  }

  /**
   * Create a PersistentAssociationClassFacadeLogicImpl object.
   * 
   * @param metaObject the AssociationClass object to wrap
   * @param context the context of the implementation
   */
  public PersistentAssociationClassFacadeLogicImpl(org.eclipse.uml2.AssociationClass metaObject,
                                                   String context) {
    super(metaObject, context);
    // Instantiate a PersistentClassFacade to share code.
    facade = new PersistentClassFacadeLogicImpl(metaObject, CONTEXT);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.poesys.cartridges.db.metafacades.PersistentAssociationClassFacadeLogic
   * #handleTransformToDto()
   */
  protected Dto handleTransformToDto(Subsystem subsystem) {
    // Use the persistent class metafacade handler to share code. Reconfigure
    // the standard persistent class strategies for the association class
    // structure.

    // Get the DTO from the cache if it's there.
    Dto dto = PersistentClassFacadeLogicImpl.cache.get(getFullyQualifiedName());
    if (dto == null) {
      // Not cached, create it.
      logger.debug("Transforming association " + getName() + " to DTO.");

      // Redo the strategy lists with association properties.
      initAllDtoProperties();
      initConstructorArgs();
      initColumns();
      initInsertProperties();
      initLocalMembers();
      initDataMembers();
      initObjectProperties();
      initKeyProperties();

      facade.associatedDtos = getAssociatedDtos();

      // Execute the class facade handler, which will use the new strategies.
      dto = facade.handleTransformToDto(subsystem);

      // Set the associated keys after DTO creation to avoid loops.
      try {
        facade.associatedKeys = getAssociatedKeys();
        // Sort keys by name so that lists of keys come out in the same order
        // for every use of the list.
        Collections.sort(facade.associatedKeys, new AssociatedKeyComparator());
        dto.setAssociatedKeys(facade.associatedKeys);
      } catch (RuntimeException e) {
        throw e;
      } catch (InvalidParametersException e) {
        throw new RuntimeException("Transforming to association DTO: "
                                   + e.getMessage(), e);
      }

      // Add generated keys after DTO creation to avoid loops.
      addAssociatedKeysToDtoForeignKeySet(dto);
    }

    return dto;
  }

  /**
   * Add the associated keys (the AssociatedKey instances representing the
   * generated Association keys that are foreign keys to the associated
   * classes). The association keys are distinct from the foreign keys for the
   * association; there may be overlap between these two sets.
   * 
   * @param dto the DTO to which to add the keys
   */
  @SuppressWarnings("unchecked")
  private void addAssociatedKeysToDtoForeignKeySet(Dto dto) {
    // TODO Test the recursive association logic more extensively to ensure
    // that this approach works under all circumstances, including the
    // n-ary association case. The logic below commented out is the old
    // code logic that checked for duplicates and didn't add the foreign
    // keys if there were any duplicates. This failed for n-ary associations
    // and it wasn't clear to me that the duplicate detection is necessary.
    /**
     * <pre>
     * Collection&lt;AssociatedKey&gt; foreignKeys = dto.getForeignKeys();
     * Collection&lt;AssociatedKey&gt; associatedKeys = facade.associatedKeys;
     * boolean duplicate = false;
     * outer: for (AssociatedKey foreignKey : foreignKeys) {
     *   for (AssociatedKey associatedKey : associatedKeys) {
     *     if (foreignKey.equals(associatedKey)) {
     *       duplicate = true;
     *       // Stop at the first duplicate found, that's enough.
     *       break outer;
     *     }
     *   }
     * }
     * 
     * // Add the associated keys to the foreign keys if not already there.
     * if (!duplicate) {
     *   foreignKeys.addAll(facade.associatedKeys);
     * }
     * </pre>
     */
    dto.getForeignKeys().addAll(facade.associatedKeys);
    // Sort the list into alphabetical order.
    Collections.sort(dto.getForeignKeys(), new AssociatedKeyComparator());
  }

  /**
   * <p>
   * Initialize the strategy list for the all-properties association by adding
   * all the association strategies to the class strategies already added. This
   * list includes the following property-addition strategies:
   * </p>
   * <ol>
   * <li>association key object properties</li>
   * <li>association key attribute properties</li>
   * <li>association object properties</li>
   * <li>association attribute properties</li>
   * </ol>
   */
  private void initAllDtoProperties() {
    // Just add the association class strategies to those already present
    // from the class strategy constructor.
    facade.allDtoPropertyStrategies.add(new AddAssociationKeyObjectProperties());
    facade.allDtoPropertyStrategies.add(new AddAssociationKeyAttributeProperties());
    facade.allDtoPropertyStrategies.add(new AddAssociationObjectProperties());
    facade.allDtoPropertyStrategies.add(new AddAssociationAttributeProperties());
  }

  /**
   * <p>
   * Initialize the strategy list for constructor args, replacing the persistent
   * class version completely. This list includes the following strategies:
   * </p>
   * <ol>
   * <li>association key attributes</li>
   * <li>key attributes other than association key (based on key type)</li>
   * <li>association attributes (not part of primary key)</li>
   * <li>inherited attributes</li>
   * <li>inherited association attributes</li>
   * <li>local attributes</li>
   * <li>to-one association attributes</li>
   * <li>to-one association required objects</li>
   * </ol>
   */
  private void initConstructorArgs() {
    facade.constructorArgStrategies = new ArrayList<IAddPropertyStrategy>(14);
    facade.constructorArgStrategies.add(new AddAssociationKeyAttributeProperties());
    facade.constructorArgStrategies.add(facade.new AddNaturalKeyProperties());
    facade.constructorArgStrategies.add(facade.new AddExplicitAssociationKeyProperties());
    facade.constructorArgStrategies.add(facade.new AddInheritedKeyProperties());
    facade.constructorArgStrategies.add(facade.new AddGeneratedGuidKeyProperties());
    facade.constructorArgStrategies.add(facade.new AddGeneratedKeyProperties());
    facade.constructorArgStrategies.add(facade.new AddParentKeyAttributes());
    facade.constructorArgStrategies.add(facade.new AddOrderedSubKeyProperties());
    facade.constructorArgStrategies.add(facade.new AddUnorderedSubKeyProperties());
    facade.constructorArgStrategies.add(facade.new AddExplicitSubKeyProperties());
    facade.constructorArgStrategies.add(new AddAssociationAttributeProperties());
    facade.constructorArgStrategies.add(facade.new AddInheritedAttributeProperties());
    facade.constructorArgStrategies.add(facade.new AddInheritedAssociationAttributeProperties());
    facade.constructorArgStrategies.add(facade.new AddLocalAttributeProperties());
    facade.constructorArgStrategies.add(facade.new AddToOneAssociationAttributeProperties());
    facade.constructorArgStrategies.add(facade.new AddToOneAssociationRequiredObjectProperties());
  }

  /**
   * <p>
   * Initialize the strategy list for SQL table columns, replacing the
   * persistent class version completely. This list includes the following
   * strategies:
   * </p>
   * <ol>
   * <li>association key attributes</li>
   * <li>key attributes other than association key (based on key type)</li>
   * <li>association attributes (not part of primary key)</li>
   * <li>local attributes</li>
   * <li>to-one association attributes</li>
   * </ol>
   */
  private void initColumns() {
    facade.columnStrategies = new ArrayList<IAddPropertyStrategy>(14);
    facade.columnStrategies.add(new AddAssociationKeyAttributeProperties());
    facade.columnStrategies.add(facade.new AddNaturalKeyProperties());
    facade.columnStrategies.add(facade.new AddExplicitAssociationKeyProperties());
    facade.columnStrategies.add(facade.new AddInheritedKeyProperties());
    facade.columnStrategies.add(facade.new AddGeneratedGuidKeyProperties());
    facade.columnStrategies.add(facade.new AddGeneratedKeyProperties());
    facade.columnStrategies.add(facade.new AddParentKeyAttributes());
    facade.columnStrategies.add(facade.new AddOrderedSubKeyProperties());
    facade.columnStrategies.add(facade.new AddUnorderedSubKeyProperties());
    facade.columnStrategies.add(facade.new AddExplicitSubKeyProperties());
    facade.columnStrategies.add(new AddAssociationAttributeProperties());
    facade.columnStrategies.add(facade.new AddLocalAttributeProperties());
    facade.columnStrategies.add(facade.new AddToOneAssociationAttributeProperties());
  }

  /**
   * <p>
   * Initialize the strategy list for insert attributes. The insert attributes
   * are the attributes in the SQL INSERT statement for a DTO. This list
   * replaces the class strategy list completely with the following
   * property-addition strategies:
   * </p>
   * <ol>
   * <li>key properties appropriate to the key type</li>
   * <li>local attributes</li>
   * <li>to-one association attributes</li>
   * </ol>
   * <p>
   * This list is a subset of the constructor arg list that excludes the
   * inherited properties.
   * </p>
   * 
   * @see #initConstructorArgs()
   */
  private void initInsertProperties() {
    facade.insertPropertyStrategies = new ArrayList<IAddPropertyStrategy>(12);
    facade.insertPropertyStrategies.add(new AddAssociationKeyAttributeProperties());
    facade.insertPropertyStrategies.add(facade.new AddNaturalKeyProperties());
    facade.insertPropertyStrategies.add(facade.new AddExplicitAssociationKeyProperties());
    facade.insertPropertyStrategies.add(facade.new AddInheritedKeyProperties());
    facade.insertPropertyStrategies.add(facade.new AddGeneratedGuidKeyProperties());
    facade.insertPropertyStrategies.add(facade.new AddGeneratedKeyProperties());
    facade.insertPropertyStrategies.add(facade.new AddParentKeyAttributes());
    facade.insertPropertyStrategies.add(facade.new AddOrderedSubKeyProperties());
    facade.insertPropertyStrategies.add(facade.new AddUnorderedSubKeyProperties());
    facade.insertPropertyStrategies.add(facade.new AddExplicitSubKeyProperties());
    facade.insertPropertyStrategies.add(facade.new SortCompositeKeyProperties());
    facade.insertPropertyStrategies.add(new AddAssociationAttributeProperties());
    facade.insertPropertyStrategies.add(facade.new AddLocalAttributeProperties());
    facade.insertPropertyStrategies.add(facade.new AddToOneAssociationAttributeProperties());
  }

  /**
   * <p>
   * Initialize the strategy list for local data members. A local data member is
   * a key attribute for any type of key (excluding inherited keys) or a local
   * non-key attribute or a to-one association member but no inherited
   * attributes or to-many association members. This list is a subset of the
   * constructor args that consists of the arguments that get assigned to local
   * data members in the constructor. This list replaces the class strategy list
   * with the following property-addition strategies:
   * </p>
   * <ol>
   * <li>association key attributes</li>
   * <li>key attributes of the appropriate type other than association key (no
   * inherited)</li>
   * <li>association attributes from associated object (not association key)</li>
   * <li>local attributes</li>
   * <li>to-one association attributes</li>
   * ,li>required, to-one association objects</li>
   * </ol>
   */
  private void initLocalMembers() {
    facade.localMemberStrategies = new ArrayList<IAddPropertyStrategy>(11);
    facade.localMemberStrategies.add(new AddAssociationKeyAttributeProperties());
    facade.localMemberStrategies.add(facade.new AddNaturalKeyProperties());
    facade.localMemberStrategies.add(facade.new AddExplicitAssociationKeyProperties());
    facade.localMemberStrategies.add(facade.new AddGeneratedGuidKeyProperties());
    facade.localMemberStrategies.add(facade.new AddGeneratedKeyProperties());
    facade.localMemberStrategies.add(facade.new AddParentKeyAttributes());
    facade.localMemberStrategies.add(facade.new AddOrderedSubKeyProperties());
    facade.localMemberStrategies.add(facade.new AddUnorderedSubKeyProperties());
    facade.localMemberStrategies.add(facade.new AddExplicitSubKeyProperties());
    facade.localMemberStrategies.add(new AddAssociationAttributeProperties());
    facade.localMemberStrategies.add(facade.new AddLocalAttributeProperties());
    facade.localMemberStrategies.add(facade.new AddToOneAssociationAttributeProperties());
    facade.localMemberStrategies.add(facade.new AddToOneAssociationRequiredObjectProperties());
  }

  /**
   * <p>
   * Initialize the strategy list for data members by adding all the association
   * class strategies to the list created for regular classes.This list includes
   * the following property-addition strategies:
   * </p>
   * <ol>
   * <li>association key object properties</li>
   * <li>association key attribute properties</li>
   * <li>association object properties</li>
   * <li>association attribute properties</li>
   * </ol>
   */
  private void initDataMembers() {
    // Just add the association data member object strategy.
    // Note that keys will already be there for non-association-key class.
    facade.dataMemberStrategies.add(new AddAssociationKeyObjectProperties());
    facade.dataMemberStrategies.add(new AddAssociationKeyAttributeProperties());
    facade.dataMemberStrategies.add(new AddAssociationObjectProperties());
    facade.dataMemberStrategies.add(new AddAssociationAttributeProperties());
  }

  /**
   * <p>
   * Initialize the strategy list for object properties. The object properties
   * are the actual data members of the AbstractDbDto that hold associated DTOs
   * in object form. Each object property has a corresponding group of setters
   * in the DbDto class that use the corresponding key attributes to query the
   * object or collection using a SQL specification. The strategies are a subset
   * of the data member strategies. The association class needs to include the
   * associated objects (either when they are part of the association key or
   * when the association class has another type of key). This list replaces the
   * class strategy list with the following strategies:
   * </p>
   * <ol>
   * <li>association key objects</li>
   * <li>association objects (association class with no association key)</li>
   * <li>to-one association objects</li>
   * <li>to-one child composite aggregations (objects or collections)</li>
   * </ol>
   */
  private void initObjectProperties() {
    facade.objectStrategies = new ArrayList<IAddPropertyStrategy>(4);
    facade.objectStrategies.add(new AddAssociationKeyObjectProperties());
    facade.objectStrategies.add(new AddAssociationObjectProperties());
    facade.objectStrategies.add(facade.new AddToOneAssociationOptionalObjectProperties());
    facade.objectStrategies.add(facade.new AddToOneAssociationRequiredObjectProperties());
    facade.objectStrategies.add(facade.new AddToOneChildProperties());
  }

  /**
   * <p>
   * Initialize the strategy list for keys by replacing the strategy list with a
   * list containing the same key properties as in the persistent class but with
   * the addition of the association class association key attribute strategy.
   * Association class association key attributes are attributes that correspond
   * to the primary keys of the association class. This list replaces the class
   * strategy list with the following strategies:
   * </p>
   * <ol>
   * <li>to-one association key attributes</li>
   * <li>key attributes of the appropriate type, including inherited</li>
   * </ol>
   */
  private void initKeyProperties() {
    facade.keyStrategies = new ArrayList<IAddPropertyStrategy>(9);
    facade.keyStrategies.add(new AddAssociationKeyAttributeProperties());
    // Add potential non-association-key key properties
    facade.keyStrategies.add(facade.new AddNaturalKeyProperties());
    facade.keyStrategies.add(facade.new AddExplicitAssociationKeyProperties());
    facade.keyStrategies.add(facade.new AddInheritedKeyProperties());
    facade.keyStrategies.add(facade.new AddGeneratedGuidKeyProperties());
    facade.keyStrategies.add(facade.new AddGeneratedKeyProperties());
    facade.keyStrategies.add(facade.new AddParentKeyAttributes());
    facade.keyStrategies.add(facade.new AddOrderedSubKeyProperties());
    facade.keyStrategies.add(facade.new AddUnorderedSubKeyProperties());
    facade.keyStrategies.add(facade.new AddExplicitSubKeyProperties());
  }

  /**
   * <p>
   * Get the DTOs for the associated classes for an association class. This
   * collection permits a template to iterate through the associated DTOs to get
   * keys and other properties of the associated classes. The list of DTOs
   * includes one DTO for each associated class, including any associated
   * through to-one associations marked with a &laquo;PK&raquo; stereotype, an
   * n-ary association, so there can be more than 2 DTOs in the list.
   * </p>
   * <p>
   * Note: If the association is recursive, the method will return the same
   * class twice. If you want to get key properties based on the associations
   * (that is, using the association name rather than just the key name), you
   * should use the getAssociatedKeys() method.
   * </p>
   * 
   * @return a collection of associated DTOs for the association class or null
   *         if the current class is not an association class
   * @see #getAssociatedKeys()
   */
  private List<Dto> getAssociatedDtos() {
    List<DtoImpl> impls = null;
    impls = new ArrayList<DtoImpl>(2);
    DtoImpl dto = null; // save last DTO created for copying if necessary

    Association a = (Association)metaObject;

    for (Object o : a.getEndTypes()) {
      Type t = (Type)o;
      dto = (DtoImpl)facade.transformTypeToDto(t);
      impls.add(dto);
    }

    if (impls.size() == 1) {
      // Recursive association, get the class again using the last DTO built.
      impls.add(new DtoImpl(dto));
    }

    // Get the to-one PK associated DTOS.
    addAssociatedNAryDtos(impls);

    // Sort the DTOs in comparison order.
    Collections.sort(impls);
    return new ArrayList<Dto>(impls);
  }

  /**
   * Iterate through the to-one associations on the current association object
   * and get any DTOs for associations marked with a &laquo;PK&raquo;
   * stereotype.
   * 
   * @param dtos a list of DTOs to which to add the associated DTOS
   */
  private void addAssociatedNAryDtos(List<DtoImpl> dtos) {
    for (Object o : metaObject.getOwnedAttributes()) {
      org.eclipse.uml2.Property p = (org.eclipse.uml2.Property)o;

      // Check for to-one association with PK stereotype
      if (p.getAssociation() != null
          && p.upper() == 1
          && p.getAppliedStereotype(PK) != null) {
        Type t = p.getType();
        DtoImpl dto = (DtoImpl)facade.transformTypeToDto(t);

        // If the DTO was created, add it to the list.
        if (dto != null) {
          dtos.add(dto);
        }
      }
    }
  }

  /**
   * <p>
   * Get the associated-key objects for the associated classes for the
   * association class. The associated-key objects represent the foreign keys
   * from the association class to the associated classes. The method also takes
   * into account the n-ary association case, which the current system
   * represents as an association class that has a foreign key (an association
   * that is to-one on the associated class) that is marked as a primary key
   * with the &laquo;PK&raquo; stereotype. The associated key to this n-th
   * associated class becomes part of the list of associated keys. The keys are
   * ordered by name so they always come out in the same order.
   * </p>
   * 
   * @return a list of associated keys
   * @throws RuntimeException when the properties cannot be copied
   * @throws InvalidParametersException when a property copying operation fails
   */
  private List<AssociatedKey> getAssociatedKeys() throws RuntimeException,
      InvalidParametersException {
    // Get the 2 associated class keys.
    List<AssociatedKey> keys = getRegularBinaryAssociatedKeys();
    // Get the to-one association keys.
    facade.addAssociationForeignKeys(keys);
    // Sort the keys in alphabetical order.
    Collections.sort(keys, new AssociatedKeyComparator());
    return keys;
  }

  /**
   * Get the associated-key objects for the associated classes for the
   * association class (the two classes associated to the association class
   * through the Association object). These objects permit the template to
   * construct appropriately named keys for the association key components,
   * especially for recursive associations, by making the individual keys with
   * their associated properties available. You can then build association keys
   * out of the key properties. This list is also added to the set of foreign
   * keys. Note that the foreign key object name is the type name if there is no
   * duplication of names or the property name if there is duplication. </p>
   * <p>
   * Note that if there are multiple associated keys, but the key attribute
   * properties have the same name, the property names must be disambiguated
   * based on the properties in the association class, not the associated
   * classes.
   * </p>
   * 
   * @return a list of the two associated keys
   * @throws RuntimeException when there is a problem copying a null key
   *           property
   */
  @SuppressWarnings("unchecked")
  private List<AssociatedKey> getRegularBinaryAssociatedKeys()
      throws RuntimeException {
    List<AssociatedKey> keys = new ArrayList<AssociatedKey>(2);
    Association a = (Association)metaObject;
    boolean hasAssociationKey = a.getAppliedStereotype(ASSOCIATION) != null;

    for (Object o : a.getMemberEnds()) {
      Collection<Property> keyProperties = new ArrayList<Property>(2);
      Collection<Property> parentKeyProperties = new ArrayList<Property>(2);
      Collection<PropertyImpl> inputProperties = null;
      org.eclipse.uml2.Property p = (org.eclipse.uml2.Property)o;
      Type t = p.getType();
      // Set the foreign key as persistent if it has a <<Persistent>> stereotype
      // and not a <<Lazy>> stereotype. The <<Lazy>> stereotype indicates this
      // is cross-database and hence not actually a foreign key to generate.
      boolean isPersistentProperty =
        p.getAppliedStereotype(PERSISTENT) != null
            && p.getAppliedStereotype(LAZY) == null;
      Dto dto = facade.transformTypeToDto(t);

      if (dto != null) {
        // Get the object name, the key type, class name, and
        // package name of the associated class.
        String objectName =
          StringUtils.uncapitalize(StringUtilities.getUniqueName(p));
        String keyType = facade.getKeyType(t).toString();
        String typeName = dto.getName();
        String packageName = dto.getPackageName();
        String businessPackageName = null;
        String subsystemName = null;
        if (dto.getSubsystem() != null) {
          businessPackageName = dto.getSubsystem().getBusinessPackageName();
          subsystemName = dto.getSubsystem().getFullyQualifiedName();
        }
        String tableName = dto.getSqlTableName();

        String parentName = null;
        String prefix = p.getName();
        if (keyType.equals(KeyType.COMPOSITE.toString())
            && dto.getCompositeParent() != null) {
          // Composite; get parent name
          parentName = dto.getCompositeParent().getName();
        }

        inputProperties = dto.getKeyProperties();

        int index = 0; // index for multiple key attrs
        for (PropertyImpl keyProperty : inputProperties) {
          // Copy the property to create a new property.
          PropertyImpl keyPropertyCopy = null;
          try {
            keyPropertyCopy = new PropertyImpl(keyProperty);
          } catch (InvalidParametersException e) {
            // Log and fail with a runtime exception.
            logger.error("Cannot copy null property", e);
            throw new RuntimeException(e.getMessage(), e);
          }

          // Set the source.
          keyPropertyCopy.setSource(keyPropertyCopy.getSource()
                                    + " + getAssociatedKeys");
          // Set the prefix to the property name.
          keyPropertyCopy.setPrefix(p.getName());

          // Add the property-name prefix to the list of prefixes.
          List<PropertyPrefix> prefixList = keyPropertyCopy.getPrefixes();
          if (prefixList == null) {
            // Create a new prefix list.
            prefixList = new ArrayList<PropertyPrefix>(1);
            keyPropertyCopy.setPrefixes(prefixList);
          }
          prefixList.add(new PropertyPrefix(p.getName()));

          // Set the owner type to be the association class.
          keyPropertyCopy.setOwnerType(metaObject);

          keyProperties.add(keyPropertyCopy);
          // If the property is a parent key attribute, save in that list too.
          if (keyType.equals(KeyType.COMPOSITE.toString())
              && keyPropertyCopy.isParentKey()) {
            parentKeyProperties.add(keyPropertyCopy);
          }

          // Set the explicit name for the property to any name specified in
          // the association end, overriding any explicit name set in the
          // property directly (in the class for the key).
          String explicitName =
            StringUtilities.getExplicitPropertyName(p, index);
          if (explicitName != null && explicitName.length() > 0) {
            keyPropertyCopy.setExplicitName(explicitName);
          }

          // If the association has no association key and this data member
          // is one of the generated association foreign keys, set the key
          // attribute in the key properties to false as they are not part
          // of the primary key of the association class.
          boolean isGenerated =
            keyPropertyCopy.getSource().contains("AddAssociationKeyAttributeProperties")
                || keyPropertyCopy.getSource().contains("AddGeneratedKeyProperties")
                || keyPropertyCopy.getSource().contains("AddGeneratedGuidKeyProperties");
          if (!hasAssociationKey && isGenerated) {
            keyPropertyCopy.setKey(false);
          }
        }

        // The name of the key is always the property name; the keyProperties
        // list provides the explicit names for the possibly multiple key
        // attributes/SQL columns.

        AssociatedKey key =
          new AssociatedKeyImpl(p.getName(),
                                keyType,
                                parentName,
                                prefix,
                                typeName,
                                packageName,
                                tableName,
                                true,
                                objectName,
                                businessPackageName,
                                subsystemName,
                                false,
                                isPersistentProperty,
                                dto.isReadWrite(),
                                dto,
                                keyProperties,
                                parentKeyProperties);
        keys.add(key);
      }
    }

    return keys;
  }

  /**
   * Get the primary key properties of the association class. These will either
   * be the set of association key attributes or the inherited primary key from
   * a superclass.
   * 
   * @return the list of primary key properties
   */
  public List<Property> getPrimaryKeyProperties() {
    List<Property> list = new ArrayList<Property>(2);
    IAddPropertyStrategy strategy = null;

    if (metaObject.getGeneralizations().size() > 0) {
      // Superclass, so get inherited key properties
      strategy = facade.new AddInheritedKeyProperties();
    } else {
      // No superclass, so get association key properties
      strategy = new AddAssociationKeyAttributeProperties();
    }

    strategy.addProperties(list);

    // Set the owning DTO and source in the properties. Property type already
    // set.
    for (Property p : list) {
      ((PropertyImpl)p).setOwnerType(metaObject);
      p.setSource(p.getSource() + "+ getPrimaryKeyProperties");
      ((PropertyImpl)p).createDto();
      ((PropertyImpl)p).createOwner();
      ((PropertyImpl)p).createInheritedFrom();
    }

    return list;
  }

  /**
   * Get a list of Persistent Property metafacades representing the properties
   * corresponding to the association.
   * 
   * @return a list of Persistent Property facades
   */
  private List<PersistentPropertyFacadeLogicImpl> getAssociatedProperties() {
    List<PersistentPropertyFacadeLogicImpl> list =
      new ArrayList<PersistentPropertyFacadeLogicImpl>();

    for (Object o : metaObject.getMemberEnds()) {
      PersistentPropertyFacadeLogicImpl facade =
        new PersistentPropertyFacadeLogicImpl((org.eclipse.uml2.impl.PropertyImpl)o,
                                              CONTEXT);
      list.add(facade);
    }

    return list;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.andromda.metafacades.uml.AssociationFacade#getAssociationEndA()
   */
  public AssociationEndFacade getAssociationEndA() {
    AssociationEndFacade facade = null;
    for (Object o : getAssociationEnds()) {
      facade = (AssociationEndFacade)o;
      break; // get first one only
    }
    return facade;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.andromda.metafacades.uml.AssociationFacade#getAssociationEndB()
   */
  public AssociationEndFacade getAssociationEndB() {
    AssociationEndFacade facade = null;
    int count = 0;
    for (Object o : getAssociationEnds()) {
      facade = (AssociationEndFacade)o;
      count++;
      if (count == 2) {
        break; // get second one only
      }
    }
    if (count != 2) {
      // There was only one association end, no B
      facade = null;
    }
    return facade;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.andromda.metafacades.uml.AssociationFacade#getRelationName()
   */
  public String getRelationName() {
    // Just return the name of the assocation class.
    return getName();
  }

  /**
   * Get the SQL table name of the association class.
   * 
   * @return the SQL table name
   */
  String getTableName() {
    return facade.getTableName(metaObject);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.andromda.metafacades.uml.AssociationFacade#isMany2Many()
   */
  public boolean isMany2Many() {
    boolean retVal = false;
    @SuppressWarnings("rawtypes")
    List list = getAssociationEnds();
    if (list.size() >= 2) {
      AssociationEndFacade end1 = (AssociationEndFacade)list.get(0);
      AssociationEndFacade end2 = (AssociationEndFacade)list.get(1);
      if (end1.isMany() && end2.isMany()) {
        retVal = true;
      }
    }
    return retVal;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.poesys.cartridges.db.metafacades.PersistentAssociationClassFacadeLogic
   * #handleIsChild()
   */
  @Override
  protected boolean handleIsChild() {
    // An association class is considered a child of its associated classes.
    return true;
  }

  /*
   * (non-Javadoc)
   * 
   * Implements the isReadOnly metafacade method
   * 
   * @see
   * com.poesys.cartridges.db.metafacades.PersistentAssociationClassFacadeLogic
   * #handleIsReadOnly()
   */
  @Override
  protected boolean handleIsReadOnly() {
    return facade.handleIsReadOnly();
  }

  /*
   * (non-Javadoc)
   * 
   * Implements the isImmutable metafacade method
   * 
   * @see
   * com.poesys.cartridges.db.metafacades.PersistentAssociationClassFacadeLogic
   * #handleIsImmutable()
   */
  @Override
  protected boolean handleIsImmutable() {
    return facade.handleIsImmutable();
  }

  /*
   * (non-Javadoc)
   * 
   * Implements the isUnremovable metafacade method
   * 
   * @see
   * com.poesys.cartridges.db.metafacades.PersistentAssociationClassFacadeLogic
   * #handleIsUnremovable()
   */
  @Override
  protected boolean handleIsUnremovable() {
    return facade.handleIsUnremovable();
  }

  /**
   * Get the fully qualified type name, including any interface prefix.
   * 
   * @param t the type name
   * @return the fully qualified type name
   */
  /*
   * private String getFullName(Type t) { String fullName = null; if (t
   * instanceof AssociationClass) { PersistentAssociationClassFacadeLogicImpl
   * classFacade = new
   * PersistentAssociationClassFacadeLogicImpl((AssociationClass)t, CONTEXT);
   * fullName = classFacade.getFullyQualifiedName(); } else if (t instanceof
   * ClassImpl) { fullName = StringUtilities.getQualifiedExtendedTypeName(t,
   * getConfiguredProperty(UMLMetafacadeProperties.NAMESPACE_SEPARATOR),
   * getLanguageMappings(), getConfiguredProperty(INTERFACE)); } return
   * fullName; }
   */

  private String getFullName(Type t) {
    return StringUtilities.getQualifiedExtendedTypeName(t,
                                                        getConfiguredProperty(UMLMetafacadeProperties.NAMESPACE_SEPARATOR),
                                                        getLanguageMappings(),
                                                        getConfiguredProperty(INTERFACE));
  }

  /**
   * Is the association class represented by the Type a lazy-loading object?
   * 
   * @param t an association class type
   * @return true if the association class objects should load lazily rather
   *         than eagerly (false)
   */
  private Boolean isLazy(Type t) {
    Boolean isLazy = new Boolean(false);

    if (t instanceof AssociationClass) {
      Stereotype stereotype = t.getAppliedStereotype(ASSOCIATION);
      isLazy =
        (Boolean)t.getValue(stereotype, PoesysDbProfile.TAGGEDVALUE_LAZY);
    }
    return isLazy;
  }
}