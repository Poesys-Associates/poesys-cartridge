/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: BsDto.vsl

package com.poesys.bs.memcached_test;


import com.poesys.db.pk.IPrimaryKey;
import com.poesys.bs.delegate.DelegateException;


/**
 * <p>
 * A business layer data-transfer object for the Self2. This class
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
 *     <li>Immutable</li>
 *     <li>Persistent</li>
 *     <li>SequenceKey</li>
 * </ul>
 * 
 * @author Poesys/DB Cartridge
 */
public class BsSelf2 extends com.poesys.bs.memcached_test.AbstractBsSelf2 {
  /**
   * Create a BsSelf2 object from a Self2 object.
   * 
   * @param dto the data-access layer Self2 DTO
   * @throws DelegateException when there is a problem creating the Self2
   */
  public BsSelf2(com.poesys.db.memcached_test.ISelf2 dto) throws DelegateException {
    super(dto);
  }

  /**
   * <p>
   * Create a BsSelf2. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the Self2
   * @param self2Id primary key attribute
   */
  public BsSelf2(IPrimaryKey key, java.math.BigInteger self2Id) {
    super(key, self2Id); 
  }
}