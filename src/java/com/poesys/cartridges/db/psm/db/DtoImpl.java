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
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.poesys.cartridges.db.utilities.DatabaseUtilities;


/**
 * @see com.poesys.cartridges.db.psm.db.Dto
 */
public class DtoImpl extends com.poesys.cartridges.db.psm.db.Dto implements
    Comparable<Dto> {
  /** Logger for this PSM class */
  @SuppressWarnings("unused")
  private static Logger logger = Logger.getLogger(DtoImpl.class);
  /** The separator string between elements of a list of SQL expressions */
  private static final String SQL_LIST_SEP = ", ";

  /**
   * Create a DtoImpl object.
   */
  public DtoImpl() {
    super();
  }

  /**
   * Create a DtoImpl object.
   * 
   * @param name the DTO name
   * @param keyType the kind of primary key the DTO has
   * @param readWrite whether the DTO is read/write or read/only
   * @param superpackage the name of the package that owns the DTO's superclass
   * @param packageName the name of the package that owns the DTO
   * @param stereotypeNames a collection of all the stereotypes of the DTO
   * @param documentation the HTML documentation for the DTO
   * @param sqlTableName the name of the SQL table corresponding to the DTO
   * @param abstractClass whether the DTO is abstract
   * @param immutable whether the DTO objects can change (false) or not (true)
   * @param removable whether the DTO objects can be removed (true) or not
   *          (false)
   * @param child whether this DTO is a child of another DTO
   * @param sequenceName the name of an Oracle or MySQL sequence a delegate uses
   *          to generate new primary keys
   * @param expiration the expiration time in milliseconds for an object in a
   *          cache that supports object expiration
   * @param dataMembers a collection of all class data members
   * @param subsystem the name of the subsystem that owns the DTO
   * @param childProperties a collection of all the child DTO associations
   * @param constructorArgs a collection of the constructor arguments
   * @param superclasses an ordered list of superclasses starting with the root
   *          class and proceeding in breadth-first order to support multiple
   *          inheritance
   * @param inheritedProperties a collection of inherited properties if any
   * @param keyProperties a collection of all the key attributes
   * @param compositeParent the optional parent DTO if this DTO is a child of a
   *          parent DTO (target of a composite aggregation)
   * @param subKeyProperties a collection of all the subkey attributes for a
   *          child DTO
   * @param associatedDtos a list of all the associated DTOs
   * @param associatedKeys a collection of all the keys of associated DTOs
   * @param insertProperties a collection of the columns to insert
   * @param localMembers a collection of the local data members
   * @param objectProperties a collection of all the objects (as opposed to
   *          attributes)
   * @param collectionProperties a collection of all the to-many objects
   * @param columns a collection of all the SQL column names for the DTO
   * @param foreignKeys a collection of all the "foreign key" association key
   *          objects; this represents the complete set of database foreign keys
   *          for the DTO table
   * @param childDtos a collection of all the child DTOs of this class; a child
   *          DTO is either the target of a composite aggregation from this DTO
   *          (the parent) or a many-to-many association class linked to this
   *          DTO
   * @param testVars a collection of the local test variables in the JUnit test
   *          class (constructor args less the required to-one association
   *          attributes)
   * @param inheritedConstructorArgs a collection of the constructor arguments
   *          inherited from a superclass
   * @param allDtoProperties a collection of the set of all properties
   *          associated with the DTO (no duplicates)
   * @param subclasses a depth-first ordered list of the subclasses of the DTO
   *          class
   */
  @SuppressWarnings("unchecked")
  public DtoImpl(java.lang.String name,
                 java.lang.String keyType,
                 boolean readWrite,
                 java.lang.String superpackage,
                 java.lang.String packageName,
                 java.util.List stereotypeNames,
                 java.lang.String documentation,
                 java.lang.String sqlTableName,
                 boolean abstractClass,
                 boolean immutable,
                 boolean removable,
                 boolean child,
                 String sequenceName,
                 Integer expiration,
                 boolean lazy,
                 java.util.Collection dataMembers,
                 com.poesys.cartridges.db.psm.db.Subsystem subsystem,
                 java.util.Collection childProperties,
                 java.util.Collection constructorArgs,
                 java.util.List superclasses,
                 java.util.Collection inheritedProperties,
                 java.util.Collection keyProperties,
                 com.poesys.cartridges.db.psm.db.Dto compositeParent,
                 java.util.Collection subKeyProperties,
                 java.util.List associatedDtos,
                 java.util.List associatedKeys,
                 java.util.Collection insertProperties,
                 java.util.Collection localMembers,
                 java.util.Collection objectProperties,
                 java.util.Collection collectionProperties,
                 java.util.Collection columns,
                 java.util.List foreignKeys,
                 java.util.List childDtos,
                 java.util.Collection testVars,
                 java.util.Collection inheritedConstructorArgs,
                 java.util.Collection allDtoProperties,
                 java.util.List subclasses) {
    super(name, keyType, readWrite, superpackage, packageName, stereotypeNames,
          documentation, sqlTableName, abstractClass, immutable, removable,
          child, sequenceName, expiration, lazy, dataMembers, subsystem,
          childProperties, constructorArgs, superclasses, inheritedProperties,
          keyProperties, compositeParent, subKeyProperties, associatedDtos,
          associatedKeys, insertProperties, localMembers, objectProperties,
          collectionProperties, columns, foreignKeys, childDtos, testVars,
          inheritedConstructorArgs, allDtoProperties, subclasses);
  }

  /**
   * Copy-constructor from other Dto
   * 
   * @param otherBean cannot be <code>null</code>
   * @throws java.lang.NullPointerException if the argument is <code>null</code>
   */
  public DtoImpl(Dto otherBean) {
    this(otherBean.getName(), otherBean.getKeyType(), otherBean.isReadWrite(),
         otherBean.getSuperpackage(), otherBean.getPackageName(),
         otherBean.getStereotypeNames(), otherBean.getDocumentation(),
         otherBean.getSqlTableName(), otherBean.isAbstractClass(),
         otherBean.isImmutable(), otherBean.isRemovable(), otherBean.isChild(),
         otherBean.getSequenceName(), otherBean.getExpiration(),
         otherBean.isLazy(), otherBean.getDataMembers(),
         otherBean.getSubsystem(), otherBean.getChildProperties(),
         otherBean.getConstructorArgs(), otherBean.getSuperclasses(),
         otherBean.getInheritedProperties(), otherBean.getKeyProperties(),
         otherBean.getCompositeParent(), otherBean.getSubKeyProperties(),
         otherBean.getAssociatedDtos(), otherBean.getAssociatedKeys(),
         otherBean.getInsertProperties(), otherBean.getLocalMembers(),
         otherBean.getObjectProperties(), otherBean.getCollectionProperties(),
         otherBean.getColumns(), otherBean.getForeignKeys(),
         otherBean.getChildDtos(), otherBean.getTestVars(),
         otherBean.getInheritedConstructorArgs(),
         otherBean.getAllDtoProperties(), otherBean.getSubclasses());
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Comparable#compareTo(java.lang.Object)
   */
  public int compareTo(Dto o) {
    // Compare by case-sensitive name
    return name.compareTo(o.name);
  }

  /**
   * @see com.poesys.cartridges.db.psm.db.Dto#getKeyClass()
   */
  public java.lang.String getKeyClass() {
    // Instiantiate a key type and get the class from it.
    String keyClass = KeyType.stringValue(keyType).getKeyClass();
    if (keyClass == null) {
      // Walk tree from root to this class, lowest key class wins
      for (Object o : superclasses) {
        Dto superclass = (Dto)o;
        String tempKeyClass =
          KeyType.stringValue(superclass.keyType).getKeyClass();
        if (tempKeyClass != null) {
          keyClass = tempKeyClass;
        }
      }
    }
    return keyClass;
  }

  @Override
  public String getInheritedKeyType() {
    String superKeyType = KeyType.NONE.toString();
    if (KeyType.NONE.toString().equals(keyType)) {
      // Walk tree from root to this class, lowest key class wins
      for (Object o : superclasses) {
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
  public String getObjectQueryAlias() {
    // Default alias to empty string.
    String alias = "";
    // Derive alias from superclasses.
    if (superclasses.size() > 0) {
      // Walk tree from root to this class exclusive, lowest alias wins
      for (Object o : superclasses) {
        Dto superclass = (Dto)o;
        // If superclass DTO has key, set alias to the class name.
        if (!superclass.keyType.equals(KeyType.NONE.toString())) {
          alias = superclass.name;
        }
      }
    }
    // If there are subclasses, set the alias to the current class name.
    if (subclasses.size() > 0) {
      alias = name;
    }
    return alias;
  }

  @Override
  public String getSuperclass() {
    // Returns the last class DTO in the list of superclasses or null if no
    // superclass.
    String name = null;
    if (superclasses != null && superclasses.size() > 0)
      for (Object o : superclasses) {
        name = ((Dto)o).getName();
      }
    return name;
  }

  @Override
  public String getRootSuperclass() {
    // Returns the name of the first class DTO in the list of superclasses or
    // returns current class if there is no superclass.
    String name = getName();

    if (superclasses != null && superclasses.size() > 0) {
      name = ((Dto)superclasses.get(0)).getName();
    }

    return name;
  }

  @Override
  public String getRootSuperclassPackage() {
    // Returns the fully qualified name of the package of the first class DTO in
    // the list of superclasses or returns the package of the current DTO if
    // there is no superclass.
    String name = getPackageName();

    if (superclasses != null && superclasses.size() > 0) {
      name = ((Dto)superclasses.get(0)).getPackageName();
    }

    return name;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.poesys.cartridges.db.psm.db.Dto#getNameAsProperty()
   */
  @Override
  public String getNameAsProperty() {
    // Return name with first letter in lower case, Java convention
    return StringUtils.uncapitalize(getName());
  }

  @Override
  public AssociatedKey getForeignKey(String name) {
    AssociatedKey key = null;

    // Look up the name as the object name of the foreign key first.
    for (Object o : foreignKeys) {
      key = (AssociatedKey)o;
      if (key.getObjectName().equals(name)) {
        break;
      } else {
        key = null;
      }
    }
    // If nothing found, try looking up the foreign key name itself.
    if (key == null) {
      for (Object o : foreignKeys) {
        key = (AssociatedKey)o;
        if (key.getName().equals(name)) {
          break;
        } else {
          key = null;
        }
      }
    }
    // If still nothing found, try looking up the prefix.
    if (key == null) {
      for (Object o : foreignKeys) {
        key = (AssociatedKey)o;
        if (key.getPrefix().equals(name)) {
          break;
        } else {
          key = null;
        }
      }
    }
    return key;
  }

  @Override
  public String getUniqueName() {
    StringBuilder builder = new StringBuilder("");
    StringTokenizer tokens = new StringTokenizer(getSubsystem().getName(), ".");
    while (tokens.hasMoreTokens()) {
      // Append the next package name, capitalizing the first character.
      builder.append(StringUtils.capitalize(tokens.nextToken()));
    }
    // Finally, append the DTO class name, already init-capped.
    builder.append(name);
    return builder.toString();
  }

  @Override
  public boolean hasLazilyLoadedMember() {
    boolean hasLazy = false;
    // Check whether the class is marked as lazily loaded.

    // Iterate through all the object properties and see if any are marked lazy.
    for (Object o : allDtoProperties) {
      Property p = (Property)o;
      if (p.isLazy()) {
        hasLazy = true;
        break; // terminate at first lazy member
      }
    }
    return hasLazy;
  }

  @Override
  public String getSqlInsertList() {
    return getSqlList(insertProperties);
  }

  @SuppressWarnings("unchecked")
  @Override
  public String getSqlSelectList() {
    String list;
    // Set appropriately aliased list depending on subclass status.
    if (superclasses.size() > 0 || subclasses.size() > 0) {
      list = getSqlSelectListWithQualifier(constructorArgs);
    } else {
      list = getSqlSelectListWithoutQualifier(constructorArgs);
    }
    return list;
  }

  /**
   * Build a SQL list, the list of expressions separated by the SQL list
   * separator (a comma). Use getSqlListWithQualifier if you want to add the
   * tablename qualifier to the expressions. The method processes only
   * attributes, not objects (nested objects that have an object type rather
   * than a SQL primitive type).
   * 
   * @param properties the list of properties to put into the list
   * @return the SELECT list
   * @see #getSqlSelectListWithoutQualifier(Collection)
   */
  @SuppressWarnings("unchecked")
  private String getSqlList(Collection properties) {
    StringBuilder builder = new StringBuilder("");
    String sep = "";
    for (Object o : properties) {
      Property p = (Property)o;
      // Must be SQL primitive
      if (p.isSqlPrimitive()) {
        builder.append(sep);
        builder.append(p.getSqlColumnName());
        sep = SQL_LIST_SEP;
      }
    }
    return builder.toString();
  }

  /**
   * Build a SQL list, the list of expressions separated by the SQL list
   * separator (a comma). This method is valid only for classes that are not
   * subclasses of other classes. The method processes only attributes, not
   * objects (nested objects that have an object type rather than a SQL
   * primitive type).
   * 
   * @param properties the list of properties to put into the list
   * @return the SELECT list
   */
  @SuppressWarnings("unchecked")
  private String getSqlSelectListWithoutQualifier(Collection properties) {
    StringBuilder builder = new StringBuilder("");
    String sep = "";
    for (Object o : properties) {
      Property p = (Property)o;
      // Must be SQL primitive (must have a JDBC set call)
      if (p.isSupportedJdbcType()) {
        builder.append(sep);
        builder.append(p.getSqlColumnName());
        sep = SQL_LIST_SEP;
      }
    }
    return builder.toString();
  }

  /**
   * Build a SQL list for a subclass or a superclass, the list of expressions
   * separated by the SQL list separator (a comma). The list prefixes the
   * property names with the SQL table name. The method processes only
   * attributes, not objects (nested objects that have an object type rather
   * than a SQL primitive type). The method creates the list using the supplied
   * properties list plus the constructor args lists for any subclasses.
   * 
   * @param properties the list of properties to put into the list for the
   *          current class and its superclasses
   * @return the SELECT list
   */
  private String getSqlSelectListWithQualifier(Collection<Property> properties) {
    List<Property> fullProperties = new ArrayList<Property>(properties);
    StringBuilder builder = new StringBuilder("");
    String sep = "";
    addSubclassesToPropertyList(fullProperties);
    List<String> revisedList = eliminateDuplicateExpressions(fullProperties);
    // Construct the list from the revised list of prefixed names.
    for (String expr : revisedList) {
      builder.append(sep);
      builder.append(expr);
      sep = SQL_LIST_SEP;
    }
    // If there are subclasses, add the discriminant.
    if (subclasses.size() > 0) {
      DatabaseUtilities.addDiscriminant(builder, subclasses, keyProperties);
    }
    return builder.toString();
  }

  /**
   * Add the subclass constructor arguments to the input properties list.
   * 
   * @param properties the list of properties to which to add the subclass
   *          constructor arguments
   */
  @SuppressWarnings("unchecked")
  public void addSubclassesToPropertyList(List properties) {
    for (Object s : subclasses) {
      Dto subclass = (Dto)s;
      properties.addAll(subclass.getConstructorArgs());
    }
  }

  /**
   * Given a list of properties, eliminate duplicates and produce a list of
   * distinct tablename.columnname expressions.
   * 
   * @param properties the list of properties to process
   * @return a list of tablename.columnname strings
   */
  private List<String> eliminateDuplicateExpressions(List<Property> properties) {
    // Eliminate duplicate columns and non-primitive columns.
    List<String> revisedList = new ArrayList<String>(properties.size());
    for (Object o : properties) {
      Property p = (Property)o;
      StringBuilder tempBuilder = new StringBuilder();
      // Must be SQL primitive (must have a JDBC set call)
      if (p.isSupportedJdbcType()) {
        // Prefix the property name with the table name as an alias.
        tempBuilder.append(p.getTableName());
        tempBuilder.append(".");
        tempBuilder.append(p.getSqlColumnName());
        if (!revisedList.contains(tempBuilder.toString())) {
          // Not in revised list yet, add it.
          revisedList.add(tempBuilder.toString());
        }
      }
    }
    return revisedList;
  }

  @SuppressWarnings("unchecked")
  @Override
  public String getSqlFromClause() {
    String tableName = getSqlTableName();

    // Drive the whole table-expression with the main table for this class.
    StringBuilder fromClause = new StringBuilder(tableName);

    // Add superclasses recursively to fill out the driver.
    if (superclasses.size() > 0) {
      fromClause.append(" ");
      fromClause.append(tableName);
      fromClause =
        addSuperclassesToFromClause(superclasses, tableName, fromClause);
    }

    // Add subclasses recursively with outer joins.
    if (subclasses.size() > 0) {
      fromClause = addSubclassesToFromClause(subclasses, tableName, fromClause);
    }

    return fromClause.toString();
  }

  /**
   * Add the subclasses of a specified class to the FROM clause in a string
   * builder instance. The method recursively walks up the tree of subclasses
   * starting from the leaves and adds LEFT OUTER JOIN clauses to the FROM
   * clause.
   * 
   * @param subclasses a list of subclasses
   * @return the augmented FROM clause
   */
  @SuppressWarnings("unchecked")
  @Override
  public StringBuilder addSubclassesToFromClause(List subclasses,
                                                 String leafAlias,
                                                 StringBuilder fromClause) {
    for (Object o : subclasses) {
      Dto dto = (Dto)o;
      fromClause.append(" LEFT OUTER JOIN ");
      createJoinExpression(leafAlias,
                           fromClause,
                           dto.getSqlTableName(),
                           dto.getKeyProperties());
    }
    return fromClause;
  }

  /**
   * Add the superclasses of a specified class to the FROM clause in a string
   * builder instance.
   * 
   * @param superclasses the set of superclasses for the current DTO class
   * @param leafAlias the name to use as the leaf-class table alias
   * @param fromClause the builder instance containing the current FROM clause
   * @return a complete FROM clause
   */
  @SuppressWarnings("unchecked")
  @Override
  public StringBuilder addSuperclassesToFromClause(java.util.List superclasses,
                                                   java.lang.String leafAlias,
                                                   java.lang.StringBuilder fromClause) {
    // Iterate through all the generalizations to construct the JOIN clauses.
    for (Object o : superclasses) {
      Dto dto = (Dto)o;
      fromClause.append(" JOIN ");
      createJoinExpression(leafAlias,
                           fromClause,
                           dto.getSqlTableName(),
                           dto.getKeyProperties());
    }
    return fromClause;
  }

  /**
   * Create the actual JOIN expression text from the various components.
   * 
   * @param leafAlias the alias for the leaf table (every other table joins to
   *          this table)
   * @param fromClause the current from clause
   * @param tableName the table name to add to the join expression
   * @param keys the collection of key attributes to add to the ON clause
   */
  private void createJoinExpression(String leafAlias, StringBuilder fromClause,
                                    String tableName, Collection<Property> keys) {
    fromClause.append(tableName);
    fromClause.append(" ");
    fromClause.append(tableName);
    fromClause.append(" ON ");

    // Iterate through the primary key attributes, ANDing them together.
    String sep = "";
    for (Property key : keys) {
      fromClause.append(sep);
      fromClause.append(leafAlias);
      fromClause.append(".");
      fromClause.append(key.getSqlColumnName());
      fromClause.append(" = ");
      fromClause.append(tableName);
      fromClause.append(".");
      fromClause.append(key.getSqlColumnName());
      sep = " AND ";
    }
  }
}
