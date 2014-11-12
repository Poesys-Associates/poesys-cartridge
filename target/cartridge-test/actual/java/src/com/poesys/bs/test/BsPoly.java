/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: BsDto.vsl

package com.poesys.bs.test;


import com.poesys.db.pk.IPrimaryKey;
import com.poesys.bs.delegate.DelegateException;


/**
 * <p>
 * A business layer data-transfer object for the Poly. This class
 * is the concrete subclass of the generated abstract class. Make any changes
 * to DTO behavior by overriding methods here rather than changing the abstract
 * superclass; AndroMDA will overwrite that class when you run it but will
 * never overwrite this concrete subclass.
 * </p>
 * <p>
 * a difference from a specific sequence in the reference species variant
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
public class BsPoly extends com.poesys.bs.test.AbstractBsPoly {
  /**
   * Create a BsPoly object from a Poly object.
   * 
   * @param dto the data-access layer Poly DTO
   * @throws DelegateException when there is a problem creating the Poly
   */
  public BsPoly(com.poesys.db.test.IPoly dto) throws DelegateException {
    super(dto);
  }

  /**
   * <p>
   * Create a BsPoly. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the Poly
   * @param polyId primary key attribute
   * @param sequence 
   */
  public BsPoly(IPrimaryKey key, java.math.BigInteger polyId, java.lang.String sequence) {
    super(key, polyId, sequence); 
  }
}