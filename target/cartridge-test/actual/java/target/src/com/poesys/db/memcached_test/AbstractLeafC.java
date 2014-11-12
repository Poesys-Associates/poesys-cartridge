/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: AbstractDbDto.vsl

package com.poesys.db.memcached_test;


import com.poesys.db.pk.IPrimaryKey;

/**
 * <p>
 * A data-access layer data-transfer object for the LeafC. This class
 * is an abstract class that contains AndroMDA generated code; change nothing
 * in this class. Instead, override any methods in the concrete subclass
 * generated in the same package. AndroMDA will overwrite this class each time
 * you run it but will never overwrite the concrete subclass.
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
public abstract class AbstractLeafC extends com.poesys.db.memcached_test.ConcreteMiddle implements ILeafC {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;
  

  /**
   * Create an empty LeafC for use in building a new object. The 
   * concrete subclass must call this constructor.
   */
  public AbstractLeafC() {
    super();
    abstractClass = false;
    createInserters();
  }

  /**
   * Create a LeafC. The concrete subclass must call this constructor.
   *
   * @param key the primary key of the LeafC
   * @param baseId primary key attribute
   * @param baseString 
   * @param middleString 
   * @param cString 
   */
  public AbstractLeafC(IPrimaryKey key, java.math.BigInteger baseId, java.lang.String baseString, java.lang.String middleString, java.lang.String cString) {
    super(key, baseId, baseString, middleString);

    this.cString = cString;

    abstractClass = false;
    createInserters();
  }

  /**
   * Nested property cString
   *
   * 
   *
   * Added by AddLocalAttributeProperties
   * Class is read/write: true
   * Class is immutable: false
   * Property is read/write: true
   * Property is lazy: false
   */
  private java.lang.String cString;
  
  /**
   * Get an object of java.lang.String.
   *
   * Source: AddLocalAttributeProperties
   * 
   * @return a java.lang.String
   */

  public java.lang.String getCString() {
    return cString;
  }

  /**
   * Clear the cString data member; override in proxy if lazily loaded,
   * otherwise this method does nothing.
   */
  public void clearCString() {
    // Override in proxy if lazily loaded; otherwise does nothing
  }

  /**
   * <p>
   * Set the cString.
   * </p>
   * <ul>
   * <li>Read/Write DTO: true</li>
   * <li>Immutable DTO: false</li>
   * <li>Read/Write property: true</li>
   * <li>Immutable property: false</li>
   * <li>Lazy property: false (if true, proxy calls this method)</li>
   * </ul>
   * 
   *
   * @param cString the value with which to set the property
   */
  public  void setCString(java.lang.String cString) {
    this.cString = cString;
    setChanged();
  }

  @Override
  public void update(com.poesys.db.dto.ISubject subject,
                     com.poesys.db.dao.DataEvent event)
      throws com.poesys.db.dto.DtoStatusException {
  }

  /**
   * Create the inserters for the LeafC and its superclasses.
   */
  private void createInserters() {
    com.poesys.db.dao.IDaoManager manager =
      com.poesys.db.dao.DaoManagerFactory.getManager(getSubsystem());
    final com.poesys.db.dao.IDaoFactory<com.poesys.db.memcached_test.ILeafC> leafCFactory =
      manager.getFactory("com.poesys.db.memcached_test.LeafC",
                         getSubsystem(),
                         2147483647);
    com.poesys.db.dao.insert.IInsertSql<ILeafC> sql =
      new com.poesys.db.memcached_test.sql.InsertLeafC();
    com.poesys.db.dao.insert.IInsert<ILeafC> inserter =
      leafCFactory.getInsert(sql, true);
    inserters.add(inserter);
  }
}