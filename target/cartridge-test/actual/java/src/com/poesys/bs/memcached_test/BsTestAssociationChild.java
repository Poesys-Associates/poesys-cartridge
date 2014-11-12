/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: BsDto.vsl

package com.poesys.bs.memcached_test;


import com.poesys.db.pk.IPrimaryKey;
import com.poesys.bs.delegate.DelegateException;


/**
 * <p>
 * A business layer data-transfer object for the TestAssociationChild. This class
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
 *     <li>Persistent</li>
 * </ul>
 * 
 * @author Poesys/DB Cartridge
 */
public class BsTestAssociationChild extends com.poesys.bs.memcached_test.AbstractBsTestAssociationChild {
  /**
   * Create a BsTestAssociationChild object from a TestAssociationChild object.
   * 
   * @param dto the data-access layer TestAssociationChild DTO
   * @throws DelegateException when there is a problem creating the TestAssociationChild
   */
  public BsTestAssociationChild(com.poesys.db.memcached_test.ITestAssociationChild dto) throws DelegateException {
    super(dto);
  }

  /**
   * <p>
   * Create a BsTestAssociationChild. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the TestAssociationChild
   * @param key1 composite super-key attribute that uniquely identifies child combined with child sub-key and any other parent super-keys
   * @param key2 composite super-key attribute that uniquely identifies child combined with child sub-key and any other parent super-keys
   * @param testXId Attribute that is part of the association key
   * @param testYId Attribute that is part of the association key
   * @param tsACAttr Data attribute for the Test Association Child composite aggregation of the Test
Natural Parent, identified by the zKey TestZ object
   */
  public BsTestAssociationChild(IPrimaryKey key, java.lang.Long key1, java.lang.Long key2, java.math.BigInteger testXId, java.math.BigInteger testYId, java.lang.Long tsACAttr) {
    super(key, key1, key2, testXId, testYId, tsACAttr); 
  }
}