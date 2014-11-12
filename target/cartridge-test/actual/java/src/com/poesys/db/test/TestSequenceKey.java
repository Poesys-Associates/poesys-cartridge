/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: DbDto.vsl

package com.poesys.db.test;


import com.poesys.db.pk.IPrimaryKey;


/**
 * <p>
 * A data-access layer data-transfer object for the TestSequenceKey. This class
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
 *     <li>SequenceKey</li>
 * </ul>
 * 
 * @author Poesys/DB Cartridge
 */
public class TestSequenceKey extends AbstractTestSequenceKey {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;

  /**
   * <p>
   * Create a TestSequenceKey as a new object. This constructor calls the abstract 
   * superclass constructor.
   * </p>
   *
   */
  public TestSequenceKey() {
    super(); 
  }

  /**
   * <p>
   * Create a TestSequenceKey. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the TestSequenceKey
   * @param testSequenceKeyId primary key attribute
   * @param data1 The first data element in the class
   */
  public TestSequenceKey(IPrimaryKey key, java.math.BigInteger testSequenceKeyId, java.lang.String data1) {
    super(key, testSequenceKeyId, data1); 
  }
}