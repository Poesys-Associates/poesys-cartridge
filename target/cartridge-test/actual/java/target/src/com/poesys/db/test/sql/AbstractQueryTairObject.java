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
 * TairObject. This SQL specification contains a SQL statement that queries
 * a single TairObject object from the database using the primary key.
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractQueryTairObject implements IKeyQuerySql<com.poesys.db.test.ITairObject> {
  /** SQL query statement for TairObject */
  private static final String SQL =
    "SELECT TairObject.tairObjectId, TairObject.version, MapElement.mapId, MapElement.polyId, MapElement.elementType, CASE WHEN MapElement.tairObjectId IS NOT NULL THEN 'MapElement' ELSE NULL END AS discriminant FROM TairObject LEFT OUTER JOIN MapElement MapElement ON TairObject.tairObjectId = MapElement.tairObjectId WHERE ";

  public com.poesys.db.test.ITairObject getData(IPrimaryKey key, ResultSet rs) throws SQLException {
    // TairObject has concrete subclasses, so the query returns an object of the actual
    // type rather than just of type TairObject. It uses a discriminant expression
    // that the result set returns to figure out which class to instantiate.
    
    // Get the discriminant from the result set.
    String discriminant = rs.getString("discriminant");
    
    // Check whether the discriminant is null and throw exception.
    if (discriminant == null) {
      throw new com.poesys.bs.delegate.DelegateException("Missing subclass for queried object of superclass com.poesys.db.test.TairObject");
    }
    
    com.poesys.db.test.ITairObject data = null;
    // Check for MapElement, set return only if not already set
    if (discriminant.equals("MapElement") && data == null) {
      // Use the test factory to get the data.
      data = com.poesys.db.test.TestFactory.getMapElementData(key, rs);
    }
    return data;
  }

  public String getSql(IPrimaryKey key) {
    return SQL + key.getSqlWhereExpression("TairObject");
  }
}