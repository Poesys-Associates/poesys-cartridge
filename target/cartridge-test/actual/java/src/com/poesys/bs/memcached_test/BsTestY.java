/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: BsDto.vsl

package com.poesys.bs.memcached_test;


import com.poesys.db.pk.IPrimaryKey;
import com.poesys.bs.delegate.DelegateException;


/**
 * <p>
 * A business layer data-transfer object for the TestY. This class
 * is the concrete subclass of the generated abstract class. Make any changes
 * to DTO behavior by overriding methods here rather than changing the abstract
 * superclass; AndroMDA will overwrite that class when you run it but will
 * never overwrite this concrete subclass.
 * </p>
 * <p>
 * The TestY object is the "other" side of a many-to-many association.
 * </p>
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
public class BsTestY extends com.poesys.bs.memcached_test.AbstractBsTestY {
  /**
   * Create a BsTestY object from a TestY object.
   * 
   * @param dto the data-access layer TestY DTO
   * @throws DelegateException when there is a problem creating the TestY
   */
  public BsTestY(com.poesys.db.memcached_test.ITestY dto) throws DelegateException {
    super(dto);
  }

  /**
   * <p>
   * Create a BsTestY. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the TestY
   * @param testYId primary key attribute
   * @param yAttr 
   */
  public BsTestY(IPrimaryKey key, java.math.BigInteger testYId, java.lang.String yAttr) {
    super(key, testYId, yAttr); 
  }
}