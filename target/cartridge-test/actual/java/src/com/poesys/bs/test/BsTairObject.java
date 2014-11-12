/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: BsDto.vsl

package com.poesys.bs.test;


import com.poesys.bs.delegate.DelegateException;


/**
 * <p>
 * A business layer data-transfer object for the TairObject. This class
 * is the concrete subclass of the generated abstract class. Make any changes
 * to DTO behavior by overriding methods here rather than changing the abstract
 * superclass; AndroMDA will overwrite that class when you run it but will
 * never overwrite this concrete subclass.
 * </p>
 * 
 * <p>
 * This class is the business proxy for the abstract class TairObject. When
 * you instantiate this class, you are proxying a concrete subclass of the
 * abstract class TairObject that conforms to the ITairObject interface.
 * </p>
 * <p>
 * Stereotypes:
 * </p>
 * <ul>
 *     <li>IdentityKey</li>
 *     <li>Persistent</li>
 * </ul>
 * 
 * @author Poesys/DB Cartridge
 */
public class BsTairObject extends com.poesys.bs.test.AbstractBsTairObject {
  /**
   * Create a BsTairObject object from a TairObject object.
   * 
   * @param dto the data-access layer TairObject DTO
   * @throws DelegateException when there is a problem creating the TairObject
   */
  public BsTairObject(com.poesys.db.test.ITairObject dto) throws DelegateException {
    super(dto);
  }

}