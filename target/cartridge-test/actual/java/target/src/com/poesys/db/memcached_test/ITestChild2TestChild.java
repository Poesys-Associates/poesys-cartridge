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
 * A data-access layer data-transfer-object interface for the TestChild2TestChild. 
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
public interface ITestChild2TestChild extends IDbDto {

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
   * 
   * <p>
   * Added by TransformToProperty + AddToManyAssociationCollectionProperties
   * Owning DTO: TestChild2TestChild
   * Owning package: com.poesys.db.memcached_test
   * Property prefix: tc1
   * </p>
   * @return a java.util.Collection<com.poesys.db.memcached_test.ITestChild2TestChild> tc1
   */
  public java.util.Collection<com.poesys.db.memcached_test.ITestChild2TestChild> getTc1();
// Setter here if the four conditions below are all true or not false
// Read/Write DTO: true
// Mutable DTO: not true
// Mutable property: not true
// Read/Write property: true

  /**
   * 
   * <p>
   * Added by TransformToProperty + AddToManyAssociationCollectionProperties
   * Owning DTO: TestChild2TestChild
   * Owning package: com.poesys.db.memcached_test
   * Property prefix: tc2
   * </p>
   * @return a java.util.Collection<com.poesys.db.memcached_test.ITestChild2TestChild> tc2
   */
  public java.util.Collection<com.poesys.db.memcached_test.ITestChild2TestChild> getTc2();
// Setter here if the four conditions below are all true or not false
// Read/Write DTO: true
// Mutable DTO: not true
// Mutable property: not true
// Read/Write property: true

  /**
   * <p>
   * Collection of association class objects (not the associated objects)
   * </p>
   * <p>
   * Added by AddAssociationClassCollectionProperties
   * Owning DTO: TC2TC2TC2TC
   * Owning package: com.poesys.db.memcached_test
   * Property prefix: tc1
   * </p>
   * @return a java.util.Collection<com.poesys.db.memcached_test.ITC2TC2TC2TC> tc1TC2TC2TC2TCLinks
   */
  public java.util.Collection<com.poesys.db.memcached_test.ITC2TC2TC2TC> getTc1TC2TC2TC2TCLinks();
// Setter here if the four conditions below are all true or not false
// Read/Write DTO: true
// Mutable DTO: not true
// Mutable property: not true
// Read/Write property: true

  /**
   * <p>
   * Collection of association class objects (not the associated objects)
   * </p>
   * <p>
   * Added by AddAssociationClassCollectionProperties
   * Owning DTO: TC2TC2TC2TC
   * Owning package: com.poesys.db.memcached_test
   * Property prefix: tc2
   * </p>
   * @return a java.util.Collection<com.poesys.db.memcached_test.ITC2TC2TC2TC> tc2TC2TC2TC2TCLinks
   */
  public java.util.Collection<com.poesys.db.memcached_test.ITC2TC2TC2TC> getTc2TC2TC2TC2TCLinks();
// Setter here if the four conditions below are all true or not false
// Read/Write DTO: true
// Mutable DTO: not true
// Mutable property: not true
// Read/Write property: true

  /**
   * <p>
   * Associated object
   * </p>
   * <p>
   * Added by AddAssociationKeyObjectProperties
   * Owning DTO: TestChild
   * Owning package: com.poesys.db.memcached_test
   * Property prefix: up
   * </p>
   * @return a com.poesys.db.memcached_test.ITestChild up
   */
  public com.poesys.db.memcached_test.ITestChild getUp();
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
   * Owning DTO: TestChild
   * Owning package: com.poesys.db.memcached_test
   * Property prefix: down
   * </p>
   * @return a com.poesys.db.memcached_test.ITestChild down
   */
  public com.poesys.db.memcached_test.ITestChild getDown();
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
   * Added by AddOrderedSubKeyProperties + AddAssociationKeyAttributeProperties
   * Owning DTO: TestChild
   * Owning package: com.poesys.db.memcached_test
   * Property prefix: down
   * </p>
   * @return a java.math.BigInteger downChildNo
   */
  public java.math.BigInteger getDownChildNo();
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
   * Added by AddOrderedSubKeyProperties + AddAssociationKeyAttributeProperties
   * Owning DTO: TestChild
   * Owning package: com.poesys.db.memcached_test
   * Property prefix: up
   * </p>
   * @return a java.math.BigInteger upChildNo
   */
  public java.math.BigInteger getUpChildNo();
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
   * Added by AddGeneratedKeyProperties + AddParentKeyAttributes + AddAssociationKeyAttributeProperties
   * Owning DTO: TestChild
   * Owning package: com.poesys.db.memcached_test
   * Property prefix: down
   * </p>
   * @return a java.math.BigInteger downTestParentId
   */
  public java.math.BigInteger getDownTestParentId();
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
   * Added by AddGeneratedKeyProperties + AddParentKeyAttributes + AddAssociationKeyAttributeProperties
   * Owning DTO: TestChild
   * Owning package: com.poesys.db.memcached_test
   * Property prefix: up
   * </p>
   * @return a java.math.BigInteger upTestParentId
   */
  public java.math.BigInteger getUpTestParentId();
// Setter here if the four conditions below are all true or not false
// Read/Write DTO: true
// Mutable DTO: not true
// Mutable property: not true
// Read/Write property: false
}