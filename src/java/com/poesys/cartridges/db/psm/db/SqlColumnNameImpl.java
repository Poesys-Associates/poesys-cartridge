/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
/**
 * This is only generated once! It will never be overwritten. You can (and have
 * to!) safely modify it by hand.
 */
package com.poesys.cartridges.db.psm.db;

/**
 * @see com.poesys.cartridges.db.psm.db.SqlColumnName
 */
public class SqlColumnNameImpl extends
    com.poesys.cartridges.db.psm.db.SqlColumnName {
  /**
   * Create a SqlColumnNameImpl object.
   */
  public SqlColumnNameImpl() {
    super();
  }

  /**
   * Create a SqlColumnNameImpl object. For a many-to-many join, the association
   * key column gets filled in using the setter.
   * 
   * @param property the DTO property corresponding to the SQL column
   */
  public SqlColumnNameImpl(com.poesys.cartridges.db.psm.db.Property property) {
    super(property);
  }

  /**
   * Create a SqlColumnNameImpl object.
   * 
   * @param associationKeyCol the association key column name
   * @param property the DTO property corresponding to the SQL column
   */
  public SqlColumnNameImpl(com.poesys.cartridges.db.psm.db.SqlColumnName associationKeyCol,
                           com.poesys.cartridges.db.psm.db.Property property) {
    super(associationKeyCol, property);
  }

  /**
   * Copy-constructor from other SqlColumnName
   * 
   * @param otherBean cannot be <code>null</code>
   * @throws java.lang.NullPointerException if the argument is <code>null</code>
   */
  public SqlColumnNameImpl(SqlColumnName otherBean) {
    this(otherBean.getProperty());
  }

  @Override
  public String getName() {
    // Call the dynamic name generation method on the property to get the SQL
    // column name based on the property configuration and context.
    return property.getSqlColumnName();
  }
}
