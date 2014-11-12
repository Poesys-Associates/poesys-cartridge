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

import org.apache.log4j.Logger;

import com.poesys.cartridges.db.utilities.DatabaseUtilities;


/**
 * @see com.poesys.cartridges.db.psm.db.ParameterizedQuery
 */
public class ParameterizedQueryImpl extends
    com.poesys.cartridges.db.psm.db.ParameterizedQuery {
  Logger logger = Logger.getLogger(ParameterizedQueryImpl.class.getName());
  /** The table alias to use to prefix a WHERE clause column for a link */
  private static final String ASSOCIATION_KEY_ALIAS = "b.";
  /** Element separator for SQL lists */
  private static final String SEP = ", ";
  /** WHERE clause equality comparison and parameter */
  private static final String WHERE_EQUALITY_EXPR = " = ? AND ";
  /** WHERE clause equality comparison and parameter for last element */
  private static final String WHERE_EQUALITY_EXPR_LAST = " = ?";
  /** ON clause equality comparison */
  private static final String ON_EQUALITY_EXPR = " = ";
  /** ON clause logical operator for combining JOIN terms */
  private static final String ON_LOGICAL_OPER = " AND ";

  /**
   * Create a ParameterizedQueryImpl object.
   */
  public ParameterizedQueryImpl() {
    super();
  }

  /**
   * Create a ParameterizedQueryImpl object.
   * 
   * @param typeName the type of object being queried
   * @param foreignTypeName the type of object by which to query
   * @param sqlTableName the name of the SQL table corresponding to the queried
   *          object type
   * @param name the association end (property) name in the foreign class
   * @param associationTypeName for a many-to-many association, the association
   *          class name; otherwise, null
   * @param sqlLinkingTableName for a many-to-many association, the SQL table
   *          name for the association class; otherwise, null
   * @param linksPropertyName the name of the collection property that contains
   *          the links (objects of the association class type)
   * @param foreignPackageName the name of the package of the type of object by
   *          which to query
   * @param packageName the name of the package that contains the type of the
   *          queried object(s)
   * @param associationPackageName the name of the package that contains the
   *          association type (linking table type)
   * @param queriedCols a collection of SELECT-list column names
   * @param keyCols a collection of foreign key column names
   * @param subsystem the subsystem in which to define the query class
   * @param joinCols a collection of ON-clause column names
   * @param queriedLinkCols a collection of SELECT-list column names from the
   *          association-class table
   * @param linkKeyCols the key columns from the association class used to query
   *          the association class by the associated class key
   * @param associationDto the DTO that represents the linking table
   * @param associatedDto the DTO that represents the associated class (the
   *          class of the object being queried)
   * @param linksSubsystem the subsystem that contains the linking association
   *          class
   * @param dto the DTO that represents the querying object
   * @param manyKeyCols the ordered list of association class key columns used
   *          to query objects from one associated class by the key of the other
   *          associated class
   */
  @SuppressWarnings("unchecked")
  public ParameterizedQueryImpl(java.lang.String typeName,
                                java.lang.String foreignTypeName,
                                java.lang.String sqlTableName,
                                java.lang.String name,
                                java.lang.String associationTypeName,
                                java.lang.String sqlLinkingTableName,
                                java.lang.String linksPropertyName,
                                java.lang.String foreignPackageName,
                                java.lang.String packageName,
                                java.lang.String associationPackageName,
                                java.util.Collection queriedCols,
                                java.util.Collection keyCols,
                                com.poesys.cartridges.db.psm.db.Subsystem subsystem,
                                java.util.Collection joinCols,
                                java.util.Collection queriedLinkCols,
                                java.util.List linkKeyCols,
                                com.poesys.cartridges.db.psm.db.Dto associationDto,
                                com.poesys.cartridges.db.psm.db.Dto associatedDto,
                                com.poesys.cartridges.db.psm.db.Subsystem linksSubsystem,
                                Dto dto,
                                java.util.List manyKeyCols) {

    super(typeName, foreignTypeName, sqlTableName, name, associationTypeName,
          sqlLinkingTableName, linksPropertyName, foreignPackageName,
          packageName, associationPackageName, queriedCols, keyCols, subsystem,
          joinCols, queriedLinkCols, linkKeyCols, associationDto,
          associatedDto, linksSubsystem, dto, manyKeyCols);
  }

  /**
   * Copy-constructor from other ParameterizedQuery
   * 
   * @param otherBean cannot be <code>null</code>
   * @throws java.lang.NullPointerException if the argument is <code>null</code>
   */
  public ParameterizedQueryImpl(ParameterizedQuery otherBean) {
    this(otherBean.getTypeName(), otherBean.getForeignTypeName(),
         otherBean.getSqlTableName(), otherBean.getName(),
         otherBean.getAssociationTypeName(),
         otherBean.getSqlLinkingTableName(), otherBean.getForeignPackageName(),
         otherBean.getPackageName(), otherBean.getAssociationPackageName(),
         otherBean.linksPropertyName, otherBean.getQueriedCols(),
         otherBean.getKeyCols(), otherBean.getSubsystem(),
         otherBean.getJoinCols(), otherBean.getQueriedLinkCols(),
         otherBean.getLinkKeyCols(), otherBean.getAssociationDto(),
         otherBean.getAssociatedDto(), otherBean.getLinksSubsystem(),
         otherBean.getDto(), otherBean.getManyKeyCols());
  }

  /**
   * @see com.poesys.cartridges.db.psm.db.ParameterizedQuery#getWhereExpr()
   */
  public java.lang.String getWhereExpr() {
    // Get the WHERE clause, not including the WHERE keyword.
    StringBuilder builder = new StringBuilder("");
    String expr = "";

    if (keyCols.size() == 0) {
      // No key columns, something's wrong
      throw new RuntimeException("No " + typeName + " (table " + sqlTableName
                                 + ") key columns for " + foreignTypeName
                                 + " query of " + name + " foreign key");
    }

    // Loop over the foreign key columns and build the expressions.
    for (Object o : keyCols) {
      SqlColumnName col = (SqlColumnName)o;
      builder.append(expr);
      if (col.getAssociationKeyCol() != null) {
        // Use hard-coded table alias for association table
        builder.append(ASSOCIATION_KEY_ALIAS);
        builder.append(col.getAssociationKeyCol().getName());
      } else {
        // Append the table name prefix
        builder.append(associatedDto.getSqlTableName());
        builder.append(".");
        builder.append(col.getName());
      }
      expr = WHERE_EQUALITY_EXPR;
    }

    // Put the " = ?" on the last column added.
    builder.append(WHERE_EQUALITY_EXPR_LAST);

    return builder.toString();
  }

  /**
   * @see com.poesys.cartridges.db.psm.db.ParameterizedQuery#getLinksWhereExpr()
   */
  @Override
  public java.lang.String getLinksWhereExpr() {
    StringBuilder builder = new StringBuilder("");
    String expr = "";

    if (linkKeyCols.size() == 0) {
      // No key columns, something's wrong
      throw new RuntimeException("No key columns for link WHERE expr for property "
                                 + getName());
    }

    // Loop over the association-end class key columns and build the
    // expressions.
    for (Object o : linkKeyCols) {
      SqlColumnName col = (SqlColumnName)o;
      builder.append(expr);
      builder.append(col.getName());
      expr = WHERE_EQUALITY_EXPR;
    }

    // Put the " = ?" on the last column added.
    builder.append(WHERE_EQUALITY_EXPR_LAST);

    return builder.toString();
  }

  @Override
  public String getManyToManyWhereExpr() {
    StringBuilder builder = new StringBuilder("");
    String expr = "";

    if (manyKeyCols.size() == 0) {
      // No key columns, something's wrong
      throw new RuntimeException("No key columns for many to many WHERE expr for property "
                                 + getName()
                                 + " querying "
                                 + associatedDto.getName()
                                 + " objects through association "
                                 + associationDto.getName());
    }

    // Loop over the opposite association-end class key columns and build the
    // expressions.
    for (Object o : manyKeyCols) {
      SqlColumnName col = (SqlColumnName)o;
      builder.append(expr);
      // Add hard-coded table alias for association table
      builder.append(ASSOCIATION_KEY_ALIAS);
      builder.append(col.getName());
      expr = WHERE_EQUALITY_EXPR;
    }

    // Put the " = ?" on the last column added.
    builder.append(WHERE_EQUALITY_EXPR_LAST);

    return builder.toString();
  }

  @Override
  public java.lang.String getSelectList() {
    StringBuilder builder = new StringBuilder("");
    String separator = "";
    boolean isManyToMany = associationTypeName != null;

    for (Object o : queriedCols) {
      SqlColumnName col = (SqlColumnName)o;
      builder.append(separator);
      if (isManyToMany) {
        String colTableName = col.getProperty().getTableName();
        // Many-to-many linking table joined, use target table prefix "a" or the
        // superclass table name
        if (colTableName.equals(associatedDto.getSqlTableName())) {
          // Property in this class, use "a" prefix for driving table
          builder.append("a.");
        } else {
          // superclass, use the property table name
          builder.append(colTableName);
          builder.append(".");
        }
      } else {
        // Append the table name prefix
        builder.append(associatedDto.getSqlTableName());
        builder.append(".");
      }
      builder.append(col.getName());
      separator = SEP;
    }

    // Add the subclass property column names, excluding superclass from above.
    for (Object s : associatedDto.getSubclasses()) {
      Dto subclass = (Dto)s;
      for (Object p : subclass.getLocalMembers()) {
        builder.append(separator);
        Property property = (Property)p;
        // Append the table name prefix
        builder.append(subclass.getSqlTableName());
        builder.append(".");
        builder.append(property.getSqlColumnName());
      }
    }

    // Add any required subclass discriminant expression to the query.
    if (associatedDto.getSubclasses().size() > 0) {
      DatabaseUtilities.addDiscriminant(builder,
                                        associatedDto.getSubclasses(),
                                        associatedDto.getKeyProperties());
    }

    return builder.toString();
  }

  @Override
  public java.lang.String getLinksSelectList() {
    return associationDto.getSqlSelectList();
  }

  @SuppressWarnings("unchecked")
  @Override
  public String getJoinExpr() {
    // Build ON clause expression, not including the ON keyword.
    StringBuilder builder = new StringBuilder("");
    String logical_oper = "";
    Collection<SqlColumnName> cols = joinCols;

    // Loop through the JOIN columns building a.col1 = b.col1 expressions.
    // The a and b aliases are hard coded in the template for the target and
    // join classes, respectively.
    for (SqlColumnName col : cols) {
      builder.append(logical_oper);
      builder.append("a.");
      builder.append(col.getName());
      builder.append(ON_EQUALITY_EXPR);
      builder.append("b.");
      if (col.getAssociationKeyCol() != null) {
        builder.append(col.getAssociationKeyCol().getName());
      } else {
        logger.error("No association key column for " + col.getName());
      }
      logical_oper = ON_LOGICAL_OPER;
    }

    return builder.toString();
  }

  @Override
  public String getFromClause() {
    // Returns the FROM clause for the linked table
    return associatedDto.getSqlFromClause();
  }

  @Override
  public String getLinksFromClause() {
    // Returns the FROM clause for the linking table
    return associationDto.getSqlFromClause();
  }

  @Override
  public String getManyToManyFromClause() {
    // ${query.sqlTableName} a JOIN ${query.sqlLinkingTableName} b ON
    // ${query.joinExpr} [ JOIN subclassA subclassA ON joinExpr ]...

    if (associatedDto == null) {
      throw new RuntimeException("No associated DTO for many-to-many from clause in "
                                 + name + " on " + typeName);
    }

    if (associationDto == null) {
      throw new RuntimeException("No association DTO for many-to-many from clause in "
                                 + name + " on " + typeName);
    }

    if (associatedDto.getSqlTableName() == null) {
      throw new RuntimeException("No SQL table name for associated DTO "
                                 + associatedDto.name);
    }

    if (associationDto.getSqlTableName() == null) {
      throw new RuntimeException("No SQL table name for association DTO "
                                 + associationDto.name);
    }

    // Drive the whole table-expression with the main table for this class as a.
    StringBuilder fromClause;
    try {
      fromClause = new StringBuilder(associatedDto.getSqlTableName());
      fromClause.append(" a JOIN ");
      // Join to the association class table as b.
      fromClause.append(associationDto.getSqlTableName());
      fromClause.append(" b ON ");
      // Add the join clause for the linking table.
      fromClause.append(getJoinExpr());

      // Add subclasses recursively with outer joins.
      if (associatedDto.subclasses.size() > 0) {
        fromClause =
          associatedDto.addSubclassesToFromClause(associatedDto.subclasses,
                                                  "a",
                                                  fromClause);
      }

      // Add superclasses recursively with outer joins.
      if (associatedDto.superclasses.size() > 0) {
        fromClause =
          associatedDto.addSuperclassesToFromClause(associatedDto.superclasses,
                                                    "a",
                                                    fromClause);
      }
    } catch (Exception e) {
      // Some kind of exception occurred, try for some context information.
      throw new RuntimeException("Exception in generating many-to-many from clause in associated class "
                                 + associatedDto.name
                                 + " to association class "
                                 + associationDto.name);
    }

    return fromClause.toString();
  }

  /**
   * Generate the lists of queried columns, key columns, and queried association
   * columns. You can call this method multiple times; whenever there are
   * properties, the internal collection gets set and after that does not
   * change, so you can attempt the generation as often as needed to get all the
   * columns filled in.
   */
  @SuppressWarnings("unchecked")
  public void generateColumnLists() {
    // Check that the querying and associated DTOs are present.
    if (dto == null) {
      throw new RuntimeException("Must have querying DTO to generate parameterized query column lists for "
                                 + getName());
    }
    if (associatedDto == null) {
      throw new RuntimeException("Must have associated DTO to generate parameterized query column lists for "
                                 + getName());
    }

    if ((queriedCols == null || queriedCols.size() == 0)
        && dto.getConstructorArgs() != null) {
      queriedCols = new ArrayList<SqlColumnName>();
      for (Object p : dto.getConstructorArgs()) {
        Property property = (Property)p;
        SqlColumnName col = new SqlColumnNameImpl(property);
        queriedCols.add(col);
      }
    }

    if ((keyCols == null || keyCols.size() == 0)
        && associatedDto.getKeyProperties() != null) {
      keyCols = new ArrayList<SqlColumnName>();
      for (Object p : associatedDto.getKeyProperties()) {
        Property property = (Property)p;
        SqlColumnName col = new SqlColumnNameImpl(property);
        keyCols.add(col);
      }
    }

    // Association DTO is present only for many-to-many links.
    if (associationDto != null) {
      if ((queriedLinkCols == null || queriedLinkCols.size() == 0)
          && associationDto.getConstructorArgs() != null) {
        queriedLinkCols = new ArrayList<SqlColumnName>();
        for (Object p : associationDto.getConstructorArgs()) {
          Property property = (Property)p;
          SqlColumnName col = new SqlColumnNameImpl(property);
          queriedLinkCols.add(col);
        }
      }
    }
  }
}
