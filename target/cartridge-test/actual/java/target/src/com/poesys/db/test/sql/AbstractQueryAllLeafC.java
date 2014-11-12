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
 * LeafC objects in the database; use with caution
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractQueryAllLeafC implements IQuerySql<com.poesys.db.test.ILeafC> {
  /** SQL query statement for LeafC */
  private static final String SQL =
    "SELECT Base.baseId, Base.baseString, ConcreteMiddle.middleString, LeafC.cString FROM LeafC LeafC JOIN Base Base ON LeafC.baseId = Base.baseId JOIN ConcreteMiddle ConcreteMiddle ON LeafC.baseId = ConcreteMiddle.baseId";

  public com.poesys.db.test.ILeafC getData(ResultSet rs) throws SQLException {
    IPrimaryKey key = 
      TestFactory.getLeafCPrimaryKey(rs, "");
      
    return TestFactory.getLeafCData(key, rs);
  }
  
  public IPrimaryKey getPrimaryKey(ResultSet rs) throws SQLException,
      InvalidParametersException {
    return TestFactory.getLeafCPrimaryKey(rs, "");
  }

  public String getSql() {
    return SQL;
  }
}