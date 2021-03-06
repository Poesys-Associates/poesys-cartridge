/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: AbstractQueryAssociatedManyToManyObjects.vsl


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
 * TestY objects using the primary key of an associated 
 * TestX object through the association Y using the 
 * association class TestZ. This association is a many-to-many 
 * association from TestY to TestX.
 * </p>
 * <p>
 * This SQL specification contains a SQL statement that queries a collection of
 * TestY objects from the database table TestY by
 * joining to the database table TestZ and using the 
 * primary key from TestX to query the collection.
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractQueryYByTestX 
    implements IParameterizedQuerySql<com.poesys.db.memcached_test.ITestY, com.poesys.db.memcached_test.ITestX> {
  /** SQL query statement for TestY */
  private static final String SQL =
    "SELECT a.testYId, a.yAttr FROM TestY a JOIN TestZ b ON a.testYId = b.testYId WHERE b.testXId = ?";

  public void bindParameters(PreparedStatement stmt, com.poesys.db.memcached_test.ITestX parameters)
      throws SQLException {
    // Set the parameters starting with the first parameter.
    parameters.getPrimaryKey().setParams(stmt, 1);
  }

  public String getParameterValues(com.poesys.db.memcached_test.ITestX parameters) {
    // Create the output string with the key parameters.
    return parameters.getPrimaryKey().getValueList();
  }

  public com.poesys.db.memcached_test.ITestY getData(ResultSet rs) 
      throws SQLException, ConstraintViolationException {
    IPrimaryKey key = 
      com.poesys.db.memcached_test.Memcached_testFactory.getTestYPrimaryKey(rs, "");
    return com.poesys.db.memcached_test.Memcached_testFactory.getTestYData(key, rs);
  }

  public IPrimaryKey getPrimaryKey(ResultSet rs) throws SQLException,
      InvalidParametersException {
    return com.poesys.db.memcached_test.Memcached_testFactory.getTestYPrimaryKey(rs, "");
  }

  public String getSql() {
    return SQL;
  }
}