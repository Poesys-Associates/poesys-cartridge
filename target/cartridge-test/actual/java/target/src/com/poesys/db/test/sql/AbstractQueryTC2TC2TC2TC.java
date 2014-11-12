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
 * TC2TC2TC2TC. This SQL specification contains a SQL statement that queries
 * a single TC2TC2TC2TC object from the database using the primary key.
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractQueryTC2TC2TC2TC implements IKeyQuerySql<com.poesys.db.test.ITC2TC2TC2TC> {
  /** SQL query statement for TC2TC2TC2TC */
  private static final String SQL =
    "SELECT tc1DownChildNo, tc1UpChildNo, tc2DownChildNo, tc2UpChildNo, tc1DownTestParentId, tc1UpTestParentId, tc2DownTestParentId, tc2UpTestParentId FROM TC2TC2TC2TC WHERE ";

  public com.poesys.db.test.ITC2TC2TC2TC getData(IPrimaryKey key, ResultSet rs) throws SQLException {
    return com.poesys.db.test.TestFactory.getTC2TC2TC2TCData(key, rs);
  }

  public String getSql(IPrimaryKey key) {
    return SQL + key.getSqlWhereExpression("");
  }
}