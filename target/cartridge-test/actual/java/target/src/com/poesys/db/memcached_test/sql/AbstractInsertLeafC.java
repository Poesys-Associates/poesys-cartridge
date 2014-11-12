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
 * SQL statement specification for inserting a LeafC
 * 
 * @author Robert J. Muller
 */
public class AbstractInsertLeafC implements IInsertSql<com.poesys.db.memcached_test.ILeafC> {
  private static final String SQL =
    "INSERT INTO LeafC (baseId, cString) VALUES (?,?)";

  public String getSql(IPrimaryKey key) {
    return SQL;
  }

  public void setParams(PreparedStatement stmt, int index, com.poesys.db.memcached_test.ILeafC object)
      throws SQLException {
    stmt.setString(index, object.getCString());
    index++;
  }
}
