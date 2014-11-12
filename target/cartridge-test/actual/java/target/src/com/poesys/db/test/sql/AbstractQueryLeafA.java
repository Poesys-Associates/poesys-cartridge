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
 * LeafA. This SQL specification contains a SQL statement that queries
 * a single LeafA object from the database using the primary key.
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractQueryLeafA implements IKeyQuerySql<com.poesys.db.test.ILeafA> {
  /** SQL query statement for LeafA */
  private static final String SQL =
    "SELECT Base.baseId, Base.baseString, Middle.middleString, LeafA.aString FROM LeafA LeafA JOIN Base Base ON LeafA.baseId = Base.baseId JOIN Middle Middle ON LeafA.baseId = Middle.baseId WHERE ";

  public com.poesys.db.test.ILeafA getData(IPrimaryKey key, ResultSet rs) throws SQLException {
    return com.poesys.db.test.TestFactory.getLeafAData(key, rs);
  }

  public String getSql(IPrimaryKey key) {
    return SQL + key.getSqlWhereExpression("Base");
  }
}