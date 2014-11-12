/**
 * Copyright (c) 2010 Carnegie Institution for Science. All rights reserved.
 */
package com.poesys.cartridges.db.utilities;

import java.util.Comparator;

import com.poesys.cartridges.db.psm.db.SqlColumnName;

/**
 * 
 * @author Robert J. Muller
 */
public class SqlColumnNameComparator implements Comparator<SqlColumnName> {

  public int compare(SqlColumnName key1, SqlColumnName key2) {
    // Sort the keys in order by name
    return key1.getName().compareTo(key2.getName());
  }
}
