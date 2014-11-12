/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: AbstractDbDto.vsl

package com.poesys.db.memcached_test;


import com.poesys.db.pk.IPrimaryKey;

import com.poesys.db.dto.AbstractDto;
import com.poesys.db.dto.DtoStatusException;


/**
 * <p>
 * A data-access layer data-transfer object for the TestIdentityKey. This class
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
 *     <li>IdentityKey</li>
 *     <li>Persistent</li>
 * </ul>
 * <p>
 * Class is read/write: true
 * </p>
 *
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractTestIdentityKey extends AbstractDto implements ITestIdentityKey {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;
  

  /**
   * Create an empty TestIdentityKey for use in building a new object. The 
   * concrete subclass must call this constructor.
   */
  public AbstractTestIdentityKey() {
    abstractClass = false;
    createInserters();
  }

  /**
   * Create a TestIdentityKey. The concrete subclass must call this constructor.
   *
   * @param key the primary key of the TestIdentityKey
   * @param testIdentityKeyId primary key attribute
   * @param data1 The first data element in the class
   */
  public AbstractTestIdentityKey(IPrimaryKey key, java.math.BigInteger testIdentityKeyId, java.lang.String data1) {
    this.key = key;

    this.testIdentityKeyId = testIdentityKeyId;

    if (testIdentityKeyId == null && !getStatus().equals(Status.NEW)) {
      throw new com.poesys.db.InvalidParametersException("testIdentityKeyId is required for " + key.getValueList());
    }
    
    this.data1 = data1;

    abstractClass = false;
    createInserters();
  }

  @Override
  public boolean equals(Object arg0) {
    AbstractTestIdentityKey other = (AbstractTestIdentityKey)arg0;
    return other.key.equals(key);
  }

  @Override
  public int hashCode() {
    return key.hashCode();
  }

  @Override
  public int compareTo(com.poesys.db.dto.IDbDto o) {
    AbstractTestIdentityKey other = (AbstractTestIdentityKey)o;
    // Sort on the key. Same semantics as equals and hashCode().
    return other.key.compareTo(key);
  }
  
  @Override
  public String getSubsystem() {
    return "com.poesys.db.memcached_test";
  }

  @Override
  public void markChildrenDeleted() throws DtoStatusException {
  }

  @Override
  public IPrimaryKey getPrimaryKey() {
    return key;
  }
  
  @Override
  public java.sql.Connection getConnection() throws java.sql.SQLException {
    java.sql.Connection connection = null;
    
	/* 
	 * The resource bundle for the DTO's subsystem contains the suffix that
	 * distinguishes multiple versions of the subsystem in the database.properties
	 * file, such as "prod" or "test". Most database.properties files have only
	 * one implementation and use external facilities to switch between the
	 * databases (JNDI, for example, or producing different database.properties
	 * files in different setups). Use the subsystem resource bundle to get the
	 * suffix, then use the full subsystem name to get a connection factory for
	 * the DTO's subsystem, then use that factory to get a JDBC connection.
	 */
	java.util.ResourceBundle rb = 
	  java.util.ResourceBundle.getBundle("com.poesys.db.memcached_test");
    String subsystem =
      "com.poesys.db.memcached_test"
          + (rb.getString("suffix") == null
             || rb.getString("suffix").length() == 0 ? ""
              : "." + rb.getString("suffix"));
	  
	try {
	  connection = 
	    com.poesys.db.connection.ConnectionFactoryFactory.getInstance(subsystem).getConnection();
	} catch (com.poesys.db.InvalidParametersException e) {
	  throw new java.sql.SQLException(e.getMessage());
	} catch (java.io.IOException e) {
	  throw new java.sql.SQLException(e.getMessage());
	}
	
	return connection;
  }
  /**
   * Nested property testIdentityKeyId
   *
   * <p>
   * Primary key attribute
   * </p>
   *
   * Added by AddGeneratedKeyProperties
   * Class is read/write: true
   * Class is immutable: false
   * Property is read/write: false
   * Property is lazy: false
   */
  private java.math.BigInteger testIdentityKeyId;
  
  /**
   * Get an object of java.math.BigInteger.
   *
   * Source: AddGeneratedKeyProperties
   * 
   * @return a java.math.BigInteger
   */

  public java.math.BigInteger getTestIdentityKeyId() {
    return testIdentityKeyId;
  }

  /**
   * Clear the testIdentityKeyId data member; override in proxy if lazily loaded,
   * otherwise this method does nothing.
   */
  public void clearTestIdentityKeyId() {
    // Override in proxy if lazily loaded; otherwise does nothing
  }
  /**
   * Nested property data1
   *
   * <p>
   * The first data element in the class
   * </p>
   *
   * Added by AddLocalAttributeProperties
   * Class is read/write: true
   * Class is immutable: false
   * Property is read/write: true
   * Property is lazy: false
   */
  private java.lang.String data1;
  
  /**
   * Get an object of java.lang.String.
   *
   * Source: AddLocalAttributeProperties
   * 
   * @return a java.lang.String
   */

  public java.lang.String getData1() {
    return data1;
  }

  /**
   * Clear the data1 data member; override in proxy if lazily loaded,
   * otherwise this method does nothing.
   */
  public void clearData1() {
    // Override in proxy if lazily loaded; otherwise does nothing
  }

  /**
   * <p>
   * Set the data1.
   * </p>
   * <ul>
   * <li>Read/Write DTO: true</li>
   * <li>Immutable DTO: false</li>
   * <li>Read/Write property: true</li>
   * <li>Immutable property: false</li>
   * <li>Lazy property: false (if true, proxy calls this method)</li>
   * </ul>
   * <p>
   * The first data element in the class
   * </p>
   *
   * @param data1 the value with which to set the property
   */
  public  void setData1(java.lang.String data1) {
    this.data1 = data1;
    setChanged();
  }

  @Override
  public void finalizeInsert(java.sql.PreparedStatement stmt) throws java.sql.SQLException {
    // Set the key attribute.
    java.sql.ResultSet rs = stmt.getGeneratedKeys();
    if (rs.next()) {
      // Get the key value.
      java.math.BigDecimal decimalValue = rs.getBigDecimal(1);
      // Convert the value to a big integer and assign.
      testIdentityKeyId = decimalValue.toBigInteger();
    }
  }

  @Override
  public void update(com.poesys.db.dto.ISubject subject,
                     com.poesys.db.dao.DataEvent event)
      throws com.poesys.db.dto.DtoStatusException {
  }

  /**
   * Create the inserters for the TestIdentityKey and its superclasses.
   */
  private void createInserters() {
    com.poesys.db.dao.IDaoManager manager =
      com.poesys.db.dao.DaoManagerFactory.getManager(getSubsystem());
    final com.poesys.db.dao.IDaoFactory<com.poesys.db.memcached_test.ITestIdentityKey> testIdentityKeyFactory =
      manager.getFactory("com.poesys.db.memcached_test.TestIdentityKey",
                         getSubsystem(),
                         2147483647);
    com.poesys.db.dao.insert.IInsertSql<ITestIdentityKey> sql =
      new com.poesys.db.memcached_test.sql.InsertTestIdentityKey();
    com.poesys.db.dao.insert.IInsert<ITestIdentityKey> inserter =
      testIdentityKeyFactory.getInsert(sql, true);
    inserters.add(inserter);
  }
}