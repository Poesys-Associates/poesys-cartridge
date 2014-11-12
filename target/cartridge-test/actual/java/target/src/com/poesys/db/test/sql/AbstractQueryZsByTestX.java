/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: AbstractQueryAssociatedLinks.vsl

package com.poesys.db.test.sql;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.poesys.db.ConstraintViolationException;
import com.poesys.db.InvalidParametersException;
import com.poesys.db.dao.query.IParameterizedQuerySql;
import com.poesys.db.pk.IPrimaryKey;

import com.poesys.db.test.TestFactory;


/**
 * <p>
 * A query Command pattern object that implements a SQL query of a collection of 
 * TestZ objects using the primary key of the associated
 * class TestX. These objects are the linking objects from the SQL 
 * table TestZ rather than the objects associated through
 * the link (TestY), which have a separate representation.
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractQueryZsByTestX 
    implements IParameterizedQuerySql<com.poesys.db.test.ITestZ, com.poesys.db.test.ITestX> {
  /** SQL query statement for TestZ */
  private static final String SQL =
    "SELECT testXId, testYId, zAttr FROM TestZ WHERE testXId = ?";

  public void bindParameters(PreparedStatement stmt, com.poesys.db.test.ITestX parameters)
      throws SQLException {
    // Set the parameters starting with the first parameter.
    parameters.getPrimaryKey().setParams(stmt, 1);
  }

  public String getParameterValues(com.poesys.db.test.ITestX parameters) {
    // Create the output string with the key parameters.
    return parameters.getPrimaryKey().getValueList();
  }

  public com.poesys.db.test.ITestZ getData(ResultSet rs) throws SQLException,
      ConstraintViolationException {
    return TestFactory.getTestZData(getPrimaryKey(rs), rs);
  }

  public IPrimaryKey getPrimaryKey(ResultSet rs) throws SQLException,
      InvalidParametersException {
    return TestFactory.getTestZPrimaryKey(rs, "");
  }

  public String getSql() {
    return SQL;
  }
}