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
 * Map. This SQL specification contains a SQL statement that queries
 * a single Map object from the database using the primary key.
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractQueryMap implements IKeyQuerySql<com.poesys.db.test.IMap> {
  /** SQL query statement for Map */
  private static final String SQL =
    "SELECT mapId, location FROM Map WHERE ";

  public com.poesys.db.test.IMap getData(IPrimaryKey key, ResultSet rs) throws SQLException {
    return com.poesys.db.test.TestFactory.getMapData(key, rs);
  }

  public String getSql(IPrimaryKey key) {
    return SQL + key.getSqlWhereExpression("");
  }
}