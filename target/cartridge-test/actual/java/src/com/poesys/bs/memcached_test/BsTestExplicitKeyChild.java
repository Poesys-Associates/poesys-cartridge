/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: BsDto.vsl

package com.poesys.bs.memcached_test;


import com.poesys.db.pk.IPrimaryKey;
import com.poesys.bs.delegate.DelegateException;


/**
 * <p>
 * A business layer data-transfer object for the TestExplicitKeyChild. This class
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
public class BsTestExplicitKeyChild extends com.poesys.bs.memcached_test.AbstractBsTestExplicitKeyChild {
  /**
   * Create a BsTestExplicitKeyChild object from a TestExplicitKeyChild object.
   * 
   * @param dto the data-access layer TestExplicitKeyChild DTO
   * @throws DelegateException when there is a problem creating the TestExplicitKeyChild
   */
  public BsTestExplicitKeyChild(com.poesys.db.memcached_test.ITestExplicitKeyChild dto) throws DelegateException {
    super(dto);
  }

  /**
   * <p>
   * Create a BsTestExplicitKeyChild. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the TestExplicitKeyChild
   * @param testParentId composite super-key attribute that uniquely identifies child combined with child sub-key and any other parent super-keys
   * @param explicitSubId sub-key within TestParent to identify the object
   */
  public BsTestExplicitKeyChild(IPrimaryKey key, java.math.BigInteger testParentId, java.lang.Long explicitSubId) {
    super(key, testParentId, explicitSubId); 
  }
}