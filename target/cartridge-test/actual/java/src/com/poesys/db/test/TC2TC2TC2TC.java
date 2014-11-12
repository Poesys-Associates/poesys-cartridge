/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: DbDto.vsl

package com.poesys.db.test;


import com.poesys.db.pk.IPrimaryKey;


/**
 * <p>
 * A data-access layer data-transfer object for the TC2TC2TC2TC. This class
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
public class TC2TC2TC2TC extends AbstractTC2TC2TC2TC {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;

  /**
   * <p>
   * Create a TC2TC2TC2TC as a new object. This constructor calls the abstract 
   * superclass constructor.
   * </p>
   *
   */
  public TC2TC2TC2TC() {
    super(); 
  }

  /**
   * <p>
   * Create a TC2TC2TC2TC. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the TC2TC2TC2TC
   * @param tc1Object wraps an associated ITestChild2TestChild object
   * @param tc2Object wraps an associated ITestChild2TestChild object
   * @param tc2UpChildNo Attribute that is part of the association key
   * @param tc2DownChildNo Attribute that is part of the association key
   * @param tc1UpChildNo Attribute that is part of the association key
   * @param tc1DownChildNo Attribute that is part of the association key
   * @param tc2UpTestParentId Attribute that is part of the association key
   * @param tc2DownTestParentId Attribute that is part of the association key
   * @param tc1UpTestParentId Attribute that is part of the association key
   * @param tc1DownTestParentId Attribute that is part of the association key
   */
  public TC2TC2TC2TC(IPrimaryKey key, com.poesys.db.test.ITestChild2TestChild tc1Object, com.poesys.db.test.ITestChild2TestChild tc2Object, java.math.BigInteger tc2UpChildNo, java.math.BigInteger tc2DownChildNo, java.math.BigInteger tc1UpChildNo, java.math.BigInteger tc1DownChildNo, java.math.BigInteger tc2UpTestParentId, java.math.BigInteger tc2DownTestParentId, java.math.BigInteger tc1UpTestParentId, java.math.BigInteger tc1DownTestParentId) {
    super(key, tc1Object, tc2Object, tc2UpChildNo, tc2DownChildNo, tc1UpChildNo, tc1DownChildNo, tc2UpTestParentId, tc2DownTestParentId, tc1UpTestParentId, tc1DownTestParentId); 
  }
}