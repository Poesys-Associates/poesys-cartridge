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
 * A data-access layer data-transfer object for the TestY. This class
 * is an abstract class that contains AndroMDA generated code; change nothing
 * in this class. Instead, override any methods in the concrete subclass
 * generated in the same package. AndroMDA will overwrite this class each time
 * you run it but will never overwrite the concrete subclass.
 * </p>
 * <p>
 * The TestY object is the "other" side of a many-to-many association.
 * </p>
 * <p>
 * Stereotypes:
 * </p>
 * <ul>
 *     <li>Persistent</li>
 *     <li>SequenceKey</li>
 * </ul>
 * <p>
 * Class is read/write: true
 * </p>
 *
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractTestY extends AbstractDto implements ITestY {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;
  
  // Setter strategy nested classes for multiple-object associations

  /**
   * Query setter for querying nested x
   *
   * Source: TransformToProperty + AddToManyAssociationCollectionProperties
   *
   * @see com.poesys.db.memcached_test.sql.QueryXsByTestY
   */
  private class QueryXsSetter 
      extends com.poesys.db.dto.AbstractListSetter<com.poesys.db.memcached_test.ITestX, ITestY, java.util.Collection<com.poesys.db.memcached_test.ITestX>> {
    private static final long serialVersionUID = 1L;
    private static final int FETCH_SIZE = 10;

    /**
     * Create a QueryXsSetter object.
     */
    public QueryXsSetter() {
      super("com.poesys.db.memcached_test", 2147483647);
    }

    @Override
    protected String getClassName() {
      return com.poesys.db.memcached_test.TestX.class.getName();
    }

    @Override
    protected int getFetchSize() {
      return FETCH_SIZE;
    }

    @Override
    protected ITestY getParametersDto() {
      return AbstractTestY.this;
    }

    @Override
    protected com.poesys.db.dao.query.IParameterizedQuerySql<com.poesys.db.memcached_test.ITestX, ITestY> getSql() {
      return new com.poesys.db.memcached_test.sql.QueryXsByTestY();
    }

    @Override
    protected void set(java.util.Collection<com.poesys.db.memcached_test.ITestX> list) {
      // No status change; this is just filling in the object data.
      xs = list;
      // Add the primary keys to the serialized key list if there are any.
      if (xs != null) {
        for (com.poesys.db.dto.IDbDto object : xs) {
          xKeys.add(object.getPrimaryKey());
        }
      }
    }

    @Override
    public boolean isSet() {
      // Object is set if the associated xs list is not null
      return xs != null;
    }
  }

  /**
   * Read-Object setter for de-serializing nested xs collection
   *
   * Source: TransformToProperty + AddToManyAssociationCollectionProperties
   *
   * @see com.poesys.db.memcached_test.sql.QueryTestX
   */
  private class ReadXsSetter 
      extends com.poesys.db.dto.AbstractCollectionReadSetter<com.poesys.db.memcached_test.ITestX> {
    private static final long serialVersionUID = 1L;

    /**
     * Create a ReadXsSetter object to read the xs collection.
     */
    public ReadXsSetter() {
      super("com.poesys.db.memcached_test", 2147483647);
    }

    @Override
    protected String getClassName() {
      return com.poesys.db.memcached_test.TestX.class.getName();
    }

    @Override
    protected java.util.Collection<com.poesys.db.memcached_test.ITestX> getObjectCollection() {
      return xs;
    }

    @Override
    protected java.util.List<IPrimaryKey> getPrimaryKeys() {
      return xKeys;
    }

    @Override
    protected com.poesys.db.dao.query.IKeyQuerySql<com.poesys.db.memcached_test.ITestX> getSql() {
      return new com.poesys.db.memcached_test.sql.QueryTestX();
    }

    @Override
    protected void set(java.util.Collection<com.poesys.db.memcached_test.ITestX> collection) {
     xs = collection;
    }
  }

  /**
   * Insert setter for inserting nested to-many association x.
   *
   * @see com.poesys.db.memcached_test.sql.InsertTestX
   */
  private class InsertXsSetter extends com.poesys.db.dto.AbstractInsertSetter {
    private static final long serialVersionUID = 1L;
    
    // Association xs source: TransformToProperty + AddToManyAssociationCollectionProperties

    /**
     * Create an InsertXsSetter object.
     */
    public InsertXsSetter() {
      super("com.poesys.db.memcached_test", 2147483647);
    }

    @Override
    protected String getClassName() {
      return com.poesys.db.memcached_test.TestX.class.getName();
    }

    @SuppressWarnings("unchecked")
    @Override
    protected java.util.Collection<com.poesys.db.dto.IDbDto> getDtos() {
      java.util.Collection<? extends com.poesys.db.dto.IDbDto> dtos = xs;
      return (java.util.Collection<com.poesys.db.dto.IDbDto>)dtos;
    }

    @Override
    protected boolean createKey() {
      return true;
    }
  }

  /**
   * Setter for processing added x, updated x, and 
   * deleted x. 
   */
   
  private class UpdateXsSetter 
      extends com.poesys.db.dto.AbstractProcessNestedObjects<com.poesys.db.memcached_test.ITestX, java.util.Collection<com.poesys.db.memcached_test.ITestX>> {
    private static final long serialVersionUID = 1L;
    private static final int BATCH_SIZE = 100;

    /**
     * Create an UpdateXsSetter object.
     */
    public UpdateXsSetter() {
      super("com.poesys.db.memcached_test", 2147483647);
    }

    @Override
    protected void doChanged(java.sql.Connection connection, java.util.Collection<com.poesys.db.memcached_test.ITestX> dtos)
        throws java.sql.SQLException, com.poesys.db.BatchException, com.poesys.db.ConstraintViolationException,
        com.poesys.db.dto.DtoStatusException {
      // xs source: TransformToProperty + AddToManyAssociationCollectionProperties
      // Immutable: false
      com.poesys.db.dao.IDaoManager manager = 
        com.poesys.db.dao.DaoManagerFactory.getManager(subsystem);

      com.poesys.db.dao.IDaoFactory<com.poesys.db.memcached_test.ITestX> factory = 
        manager.getFactory(com.poesys.db.memcached_test.TestX.class.getName(), subsystem, 2147483647);
      com.poesys.db.dao.update.IUpdateBatch<com.poesys.db.memcached_test.ITestX> updater =
        factory.getUpdateBatch(new com.poesys.db.memcached_test.sql.UpdateTestX());

      // Update the object of the current class.
      updater.update(connection, dtos, dtos.size() / 2);
      // Complete the update by setting the DTOs to EXISTING status.
      for (com.poesys.db.dto.IDbDto dto : dtos) {
        if (dto.getStatus() == Status.CHANGED) {
          dto.setExisting();
        }
      }
    }
    
    @Override
    protected void doDeleted(java.sql.Connection connection, java.util.Collection<com.poesys.db.memcached_test.ITestX> dtos)
        throws java.sql.SQLException, com.poesys.db.BatchException, com.poesys.db.ConstraintViolationException,
        com.poesys.db.dto.DtoStatusException {
      com.poesys.db.dao.IDaoManager manager = 
        com.poesys.db.dao.DaoManagerFactory.getManager(subsystem);
      com.poesys.db.dao.IDaoFactory<com.poesys.db.memcached_test.ITestX> factory = 
        manager.getFactory(com.poesys.db.memcached_test.TestX.class.getName(), subsystem, 2147483647);
      com.poesys.db.dao.delete.IDeleteBatch<com.poesys.db.memcached_test.ITestX> dao = 
        factory.getDeleteBatch(new com.poesys.db.memcached_test.sql.DeleteTestX());
      dao.delete(connection, dtos, BATCH_SIZE);
    }

    @Override
    protected void doNew(java.sql.Connection connection, java.util.Collection<com.poesys.db.memcached_test.ITestX> dtos)
        throws java.sql.SQLException, com.poesys.db.BatchException, com.poesys.db.ConstraintViolationException,
        com.poesys.db.dto.DtoStatusException {
      com.poesys.db.dao.IDaoManager manager = 
        com.poesys.db.dao.DaoManagerFactory.getManager(subsystem);


      com.poesys.db.dao.IDaoFactory<com.poesys.db.memcached_test.ITestX> factory = 
        manager.getFactory(com.poesys.db.memcached_test.TestX.class.getName(), subsystem, 2147483647);
      com.poesys.db.dao.insert.IInsertBatch<com.poesys.db.memcached_test.ITestX> inserter =
        factory.getInsertBatch(new com.poesys.db.memcached_test.sql.InsertTestX());


      // Insert the object of the current class after enabling nested inserts,
      // which will allow connecting up linked objects to any of the inserted
      // classes.
      for (com.poesys.db.dto.IDbDto dto : dtos) {
        dto.setSuppressNestedInserts(false);
      }
      inserter.insert(connection, dtos, dtos.size() / 2);
    }

    @Override
    protected java.util.Collection<com.poesys.db.memcached_test.ITestX> getDtos() {
      return xs;
    }
    
    @Override
    protected String getClassName() {
      return com.poesys.db.memcached_test.TestX.class.getName();
    }
  }

  /**
   * Add com.poesys.db.memcached_test.ITestX object to xs collection.
   * 
   * @param object the com.poesys.db.memcached_test.ITestX object
   */
  public void addXsTestX(com.poesys.db.memcached_test.ITestX object) {
    if (xs == null) {
      // Association not yet created, create it.
      xs = new java.util.concurrent.CopyOnWriteArrayList<com.poesys.db.memcached_test.ITestX>();
    }
    xs.add(object);
    // Add the primary key to the primary key array.
    xKeys.add(object.getPrimaryKey());
  }
   
  /**
   * Query setter for querying nested zs
   *
   * Source: AddAssociationClassCollectionProperties
   *
   * @see com.poesys.db.memcached_test.sql.QueryZsByTestY
   */
  private class QueryZsSetter 
      extends com.poesys.db.dto.AbstractListSetter<com.poesys.db.memcached_test.ITestZ, ITestY, java.util.Collection<com.poesys.db.memcached_test.ITestZ>> {
    private static final long serialVersionUID = 1L;
    private static final int FETCH_SIZE = 10;

    /**
     * Create a QueryZsSetter object.
     */
    public QueryZsSetter() {
      super("com.poesys.db.memcached_test", 2147483647);
    }

    @Override
    protected String getClassName() {
      return com.poesys.db.memcached_test.TestZ.class.getName();
    }

    @Override
    protected int getFetchSize() {
      return FETCH_SIZE;
    }

    @Override
    protected ITestY getParametersDto() {
      return AbstractTestY.this;
    }

    @Override
    protected com.poesys.db.dao.query.IParameterizedQuerySql<com.poesys.db.memcached_test.ITestZ, ITestY> getSql() {
      return new com.poesys.db.memcached_test.sql.QueryZsByTestY();
    }

    @Override
    protected void set(java.util.Collection<com.poesys.db.memcached_test.ITestZ> list) {
      // No status change; this is just filling in the object data.
      zs = list;
      // Add the primary keys to the serialized key list if there are any.
      if (zs != null) {
        for (com.poesys.db.dto.IDbDto object : zs) {
          zsKeys.add(object.getPrimaryKey());
        }
      }
    }

    @Override
    public boolean isSet() {
      // Object is set if the associated zs list is not null
      return zs != null;
    }
  }

  /**
   * Read-Object setter for de-serializing nested zs collection
   *
   * Source: AddAssociationClassCollectionProperties
   *
   * @see com.poesys.db.memcached_test.sql.QueryTestZ
   */
  private class ReadZsSetter 
      extends com.poesys.db.dto.AbstractCollectionReadSetter<com.poesys.db.memcached_test.ITestZ> {
    private static final long serialVersionUID = 1L;

    /**
     * Create a ReadZsSetter object to read the zs collection.
     */
    public ReadZsSetter() {
      super("com.poesys.db.memcached_test", 2147483647);
    }

    @Override
    protected String getClassName() {
      return com.poesys.db.memcached_test.TestZ.class.getName();
    }

    @Override
    protected java.util.Collection<com.poesys.db.memcached_test.ITestZ> getObjectCollection() {
      return zs;
    }

    @Override
    protected java.util.List<IPrimaryKey> getPrimaryKeys() {
      return zsKeys;
    }

    @Override
    protected com.poesys.db.dao.query.IKeyQuerySql<com.poesys.db.memcached_test.ITestZ> getSql() {
      return new com.poesys.db.memcached_test.sql.QueryTestZ();
    }

    @Override
    protected void set(java.util.Collection<com.poesys.db.memcached_test.ITestZ> collection) {
     zs = collection;
    }
  }

  /**
   * Insert setter for inserting nested to-many association zs.
   *
   * @see com.poesys.db.memcached_test.sql.InsertTestZ
   */
  private class InsertZsSetter extends com.poesys.db.dto.AbstractInsertSetter {
    private static final long serialVersionUID = 1L;
    
    // Association zs source: AddAssociationClassCollectionProperties

    /**
     * Create an InsertZsSetter object.
     */
    public InsertZsSetter() {
      super("com.poesys.db.memcached_test", 2147483647);
    }

    @Override
    protected String getClassName() {
      return com.poesys.db.memcached_test.TestZ.class.getName();
    }

    @SuppressWarnings("unchecked")
    @Override
    protected java.util.Collection<com.poesys.db.dto.IDbDto> getDtos() {
      java.util.Collection<? extends com.poesys.db.dto.IDbDto> dtos = zs;
      return (java.util.Collection<com.poesys.db.dto.IDbDto>)dtos;
    }

    @Override
    protected boolean createKey() {
      return true;
    }
  }

  /**
   * Setter for processing added zs and updated zs.
   * Deleted zs are deleted in the database cascade delete.
   */
   
  private class UpdateZsSetter 
      extends com.poesys.db.dto.AbstractProcessNestedObjects<com.poesys.db.memcached_test.ITestZ, java.util.Collection<com.poesys.db.memcached_test.ITestZ>> {
    private static final long serialVersionUID = 1L;
    private static final int BATCH_SIZE = 100;

    /**
     * Create an UpdateZsSetter object.
     */
    public UpdateZsSetter() {
      super("com.poesys.db.memcached_test", 2147483647);
    }

    @Override
    protected void doChanged(java.sql.Connection connection, java.util.Collection<com.poesys.db.memcached_test.ITestZ> dtos)
        throws java.sql.SQLException, com.poesys.db.BatchException, com.poesys.db.ConstraintViolationException,
        com.poesys.db.dto.DtoStatusException {
      // zs source: AddAssociationClassCollectionProperties
      // Immutable: false
      com.poesys.db.dao.IDaoManager manager = 
        com.poesys.db.dao.DaoManagerFactory.getManager(subsystem);

      com.poesys.db.dao.IDaoFactory<com.poesys.db.memcached_test.ITestZ> factory = 
        manager.getFactory(com.poesys.db.memcached_test.TestZ.class.getName(), subsystem, 2147483647);
      com.poesys.db.dao.update.IUpdateBatch<com.poesys.db.memcached_test.ITestZ> updater =
        factory.getUpdateBatch(new com.poesys.db.memcached_test.sql.UpdateTestZ());

      // Update the object of the current class.
      updater.update(connection, dtos, dtos.size() / 2);
      // Complete the update by setting the DTOs to EXISTING status.
      for (com.poesys.db.dto.IDbDto dto : dtos) {
        if (dto.getStatus() == Status.CHANGED) {
          dto.setExisting();
        }
      }
    }
    
    @Override
    protected void doDeleted(java.sql.Connection connection, java.util.Collection<com.poesys.db.memcached_test.ITestZ> dtos)
        throws java.sql.SQLException, com.poesys.db.BatchException, com.poesys.db.ConstraintViolationException,
        com.poesys.db.dto.DtoStatusException {
      com.poesys.db.dao.IDaoManager manager = 
        com.poesys.db.dao.DaoManagerFactory.getManager(subsystem);
      com.poesys.db.dao.IDaoFactory<com.poesys.db.memcached_test.ITestZ> factory = 
        manager.getFactory(com.poesys.db.memcached_test.TestZ.class.getName(), subsystem, 2147483647);
      com.poesys.db.dao.delete.IDeleteBatch<com.poesys.db.memcached_test.ITestZ> dao = 
        factory.getDeleteBatch(new com.poesys.db.memcached_test.sql.DeleteTestZ());
      dao.delete(connection, dtos, BATCH_SIZE);
    }

    @Override
    protected void doNew(java.sql.Connection connection, java.util.Collection<com.poesys.db.memcached_test.ITestZ> dtos)
        throws java.sql.SQLException, com.poesys.db.BatchException, com.poesys.db.ConstraintViolationException,
        com.poesys.db.dto.DtoStatusException {
      com.poesys.db.dao.IDaoManager manager = 
        com.poesys.db.dao.DaoManagerFactory.getManager(subsystem);


      com.poesys.db.dao.IDaoFactory<com.poesys.db.memcached_test.ITestZ> factory = 
        manager.getFactory(com.poesys.db.memcached_test.TestZ.class.getName(), subsystem, 2147483647);
      com.poesys.db.dao.insert.IInsertBatch<com.poesys.db.memcached_test.ITestZ> inserter =
        factory.getInsertBatch(new com.poesys.db.memcached_test.sql.InsertTestZ());


      // Insert the object of the current class after enabling nested inserts,
      // which will allow connecting up linked objects to any of the inserted
      // classes.
      for (com.poesys.db.dto.IDbDto dto : dtos) {
        dto.setSuppressNestedInserts(false);
      }
      inserter.insert(connection, dtos, dtos.size() / 2);
    }

    @Override
    protected java.util.Collection<com.poesys.db.memcached_test.ITestZ> getDtos() {
      return zs;
    }
    
    @Override
    protected String getClassName() {
      return com.poesys.db.memcached_test.TestZ.class.getName();
    }
  }

  /**
   * Add com.poesys.db.memcached_test.ITestZ object to zs collection.
   * 
   * @param object the com.poesys.db.memcached_test.ITestZ object
   */
  public void addZsTestZ(com.poesys.db.memcached_test.ITestZ object) {
    if (zs == null) {
      // Association not yet created, create it.
      zs = new java.util.concurrent.CopyOnWriteArrayList<com.poesys.db.memcached_test.ITestZ>();
    }
    zs.add(object);
    // Add the primary key to the primary key array.
    zsKeys.add(object.getPrimaryKey());
  }
   
  /**
   * Create an empty TestY for use in building a new object. The 
   * concrete subclass must call this constructor.
   */
  public AbstractTestY() {
    abstractClass = false;
    createInserters();

    // Setter arrays (create if null)
    if (querySetters == null) {
      querySetters = new java.util.concurrent.CopyOnWriteArrayList<com.poesys.db.dto.ISet>();
    }
    if (insertSetters == null) {
      insertSetters = new java.util.concurrent.CopyOnWriteArrayList<com.poesys.db.dto.ISet>();
    }
    if (preSetters == null) {
      preSetters = new java.util.concurrent.CopyOnWriteArrayList<com.poesys.db.dto.ISet>();
    }
    if (postSetters == null) {
      postSetters = new java.util.concurrent.CopyOnWriteArrayList<com.poesys.db.dto.ISet>();
    }
    if (readObjectSetters == null) {
      readObjectSetters = new java.util.concurrent.CopyOnWriteArrayList<com.poesys.db.dto.ISet>();
    }
    
    // Add the many-to-many collection setters for the xs property.
    querySetters.add(new QueryXsSetter());
    readObjectSetters.add(new ReadXsSetter());
    insertSetters.add(new InsertXsSetter());
    postSetters.add(new UpdateXsSetter());
    
    // Add the many-to-many collection setters for the zs property.
    querySetters.add(new QueryZsSetter());
    readObjectSetters.add(new ReadZsSetter());
    insertSetters.add(new InsertZsSetter());
    postSetters.add(new UpdateZsSetter());
  }

  /**
   * Create a TestY. The concrete subclass must call this constructor.
   *
   * @param key the primary key of the TestY
   * @param testYId primary key attribute
   * @param yAttr 
   */
  public AbstractTestY(IPrimaryKey key, java.math.BigInteger testYId, java.lang.String yAttr) {
    this.key = key;

    this.testYId = testYId;

    if (testYId == null) {
      throw new com.poesys.db.InvalidParametersException("testYId is required for " + key.getValueList());
    }
    
    this.yAttr = yAttr;

    // Setter arrays (create if null)
    if (querySetters == null) {
      querySetters = new java.util.concurrent.CopyOnWriteArrayList<com.poesys.db.dto.ISet>();
    }
    if (insertQuerySetters == null) {
      insertQuerySetters = new java.util.concurrent.CopyOnWriteArrayList<com.poesys.db.dto.ISet>();
    }
    if (insertSetters == null) {
      insertSetters = new java.util.concurrent.CopyOnWriteArrayList<com.poesys.db.dto.ISet>();
    }
    if (preSetters == null) {
      preSetters = new java.util.concurrent.CopyOnWriteArrayList<com.poesys.db.dto.ISet>();
    }
    if (postSetters == null) {
      postSetters = new java.util.concurrent.CopyOnWriteArrayList<com.poesys.db.dto.ISet>();
    }
    if (readObjectSetters == null) {
      readObjectSetters = new java.util.concurrent.CopyOnWriteArrayList<com.poesys.db.dto.ISet>();
    }
    
    // Add the many-to-many collection setters for the x property.
    querySetters.add(new QueryXsSetter());
    readObjectSetters.add(new ReadXsSetter());
    insertSetters.add(new InsertXsSetter());
    postSetters.add(new UpdateXsSetter());
    
    // Add the many-to-many collection setters for the zs property.
    querySetters.add(new QueryZsSetter());
    readObjectSetters.add(new ReadZsSetter());
    insertSetters.add(new InsertZsSetter());
    postSetters.add(new UpdateZsSetter());
    abstractClass = false;
    createInserters();
  }

  @Override
  public boolean equals(Object arg0) {
    AbstractTestY other = (AbstractTestY)arg0;
    return other.key.equals(key);
  }

  @Override
  public int hashCode() {
    return key.hashCode();
  }

  @Override
  public int compareTo(com.poesys.db.dto.IDbDto o) {
    AbstractTestY other = (AbstractTestY)o;
    // Sort on the key. Same semantics as equals and hashCode().
    return other.key.compareTo(key);
  }
  
  @Override
  public String getSubsystem() {
    return "com.poesys.db.memcached_test";
  }

  @Override
  public void markChildrenDeleted() throws DtoStatusException {
    // Only process deletes if child exists.
    if (zs != null) {
      for (com.poesys.db.dto.IDbDto dto : zs) {
        dto.cascadeDelete();
      }
    }
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
   * Nested property testYId
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
  private java.math.BigInteger testYId;
  
  /**
   * Get an object of java.math.BigInteger.
   *
   * Source: AddGeneratedKeyProperties
   * 
   * @return a java.math.BigInteger
   */

  public java.math.BigInteger getTestYId() {
    return testYId;
  }

  /**
   * Clear the testYId data member; override in proxy if lazily loaded,
   * otherwise this method does nothing.
   */
  public void clearTestYId() {
    // Override in proxy if lazily loaded; otherwise does nothing
  }
  /**
   * Nested property yAttr
   *
   * 
   *
   * Added by AddLocalAttributeProperties
   * Class is read/write: true
   * Class is immutable: false
   * Property is read/write: true
   * Property is lazy: false
   */
  private java.lang.String yAttr;
  
  /**
   * Get an object of java.lang.String.
   *
   * Source: AddLocalAttributeProperties
   * 
   * @return a java.lang.String
   */

  public java.lang.String getYAttr() {
    return yAttr;
  }

  /**
   * Clear the yAttr data member; override in proxy if lazily loaded,
   * otherwise this method does nothing.
   */
  public void clearYAttr() {
    // Override in proxy if lazily loaded; otherwise does nothing
  }

  /**
   * <p>
   * Set the yAttr.
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
   * @param yAttr the value with which to set the property
   */
  public  void setYAttr(java.lang.String yAttr) {
    this.yAttr = yAttr;
    setChanged();
  }
  /**
   * Nested property xs
   *
   * 
   *
   * Added by TransformToProperty + AddToManyAssociationCollectionProperties
   * Class is read/write: true
   * Class is immutable: false
   * Property is read/write: true
   * Property is lazy: false
   */
  // Doesn't serialize; package access allows proxy to set on readObject()
  transient java.util.Collection<com.poesys.db.memcached_test.ITestX> xs;
  // Ordered list of keys of the objects in the xs list
  java.util.List<com.poesys.db.pk.IPrimaryKey> xKeys = 
    new java.util.ArrayList<com.poesys.db.pk.IPrimaryKey>();
  
  /**
   * Get a collection of com.poesys.db.memcached_test.ITestX.
   *
   * Source: TransformToProperty + AddToManyAssociationCollectionProperties
   * 
   * @return a java.util.Collection<com.poesys.db.memcached_test.ITestX>
   */

  public java.util.Collection<com.poesys.db.memcached_test.ITestX> getXs() {
    return xs;
  }

  /**
   * Clear the xs data member; override in proxy if lazily loaded,
   * otherwise this method does nothing.
   */
  public void clearXs() {
    // Override in proxy if lazily loaded; otherwise does nothing
  }

  /**
   * <p>
   * Set the xs.
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
   * @param xs the value with which to set the property
   */
  public  void setX(java.util.Collection<com.poesys.db.memcached_test.ITestX> xs) {
    this.xs = xs;
    // Add the primary keys of the new collection to the serialized key list.
    xKeys.clear();
    if (xs != null) {
      for (com.poesys.db.dto.IDbDto object : xs) {
        xKeys.add(object.getPrimaryKey());
      }
    }
    setChanged();
  }
  /**
   * Nested property zs
   *
   * <p>
   * Collection of association class objects (not the associated objects)
   * </p>
   *
   * Added by AddAssociationClassCollectionProperties
   * Class is read/write: true
   * Class is immutable: false
   * Property is read/write: true
   * Property is lazy: false
   */
  // Doesn't serialize; package access allows proxy to set on readObject()
  transient java.util.Collection<com.poesys.db.memcached_test.ITestZ> zs;
  // Ordered list of keys of the objects in the zs list
  java.util.List<com.poesys.db.pk.IPrimaryKey> zsKeys = 
    new java.util.ArrayList<com.poesys.db.pk.IPrimaryKey>();
  
  /**
   * Get a collection of com.poesys.db.memcached_test.ITestZ.
   *
   * Source: AddAssociationClassCollectionProperties
   * 
   * @return a java.util.Collection<com.poesys.db.memcached_test.ITestZ>
   */

  public java.util.Collection<com.poesys.db.memcached_test.ITestZ> getZs() {
    return zs;
  }

  /**
   * Clear the zs data member; override in proxy if lazily loaded,
   * otherwise this method does nothing.
   */
  public void clearZs() {
    // Override in proxy if lazily loaded; otherwise does nothing
  }

  /**
   * <p>
   * Set the zs.
   * </p>
   * <ul>
   * <li>Read/Write DTO: true</li>
   * <li>Immutable DTO: false</li>
   * <li>Read/Write property: true</li>
   * <li>Immutable property: false</li>
   * <li>Lazy property: false (if true, proxy calls this method)</li>
   * </ul>
   * <p>
   * Collection of association class objects (not the associated objects)
   * </p>
   *
   * @param zs the value with which to set the property
   * @throws com.poesys.db.InvalidParametersException when the input zs is null
   */
  public void setZs(java.util.Collection<com.poesys.db.memcached_test.ITestZ> zs)
      throws com.poesys.db.InvalidParametersException {
    if (zs == null) {
      throw new com.poesys.db.InvalidParametersException("zs is required");
    }
    
    this.zs = zs;
    // Add the primary keys of the new collection to the serialized key list.
    zsKeys.clear();
    if (zs != null) {
      for (com.poesys.db.dto.IDbDto object : zs) {
        zsKeys.add(object.getPrimaryKey());
      }
    }
    setChanged();
  }

  @Override
  public void update(com.poesys.db.dto.ISubject subject,
                     com.poesys.db.dao.DataEvent event)
      throws com.poesys.db.dto.DtoStatusException {

    // Clean up zs.
    if (subject != null && subject instanceof com.poesys.db.memcached_test.ITestZ && 
        event == com.poesys.db.dao.DataEvent.DELETE &&
        zs != null) {
      // Delete to-many zs child from collection
      zs.remove(subject);
      subject.detach(this, com.poesys.db.dao.DataEvent.MARKED_DELETED);
      subject.detach(this, com.poesys.db.dao.DataEvent.DELETE);
    }

    // Cascade delete to zs.
    if (event == com.poesys.db.dao.DataEvent.MARKED_DELETED &&
        zs != null) {
      // Mark zs association object cascade-deleted.
      outer: for (com.poesys.db.memcached_test.ITestZ value : zs) {
        com.poesys.db.pk.AssociationPrimaryKey keys = 
          (com.poesys.db.pk.AssociationPrimaryKey) value.getPrimaryKey();
        for (com.poesys.db.pk.IPrimaryKey key : keys.getKeyListCopy()) {
          if (key.equals(subject.getPrimaryKey())) {
            value.cascadeDelete();
            break outer;
          }
        }
      }
    }
  }

  /**
   * Create the inserters for the TestY and its superclasses.
   */
  private void createInserters() {
    com.poesys.db.dao.IDaoManager manager =
      com.poesys.db.dao.DaoManagerFactory.getManager(getSubsystem());
    final com.poesys.db.dao.IDaoFactory<com.poesys.db.memcached_test.ITestY> testYFactory =
      manager.getFactory("com.poesys.db.memcached_test.TestY",
                         getSubsystem(),
                         2147483647);
    com.poesys.db.dao.insert.IInsertSql<ITestY> sql =
      new com.poesys.db.memcached_test.sql.InsertTestY();
    com.poesys.db.dao.insert.IInsert<ITestY> inserter =
      testYFactory.getInsert(sql, true);
    inserters.add(inserter);
  }
}