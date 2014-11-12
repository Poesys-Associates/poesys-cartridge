/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: DbDto.vsl

package com.poesys.db.test;


import com.poesys.db.pk.IPrimaryKey;


/**
 * <p>
 * A data-access layer data-transfer object for the TestNaturalParent. This class
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
 *     <li>NaturalKey</li>
 *     <li>Persistent</li>
 * </ul>
 * 
 * @author Poesys/DB Cartridge
 */
public class TestNaturalParent extends AbstractTestNaturalParent {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;

  /**
   * <p>
   * Create a TestNaturalParent as a new object. This constructor calls the abstract 
   * superclass constructor.
   * </p>
   *
   */
  public TestNaturalParent() {
    super(); 
  }

  /**
   * <p>
   * Create a TestNaturalParent. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the TestNaturalParent
   * @param key1 
   * @param key2 
   */
  public TestNaturalParent(IPrimaryKey key, java.lang.Long key1, java.lang.Long key2) {
    super(key, key1, key2); 
  }
}