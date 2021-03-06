/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: AbstractQueryAssociatedManyToManyObjects.vsl


package com.poesys.db.memcached_test.sql;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.poesys.db.ConstraintViolationException;
import com.poesys.db.InvalidParametersException;
import com.poesys.db.dao.query.IParameterizedQuerySql;
import com.poesys.db.pk.IPrimaryKey;



/**
 * <p>
 * A query Command pattern object that implements a SQL query of a collection of 
 * Self2 objects using the primary key of an associated 
 * Self2 object through the association Children2 using the 
 * association class Self2Self. This association is a many-to-many 
 * association from Self2 to Self2.
 * </p>
 * <p>
 * This SQL specification contains a SQL statement that queries a collection of
 * Self2 objects from the database table Self2 by
 * joining to the database table Self2Self and using the 
 * primary key from Self2 to query the collection.
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractQueryChildren2BySelf2 
    implements IParameterizedQuerySql<com.poesys.db.memcached_test.ISelf2, com.poesys.db.memcached_test.ISelf2> {
  /** SQL query statement for Self2 */
  private static final String SQL =
    "SELECT a.self2Id FROM Self2 a JOIN Self2Self b ON a.self2Id = b.children2Self2Id WHERE b.parents2Self2Id = ?";

  public void bindParameters(PreparedStatement stmt, com.poesys.db.memcached_test.ISelf2 parameters)
      throws SQLException {
    // Set the parameters starting with the first parameter.
    parameters.getPrimaryKey().setParams(stmt, 1);
  }

  public String getParameterValues(com.poesys.db.memcached_test.ISelf2 parameters) {
    // Create the output string with the key parameters.
    return parameters.getPrimaryKey().getValueList();
  }

  public com.poesys.db.memcached_test.ISelf2 getData(ResultSet rs) 
      throws SQLException, ConstraintViolationException {
    IPrimaryKey key = 
      com.poesys.db.memcached_test.Memcached_testFactory.getSelf2PrimaryKey(rs, "");
    return com.poesys.db.memcached_test.Memcached_testFactory.getSelf2Data(key, rs);
  }

  public IPrimaryKey getPrimaryKey(ResultSet rs) throws SQLException,
      InvalidParametersException {
    return com.poesys.db.memcached_test.Memcached_testFactory.getSelf2PrimaryKey(rs, "");
  }

  public String getSql() {
    return SQL;
  }
}