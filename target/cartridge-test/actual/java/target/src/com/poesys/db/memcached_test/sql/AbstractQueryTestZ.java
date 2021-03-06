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
 * TestZ. This SQL specification contains a SQL statement that queries
 * a single TestZ object from the database using the primary key.
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractQueryTestZ implements IKeyQuerySql<com.poesys.db.memcached_test.ITestZ> {
  /** SQL query statement for TestZ */
  private static final String SQL =
    "SELECT testXId, testYId, zAttr FROM TestZ WHERE ";

  public com.poesys.db.memcached_test.ITestZ getData(IPrimaryKey key, ResultSet rs) throws SQLException {
    return com.poesys.db.memcached_test.Memcached_testFactory.getTestZData(key, rs);
  }

  public String getSql(IPrimaryKey key) {
    return SQL + key.getSqlWhereExpression("");
  }
}