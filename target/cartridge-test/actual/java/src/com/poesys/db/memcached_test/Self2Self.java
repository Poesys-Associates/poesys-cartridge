/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: DbDto.vsl

package com.poesys.db.memcached_test;


import com.poesys.db.pk.IPrimaryKey;


/**
 * <p>
 * A data-access layer data-transfer object for the Self2Self. This class
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
 *     <li>AssociationKey</li>
 *     <li>Immutable</li>
 *     <li>Persistent</li>
 * </ul>
 * 
 * @author Poesys/DB Cartridge
 */
public class Self2Self extends AbstractSelf2Self {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;

  /**
   * <p>
   * Create a Self2Self as a new object. This constructor calls the abstract 
   * superclass constructor.
   * </p>
   *
   */
  public Self2Self() {
    super(); 
  }

  /**
   * <p>
   * Create a Self2Self. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the Self2Self
   * @param children2Object wraps an associated ISelf2 object
   * @param parents2Object wraps an associated ISelf2 object
   * @param parents2Self2Id Attribute that is part of the association key
   * @param children2Self2Id Attribute that is part of the association key
   */
  public Self2Self(IPrimaryKey key, com.poesys.db.memcached_test.ISelf2 children2Object, com.poesys.db.memcached_test.ISelf2 parents2Object, java.math.BigInteger parents2Self2Id, java.math.BigInteger children2Self2Id) {
    super(key, children2Object, parents2Object, parents2Self2Id, children2Self2Id); 
  }
}