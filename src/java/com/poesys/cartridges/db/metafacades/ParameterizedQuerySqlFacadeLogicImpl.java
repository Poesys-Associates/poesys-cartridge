package com.poesys.cartridges.db.metafacades;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.andromda.metafacades.uml.ModelElementFacade;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.uml2.Association;
import org.eclipse.uml2.AssociationClass;
import org.eclipse.uml2.Stereotype;
import org.eclipse.uml2.Type;
import org.eclipse.uml2.impl.ClassImpl;

import com.poesys.cartridges.db.profile.PoesysDbProfile;
import com.poesys.cartridges.db.psm.db.AssociatedKey;
import com.poesys.cartridges.db.psm.db.Dto;
import com.poesys.cartridges.db.psm.db.ParameterizedQueryImpl;
import com.poesys.cartridges.db.psm.db.Property;
import com.poesys.cartridges.db.psm.db.PropertyPrefix;
import com.poesys.cartridges.db.psm.db.SqlColumnName;
import com.poesys.cartridges.db.psm.db.SqlColumnNameImpl;
import com.poesys.cartridges.db.psm.db.Subsystem;
import com.poesys.cartridges.db.utilities.SqlColumnNameComparator;
import com.poesys.cartridges.db.utilities.StereotypeUtilities;
import com.poesys.cartridges.db.utilities.StringUtilities;


/**
 * MetafacadeLogic implementation for
 * com.poesys.cartridges.db.metafacades.ParameterizedQuerySqlFacade.
 * 
 * @see com.poesys.cartridges.db.metafacades.ParameterizedQuerySqlFacade
 */
