/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: DbDto.vsl

package com.poesys.db.test;


import com.poesys.db.pk.IPrimaryKey;


/**
 * <p>
 * A data-access layer data-transfer object for the TestChildChild. This class
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
 *     <li>CompositeKey</li>
 *     <li>Immutable</li>
 *     <li>Persistent</li>
 * </ul>
 * 
 * @author Poesys/DB Cartridge
 */
public class TestChildChild extends AbstractTestChildChild {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;

  /**
   * <p>
   * Create a TestChildChild as a new object. This constructor calls the abstract 
   * superclass constructor.
   * </p>
   *
   */
  public TestChildChild() {
    super(); 
  }

  /**
   * <p>
   * Create a TestChildChild. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the TestChildChild
   * @param childNo composite super-key attribute that uniquely identifies child combined with child sub-key and any other parent super-keys
   * @param testParentId composite super-key attribute that uniquely identifies child combined with child sub-key and any other parent super-keys
   * @param testChildChildId composite subkey attribute that uniquely identifies child combined with parent key
   */
  public TestChildChild(IPrimaryKey key, java.math.BigInteger childNo, java.math.BigInteger testParentId, java.lang.String testChildChildId) {
    super(key, childNo, testParentId, testChildChildId); 
  }
}