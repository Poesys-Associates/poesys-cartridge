/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: AbstractDelete.vsl

package com.poesys.db.test.sql;



import com.poesys.db.dao.delete.AbstractDeleteSql;
import com.poesys.db.pk.IPrimaryKey;


/**
 * SQL statement specification for deleting a TestParent
 * 
 * @author Robert J. Muller
 */
public class AbstractDeleteTestParent extends AbstractDeleteSql<com.poesys.db.test.ITestParent> {
  private static final String SQL =
    "DELETE FROM TestParent WHERE ";

  public String getSql(IPrimaryKey key) {
    return super.getSql(key, SQL);
  }
}
