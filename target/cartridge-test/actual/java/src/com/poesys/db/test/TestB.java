/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: DbDto.vsl

package com.poesys.db.test;


import com.poesys.db.pk.IPrimaryKey;


/**
 * <p>
 * A data-access layer data-transfer object for the TestB. This class
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
 *     <li>Immutable</li>
 *     <li>Persistent</li>
 *     <li>SequenceKey</li>
 * </ul>
 * 
 * @author Poesys/DB Cartridge
 */
public class TestB extends AbstractTestB {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;

  /**
   * <p>
   * Create a TestB as a new object. This constructor calls the abstract 
   * superclass constructor.
   * </p>
   *
   */
  public TestB() {
    super(); 
  }

  /**
   * <p>
   * Create a TestB. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the TestB
   * @param testBId primary key attribute
   * @param testAId foreign key used by setter to query associated object
   */
  public TestB(IPrimaryKey key, java.math.BigInteger testBId, java.math.BigInteger testAId) {
    super(key, testBId, testAId); 
  }
}