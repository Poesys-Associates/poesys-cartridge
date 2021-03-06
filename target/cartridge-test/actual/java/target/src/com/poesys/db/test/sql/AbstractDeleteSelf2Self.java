/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: AbstractDelete.vsl

package com.poesys.db.test.sql;



import com.poesys.db.dao.delete.AbstractDeleteSql;
import com.poesys.db.pk.IPrimaryKey;


/**
 * SQL statement specification for deleting a Self2Self
 * 
 * @author Robert J. Muller
 */
public class AbstractDeleteSelf2Self extends AbstractDeleteSql<com.poesys.db.test.ISelf2Self> {
  private static final String SQL =
    "DELETE FROM Self2Self WHERE ";

  public String getSql(IPrimaryKey key) {
    return super.getSql(key, SQL);
  }
}
