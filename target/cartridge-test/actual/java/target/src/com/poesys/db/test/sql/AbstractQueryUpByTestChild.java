/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: AbstractQueryAssociatedManyToManyObjects.vsl


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
 * TestChild objects using the primary key of an associated 
 * TestChild object through the association Up using the 
 * association class TestChild2TestChild. This association is a many-to-many 
 * association from TestChild to TestChild.
 * </p>
 * <p>
 * This SQL specification contains a SQL statement that queries a collection of
 * TestChild objects from the database table Child by
 * joining to the database table TestChild2TestChild and using the 
 * primary key from TestChild to query the collection.
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractQueryUpByTestChild 
    implements IParameterizedQuerySql<com.poesys.db.test.ITestChild, com.poesys.db.test.ITestChild> {
  /** SQL query statement for Child */
  private static final String SQL =
    "SELECT a.testParentId, a.childNo FROM Child a JOIN TestChild2TestChild b ON a.childNo = b.upChildNo AND a.testParentId = b.upTestParentId WHERE b.downChildNo = ? AND b.downTestParentId = ?";

  public void bindParameters(PreparedStatement stmt, com.poesys.db.test.ITestChild parameters)
      throws SQLException {
    // Set the parameters starting with the first parameter.
    parameters.getPrimaryKey().setParams(stmt, 1);
  }

  public String getParameterValues(com.poesys.db.test.ITestChild parameters) {
    // Create the output string with the key parameters.
    return parameters.getPrimaryKey().getValueList();
  }

  public com.poesys.db.test.ITestChild getData(ResultSet rs) 
      throws SQLException, ConstraintViolationException {
    IPrimaryKey key = 
      com.poesys.db.test.TestFactory.getTestChildPrimaryKey(rs, "");
    return com.poesys.db.test.TestFactory.getTestChildData(key, rs);
  }

  public IPrimaryKey getPrimaryKey(ResultSet rs) throws SQLException,
      InvalidParametersException {
    return com.poesys.db.test.TestFactory.getTestChildPrimaryKey(rs, "");
  }

  public String getSql() {
    return SQL;
  }
}