/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
/**
 * This is only generated once! It will never be overwritten. You can (and have
 * to!) safely modify it by hand.
 */
package com.poesys.cartridges.db.psm.db;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.uml2.AssociationClass;
import org.eclipse.uml2.Stereotype;
import org.eclipse.uml2.Type;
import org.eclipse.uml2.impl.ClassImpl;

import com.poesys.cartridges.db.InvalidParametersException;
import com.poesys.cartridges.db.metafacades.PersistentAssociationClassFacade;
import com.poesys.cartridges.db.metafacades.PersistentAssociationClassFacadeLogicImpl;
import com.poesys.cartridges.db.metafacades.PersistentClassFacade;
import com.poesys.cartridges.db.metafacades.PersistentClassFacadeLogicImpl;
import com.poesys.cartridges.db.utilities.StringUtilities;


/**
 * Concrete implementation of the Property PSM class.
 * 
 * @see com.poesys.cartridges.db.psm.db.Property
 */
public class PropertyImpl extends com.poesys.cartridges.db.psm.db.Property {
  /** Logger for this PSM class */
  private static Logger logger = Logger.getLogger(PropertyImpl.class);

  /** Constant name for object variable in JDBC setter call */
  private static final String OBJECT_VAR = "object";
  /** Constant name for index variable in JDBC setter call */
  private static final String INDEX_VAR = "index";
  /** Constant prefix for getter */
  private static final String GET = "get";
  /** Constant prefix for boolean getter */
  private static final String IS = "is";
  /** Constant prefix for setter */
  private static final String SET = "set";
  /** Constant boolean data type */
  private static final String BOOLEAN = "boolean";

  /** The model prefix to a fully-qualified UML data type name */
  private static final String UML_TYPE_PREFIX = "Data::";
  /** The UML namespace separator */
  private static final String UML_SEP = "::";
  /** The Java package separator */
  private static final String JAVA_SEP = ".";

  /**
   * The UML2 type of the property object (ClassImpl or AssociationClass), used
   * to create the property DTO for object properties after all the DTOs have
   * been created and cached
   */
  private Type propertyType;
  /**
   * The UML2 type of the DTO that owns the property, used to create the owner
   * DTO for all properties after all the DTOs have been created and cached
   */
  private Type ownerType;

  /**
   * The UML2 type of the DTO from which the current DTO inherited the property;
   * used in situations where you need to use the superclass rather than the
   * current class or the root class.
   */
  private Type inheritedFromType;

  /**
   * A static map of all the SQL-relevant property maps indexed by DTO name;
   * used to cache the property maps for name disambiguation as a performance
   * improvement.
   */
  private static final Map<String, Map<String, List<Property>>> sqlMap =
    new HashMap<String, Map<String, List<Property>>>();

  /** The metafacade context for instantiated metafacades */
  private static final String CONTEXT = PropertyImpl.class.getName();

  /**
   * Create a PropertyImpl object.
   */
  public PropertyImpl() {
    super();
  }

