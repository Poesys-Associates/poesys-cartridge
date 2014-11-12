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
 * LeafA objects in the database; use with caution
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractQueryAllLeafA implements IQuerySql<com.poesys.db.memcached_test.ILeafA> {
  /** SQL query statement for LeafA */
  private static final String SQL =
    "SELECT Base.baseId, Base.baseString, Middle.middleString, LeafA.aString FROM LeafA LeafA JOIN Base Base ON LeafA.baseId = Base.baseId JOIN Middle Middle ON LeafA.baseId = Middle.baseId";

  public com.poesys.db.memcached_test.ILeafA getData(ResultSet rs) throws SQLException {
    IPrimaryKey key = 
      Memcached_testFactory.getLeafAPrimaryKey(rs, "");
      
    return Memcached_testFactory.getLeafAData(key, rs);
  }
  
  public IPrimaryKey getPrimaryKey(ResultSet rs) throws SQLException,
      InvalidParametersException {
    return Memcached_testFactory.getLeafAPrimaryKey(rs, "");
  }

  public String getSql() {
    return SQL;
  }
}