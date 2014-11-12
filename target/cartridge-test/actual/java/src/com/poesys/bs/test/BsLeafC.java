/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: BsDto.vsl

package com.poesys.bs.test;


import com.poesys.db.pk.IPrimaryKey;
import com.poesys.bs.delegate.DelegateException;


/**
 * <p>
 * A business layer data-transfer object for the LeafC. This class
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
 *     <li>Persistent</li>
 * </ul>
 * 
 * @author Poesys/DB Cartridge
 */
public class BsLeafC extends com.poesys.bs.test.AbstractBsLeafC {
  /**
   * Create a BsLeafC object from a LeafC object.
   * 
   * @param dto the data-access layer LeafC DTO
   * @throws DelegateException when there is a problem creating the LeafC
   */
  public BsLeafC(com.poesys.db.test.ILeafC dto) throws DelegateException {
    super(dto);
  }

  /**
   * <p>
   * Create a BsLeafC. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the LeafC
   * @param baseId primary key attribute
   * @param baseString 
   * @param middleString 
   * @param cString 
   */
  public BsLeafC(IPrimaryKey key, java.math.BigInteger baseId, java.lang.String baseString, java.lang.String middleString, java.lang.String cString) {
    super(key, baseId, baseString, middleString, cString); 
  }
}