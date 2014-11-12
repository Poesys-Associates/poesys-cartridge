/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: BsDto.vsl

package com.poesys.bs.memcached_test;


import com.poesys.db.pk.IPrimaryKey;
import com.poesys.bs.delegate.DelegateException;


/**
 * <p>
 * A business layer data-transfer object for the LeafB. This class
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
public class BsLeafB extends com.poesys.bs.memcached_test.AbstractBsLeafB {
  /**
   * Create a BsLeafB object from a LeafB object.
   * 
   * @param dto the data-access layer LeafB DTO
   * @throws DelegateException when there is a problem creating the LeafB
   */
  public BsLeafB(com.poesys.db.memcached_test.ILeafB dto) throws DelegateException {
    super(dto);
  }

  /**
   * <p>
   * Create a BsLeafB. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the LeafB
   * @param baseId primary key attribute
   * @param baseString 
   * @param middleString 
   * @param bString 
   */
  public BsLeafB(IPrimaryKey key, java.math.BigInteger baseId, java.lang.String baseString, java.lang.String middleString, java.lang.String bString) {
    super(key, baseId, baseString, middleString, bString); 
  }
}