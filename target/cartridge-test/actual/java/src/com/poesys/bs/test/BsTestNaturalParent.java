/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: BsDto.vsl

package com.poesys.bs.test;


import com.poesys.db.pk.IPrimaryKey;
import com.poesys.bs.delegate.DelegateException;


/**
 * <p>
 * A business layer data-transfer object for the TestNaturalParent. This class
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
public class BsTestNaturalParent extends com.poesys.bs.test.AbstractBsTestNaturalParent {
  /**
   * Create a BsTestNaturalParent object from a TestNaturalParent object.
   * 
   * @param dto the data-access layer TestNaturalParent DTO
   * @throws DelegateException when there is a problem creating the TestNaturalParent
   */
  public BsTestNaturalParent(com.poesys.db.test.ITestNaturalParent dto) throws DelegateException {
    super(dto);
  }

  /**
   * <p>
   * Create a BsTestNaturalParent. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the TestNaturalParent
   * @param key1 
   * @param key2 
   */
  public BsTestNaturalParent(IPrimaryKey key, java.lang.Long key1, java.lang.Long key2) {
    super(key, key1, key2); 
  }
}