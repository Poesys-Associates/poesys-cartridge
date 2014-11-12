package com.poesys.cartridges.db.metafacades;


import java.util.List;

import org.andromda.metafacades.uml.TypeMappings;
import org.andromda.metafacades.uml.UMLMetafacadeProperties;
import org.andromda.metafacades.uml.UMLProfile;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.uml2.AssociationClass;
import org.eclipse.uml2.DataType;
import org.eclipse.uml2.Property;
import org.eclipse.uml2.Stereotype;
import org.eclipse.uml2.Type;
import org.eclipse.uml2.ValueSpecification;
import org.eclipse.uml2.impl.ClassImpl;
import org.eclipse.uml2.impl.ModelImpl;

import com.poesys.cartridges.db.profile.PoesysDbProfile;
import com.poesys.cartridges.db.psm.db.KeyType;
import com.poesys.cartridges.db.psm.db.PropertyImpl;
import com.poesys.cartridges.db.psm.db.Subsystem;
import com.poesys.cartridges.db.utilities.DatabaseUtilities;
import com.poesys.cartridges.db.utilities.StereotypeUtilities;
import com.poesys.cartridges.db.utilities.StringUtilities;


/**
 * MetafacadeLogic implementation for
 * com.poesys.cartridges.db.metafacades.PersistentPropertyFacade.
 * 
 * @see com.poesys.cartridges.db.metafacades.PersistentPropertyFacade
 */
