/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: AbstractQuery.vsl

package com.poesys.db.test.sql;


import java.sql.ResultSet;
import java.sql.SQLException;

import com.poesys.db.dao.query.IKeyQuerySql;
import com.poesys.db.pk.IPrimaryKey;


/**
 * <p>
 * A query Command pattern object that implements a SQL key query for the 
 * TestParent. This SQL specification contains a SQL statement that queries
 * a single TestParent object from the database using the primary key.
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractQueryTestParent implements IKeyQuerySql<com.poesys.db.test.ITestParent> {
  /** SQL query statement for TestParent */
  private static final String SQL =
    "SELECT testParentId, parentOccupation FROM TestParent WHERE ";

  public com.poesys.db.test.ITestParent getData(IPrimaryKey key, ResultSet rs) throws SQLException {
    return com.poesys.db.test.TestFactory.getTestParentData(key, rs);
  }

  public String getSql(IPrimaryKey key) {
    return SQL + key.getSqlWhereExpression("");
  }
}