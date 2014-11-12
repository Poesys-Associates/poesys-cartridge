/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: AbstractDelegate.vsl

package com.poesys.bs.memcached_test;


import com.poesys.bs.delegate.AbstractDataDelegate;
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
 * TestParent objects and their dependents. You should not modify this class;
 * instead, override or add methods in the class TestParentDelegate, which
 * specializes this class.
 * </p>
 * 
 * @see TestParentDelegate
 * 
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractTestParentDelegate
    extends AbstractDataDelegate<BsTestParent, com.poesys.db.memcached_test.ITestParent, com.poesys.db.pk.SequencePrimaryKey> {
  /**
   * Create an AbstractTestParentDelegate object by supplying the database
   * subsystem in the database.properties file.
   * 
   * @param subsystem the database subsystem to use in database.properties
   */
  public AbstractTestParentDelegate(String subsystem) {
    super(subsystem, 2147483647);
  }

  /**
   * Create an AbstractTestParentDelegate object by supplying the database
   * subsystem in the database.properties file and a specific DBMS, usually
   * IConnectionFactory.DBMS.JNDI.
   * 
   * @param subsystem the database subsystem to use in database.properties
   * @param dbms the DBMS to use (usually IConnectionFactory.DBMS.JNDI)
   */
  public AbstractTestParentDelegate(String subsystem, DBMS dbms) {
    super(subsystem, dbms, 2147483647);
  }

  @Override
  protected String getClassName() {
    return com.poesys.db.memcached_test.TestParent.class.getName();
  }

  @Override
  protected IInsertSql<com.poesys.db.memcached_test.ITestParent> getInsertSql() {
    return new com.poesys.db.memcached_test.sql.InsertTestParent();
  }

  @Override
  protected IDeleteSql<com.poesys.db.memcached_test.ITestParent> getDeleteSql() {
    return new com.poesys.db.memcached_test.sql.DeleteTestParent();
  }

  @Override
  protected IUpdateSql<com.poesys.db.memcached_test.ITestParent> getUpdateSql() {
    return new com.poesys.db.memcached_test.sql.UpdateTestParent();
  }

  @Override
  protected IKeyQuerySql<com.poesys.db.memcached_test.ITestParent> getQueryByKeySql() {
    return new com.poesys.db.memcached_test.sql.QueryTestParent();
  }

  @Override
  protected IQuerySql<com.poesys.db.memcached_test.ITestParent> getQueryListSql() {
    // Query-All method not required
    return new com.poesys.db.memcached_test.sql.QueryAllTestParent();
  }

  @Override
  protected com.poesys.bs.memcached_test.BsTestParent wrapData(com.poesys.db.memcached_test.ITestParent dto) {
    return new com.poesys.bs.memcached_test.BsTestParent(dto);
  }

  /**
   * <p>
   * Create a new TestParent with data fields.
   * </p>
   * <p>
   * The TestParent class has a sequence key; this method generates the
   * sequence for later insertion into the database.
   * </p>
   * 
   * @param testParentId primary key attribute
   * @param parentOccupation 
   * @return the new TestParent object
   * @throws DelegateException when there is a problem generating the key or
   *             creating the object
   */
  public com.poesys.bs.memcached_test.BsTestParent createTestParent(java.math.BigInteger testParentId, java.lang.String parentOccupation)
      throws DelegateException {
      com.poesys.db.pk.SequencePrimaryKey key = null;

    // Generate a new TestParent id if the input key is null.
    if (testParentId == null) {
      java.sql.Connection connection = null;
      try {
        connection = getConnection();
        if (connection == null) {
          throw new DelegateException("Could not get database connection to generate sequence");
        }
        
        if (dbms.equals(DBMS.MYSQL) || dbms.equals(DBMS.JNDI_MYSQL)) {
          key =
            com.poesys.db.pk.PrimaryKeyFactory.createMySqlSequenceKey(connection,
                                                                      "TestParent",
                                                                      "testParentId",
                                                                      "com.poesys.db.memcached_test.TestParent");
        } else if (dbms.equals(DBMS.ORACLE) || dbms.equals(DBMS.JNDI_ORACLE)) {
          // Create key with sequence TestParent
          key =
            com.poesys.db.pk.PrimaryKeyFactory.createOracleSequenceKey(connection,
                                                                       "TestParent",
                                                                       "testParentId",
                                                                       "com.poesys.db.memcached_test.TestParent");
        } else {
          throw new DelegateException("com.poesys.bs.delegate.msg.noDbms");
        }
        // Get the sequence number for use as an attribute value.
        testParentId = key.getValue();
      } catch (com.poesys.db.InvalidParametersException e) {
        Object[] args = e.getParameters().toArray();
        String message = com.poesys.db.Message.getMessage(e.getMessage(), args);
        throw new DelegateException(message, e);
      } catch (com.poesys.db.NoPrimaryKeyException e) {
        Object[] args = e.getParameters().toArray();
        String message = com.poesys.db.Message.getMessage(e.getMessage(), args);
        throw new DelegateException(message, e);
      } catch (java.sql.SQLException e) {
        throw new DelegateException(e.getMessage(), e);
      } finally {
        // Done with this connection, close it and return it to the pool.
        if (connection != null) {
          try {
            connection.close();
          } catch (java.sql.SQLException e) {
            throw new DelegateException(e.getMessage(), e);
          }
        }
      }
    } else {
      key = com.poesys.db.pk.PrimaryKeyFactory.createSequenceKey("testParentId", testParentId, "com.poesys.db.memcached_test.TestParent");
    }

    // Create a data-access DTO for direct access, no proxy required.
    com.poesys.db.memcached_test.ITestParent dto =
      new com.poesys.db.memcached_test.TestParent(key, testParentId, parentOccupation);

    // Create the business DTO.
    return new com.poesys.bs.memcached_test.BsTestParent(dto);
  }

  /**
   * Create a new TestChildChild child of TestParent with a composite key.
   * 
   * @param parent the parent of the child object to create
   * @param childNo composite super-key attribute that uniquely identifies child combined with child sub-key and any other parent super-keys
   * @param testParentId composite super-key attribute that uniquely identifies child combined with child sub-key and any other parent super-keys
   * @param testChildChildId composite subkey attribute that uniquely identifies child combined with parent key
   * @return a new TestChildChild business layer DTO
   * @throws DelegateException when a parameter causes a problem
   */
  public com.poesys.bs.memcached_test.BsTestChildChild createTestChildChild(com.poesys.bs.memcached_test.BsTestParent parent, java.math.BigInteger childNo, java.math.BigInteger testParentId, java.lang.String testChildChildId) throws DelegateException {
    // Create the key.
    com.poesys.db.pk.CompositePrimaryKey key = null;
    try {
      java.util.ArrayList<com.poesys.db.col.AbstractColumnValue> list =
        new java.util.ArrayList<com.poesys.db.col.AbstractColumnValue>();
      list.add(new com.poesys.db.col.StringColumnValue("testChildChildId", testChildChildId));
	  com.poesys.db.pk.IPrimaryKey subKey = 
	    com.poesys.db.pk.PrimaryKeyFactory.createNaturalKey(list, "com.poesys.db.memcached_test.TestChildChild");
      key = 
        com.poesys.db.pk.PrimaryKeyFactory.createCompositeKey(parent.getPrimaryKey(), 
                                                              subKey,
                                                              "com.poesys.db.memcached_test.TestChildChild");
    } catch (com.poesys.db.InvalidParametersException e) {
      Object[] args = e.getParameters().toArray();
      String message = com.poesys.db.Message.getMessage(e.getMessage(), args);
      throw new DelegateException(message, e);
    } catch (com.poesys.db.DuplicateKeyNameException e) {
      Object[] args = e.getParameters().toArray();
      String message = com.poesys.db.Message.getMessage(e.getMessage(), args);
      throw new DelegateException(message, e);
    }

    // Create a composite-key child data-access TestChildChild DTO for direct access, no proxy required.
    com.poesys.db.memcached_test.ITestChildChild dto =
      new com.poesys.db.memcached_test.TestChildChild(key, childNo, testParentId, testChildChildId);

    // Create the business DTO.
    return new com.poesys.bs.memcached_test.BsTestChildChild(dto);
  }
  /**
   * Create a new TC2TC2TC2TC association class child of TestParent with 
   * an association key. This class links the input objects.
   * 
   * @param tc1Object associated TestChild2TestChild object (part of the key)
   * @param tc2Object associated TestChild2TestChild object (part of the key)
   * @param tc1DownChildNo Attribute that is part of the association key
   * @param tc1UpChildNo Attribute that is part of the association key
   * @param tc2DownChildNo Attribute that is part of the association key
   * @param tc2UpChildNo Attribute that is part of the association key
   * @param tc1DownTestParentId Attribute that is part of the association key
   * @param tc1UpTestParentId Attribute that is part of the association key
   * @param tc2DownTestParentId Attribute that is part of the association key
   * @param tc2UpTestParentId Attribute that is part of the association key
   * @return a new TC2TC2TC2TC business layer DTO
   * @throws DelegateException when a parameter causes a problem
   */
  public com.poesys.bs.memcached_test.BsTC2TC2TC2TC createTC2TC2TC2TC(com.poesys.bs.memcached_test.BsTestChild2TestChild tc1Object, com.poesys.bs.memcached_test.BsTestChild2TestChild tc2Object, java.math.BigInteger tc1DownChildNo, java.math.BigInteger tc1UpChildNo, java.math.BigInteger tc2DownChildNo, java.math.BigInteger tc2UpChildNo, java.math.BigInteger tc1DownTestParentId, java.math.BigInteger tc1UpTestParentId, java.math.BigInteger tc2DownTestParentId, java.math.BigInteger tc2UpTestParentId) throws DelegateException {
    // Create the key.
    com.poesys.db.pk.AssociationPrimaryKey key = null;
    try {
      java.util.ArrayList<com.poesys.db.pk.IPrimaryKey> list =
        new java.util.ArrayList<com.poesys.db.pk.IPrimaryKey>();
      list.add(tc1Object.getPrimaryKey());
      list.add(tc2Object.getPrimaryKey());
      key = 
        com.poesys.db.pk.PrimaryKeyFactory.createAssociationKey(list, "com.poesys.db.memcached_test.TC2TC2TC2TC");
    } catch (com.poesys.db.InvalidParametersException e) {
      Object[] args = e.getParameters().toArray();
      String message = com.poesys.db.Message.getMessage(e.getMessage(), args);
      throw new DelegateException(message, e);
    } catch (com.poesys.db.DuplicateKeyNameException e) {
      Object[] args = e.getParameters().toArray();
      String message = com.poesys.db.Message.getMessage(e.getMessage(), args);
      throw new DelegateException(message, e);
    }

    // Create an association-key child data-access TC2TC2TC2TC DTO for direct access, no proxy required.
    com.poesys.db.memcached_test.ITC2TC2TC2TC dto =
      new com.poesys.db.memcached_test.TC2TC2TC2TC(key, tc1Object.toDto(), tc2Object.toDto(), tc1DownChildNo, tc1UpChildNo, tc2DownChildNo, tc2UpChildNo, tc1DownTestParentId, tc1UpTestParentId, tc2DownTestParentId, tc2UpTestParentId);

    // Create the business DTO.
    return new com.poesys.bs.memcached_test.BsTC2TC2TC2TC(dto);
  }
  /**
   * Create a new TestChild2TestChild association class child of TestParent with 
   * an association key. This class links the input objects.
   * 
   * @param downObject associated TestChild object (part of the key)
   * @param upObject associated TestChild object (part of the key)
   * @param downChildNo Attribute that is part of the association key
   * @param upChildNo Attribute that is part of the association key
   * @param downTestParentId Attribute that is part of the association key
   * @param upTestParentId Attribute that is part of the association key
   * @return a new TestChild2TestChild business layer DTO
   * @throws DelegateException when a parameter causes a problem
   */
  public com.poesys.bs.memcached_test.BsTestChild2TestChild createTestChild2TestChild(com.poesys.bs.memcached_test.BsTestChild downObject, com.poesys.bs.memcached_test.BsTestChild upObject, java.math.BigInteger downChildNo, java.math.BigInteger upChildNo, java.math.BigInteger downTestParentId, java.math.BigInteger upTestParentId) throws DelegateException {
    // Create the key.
    com.poesys.db.pk.AssociationPrimaryKey key = null;
    try {
      java.util.ArrayList<com.poesys.db.pk.IPrimaryKey> list =
        new java.util.ArrayList<com.poesys.db.pk.IPrimaryKey>();
      list.add(downObject.getPrimaryKey());
      list.add(upObject.getPrimaryKey());
      key = 
        com.poesys.db.pk.PrimaryKeyFactory.createAssociationKey(list, "com.poesys.db.memcached_test.TestChild2TestChild");
    } catch (com.poesys.db.InvalidParametersException e) {
      Object[] args = e.getParameters().toArray();
      String message = com.poesys.db.Message.getMessage(e.getMessage(), args);
      throw new DelegateException(message, e);
    } catch (com.poesys.db.DuplicateKeyNameException e) {
      Object[] args = e.getParameters().toArray();
      String message = com.poesys.db.Message.getMessage(e.getMessage(), args);
      throw new DelegateException(message, e);
    }

    // Create an association-key child data-access TestChild2TestChild DTO for direct access, no proxy required.
    com.poesys.db.memcached_test.ITestChild2TestChild dto =
      new com.poesys.db.memcached_test.TestChild2TestChild(key, downObject.toDto(), upObject.toDto(), downChildNo, upChildNo, downTestParentId, upTestParentId);

    // Create the business DTO.
    return new com.poesys.bs.memcached_test.BsTestChild2TestChild(dto);
  }
  /**
   * Create a new TestChild child of TestParent with a composite key.
   * 
   * @param parent the parent of the child object to create
   * @param testParentId composite super-key attribute that uniquely identifies child combined with child sub-key and any other parent super-keys
   * @param childNo composite subkey attribute that uniquely identifies child combined with parent key
   * @return a new TestChild business layer DTO
   * @throws DelegateException when a parameter causes a problem
   */
  public com.poesys.bs.memcached_test.BsTestChild createTestChild(com.poesys.bs.memcached_test.BsTestParent parent, java.math.BigInteger testParentId, java.math.BigInteger childNo) throws DelegateException {
    // Create the key.
    com.poesys.db.pk.CompositePrimaryKey key = null;
    try {
      java.util.ArrayList<com.poesys.db.col.AbstractColumnValue> list =
        new java.util.ArrayList<com.poesys.db.col.AbstractColumnValue>();
      list.add(new com.poesys.db.col.BigIntegerColumnValue("childNo", childNo));
	  com.poesys.db.pk.IPrimaryKey subKey = 
	    com.poesys.db.pk.PrimaryKeyFactory.createNaturalKey(list, "com.poesys.db.memcached_test.TestChild");
      key = 
        com.poesys.db.pk.PrimaryKeyFactory.createCompositeKey(parent.getPrimaryKey(), 
                                                              subKey,
                                                              "com.poesys.db.memcached_test.TestChild");
    } catch (com.poesys.db.InvalidParametersException e) {
      Object[] args = e.getParameters().toArray();
      String message = com.poesys.db.Message.getMessage(e.getMessage(), args);
      throw new DelegateException(message, e);
    } catch (com.poesys.db.DuplicateKeyNameException e) {
      Object[] args = e.getParameters().toArray();
      String message = com.poesys.db.Message.getMessage(e.getMessage(), args);
      throw new DelegateException(message, e);
    }

    // Create a composite-key child data-access TestChild DTO for direct access, no proxy required.
    com.poesys.db.memcached_test.ITestChild dto =
      new com.poesys.db.memcached_test.TestChild(key, testParentId, childNo);

    // Create the business DTO.
    return new com.poesys.bs.memcached_test.BsTestChild(dto);
  }
  /**
   * Create a new TestExplicitKeyChild child of TestParent with a composite key.
   * 
   * @param parent the parent of the child object to create
   * @param testParentId composite super-key attribute that uniquely identifies child combined with child sub-key and any other parent super-keys
   * @param explicitSubId sub-key within TestParent to identify the object
   * @return a new TestExplicitKeyChild business layer DTO
   * @throws DelegateException when a parameter causes a problem
   */
  public com.poesys.bs.memcached_test.BsTestExplicitKeyChild createTestExplicitKeyChild(com.poesys.bs.memcached_test.BsTestParent parent, java.math.BigInteger testParentId, java.lang.Long explicitSubId) throws DelegateException {
    // Create the key.
    com.poesys.db.pk.CompositePrimaryKey key = null;
    try {
      java.util.ArrayList<com.poesys.db.col.AbstractColumnValue> list =
        new java.util.ArrayList<com.poesys.db.col.AbstractColumnValue>();
      list.add(new com.poesys.db.col.LongColumnValue("explicitSubId", explicitSubId));
	  com.poesys.db.pk.IPrimaryKey subKey = 
	    com.poesys.db.pk.PrimaryKeyFactory.createNaturalKey(list, "com.poesys.db.memcached_test.TestExplicitKeyChild");
      key = 
        com.poesys.db.pk.PrimaryKeyFactory.createCompositeKey(parent.getPrimaryKey(), 
                                                              subKey,
                                                              "com.poesys.db.memcached_test.TestExplicitKeyChild");
    } catch (com.poesys.db.InvalidParametersException e) {
      Object[] args = e.getParameters().toArray();
      String message = com.poesys.db.Message.getMessage(e.getMessage(), args);
      throw new DelegateException(message, e);
    } catch (com.poesys.db.DuplicateKeyNameException e) {
      Object[] args = e.getParameters().toArray();
      String message = com.poesys.db.Message.getMessage(e.getMessage(), args);
      throw new DelegateException(message, e);
    }

    // Create a composite-key child data-access TestExplicitKeyChild DTO for direct access, no proxy required.
    com.poesys.db.memcached_test.ITestExplicitKeyChild dto =
      new com.poesys.db.memcached_test.TestExplicitKeyChild(key, testParentId, explicitSubId);

    // Create the business DTO.
    return new com.poesys.bs.memcached_test.BsTestExplicitKeyChild(dto);
  }

  @Override
  public void truncateTable(String tableName) throws DelegateException {
    java.sql.Connection c = getConnection();
    com.poesys.db.dao.ddl.ISql sql = null;
    com.poesys.db.dao.ddl.IExecuteSql executive = null;

    // First truncate any child tables.
    sql = new com.poesys.db.dao.ddl.TruncateTableSql("TestChildChild");
    executive = new com.poesys.db.dao.ddl.ExecuteSql(sql);
    try {
      executive.execute(c);
    } catch (java.sql.SQLException e) {
      throw new DelegateException(e.getMessage() + ": " + "TestChildChild", e);
    }

    sql = new com.poesys.db.dao.ddl.TruncateTableSql("TC2TC2TC2TC");
    executive = new com.poesys.db.dao.ddl.ExecuteSql(sql);
    try {
      executive.execute(c);
    } catch (java.sql.SQLException e) {
      throw new DelegateException(e.getMessage() + ": " + "TC2TC2TC2TC", e);
    }

    sql = new com.poesys.db.dao.ddl.TruncateTableSql("TestChild2TestChild");
    executive = new com.poesys.db.dao.ddl.ExecuteSql(sql);
    try {
      executive.execute(c);
    } catch (java.sql.SQLException e) {
      throw new DelegateException(e.getMessage() + ": " + "TestChild2TestChild", e);
    }

    sql = new com.poesys.db.dao.ddl.TruncateTableSql("Child");
    executive = new com.poesys.db.dao.ddl.ExecuteSql(sql);
    try {
      executive.execute(c);
    } catch (java.sql.SQLException e) {
      throw new DelegateException(e.getMessage() + ": " + "Child", e);
    }

    sql = new com.poesys.db.dao.ddl.TruncateTableSql("TestExplicitKeyChild");
    executive = new com.poesys.db.dao.ddl.ExecuteSql(sql);
    try {
      executive.execute(c);
    } catch (java.sql.SQLException e) {
      throw new DelegateException(e.getMessage() + ": " + "TestExplicitKeyChild", e);
    }

    // Now truncate the current table.
    sql = new com.poesys.db.dao.ddl.TruncateTableSql(tableName);
    executive = new com.poesys.db.dao.ddl.ExecuteSql(sql);
    try {
      executive.execute(c);
    } catch (java.sql.SQLException e) {
      throw new DelegateException(e.getMessage() + ": " + "TestParent", e);
    }
  }
}