/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: BsDto.vsl

package com.poesys.bs.memcached_test;


import com.poesys.db.pk.IPrimaryKey;
import com.poesys.bs.delegate.DelegateException;


/**
 * <p>
 * A business layer data-transfer object for the TestA. This class
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
public class BsTestA extends com.poesys.bs.memcached_test.AbstractBsTestA {
  /**
   * Create a BsTestA object from a TestA object.
   * 
   * @param dto the data-access layer TestA DTO
   * @throws DelegateException when there is a problem creating the TestA
   */
  public BsTestA(com.poesys.db.memcached_test.ITestA dto) throws DelegateException {
    super(dto);
  }

  /**
   * <p>
   * Create a BsTestA. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the TestA
   * @param testAId primary key attribute
   */
  public BsTestA(IPrimaryKey key, java.math.BigInteger testAId) {
    super(key, testAId); 
  }
}