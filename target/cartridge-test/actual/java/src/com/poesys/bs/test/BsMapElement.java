/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: BsDto.vsl

package com.poesys.bs.test;


import com.poesys.db.pk.IPrimaryKey;
import com.poesys.bs.delegate.DelegateException;


/**
 * <p>
 * A business layer data-transfer object for the MapElement. This class
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
public class BsMapElement extends com.poesys.bs.test.AbstractBsMapElement {
  /**
   * Create a BsMapElement object from a MapElement object.
   * 
   * @param dto the data-access layer MapElement DTO
   * @throws DelegateException when there is a problem creating the MapElement
   */
  public BsMapElement(com.poesys.db.test.IMapElement dto) throws DelegateException {
    super(dto);
  }

  /**
   * <p>
   * Create a BsMapElement. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the MapElement
   * @param mapsObject ${key.paramTag}
   * @param polysObject ${key.paramTag}
   * @param tairObjectId primary key attribute
   * @param mapId Attribute that is part of the key of an associated object (not part of primary key of association)
   * @param polyId Attribute that is part of the key of an associated object (not part of primary key of association)
   * @param version 
   * @param elementType 
   */
  public BsMapElement(IPrimaryKey key, com.poesys.db.test.IMap mapsObject, com.poesys.db.test.IPoly polysObject, java.math.BigInteger tairObjectId, java.math.BigInteger mapId, java.math.BigInteger polyId, java.lang.Integer version, java.lang.String elementType) {
    super(key, mapsObject, polysObject, tairObjectId, mapId, polyId, version, elementType); 
  }
}