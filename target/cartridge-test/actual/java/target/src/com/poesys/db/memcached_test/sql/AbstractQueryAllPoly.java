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
 * Poly objects in the database; use with caution
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractQueryAllPoly implements IQuerySql<com.poesys.db.memcached_test.IPoly> {
  /** SQL query statement for Poly */
  private static final String SQL =
    "SELECT polyId, sequence FROM Poly";

  public com.poesys.db.memcached_test.IPoly getData(ResultSet rs) throws SQLException {
    IPrimaryKey key = 
      Memcached_testFactory.getPolyPrimaryKey(rs, "");
      
    return Memcached_testFactory.getPolyData(key, rs);
  }
  
  public IPrimaryKey getPrimaryKey(ResultSet rs) throws SQLException,
      InvalidParametersException {
    return Memcached_testFactory.getPolyPrimaryKey(rs, "");
  }

  public String getSql() {
    return SQL;
  }
}