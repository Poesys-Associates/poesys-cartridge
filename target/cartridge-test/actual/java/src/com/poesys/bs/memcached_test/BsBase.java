/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: BsDto.vsl

package com.poesys.bs.memcached_test;


import com.poesys.bs.delegate.DelegateException;


/**
 * <p>
 * A business layer data-transfer object for the Base. This class
 * is the concrete subclass of the generated abstract class. Make any changes
 * to DTO behavior by overriding methods here rather than changing the abstract
 * superclass; AndroMDA will overwrite that class when you run it but will
 * never overwrite this concrete subclass.
 * </p>
 * 
 * <p>
 * This class is the business proxy for the abstract class Base. When
 * you instantiate this class, you are proxying a concrete subclass of the
 * abstract class Base that conforms to the IBase interface.
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
public class BsBase extends com.poesys.bs.memcached_test.AbstractBsBase {
  /**
   * Create a BsBase object from a Base object.
   * 
   * @param dto the data-access layer Base DTO
   * @throws DelegateException when there is a problem creating the Base
   */
  public BsBase(com.poesys.db.memcached_test.IBase dto) throws DelegateException {
    super(dto);
  }

}