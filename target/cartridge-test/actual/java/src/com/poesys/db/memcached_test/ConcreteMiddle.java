/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: DbDto.vsl

package com.poesys.db.memcached_test;


import com.poesys.db.pk.IPrimaryKey;


/**
 * <p>
 * A data-access layer data-transfer object for the ConcreteMiddle. This class
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
public class ConcreteMiddle extends AbstractConcreteMiddle {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;

  /**
   * <p>
   * Create a ConcreteMiddle as a new object. This constructor calls the abstract 
   * superclass constructor.
   * </p>
   *
   */
  public ConcreteMiddle() {
    super(); 
  }

  /**
   * <p>
   * Create a ConcreteMiddle. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the ConcreteMiddle
   * @param baseId primary key attribute
   * @param baseString 
   * @param middleString 
   */
  public ConcreteMiddle(IPrimaryKey key, java.math.BigInteger baseId, java.lang.String baseString, java.lang.String middleString) {
    super(key, baseId, baseString, middleString); 
  }
}