public class PersistentPropertyFacadeLogicImpl extends
    PersistentPropertyFacadeLogic {
  /** Logger for this class */
  Logger logger = Logger.getLogger(PersistentPropertyFacadeLogicImpl.class);

  /**
   * AndroMDA context string for error reporting
   */
  private static final String CONTEXT =
    PersistentPropertyFacadeLogicImpl.class.getName();

  /** The string that prefixes the getter accessor for a non-boolean property. */
  private static final String GETTER_PREFIX = "get";

  /** The string that prefixes the getter accessor for a boolean property. */
  private static final String BOOLEAN_GETTER_PREFIX = "is";

  /** The string that prefixes the setter accessor for the property. */
  private static final String SETTER_PREFIX = "set";

  /** Property indicating whether to use an interface for an association type */
  private static final String INTERFACE = "useInterfaceForAssociation";

  /** Persistent stereotype name */
  private static final String PERSISTENT =
    PoesysDbProfile.NAMESPACE_PERSISTENCE
        + PoesysDbProfile.STEREOTYPE_PERSISTENT;

  /** Lazy stereotype name */
  private static final String LAZY = PoesysDbProfile.NAMESPACE_PERSISTENCE
                                     + PoesysDbProfile.STEREOTYPE_LAZY;

  /** Immutable stereotype name */
  private static final String IMMUTABLE =
    PoesysDbProfile.NAMESPACE_PERSISTENCE
        + PoesysDbProfile.STEREOTYPE_IMMUTABLE;

  /** Unremovable stereotype name */
  private static final String UNREMOVABLE =
    PoesysDbProfile.NAMESPACE_PERSISTENCE
        + PoesysDbProfile.STEREOTYPE_UNREMOVABLE;

  /** BooleanString stereotype name */
  private static final String BOOLEAN_STRING =
    PoesysDbProfile.NAMESPACE_PERSISTENCE
        + PoesysDbProfile.STEREOTYPE_BOOLEAN_STRING;

  /**
   * Create a PersistentPropertyFacadeLogicImpl object.
   * 
   * @param metaObject the Property object the metafacade wraps
   * @param context the context creating the metafacade
   */
  public PersistentPropertyFacadeLogicImpl(org.eclipse.uml2.Property metaObject,
                                           String context) {
    super(metaObject, context);
  }

  /*
   * (non-Javadoc)
   * 
   * @seecom.poesys.cartridges.db.metafacades.PersistentPropertyFacadeLogic#
   * handleGetType()
   */
  protected Type handleGetType() {
    return metaObject.getType();
  }

  /**
   * Get the subsystem to which the class belongs, which is the UML package that
   * contains the class.
   * 
   * @return the subsystem
   */
  private Subsystem getSubsystem() {
    Subsystem subsystem = null;

    // Instantiate a subsystem with the package of the end type that has the
    // stereotype SUBSYSTEM.
    org.eclipse.uml2.Package umlPackage = metaObject.getType().getPackage();
    while (umlPackage.getAppliedStereotype(PoesysDbProfile.NAMESPACE_PERSISTENCE
                                           + PoesysDbProfile.STEREOTYPE_SUBSYSTEM) == null) {
      umlPackage = umlPackage.getNestingPackage();
      if (umlPackage instanceof ModelImpl || umlPackage == null) {
        // Reached the top level Model, end here with a null package.
        umlPackage = null;
        break;
      }
    }

    // If there is a valid subsystem package, produce a Subsystem.
    if (umlPackage != null) {
      SubsystemFacadeLogicImpl facade =
        new SubsystemFacadeLogicImpl(umlPackage, CONTEXT);
      subsystem = facade.handleTransformToSubsystem();
    } else {
      subsystem =
        new Subsystem(PoesysDbProfile.DEFAULT_SUBSYSTEM,
                      PoesysDbProfile.DEFAULT_SUBSYSTEM,
                      "Default subsystem name",
                      StringUtils.capitalize(PoesysDbProfile.DEFAULT_SUBSYSTEM),
                      StringUtils.capitalize(PoesysDbProfile.DEFAULT_SUBSYSTEM),
                      false,
                      StereotypeUtilities.CACHED_VALUE,
                      null,
                      null,
                      null);
    }
    return subsystem;
  }

  /*
   * (non-Javadoc)
   * 
   * @seecom.poesys.cartridges.db.metafacades.PersistentPropertyFacadeLogic#
   * handleGetGetterSetterTypeName()
   */
  protected java.lang.String handleGetGetterSetterTypeName() {
    Type type = getType();
    String name = null;
    Property property = (Property)getMetaObject();
    int upperMultiplicityElement = property.getUpper();
    // Multiplicity of >1 or -1 means a collection type, 1 means an object.
    if (upperMultiplicityElement != 1) {
      // Anything other than 1 (including -1 meaning "*") means a collection.
      TypeMappings mappings = getLanguageMappings();

      // Produce either an ordered list type or an unordered collection type
      // depending on whether the property is ordered.
      name =
        property.isOrdered() ? mappings.getTo(UMLProfile.LIST_TYPE_NAME)
            : mappings.getTo(UMLProfile.COLLECTION_TYPE_NAME);

      // Add the type as a parameter to the collection type if required. If the
      // useInterfaceForAssociation property is set to true, prefix the type
      // name with I to use the interface for the type as the template parameter
      // type.
      Object p =
        getConfiguredProperty(UMLMetafacadeProperties.ENABLE_TEMPLATING);
      Boolean enableTemplating =
        BooleanUtils.toBoolean(ObjectUtils.toString(p));
      if (enableTemplating) {
        name = name + "<" + getQualifiedTypeName(type) + ">";
      }
    } else {
      name = getQualifiedTypeName(type);
    }
    return name;
  }

  /**
   * Get the fully qualified type name based on a specified data type.
   * 
   * @param type the UML type
   * @return the fully qualified language-specific type
   */
  protected java.lang.String handleGetQualifiedTypeName(Type type) {
    return StringUtilities.getQualifiedExtendedTypeName(type,
                                                        getConfiguredProperty(UMLMetafacadeProperties.NAMESPACE_SEPARATOR),
                                                        getLanguageMappings(),
                                                        getConfiguredProperty(INTERFACE));
  }

  /*
   * (non-Javadoc)
   * 
   * @seecom.poesys.cartridges.db.metafacades.PersistentPropertyFacadeLogic#
   * handleGetGetterName()
   */
  protected java.lang.String handleGetGetterName() {
    String prefix = GETTER_PREFIX;
    Type type = getType();
    if (type.getName().equalsIgnoreCase(UMLProfile.BOOLEAN_TYPE_NAME)) {
      prefix = BOOLEAN_GETTER_PREFIX;
    }
    return prefix + StringUtils.capitalize(getName());
  }

  /*
   * (non-Javadoc)
   * 
   * @seecom.poesys.cartridges.db.metafacades.PersistentPropertyFacadeLogic#
   * handleGetSetterName()
   */
  protected java.lang.String handleGetSetterName() {
    return SETTER_PREFIX + StringUtils.capitalize(getName());
  }

  /*
   * (non-Javadoc)
   * 
   * @seecom.poesys.cartridges.db.metafacades.PersistentPropertyFacadeLogic#
   * handleTransformToAccessor()
   */
  protected com.poesys.cartridges.db.psm.db.Property handleTransformToProperty() {
    String group =
      (String)findTaggedValue(PoesysDbProfile.TAGGEDVALUE_UNIQUE_GROUP);
    boolean isAssociationEnd = metaObject.getAssociation() != null;

    boolean immutable = false;
    boolean removable = true;
    String classKeyType = null;
    String bsDataType = getType().getName();
    boolean lazy = metaObject.getAppliedStereotype(LAZY) != null;
    Subsystem subsystem = getSubsystem();
    Type propertyType = null;

    if (getType() instanceof DataType) {
      // The business-layer data type is the primitive type itself, use
      // type mappings to translate to appropriate Java type.
      bsDataType = handleGetQualifiedTypeName(getType());
      // Keep the property type null, as this is a primitive, not an object.
    } else {
      // Set the property type to the type.
      propertyType = getType();
      // Determine the flags through the type class.
      immutable = propertyType.getAppliedStereotype(IMMUTABLE) != null;
      removable = propertyType.getAppliedStereotype(UNREMOVABLE) == null;
      // Get the key type.
      classKeyType = getKeyType(propertyType).toString();
      // Get the object type for the business layer.
      bsDataType =
        StringUtilities.getBsTypeName(propertyType,
                                      subsystem.getBusinessPackageName());
    }

    String className =
      StringUtilities.getQualifiedExtendedTypeName(getType(),
                                                   getConfiguredProperty(UMLMetafacadeProperties.NAMESPACE_SEPARATOR),
                                                   getLanguageMappings(),
                                                   getConfiguredProperty(INTERFACE));

    String tableName = getTableName();
    String explicitName = StringUtilities.getExplicitPropertyName(metaObject);
    String objectName = StringUtilities.getExplicitObjectName(metaObject);

    // Get any Boolean string representation.
    boolean isBooleanString = false;
    String booleanTrue = null;
    String booleanFalse = null;
    if (metaObject.getAppliedStereotype(BOOLEAN_STRING) != null) {
      isBooleanString = true;
      List<String> representation =
        StringUtilities.getBooleanRepresentation(metaObject);
      booleanTrue = representation.get(0);
      booleanFalse = representation.get(1);
    }

    // Transform the readOnly to readWrite by negation.
    com.poesys.cartridges.db.psm.db.PropertyImpl property =
      new PropertyImpl(getName(),
                       group,
                       getLength(),
                       getPrecision(),
                       getScale(),
                       !getReadOnly(),
                       hasStereotype(PoesysDbProfile.STEREOTYPE_PK),
                       getGetterSetterTypeName(),
                       getDocumentation("   * ", 80),
                       getVisibility(),
                       isToMany(),
                       isRequired(),
                       getDefaultValue(),
                       getDocumentation("", 80, false),
                       getSqlType(),
                       getKeyColType(getGetterSetterTypeName()),
                       subsystem.getName(),
                       isAssociationEnd,
                       "TransformToProperty",
                       tableName,
                       lazy,
                       isChild(),
                       className,
                       immutable,
                       removable,
                       classKeyType,
                       metaObject.isOrdered(),
                       getName(),
                       getFetchSize(),
                       getBatchSize(),
                       bsDataType,
                       DatabaseUtilities.getAnsiSqlType(StringUtilities.getQualifiedType(getType())),
                       DatabaseUtilities.getMySql51SqlType(StringUtilities.getQualifiedType(getType())),
                       DatabaseUtilities.getOracle11SqlType(StringUtilities.getQualifiedType(getType())),
                       DatabaseUtilities.getSybaseAse125SqlType(StringUtilities.getQualifiedType(getType())),
                       false,
                       explicitName,
                       metaObject.getName(),
                       isBooleanString,
                       booleanTrue,
                       booleanFalse,
                       objectName);
    // Set the property type as previously determined.
    property.setPropertyType(propertyType);
    // Set the owner type as the property owner, guaranteed to be a Type.
    property.setOwnerType((Type)metaObject.getOwner());

    return property;
  }

  /**
   * Get the batchSize tagged value for the property. Default to 100.
   * 
   * @return the batch size for the property
   */
  private Integer getBatchSize() {
    // Get the precision tagged value, if any.
    Integer batchSize = new Integer(100);
    String batchSizeString =
      (String)findTaggedValue(PoesysDbProfile.TAGGEDVALUE_BATCH_SIZE);
    if (batchSizeString != null) {
      batchSize = new Integer(batchSizeString);
    }
    return batchSize;
  }

  /**
   * Get the fetchSize tagged value for the property. Default to 10.
   * 
   * @return the fetch size for the property
   */
  private Integer getFetchSize() {
    // Get the precision tagged value, if any.
    Integer fetchSize = new Integer(10);
    String fetchSizeString =
      (String)findTaggedValue(PoesysDbProfile.TAGGEDVALUE_FETCH_SIZE);
    if (fetchSizeString != null) {
      fetchSize = new Integer(fetchSizeString);
    }
    return fetchSize;
  }

  /**
   * Is the property a composite aggregation child? A composite aggregation
   * child is the child side of a parent-child composite aggregation
   * association.
   * 
   * @return true if the property is a child, false otherwise
   */
  private Boolean isChild() {
    Boolean child = Boolean.FALSE;
    if (metaObject.getAssociation() != null && metaObject.isComposite()) {
      child = Boolean.TRUE;
    }
    return child;
  }

  /**
   * Get the SQL table name of the class that owns this property.
   * 
   * @return the SQL table name of the owning class
   */
  private String getTableName() {
    String tableName = null;
    if (metaObject.getOwner() instanceof org.eclipse.uml2.Class) {
      // Owned by a class
      org.eclipse.uml2.Class umlClass =
        (org.eclipse.uml2.Class)metaObject.getOwner();
      if (umlClass.getAppliedStereotype(PERSISTENT) != null) {
        // Owned by a Persistent class
        if (umlClass instanceof AssociationClass) {
          // Association class
          PersistentAssociationClassFacadeLogicImpl classFacade =
            new PersistentAssociationClassFacadeLogicImpl((AssociationClass)umlClass,
                                                          CONTEXT);
          tableName = classFacade.getTableName();
        } else if (umlClass instanceof ClassImpl) {
          // Regular class
          PersistentClassFacadeLogicImpl classFacade =
            new PersistentClassFacadeLogicImpl((ClassImpl)umlClass, CONTEXT);
          tableName = classFacade.getTableName(umlClass);
        }
      }
    }

    return tableName;
  }

  /**
   * Get the type of key of a type. The stereotypes on the type determine the
   * key type; the method takes the first stereotype of a key type and returns
   * that.
   * 
   * @param t the type
   * 
   * @return the key type based on stereotypes or NONE
   */
  private KeyType getKeyType(Type t) {
    KeyType type = null;
    for (Object o : t.getAppliedStereotypes()) {
      Stereotype s = (Stereotype)o;
      type = KeyType.stringValue(s.getName());
      if (type != null) {
        break;
      }
    }

    if (type == null) {
      type = KeyType.NONE;
    }

    return type;
  }

  /**
   * Get the AbstractColumnValue concrete subtype for the property.
   * 
   * @param type the Java data type of the property
   * @return the Poesys/DB AbstractColumnValue concrete subclass
   */
  private String getKeyColType(String type) {
    String colType = null;
    try {
      colType = DatabaseUtilities.getColumnType(type);
    } catch (RuntimeException e) {
      // An unsupported column type for keys, ignore and return null.
    }
    return colType;
  }

  /**
   * Get the SQL data type of the column corresponding to the property.
   * 
   * @return a SQL data type
   */
  private String getSqlType() {
    return getSqlMappings().getTo(StringUtilities.getQualifiedType(metaObject.getType()));
  }

  /**
   * Get the default value code fragment for the property, if defined.
   * 
   * @return a default value Java code fragment or null
   */
  private String getDefaultValue() {
    return metaObject.getDefault();
  }

  /**
   * Is the property required? That is, must the property not be null? This flag
   * comes from the UML multiplicity lower bound; if it is less than or equal to
   * 0, the property is not required, and if it is greater than 0, it is
   * required. If there is no multiplicity specified, the property is not
   * required.
   * 
   * @return true if the property is required, false otherwise
   */
  private Boolean isRequired() {
    int lower = 0;
    ValueSpecification vs = metaObject.getLowerValue();
    if (vs != null) {
      try {
        lower = vs.integerValue();
      } catch (RuntimeException e) {
        // Probably UnsupportedOperationException
        org.eclipse.uml2.Class dto =
          (org.eclipse.uml2.Class)metaObject.getOwner();
        logger.warn("Problem with property multiplicity on property "
                    + this.getName() + " in class " + dto.getQualifiedName()
                    + ": value " + vs.stringValue().toString());
      }
    }
    return lower > 0 ? true : false;
  }

  /**
   * Get the read-only flag for the Persistent property. Set the flag to true if
   * the PK stereotype is set for the property. Otherwise, determine the status
   * from the tagged values of the property and its owning class. If the owning
   * class is read only, the property is read only. If the owning class is
   * read-write, then the tagged value on the property sets the status. If
   * neither tagged value is set, default to false.
   * 
   * @return the read only flag set in the tagged value or false if not set
   */
  private Boolean getReadOnly() {
    Boolean readOnly = false;
    // Get the PK stereotype if it's there.
    boolean isPrimaryKey = hasStereotype(PoesysDbProfile.STEREOTYPE_PK);

    if (isPrimaryKey) {
      // Set the flag to true for all PK properties.
      readOnly = true;
    } else {
      // First check the read-only tag on the owning class.
      Stereotype s = metaObject.getOwner().getAppliedStereotype(PERSISTENT);
      if (s != null) {
        readOnly =
          (Boolean)metaObject.getOwner().getValue(s,
                                                  PoesysDbProfile.TAGGEDVALUE_READ_ONLY);
        if (!readOnly) {
          // read-write, check property tag
          readOnly = getPropertyReadOnlyTagValue(readOnly);
        }
      } else {
        readOnly = getPropertyReadOnlyTagValue(readOnly);
      }
    }
    return readOnly;
  }

  /**
   * Get the readOnly status of the property. If there is a Persistent
   * stereotype and the readOnly tagged value is set, return that value;
   * otherwise, return the input value.
   * 
   * @param readOnly the read-only status of the property
   * @return
   */
  private Boolean getPropertyReadOnlyTagValue(Boolean readOnly) {
    Stereotype s;
    // Get the read-only flag from the property tagged value.
    s = metaObject.getAppliedStereotype(PERSISTENT);
    if (s != null) {
      try {
        readOnly =
          (Boolean)metaObject.getValue(s, PoesysDbProfile.TAGGEDVALUE_READ_ONLY);
      } catch (Exception e) {
        logger.error("Couldn't get readOnly tagged value from property Persistent stereotype",
                     e);
        logger.info("Setting readOnly to false for property "
                    + getFullyQualifiedName());
      }
    }
    return readOnly;
  }

  /**
   * Get the numeric scale (number of digits after the decimal point) from the
   * tagged value.
   * 
   * @return the scale
   * @throws NumberFormatException
   */
  private Integer getScale() throws NumberFormatException {
    // Get the scale tagged value, if any.
    Integer scale = null;
    String scaleStr =
      (String)findTaggedValue(PoesysDbProfile.TAGGEDVALUE_SCALE);
    if (scaleStr != null) {
      scale = new Integer(scaleStr);
    }
    return scale;
  }

  /**
   * Get the numeric precision (max number of digits) from the tagged value.
   * 
   * @return the precision
   * @throws NumberFormatException
   */
  private Integer getPrecision() throws NumberFormatException {
    // Get the precision tagged value, if any.
    Integer precision = null;
    String precString =
      (String)findTaggedValue(PoesysDbProfile.TAGGEDVALUE_PRECISION);
    if (precString != null) {
      precision = new Integer(precString);
    }
    return precision;
  }

  /**
   * Get the length (number of characters, bytes, etc.) from the SizedProperty
   * tagged value.
   * 
   * @return the length
   * @throws NumberFormatException
   */
  private Long getLength() throws NumberFormatException {
    // Get the length tagged value, if any.
    Long length = null;
    String lengthStr =
      (String)findTaggedValue(PoesysDbProfile.TAGGEDVALUE_LENGTH);
    if (lengthStr != null) {
      length = new Long(lengthStr);
    }
    return length;
  }

  /**
   * Gets the SQL mappings that have been set for this entity attribute.
   * 
   * @return the SQL Mappings instance.
   */
  private TypeMappings getSqlMappings() {
    final String propertyName = UMLMetafacadeProperties.SQL_MAPPINGS_URI;
    final Object property = getConfiguredProperty(propertyName);
    TypeMappings mappings = null;
    String uri;
    if (property instanceof String) {
      uri = (String)property;
      try {
        mappings = TypeMappings.getInstance(uri);
        setProperty(propertyName, mappings);
      } catch (Throwable throwable) {
        String errMsg =
          "Error getting '" + propertyName + "' --> '" + uri + "'";
        logger.error(errMsg, throwable);

        // Proceed without throwing the exception
      }
    } else {
      mappings = (TypeMappings)property;
    }
    return mappings;
  }

  @Override
  protected boolean handleIsToMany() {
    boolean toMany = false;

    // Set the flag based on the value of the upper multiplicity.
    Property property = (Property)getMetaObject();
    int upperMultiplicityElement = property.getUpper();
    if (upperMultiplicityElement != 1) {
      toMany = true;
    }
    return toMany;
  }
}