/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: BsDto.vsl

package com.poesys.bs.test;


import com.poesys.db.pk.IPrimaryKey;
import com.poesys.bs.delegate.DelegateException;


/**
 * <p>
 * A business layer data-transfer object for the TestChildChild. This class
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
public class BsTestChildChild extends com.poesys.bs.test.AbstractBsTestChildChild {
  /**
   * Create a BsTestChildChild object from a TestChildChild object.
   * 
   * @param dto the data-access layer TestChildChild DTO
   * @throws DelegateException when there is a problem creating the TestChildChild
   */
  public BsTestChildChild(com.poesys.db.test.ITestChildChild dto) throws DelegateException {
    super(dto);
  }

  /**
   * <p>
   * Create a BsTestChildChild. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the TestChildChild
   * @param childNo composite super-key attribute that uniquely identifies child combined with child sub-key and any other parent super-keys
   * @param testParentId composite super-key attribute that uniquely identifies child combined with child sub-key and any other parent super-keys
   * @param testChildChildId composite subkey attribute that uniquely identifies child combined with parent key
   */
  public BsTestChildChild(IPrimaryKey key, java.math.BigInteger childNo, java.math.BigInteger testParentId, java.lang.String testChildChildId) {
    super(key, childNo, testParentId, testChildChildId); 
  }
}