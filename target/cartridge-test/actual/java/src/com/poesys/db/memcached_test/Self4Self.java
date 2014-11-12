/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: DbDto.vsl

package com.poesys.db.memcached_test;


import com.poesys.db.pk.IPrimaryKey;


/**
 * <p>
 * A data-access layer data-transfer object for the Self4Self. This class
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
public class Self4Self extends AbstractSelf4Self {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;

  /**
   * <p>
   * Create a Self4Self as a new object. This constructor calls the abstract 
   * superclass constructor.
   * </p>
   *
   */
  public Self4Self() {
    super(); 
  }

  /**
   * <p>
   * Create a Self4Self. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the Self4Self
   * @param children4Object wraps an associated ISelf4 object
   * @param parents4Object wraps an associated ISelf4 object
   * @param parents4Key1 Attribute that is part of the association key
   * @param children4Key1 Attribute that is part of the association key
   * @param parents4Key2 Attribute that is part of the association key
   * @param children4Key2 Attribute that is part of the association key
   */
  public Self4Self(IPrimaryKey key, com.poesys.db.memcached_test.ISelf4 children4Object, com.poesys.db.memcached_test.ISelf4 parents4Object, java.lang.Long parents4Key1, java.lang.Long children4Key1, java.lang.Long parents4Key2, java.lang.Long children4Key2) {
    super(key, children4Object, parents4Object, parents4Key1, children4Key1, parents4Key2, children4Key2); 
  }
}