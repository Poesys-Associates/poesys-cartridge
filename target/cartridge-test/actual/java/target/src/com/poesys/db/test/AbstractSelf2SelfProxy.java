/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: AbstractDbDtoProxy.vsl

package com.poesys.db.test;


import com.poesys.db.dto.AbstractLazyLoadingDtoProxy;


/**
 * <p>
 * A data-access layer data-transfer object (DTO) lazy-loading proxy for the 
 * Self2Self. This class is an abstract class that contains AndroMDA 
 * generated code; change nothing in this class. Instead, override any methods 
 * in the concrete subclass generated in the same package. AndroMDA will 
 * overwrite this class each time you run it but will never overwrite the concrete subclass.
 * </p>
 * 
 *
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractSelf2SelfProxy extends AbstractLazyLoadingDtoProxy implements ISelf2Self {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;

  // Lazy-loading query setter strategy nested classes for single-object associations
  
  /**
   * Create a Self2SelfProxy. The concrete subclass must call this constructor.
   *
   * @param dto the DTO to proxy
   */
  public AbstractSelf2SelfProxy(Self2Self dto) {
    super(dto);

    // Setter arrays
    readObjectSetters = new java.util.concurrent.CopyOnWriteArrayList<com.poesys.db.dto.ISet>();
  }

  @Override
  public boolean equals(Object obj) {
    AbstractSelf2SelfProxy otherProxy = (AbstractSelf2SelfProxy)obj;
    return ((Self2Self)dto).equals(otherProxy.dto);
  }

  @Override
  public int hashCode() {
    return ((Self2Self)dto).hashCode();
  }

  // Local properties (attributes, associations, and association classes)

  /**
   * Get an object of com.poesys.db.test.ISelf2
   *
   * Source: AddAssociationKeyObjectProperties
   * Lazy: false
   * 
   * @return a com.poesys.db.test.ISelf2
   */
  public com.poesys.db.test.ISelf2 getParents2() {
    return ((Self2Self)dto).getParents2();
  }

  /**
   * Get an object of com.poesys.db.test.ISelf2
   *
   * Source: AddAssociationKeyObjectProperties
   * Lazy: false
   * 
   * @return a com.poesys.db.test.ISelf2
   */
  public com.poesys.db.test.ISelf2 getChildren2() {
    return ((Self2Self)dto).getChildren2();
  }

  /**
   * Get an object of java.math.BigInteger
   *
   * Source: AddGeneratedKeyProperties + AddAssociationKeyAttributeProperties
   * Lazy: false
   * 
   * @return a java.math.BigInteger
   */
  public java.math.BigInteger getChildren2Self2Id() {
    return ((Self2Self)dto).getChildren2Self2Id();
  }

  /**
   * Get an object of java.math.BigInteger
   *
   * Source: AddGeneratedKeyProperties + AddAssociationKeyAttributeProperties
   * Lazy: false
   * 
   * @return a java.math.BigInteger
   */
  public java.math.BigInteger getParents2Self2Id() {
    return ((Self2Self)dto).getParents2Self2Id();
  }

  public void markChildrenDeleted() throws com.poesys.db.dto.DtoStatusException {
  	((Self2Self)dto).markChildrenDeleted();
  }
}