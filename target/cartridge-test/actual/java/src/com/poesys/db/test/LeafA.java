/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: DbDto.vsl

package com.poesys.db.test;


import com.poesys.db.pk.IPrimaryKey;


/**
 * <p>
 * A data-access layer data-transfer object for the LeafA. This class
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
public class LeafA extends AbstractLeafA {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;

  /**
   * <p>
   * Create a LeafA as a new object. This constructor calls the abstract 
   * superclass constructor.
   * </p>
   *
   */
  public LeafA() {
    super(); 
  }

  /**
   * <p>
   * Create a LeafA. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the LeafA
   * @param baseId primary key attribute
   * @param baseString 
   * @param middleString 
   * @param aString 
   */
  public LeafA(IPrimaryKey key, java.math.BigInteger baseId, java.lang.String baseString, java.lang.String middleString, java.lang.String aString) {
    super(key, baseId, baseString, middleString, aString); 
  }
}