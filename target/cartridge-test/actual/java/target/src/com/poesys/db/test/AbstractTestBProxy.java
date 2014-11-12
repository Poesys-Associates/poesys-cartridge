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
 * TestB. This class is an abstract class that contains AndroMDA 
 * generated code; change nothing in this class. Instead, override any methods 
 * in the concrete subclass generated in the same package. AndroMDA will 
 * overwrite this class each time you run it but will never overwrite the concrete subclass.
 * </p>
 * 
 *
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractTestBProxy extends AbstractLazyLoadingDtoProxy implements ITestB {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;

  // Lazy-loading query setter strategy nested classes for single-object associations
  
  /**
   * Create a TestBProxy. The concrete subclass must call this constructor.
   *
   * @param dto the DTO to proxy
   */
  public AbstractTestBProxy(TestB dto) {
    super(dto);

    // Setter arrays
    readObjectSetters = new java.util.concurrent.CopyOnWriteArrayList<com.poesys.db.dto.ISet>();
  }

  @Override
  public boolean equals(Object obj) {
    AbstractTestBProxy otherProxy = (AbstractTestBProxy)obj;
    return ((TestB)dto).equals(otherProxy.dto);
  }

  @Override
  public int hashCode() {
    return ((TestB)dto).hashCode();
  }

  // Local properties (attributes, associations, and association classes)

  /**
   * Get an object of java.math.BigInteger
   *
   * Source: AddGeneratedKeyProperties
   * Lazy: false
   * 
   * @return a java.math.BigInteger
   */
  public java.math.BigInteger getTestBId() {
    return ((TestB)dto).getTestBId();
  }

  /**
   * Get an object of com.poesys.db.test.ITestA
   *
   * Source: AddToOneAssociationRequiredObjectProperties
   * Lazy: false
   * 
   * @return a com.poesys.db.test.ITestA
   */
  public com.poesys.db.test.ITestA getA() {
    return ((TestB)dto).getA();
  }

  /**
   * Get an object of java.math.BigInteger
   *
   * Source: AddGeneratedKeyProperties + AddToOneAssociationAttributeProperties
   * Lazy: false
   * 
   * @return a java.math.BigInteger
   */
  public java.math.BigInteger getTestAId() {
    return ((TestB)dto).getTestAId();
  }

  public void markChildrenDeleted() throws com.poesys.db.dto.DtoStatusException {
  	((TestB)dto).markChildrenDeleted();
  }
}