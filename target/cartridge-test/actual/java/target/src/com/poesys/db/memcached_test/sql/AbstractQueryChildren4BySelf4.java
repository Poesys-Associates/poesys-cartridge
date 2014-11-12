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
 * Self4 objects using the primary key of an associated 
 * Self4 object through the association Children4 using the 
 * association class Self4Self. This association is a many-to-many 
 * association from Self4 to Self4.
 * </p>
 * <p>
 * This SQL specification contains a SQL statement that queries a collection of
 * Self4 objects from the database table Self4 by
 * joining to the database table Self4Self and using the 
 * primary key from Self4 to query the collection.
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractQueryChildren4BySelf4 
    implements IParameterizedQuerySql<com.poesys.db.memcached_test.ISelf4, com.poesys.db.memcached_test.ISelf4> {
  /** SQL query statement for Self4 */
  private static final String SQL =
    "SELECT a.key1, a.key2 FROM Self4 a JOIN Self4Self b ON a.key1 = b.children4Child AND a.key2 = b.children4Child WHERE b.parents4Parent = ? AND b.parents4Parent = ?";

  public void bindParameters(PreparedStatement stmt, com.poesys.db.memcached_test.ISelf4 parameters)
      throws SQLException {
    // Set the parameters starting with the first parameter.
    parameters.getPrimaryKey().setParams(stmt, 1);
  }

  public String getParameterValues(com.poesys.db.memcached_test.ISelf4 parameters) {
    // Create the output string with the key parameters.
    return parameters.getPrimaryKey().getValueList();
  }

  public com.poesys.db.memcached_test.ISelf4 getData(ResultSet rs) 
      throws SQLException, ConstraintViolationException {
    IPrimaryKey key = 
      com.poesys.db.memcached_test.Memcached_testFactory.getSelf4PrimaryKey(rs, "");
    return com.poesys.db.memcached_test.Memcached_testFactory.getSelf4Data(key, rs);
  }

  public IPrimaryKey getPrimaryKey(ResultSet rs) throws SQLException,
      InvalidParametersException {
    return com.poesys.db.memcached_test.Memcached_testFactory.getSelf4PrimaryKey(rs, "");
  }

  public String getSql() {
    return SQL;
  }
}