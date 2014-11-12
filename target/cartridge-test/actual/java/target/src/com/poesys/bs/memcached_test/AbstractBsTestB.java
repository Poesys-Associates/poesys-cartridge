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


import com.poesys.db.memcached_test.ITestB;
import com.poesys.db.memcached_test.TestBProxy;
import com.poesys.db.memcached_test.TestB;


/**
 * <p>
 * A business layer data-transfer object (DTO) for the TestB. This class
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
 *     <li>Persistent</li>
 *     <li>SequenceKey</li>
 * </ul>
 * <p>
 * Class is read/write: true
 * </p>
 *
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractBsTestB 
    extends AbstractDto<com.poesys.db.memcached_test.ITestB> {

  /**
   * Create a BsTestB object from a TestB object.
   * 
   * @param dto the data-access layer TestB DTO
   * @throws DelegateException when there is a problem creating the TestB
   */
  public AbstractBsTestB(ITestB dto) throws DelegateException {
    super(dto);
  }

  /**
   * Create a TestB from new data.
   *
   * @param key the primary key of the TestB
   * @param testBId primary key attribute
   * @param testAId foreign key used by setter to query associated object
   */
  public AbstractBsTestB(IPrimaryKey key, java.math.BigInteger testBId, java.math.BigInteger testAId) {
    super(new TestBProxy(new TestB(key, testBId, testAId)));
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean equals(Object arg0) {
    // Unchecked cast here
    IDto<ITestB> other = (IDto<ITestB>)arg0;
    return dto.equals(other.toDto());
  }

  @Override
  public int hashCode() {
    return dto.hashCode();
  }

  public int compareTo(IDto<ITestB> o) {
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
   * <li>Property is defined in the data-access object TestB</li>
   * </ul>
   * </p>
   * @return a java.math.BigInteger testBId
   */
  public java.math.BigInteger getTestBId() {
    return dto.getTestBId();
  }

  /**
   * 
   * <p>
   * Added by AddToOneAssociationRequiredObjectProperties as data member
   * </p>
   * <p>
   * <ul>
   * <li>Property is read/write: true</li>
   * <li>Property is defined in the data-access object TestB</li>
   * </ul>
   * </p>
   * @return a com.poesys.bs.memcached_test.BsTestA a
   */
  public com.poesys.bs.memcached_test.BsTestA getA() {
    // Return 4
    return new com.poesys.bs.memcached_test.BsTestA(dto.getA());
  }

  /**
   * <p>
   * Foreign key used by setter to query associated object
   * </p>
   * <p>
   * Added by AddGeneratedKeyProperties + AddToOneAssociationAttributeProperties as data member
   * </p>
   * <p>
   * <ul>
   * <li>Property is read/write: false</li>
   * <li>Property is defined in the data-access object TestB</li>
   * </ul>
   * </p>
   * @return a java.math.BigInteger testAId
   */
  public java.math.BigInteger getTestAId() {
    // Return 3
    return dto.getTestAId();
  }
}