/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: AbstractQueryAll.vsl

package com.poesys.db.test.sql;


import java.sql.ResultSet;
import java.sql.SQLException;

import com.poesys.db.InvalidParametersException;
import com.poesys.db.dao.query.IQuerySql;
import com.poesys.db.pk.IPrimaryKey;

import com.poesys.db.test.TestFactory;


/**
 * <p>
 * A query Command pattern object that implements a SQL query for all the 
 * TestB objects in the database; use with caution
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractQueryAllTestB implements IQuerySql<com.poesys.db.test.ITestB> {
  /** SQL query statement for TestB */
  private static final String SQL =
    "SELECT b_id, testAId FROM TestB";

  public com.poesys.db.test.ITestB getData(ResultSet rs) throws SQLException {
    IPrimaryKey key = 
      TestFactory.getTestBPrimaryKey(rs, "");
      
    return TestFactory.getTestBData(key, rs);
  }
  
  public IPrimaryKey getPrimaryKey(ResultSet rs) throws SQLException,
      InvalidParametersException {
    return TestFactory.getTestBPrimaryKey(rs, "");
  }

  public String getSql() {
    return SQL;
  }
}