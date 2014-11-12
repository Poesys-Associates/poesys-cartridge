/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: AbstractQueryAssociatedOneToManyObjects.vsl

package com.poesys.db.memcached_test.sql;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.poesys.db.ConstraintViolationException;
import com.poesys.db.InvalidParametersException;
import com.poesys.db.dao.query.IParameterizedQuerySql;
import com.poesys.db.pk.IPrimaryKey;


/**
 * <p>
 * A query Command pattern object that implements a SQL query of a collection of 
 * TestNaturalChild objects using the primary key of an associated 
 * TestNaturalParent object through the association Children. This
 * is a many-to-one association from TestNaturalChild to TestNaturalParent.
 * </p>
 * <p>
 * This SQL specification contains a SQL statement that queries a collection of
 * TestNaturalChild objects from the database using the foreign key type
 * TestNaturalParent.
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractQueryChildrenByTestNaturalParent 
    implements IParameterizedQuerySql<com.poesys.db.memcached_test.ITestNaturalChild, com.poesys.db.memcached_test.ITestNaturalParent> {
  /** SQL query statement for TestNaturalChild */
  private static final String SQL =
    "SELECT TestNaturalChild.key1, TestNaturalChild.key2, TestNaturalChild.subkey FROM TestNaturalChild WHERE TestNaturalChild.key1 = ? AND TestNaturalChild.key2 = ?";

  public void bindParameters(PreparedStatement stmt, com.poesys.db.memcached_test.ITestNaturalParent parameters)
      throws SQLException {
    // Set the parameters starting with the first parameter.
    parameters.getPrimaryKey().setParams(stmt, 1);
  }

  public String getParameterValues(com.poesys.db.memcached_test.ITestNaturalParent parameters) {
    // Create the output string with the key parameters.
    return parameters.getPrimaryKey().getValueList();
  }

  public com.poesys.db.memcached_test.ITestNaturalChild getData(ResultSet rs) throws SQLException,
      ConstraintViolationException {
    return com.poesys.db.memcached_test.Memcached_testFactory.getTestNaturalChildData(getPrimaryKey(rs), rs);
  }

  public IPrimaryKey getPrimaryKey(ResultSet rs) throws SQLException,
      InvalidParametersException {
    return com.poesys.db.memcached_test.Memcached_testFactory.getTestNaturalChildPrimaryKey(rs, "");
  }

  public String getSql() {
    return SQL;
  }
}