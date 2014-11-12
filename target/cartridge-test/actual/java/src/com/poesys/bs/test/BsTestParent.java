/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: BsDto.vsl

package com.poesys.bs.test;


import com.poesys.db.pk.IPrimaryKey;
import com.poesys.bs.delegate.DelegateException;


/**
 * <p>
 * A business layer data-transfer object for the TestParent. This class
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
public class BsTestParent extends com.poesys.bs.test.AbstractBsTestParent {
  /**
   * Create a BsTestParent object from a TestParent object.
   * 
   * @param dto the data-access layer TestParent DTO
   * @throws DelegateException when there is a problem creating the TestParent
   */
  public BsTestParent(com.poesys.db.test.ITestParent dto) throws DelegateException {
    super(dto);
  }

  /**
   * <p>
   * Create a BsTestParent. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the TestParent
   * @param testParentId primary key attribute
   * @param parentOccupation 
   */
  public BsTestParent(IPrimaryKey key, java.math.BigInteger testParentId, java.lang.String parentOccupation) {
    super(key, testParentId, parentOccupation); 
  }
}