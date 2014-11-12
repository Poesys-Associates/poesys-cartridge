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
 * Base objects in the database; use with caution
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractQueryAllBase implements IQuerySql<com.poesys.db.test.IBase> {
  /** SQL query statement for Base */
  private static final String SQL =
    "SELECT Base.baseId, Base.baseString, ConcreteMiddle.middleString, LeafC.cString, Middle.middleString, LeafB.bString, LeafA.aString, CASE WHEN LeafC.baseId IS NOT NULL THEN 'LeafC' WHEN ConcreteMiddle.baseId IS NOT NULL THEN 'ConcreteMiddle' WHEN LeafB.baseId IS NOT NULL THEN 'LeafB' WHEN LeafA.baseId IS NOT NULL THEN 'LeafA' ELSE NULL END AS discriminant FROM Base LEFT OUTER JOIN LeafC LeafC ON Base.baseId = LeafC.baseId LEFT OUTER JOIN ConcreteMiddle ConcreteMiddle ON Base.baseId = ConcreteMiddle.baseId LEFT OUTER JOIN LeafB LeafB ON Base.baseId = LeafB.baseId LEFT OUTER JOIN LeafA LeafA ON Base.baseId = LeafA.baseId LEFT OUTER JOIN Middle Middle ON Base.baseId = Middle.baseId";

  public com.poesys.db.test.IBase getData(ResultSet rs) throws SQLException {
    IPrimaryKey key = 
      TestFactory.getBasePrimaryKey(rs, "");
      
    // Base has subclasses, so the query returns an object of the actual
    // type rather than just of type Base. It uses a discriminant expression
    // that the result set returns to figure out which class to instantiate.
    
    // Get the discriminant from the result set.
    String discriminant = rs.getString("discriminant");
    
    // Check whether the discriminant is null and throw exception.
    if (discriminant == null) {
      throw new com.poesys.bs.delegate.DelegateException("Missing subclass for queried object of superclass com.poesys.db.test.Base");
    }
    
    com.poesys.db.test.IBase data = null;
    // Check for LeafC, set return only if not already set
    if (discriminant.equals("LeafC") && data == null) {
      // Use the test factory to get the data.
      data = 
        (com.poesys.db.test.IBase)com.poesys.db.test.TestFactory.getLeafCData(key, rs);
    }
    // Check for ConcreteMiddle, set return only if not already set
    if (discriminant.equals("ConcreteMiddle") && data == null) {
      // Use the test factory to get the data.
      data = 
        (com.poesys.db.test.IBase)com.poesys.db.test.TestFactory.getConcreteMiddleData(key, rs);
    }
    // Check for LeafB, set return only if not already set
    if (discriminant.equals("LeafB") && data == null) {
      // Use the test factory to get the data.
      data = 
        (com.poesys.db.test.IBase)com.poesys.db.test.TestFactory.getLeafBData(key, rs);
    }
    // Check for LeafA, set return only if not already set
    if (discriminant.equals("LeafA") && data == null) {
      // Use the test factory to get the data.
      data = 
        (com.poesys.db.test.IBase)com.poesys.db.test.TestFactory.getLeafAData(key, rs);
    }
    return data;
  }
  
  public IPrimaryKey getPrimaryKey(ResultSet rs) throws SQLException,
      InvalidParametersException {
    return TestFactory.getBasePrimaryKey(rs, "");
  }

  public String getSql() {
    return SQL;
  }
}