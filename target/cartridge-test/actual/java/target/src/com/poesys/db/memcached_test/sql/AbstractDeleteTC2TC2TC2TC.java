/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: AbstractDelete.vsl

package com.poesys.db.memcached_test.sql;



import com.poesys.db.dao.delete.AbstractDeleteSql;
import com.poesys.db.pk.IPrimaryKey;


/**
 * SQL statement specification for deleting a TC2TC2TC2TC
 * 
 * @author Robert J. Muller
 */
public class AbstractDeleteTC2TC2TC2TC extends AbstractDeleteSql<com.poesys.db.memcached_test.ITC2TC2TC2TC> {
  private static final String SQL =
    "DELETE FROM TC2TC2TC2TC WHERE ";

  public String getSql(IPrimaryKey key) {
    return super.getSql(key, SQL);
  }
}