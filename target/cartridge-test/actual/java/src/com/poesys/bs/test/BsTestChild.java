/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: BsDto.vsl

package com.poesys.bs.test;


import com.poesys.db.pk.IPrimaryKey;
import com.poesys.bs.delegate.DelegateException;


/**
 * <p>
 * A business layer data-transfer object for the TestChild. This class
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
public class BsTestChild extends com.poesys.bs.test.AbstractBsTestChild {
  /**
   * Create a BsTestChild object from a TestChild object.
   * 
   * @param dto the data-access layer TestChild DTO
   * @throws DelegateException when there is a problem creating the TestChild
   */
  public BsTestChild(com.poesys.db.test.ITestChild dto) throws DelegateException {
    super(dto);
  }

  /**
   * <p>
   * Create a BsTestChild. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the TestChild
   * @param testParentId composite super-key attribute that uniquely identifies child combined with child sub-key and any other parent super-keys
   * @param childNo composite subkey attribute that uniquely identifies child combined with parent key
   */
  public BsTestChild(IPrimaryKey key, java.math.BigInteger testParentId, java.math.BigInteger childNo) {
    super(key, testParentId, childNo); 
  }
}