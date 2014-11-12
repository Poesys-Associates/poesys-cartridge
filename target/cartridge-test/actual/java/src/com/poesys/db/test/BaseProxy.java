/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: DbDtoProxy.vsl

package com.poesys.db.test;


/**
 * <p>
 * A data-access layer proxy for the data-transfer object (DTO) for the 
 * Base. This class is the concrete subclass of the generated abstract 
 * class. Make any changes to proxy behavior by overriding methods here rather 
 * than changing the abstract superclass; AndroMDA will overwrite that class 
 * when you run it but will never overwrite this concrete subclass.
 * </p>
 * 
 * 
 * @author Poesys/DB Cartridge
 */
public abstract class BaseProxy extends AbstractBaseProxy {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;

  /**
   * <p>
   * Create a BaseProxy as a new object. This constructor calls the abstract 
   * superclass constructor.
   * </p>
   *
   * @param dto the DTO to proxy
   */
  public BaseProxy(Base dto) {
    super(dto); 
  }
}