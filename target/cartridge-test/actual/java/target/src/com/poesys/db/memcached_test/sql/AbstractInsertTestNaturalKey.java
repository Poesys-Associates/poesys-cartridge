/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: AbstractInsert.vsl

package com.poesys.db.memcached_test.sql;



import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.poesys.db.dao.insert.IInsertSql;
import com.poesys.db.pk.IPrimaryKey;


/**
 * SQL statement specification for inserting a TestNaturalKey
 * 
 * @author Robert J. Muller
 */
public class AbstractInsertTestNaturalKey implements IInsertSql<com.poesys.db.memcached_test.ITestNaturalKey> {
  private static final String SQL =
    "INSERT INTO TestNaturalKey (key1, number1, number2) VALUES (?,?,?)";

  public String getSql(IPrimaryKey key) {
    return SQL;
  }

  public void setParams(PreparedStatement stmt, int index, com.poesys.db.memcached_test.ITestNaturalKey object)
      throws SQLException {
    stmt.setDouble(index, object.getNumber1());
    index++;
    stmt.setDouble(index, object.getNumber2());
    index++;
  }
}
