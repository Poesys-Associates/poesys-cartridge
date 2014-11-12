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
 * A data-access layer data-transfer-object interface for the TestB. 
 * The data-transfer-object class and proxy class implement this interface, as
 * does the business-layer data-transfer-object class.
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
public interface ITestB extends IDbDto {

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
   * Primary key attribute
   * </p>
   * <p>
   * Added by AddGeneratedKeyProperties
   * Owning DTO: ${property.dto.name}
   * Owning package: ${property.dto.packageName}
   * Property prefix: ${property.prefix}
   * </p>
   * @return a java.math.BigInteger testBId
   */
  public java.math.BigInteger getTestBId();
// Setter here if the four conditions below are all true or not false
// Read/Write DTO: true
// Mutable DTO: not true
// Mutable property: not true
// Read/Write property: false

  /**
   * 
   * <p>
   * Added by AddToOneAssociationRequiredObjectProperties
   * Owning DTO: TestA
   * Owning package: com.poesys.db.memcached_test
   * Property prefix: a
   * </p>
   * @return a com.poesys.db.memcached_test.ITestA a
   */
  public com.poesys.db.memcached_test.ITestA getA();
// Setter here if the four conditions below are all true or not false
// Read/Write DTO: true
// Mutable DTO: not true
// Mutable property: not true
// Read/Write property: true

  /**
   * <p>
   * Foreign key used by setter to query associated object
   * </p>
   * <p>
   * Added by AddGeneratedKeyProperties + AddToOneAssociationAttributeProperties
   * Owning DTO: TestA
   * Owning package: com.poesys.db.memcached_test
   * Property prefix: a
   * </p>
   * @return a java.math.BigInteger testAId
   */
  public java.math.BigInteger getTestAId();
// Setter here if the four conditions below are all true or not false
// Read/Write DTO: true
// Mutable DTO: not true
// Mutable property: not true
// Read/Write property: false
}