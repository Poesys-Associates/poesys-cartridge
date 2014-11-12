/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: AbstractDbDtoProxy.vsl

package com.poesys.db.memcached_test;


import com.poesys.db.dto.AbstractLazyLoadingDtoProxy;


/**
 * <p>
 * A data-access layer data-transfer object (DTO) lazy-loading proxy for the 
 * TestAssociationChild. This class is an abstract class that contains AndroMDA 
 * generated code; change nothing in this class. Instead, override any methods 
 * in the concrete subclass generated in the same package. AndroMDA will 
 * overwrite this class each time you run it but will never overwrite the concrete subclass.
 * </p>
 * 
 *
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractTestAssociationChildProxy extends AbstractLazyLoadingDtoProxy implements ITestAssociationChild {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;

  // Lazy-loading query setter strategy nested classes for single-object associations
  
  /**
   * Create a TestAssociationChildProxy. The concrete subclass must call this constructor.
   *
   * @param dto the DTO to proxy
   */
  public AbstractTestAssociationChildProxy(TestAssociationChild dto) {
    super(dto);

    // Setter arrays
    readObjectSetters = new java.util.concurrent.CopyOnWriteArrayList<com.poesys.db.dto.ISet>();
  }

  @Override
  public boolean equals(Object obj) {
    AbstractTestAssociationChildProxy otherProxy = (AbstractTestAssociationChildProxy)obj;
    return ((TestAssociationChild)dto).equals(otherProxy.dto);
  }

  @Override
  public int hashCode() {
    return ((TestAssociationChild)dto).hashCode();
  }

  // Local properties (attributes, associations, and association classes)

  /**
   * Get an object of java.lang.Long
   *
   * Source: AddNaturalKeyProperties + AddParentKeyAttributes
   * Lazy: false
   * 
   * @return a java.lang.Long
   */
  public java.lang.Long getKey1() {
    return ((TestAssociationChild)dto).getKey1();
  }

  /**
   * Get an object of java.lang.Long
   *
   * Source: AddNaturalKeyProperties + AddParentKeyAttributes
   * Lazy: false
   * 
   * @return a java.lang.Long
   */
  public java.lang.Long getKey2() {
    return ((TestAssociationChild)dto).getKey2();
  }

  /**
   * Get an object of java.math.BigInteger
   *
   * Source: AddGeneratedKeyProperties + AddAssociationKeyAttributeProperties + addExplicitSubkeyOnAssociation
   * Lazy: false
   * 
   * @return a java.math.BigInteger
   */
  public java.math.BigInteger getTestXId() {
    return ((TestAssociationChild)dto).getTestXId();
  }

  /**
   * Get an object of java.math.BigInteger
   *
   * Source: AddGeneratedKeyProperties + AddAssociationKeyAttributeProperties + addExplicitSubkeyOnAssociation
   * Lazy: false
   * 
   * @return a java.math.BigInteger
   */
  public java.math.BigInteger getTestYId() {
    return ((TestAssociationChild)dto).getTestYId();
  }

  /**
   * Get an object of java.lang.Long
   *
   * Source: AddLocalAttributeProperties
   * Lazy: false
   * 
   * @return a java.lang.Long
   */
  public java.lang.Long getTsACAttr() {
    return ((TestAssociationChild)dto).getTsACAttr();
  }

  public void setTsACAttr(java.lang.Long tsACAttr)
      {
    ((TestAssociationChild)dto).setTsACAttr(tsACAttr);
  }

  /**
   * Get an object of com.poesys.db.memcached_test.ITestZ
   *
   * Source: AddToOneAssociationRequiredObjectProperties
   * Lazy: false
   * 
   * @return a com.poesys.db.memcached_test.ITestZ
   */
  public com.poesys.db.memcached_test.ITestZ getZKey() {
    return ((TestAssociationChild)dto).getZKey();
  }

  /**
   * Get an object of com.poesys.db.memcached_test.ITestNaturalParent
   *
   * Source: AddToOneAssociationRequiredObjectProperties
   * Lazy: false
   * 
   * @return a com.poesys.db.memcached_test.ITestNaturalParent
   */
  public com.poesys.db.memcached_test.ITestNaturalParent getAssocParent() {
    return ((TestAssociationChild)dto).getAssocParent();
  }

  public void markChildrenDeleted() throws com.poesys.db.dto.DtoStatusException {
  	((TestAssociationChild)dto).markChildrenDeleted();
  }
}