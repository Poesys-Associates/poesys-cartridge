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
 * Middle. This SQL specification contains a SQL statement that queries
 * a single Middle object from the database using the primary key.
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractQueryMiddle implements IKeyQuerySql<com.poesys.db.test.IMiddle> {
  /** SQL query statement for Middle */
  private static final String SQL =
    "SELECT Base.baseId, Base.baseString, Middle.middleString, LeafB.bString, LeafA.aString, CASE WHEN LeafB.baseId IS NOT NULL THEN 'LeafB' WHEN LeafA.baseId IS NOT NULL THEN 'LeafA' ELSE NULL END AS discriminant FROM Middle Middle JOIN Base Base ON Middle.baseId = Base.baseId LEFT OUTER JOIN LeafB LeafB ON Middle.baseId = LeafB.baseId LEFT OUTER JOIN LeafA LeafA ON Middle.baseId = LeafA.baseId WHERE ";

  public com.poesys.db.test.IMiddle getData(IPrimaryKey key, ResultSet rs) throws SQLException {
    // Middle has concrete subclasses, so the query returns an object of the actual
    // type rather than just of type Middle. It uses a discriminant expression
    // that the result set returns to figure out which class to instantiate.
    
    // Get the discriminant from the result set.
    String discriminant = rs.getString("discriminant");
    
    // Check whether the discriminant is null and throw exception.
    if (discriminant == null) {
      throw new com.poesys.bs.delegate.DelegateException("Missing subclass for queried object of superclass com.poesys.db.test.Middle");
    }
    
    com.poesys.db.test.IMiddle data = null;
    // Check for LeafB, set return only if not already set
    if (discriminant.equals("LeafB") && data == null) {
      // Use the test factory to get the data.
      data = com.poesys.db.test.TestFactory.getLeafBData(key, rs);
    }
    // Check for LeafA, set return only if not already set
    if (discriminant.equals("LeafA") && data == null) {
      // Use the test factory to get the data.
      data = com.poesys.db.test.TestFactory.getLeafAData(key, rs);
    }
    return data;
  }

  public String getSql(IPrimaryKey key) {
    return SQL + key.getSqlWhereExpression("Middle");
  }
}