  /**
   * Create a PropertyImpl object.
   * 
   * @param name the property Java variable name
   * @param uniqueGroup the unique group to which the attribute belongs
   * @param length the length of a sized attribute
   * @param precision the precision of a numeric attribute
   * @param scale the scale of a numeric attribute
   * @param readWrite whether the property is read/write
   * @param key whether the attribute is a key
   * @param typeName the data type name of the property
   * @param documentation the documentation for the property
   * @param visibility the visibility of the accessors
   * @param toMany whether the property is a collection
   * @param required whether the property must not be null
   * @param defaultValue the default value to assign to the property if null
   * @param paramTag the comment to put into the javadoc param tag for the
   *          property
   * @param sqlColumnType the SQL column type for the attribute value from a
   *          query
   * @param pkColType the AbstractColumnValue concrete subclass for the property
   * @param subsystem the name of the subsystem of the class that contains the
   *          property property value
   * @param associationEnd whether the property is an association end (true) or
   *          an attribute (false)
   * @param source the Add method that added the property; for debugging
   *          multiple-property problems
   * @param tableName the name of the SQL table that contains the property (for
   *          use as an alias in SQL expressions)
   * @param lazy whether to load the property lazily or eagerly
   * @param child whether the property is a composite aggregation child
   * @param className the name of the class of the object the property contains;
   *          same as the type name for single-object properties, embedded in
   *          the template instance for collections
   * @param immutable whether the application can change the object through this
   *          property (based on whether the property's class is immutable)
   * @param removable whether the application can delete the object through this
   *          property (based on whether the property's class is removable)
   * @param classKeyType the key type of the class of the object; null if the
   *          property has a data type rather than a persistent object type
   * @param ordered whether the property is an ordered list (true) or an
   *          unordered collection or single object (false)
   * @param originalName the original name of the property before it was
   *          modified to handle duplicate names; used also in mapping a foreign
   *          key back to the primary key of the target class
   * @param fetchSize the number of rows to fetch at once when querying this
   *          prooperty
   * @param batchSize the number of rows to process at once when processing this
   *          property
   * @param bsTypeName the business-layer type name for the property value; same
   *          as typeName for primitive types, otherwise business-DTO name
   * @param sqlTypeAnsi the ANSI SQL data type for the column
   * @param sqlTypeMySql51 the MySQL 5.1 data type for the column
   * @param sqlTypeOracle11g the Oracle11g data type for the column
   * @param sqlTypeSybaseAse125 the Sybase ASE 12.5 data type for the column
   * @param parentKey whether this property is a parent key (composite
   *          aggregation target foreign key to parent)
   * @param explicitName the explicitly specified name for the property
   * @param prefix the prefix for generating a unique property name
   * @param booleanString whether this property represents a Boolean value that
   *          has a string representation in the persistent store
   * @param booleanTrue boolean true value in String form
   * @param booleanFalse boolean false value in String form
   * @param objectName if the property is an association end on the
   *          associated-class side of an association to an association class,
   *          this name represents the name of the PSM object class that
   *          represents the object in the PSM association class; it allows
   *          specifying a singular name for that object and a plural
   *          association end name for the collection of objects in associated
   *          classes
   */
  public PropertyImpl(java.lang.String name,
                      java.lang.String uniqueGroup,
                      java.lang.Long length,
                      java.lang.Integer precision,
                      java.lang.Integer scale,
                      java.lang.Boolean readWrite,
                      java.lang.Boolean key,
                      java.lang.String typeName,
                      java.lang.String documentation,
                      java.lang.String visibility,
                      java.lang.Boolean toMany,
                      java.lang.Boolean required,
                      java.lang.String defaultValue,
                      java.lang.String paramTag,
                      java.lang.String sqlColumnType,
                      java.lang.String pkColType,
                      java.lang.String subsystem,
                      boolean associationEnd,
                      java.lang.String source,
                      java.lang.String tableName,
                      boolean lazy,
                      boolean child,
                      String className,
                      boolean immutable,
                      boolean removable,
                      String classKeyType,
                      boolean ordered,
                      String originalName,
                      int fetchSize,
                      int batchSize,
                      String bsTypeName,
                      String sqlTypeAnsi,
                      String sqlTypeMySql51,
                      String sqlTypeOracle11g,
                      String sqlTypeSybaseAse125,
                      boolean parentKey,
                      String explicitName,
                      String prefix,
                      boolean booleanString,
                      String booleanTrue,
                      String booleanFalse,
                      String objectName) {
    super(name, uniqueGroup, length, precision, scale, readWrite, key,
          typeName, documentation, visibility, toMany, required, defaultValue,
          paramTag, sqlColumnType, pkColType, subsystem == null ? ""
              : subsystem, associationEnd, source, tableName, lazy, child,
          className, immutable, removable,
          classKeyType == null ? KeyType.NONE.toString() : classKeyType,
          ordered, originalName, fetchSize, batchSize, bsTypeName, sqlTypeAnsi,
          sqlTypeMySql51, sqlTypeOracle11g, sqlTypeSybaseAse125, parentKey,
          explicitName, prefix, booleanString, booleanTrue, booleanFalse,
          objectName);
    if (defaultValue == null || defaultValue.length() == 0) {
      if (typeName.equals("boolean")) {
        this.defaultValue = "false";
      } else if (typeName.equals("byte[]")) {
        this.defaultValue = "null";
      } else {
        this.defaultValue = "null";
      }
    }
  }

  /**
   * Copy-constructor from other Property; needs to be explicitly updated if you
   * add fields to the constructor
   * 
   * @param otherBean the other property to copy, cannot be <code>null</code>
   * @throws InvalidParametersException when the property bean is null
   */
  @SuppressWarnings("unchecked")
  public PropertyImpl(Property otherBean) throws InvalidParametersException {
    if (otherBean == null) {
      throw new InvalidParametersException("Trying to copy a null property bean");
    }
    this.name = otherBean.name;
    this.uniqueGroup = otherBean.uniqueGroup;
    this.length = otherBean.length;
    this.precision = otherBean.precision;
    this.scale = otherBean.scale;
    this.readWrite = otherBean.readWrite;
    this.key = otherBean.key;
    this.typeName = otherBean.typeName;
    this.documentation = otherBean.documentation;
    this.visibility = otherBean.visibility;
    this.toMany = otherBean.toMany;
    this.required = otherBean.required;
    this.defaultValue = otherBean.defaultValue;
    this.paramTag = otherBean.paramTag;
    this.sqlType = otherBean.sqlType;
    this.pkColType = otherBean.pkColType;
    this.subsystem = otherBean.subsystem;
    this.associationEnd = otherBean.associationEnd;
    this.source = otherBean.source;
    this.tableName = otherBean.tableName;
    this.lazy = otherBean.lazy;
    this.child = otherBean.child;
    this.className = otherBean.className;
    this.immutable = otherBean.immutable;
    this.removable = otherBean.removable;
    this.classKeyType = otherBean.classKeyType;
    this.ordered = otherBean.ordered;
    this.dto = otherBean.dto;
    this.originalName = otherBean.originalName;
    this.fetchSize = otherBean.fetchSize;
    this.batchSize = otherBean.batchSize;
    this.propertyType = ((PropertyImpl)otherBean).propertyType;
    this.bsTypeName = otherBean.bsTypeName;
    this.sqlTypeAnsi = otherBean.sqlTypeAnsi;
    this.sqlTypeMySql51 = otherBean.sqlTypeMySql51;
    this.sqlTypeOracle11g = otherBean.sqlTypeOracle11g;
    this.sqlTypeSybaseAse125 = otherBean.sqlTypeSybaseAse125;
    this.parentKey = otherBean.parentKey;
    this.explicitName = otherBean.explicitName;
    this.prefix = otherBean.prefix;
    // Copy the prefix list, don't reuse the list object.
    if (otherBean.prefixes != null) {
      this.prefixes = new ArrayList<PropertyPrefix>(otherBean.prefixes);
    } else {
      this.prefixes = null;
    }
    this.ownerType = ((PropertyImpl)otherBean).ownerType;
    this.dto = (otherBean).dto;
    this.owner = otherBean.owner;
    this.booleanString = otherBean.booleanString;
    this.booleanFalse = otherBean.booleanFalse;
    this.booleanTrue = otherBean.booleanTrue;
  }

