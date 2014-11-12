/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: DbDto.vsl

package com.poesys.db.memcached_test;


import com.poesys.db.pk.IPrimaryKey;


/**
 * <p>
 * A data-access layer data-transfer object for the TestNaturalKey. This class
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
public class TestNaturalKey extends AbstractTestNaturalKey {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;

  /**
   * <p>
   * Create a TestNaturalKey as a new object. This constructor calls the abstract 
   * superclass constructor.
   * </p>
   *
   */
  public TestNaturalKey() {
    super(); 
  }

  /**
   * <p>
   * Create a TestNaturalKey. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the TestNaturalKey
   * @param key1 key attribute for the Test1
   * @param number1 
   * @param number2 
   */
  public TestNaturalKey(IPrimaryKey key, java.lang.String key1, java.lang.Double number1, java.lang.Double number2) {
    super(key, key1, number1, number2); 
  }
}