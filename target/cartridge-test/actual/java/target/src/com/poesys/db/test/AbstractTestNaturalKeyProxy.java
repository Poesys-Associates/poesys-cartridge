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
 * TestNaturalKey. This class is an abstract class that contains AndroMDA 
 * generated code; change nothing in this class. Instead, override any methods 
 * in the concrete subclass generated in the same package. AndroMDA will 
 * overwrite this class each time you run it but will never overwrite the concrete subclass.
 * </p>
 * 
 *
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractTestNaturalKeyProxy extends AbstractLazyLoadingDtoProxy implements ITestNaturalKey {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;
  
  /**
   * Create a TestNaturalKeyProxy. The concrete subclass must call this constructor.
   *
   * @param dto the DTO to proxy
   */
  public AbstractTestNaturalKeyProxy(TestNaturalKey dto) {
    super(dto);

  }

  @Override
  public boolean equals(Object obj) {
    AbstractTestNaturalKeyProxy otherProxy = (AbstractTestNaturalKeyProxy)obj;
    return ((TestNaturalKey)dto).equals(otherProxy.dto);
  }

  @Override
  public int hashCode() {
    return ((TestNaturalKey)dto).hashCode();
  }

  // Local properties (attributes, associations, and association classes)

  /**
   * Get an object of java.lang.String
   *
   * Source: AddNaturalKeyProperties
   * Lazy: false
   * 
   * @return a java.lang.String
   */
  public java.lang.String getKey1() {
    return ((TestNaturalKey)dto).getKey1();
  }

  /**
   * Get an object of java.lang.Double
   *
   * Source: AddLocalAttributeProperties
   * Lazy: false
   * 
   * @return a java.lang.Double
   */
  public java.lang.Double getNumber1() {
    return ((TestNaturalKey)dto).getNumber1();
  }

  /**
   * Get an object of java.lang.Double
   *
   * Source: AddLocalAttributeProperties
   * Lazy: false
   * 
   * @return a java.lang.Double
   */
  public java.lang.Double getNumber2() {
    return ((TestNaturalKey)dto).getNumber2();
  }

  public void markChildrenDeleted() throws com.poesys.db.dto.DtoStatusException {
  	((TestNaturalKey)dto).markChildrenDeleted();
  }
}