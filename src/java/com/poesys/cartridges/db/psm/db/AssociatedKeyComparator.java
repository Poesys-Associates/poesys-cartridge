/**
 * Copyright (c) 2010 Carnegie Institution for Science. All rights reserved.
 */
package com.poesys.cartridges.db.psm.db;

import java.util.Comparator;

import com.poesys.cartridges.db.psm.db.AssociatedKey;

/**
 * A Comparator implementation for AssociatedKey objects that enables sorting
 * of the AssociatedKey objects in a collection; sorts by name, ignoring case.
 * The class is independent because the AssociatedKey class is generated and
 * cannot embed this class.
 * 
 * @author Robert J. Muller
 */
public class AssociatedKeyComparator implements Comparator<AssociatedKey> {

  public int compare(AssociatedKey key1, AssociatedKey key2) {
    // Sort the keys in order by name
    return key1.getName().compareToIgnoreCase(key2.getName());
  }
}
