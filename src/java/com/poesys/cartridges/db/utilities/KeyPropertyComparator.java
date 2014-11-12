/**
 * Copyright (c) 2010 Carnegie Institution for Science. All rights reserved.
 */
package com.poesys.cartridges.db.utilities;


import java.util.Comparator;

import com.poesys.cartridges.db.psm.db.Property;


/**
 * Compare two keys by name (taking explicit name and prefix into account if the
 * two key names are the same).
 * 
 * @author Robert J. Muller
 */
public class KeyPropertyComparator implements Comparator<Property> {

  /**
   * Compare the keys, returning a compareTo value.
   */
  public int compare(Property key1, Property key2) {
    int returnCode = 0;
    // Sort the keys in order by name.

    // If the two keys have the same name, sort by explicit name or prefix.
    if (key1.getName().equalsIgnoreCase(key2.getName())) {
      String name1 = key1.getExplicitName();
      String name2 = key1.getExplicitName();
      if (name1 != null && name2 != null) {
        returnCode = name1.compareTo(name2);
      } else {
        name1 = key1.getPrefix();
        name2 = key2.getPrefix();
        if (name1 != null && name2 != null) {
          returnCode = name1.compareTo(name2);
        }
      }
    } else {
      // names not the same, just compare the names.
      returnCode = key1.getName().compareTo(key2.getName());
    }
    return returnCode;
  }
}
