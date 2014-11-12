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
 * TestAssociationChild. This SQL specification contains a SQL statement that queries
 * a single TestAssociationChild object from the database using the primary key.
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractQueryTestAssociationChild implements IKeyQuerySql<com.poesys.db.test.ITestAssociationChild> {
  /** SQL query statement for TestAssociationChild */
  private static final String SQL =
    "SELECT key1, key2, testXId, testYId, tsACAttr FROM TestAssociationChild WHERE ";

  public com.poesys.db.test.ITestAssociationChild getData(IPrimaryKey key, ResultSet rs) throws SQLException {
    return com.poesys.db.test.TestFactory.getTestAssociationChildData(key, rs);
  }

  public String getSql(IPrimaryKey key) {
    return SQL + key.getSqlWhereExpression("");
  }
}