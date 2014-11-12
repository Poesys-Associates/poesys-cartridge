/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: DbDto.vsl

package com.poesys.db.memcached_test;


import com.poesys.db.pk.IPrimaryKey;


/**
 * <p>
 * A data-access layer data-transfer object for the TestIdentityKey. This class
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
 *     <li>IdentityKey</li>
 *     <li>Persistent</li>
 * </ul>
 * 
 * @author Poesys/DB Cartridge
 */
public class TestIdentityKey extends AbstractTestIdentityKey {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;

  /**
   * <p>
   * Create a TestIdentityKey as a new object. This constructor calls the abstract 
   * superclass constructor.
   * </p>
   *
   */
  public TestIdentityKey() {
    super(); 
  }

  /**
   * <p>
   * Create a TestIdentityKey. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the TestIdentityKey
   * @param testIdentityKeyId primary key attribute
   * @param data1 The first data element in the class
   */
  public TestIdentityKey(IPrimaryKey key, java.math.BigInteger testIdentityKeyId, java.lang.String data1) {
    super(key, testIdentityKeyId, data1); 
  }
}