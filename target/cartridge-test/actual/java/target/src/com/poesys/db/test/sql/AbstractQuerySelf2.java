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
 * Self2. This SQL specification contains a SQL statement that queries
 * a single Self2 object from the database using the primary key.
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractQuerySelf2 implements IKeyQuerySql<com.poesys.db.test.ISelf2> {
  /** SQL query statement for Self2 */
  private static final String SQL =
    "SELECT self2Id FROM Self2 WHERE ";

  public com.poesys.db.test.ISelf2 getData(IPrimaryKey key, ResultSet rs) throws SQLException {
    return com.poesys.db.test.TestFactory.getSelf2Data(key, rs);
  }

  public String getSql(IPrimaryKey key) {
    return SQL + key.getSqlWhereExpression("");
  }
}