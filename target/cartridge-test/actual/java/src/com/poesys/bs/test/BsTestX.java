/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: BsDto.vsl

package com.poesys.bs.test;


import com.poesys.db.pk.IPrimaryKey;
import com.poesys.bs.delegate.DelegateException;


/**
 * <p>
 * A business layer data-transfer object for the TestX. This class
 * is the concrete subclass of the generated abstract class. Make any changes
 * to DTO behavior by overriding methods here rather than changing the abstract
 * superclass; AndroMDA will overwrite that class when you run it but will
 * never overwrite this concrete subclass.
 * </p>
 * <p>
 * The TestX object is the "this" side of a many-to-many association.
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
public class BsTestX extends com.poesys.bs.test.AbstractBsTestX {
  /**
   * Create a BsTestX object from a TestX object.
   * 
   * @param dto the data-access layer TestX DTO
   * @throws DelegateException when there is a problem creating the TestX
   */
  public BsTestX(com.poesys.db.test.ITestX dto) throws DelegateException {
    super(dto);
  }

  /**
   * <p>
   * Create a BsTestX. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the TestX
   * @param testXId primary key attribute
   * @param xAttr 
   */
  public BsTestX(IPrimaryKey key, java.math.BigInteger testXId, java.lang.String xAttr) {
    super(key, testXId, xAttr); 
  }
}