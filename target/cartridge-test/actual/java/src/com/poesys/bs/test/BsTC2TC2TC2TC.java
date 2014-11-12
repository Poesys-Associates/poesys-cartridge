/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: BsDto.vsl

package com.poesys.bs.test;


import com.poesys.db.pk.IPrimaryKey;
import com.poesys.bs.delegate.DelegateException;


/**
 * <p>
 * A business layer data-transfer object for the TC2TC2TC2TC. This class
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
public class BsTC2TC2TC2TC extends com.poesys.bs.test.AbstractBsTC2TC2TC2TC {
  /**
   * Create a BsTC2TC2TC2TC object from a TC2TC2TC2TC object.
   * 
   * @param dto the data-access layer TC2TC2TC2TC DTO
   * @throws DelegateException when there is a problem creating the TC2TC2TC2TC
   */
  public BsTC2TC2TC2TC(com.poesys.db.test.ITC2TC2TC2TC dto) throws DelegateException {
    super(dto);
  }

  /**
   * <p>
   * Create a BsTC2TC2TC2TC. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the TC2TC2TC2TC
   * @param tc1Object ${key.paramTag}
   * @param tc2Object ${key.paramTag}
   * @param tc2UpChildNo Attribute that is part of the association key
   * @param tc2DownChildNo Attribute that is part of the association key
   * @param tc1UpChildNo Attribute that is part of the association key
   * @param tc1DownChildNo Attribute that is part of the association key
   * @param tc2UpTestParentId Attribute that is part of the association key
   * @param tc2DownTestParentId Attribute that is part of the association key
   * @param tc1UpTestParentId Attribute that is part of the association key
   * @param tc1DownTestParentId Attribute that is part of the association key
   */
  public BsTC2TC2TC2TC(IPrimaryKey key, com.poesys.db.test.ITestChild2TestChild tc1Object, com.poesys.db.test.ITestChild2TestChild tc2Object, java.math.BigInteger tc2UpChildNo, java.math.BigInteger tc2DownChildNo, java.math.BigInteger tc1UpChildNo, java.math.BigInteger tc1DownChildNo, java.math.BigInteger tc2UpTestParentId, java.math.BigInteger tc2DownTestParentId, java.math.BigInteger tc1UpTestParentId, java.math.BigInteger tc1DownTestParentId) {
    super(key, tc1Object, tc2Object, tc2UpChildNo, tc2DownChildNo, tc1UpChildNo, tc1DownChildNo, tc2UpTestParentId, tc2DownTestParentId, tc1UpTestParentId, tc1DownTestParentId); 
  }
}