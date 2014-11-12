/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: DbDto.vsl

package com.poesys.db.test;


import com.poesys.db.pk.IPrimaryKey;


/**
 * <p>
 * A data-access layer data-transfer object for the Map. This class
 * is the concrete subclass of the generated abstract class. Make any changes
 * to DTO behavior by overriding methods here rather than changing the abstract
 * superclass; AndroMDA will overwrite that class when you run it but will
 * never overwrite this concrete subclass.
 * </p>
 * <p>
 * a map of objects to a genome
 * </p>
 * <p>
 * Stereotypes:
 * </p>
 * <ul>
 *     <li>IdentityKey</li>
 *     <li>Persistent</li>
 * </ul>
 * 
 * @author Poesys/DB Cartridge
 */
public class Map extends AbstractMap {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;

  /**
   * <p>
   * Create a Map as a new object. This constructor calls the abstract 
   * superclass constructor.
   * </p>
   *
   */
  public Map() {
    super(); 
  }

  /**
   * <p>
   * Create a Map. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the Map
   * @param mapId primary key attribute
   * @param location 
   */
  public Map(IPrimaryKey key, java.math.BigInteger mapId, java.lang.Long location) {
    super(key, mapId, location); 
  }
}