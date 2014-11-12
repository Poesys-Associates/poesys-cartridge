/**
 * Copyright (c) 2010 Carnegie Institution for Science. All rights reserved.
 */
package com.poesys.cartridges.db.utilities;

import java.util.Comparator;

import com.poesys.cartridges.db.psm.db.AssociatedKey;

/**
 * 
 * @author Robert J. Muller
 */
public class AssociatedKeyComparator implements Comparator<AssociatedKey> {

  public int compare(AssociatedKey key1, AssociatedKey key2) {
    // Sort the keys in order by name
    return key1.getName().compareTo(key2.getName());
  }
}
