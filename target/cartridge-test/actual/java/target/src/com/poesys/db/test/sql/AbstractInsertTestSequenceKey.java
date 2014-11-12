/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: AbstractInsert.vsl

package com.poesys.db.test.sql;



import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.poesys.db.dao.insert.IInsertSql;
import com.poesys.db.pk.IPrimaryKey;


/**
 * SQL statement specification for inserting a TestSequenceKey
 * 
 * @author Robert J. Muller
 */
public class AbstractInsertTestSequenceKey implements IInsertSql<com.poesys.db.test.ITestSequenceKey> {
  private static final String SQL =
    "INSERT INTO TestSequenceKey (testSequenceKeyId, data1) VALUES (?,?)";

  public String getSql(IPrimaryKey key) {
    return SQL;
  }

  public void setParams(PreparedStatement stmt, int index, com.poesys.db.test.ITestSequenceKey object)
      throws SQLException {
    stmt.setString(index, object.getData1());
    index++;
  }
}
