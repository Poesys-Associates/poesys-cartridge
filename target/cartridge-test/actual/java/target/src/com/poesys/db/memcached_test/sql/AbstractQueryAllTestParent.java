/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: AbstractQueryAll.vsl

package com.poesys.db.memcached_test.sql;


import java.sql.ResultSet;
import java.sql.SQLException;

import com.poesys.db.InvalidParametersException;
import com.poesys.db.dao.query.IQuerySql;
import com.poesys.db.pk.IPrimaryKey;

import com.poesys.db.memcached_test.Memcached_testFactory;


/**
 * <p>
 * A query Command pattern object that implements a SQL query for all the 
 * TestParent objects in the database; use with caution
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractQueryAllTestParent implements IQuerySql<com.poesys.db.memcached_test.ITestParent> {
  /** SQL query statement for TestParent */
  private static final String SQL =
    "SELECT testParentId, parentOccupation FROM TestParent";

  public com.poesys.db.memcached_test.ITestParent getData(ResultSet rs) throws SQLException {
    IPrimaryKey key = 
      Memcached_testFactory.getTestParentPrimaryKey(rs, "");
      
    return Memcached_testFactory.getTestParentData(key, rs);
  }
  
  public IPrimaryKey getPrimaryKey(ResultSet rs) throws SQLException,
      InvalidParametersException {
    return Memcached_testFactory.getTestParentPrimaryKey(rs, "");
  }

  public String getSql() {
    return SQL;
  }
}