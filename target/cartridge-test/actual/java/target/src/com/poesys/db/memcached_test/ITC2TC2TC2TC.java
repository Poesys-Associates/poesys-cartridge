/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: IDbDto.vsl

package com.poesys.db.memcached_test;

import com.poesys.db.dto.IDbDto;

import com.poesys.db.dto.DtoStatusException;
import com.poesys.db.pk.IPrimaryKey;


/**
 * <p>
 * A data-access layer data-transfer-object interface for the TC2TC2TC2TC. 
 * The data-transfer-object class and proxy class implement this interface, as
 * does the business-layer data-transfer-object class.
 * </p>
 * 
 * <p>
 * Stereotypes:
 * </p>
 * <ul>
 *     <li>AssociationKey</li>
 *     <li>Immutable</li>
 *     <li>Persistent</li>
 * </ul>
 * <p>
 * Class is read/write: true
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public interface ITC2TC2TC2TC extends IDbDto {

  boolean equals(Object arg0);

  int hashCode();

  /**
   * <p>
   * Mark any children of this DTO as deleted.
   * </p>
   *
   * @throws DtoStatusException when the child cannot be set to DELETED status
   */
  void markChildrenDeleted() throws DtoStatusException;

  IPrimaryKey getPrimaryKey();
  

  /**
   * <p>
   * Associated object
   * </p>
   * <p>
   * Added by AddAssociationKeyObjectProperties
   * Owning DTO: TestChild2TestChild
   * Owning package: com.poesys.db.memcached_test
   * Property prefix: tc2
   * </p>
   * @return a com.poesys.db.memcached_test.ITestChild2TestChild tc2
   */
  public com.poesys.db.memcached_test.ITestChild2TestChild getTc2();
// Setter here if the four conditions below are all true or not false
// Read/Write DTO: true
// Mutable DTO: not true
// Mutable property: not true
// Read/Write property: false

  /**
   * <p>
   * Associated object
   * </p>
   * <p>
   * Added by AddAssociationKeyObjectProperties
   * Owning DTO: TestChild2TestChild
   * Owning package: com.poesys.db.memcached_test
   * Property prefix: tc1
   * </p>
   * @return a com.poesys.db.memcached_test.ITestChild2TestChild tc1
   */
  public com.poesys.db.memcached_test.ITestChild2TestChild getTc1();
// Setter here if the four conditions below are all true or not false
// Read/Write DTO: true
// Mutable DTO: not true
// Mutable property: not true
// Read/Write property: false

  /**
   * <p>
   * Attribute that is part of the association key
   * </p>
   * <p>
   * Added by AddOrderedSubKeyProperties + AddAssociationKeyAttributeProperties + AddAssociationKeyAttributeProperties
   * Owning DTO: TestChild2TestChild
   * Owning package: com.poesys.db.memcached_test
   * Property prefix: tc1
   * </p>
   * @return a java.math.BigInteger tc1DownChildNo
   */
  public java.math.BigInteger getTc1DownChildNo();
// Setter here if the four conditions below are all true or not false
// Read/Write DTO: true
// Mutable DTO: not true
// Mutable property: not false
// Read/Write property: true

  /**
   * <p>
   * Attribute that is part of the association key
   * </p>
   * <p>
   * Added by AddOrderedSubKeyProperties + AddAssociationKeyAttributeProperties + AddAssociationKeyAttributeProperties
   * Owning DTO: TestChild2TestChild
   * Owning package: com.poesys.db.memcached_test
   * Property prefix: tc1
   * </p>
   * @return a java.math.BigInteger tc1UpChildNo
   */
  public java.math.BigInteger getTc1UpChildNo();
