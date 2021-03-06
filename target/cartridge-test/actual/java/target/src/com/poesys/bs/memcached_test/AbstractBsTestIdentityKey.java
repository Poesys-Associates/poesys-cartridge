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


import com.poesys.db.memcached_test.ITestIdentityKey;
import com.poesys.db.memcached_test.TestIdentityKeyProxy;
import com.poesys.db.memcached_test.TestIdentityKey;


/**
 * <p>
 * A business layer data-transfer object (DTO) for the TestIdentityKey. This class
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
 *     <li>IdentityKey</li>
 *     <li>Persistent</li>
 * </ul>
 * <p>
 * Class is read/write: true
 * </p>
 *
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractBsTestIdentityKey 
    extends AbstractDto<com.poesys.db.memcached_test.ITestIdentityKey> {

  /**
   * Create a BsTestIdentityKey object from a TestIdentityKey object.
   * 
   * @param dto the data-access layer TestIdentityKey DTO
   * @throws DelegateException when there is a problem creating the TestIdentityKey
   */
  public AbstractBsTestIdentityKey(ITestIdentityKey dto) throws DelegateException {
    super(dto);
  }

  /**
   * Create a TestIdentityKey from new data.
   *
   * @param key the primary key of the TestIdentityKey
   * @param testIdentityKeyId primary key attribute
   * @param data1 The first data element in the class
   */
  public AbstractBsTestIdentityKey(IPrimaryKey key, java.math.BigInteger testIdentityKeyId, java.lang.String data1) {
    super(new TestIdentityKeyProxy(new TestIdentityKey(key, testIdentityKeyId, data1)));
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean equals(Object arg0) {
    // Unchecked cast here
    IDto<ITestIdentityKey> other = (IDto<ITestIdentityKey>)arg0;
    return dto.equals(other.toDto());
  }

  @Override
  public int hashCode() {
    return dto.hashCode();
  }

  public int compareTo(IDto<ITestIdentityKey> o) {
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
   * Primary key attribute
   * </p>
   * <p>
   * Added by AddGeneratedKeyProperties as data member
   * </p>
   * <p>
   * <ul>
   * <li>Property is read/write: false</li>
   * <li>Property is defined in the data-access object TestIdentityKey</li>
   * </ul>
   * </p>
   * @return a java.math.BigInteger testIdentityKeyId
   */
  public java.math.BigInteger getTestIdentityKeyId() {
    return dto.getTestIdentityKeyId();
  }

  /**
   * <p>
   * The first data element in the class
   * </p>
   * <p>
   * Added by AddLocalAttributeProperties as data member
   * </p>
   * <p>
   * <ul>
   * <li>Property is read/write: true</li>
   * <li>Property is defined in the data-access object TestIdentityKey</li>
   * </ul>
   * </p>
   * @return a java.lang.String data1
   */
  public java.lang.String getData1() {
    return dto.getData1();
  }

  /**
   * <p>
   * Set the data1.
   * </p>
   * <p>
   * The first data element in the class
   * </p>
   * <p>
   * Added by AddLocalAttributeProperties
   * </p>
   * <p>
   * <ul>
   * <li>Property is read/write: true</li>
   * <li>Property is defined in the data-access object TestIdentityKey</li>
   * </ul>
   * </p>
   * @param data1 the associated business object
   * @throws com.poesys.db.dto.DtoStatusException when the system can't set
   *                 the data-access status to CHANGED
   */
  public void setData1(java.lang.String data1) 
      throws com.poesys.db.dto.DtoStatusException {
    dto.setData1(data1);
  }
}