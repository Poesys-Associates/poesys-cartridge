/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: AbstractQuery.vsl

package com.poesys.db.memcached_test.sql;


import java.sql.ResultSet;
import java.sql.SQLException;

import com.poesys.db.dao.query.IKeyQuerySql;
import com.poesys.db.pk.IPrimaryKey;


/**
 * <p>
 * A query Command pattern object that implements a SQL key query for the 
 * TestChildChild. This SQL specification contains a SQL statement that queries
 * a single TestChildChild object from the database using the primary key.
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractQueryTestChildChild implements IKeyQuerySql<com.poesys.db.memcached_test.ITestChildChild> {
  /** SQL query statement for TestChildChild */
  private static final String SQL =
    "SELECT childNo, testParentId, testChildChildId FROM TestChildChild WHERE ";

  public com.poesys.db.memcached_test.ITestChildChild getData(IPrimaryKey key, ResultSet rs) throws SQLException {
    return com.poesys.db.memcached_test.Memcached_testFactory.getTestChildChildData(key, rs);
  }

  public String getSql(IPrimaryKey key) {
    return SQL + key.getSqlWhereExpression("");
  }
}