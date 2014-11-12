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
 * A data-access layer data-transfer-object interface for the TestChild. 
 * The data-transfer-object class and proxy class implement this interface, as
 * does the business-layer data-transfer-object class.
 * </p>
 * 
 * <p>
 * Stereotypes:
 * </p>
 * <ul>
 *     <li>CompositeKey</li>
 *     <li>Immutable</li>
 *     <li>Persistent</li>
 * </ul>
 * <p>
 * Class is read/write: true
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public interface ITestChild extends IDbDto {

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
   * Composite super-key attribute that uniquely identifies child combined with child sub-key and any other parent super-keys
   * </p>
   * <p>
   * Added by AddGeneratedKeyProperties + AddParentKeyAttributes
   * Owning DTO: ${property.dto.name}
   * Owning package: ${property.dto.packageName}
   * Property prefix: ${property.prefix}
   * </p>
   * @return a java.math.BigInteger testParentId
   */
  public java.math.BigInteger getTestParentId();
// Setter here if the four conditions below are all true or not false
// Read/Write DTO: true
// Mutable DTO: not true
// Mutable property: not true
// Read/Write property: false

  /**
   * <p>
   * Composite subkey attribute that uniquely identifies child combined with parent key
   * </p>
   * <p>
   * Added by AddOrderedSubKeyProperties
   * Owning DTO: ${property.dto.name}
   * Owning package: ${property.dto.packageName}
   * Property prefix: ${property.prefix}
   * </p>
   * @return a java.math.BigInteger childNo
   */
  public java.math.BigInteger getChildNo();
// Setter here if the four conditions below are all true or not false
// Read/Write DTO: true
// Mutable DTO: not true
// Mutable property: not false
// Read/Write property: true

  /**
   * <p>
   * The parent of all the test children
   * </p>
   * <p>
   * Added by AddToOneAssociationRequiredObjectProperties
   * Owning DTO: TestParent
   * Owning package: com.poesys.db.memcached_test
   * Property prefix: testParent
   * </p>
   * @return a com.poesys.db.memcached_test.ITestParent testParent
   */
  public com.poesys.db.memcached_test.ITestParent getTestParent();
// Setter here if the four conditions below are all true or not false
// Read/Write DTO: true
// Mutable DTO: not true
// Mutable property: not false
// Read/Write property: true

  /**
   * 
   * <p>
   * Added by TransformToProperty + AddToManyAssociationCollectionProperties
   * Owning DTO: TestChild
   * Owning package: com.poesys.db.memcached_test
   * Property prefix: up
   * </p>
   * @return a java.util.Collection<com.poesys.db.memcached_test.ITestChild> up
   */
  public java.util.Collection<com.poesys.db.memcached_test.ITestChild> getUp();
// Setter here if the four conditions below are all true or not false
// Read/Write DTO: true
// Mutable DTO: not true
// Mutable property: not true
// Read/Write property: true

  /**
   * 
   * <p>
   * Added by TransformToProperty + AddToManyAssociationCollectionProperties
   * Owning DTO: TestChild
   * Owning package: com.poesys.db.memcached_test
   * Property prefix: down
   * </p>
   * @return a java.util.Collection<com.poesys.db.memcached_test.ITestChild> down
   */
  public java.util.Collection<com.poesys.db.memcached_test.ITestChild> getDown();
// Setter here if the four conditions below are all true or not false
// Read/Write DTO: true
// Mutable DTO: not true
// Mutable property: not true
// Read/Write property: true

  /**
   * 
   * <p>
   * Added by AddToManyChildCollectionProperties
   * Owning DTO: TestChildChild
   * Owning package: com.poesys.db.memcached_test
   * Property prefix: grandchildren
   * </p>
   * @return a java.util.Collection<com.poesys.db.memcached_test.ITestChildChild> grandchildren
   */
  public java.util.Collection<com.poesys.db.memcached_test.ITestChildChild> getGrandchildren();
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
   * Owning DTO: TestChild2TestChild
   * Owning package: com.poesys.db.memcached_test
   * Property prefix: up
   * </p>
   * @return a java.util.Collection<com.poesys.db.memcached_test.ITestChild2TestChild> upTest2Child2TestChildLinks
   */
  public java.util.Collection<com.poesys.db.memcached_test.ITestChild2TestChild> getUpTest2Child2TestChildLinks();
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
   * Owning DTO: TestChild2TestChild
   * Owning package: com.poesys.db.memcached_test
   * Property prefix: down
   * </p>
   * @return a java.util.Collection<com.poesys.db.memcached_test.ITestChild2TestChild> downTest2Child2TestChildLinks
   */
  public java.util.Collection<com.poesys.db.memcached_test.ITestChild2TestChild> getDownTest2Child2TestChildLinks();
// Setter here if the four conditions below are all true or not false
// Read/Write DTO: true
// Mutable DTO: not true
// Mutable property: not true
// Read/Write property: true
}