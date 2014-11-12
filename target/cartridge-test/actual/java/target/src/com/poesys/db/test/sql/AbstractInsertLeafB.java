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
 * SQL statement specification for inserting a LeafB
 * 
 * @author Robert J. Muller
 */
public class AbstractInsertLeafB implements IInsertSql<com.poesys.db.test.ILeafB> {
  private static final String SQL =
    "INSERT INTO LeafB (baseId, bString) VALUES (?,?)";

  public String getSql(IPrimaryKey key) {
    return SQL;
  }

  public void setParams(PreparedStatement stmt, int index, com.poesys.db.test.ILeafB object)
      throws SQLException {
    stmt.setString(index, object.getBString());
    index++;
  }
}