// Setter here if the four conditions below are all true or not false
// Read/Write DTO: true
// Mutable DTO: not true
// Mutable property: not false
// Read/Write property: true

  /**
   * <p>
   * Attribute that is part of the association key
   * </p>
   * <p>
   * Added by AddOrderedSubKeyProperties + AddAssociationKeyAttributeProperties + AddAssociationKeyAttributeProperties
   * Owning DTO: TestChild2TestChild
   * Owning package: com.poesys.db.memcached_test
   * Property prefix: tc2
   * </p>
   * @return a java.math.BigInteger tc2DownChildNo
   */
  public java.math.BigInteger getTc2DownChildNo();
// Setter here if the four conditions below are all true or not false
// Read/Write DTO: true
// Mutable DTO: not true
// Mutable property: not false
// Read/Write property: true

  /**
   * <p>
   * Attribute that is part of the association key
   * </p>
   * <p>
   * Added by AddOrderedSubKeyProperties + AddAssociationKeyAttributeProperties + AddAssociationKeyAttributeProperties
   * Owning DTO: TestChild2TestChild
   * Owning package: com.poesys.db.memcached_test
   * Property prefix: tc2
   * </p>
   * @return a java.math.BigInteger tc2UpChildNo
   */
  public java.math.BigInteger getTc2UpChildNo();
// Setter here if the four conditions below are all true or not false
// Read/Write DTO: true
// Mutable DTO: not true
// Mutable property: not false
// Read/Write property: true

  /**
   * <p>
   * Attribute that is part of the association key
   * </p>
   * <p>
   * Added by AddGeneratedKeyProperties + AddParentKeyAttributes + AddAssociationKeyAttributeProperties + AddAssociationKeyAttributeProperties
   * Owning DTO: TestChild2TestChild
   * Owning package: com.poesys.db.memcached_test
   * Property prefix: tc1
   * </p>
   * @return a java.math.BigInteger tc1DownTestParentId
   */
  public java.math.BigInteger getTc1DownTestParentId();
// Setter here if the four conditions below are all true or not false
// Read/Write DTO: true
// Mutable DTO: not true
// Mutable property: not true
// Read/Write property: false

  /**
   * <p>
   * Attribute that is part of the association key
   * </p>
   * <p>
   * Added by AddGeneratedKeyProperties + AddParentKeyAttributes + AddAssociationKeyAttributeProperties + AddAssociationKeyAttributeProperties
   * Owning DTO: TestChild2TestChild
   * Owning package: com.poesys.db.memcached_test
   * Property prefix: tc1
   * </p>
   * @return a java.math.BigInteger tc1UpTestParentId
   */
  public java.math.BigInteger getTc1UpTestParentId();
// Setter here if the four conditions below are all true or not false
// Read/Write DTO: true
// Mutable DTO: not true
// Mutable property: not true
// Read/Write property: false

  /**
   * <p>
   * Attribute that is part of the association key
   * </p>
   * <p>
   * Added by AddGeneratedKeyProperties + AddParentKeyAttributes + AddAssociationKeyAttributeProperties + AddAssociationKeyAttributeProperties
   * Owning DTO: TestChild2TestChild
   * Owning package: com.poesys.db.memcached_test
   * Property prefix: tc2
   * </p>
   * @return a java.math.BigInteger tc2DownTestParentId
   */
  public java.math.BigInteger getTc2DownTestParentId();
// Setter here if the four conditions below are all true or not false
// Read/Write DTO: true
// Mutable DTO: not true
// Mutable property: not true
// Read/Write property: false

  /**
   * <p>
   * Attribute that is part of the association key
   * </p>
   * <p>
   * Added by AddGeneratedKeyProperties + AddParentKeyAttributes + AddAssociationKeyAttributeProperties + AddAssociationKeyAttributeProperties
   * Owning DTO: TestChild2TestChild
   * Owning package: com.poesys.db.memcached_test
   * Property prefix: tc2
   * </p>
   * @return a java.math.BigInteger tc2UpTestParentId
   */
  public java.math.BigInteger getTc2UpTestParentId();
// Setter here if the four conditions below are all true or not false
// Read/Write DTO: true
// Mutable DTO: not true
// Mutable property: not true
// Read/Write property: false
}