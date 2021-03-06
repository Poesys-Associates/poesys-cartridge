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
 * TestSequenceKey. This class is an abstract class that contains AndroMDA 
 * generated code; change nothing in this class. Instead, override any methods 
 * in the concrete subclass generated in the same package. AndroMDA will 
 * overwrite this class each time you run it but will never overwrite the concrete subclass.
 * </p>
 * 
 *
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractTestSequenceKeyProxy extends AbstractLazyLoadingDtoProxy implements ITestSequenceKey {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;
  
  /**
   * Create a TestSequenceKeyProxy. The concrete subclass must call this constructor.
   *
   * @param dto the DTO to proxy
   */
  public AbstractTestSequenceKeyProxy(TestSequenceKey dto) {
    super(dto);

  }

  @Override
  public boolean equals(Object obj) {
    AbstractTestSequenceKeyProxy otherProxy = (AbstractTestSequenceKeyProxy)obj;
    return ((TestSequenceKey)dto).equals(otherProxy.dto);
  }

  @Override
  public int hashCode() {
    return ((TestSequenceKey)dto).hashCode();
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
  public java.math.BigInteger getTestSequenceKeyId() {
    return ((TestSequenceKey)dto).getTestSequenceKeyId();
  }

  /**
   * Get an object of java.lang.String
   *
   * Source: AddLocalAttributeProperties
   * Lazy: false
   * 
   * @return a java.lang.String
   */
  public java.lang.String getData1() {
    return ((TestSequenceKey)dto).getData1();
  }

  public void setData1(java.lang.String data1)
      {
    ((TestSequenceKey)dto).setData1(data1);
  }

  public void markChildrenDeleted() throws com.poesys.db.dto.DtoStatusException {
  	((TestSequenceKey)dto).markChildrenDeleted();
  }
}