  /**
   * <p>
   * Map all the SQL columns and inherited columns in the shared map of
   * properties keyed on name. The getSqlColumnName() method (through the
   * mapSqlPropertyNames() method) uses this map to generate unique column names
   * on demand. The method indexes both the property name and the explicit name
   * (tagged value name) for all properties, since SQL column names may be
   * explicit.
   * </p>
   * <p>
   * SQL columns come from various sources:
   * <ul>
   * <li>generated key properties</li>
   * <li>local attributes</li>
   * <li>to-one association attributes (not objects)</li>
   * <li>inherited columns (any of the above in a superclass)</li>
   * </ul>
   * The to-many association attributes do not correspond to SQL columns, and
   * the generator ignores all association object (association end) properties.
   * </p>
   * <p>
   * The SQL column name comes from one of four possible situations:
   * <ol>
   * <li>explicit name with prefix to disambiguate duplicate names</li>
   * <li>property name with prefix to disambiguate duplicate names if there is
   * no explicit name</li>
   * <li>explicit name if there is no duplication</li>
   * <li>property name if there is no explicit name and no duplication</li>
   * </ol>
   * Putting an explicit name on a property thus forces the use of that name for
   * the SQL column corresponding to the property unless there is a duplicate
   * occurrence of the name in another property, in which case the generator
   * uses the prefix to disambiguate the name.
   * </p>
   * 
   * @param ownerName the name of the DTO that owns the properties
   * 
   * @see #getUniqueName()
   * @see #mapSqlPropertyNames(String)
   * @see #getSqlColumnName()
   */
  @SuppressWarnings("unchecked")
  private void mapAllSqlProperties(String ownerName) {
    // Create the property map for the current DTO for name disambiguation.
    if (ownerName != null && owner != null) {
      // Check the cache just to make sure it's not already there.
      if (sqlMap.get(ownerName) == null) {
        // Create the new property map.
        Map<String, List<Property>> propertyNames =
          new HashMap<String, List<Property>>();
        // Create a collection of all properties of the DTO.
        Collection<Property> allProperties = owner.getAllDtoProperties();

        for (Property p : allProperties) {
          // Exclude to-many properties and association ends.
          if (!p.toMany && p.associationEnd == false) {
            // Get the explicit name.
            String name = p.explicitName == null ? p.name : p.explicitName;
            addNameToMap(propertyNames, p, name);
          }
        }
        // Put the map into the SQL map.
        sqlMap.put(ownerName, propertyNames);
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
  private void addNameToMap(Map<String, List<Property>> propertyNames,
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
   * @see com.poesys.cartridges.db.psm.db.Property#prefix(java.lang.String)
   */
  @Override
  public void prefix(java.lang.String prefix) {
    name = StringUtilities.buildVariableName(name, prefix, null);
  }

  /**
   * @see com.poesys.cartridges.db.psm.db.Property#getGetterName()
   */
  @Override
  public java.lang.String getGetterName() {
    String prefix = GET;
    if (this.getTypeName().equalsIgnoreCase(BOOLEAN)) {
      prefix = IS;
    }
    return StringUtilities.buildVariableName(getUniqueName(), prefix, null);
  }

  /**
   * @see com.poesys.cartridges.db.psm.db.Property#getSetterName()
   */
  @Override
  public java.lang.String getSetterName() {
    return StringUtilities.buildVariableName(getUniqueName(), SET, null);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.poesys.cartridges.db.psm.db.Property#getJdbcGetCall()
   */
  @Override
  public String getJdbcGetCall() {
    if (typeName == null) {
      throw new RuntimeException("Null data type while getting JDBC get call");
    }
    if (getSqlColumnName() == null) {
      throw new RuntimeException("Null SQL column name while getting JDBC get call");
    }

    // Implement all supported types here. Any type that requires a dereference
    // must check for null with a conditional operator ?:. Also, any type that
    // can be null in the database and in Java must check for a null return
    // and set the result variable to null (except for String, which handles
    // the null return automatically).
    String call = null;
    if (typeName.equals("java.math.BigInteger")) {
      // Check for null before dereferencing.
      call =
        "rs.getBigDecimal(\"" + getSqlColumnName()
            + "\") == null ? null : rs.getBigDecimal(\"" + getSqlColumnName()
            + "\").toBigInteger();";
    } else if (typeName.equals("java.lang.String")) {
      call = "rs.getString(\"" + getSqlColumnName() + "\")";
    } else if (typeName.equals("java.util.UUID")) {
      call = "java.util.UUID.fromString(rs.getString(\"" + getSqlColumnName() + "\"))";
    } else if (typeName.equals("java.math.BigDecimal")) {
      if (required) {
        call = "rs.getBigDecimal(\"" + getSqlColumnName() + "\")";
      } else {
        call =
          "rs.getBigDecimal(\"" + getSqlColumnName()
              + "\");\n    if (rs.wasNull()) {\n      " + getUniqueName()
              + "Value = null;\n    }";
      }
    } else if (typeName.equals("java.lang.float")) {
      // JDBC float is really DOUBLE
      call = "rs.getDouble(\"" + getSqlColumnName() + "\")";
    } else if (typeName.equals("java.lang.Float")) {
      if (required) {
        call = "rs.getDouble(\"" + getSqlColumnName() + "\")";
      } else {
        call =
          "rs.getDouble(\"" + getSqlColumnName()
              + "\");\n    if (rs.wasNull()) {\n      " + getUniqueName()
              + "Value = null;\n    }";
      }
    } else if (typeName.equals("java.lang.double")) {
      call = "rs.getDouble(\"" + getSqlColumnName() + "\")";
    } else if (typeName.equals("java.lang.Double")) {
      if (required) {
        call = "rs.getDouble(\"" + getSqlColumnName() + "\")";
      } else {
        call =
          "rs.getDouble(\"" + getSqlColumnName()
              + "\");\n    if (rs.wasNull()) {\n      " + getUniqueName()
              + "Value = null;\n    }";
      }
    } else if (typeName.equalsIgnoreCase("java.lang.Integer")) {
      if (required) {
        call = "rs.getInt(\"" + getSqlColumnName() + "\")";
      } else {
        call =
          "rs.getInt(\"" + getSqlColumnName()
              + "\");\n    if (rs.wasNull()) {\n      " + getUniqueName()
              + "Value = null;\n    }";
      }
    } else if (typeName.equalsIgnoreCase("java.lang.Long")) {
      if (required) {
        call = "rs.getLong(\"" + getSqlColumnName() + "\")";
      } else {
        call =
          "rs.getLong(\"" + getSqlColumnName()
              + "\");\n    if (rs.wasNull()) {\n      " + getUniqueName()
              + "Value = null;\n    }";
      }
    } else if (isBooleanString()) {
      // Boolean but has string representation in database, might be null
      call =
        "rs.getString(\"" + getSqlColumnName()
            + "\") == null ? null : rs.getString(\"" + getSqlColumnName()
            + "\").equalsIgnoreCase(\"" + booleanTrue + "\") ? true : false";
    } else if (typeName.equals("java.lang.boolean")) {
      call = "rs.getBoolean(\"" + getSqlColumnName() + "\")";
    } else if (typeName.equals("java.lang.Boolean")) {
      if (required) {
        call = "rs.getBoolean(\"" + getSqlColumnName() + "\")";
      } else {
        call =
          "rs.getBoolean(\"" + getSqlColumnName()
              + "\");\n    if (rs.wasNull()) {\n      " + getUniqueName()
              + "Value = null;\n    }";
      }
    } else if (typeName.equals("java.sql.Blob")) {
      call = "rs.getBlob(\"" + getSqlColumnName() + "\")";
    } else if (typeName.equals("java.sql.Date")) {
      if (required) {
        call = "rs.getDate(\"" + getSqlColumnName() + "\")";
      } else {
        call =
          "rs.getDate(\"" + getSqlColumnName()
              + "\");\n    if (rs.wasNull()) {\n      " + getUniqueName()
              + "Value = null;\n    }";
      }
    } else if (typeName.equals("java.sql.Time")) {
      if (required) {
        call = "rs.getTime(\"" + getSqlColumnName() + "\")";
      } else {
        call =
          "rs.getTime(\"" + getSqlColumnName()
              + "\");\n    if (rs.wasNull()) {\n      " + getUniqueName()
              + "Value = null;\n    }";
      }
    } else if (typeName.equals("java.sql.Timestamp")) {
      call = "rs.getTimestamp(\"" + getSqlColumnName() + "\")";
      if (required) {
        call = "rs.getTimestamp(\"" + getSqlColumnName() + "\")";
      } else {
        call =
          "rs.getTimestamp(\"" + getSqlColumnName()
              + "\");\n    if (rs.wasNull()) {\n      " + getUniqueName()
              + "Value = null;\n    }";
      }
    } else if (StringUtils.contains(typeName, "java.util.List")) {
      // to-many attributes of List don't come from result set
      call = null;
    } else if (StringUtils.contains(typeName, "java.util.Collection")) {
      // to-many attributes of Collection don't come from result set
      call = null;
    } else {
      logger.debug("Unsupported JDBC type: " + typeName + " on property "
                   + getSqlColumnName() + " in DTO " + owner.name);
    }
    return call;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.poesys.cartridges.db.psm.db.Property#getSqlColumnName()
   */
  @Override
  public String getSqlColumnName() {
    // Default name to explicit name or name in that order
    String sqlName = explicitName != null ? explicitName : name;

    try {
      String ownerName = getOwnerName();
      
      // Map the property names if needed.
      if (ownerName != null) {
        Map<String, List<Property>> propertyNames =
          mapSqlPropertyNames(ownerName);

        // Test for duplication and resolve.
        if (propertyNames != null && propertyNames.get(sqlName) != null) {
          sqlName = resolveDuplicateName(sqlName, propertyNames);
        }
      } else {
        // No owner name, try the best we can with what we've got and log it.
        logNoOwnerError();
      }
    } catch (RuntimeException e) {
      String errMsg = logSqlGenerationError(e);
      throw new RuntimeException(errMsg, e);
    }

    // If the name is longer than 30 characters, truncate to 30 characters.
    if (sqlName.length() > 30) {
      sqlName = sqlName.substring(0, 29);
    }

    return sqlName;
  }

  /**
   * Log an error message giving details on a name generation problem.
   * 
   * @param e the runtime exception
   * @return the error message constructed with details
   */
  private String logSqlGenerationError(RuntimeException e) {
    // Log the message as an error and throw the RuntimeException.
    String errMsg =
      "Exception while getting SQL column name for property "
          + ownerType.getName() == null ? null : ownerType.getName() + "."
                                                 + name + " from source "
                                                 + getSource();
    logger.error(errMsg, e);
    return errMsg;
  }

  /**
   * Log an error in getting the owning DTO for the SQL column.
   */
  private void logNoOwnerError() {
    String className = ownerType != null ? ownerType.getName() + "." : null;
    String errorMsg =
      "Couldn't get SQL column name for " + className + name
          + ", no owning DTO (source " + source + ")";
    logger.error(errorMsg);
  }

  /**
   * Test a name against the full collection of property names for the DTO and
   * resolve any duplication using the prefix set.
   * 
   * @param sqlName the name to resolve
   * @param propertyNames the collection of property names for the DTO
   * @return the resolved, unique SQL column name
   */
  @SuppressWarnings("unchecked")
  private String resolveDuplicateName(String sqlName,
                                      Map<String, List<Property>> propertyNames) {
    int count = propertyNames.get(sqlName).size();
    if (count > 1) {
      // Duplicate, use the prefix list or prefix.
      if (prefixes != null && prefixes.size() > 0) {
        List<PropertyPrefix> prefixList = prefixes;
        for (PropertyPrefix prefix : prefixList) {
          sqlName =
            StringUtilities.buildVariableName(sqlName, prefix.getPrefix(), null);
        }
      } else {
        sqlName = StringUtilities.buildVariableName(sqlName, prefix, null);
      }
    } else if (count == 0) {
      logger.debug("Property " + getName() + " names map does not include "
                   + sqlName + " from source " + getSource());
    }
    return sqlName;
  }

  /**
   * Map the set of SQL-relevant property names for the owning DTO of the
   * property. The resulting map is keyed on property name and lists all the
   * properties that have each name in a list keyed by the name.
   * 
   * @param ownerName the name of the owning DTO
   * @return a map of lists of properties keyed on property name
   */
  private Map<String, List<Property>> mapSqlPropertyNames(String ownerName) {
    Map<String, List<Property>> propertyNames = sqlMap.get(ownerName);
    if (propertyNames == null) {
      mapAllSqlProperties(ownerName);
      propertyNames = sqlMap.get(ownerName);
      if (propertyNames == null) {
        logger.error("Could not map SQL property names for DTO " + owner.name);
      }
    }
    return propertyNames;
  }

  /**
   * Set the UML2 type of the property for use in creating a DTO; if there is a
   * current DTO set, this removes it but does not create a new one, a later
   * stage will need to do that after all DTOs are created. The type may be
   * null, indicating that the property is a primitive type rather than an
   * object type (for example, a generated sequence primary key is a Big
   * Integer, not a Persistent object type).
   * 
   * @param type the UML2 type
   */
  public void setPropertyType(Type type) {
    propertyType = type;
    dto = null;
  }
  
  /**
   * Get the owner type (a UML2 Type)
   * @return the UML2 type
   */
  public Type getOwnerType() {
    return ownerType;
  }

  /**
   * Set the UML2 type of the owning DTO for use in creating the DTO object; if
   * there is a current DTO set, this removes it but does not create a new one.
   * 
   * @param type the UML2 type
   */
  public void setOwnerType(Type type) {
    ownerType = type;
    owner = null;
  }

  /**
   * Set the UML2 type of the DTO from which the property was inherited for use
   * in creating the DTO object for that type
   * @param type the UML2 type
   */
  public void setInheritedFromType(Type type) {
    inheritedFromType = type;
    inheritedFrom = null;
  }

  /**
   * Create a DTO that represents the object class of the property. If the
   * current property type is null, this is a primitive, so don't create a DTO.
   */
  public void createDto() {
    // Proceed only if DTO doesn't already exist and there is a property type.
    try {
      if (dto == null && propertyType != null) {
        if (propertyType instanceof AssociationClass) {
          PersistentAssociationClassFacade classFacade =
            new PersistentAssociationClassFacadeLogicImpl((AssociationClass)propertyType,
                                                          CONTEXT);
          dto = classFacade.transformToDto(null);
        } else if (propertyType instanceof ClassImpl) {
          PersistentClassFacade classFacade =
            new PersistentClassFacadeLogicImpl((ClassImpl)propertyType, CONTEXT);
          dto = classFacade.transformToDto(null);
        } else {
          String errorMsg =
            "Property " + name
                + " is not a Persistent object but has property type "
                + propertyType.getName();
          logger.error(errorMsg);
          throw new RuntimeException(errorMsg);
        }
      }
      // Ignore null property types entirely.
    } catch (RuntimeException e) {
      logger.error(e.getMessage(), e);
      throw e;
    }
  }

  /**
   * Create a DTO that represents the object class that owns the property.
   */
  public void createOwner() {
    // Proceed only if owner DTO doesn't already exist.
    try {
      if (owner == null && ownerType != null
          && ownerType.getName().trim().length() != 0) {
        if (ownerType instanceof AssociationClass) {
          PersistentAssociationClassFacade classFacade =
            new PersistentAssociationClassFacadeLogicImpl((AssociationClass)ownerType,
                                                          CONTEXT);
          owner = classFacade.transformToDto(null);
        } else if (ownerType instanceof ClassImpl) {
          PersistentClassFacade classFacade =
            new PersistentClassFacadeLogicImpl((ClassImpl)ownerType, CONTEXT);
          owner = classFacade.transformToDto(null);
        } else {
          logger.error("Did not create owner DTO for property " + name
                       + " with type " + ownerType.getName() + " from "
                       + source);
        }
      } else if (ownerType == null) {
        String errorMsg =
          "Property " + name + " of class " + className + " (source " + source
              + ") had no owner type";
        logger.error(errorMsg);
        throw new RuntimeException(errorMsg);
      } else if (ownerType.getName().trim().length() == 0) {
        String errorMsg =
          "Property " + name + " of class " + className + " (source " + source
              + ") had empty-string owner type; check for non-navigable, "
              + "nameless association end with Persistent stereotype";
        logger.error(errorMsg);
        throw new RuntimeException(errorMsg);
      }
    } catch (RuntimeException e) {
      logger.error(e.getMessage(), e);
      throw e;
    }
  }

  /**
   * Create a DTO that represents the object class from which the property is inherited.
   */
  public void createInheritedFrom() {
    // Proceed only if inherited-from DTO doesn't already exist.
    try {
      if (inheritedFrom == null && inheritedFromType != null
          && inheritedFromType.getName().trim().length() != 0) {
        if (inheritedFromType instanceof AssociationClass) {
          PersistentAssociationClassFacade classFacade =
            new PersistentAssociationClassFacadeLogicImpl((AssociationClass)inheritedFromType,
                                                          CONTEXT);
          inheritedFrom = classFacade.transformToDto(null);
        } else if (inheritedFromType instanceof ClassImpl) {
          PersistentClassFacade classFacade =
            new PersistentClassFacadeLogicImpl((ClassImpl)inheritedFromType, CONTEXT);
          inheritedFrom = classFacade.transformToDto(null);
        } else {
          logger.error("Did not create inherited-from DTO for property " + name
                       + " with type " + inheritedFromType.getName() + " from "
                       + source);
        }
      } else if (inheritedFromType == null) {
        // Default to the Owner
        inheritedFromType = ownerType;
        inheritedFrom = owner;
      } else if (inheritedFromType.getName().trim().length() == 0) {
        String errorMsg =
          "Property " + name + " of class " + className + " (source " + source
              + ") had empty-string inherited-from type; check for non-navigable, "
              + "nameless association end with Persistent stereotype";
        logger.error(errorMsg);
        throw new RuntimeException(errorMsg);
      }
    } catch (RuntimeException e) {
      logger.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public String getUniqueName() {
    // Getting property name, not query name, set the isQuery flag to false
    return StringUtilities.getUniqueName(this, false);
  }

  /**
   * Get the owner name from the owner object or from the owner Type.
   * 
   * @return the owner name
   */
  public String getOwnerName() {
    String ownerName = null;

    if (owner != null) {
      ownerName = owner.getPackageName() + "." + owner.name;
    } else if (owner == null && ownerType != null) {
      String qualName = ownerType.getQualifiedName();
      qualName = StringUtils.removeStart(qualName, UML_TYPE_PREFIX);
      ownerName = StringUtils.replace(qualName, UML_SEP, JAVA_SEP);

      // Try to create the owner and inherited-from DTOs for use later.
      createOwner();
      createInheritedFrom();
    }
    return ownerName;
  }

  /**
   * Get the owner key type from the owner object or from the owner Type.
   * 
   * @return the owner key type
   */
  public String getOwnerKeyType() {
    String ownerKeyType = null;

    if (owner != null) {
      ownerKeyType = owner.keyType;
    } else if (ownerType != null) {
      ownerKeyType = getKeyType(ownerType);
    }
    return ownerKeyType;
  }

  /**
   * Get the string representation of the persistent key type of a UML2 type.
   * 
   * @param t the UML2 type
   * @return the string representation of the key type of the type
   */
  public String getKeyType(Type t) {
    KeyType type = null;
    for (Object o : t.getAppliedStereotypes()) {
      Stereotype stereotype = (Stereotype)o;
      type = KeyType.stringValue(stereotype.getName());
      if (type != null) {
        break;
      }
    }

    if (type == null) {
      type = KeyType.NONE;
    }

    return type.toString();
  }

  /**
   * Is this property an "association attribute", meaning a data member that
   * tracks the individual attribute key values for lookup in the database?
   * These attributes have slightly different name resolution. This method uses
   * the source to identify properties coming from the nested strategy that
   * produces such attributes. There are several variations on the association
   * attribute:
   * <ul>
   * <li>the association foreign key attribute</li>
   * <li>the to-one association attribute property</li>
   * <li>the explicit association key</li>
   * <li>the inherited association attribute property</li>
   * </ul>
   * 
   * @return true if the source indicates this is an association attribute
   */
  public boolean isAssociationAttribute() {
    boolean attr = false;

    // Exclude primary key attributes.
    if (!isKey()) {
      // Test for various sources to include.
      Set<String> sources = new HashSet<String>(3);
      sources.add("addAssociationForeignKeys");
      sources.add("AddToOneAssociationAttributeProperties");
      sources.add("AddExplicitAssociationKeyProperties");
      sources.add("AddInheritedAssociationAttributeProperties");

      for (String attrSource : sources) {
        if (StringUtils.contains(source, attrSource)) {
          attr = true;
          break;
        }
      }
    }

    return attr;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.poesys.cartridges.db.psm.db.Property#isBooleanString()
   */
  public boolean isBooleanString() {
    boolean retVal = false;
    if (typeName.equalsIgnoreCase("java.lang.Boolean") && booleanString) {
      retVal = true;
    }
    return retVal;
  }

  @Override
  public String getJdbcSetCall() {
    if (typeName == null || typeName.length() == 0) {
      throw new RuntimeException("Null Java data type while getting JDBC set call");
    }
    if (getGetterName() == null || getGetterName().length() == 0) {
      throw new RuntimeException("Null property name while getting JDBC set call");
    }

    // Implement all supported types here.
    String call = null;
    if (typeName.equals("java.math.BigInteger")) {
      // Check for null value
      call =
        "stmt.setBigDecimal(" + INDEX_VAR + ", " + OBJECT_VAR + "."
            + getGetterName() + "() == null ? null : new java.math.BigDecimal("
            + OBJECT_VAR + "." + getGetterName() + "()))";
    } else if (typeName.equals("java.lang.String")) {
      call =
        "stmt.setString(" + INDEX_VAR + ", " + OBJECT_VAR + "."
            + getGetterName() + "())";
    } else if (typeName.equals("java.util.UUID")) {
      call =
        "stmt.setString(" + INDEX_VAR + ", " + OBJECT_VAR + "."
            + getGetterName() + "())";
    } else if (typeName.equals("java.math.BigDecimal")) {
      call =
        "stmt.setBigDecimal(" + INDEX_VAR + ", " + OBJECT_VAR + "."
            + getGetterName() + "())";
    } else if (typeName.equalsIgnoreCase("java.lang.Integer")) {
      call =
        "stmt.setInt(" + INDEX_VAR + ", " + OBJECT_VAR + "." + getGetterName()
            + "())";
    } else if (typeName.equalsIgnoreCase("java.lang.Double")) {
      call =
        "if (object." + getGetterName() + "() != null) \n" + "        stmt.setDouble(" + INDEX_VAR + ", " + OBJECT_VAR + "."
            + getGetterName() + "());\n" + "      else\n              stmt.setNull(index, java.sql.Types.DOUBLE)";
    } else if (typeName.equalsIgnoreCase("java.lang.Float")) {
      // SQL "float" is really double
      call =
        "stmt.setDouble(" + INDEX_VAR + ", " + OBJECT_VAR + "."
            + getGetterName() + "())";
    } else if (typeName.equalsIgnoreCase("java.lang.Long")) {
      call = "if (" + OBJECT_VAR + "." + getGetterName()
          + "() != null) {" +
        "stmt.setLong(" + INDEX_VAR + ", " + OBJECT_VAR + "." + getGetterName()
            + "());} else {stmt.setNull(index, java.sql.Types.BIGINT);}";
    } else if (isBooleanString()) {
      // check for null
      call =
        "stmt.setString(" + INDEX_VAR + ", " + OBJECT_VAR + "."
            + getGetterName() + "() == null ? null : " + OBJECT_VAR + "."
            + getGetterName() + "() ? \"" + booleanTrue + "\" : \""
            + booleanFalse + "\")";
    } else if (typeName.equalsIgnoreCase("java.lang.Boolean")) {
      call =
        "stmt.setInt(" + INDEX_VAR + ", " + OBJECT_VAR + "." + getGetterName()
            + "() ? 1 : 0)";
    } else if (typeName.equals("java.sql.Blob")) {
      call =
        "stmt.setBlob(" + INDEX_VAR + ", " + OBJECT_VAR + "." + getGetterName()
            + "())";
    } else if (typeName.equals("java.sql.Date")) {
      call =
        "stmt.setDate(" + INDEX_VAR + ", " + OBJECT_VAR + "." + getGetterName()
            + "())";
    } else if (typeName.equals("java.sql.Time")) {
      call =
        "stmt.setTime(" + INDEX_VAR + ", " + OBJECT_VAR + "." + getGetterName()
            + "())";
    } else if (typeName.equals("java.sql.Timestamp")) {
      call =
        "stmt.setTimestamp(" + INDEX_VAR + ", " + OBJECT_VAR + "."
            + getGetterName() + "())";
    } else if (StringUtils.contains(typeName, "java.util.List")) {
      // to-many attributes of List don't come from result set
    } else if (StringUtils.contains(typeName, "java.util.Collection")) {
      // to-many attributes of Collection don't come from result set
    } else {
      logger.debug("Unsupported JDBC type: " + typeName
                   + " on property getter " + getGetterName());
    }
    return call;
  }

  public boolean isSqlPrimitive() {
    boolean primitive = false;
    if (typeName == null || typeName.length() == 0) {
      throw new RuntimeException("Null Java data type");
    }

    // Implement all supported types here.
    if (typeName.equals("java.math.BigInteger")) {
      primitive = true;
    } else if (typeName.equals("java.lang.String")) {
      primitive = true;
    } else if (typeName.equals("java.util.UUID")) {
      primitive = true;
    } else if (typeName.equals("java.math.BigDecimal")) {
      primitive = true;
    } else if (typeName.equalsIgnoreCase("java.lang.Integer")) {
      primitive = true;
    } else if (typeName.equalsIgnoreCase("java.lang.Double")) {
      primitive = true;
    } else if (typeName.equalsIgnoreCase("java.lang.Float")) {
      primitive = true;
    } else if (typeName.equalsIgnoreCase("java.lang.Long")) {
      primitive = true;
    } else if (typeName.equalsIgnoreCase("java.lang.Boolean")) {
      primitive = true;
    } else if (typeName.equals("java.sql.Blob")) {
      primitive = true;
    } else if (typeName.equals("java.sql.Date")) {
      primitive = true;
    } else if (typeName.equals("java.sql.Time")) {
      primitive = true;
    } else if (typeName.equals("java.sql.Timestamp")) {
      primitive = true;
    }
    return primitive;
  }

  @Override
  public boolean isSupportedJdbcType() {
    boolean supported = false;

    if (typeName.equals("java.math.BigInteger")) {
      supported = true;
    } else if (typeName.equals("java.lang.String")) {
      supported = true;
    } else if (typeName.equals("java.util.UUID")) {
      supported = true;
    } else if (typeName.equals("java.math.BigDecimal")) {
      supported = true;
    } else if (typeName.equalsIgnoreCase("java.lang.Integer")) {
      supported = true;
    } else if (typeName.equalsIgnoreCase("java.lang.Double")) {
      supported = true;
    } else if (typeName.equalsIgnoreCase("java.lang.Float")) {
      supported = true;
    } else if (typeName.equalsIgnoreCase("java.lang.Long")) {
      supported = true;
    } else if (typeName.equalsIgnoreCase("java.lang.Boolean")) {
      supported = true;
    } else if (typeName.equals("java.sql.Blob")) {
      supported = true;
    } else if (typeName.equals("java.sql.Date")) {
      supported = true;
    } else if (typeName.equals("java.sql.Time")) {
      supported = true;
    } else if (typeName.equals("java.sql.Timestamp")) {
      supported = true;
    } else if (StringUtils.contains(typeName, "java.util.List")) {
      supported = true;
    } else if (StringUtils.contains(typeName, "java.util.Collection")) {
      supported = true;
    }
    return supported;
  }

  @Override
  public String toString() {
    // Used to print names for debugging
    return "{Property--name: " + name + ", class: " + className
           + ", explicit: " + explicitName + ", object: " + objectName + "}";
  }

  /**
   * Compare one property to another by unique name.
   * 
   * @param other the other property
   * @return 1, 0, or -1 depending on whether the name is greater than, equal
   *         to, or less than the other name
   */
  public int compareTo(Property other) {
    // Compare on property name
    return getUniqueName().compareTo(other.getUniqueName());
  }

  @Override
  public String getQueryName() {
    if (getName().trim().length() == 0) {
      throw new RuntimeException("Property has no name");
    }
    
    String name = StringUtilities.getUniqueName(this, true);
    return StringUtils.capitalize(name);
  }

  @Override
  public String getQueryGetterName() {
    String prefix = GET;
    if (this.getTypeName().equalsIgnoreCase(BOOLEAN)) {
      prefix = IS;
    }
    String name = StringUtilities.getUniqueName(this, true);
    return StringUtilities.buildVariableName(name, prefix, null);
  }

  @Override
  public String getObjectClassName() {
    // Get the class name and fully-qualified package name.
    String name = propertyType.getName();
    String packageName = StringUtilities.getQualifiedJavaName(propertyType.getPackage().getQualifiedName());
    return packageName + "." + name;
  }
}
