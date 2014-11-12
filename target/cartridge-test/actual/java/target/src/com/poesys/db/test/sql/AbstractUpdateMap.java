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
 * SQL statement specification for updating a Map with read/write properties
 * 
 * @author Robert J. Muller
 */
public class AbstractUpdateMap implements IUpdateSql<com.poesys.db.test.IMap> {
  /** SQL UPDATE statement for Map */
  private static final String SQL =
    "UPDATE Map SET location = ? WHERE ";

  @Override
  public String getSql(IPrimaryKey key) {
    StringBuilder builder = new StringBuilder(SQL);
    builder.append(key.getSqlWhereExpression(""));
    return builder.toString();
  }

  @Override
  public int setParams(PreparedStatement stmt, int index, com.poesys.db.test.IMap object)
      throws SQLException {
    stmt.setLong(index, object.getLocation());
    index++;
    // sets primary key in where clause
    index = object.getPrimaryKey().setParams(stmt, index);
    return index;
  }
}
