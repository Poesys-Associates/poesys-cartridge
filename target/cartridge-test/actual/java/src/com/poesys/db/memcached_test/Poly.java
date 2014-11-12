/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: DbDto.vsl

package com.poesys.db.memcached_test;


import com.poesys.db.pk.IPrimaryKey;


/**
 * <p>
 * A data-access layer data-transfer object for the Poly. This class
 * is the concrete subclass of the generated abstract class. Make any changes
 * to DTO behavior by overriding methods here rather than changing the abstract
 * superclass; AndroMDA will overwrite that class when you run it but will
 * never overwrite this concrete subclass.
 * </p>
 * <p>
 * a difference from a specific sequence in the reference species variant
 * </p>
 * <p>
 * Stereotypes:
 * </p>
 * <ul>
 *     <li>IdentityKey</li>
 *     <li>Persistent</li>
 * </ul>
 * 
 * @author Poesys/DB Cartridge
 */
public class Poly extends AbstractPoly {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;

  /**
   * <p>
   * Create a Poly as a new object. This constructor calls the abstract 
   * superclass constructor.
   * </p>
   *
   */
  public Poly() {
    super(); 
  }

  /**
   * <p>
   * Create a Poly. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the Poly
   * @param polyId primary key attribute
   * @param sequence 
   */
  public Poly(IPrimaryKey key, java.math.BigInteger polyId, java.lang.String sequence) {
    super(key, polyId, sequence); 
  }
}