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
 * A data-access layer data-transfer-object interface for the Self2Self. 
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
public interface ISelf2Self extends IDbDto {

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
   * Owning DTO: Self2
   * Owning package: com.poesys.db.memcached_test
   * Property prefix: children2
   * </p>
   * @return a com.poesys.db.memcached_test.ISelf2 children2
   */
  public com.poesys.db.memcached_test.ISelf2 getChildren2();
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
   * Owning DTO: Self2
   * Owning package: com.poesys.db.memcached_test
   * Property prefix: parents2
   * </p>
   * @return a com.poesys.db.memcached_test.ISelf2 parents2
   */
  public com.poesys.db.memcached_test.ISelf2 getParents2();
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
   * Added by AddGeneratedKeyProperties + AddAssociationKeyAttributeProperties
   * Owning DTO: Self2
   * Owning package: com.poesys.db.memcached_test
   * Property prefix: children2
   * </p>
   * @return a java.math.BigInteger children2Self2Id
   */
  public java.math.BigInteger getChildren2Self2Id();
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
   * Added by AddGeneratedKeyProperties + AddAssociationKeyAttributeProperties
   * Owning DTO: Self2
   * Owning package: com.poesys.db.memcached_test
   * Property prefix: parents2
   * </p>
   * @return a java.math.BigInteger parents2Self2Id
   */
  public java.math.BigInteger getParents2Self2Id();
// Setter here if the four conditions below are all true or not false
// Read/Write DTO: true
// Mutable DTO: not true
// Mutable property: not true
// Read/Write property: false
}