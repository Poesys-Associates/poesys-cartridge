/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
/**
 * This is only generated once! It will never be overwritten. You can (and have
 * to!) safely modify it by hand.
 */
package com.poesys.cartridges.db.psm.db;


import java.util.Collection;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;


/**
 * @see com.poesys.cartridges.db.psm.db.AssociatedKey
 */
public class AssociatedKeyImpl extends
    com.poesys.cartridges.db.psm.db.AssociatedKey {
  /**
   * Create a AssociatedKeyImpl object.
   */
  public AssociatedKeyImpl() {
    super();
  }

  /**
   * Create a AssociatedKeyImpl object.
   * 
   * @param name the name of the key property in the DTO
   * @param keyType the type of key in the foreign DTO (Sequence, Composite,
   *            etc.)
   * @param parentName the name of the parent DTO type for a composite key
   * @param prefix the prefix for the key in the DTO
   * @param typeName the data type of the key in the foreign DTO
   * @param packageName the data-access layer package of the data type
   * @param tableName the SQL table name of the foreign DTO
   * @param required whether the key is required in the current DTO
   * @param objectName the name of the key object in the Java class (type name
   *            or property name if duplication present)
   * @param businessPackageName the business-layer package of the data type
   * @param subsystemName the name of the subsystem that contains the data type
   * @param generalization whether the associated key represents the
   *            generalization/inheritance relationship to the superclass(es)
   * @param persistent whether the associated key represents a database foreign
   *            key or a transient association only
   * @param readWrite whether the associated object is read/write
   * @param dto the DTO to which the associated key corresponds
   * @param keyProperties the collection of key attributes
   */
  @SuppressWarnings("unchecked")
  public AssociatedKeyImpl(java.lang.String name,
                           java.lang.String keyType,
                           java.lang.String parentName,
                           java.lang.String prefix,
                           java.lang.String typeName,
                           java.lang.String packageName,
                           java.lang.String tableName,
                           boolean required,
                           java.lang.String objectName,
                           java.lang.String businessPackageName,
                           java.lang.String subsystemName,
                           boolean generalization,
                           boolean persistent,
                           boolean readWrite,
                           Dto dto,
                           java.util.Collection keyProperties) {
    super(name, keyType, parentName, prefix, typeName, packageName, tableName,
          required, objectName, businessPackageName, subsystemName,
          generalization, persistent, readWrite, dto, keyProperties);
  }

  /**
   * Create a AssociatedKeyImpl object.
   * 
   * @param name the name of the key property in the DTO
   * @param keyType the type of key in the foreign DTO (Sequence, Composite,
   *            etc.)
   * @param parentName the name of the parent DTO type for a composite key
   * @param prefix the prefix for the key in the DTO
   * @param typeName the data type of the key in the foreign DTO
   * @param packageName the data-access layer package of the data type
   * @param tableName the SQL table name of the foreign DTO
   * @param required whether the key is required in the current DTO
   * @param objectName the name of the key object in the Java class (type name
   *            or property name if duplication present)
   * @param businessPackageName the business layer package of the data type
   * @param subsystemName the name of the subsystem that contains the data type
   * @param generalization whether the associated key represents the
   *            generalization/inheritance relationship to the superclass(es)
   * @param persistent whether the associated key represents a database foreign
   *            key or a transient association only
   * @param readWrite whether the associated object is read/write
   * @param keyProperties the collection of key attributes
   * @param parentKeyProperties the collection of parent key attributes (a
   *            subset of keyProperties)
   */
  @SuppressWarnings("unchecked")
  public AssociatedKeyImpl(java.lang.String name,
                           java.lang.String keyType,
                           java.lang.String parentName,
                           java.lang.String prefix,
                           java.lang.String typeName,
                           java.lang.String packageName,
                           java.lang.String tableName,
                           boolean required,
                           java.lang.String objectName,
                           java.lang.String businessPackageName,
                           java.lang.String subsystemName,
                           boolean generalization,
                           boolean persistent,
                           boolean readWrite,
                           Dto dto,
                           java.util.Collection keyProperties,
                           java.util.Collection parentKeyProperties) {
    super(name, keyType, parentName, prefix, typeName, packageName, tableName,
          required, objectName, businessPackageName, subsystemName,
          generalization, persistent, readWrite, dto, keyProperties,
          parentKeyProperties);
  }

  /**
   * Copy-constructor from other AssociatedKey
   * 
   * @param otherBean cannot be <code>null</code>
   * @throws java.lang.NullPointerException if the argument is <code>null</code>
   */
  public AssociatedKeyImpl(AssociatedKey otherBean) {
    this(otherBean.getName(), otherBean.getKeyType(),
         otherBean.getParentName(), otherBean.getPrefix(),
         otherBean.getTypeName(), otherBean.getPackageName(),
         otherBean.getTableName(), otherBean.isRequired(),
         otherBean.getObjectName(), otherBean.getBusinessPackageName(),
         otherBean.getSubsystemName(), otherBean.isGeneralization(),
         otherBean.isPersistent(), otherBean.isReadWrite(), otherBean.getDto(),
         otherBean.getKeyProperties());
  }

  /**
   * @see com.poesys.cartridges.db.psm.db.AssociatedKey#isKey()
   */
  public boolean isKey() {
    boolean key = false;
    for (Object o : keyProperties) {
      Property p = (Property)o;
      if (p.isKey()) {
        key = true;
        break;
      }
    }
    return key;
  }

  @Override
  public String getInheritedKeyType() {
    String superKeyType = KeyType.NONE.toString();
    if (KeyType.NONE.toString().equals(keyType)) {
      // Walk tree from root to this class, lowest key class wins
      for (Object o : dto.superclasses) {
        Dto superclass = (Dto)o;
        String tempSuperKeyType =
          KeyType.stringValue(superclass.keyType).toString();
        if (!tempSuperKeyType.equals(KeyType.NONE.toString())) {
          superKeyType = tempSuperKeyType;
        }
      }
    }
    return superKeyType;
  }

  @Override
  public String getUniqueTypeName() {
    return StringUtils.capitalize(name) + getSubsystemName() + typeName;
  }

  @Override
  public String getSubsystemName() {
    String name = null;
    StringTokenizer tokens = new StringTokenizer(getFullSubsystemName(), ".");
    while (tokens.hasMoreTokens()) {
      // Get the last part of the full name as the name.
      name = tokens.nextToken();
    }
    return StringUtils.capitalize(name);
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @SuppressWarnings("unchecked")
  @Override
  public boolean equals(Object obj) {
    boolean isEqual = false;
    AssociatedKey other = (AssociatedKey)obj;
    // Compare the key property unique names.
    Collection<Property> keys = keyProperties;
    Collection<Property> otherKeys = other.keyProperties;
    outer: for (Property key : keys) {
      for (Property otherKey : otherKeys) {
        if (key.getUniqueName().equals(otherKey.getUniqueName())) {
          isEqual = true;
          break outer;
        }
      }
    }
    return isEqual;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#hashCode()
   */
  @SuppressWarnings("unchecked")
  @Override
  public int hashCode() {
    // Generate a hash code from the concatenated unique property names.
    Collection<Property> keys = keyProperties;
    StringBuilder names = new StringBuilder();
    for (Property key : keys) {
      names.append(key.getUniqueName());
      names.append(".");
    }
    return names.toString().hashCode();
  }

}
