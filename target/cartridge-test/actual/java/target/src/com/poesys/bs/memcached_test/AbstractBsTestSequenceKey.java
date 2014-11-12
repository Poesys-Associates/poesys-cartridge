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


import com.poesys.db.memcached_test.ITestSequenceKey;
import com.poesys.db.memcached_test.TestSequenceKeyProxy;
import com.poesys.db.memcached_test.TestSequenceKey;


/**
 * <p>
 * A business layer data-transfer object (DTO) for the TestSequenceKey. This class
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
 *     <li>Persistent</li>
 *     <li>SequenceKey</li>
 * </ul>
 * <p>
 * Class is read/write: true
 * </p>
 *
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractBsTestSequenceKey 
    extends AbstractDto<com.poesys.db.memcached_test.ITestSequenceKey> {

  /**
   * Create a BsTestSequenceKey object from a TestSequenceKey object.
   * 
   * @param dto the data-access layer TestSequenceKey DTO
   * @throws DelegateException when there is a problem creating the TestSequenceKey
   */
  public AbstractBsTestSequenceKey(ITestSequenceKey dto) throws DelegateException {
    super(dto);
  }

  /**
   * Create a TestSequenceKey from new data.
   *
   * @param key the primary key of the TestSequenceKey
   * @param testSequenceKeyId primary key attribute
   * @param data1 The first data element in the class
   */
  public AbstractBsTestSequenceKey(IPrimaryKey key, java.math.BigInteger testSequenceKeyId, java.lang.String data1) {
    super(new TestSequenceKeyProxy(new TestSequenceKey(key, testSequenceKeyId, data1)));
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean equals(Object arg0) {
    // Unchecked cast here
    IDto<ITestSequenceKey> other = (IDto<ITestSequenceKey>)arg0;
    return dto.equals(other.toDto());
  }

  @Override
  public int hashCode() {
    return dto.hashCode();
  }

  public int compareTo(IDto<ITestSequenceKey> o) {
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
   * <li>Property is defined in the data-access object TestSequenceKey</li>
   * </ul>
   * </p>
   * @return a java.math.BigInteger testSequenceKeyId
   */
  public java.math.BigInteger getTestSequenceKeyId() {
    return dto.getTestSequenceKeyId();
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
   * <li>Property is defined in the data-access object TestSequenceKey</li>
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
   * <li>Property is defined in the data-access object TestSequenceKey</li>
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