/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: DbDtoProxy.vsl

package com.poesys.db.memcached_test;


/**
 * <p>
 * A data-access layer proxy for the data-transfer object (DTO) for the 
 * MapElement. This class is the concrete subclass of the generated abstract 
 * class. Make any changes to proxy behavior by overriding methods here rather 
 * than changing the abstract superclass; AndroMDA will overwrite that class 
 * when you run it but will never overwrite this concrete subclass.
 * </p>
 * <p>
 * A polymorphism mapped to a genome map
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public class MapElementProxy extends AbstractMapElementProxy {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;

  /**
   * <p>
   * Create a MapElementProxy as a new object. This constructor calls the abstract 
   * superclass constructor.
   * </p>
   *
   * @param dto the DTO to proxy
   */
  public MapElementProxy(MapElement dto) {
    super(dto); 
  }
}