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
 * LeafB. This SQL specification contains a SQL statement that queries
 * a single LeafB object from the database using the primary key.
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractQueryLeafB implements IKeyQuerySql<com.poesys.db.memcached_test.ILeafB> {
  /** SQL query statement for LeafB */
  private static final String SQL =
    "SELECT Base.baseId, Base.baseString, Middle.middleString, LeafB.bString FROM LeafB LeafB JOIN Base Base ON LeafB.baseId = Base.baseId JOIN Middle Middle ON LeafB.baseId = Middle.baseId WHERE ";

  public com.poesys.db.memcached_test.ILeafB getData(IPrimaryKey key, ResultSet rs) throws SQLException {
    return com.poesys.db.memcached_test.Memcached_testFactory.getLeafBData(key, rs);
  }

  public String getSql(IPrimaryKey key) {
    return SQL + key.getSqlWhereExpression("Base");
  }
}