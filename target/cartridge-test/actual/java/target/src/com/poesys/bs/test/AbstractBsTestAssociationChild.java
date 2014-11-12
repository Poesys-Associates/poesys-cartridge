/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: AbstractBsDto.vsl


package com.poesys.bs.test;


import com.poesys.bs.delegate.DelegateException;
import com.poesys.bs.dto.IDto;
import com.poesys.db.dto.DtoStatusException;
import com.poesys.db.pk.IPrimaryKey;
import com.poesys.bs.dto.AbstractDto;


import com.poesys.db.test.ITestAssociationChild;
import com.poesys.db.test.TestAssociationChildProxy;
import com.poesys.db.test.TestAssociationChild;


/**
 * <p>
 * A business layer data-transfer object (DTO) for the TestAssociationChild. This class
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
 *     <li>CompositeKey</li>
 *     <li>Persistent</li>
 * </ul>
 * <p>
 * Class is read/write: true
 * </p>
 *
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractBsTestAssociationChild 
    extends AbstractDto<com.poesys.db.test.ITestAssociationChild> {

  /**
   * Create a BsTestAssociationChild object from a TestAssociationChild object.
   * 
   * @param dto the data-access layer TestAssociationChild DTO
   * @throws DelegateException when there is a problem creating the TestAssociationChild
   */
  public AbstractBsTestAssociationChild(ITestAssociationChild dto) throws DelegateException {
    super(dto);
  }

  /**
   * Create a TestAssociationChild from new data.
   *
   * @param key the primary key of the TestAssociationChild
   * @param key1 composite super-key attribute that uniquely identifies child combined with child sub-key and any other parent super-keys
   * @param key2 composite super-key attribute that uniquely identifies child combined with child sub-key and any other parent super-keys
   * @param testXId Attribute that is part of the association key
   * @param testYId Attribute that is part of the association key
   * @param tsACAttr Data attribute for the Test Association Child composite aggregation of the Test
Natural Parent, identified by the zKey TestZ object
   */
  public AbstractBsTestAssociationChild(IPrimaryKey key, java.lang.Long key1, java.lang.Long key2, java.math.BigInteger testXId, java.math.BigInteger testYId, java.lang.Long tsACAttr) {
    super(new TestAssociationChildProxy(new TestAssociationChild(key, key1, key2, testXId, testYId, tsACAttr)));
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean equals(Object arg0) {
    // Unchecked cast here
    IDto<ITestAssociationChild> other = (IDto<ITestAssociationChild>)arg0;
    return dto.equals(other.toDto());
  }

  @Override
  public int hashCode() {
    return dto.hashCode();
  }

  public int compareTo(IDto<ITestAssociationChild> o) {
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
   * Composite super-key attribute that uniquely identifies child combined with child sub-key and any other parent super-keys
   * </p>
   * <p>
   * Added by AddNaturalKeyProperties + AddParentKeyAttributes as data member
   * </p>
   * <p>
   * <ul>
   * <li>Property is read/write: false</li>
   * <li>Property is defined in the data-access object TestAssociationChild</li>
   * </ul>
   * </p>
   * @return a java.lang.Long key1
   */
  public java.lang.Long getKey1() {
    return dto.getKey1();
  }

  /**
   * <p>
   * Composite super-key attribute that uniquely identifies child combined with child sub-key and any other parent super-keys
   * </p>
   * <p>
   * Added by AddNaturalKeyProperties + AddParentKeyAttributes as data member
   * </p>
   * <p>
   * <ul>
   * <li>Property is read/write: false</li>
   * <li>Property is defined in the data-access object TestAssociationChild</li>
   * </ul>
   * </p>
   * @return a java.lang.Long key2
   */
  public java.lang.Long getKey2() {
    return dto.getKey2();
  }

  /**
   * <p>
   * Foreign key used as primary sub-key in composite
   * </p>
   * <p>
   * Added by AddGeneratedKeyProperties + AddAssociationKeyAttributeProperties + addExplicitSubkeyOnAssociation as data member
   * </p>
   * <p>
   * <ul>
   * <li>Property is read/write: false</li>
   * <li>Property is defined in the data-access object TestAssociationChild</li>
   * </ul>
   * </p>
   * @return a java.math.BigInteger testXId
   */
  public java.math.BigInteger getTestXId() {
    return dto.getTestXId();
  }

  /**
   * <p>
   * Foreign key used as primary sub-key in composite
   * </p>
   * <p>
   * Added by AddGeneratedKeyProperties + AddAssociationKeyAttributeProperties + addExplicitSubkeyOnAssociation as data member
   * </p>
   * <p>
   * <ul>
   * <li>Property is read/write: false</li>
   * <li>Property is defined in the data-access object TestAssociationChild</li>
   * </ul>
   * </p>
   * @return a java.math.BigInteger testYId
   */
  public java.math.BigInteger getTestYId() {
    return dto.getTestYId();
  }

  /**
   * <p>
   * Data attribute for the Test Association Child composite aggregation of the Test
   * Natural Parent, identified by the zKey TestZ object
   * </p>
   * <p>
   * Added by AddLocalAttributeProperties as data member
   * </p>
   * <p>
   * <ul>
   * <li>Property is read/write: true</li>
   * <li>Property is defined in the data-access object TestAssociationChild</li>
   * </ul>
   * </p>
   * @return a java.lang.Long tsACAttr
   */
  public java.lang.Long getTsACAttr() {
    return dto.getTsACAttr();
  }

  /**
   * <p>
   * Set the tsACAttr.
   * </p>
   * <p>
   * Data attribute for the Test Association Child composite aggregation of the Test
   * Natural Parent, identified by the zKey TestZ object
   * </p>
   * <p>
   * Added by AddLocalAttributeProperties
   * </p>
   * <p>
   * <ul>
   * <li>Property is read/write: true</li>
   * <li>Property is defined in the data-access object TestAssociationChild</li>
   * </ul>
   * </p>
   * @param tsACAttr the associated business object
   * @throws com.poesys.db.dto.DtoStatusException when the system can't set
   *                 the data-access status to CHANGED
   */
  public void setTsACAttr(java.lang.Long tsACAttr) 
      throws com.poesys.db.dto.DtoStatusException {
    dto.setTsACAttr(tsACAttr);
  }

  /**
   * 
   * <p>
   * Added by AddToOneAssociationRequiredObjectProperties as data member
   * </p>
   * <p>
   * <ul>
   * <li>Property is read/write: false</li>
   * <li>Property is defined in the data-access object TestAssociationChild</li>
   * </ul>
   * </p>
   * @return a com.poesys.bs.test.BsTestZ zKey
   */
  public com.poesys.bs.test.BsTestZ getZKey() {
    // Return 4
    return new com.poesys.bs.test.BsTestZ(dto.getZKey());
  }

  /**
   * 
   * <p>
   * Added by AddToOneAssociationRequiredObjectProperties as data member
   * </p>
   * <p>
   * <ul>
   * <li>Property is read/write: true</li>
   * <li>Property is defined in the data-access object TestAssociationChild</li>
   * </ul>
   * </p>
   * @return a com.poesys.bs.test.BsTestNaturalParent assocParent
   */
  public com.poesys.bs.test.BsTestNaturalParent getAssocParent() {
    // Return 4
    return new com.poesys.bs.test.BsTestNaturalParent(dto.getAssocParent());
  }
}