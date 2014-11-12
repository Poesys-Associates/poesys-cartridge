/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: BsDto.vsl

package com.poesys.bs.memcached_test;


import com.poesys.db.pk.IPrimaryKey;
import com.poesys.bs.delegate.DelegateException;


/**
 * <p>
 * A business layer data-transfer object for the TestChild2TestChild. This class
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
 *     <li>AssociationKey</li>
 *     <li>Immutable</li>
 *     <li>Persistent</li>
 * </ul>
 * 
 * @author Poesys/DB Cartridge
 */
public class BsTestChild2TestChild extends com.poesys.bs.memcached_test.AbstractBsTestChild2TestChild {
  /**
   * Create a BsTestChild2TestChild object from a TestChild2TestChild object.
   * 
   * @param dto the data-access layer TestChild2TestChild DTO
   * @throws DelegateException when there is a problem creating the TestChild2TestChild
   */
  public BsTestChild2TestChild(com.poesys.db.memcached_test.ITestChild2TestChild dto) throws DelegateException {
    super(dto);
  }

  /**
   * <p>
   * Create a BsTestChild2TestChild. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the TestChild2TestChild
   * @param downObject ${key.paramTag}
   * @param upObject ${key.paramTag}
   * @param upChildNo Attribute that is part of the association key
   * @param downChildNo Attribute that is part of the association key
   * @param upTestParentId Attribute that is part of the association key
   * @param downTestParentId Attribute that is part of the association key
   */
  public BsTestChild2TestChild(IPrimaryKey key, com.poesys.db.memcached_test.ITestChild downObject, com.poesys.db.memcached_test.ITestChild upObject, java.math.BigInteger upChildNo, java.math.BigInteger downChildNo, java.math.BigInteger upTestParentId, java.math.BigInteger downTestParentId) {
    super(key, downObject, upObject, upChildNo, downChildNo, upTestParentId, downTestParentId); 
  }
}