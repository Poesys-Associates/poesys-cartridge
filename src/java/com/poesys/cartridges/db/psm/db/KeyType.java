/*
 * Copyright (c) 2008 Carnegie Institution for Science. All rights reserved.
 */
package com.poesys.cartridges.db.psm.db;


import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;


/**
 * Enumerated type describing the possible types of primary key for a persistent
 * class.
 * 
 * @author Robert J. Muller
 */
public enum KeyType {
  /** Key for an association class comprising two or more nested keys */
  ASSOCIATION("AssociationKey", "com.poesys.db.pk.AssociationPrimaryKey"),
  /**
   * Key for a composite class comprising two nested keys, the parent key and
   * the child key
   */
  COMPOSITE("CompositeKey", "com.poesys.db.pk.CompositePrimaryKey"),
  /** Key generated internally for the class (auto-generated key, identity key) */
  IDENTITY("IdentityKey", "com.poesys.db.pk.IdentityPrimaryKey"),
  /** Key comprising one or more data attributes of the class */
  NATURAL("NaturalKey", "com.poesys.db.pk.NaturalPrimaryKey"),
  /** Key generated externally through a sequence for the class */
  SEQUENCE("SequenceKey", "com.poesys.db.pk.SequencePrimaryKey"),
  /** Key generated externally through a GUID generator */
  GUID("GuidKey", "com.poesys.db.pk.GuidPrimaryKey"),
  /** No key */
  NONE("None", null);

  /** The internal string representation */
  private final String string;
  /** The internal class name */
  private final String keyClass;

  /** Map of internal string representations for lookup */
  private static final Map<String, KeyType> values =
    new HashMap<String, KeyType>();

  // Initialize the map statically
  static {
    for (KeyType k : EnumSet.allOf(KeyType.class)) {
      values.put(k.toString(), k);
    }
  }

  /**
   * Create a KeyType object.
   * 
   * @param string the string with which to construct the internal
   *            representation
   * @param keyClass the fully qualified class name of the key class
   */
  private KeyType(String string, String keyClass) {
    this.string = string;
    this.keyClass = keyClass;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Enum#toString()
   */
  public String toString() {
    return string;
  }

  /**
   * Look up the KeyType based on its string representation.
   * 
   * @param value the string to look up
   * @return the key type corresponding to the string or null if there is no
   *         such key type
   */
  public static KeyType stringValue(String value) {
    return values.get(value);
  }
  
  /**
   * Get the fully qualified class name for the primary key class.
   * 
   * @return a class name
   */
  public String getKeyClass() {
    return keyClass;
  }
}
