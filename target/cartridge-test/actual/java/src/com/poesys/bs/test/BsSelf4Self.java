/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: BsDto.vsl

package com.poesys.bs.test;


import com.poesys.db.pk.IPrimaryKey;
import com.poesys.bs.delegate.DelegateException;


/**
 * <p>
 * A business layer data-transfer object for the Self4Self. This class
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
public class BsSelf4Self extends com.poesys.bs.test.AbstractBsSelf4Self {
  /**
   * Create a BsSelf4Self object from a Self4Self object.
   * 
   * @param dto the data-access layer Self4Self DTO
   * @throws DelegateException when there is a problem creating the Self4Self
   */
  public BsSelf4Self(com.poesys.db.test.ISelf4Self dto) throws DelegateException {
    super(dto);
  }

  /**
   * <p>
   * Create a BsSelf4Self. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the Self4Self
   * @param children4Object ${key.paramTag}
   * @param parents4Object ${key.paramTag}
   * @param children4Key1 Attribute that is part of the association key
   * @param parents4Key1 Attribute that is part of the association key
   * @param children4Key2 Attribute that is part of the association key
   * @param parents4Key2 Attribute that is part of the association key
   */
  public BsSelf4Self(IPrimaryKey key, com.poesys.db.test.ISelf4 children4Object, com.poesys.db.test.ISelf4 parents4Object, java.lang.Long children4Key1, java.lang.Long parents4Key1, java.lang.Long children4Key2, java.lang.Long parents4Key2) {
    super(key, children4Object, parents4Object, children4Key1, parents4Key1, children4Key2, parents4Key2); 
  }
}