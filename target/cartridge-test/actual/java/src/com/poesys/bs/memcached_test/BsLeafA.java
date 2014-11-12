/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: BsDto.vsl

package com.poesys.bs.memcached_test;


import com.poesys.db.pk.IPrimaryKey;
import com.poesys.bs.delegate.DelegateException;


/**
 * <p>
 * A business layer data-transfer object for the LeafA. This class
 * is the concrete subclass of the generated abstract class. Make any changes
 * to DTO behavior by overriding methods here rather than changing the abstract
 * superclass; AndroMDA will overwrite that class when you run it but will
 * never overwrite this concrete subclass.
 * </p>
 * 
 * <p>
 * Stereotypes:
 * </p>
 * <ul>
 *     <li>Persistent</li>
 * </ul>
 * 
 * @author Poesys/DB Cartridge
 */
public class BsLeafA extends com.poesys.bs.memcached_test.AbstractBsLeafA {
  /**
   * Create a BsLeafA object from a LeafA object.
   * 
   * @param dto the data-access layer LeafA DTO
   * @throws DelegateException when there is a problem creating the LeafA
   */
  public BsLeafA(com.poesys.db.memcached_test.ILeafA dto) throws DelegateException {
    super(dto);
  }

  /**
   * <p>
   * Create a BsLeafA. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the LeafA
   * @param baseId primary key attribute
   * @param baseString 
   * @param middleString 
   * @param aString 
   */
  public BsLeafA(IPrimaryKey key, java.math.BigInteger baseId, java.lang.String baseString, java.lang.String middleString, java.lang.String aString) {
    super(key, baseId, baseString, middleString, aString); 
  }
}