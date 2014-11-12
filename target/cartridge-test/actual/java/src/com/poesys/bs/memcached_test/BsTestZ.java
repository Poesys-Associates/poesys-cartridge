/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: BsDto.vsl

package com.poesys.bs.memcached_test;


import com.poesys.db.pk.IPrimaryKey;
import com.poesys.bs.delegate.DelegateException;


/**
 * <p>
 * A business layer data-transfer object for the TestZ. This class
 * is the concrete subclass of the generated abstract class. Make any changes
 * to DTO behavior by overriding methods here rather than changing the abstract
 * superclass; AndroMDA will overwrite that class when you run it but will
 * never overwrite this concrete subclass.
 * </p>
 * <p>
 * The TestZ object is the assocation class that represents the many-to-many
 * association of TestX and TestY objects.
 * </p>
 * <p>
 * Stereotypes:
 * </p>
 * <ul>
 *     <li>AssociationKey</li>
 *     <li>Persistent</li>
 * </ul>
 * 
 * @author Poesys/DB Cartridge
 */
public class BsTestZ extends com.poesys.bs.memcached_test.AbstractBsTestZ {
  /**
   * Create a BsTestZ object from a TestZ object.
   * 
   * @param dto the data-access layer TestZ DTO
   * @throws DelegateException when there is a problem creating the TestZ
   */
  public BsTestZ(com.poesys.db.memcached_test.ITestZ dto) throws DelegateException {
    super(dto);
  }

  /**
   * <p>
   * Create a BsTestZ. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the TestZ
   * @param xsObject ${key.paramTag}
   * @param ysObject ${key.paramTag}
   * @param testXId Attribute that is part of the association key
   * @param testYId Attribute that is part of the association key
   * @param zAttr 
   */
  public BsTestZ(IPrimaryKey key, com.poesys.db.memcached_test.ITestX xsObject, com.poesys.db.memcached_test.ITestY ysObject, java.math.BigInteger testXId, java.math.BigInteger testYId, java.lang.String zAttr) {
    super(key, xsObject, ysObject, testXId, testYId, zAttr); 
  }
}