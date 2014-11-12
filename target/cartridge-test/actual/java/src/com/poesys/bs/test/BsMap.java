/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: BsDto.vsl

package com.poesys.bs.test;


import com.poesys.db.pk.IPrimaryKey;
import com.poesys.bs.delegate.DelegateException;


/**
 * <p>
 * A business layer data-transfer object for the Map. This class
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
public class BsMap extends com.poesys.bs.test.AbstractBsMap {
  /**
   * Create a BsMap object from a Map object.
   * 
   * @param dto the data-access layer Map DTO
   * @throws DelegateException when there is a problem creating the Map
   */
  public BsMap(com.poesys.db.test.IMap dto) throws DelegateException {
    super(dto);
  }

  /**
   * <p>
   * Create a BsMap. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the Map
   * @param mapId primary key attribute
   * @param location 
   */
  public BsMap(IPrimaryKey key, java.math.BigInteger mapId, java.lang.Long location) {
    super(key, mapId, location); 
  }
}