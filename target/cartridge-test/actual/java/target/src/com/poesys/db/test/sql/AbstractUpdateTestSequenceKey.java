/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: AbstractUpdate.vsl

package com.poesys.db.test.sql;


import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.poesys.db.dao.update.IUpdateSql;
import com.poesys.db.pk.IPrimaryKey;


/**
 * SQL statement specification for updating a TestSequenceKey with read/write properties
 * 
 * @author Robert J. Muller
 */
public class AbstractUpdateTestSequenceKey implements IUpdateSql<com.poesys.db.test.ITestSequenceKey> {
  /** SQL UPDATE statement for TestSequenceKey */
  private static final String SQL =
    "UPDATE TestSequenceKey SET data1 = ? WHERE ";

  @Override
  public String getSql(IPrimaryKey key) {
    StringBuilder builder = new StringBuilder(SQL);
    builder.append(key.getSqlWhereExpression(""));
    return builder.toString();
  }

  @Override
  public int setParams(PreparedStatement stmt, int index, com.poesys.db.test.ITestSequenceKey object)
      throws SQLException {
    stmt.setString(index, object.getData1());
    index++;
    // sets primary key in where clause
    index = object.getPrimaryKey().setParams(stmt, index);
    return index;
  }
}
