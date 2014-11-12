/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: DbDto.vsl

package com.poesys.db.test;


import com.poesys.db.pk.IPrimaryKey;


/**
 * <p>
 * A data-access layer data-transfer object for the MapElement. This class
 * is the concrete subclass of the generated abstract class. Make any changes
 * to DTO behavior by overriding methods here rather than changing the abstract
 * superclass; AndroMDA will overwrite that class when you run it but will
 * never overwrite this concrete subclass.
 * </p>
 * <p>
 * A polymorphism mapped to a genome map
 * </p>
 * <p>
 * Stereotypes:
 * </p>
 * <ul>
 *     <li>Persistent</li>
 * </ul>
 * 
 * @author Poesys/DB Cartridge
 */
public class MapElement extends AbstractMapElement {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;

  /**
   * <p>
   * Create a MapElement as a new object. This constructor calls the abstract 
   * superclass constructor.
   * </p>
   *
   */
  public MapElement() {
    super(); 
  }

  /**
   * <p>
   * Create a MapElement. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the MapElement
   * @param mapsObject wraps an associated IMap object
   * @param polysObject wraps an associated IPoly object
   * @param tairObjectId primary key attribute
   * @param mapId Attribute that is part of the key of an associated object (not part of primary key of association)
   * @param polyId Attribute that is part of the key of an associated object (not part of primary key of association)
   * @param version 
   * @param elementType 
   */
  public MapElement(IPrimaryKey key, com.poesys.db.test.IMap mapsObject, com.poesys.db.test.IPoly polysObject, java.math.BigInteger tairObjectId, java.math.BigInteger mapId, java.math.BigInteger polyId, java.lang.Integer version, java.lang.String elementType) {
    super(key, mapsObject, polysObject, tairObjectId, mapId, polyId, version, elementType); 
  }
}