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
 * A data-access layer data-transfer-object interface for the TestNaturalChild. 
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
public interface ITestNaturalChild extends IDbDto {

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
   * Added by AddNaturalKeyProperties + AddParentKeyAttributes
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
   * <p>
   * Composite super-key attribute that uniquely identifies child combined with child sub-key and any other parent super-keys
   * </p>
   * <p>
   * Added by AddNaturalKeyProperties + AddParentKeyAttributes
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
   * Added by AddExplicitSubKeyProperties + addNaturalSubkeyOnClass
   * Owning DTO: ${property.dto.name}
   * Owning package: ${property.dto.packageName}
   * Property prefix: subkey
   * </p>
   * @return a java.lang.Long subkey
   */
  public java.lang.Long getSubkey();
// Setter here if the four conditions below are all true or not false
// Read/Write DTO: true
// Mutable DTO: not true
// Mutable property: not false
// Read/Write property: false

  /**
   * 
   * <p>
   * Added by AddToOneAssociationRequiredObjectProperties
   * Owning DTO: TestNaturalParent
   * Owning package: com.poesys.db.memcached_test
   * Property prefix: naturalParent
   * </p>
   * @return a com.poesys.db.memcached_test.ITestNaturalParent naturalParent
   */
  public com.poesys.db.memcached_test.ITestNaturalParent getNaturalParent();
// Setter here if the four conditions below are all true or not false
// Read/Write DTO: true
// Mutable DTO: not true
// Mutable property: not true
// Read/Write property: true
}