/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: DbDto.vsl

package com.poesys.db.test;


import com.poesys.db.pk.IPrimaryKey;


/**
 * <p>
 * A data-access layer data-transfer object for the TestParent. This class
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
public class TestParent extends AbstractTestParent {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;

  /**
   * <p>
   * Create a TestParent as a new object. This constructor calls the abstract 
   * superclass constructor.
   * </p>
   *
   */
  public TestParent() {
    super(); 
  }

  /**
   * <p>
   * Create a TestParent. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the TestParent
   * @param testParentId primary key attribute
   * @param parentOccupation 
   */
  public TestParent(IPrimaryKey key, java.math.BigInteger testParentId, java.lang.String parentOccupation) {
    super(key, testParentId, parentOccupation); 
  }
}