/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: BsDto.vsl

package com.poesys.bs.memcached_test;


import com.poesys.db.pk.IPrimaryKey;
import com.poesys.bs.delegate.DelegateException;


/**
 * <p>
 * A business layer data-transfer object for the TestSequenceKey. This class
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
 *     <li>SequenceKey</li>
 * </ul>
 * 
 * @author Poesys/DB Cartridge
 */
public class BsTestSequenceKey extends com.poesys.bs.memcached_test.AbstractBsTestSequenceKey {
  /**
   * Create a BsTestSequenceKey object from a TestSequenceKey object.
   * 
   * @param dto the data-access layer TestSequenceKey DTO
   * @throws DelegateException when there is a problem creating the TestSequenceKey
   */
  public BsTestSequenceKey(com.poesys.db.memcached_test.ITestSequenceKey dto) throws DelegateException {
    super(dto);
  }

  /**
   * <p>
   * Create a BsTestSequenceKey. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the TestSequenceKey
   * @param testSequenceKeyId primary key attribute
   * @param data1 The first data element in the class
   */
  public BsTestSequenceKey(IPrimaryKey key, java.math.BigInteger testSequenceKeyId, java.lang.String data1) {
    super(key, testSequenceKeyId, data1); 
  }
}