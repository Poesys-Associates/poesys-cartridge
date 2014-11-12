/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: BsDto.vsl

package com.poesys.bs.test;


import com.poesys.db.pk.IPrimaryKey;
import com.poesys.bs.delegate.DelegateException;


/**
 * <p>
 * A business layer data-transfer object for the TestNaturalKey. This class
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
 *     <li>NaturalKey</li>
 *     <li>Persistent</li>
 * </ul>
 * 
 * @author Poesys/DB Cartridge
 */
public class BsTestNaturalKey extends com.poesys.bs.test.AbstractBsTestNaturalKey {
  /**
   * Create a BsTestNaturalKey object from a TestNaturalKey object.
   * 
   * @param dto the data-access layer TestNaturalKey DTO
   * @throws DelegateException when there is a problem creating the TestNaturalKey
   */
  public BsTestNaturalKey(com.poesys.db.test.ITestNaturalKey dto) throws DelegateException {
    super(dto);
  }

  /**
   * <p>
   * Create a BsTestNaturalKey. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the TestNaturalKey
   * @param key1 key attribute for the Test1
   * @param number1 
   * @param number2 
   */
  public BsTestNaturalKey(IPrimaryKey key, java.lang.String key1, java.lang.Double number1, java.lang.Double number2) {
    super(key, key1, number1, number2); 
  }
}