public class ParameterizedQuerySqlFacadeLogicImpl extends
    ParameterizedQuerySqlFacadeLogic {
  /** Logger for this class */
  Logger logger = Logger.getLogger(ParameterizedQuerySqlFacadeLogicImpl.class);

  /**
   * The creation context of metafacades created internally for error reporting
   */
  private static final String CONTEXT =
    ParameterizedQuerySqlFacadeLogicImpl.class.getName();

  /**
   * Create a ParameterizedQuerySqlFacadeLogicImpl object.
   * 
   * @param metaObject the Association End object the metafacade wraps
   * @param context the creation context of the metafacade for error reporting
   */
  public ParameterizedQuerySqlFacadeLogicImpl(org.andromda.metafacades.emf.uml2.AssociationEnd metaObject,
                                              String context) {
    super(metaObject, context);
    if (metaObject.getName().trim().length() == 0) {
      throw new RuntimeException("Null name in creating ParameterizedQuerySql "
                                 + "facade, check for non-navigable, nameless "
                                 + "association end with Persistent stereotype");
    }
  }

  /**
   * @see com.poesys.cartridges.db.metafacades.ParameterizedQuerySqlFacade#transformToQuery()
   */
  protected com.poesys.cartridges.db.psm.db.ParameterizedQuery handleTransformToQuery() {
    ParameterizedQueryImpl query = null;
    Type associationClass = getAssociationClassType();
    String associationName =
      associationClass != null ? associationClass.getName() : null;
    String associationSqlTableName =
      associationClass != null ? getSqlTableName(associationClass) : null;
    String associationPackageName =
      associationClass != null ? StringUtilities.getQualifiedJavaName(associationClass.getPackage().getQualifiedName())
          : null;

    try {
      String packageName =
        StringUtilities.getQualifiedJavaName(metaObject.getType().getPackage().getQualifiedName());
      String foreignPackage =
        StringUtilities.getQualifiedJavaName(getOppositeEndType().getPackage().getQualifiedName());

      query =
        new ParameterizedQueryImpl(metaObject.getType().getName(),
                                   getOppositeEndType().getName(),
                                   getSqlTableName(metaObject.getType()),
                                   getQueryName(),
                                   associationName,
                                   associationSqlTableName,
                                   getLinksPropertyName(),
                                   foreignPackage,
                                   packageName,
                                   associationPackageName,
                                   getQueriedCols(),
                                   getKeyCols(),
                                   getSubsystem(),
                                   getJoinCols(),
                                   getQueriedLinkCols(),
                                   getLinkKeyCols(),
                                   getAssociationClassDto(),
                                   getAssociatedClassDto(),
                                   getLinksSubsystem(),
                                   getQueryingClassDto(),
                                   getManyKeyCols());
    } catch (RuntimeException e) {
      e.printStackTrace();
    }

    return query;
  }

  /**
   * Get the query name. The query name is the unique name of the association
   * end property. The method returns the name capitalized for use as a class
   * name.
   * 
   * @return the name of the query suitable for use as a class name
   */
  private String getQueryName() {
    if (metaObject.getName().trim().length() == 0) {
      throw new RuntimeException("Empty association end on class, check for "
                                 + "non-navigable, nameless association end with"
                                 + " Persistent stereotype");
    }
    
    String name = StringUtilities.getUniqueQueryName(metaObject);
    return StringUtils.capitalize(name);
  }

  /**
   * Get the SQL column names to use in the JOIN ON clause. These are the keys
   * for joining an association class to the target class on a shared key, with
   * that shared key being a foreign key from the association class to the
   * target class and the primary key of the target class. The target class is
   * the class associated with the wrapped association end.
   * 
   * @return a collection of SQL column names
   */
  private Collection<SqlColumnName> getJoinCols() {
    Type type = metaObject.getType();
    List<SqlColumnName> list = new ArrayList<SqlColumnName>(1);

    // Get the column names for the queried class.
    if (type instanceof AssociationClass) {
      // AssociationClass, build a persistent class metafacade and get the keys.
      PersistentAssociationClassFacade facade =
        new PersistentAssociationClassFacadeLogicImpl((AssociationClass)type,
                                                      CONTEXT);
      Dto dto = facade.transformToDto(null);
      for (Object p : dto.getKeyProperties()) {
        Property property = (Property)p;
        SqlColumnName col = new SqlColumnNameImpl(property);
        list.add(col);
      }
    } else if (type instanceof ClassImpl) {
      // Class, build a persistent class metafacade and get the keys.
      PersistentClassFacade facade =
        new PersistentClassFacadeLogicImpl((ClassImpl)type, CONTEXT);
      Dto dto = facade.transformToDto(null);
      for (Object p : dto.getKeyProperties()) {
        Property property = (Property)p;
        SqlColumnName col = new SqlColumnNameImpl(property);
        list.add(col);
      }
    }

    // For a many-to-many query, fill in the join key columns.
    if (isMany2Many()) {
      // Use the association end name as the prefix.
      String prefix = metaObject.getName();
      addAssociationClassKeys(list, prefix);
    }

    return list;
  }

  /**
   * Get the type for the association if there is one
   * 
   * @return the type of the association class or null if there is none
   */
  private AssociationClass getAssociationClassType() {
    Association a = metaObject.getAssociation();
    AssociationClass ac = null;
    if (a != null && a instanceof AssociationClass) {
      // The end is part of an association class, set the return class.
      ac = (AssociationClass)metaObject.getAssociation();
    }
    return ac;
  }

  /**
   * Get the DTO that represents the association class.
   * 
   * @return a DTO
   */
  private Dto getAssociationClassDto() {
    Dto dto = null;
    AssociationClass a = getAssociationClassType();
    if (a != null) {
      PersistentAssociationClassFacadeLogicImpl facade =
        new PersistentAssociationClassFacadeLogicImpl(a, CONTEXT);
      dto = facade.transformToDto(null);
    }
    return dto;
  }

  /**
   * Get the DTO that represents the querying class.
   * 
   * @return a DTO
   */
  private Dto getQueryingClassDto() {
    Dto dto = null;
    Type dtoType = getAssociatedDto();
    if (dtoType instanceof AssociationClass) {
      PersistentAssociationClassFacade facade =
        new PersistentAssociationClassFacadeLogicImpl((AssociationClass)dtoType,
                                                      CONTEXT);
      dto = facade.transformToDto(null);
    } else if (dtoType instanceof ClassImpl) {
      PersistentClassFacade facade =
        new PersistentClassFacadeLogicImpl((ClassImpl)dtoType, CONTEXT);
      dto = facade.transformToDto(null);
    }
    return dto;
  }

  /**
   * Get the DTO that is associated with the current associated end on the other
   * end of the association.
   * 
   * @return the associated DTO
   */
  private Type getAssociatedDto() {
    Type dtoType = null;
    AssociationClass a = getAssociationClassType();
    if (a != null) {
      for (Object o : a.getEndTypes()) {
        Type type = (Type)o;
        if (!type.getQualifiedName().equals(metaObject.getType().getQualifiedName())) {
          // Not the current end, so it must be the other end :)
          dtoType = type;
          break;
        }
      }
    } else {
      // No association class, one-to-many or one-to-one association
      // so try getOpposite().
      if (metaObject.getOpposite() != null) {
        dtoType = metaObject.getOpposite().getType();
      } else {
        // Probably not navigable, so get the association and extract type.
        for (Object o : metaObject.getAssociation().getEndTypes()) {
          Type type = (Type)o;
          if (!type.getQualifiedName().equals(metaObject.getType().getQualifiedName())) {
            // Not the current end, so it must be the other end :)
            dtoType = type;
            break;
          }
        }
      }
    }
    return dtoType;
  }

  private Dto getAssociatedClassDto() {
    Dto dto = null;
    Type t = metaObject.getType();
    if (t instanceof AssociationClass) {
      PersistentAssociationClassFacade facade =
        new PersistentAssociationClassFacadeLogicImpl((AssociationClass)t,
                                                      CONTEXT);
      dto = facade.transformToDto(null);
    } else if (t instanceof ClassImpl) {
      PersistentClassFacade facade =
        new PersistentClassFacadeLogicImpl((ClassImpl)t, CONTEXT);
      dto = facade.transformToDto(null);
    }
    return dto;
  }

  /**
   * Get the collection of primary key SQL column names for the Class or
   * AssociationClass type that is at the opposite end of the association from
   * the wrapped association end. The join-key column for association-class
   * joins is either the column from the opposite end for an association key or
   * the primary key for a non-association-key class. If the column name is not
   * unique in the current class, the method appends the property name to it to
   * disambiguate it, as do all the column creation methods in the class
   * facades.
   * 
   * @return the collection of SQL column names
   */
  private List<SqlColumnName> getKeyCols() {
    List<SqlColumnName> list = new ArrayList<SqlColumnName>(1);

    // Get the associated foreign key for the DTO of the association end.
    Type type = metaObject.getType();

    if (!isManyToMany()) {
      // One-to-many, get the key columns from the foreign type.
      if (type instanceof AssociationClass) {
        // AssociationClass, build a persistent association class metafacade and
        // get the keys.
        PersistentAssociationClassFacade facade =
          new PersistentAssociationClassFacadeLogicImpl((AssociationClass)type,
                                                        CONTEXT);
        Dto dto = facade.transformToDto(null);
        // Find the association as a foreign key.
        AssociatedKey key = dto.getForeignKey(getOppositeEndName());
        if (key != null) {
          for (Object p : key.getKeyProperties()) {
            Property property = (Property)p;
            SqlColumnName col = new SqlColumnNameImpl(property);
            list.add(col);
          }
        } else {
          String error =
            "No foreign key for association-class association "
                + getOppositeEndName();
          logger.error(error);
          throw new RuntimeException(error);
        }
      } else if (type instanceof ClassImpl) {
        // Class, build a persistent class metafacade.
        PersistentClassFacade facade =
          new PersistentClassFacadeLogicImpl((ClassImpl)type, CONTEXT);
        Dto dto = facade.transformToDto(null);
        // Find the association as a foreign key.
        AssociatedKey key = dto.getForeignKey(getOppositeEndName());
        if (key != null) {
          for (Object p : key.getKeyProperties()) {
            Property property = (Property)p;
            SqlColumnName col = new SqlColumnNameImpl(property);
            list.add(col);
          }
        } else {
          String error =
            "No foreign key for association " + getOppositeEndName();
          logger.error(error);
          throw new RuntimeException(error);
        }
      }
    } else {
      // For many-to-many, get the association class keys.
      // Build the list of column names.
      if (type instanceof AssociationClass) {
        // AssociationClass, build a persistent association class metafacade and
        // get the keys.
        PersistentAssociationClassFacade facade =
          new PersistentAssociationClassFacadeLogicImpl((AssociationClass)type,
                                                        CONTEXT);
        Dto dto = facade.transformToDto(null);
        for (Object p : dto.getKeyProperties()) {
          Property property = (Property)p;
          SqlColumnName col = new SqlColumnNameImpl(property);
          list.add(col);
        }
      } else if (type instanceof ClassImpl) {
        // Class, build a persistent class metafacade and get the keys.
        PersistentClassFacade facade =
          new PersistentClassFacadeLogicImpl((ClassImpl)type, CONTEXT);
        Dto dto = facade.transformToDto(null);
        for (Object p : dto.getKeyProperties()) {
          Property property = (Property)p;
          SqlColumnName col = new SqlColumnNameImpl(property);
          list.add(col);
        }
      }

      // Using the list of column names, get the association class keys.
      addAssociationClassKeys(list, getOppositeEndName());
    }

    return list;
  }

  /**
   * Get the name of the association end at the opposite end of the association
   * from the current association end (parameterized query) regardless of the
   * navigability status.
   * 
   * @return the opposite end name
   */
  private String getOppositeEndName() {
    String thisEndName = metaObject.getQualifiedName();
    Association a = metaObject.getAssociation();
    String oppositeEndName = null;

    for (Object o : a.getMemberEnds()) {
      org.eclipse.uml2.Property end = (org.eclipse.uml2.Property)o;
      if (!end.getQualifiedName().equals(thisEndName)) {
        oppositeEndName = end.getName();
      }
    }

    return oppositeEndName;
  }

  /**
   * Get the name of the association end at the opposite end of the association
   * from the current association end (parameterized query) regardless of the
   * navigability status.
   * 
   * @return the opposite end name
   */
  private Type getOppositeEndType() {
    String thisEndName = metaObject.getQualifiedName();
    Association a = metaObject.getAssociation();
    Type oppositeEndType = null;

    for (Object o : a.getMemberEnds()) {
      org.eclipse.uml2.Property end = (org.eclipse.uml2.Property)o;
      if (!end.getQualifiedName().equals(thisEndName)) {
        oppositeEndType = end.getType();
      }
    }

    return oppositeEndType;
  }

  /**
   * Get the collection of the primary key SQL column names of the part of the
   * association key in the association class that corresponds to the foreign
   * primary key in the class that owns the association end. This set of key
   * columns appears in the WHERE clause of the links query: the query of the
   * association class objects linked to the association end class. So, for
   * example, if AB links classes A and B, and this is the association end on
   * class A, then the key columns are the part of the primary key of AB that is
   * a foreign key to A. In UML terms, the property named "a" with a single key
   * tag name "a_id", which is owned by Class A, yields the key named "a_id"
   * (which should be a part of the primary key of the AB class). You can have
   * multiple key names, so this yields a collection of keys, not just a single
   * key.
   * 
   * @return the collection of key columns
   */
  private List<SqlColumnName> getLinkKeyCols() {
    List<SqlColumnName> list = new ArrayList<SqlColumnName>(1);
    // Get the foreign keys for the association.
    Collection<AssociatedKey> keys = getAssociationForeignKeys();

    // Produce a list for all higher-order associations.
    if (keys != null && keys.size() >= 2) {
      // Find the key by looking for the property name of this association end
      // in the set of associated keys.
      for (AssociatedKey key : keys) {
        if (key.getName().equals(getName())) {
          // Get the key properties from the key and create column names.
          for (Object p : key.getKeyProperties()) {
            Property property = (Property)p;
            SqlColumnName col = new SqlColumnNameImpl(property);
            list.add(col);
          }
        }
      }
    }
    // Sort the list by column name
    Collections.sort(list, new SqlColumnNameComparator());
    return list;
  }

  /**
   * Get the collection of the primary key SQL column names of the part of the
   * association key in the association class that corresponds to the foreign
   * primary key in the class that owns the opposite association end. This set
   * of key columns appears in the WHERE clause of the many-to-many
   * associated-object query: the query of the associated class objects linked
   * by the association to the owning class. So, for example, if AB links
   * classes A and B, and this is the association end on class A, then the key
   * columns are the part of the primary key of AB that is a foreign key to B.
   * In UML terms, the property named "b" with a single key tag name "b_id",
   * which is owned by Class B, yields the key named "b_id" (which should be a
   * part of the primary key of the AB class). You can have multiple key names,
   * so this yields a collection of keys, not just a single key.
   * 
   * @return the collection of key columns
   */
  private List<SqlColumnName> getManyKeyCols() {
    List<SqlColumnName> list = new ArrayList<SqlColumnName>(1);
    // Get the foreign keys for the association.
    Collection<AssociatedKey> keys = getAssociationForeignKeys();

    // Produce a list for all higher-order associations.
    if (keys != null && keys.size() >= 2) {
      // Find the key by looking for the property name of the opposite
      // association end in the set of associated keys.
      for (AssociatedKey key : keys) {
        if (key.getName().equals(getOppositeEnd())) {
          // Get the key properties from the key and create column names.
          for (Object p : key.getKeyProperties()) {
            Property property = (Property)p;
            SqlColumnName col = new SqlColumnNameImpl(property);
            list.add(col);
          }
        }
      }
    }
    // Sort the list by column name
    Collections.sort(list, new SqlColumnNameComparator());
    return list;
  }

  /**
   * Based on the collection of foreign keys, determine whether the association
   * class represents an n-ary association, an association with more than two
   * primary keys.
   * 
   * @param keys a Collection of association keys
   * @return true if there are more than two primary keys in the collection
   */
  @SuppressWarnings("unused")
  private boolean isNAryAssociation(Collection<AssociatedKey> keys) {
    int keyCount = 0;
    if (keys != null && keys.size() > 2) {
      for (AssociatedKey key : keys) {
        if (key.isKey()) {
          keyCount++;
          if (keyCount > 2) {
            // More then 2, that's enough.
            break;
          }
        }
      }
    }
    return keyCount > 2;
  }

  /**
   * Get the foreign keys for the association class of which this association
   * end is a part. This method returns null if the current association end is
   * not on a many-to-many association with an association class.
   * 
   * @return a collection of AssociatedKey foreign keys
   */
  @SuppressWarnings("unchecked")
  private List<AssociatedKey> getAssociationForeignKeys() {
    List<AssociatedKey> keys = null;
    Association association = metaObject.getAssociation();
    if (association instanceof AssociationClass) {
      PersistentAssociationClassFacade facade =
        new PersistentAssociationClassFacadeLogicImpl((AssociationClass)association,
                                                      CONTEXT);
      Dto dto = facade.transformToDto(null);
      keys = dto.getAssociatedKeys();
      if (keys == null || keys.size() == 0) {
        throw new RuntimeException("No key columns for many-to-many association "
                                   + facade.getName());
      }
    }
    return keys;
  }

  /**
   * Get the name of the property at the opposite end of a specified association
   * that contains the current association end. This method gets the opposite
   * end regardless of navigability status. The property name is the actual
   * property name, not the "modified" one that takes explicit names specified
   * in tags into account.
   * 
   * @return a property name
   */
  private String getOppositeEnd() {
    // Use association member ends to ignore navigability.
    Association association = metaObject.getAssociation();
    String propertyName = null;
    EList ends = association.getMemberEnds();
    for (Object e : ends) {
      org.eclipse.uml2.Property p = (org.eclipse.uml2.Property)e;
      if (!p.getName().equals(metaObject.getName())) {
        propertyName = p.getName();
        break;
      }
    }
    return propertyName;
  }

  /**
   * Add the keys of the association class to the appropriate columns in the
   * input list. These either have the same column name or contain the column
   * name and the association-end name (for association classes that have
   * duplicate key names because of recursion or just similar naming).
   * 
   * @param list the input list of key columns for the queried class
   * @param prefix the association end name to use to prefix the keys
   */
  @SuppressWarnings("unchecked")
  private void addAssociationClassKeys(List<SqlColumnName> list, String prefix) {
    if (metaObject.getAssociation() != null
        && metaObject.getAssociation() instanceof AssociationClass) {
      // Get the key list of the association class.
      AssociationClass association =
        (AssociationClass)metaObject.getAssociation();
      PersistentAssociationClassFacade facade =
        new PersistentAssociationClassFacadeLogicImpl(association, CONTEXT);
      Dto dto = facade.transformToDto(null);
      // Check all foreign keys, not just primary; association class may have
      // different key structure (inherited, natural, etc.).
      Collection<Property> keys = dto.getDataMembers();
      // Iterate through the columns in the list and, for each one, find the
      // corresponding key in the association class key list. The corresponding
      // key is the key with the same name and prefix.
      for (SqlColumnName col : list) {
        inner: for (Property key : keys) {
          if (col.getProperty().getName().equalsIgnoreCase(key.getName())) {
            // Check for the prefixes.
            List<PropertyPrefix> prefixes = key.getPrefixes();
            if (prefixes != null) {
              for (PropertyPrefix keyPrefix : prefixes) {
                if (keyPrefix != null && keyPrefix.getPrefix().equals(prefix)) {
                  // Names and prefixes are the same, take the name directly.
                  col.setAssociationKeyCol(new SqlColumnNameImpl(key));
                  break inner; // inner loop terminated
                } else if (keyPrefix == null) {
                  // Names are the same, no prefix, take the name directly.
                  col.setAssociationKeyCol(new SqlColumnNameImpl(key));
                  break inner; // inner loop terminated
                }
              }
            } else {
              // Use the single prefix field.
              if (key.getPrefix() != null && key.getPrefix().equals(prefix)) {
                // Names and prefixes are the same, take the name directly.
                col.setAssociationKeyCol(new SqlColumnNameImpl(key));
                break inner; // inner loop terminated
              } else if (key.getPrefix() == null) {
                // Names are the same, no prefix, take the name directly.
                col.setAssociationKeyCol(new SqlColumnNameImpl(key));
                break inner; // inner loop terminated
              }
            }
          } else if ((prefix + StringUtils.capitalize(col.getName())).equalsIgnoreCase(key.getName())) {
            // Names not the same but prefixed with property name they are.
            col.setAssociationKeyCol(new SqlColumnNameImpl(key));
            break inner; // inner loop terminated
          }
        }
      }
    }
  }

  /**
   * Get the collection of queryable SQL column names for a Class or
   * AssocationClass type that is the currently wrapped object. This list is
   * suitable for use as a SELECT list in a SQL query for the type.
   * 
   * @return the collection of SQL column names
   */
  private Collection<SqlColumnName> getQueriedCols() {
    Type type = metaObject.getType();
    Collection<SqlColumnName> collection = new ArrayList<SqlColumnName>(1);

    if (type instanceof AssociationClass) {
      // AssociationClass, build a persistent class metafacade and get the keys.
      PersistentAssociationClassFacade facade =
        new PersistentAssociationClassFacadeLogicImpl((AssociationClass)type,
                                                      CONTEXT);
      Dto dto = facade.transformToDto(null);
      for (Object p : dto.getConstructorArgs()) {
        Property property = (Property)p;
        SqlColumnName col = new SqlColumnNameImpl(property);
        collection.add(col);
      }
    } else if (type instanceof ClassImpl) {
      // Class, build a persistent class metafacade and get the keys.
      PersistentClassFacade facade =
        new PersistentClassFacadeLogicImpl((ClassImpl)type, CONTEXT);
      Dto dto = facade.transformToDto(null);
      for (Object p : dto.getConstructorArgs()) {
        Property property = (Property)p;
        SqlColumnName col = new SqlColumnNameImpl(property);
        collection.add(col);
      }
    }
    return collection;
  }

  /**
   * Get the collection of columns to query from the linking table. These are
   * the primary key and attribute columns of the association class table.
   * 
   * @return a collection of SqlColumnName objects
   */
  private Collection<SqlColumnName> getQueriedLinkCols() {
    Type type = metaObject.getAssociation();
    Collection<SqlColumnName> collection = new ArrayList<SqlColumnName>(1);

    if (type instanceof AssociationClass) {
      // AssociationClass, build a persistent class metafacade and get the keys.
      PersistentAssociationClassFacade facade =
        new PersistentAssociationClassFacadeLogicImpl((AssociationClass)type,
                                                      CONTEXT);
      Dto dto = facade.transformToDto(null);
      for (Object p : dto.getConstructorArgs()) {
        Property property = (Property)p;
        SqlColumnName col = new SqlColumnNameImpl(property);
        collection.add(col);
      }
    }

    return collection;
  }

  /**
   * Get the SQL table name for a type. This name is either the table name (the
   * default) or an explicit name specified within the Persistent stereotype.
   * You use this name as the SQL identifier for the table corresponding to the
   * type.
   * 
   * @param type the type for which to derive the name
   * @return the SQL identifier for the table corresponding to the type
   */
  private String getSqlTableName(Type type) {
    String tableName = type.getName(); // default name to type name

    // Get any persistent name tagged value for the type.
    Stereotype stereotype =
      type.getAppliedStereotype(PoesysDbProfile.NAMESPACE_PERSISTENCE
                                + PoesysDbProfile.STEREOTYPE_PERSISTENT);
    if (stereotype != null) {
      // Persistent stereotype exists, check the tagged value name.
      // Get the list of strings and extract the first one, ignoring the others.
      EDataTypeUniqueEList values =
        (EDataTypeUniqueEList)type.getValue(stereotype,
                                            PoesysDbProfile.TAGGEDVALUE_NAME);
      if (values != null && values.size() > 0
          && ((String)values.get(0)).length() > 0) {
        tableName = (String)values.get(0);
      }
    }
    return tableName;
  }

  /**
   * <p>
   * Get the property name of the property in the class that owns the
   * association end of the Collection property for the collection of
   * association class objects. This is either the class name of the association
   * class or the tagged value "name" from the AssociationKey stereotype on that
   * class if it's defined.
   * </p>
   * <p>
   * The special case where the association is a recursive association requires
   * the special logic for prefixing the name with the property name to make the
   * association-link property name unique. That logic is in
   * PersistentClassFacadeLogicImpl.updateProperty() and PropertyImpl.prefix(),
   * the methods that update the property with the unique name among other
   * changed characteristics.
   * </p>
   * <p>
   * This name is the name of the property that is the collection of the
   * <strong>linking objects</strong> of the association class type for this
   * association end as opposed to the collection of objects associated through
   * the association.
   * </p>
   * 
   * @see PersistentClassFacadeLogicImpl.AddAssociationClassCollectionProperties
   * 
   * @return the links property name
   */
  private String getLinksPropertyName() {
    // Get the association class type.
    Type t = metaObject.getAssociation();
    // Get the type on this association end.
    Type thisType = metaObject.getType();
    // Get the type on the opposite association end.
    Type oppositeType = getOppositeEndType();
    String name = t.getName(); // default to association class name
    Stereotype s =
      t.getAppliedStereotype(PoesysDbProfile.NAMESPACE_KEY
                             + PoesysDbProfile.STEREOTYPE_ASSOCIATION_KEY);
    if (s != null) {
      String taggedValue =
        (String)t.getValue(s, PoesysDbProfile.TAGGEDVALUE_NAME);
      if (taggedValue != null && taggedValue.length() > 0) {
        name = taggedValue;
      }
    }

    if (thisType.equals(oppositeType)) {
      // Recursive association, names will be the same on both ends.
      // Make the name unique by prefixing with the property name.
      try {
        name =
          StringUtilities.buildVariableName(name, metaObject.getName(), null);
      } catch (RuntimeException e) {
        // Probably null strings being passed, print debugging information.
        logger.debug("Failed to build variable name for association between "
                     + thisType.getName() + " and " + oppositeType.getName());
        throw e;

      }
    }
    return name;
  }

  /**
   * Get the subsystem to which the query belongs. This is the subsystem that
   * contains the package that contains the association end being queried. This
   * subsystem is correct only for association end queries, not for association
   * link queries (queries of the linking association class objects). Use the
   * linking subsystem for such queries.
   * 
   * @return the subsystem of the query
   */
  Subsystem getSubsystem() {
    Subsystem subsystem = null;

    // Find the Subsystem-stereotyped package that contains the package of the
    // association end (the package of the association).
    ModelElementFacade facade = getPackage();
    while (facade != null && !(facade instanceof SubsystemFacadeLogicImpl)) {
      facade = facade.getPackage();
      if (facade == null) {
        break;
      }
    }

    if (facade != null) {
      // Found the subsystem, produce a Subsystem object
      subsystem =
        ((SubsystemFacadeLogicImpl)facade).handleTransformToSubsystem();
    } else {
      // No subsystem, create the default subsystem.
      subsystem = createDefaultSubsystem();
    }
    return subsystem;
  }

  /**
   * Create a Subsystem that represents the default subsystem.
   * 
   * @return a default Subsystem object
   */
  private Subsystem createDefaultSubsystem() {
    return new Subsystem(PoesysDbProfile.DEFAULT_SUBSYSTEM,
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

  /**
   * Get the subsystem to which the linking association class belongs. This is
   * the subsystem that contains the package that contains the association
   * class. Use this package as the subsystem for linking object queries.
   * 
   * @return the linking subsystem
   */
  private Subsystem getLinksSubsystem() {
    Subsystem subsystem = null;
    // Get the association object package and find its subsystem parent.
    ModelElementFacade facade = getAssociation().getPackage();
    while (facade != null && !(facade instanceof SubsystemFacadeLogicImpl)) {
      facade = facade.getPackage();
      if (facade == null) {
        break;
      }
    }

    if (facade != null) {
      // Found the subsystem, produce a Subsystem object
      subsystem =
        ((SubsystemFacadeLogicImpl)facade).handleTransformToSubsystem();
    } else {
      // No subsystem, create the default subsystem.
      subsystem = createDefaultSubsystem();
    }
    return subsystem;
  }

  /**
   * Is the association end (query) a to-many association end (multi-row query
   * either directly or through a linking table)?
   * 
   * @return true if the association end is one-to-many or many-to-many, false
   *         if one-to-one, false if many-to-one
   */
  public boolean isToMany() {
    boolean toMany = false;

    if (metaObject.getUpper() != 1) {
      toMany = true;
    }

    return toMany;
  }

  /**
   * Is the association end (query) a many-to-many association end (multi-row
   * query through a linking table)? This method returns the correct result
   * regardless of navigability on the association ends.
   * 
   * @return true if the association end is many-to-many, false if many-to-one,
   *         one-to-many, or one-to-one
   */
  public boolean isManyToMany() {
    return getManyEndCount() == 2;
  }

  /**
   * Is the association end (query) a one-to-many association end? This method
   * returns the correct result regardless of navigability on the association
   * ends.
   * 
   * @return true if the association end is one-to-many, false if many-to-one,
   *         many-to-many, or one-to-one
   */
  public boolean isOneToMany() {
    // one to many if there is a to-many and this end is not 1
    return getManyEndCount() == 1 && metaObject.getUpper() != 1;
  }

  /**
   * Get the number of association ends on the association that contains the
   * current association end (parameterized query) that are "many" ends, that
   * is, have a multiplicity greater than 1 (or *). The method returns the real
   * number regardless of any navigability restrictions on the association ends.
   * 
   * @return the number of ends with multiplicity greater than 1 (or *)
   */
  private int getManyEndCount() {
    Association a = metaObject.getAssociation();
    int manyEndCount = 0;

    if (a != null) {
      for (Object o : a.getMemberEnds()) {
        org.eclipse.uml2.Property end = (org.eclipse.uml2.Property)o;
        if (end.getUpper() != 1) {
          manyEndCount++;
        }
      }
    }

    return manyEndCount;
  }
}