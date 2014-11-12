/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: DbDto.vsl

package com.poesys.db.test;


import com.poesys.db.pk.IPrimaryKey;


/**
 * <p>
 * A data-access layer data-transfer object for the TestX. This class
 * is the concrete subclass of the generated abstract class. Make any changes
 * to DTO behavior by overriding methods here rather than changing the abstract
 * superclass; AndroMDA will overwrite that class when you run it but will
 * never overwrite this concrete subclass.
 * </p>
 * <p>
 * The TestX object is the "this" side of a many-to-many association.
 * </p>
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
public class TestX extends AbstractTestX {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;

  /**
   * <p>
   * Create a TestX as a new object. This constructor calls the abstract 
   * superclass constructor.
   * </p>
   *
   */
  public TestX() {
    super(); 
  }

  /**
   * <p>
   * Create a TestX. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the TestX
   * @param testXId primary key attribute
   * @param xAttr 
   */
  public TestX(IPrimaryKey key, java.math.BigInteger testXId, java.lang.String xAttr) {
    super(key, testXId, xAttr); 
  }
}