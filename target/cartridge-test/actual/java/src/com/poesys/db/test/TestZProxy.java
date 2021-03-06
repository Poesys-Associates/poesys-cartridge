/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: DbDtoProxy.vsl

package com.poesys.db.test;


/**
 * <p>
 * A data-access layer proxy for the data-transfer object (DTO) for the 
 * TestZ. This class is the concrete subclass of the generated abstract 
 * class. Make any changes to proxy behavior by overriding methods here rather 
 * than changing the abstract superclass; AndroMDA will overwrite that class 
 * when you run it but will never overwrite this concrete subclass.
 * </p>
 * <p>
 * The TestZ object is the assocation class that represents the many-to-many
 * association of TestX and TestY objects.
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public class TestZProxy extends AbstractTestZProxy {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;

  /**
   * <p>
   * Create a TestZProxy as a new object. This constructor calls the abstract 
   * superclass constructor.
   * </p>
   *
   * @param dto the DTO to proxy
   */
  public TestZProxy(TestZ dto) {
    super(dto); 
  }
}