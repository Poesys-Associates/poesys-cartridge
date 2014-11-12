/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: DbDto.vsl

package com.poesys.db.memcached_test;


import com.poesys.db.pk.IPrimaryKey;


/**
 * <p>
 * A data-access layer data-transfer object for the TestNaturalChild. This class
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
public class TestNaturalChild extends AbstractTestNaturalChild {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;

  /**
   * <p>
   * Create a TestNaturalChild as a new object. This constructor calls the abstract 
   * superclass constructor.
   * </p>
   *
   */
  public TestNaturalChild() {
    super(); 
  }

  /**
   * <p>
   * Create a TestNaturalChild. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the TestNaturalChild
   * @param key1 composite super-key attribute that uniquely identifies child combined with child sub-key and any other parent super-keys
   * @param key2 composite super-key attribute that uniquely identifies child combined with child sub-key and any other parent super-keys
   * @param subkey 
   */
  public TestNaturalChild(IPrimaryKey key, java.lang.Long key1, java.lang.Long key2, java.lang.Long subkey) {
    super(key, key1, key2, subkey); 
  }
}