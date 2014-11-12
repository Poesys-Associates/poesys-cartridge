/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: BsDto.vsl

package com.poesys.bs.test;


import com.poesys.db.pk.IPrimaryKey;
import com.poesys.bs.delegate.DelegateException;


/**
 * <p>
 * A business layer data-transfer object for the TestB. This class
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
public class BsTestB extends com.poesys.bs.test.AbstractBsTestB {
  /**
   * Create a BsTestB object from a TestB object.
   * 
   * @param dto the data-access layer TestB DTO
   * @throws DelegateException when there is a problem creating the TestB
   */
  public BsTestB(com.poesys.db.test.ITestB dto) throws DelegateException {
    super(dto);
  }

  /**
   * <p>
   * Create a BsTestB. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the TestB
   * @param testBId primary key attribute
   * @param testAId foreign key used by setter to query associated object
   */
  public BsTestB(IPrimaryKey key, java.math.BigInteger testBId, java.math.BigInteger testAId) {
    super(key, testBId, testAId); 
  }
}