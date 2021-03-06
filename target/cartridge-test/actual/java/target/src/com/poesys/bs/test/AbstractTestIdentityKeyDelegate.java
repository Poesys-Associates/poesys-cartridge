/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: AbstractDelegate.vsl

package com.poesys.bs.test;


import com.poesys.bs.delegate.AbstractIdentityDataDelegate;
import com.poesys.bs.delegate.DelegateException;
import com.poesys.db.connection.IConnectionFactory.DBMS;
import com.poesys.db.dao.insert.IInsertSql;
import com.poesys.db.dao.update.IUpdateSql;
import com.poesys.db.dao.delete.IDeleteSql;
import com.poesys.db.dao.query.IKeyQuerySql;
import com.poesys.db.dao.query.IQuerySql;


/**
 * <p>
 * A business delegate that provides an application programming interface for
 * TestIdentityKey objects and their dependents. You should not modify this class;
 * instead, override or add methods in the class TestIdentityKeyDelegate, which
 * specializes this class.
 * </p>
 * 
 * @see TestIdentityKeyDelegate
 * 
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractTestIdentityKeyDelegate
    extends AbstractIdentityDataDelegate<BsTestIdentityKey, com.poesys.db.test.ITestIdentityKey, com.poesys.db.pk.IdentityPrimaryKey> {
  /**
   * Create an AbstractTestIdentityKeyDelegate object by supplying the database
   * subsystem in the database.properties file.
   * 
   * @param subsystem the database subsystem to use in database.properties
   */
  public AbstractTestIdentityKeyDelegate(String subsystem) {
    super(subsystem, 2147483647);
  }

  /**
   * Create an AbstractTestIdentityKeyDelegate object by supplying the database
   * subsystem in the database.properties file and a specific DBMS, usually
   * IConnectionFactory.DBMS.JNDI.
   * 
   * @param subsystem the database subsystem to use in database.properties
   * @param dbms the DBMS to use (usually IConnectionFactory.DBMS.JNDI)
   */
  public AbstractTestIdentityKeyDelegate(String subsystem, DBMS dbms) {
    super(subsystem, dbms, 2147483647);
  }

  @Override
  protected String getClassName() {
    return com.poesys.db.test.TestIdentityKey.class.getName();
  }

  @Override
  protected IInsertSql<com.poesys.db.test.ITestIdentityKey> getInsertSql() {
    return new com.poesys.db.test.sql.InsertTestIdentityKey();
  }

  @Override
  protected IDeleteSql<com.poesys.db.test.ITestIdentityKey> getDeleteSql() {
    return new com.poesys.db.test.sql.DeleteTestIdentityKey();
  }

  @Override
  protected IUpdateSql<com.poesys.db.test.ITestIdentityKey> getUpdateSql() {
    return new com.poesys.db.test.sql.UpdateTestIdentityKey();
  }

  @Override
  protected IKeyQuerySql<com.poesys.db.test.ITestIdentityKey> getQueryByKeySql() {
    return new com.poesys.db.test.sql.QueryTestIdentityKey();
  }

  @Override
  protected IQuerySql<com.poesys.db.test.ITestIdentityKey> getQueryListSql() {
    // Query-All method not required
    return new com.poesys.db.test.sql.QueryAllTestIdentityKey();
  }

  @Override
  protected com.poesys.bs.test.BsTestIdentityKey wrapData(com.poesys.db.test.ITestIdentityKey dto) {
    return new com.poesys.bs.test.BsTestIdentityKey(dto);
  }

  /**
   * <p>
   * Create a new TestIdentityKey with data fields.
   * </p>
   * <p>
   * The TestIdentityKey class has an identity key; this method does nothing with
   * respect to the key, which the database will generate when the new TestIdentityKey
   * inserts into the database.
   * </p>
   * 
   * @param testIdentityKeyId primary key attribute
   * @param data1 The first data element in the class
   * @return the new TestIdentityKey object
   * @throws DelegateException when there is a problem generating the key or
   *             creating the object
   */
  public com.poesys.bs.test.BsTestIdentityKey createTestIdentityKey(java.math.BigInteger testIdentityKeyId, java.lang.String data1)
      throws DelegateException {
    com.poesys.db.pk.IPrimaryKey key = 
      com.poesys.db.test.TestFactory.getTestIdentityKeyPrimaryKey(testIdentityKeyId);
      
    // Create a data-access DTO for direct access, no proxy required.
    com.poesys.db.test.ITestIdentityKey dto =
      new com.poesys.db.test.TestIdentityKey(key, testIdentityKeyId, data1);

    // Create the business DTO.
    return new com.poesys.bs.test.BsTestIdentityKey(dto);
  }

}
