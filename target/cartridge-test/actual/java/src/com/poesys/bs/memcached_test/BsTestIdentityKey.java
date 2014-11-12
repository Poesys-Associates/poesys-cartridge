/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: BsDto.vsl

package com.poesys.bs.memcached_test;


import com.poesys.db.pk.IPrimaryKey;
import com.poesys.bs.delegate.DelegateException;


/**
 * <p>
 * A business layer data-transfer object for the TestIdentityKey. This class
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
 *     <li>IdentityKey</li>
 *     <li>Persistent</li>
 * </ul>
 * 
 * @author Poesys/DB Cartridge
 */
public class BsTestIdentityKey extends com.poesys.bs.memcached_test.AbstractBsTestIdentityKey {
  /**
   * Create a BsTestIdentityKey object from a TestIdentityKey object.
   * 
   * @param dto the data-access layer TestIdentityKey DTO
   * @throws DelegateException when there is a problem creating the TestIdentityKey
   */
  public BsTestIdentityKey(com.poesys.db.memcached_test.ITestIdentityKey dto) throws DelegateException {
    super(dto);
  }

  /**
   * <p>
   * Create a BsTestIdentityKey. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the TestIdentityKey
   * @param testIdentityKeyId primary key attribute
   * @param data1 The first data element in the class
   */
  public BsTestIdentityKey(IPrimaryKey key, java.math.BigInteger testIdentityKeyId, java.lang.String data1) {
    super(key, testIdentityKeyId, data1); 
  }
}