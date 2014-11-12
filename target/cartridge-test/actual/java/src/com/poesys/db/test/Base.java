/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: DbDto.vsl

package com.poesys.db.test;


import com.poesys.db.pk.IPrimaryKey;


/**
 * <p>
 * A data-access layer data-transfer object for the Base. This class
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
public abstract class Base extends AbstractBase {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;

  /**
   * <p>
   * Create a Base as a new object. This constructor calls the abstract 
   * superclass constructor.
   * </p>
   * <p>
   * This class is abstract, so there must be a concrete subclass defined in 
   * the UML diagram that calls this constructor.
   * </p>
   *
   */
  public Base() {
    super(); 
  }

  /**
   * <p>
   * Create a Base. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   * <p>
   * This class is abstract, so there must be a concrete subclass defined in 
   * the UML diagram that calls this constructor.
   * </p>
   *
   * @param key the primary key of the Base
   * @param baseId primary key attribute
   * @param baseString 
   */
  public Base(IPrimaryKey key, java.math.BigInteger baseId, java.lang.String baseString) {
    super(key, baseId, baseString); 
  }
}