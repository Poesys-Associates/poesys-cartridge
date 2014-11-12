/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: AbstractQueryAssociatedOneToManyObjects.vsl

package com.poesys.db.test.sql;


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
 * TestChildChild objects using the primary key of an associated 
 * TestChild object through the association Grandchildren. This
 * is a many-to-one association from TestChildChild to TestChild.
 * </p>
 * <p>
 * This SQL specification contains a SQL statement that queries a collection of
 * TestChildChild objects from the database using the foreign key type
 * TestChild.
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractQueryGrandchildrenByTestChild 
    implements IParameterizedQuerySql<com.poesys.db.test.ITestChildChild, com.poesys.db.test.ITestChild> {
  /** SQL query statement for TestChildChild */
  private static final String SQL =
    "SELECT TestChildChild.childNo, TestChildChild.testParentId, TestChildChild.testChildChildId FROM TestChildChild WHERE TestChildChild.childNo = ? AND TestChildChild.testParentId = ?";

  public void bindParameters(PreparedStatement stmt, com.poesys.db.test.ITestChild parameters)
      throws SQLException {
    // Set the parameters starting with the first parameter.
    parameters.getPrimaryKey().setParams(stmt, 1);
  }

  public String getParameterValues(com.poesys.db.test.ITestChild parameters) {
    // Create the output string with the key parameters.
    return parameters.getPrimaryKey().getValueList();
  }

  public com.poesys.db.test.ITestChildChild getData(ResultSet rs) throws SQLException,
      ConstraintViolationException {
    return com.poesys.db.test.TestFactory.getTestChildChildData(getPrimaryKey(rs), rs);
  }

  public IPrimaryKey getPrimaryKey(ResultSet rs) throws SQLException,
      InvalidParametersException {
    return com.poesys.db.test.TestFactory.getTestChildChildPrimaryKey(rs, "");
  }

  public String getSql() {
    return SQL;
  }
}