/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: BsDto.vsl

package com.poesys.bs.memcached_test;


import com.poesys.db.pk.IPrimaryKey;
import com.poesys.bs.delegate.DelegateException;


/**
 * <p>
 * A business layer data-transfer object for the TestNaturalChild. This class
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
 *     <li>CompositeKey</li>
 *     <li>Immutable</li>
 *     <li>Persistent</li>
 * </ul>
 * 
 * @author Poesys/DB Cartridge
 */
public class BsTestNaturalChild extends com.poesys.bs.memcached_test.AbstractBsTestNaturalChild {
  /**
   * Create a BsTestNaturalChild object from a TestNaturalChild object.
   * 
   * @param dto the data-access layer TestNaturalChild DTO
   * @throws DelegateException when there is a problem creating the TestNaturalChild
   */
  public BsTestNaturalChild(com.poesys.db.memcached_test.ITestNaturalChild dto) throws DelegateException {
    super(dto);
  }

  /**
   * <p>
   * Create a BsTestNaturalChild. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the TestNaturalChild
   * @param key1 composite super-key attribute that uniquely identifies child combined with child sub-key and any other parent super-keys
   * @param key2 composite super-key attribute that uniquely identifies child combined with child sub-key and any other parent super-keys
   * @param subkey 
   */
  public BsTestNaturalChild(IPrimaryKey key, java.lang.Long key1, java.lang.Long key2, java.lang.Long subkey) {
    super(key, key1, key2, subkey); 
  }
}