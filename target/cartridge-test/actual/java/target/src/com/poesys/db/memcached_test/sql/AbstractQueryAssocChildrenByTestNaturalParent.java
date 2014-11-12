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
 * TestAssociationChild objects using the primary key of an associated 
 * TestNaturalParent object through the association AssocChildren. This
 * is a many-to-one association from TestAssociationChild to TestNaturalParent.
 * </p>
 * <p>
 * This SQL specification contains a SQL statement that queries a collection of
 * TestAssociationChild objects from the database using the foreign key type
 * TestNaturalParent.
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractQueryAssocChildrenByTestNaturalParent 
    implements IParameterizedQuerySql<com.poesys.db.memcached_test.ITestAssociationChild, com.poesys.db.memcached_test.ITestNaturalParent> {
  /** SQL query statement for TestAssociationChild */
  private static final String SQL =
    "SELECT TestAssociationChild.key1, TestAssociationChild.key2, TestAssociationChild.testXId, TestAssociationChild.testYId, TestAssociationChild.tsACAttr FROM TestAssociationChild WHERE TestAssociationChild.key1 = ? AND TestAssociationChild.key2 = ?";

  public void bindParameters(PreparedStatement stmt, com.poesys.db.memcached_test.ITestNaturalParent parameters)
      throws SQLException {
    // Set the parameters starting with the first parameter.
    parameters.getPrimaryKey().setParams(stmt, 1);
  }

  public String getParameterValues(com.poesys.db.memcached_test.ITestNaturalParent parameters) {
    // Create the output string with the key parameters.
    return parameters.getPrimaryKey().getValueList();
  }

  public com.poesys.db.memcached_test.ITestAssociationChild getData(ResultSet rs) throws SQLException,
      ConstraintViolationException {
    return com.poesys.db.memcached_test.Memcached_testFactory.getTestAssociationChildData(getPrimaryKey(rs), rs);
  }

  public IPrimaryKey getPrimaryKey(ResultSet rs) throws SQLException,
      InvalidParametersException {
    return com.poesys.db.memcached_test.Memcached_testFactory.getTestAssociationChildPrimaryKey(rs, "");
  }

  public String getSql() {
    return SQL;
  }
}