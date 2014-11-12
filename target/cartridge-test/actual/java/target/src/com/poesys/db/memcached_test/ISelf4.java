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
 * A data-access layer data-transfer-object interface for the Self4. 
 * The data-transfer-object class and proxy class implement this interface, as
 * does the business-layer data-transfer-object class.
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
public interface ISelf4 extends IDbDto {

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
   * Added by AddNaturalKeyProperties
   * Owning DTO: ${property.dto.name}
   * Owning package: ${property.dto.packageName}
   * Property prefix: key1
   * </p>
   * @return a java.lang.Long key1
   */
  public java.lang.Long getKey1();
// Setter here if the four conditions below are all true or not false
// Read/Write DTO: true
// Mutable DTO: not true
// Mutable property: not false
// Read/Write property: false

  /**
   * 
   * <p>
   * Added by AddNaturalKeyProperties
   * Owning DTO: ${property.dto.name}
   * Owning package: ${property.dto.packageName}
   * Property prefix: key2
   * </p>
   * @return a java.lang.Long key2
   */
  public java.lang.Long getKey2();
// Setter here if the four conditions below are all true or not false
// Read/Write DTO: true
// Mutable DTO: not true
// Mutable property: not false
// Read/Write property: false

  /**
   * 
   * <p>
   * Added by TransformToProperty + AddToManyAssociationCollectionProperties
   * Owning DTO: Self4
   * Owning package: com.poesys.db.memcached_test
   * Property prefix: parents4
   * </p>
   * @return a java.util.Collection<com.poesys.db.memcached_test.ISelf4> parents4
   */
  public java.util.Collection<com.poesys.db.memcached_test.ISelf4> getParents4();
// Setter here if the four conditions below are all true or not false
// Read/Write DTO: true
// Mutable DTO: not true
// Mutable property: not true
// Read/Write property: true

  /**
   * 
   * <p>
   * Added by TransformToProperty + AddToManyAssociationCollectionProperties
   * Owning DTO: Self4
   * Owning package: com.poesys.db.memcached_test
   * Property prefix: children4
   * </p>
   * @return a java.util.Collection<com.poesys.db.memcached_test.ISelf4> children4
   */
  public java.util.Collection<com.poesys.db.memcached_test.ISelf4> getChildren4();
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
   * Owning DTO: Self4Self
   * Owning package: com.poesys.db.memcached_test
   * Property prefix: parents4
   * </p>
   * @return a java.util.Collection<com.poesys.db.memcached_test.ISelf4Self> parents4Self4SelfLinks
   */
  public java.util.Collection<com.poesys.db.memcached_test.ISelf4Self> getParents4Self4SelfLinks();
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
   * Owning DTO: Self4Self
   * Owning package: com.poesys.db.memcached_test
   * Property prefix: children4
   * </p>
   * @return a java.util.Collection<com.poesys.db.memcached_test.ISelf4Self> children4Self4SelfLinks
   */
  public java.util.Collection<com.poesys.db.memcached_test.ISelf4Self> getChildren4Self4SelfLinks();
// Setter here if the four conditions below are all true or not false
// Read/Write DTO: true
// Mutable DTO: not true
// Mutable property: not true
// Read/Write property: true
}