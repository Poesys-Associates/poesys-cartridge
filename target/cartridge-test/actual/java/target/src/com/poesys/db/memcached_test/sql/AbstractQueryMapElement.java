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
 * MapElement. This SQL specification contains a SQL statement that queries
 * a single MapElement object from the database using the primary key.
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractQueryMapElement implements IKeyQuerySql<com.poesys.db.memcached_test.IMapElement> {
  /** SQL query statement for MapElement */
  private static final String SQL =
    "SELECT TairObject.tairObjectId, MapElement.mapId, MapElement.polyId, TairObject.version, MapElement.elementType FROM MapElement MapElement JOIN TairObject TairObject ON MapElement.tairObjectId = TairObject.tairObjectId WHERE ";

  public com.poesys.db.memcached_test.IMapElement getData(IPrimaryKey key, ResultSet rs) throws SQLException {
    return com.poesys.db.memcached_test.Memcached_testFactory.getMapElementData(key, rs);
  }

  public String getSql(IPrimaryKey key) {
    return SQL + key.getSqlWhereExpression("TairObject");
  }
}