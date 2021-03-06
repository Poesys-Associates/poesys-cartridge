/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: AbstractQueryAll.vsl

package com.poesys.db.test.sql;


import java.sql.ResultSet;
import java.sql.SQLException;

import com.poesys.db.InvalidParametersException;
import com.poesys.db.dao.query.IQuerySql;
import com.poesys.db.pk.IPrimaryKey;

import com.poesys.db.test.TestFactory;


/**
 * <p>
 * A query Command pattern object that implements a SQL query for all the 
 * Self2Self objects in the database; use with caution
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractQueryAllSelf2Self implements IQuerySql<com.poesys.db.test.ISelf2Self> {
  /** SQL query statement for Self2Self */
  private static final String SQL =
    "SELECT children2Self2Id, parents2Self2Id FROM Self2Self";

  public com.poesys.db.test.ISelf2Self getData(ResultSet rs) throws SQLException {
    IPrimaryKey key = 
      TestFactory.getSelf2SelfPrimaryKey(rs, "");
      
    return TestFactory.getSelf2SelfData(key, rs);
  }
  
  public IPrimaryKey getPrimaryKey(ResultSet rs) throws SQLException,
      InvalidParametersException {
    return TestFactory.getSelf2SelfPrimaryKey(rs, "");
  }

  public String getSql() {
    return SQL;
  }
}