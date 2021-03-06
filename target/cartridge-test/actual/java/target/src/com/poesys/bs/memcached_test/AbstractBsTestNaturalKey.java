/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: AbstractBsDto.vsl


package com.poesys.bs.memcached_test;


import com.poesys.bs.delegate.DelegateException;
import com.poesys.bs.dto.IDto;
import com.poesys.db.dto.DtoStatusException;
import com.poesys.db.pk.IPrimaryKey;
import com.poesys.bs.dto.AbstractDto;


import com.poesys.db.memcached_test.ITestNaturalKey;
import com.poesys.db.memcached_test.TestNaturalKeyProxy;
import com.poesys.db.memcached_test.TestNaturalKey;


/**
 * <p>
 * A business layer data-transfer object (DTO) for the TestNaturalKey. This class
 * is an abstract class that contains AndroMDA generated code; change nothing
 * in this class. Instead, override any methods in the concrete subclass
 * generated in the same package. AndroMDA will overwrite this class each time
 * you run it but will never overwrite the concrete subclass.
 * </p>
 * 
 * <p>
 * Stereotypes:
 * </p>
 * <ul>
 *     <li>Immutable</li>
 *     <li>NaturalKey</li>
 *     <li>Persistent</li>
 * </ul>
 * <p>
 * Class is read/write: true
 * </p>
 *
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractBsTestNaturalKey 
    extends AbstractDto<com.poesys.db.memcached_test.ITestNaturalKey> {

  /**
   * Create a BsTestNaturalKey object from a TestNaturalKey object.
   * 
   * @param dto the data-access layer TestNaturalKey DTO
   * @throws DelegateException when there is a problem creating the TestNaturalKey
   */
  public AbstractBsTestNaturalKey(ITestNaturalKey dto) throws DelegateException {
    super(dto);
  }

  /**
   * Create a TestNaturalKey from new data.
   *
   * @param key the primary key of the TestNaturalKey
   * @param key1 key attribute for the Test1
   * @param number1 
   * @param number2 
   */
  public AbstractBsTestNaturalKey(IPrimaryKey key, java.lang.String key1, java.lang.Double number1, java.lang.Double number2) {
    super(new TestNaturalKeyProxy(new TestNaturalKey(key, key1, number1, number2)));
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean equals(Object arg0) {
    // Unchecked cast here
    IDto<ITestNaturalKey> other = (IDto<ITestNaturalKey>)arg0;
    return dto.equals(other.toDto());
  }

  @Override
  public int hashCode() {
    return dto.hashCode();
  }

  public int compareTo(IDto<ITestNaturalKey> o) {
    return dto.compareTo(o.toDto());
  }

  public void markChildrenDeleted() throws DtoStatusException {
    dto.markChildrenDeleted();
  }
  
  public IPrimaryKey getPrimaryKey() {
    return dto.getPrimaryKey();
  }

  // Data member properties

  /**
   * <p>
   * key attribute for the Test1
   * </p>
   * <p>
   * Added by AddNaturalKeyProperties as data member
   * </p>
   * <p>
   * <ul>
   * <li>Property is read/write: false</li>
   * <li>Property is defined in the data-access object TestNaturalKey</li>
   * </ul>
   * </p>
   * @return a java.lang.String key1
   */
  public java.lang.String getKey1() {
    return dto.getKey1();
  }

  /**
   * 
   * <p>
   * Added by AddLocalAttributeProperties as data member
   * </p>
   * <p>
   * <ul>
   * <li>Property is read/write: true</li>
   * <li>Property is defined in the data-access object TestNaturalKey</li>
   * </ul>
   * </p>
   * @return a java.lang.Double number1
   */
  public java.lang.Double getNumber1() {
    return dto.getNumber1();
  }

  /**
   * 
   * <p>
   * Added by AddLocalAttributeProperties as data member
   * </p>
   * <p>
   * <ul>
   * <li>Property is read/write: true</li>
   * <li>Property is defined in the data-access object TestNaturalKey</li>
   * </ul>
   * </p>
   * @return a java.lang.Double number2
   */
  public java.lang.Double getNumber2() {
    return dto.getNumber2();
  }
}