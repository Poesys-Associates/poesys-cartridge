/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: BsDto.vsl

package com.poesys.bs.memcached_test;


import com.poesys.bs.delegate.DelegateException;


/**
 * <p>
 * A business layer data-transfer object for the Middle. This class
 * is the concrete subclass of the generated abstract class. Make any changes
 * to DTO behavior by overriding methods here rather than changing the abstract
 * superclass; AndroMDA will overwrite that class when you run it but will
 * never overwrite this concrete subclass.
 * </p>
 * 
 * <p>
 * This class is the business proxy for the abstract class Middle. When
 * you instantiate this class, you are proxying a concrete subclass of the
 * abstract class Middle that conforms to the IMiddle interface.
 * </p>
 * <p>
 * Stereotypes:
 * </p>
 * <ul>
 *     <li>Persistent</li>
 * </ul>
 * 
 * @author Poesys/DB Cartridge
 */
public class BsMiddle extends com.poesys.bs.memcached_test.AbstractBsMiddle {
  /**
   * Create a BsMiddle object from a Middle object.
   * 
   * @param dto the data-access layer Middle DTO
   * @throws DelegateException when there is a problem creating the Middle
   */
  public BsMiddle(com.poesys.db.memcached_test.IMiddle dto) throws DelegateException {
    super(dto);
  }

}