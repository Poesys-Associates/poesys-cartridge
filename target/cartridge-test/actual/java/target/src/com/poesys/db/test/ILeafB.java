/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: IDbDto.vsl

package com.poesys.db.test;

import com.poesys.db.dto.IDbDto;

import com.poesys.db.test.IMiddle;


/**
 * <p>
 * A data-access layer data-transfer-object interface for the LeafB. 
 * The data-transfer-object class and proxy class implement this interface, as
 * does the business-layer data-transfer-object class.
 * </p>
 * 
 * <p>
 * Stereotypes:
 * </p>
 * <ul>
 *     <li>Persistent</li>
 * </ul>
 * <p>
 * Class is read/write: true
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public interface ILeafB extends IDbDto, IMiddle {
  

  /**
   * 
   * <p>
   * Added by AddLocalAttributeProperties
   * Owning DTO: ${property.dto.name}
   * Owning package: ${property.dto.packageName}
   * Property prefix: bString
   * </p>
   * @return a java.lang.String bString
   */
  public java.lang.String getBString();
// Setter here if the four conditions below are all true or not false
// Read/Write DTO: true
// Mutable DTO: not false
// Mutable property: not false
// Read/Write property: true

  /**
   * <p>
   * Set the bString.
   * </p>
   *
   * @param bString the value to set into the bString
   * @throws com.poesys.db.InvalidParametersException when the bString 
   *       value is null
   * @throws com.poesys.db.dto.DtoStatusException when the status cannot be set to CHANGED
   */
  public void setBString(java.lang.String bString) throws com.poesys.db.dto.DtoStatusException, com.poesys.db.InvalidParametersException;

}