/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: BsDto.vsl

package com.poesys.bs.test;


import com.poesys.db.pk.IPrimaryKey;
import com.poesys.bs.delegate.DelegateException;


/**
 * <p>
 * A business layer data-transfer object for the Self2Self. This class
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
public class BsSelf2Self extends com.poesys.bs.test.AbstractBsSelf2Self {
  /**
   * Create a BsSelf2Self object from a Self2Self object.
   * 
   * @param dto the data-access layer Self2Self DTO
   * @throws DelegateException when there is a problem creating the Self2Self
   */
  public BsSelf2Self(com.poesys.db.test.ISelf2Self dto) throws DelegateException {
    super(dto);
  }

  /**
   * <p>
   * Create a BsSelf2Self. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the Self2Self
   * @param children2Object ${key.paramTag}
   * @param parents2Object ${key.paramTag}
   * @param children2Self2Id Attribute that is part of the association key
   * @param parents2Self2Id Attribute that is part of the association key
   */
  public BsSelf2Self(IPrimaryKey key, com.poesys.db.test.ISelf2 children2Object, com.poesys.db.test.ISelf2 parents2Object, java.math.BigInteger children2Self2Id, java.math.BigInteger parents2Self2Id) {
    super(key, children2Object, parents2Object, children2Self2Id, parents2Self2Id); 
  }
}