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
 * SQL statement specification for inserting a ConcreteMiddle
 * 
 * @author Robert J. Muller
 */
public class AbstractInsertConcreteMiddle implements IInsertSql<com.poesys.db.test.IConcreteMiddle> {
  private static final String SQL =
    "INSERT INTO ConcreteMiddle (baseId, middleString) VALUES (?,?)";

  public String getSql(IPrimaryKey key) {
    return SQL;
  }

  public void setParams(PreparedStatement stmt, int index, com.poesys.db.test.IConcreteMiddle object)
      throws SQLException {
    stmt.setString(index, object.getMiddleString());
    index++;
  }
}