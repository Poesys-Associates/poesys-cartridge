/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: AbstractDbDto.vsl

package com.poesys.db.test;


import com.poesys.db.pk.IPrimaryKey;

import com.poesys.db.dto.AbstractDto;
import com.poesys.db.dto.DtoStatusException;


/**
 * <p>
 * A data-access layer data-transfer object for the TestNaturalChild. This class
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
public abstract class AbstractTestNaturalChild extends AbstractDto implements ITestNaturalChild {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;
  // Setter strategy nested classes for single-object associations
  
  /**
   * Nested class that manages the naturalParent association data
   *
   * Source: AddToOneAssociationRequiredObjectProperties
   *
   * @author Poesys/DB Cartridge
   */
  private class QueryNaturalParentSetter extends com.poesys.db.dto.AbstractObjectSetter<com.poesys.db.test.ITestNaturalParent> {
    private static final long serialVersionUID = 1L;
    
    /**
     * Create a QueryNaturalParentSetter object.
     */
    public QueryNaturalParentSetter() {
      super("com.poesys.db.test", 2147483647);
    }

    @Override
    protected String getClassName() {
      return com.poesys.db.test.TestNaturalParent.class.getName();
    }

    @Override
    protected IPrimaryKey getKey() {
      return naturalParentKey;
    }

    @Override
    protected com.poesys.db.dao.query.IKeyQuerySql<com.poesys.db.test.ITestNaturalParent> getSql() {
      return new com.poesys.db.test.sql.QueryTestNaturalParent();
    }

    @Override
    protected void set(com.poesys.db.test.ITestNaturalParent dto) {
      // No status change, this is just filling in the object data.
      naturalParent = dto;
    }

    @Override
    public boolean isSet() {
      // Object is set if the associated naturalParent is not null
      return naturalParent != null;
    }
  }

  /**
   * Insert setter for inserting nested object property naturalParent.
   */
  private class InsertNaturalParentSetter 
      extends com.poesys.db.dto.AbstractInsertSetter {
    // Property naturalParent source: AddToOneAssociationRequiredObjectProperties
    private static final long serialVersionUID = 1L;

    /**
     * Create an InsertNaturalParentSetter object.
     */
    public InsertNaturalParentSetter() {
      super("com.poesys.db.test", 2147483647);
    }

    @Override
    protected String getClassName() {
      return com.poesys.db.test.TestNaturalParent.class.getName();
    }

    @SuppressWarnings("unchecked")
    @Override
    protected java.util.Collection<com.poesys.db.dto.IDbDto> getDtos() {
      java.util.ArrayList<com.poesys.db.dto.IDbDto> array =
        new java.util.ArrayList<com.poesys.db.dto.IDbDto>(1);
      array.add(naturalParent);
      java.util.Collection<? extends com.poesys.db.dto.IDbDto> dtos = array;
      return (java.util.Collection<com.poesys.db.dto.IDbDto>)dtos;
    }

    @Override
    protected boolean createKey() {
      // Key type: NaturalKey
      return true;
    }
  }

  /**
   * Setter for processing added naturalParent, updated naturalParent, and 
   * deleted naturalParent. 
   */
   
  private class UpdateNaturalParentSetter 
      extends com.poesys.db.dto.AbstractProcessNestedObject<com.poesys.db.test.ITestNaturalParent> {
    private static final long serialVersionUID = 1L;

    /**
     * Create an UpdateNaturalParentSetter object.
     */
    public UpdateNaturalParentSetter() {
      super("com.poesys.db.test", 2147483647);
    }

    @Override
    protected void doChanged(java.sql.Connection connection, com.poesys.db.test.ITestNaturalParent dto)
        throws java.sql.SQLException, com.poesys.db.BatchException, com.poesys.db.ConstraintViolationException,
        com.poesys.db.dto.DtoStatusException {
        // naturalParent source: AddToOneAssociationRequiredObjectProperties
        // Immutable: true
    }
    
    @Override
    protected void doDeleted(java.sql.Connection connection, com.poesys.db.test.ITestNaturalParent dto)
        throws java.sql.SQLException, com.poesys.db.BatchException, com.poesys.db.ConstraintViolationException,
        com.poesys.db.dto.DtoStatusException {
      com.poesys.db.dao.IDaoManager manager = 
        com.poesys.db.dao.DaoManagerFactory.getManager(subsystem);
      com.poesys.db.dao.IDaoFactory<com.poesys.db.test.ITestNaturalParent> factory = 
        manager.getFactory(com.poesys.db.test.TestNaturalParent.class.getName(), subsystem, 2147483647);
      com.poesys.db.dao.delete.IDelete<com.poesys.db.test.ITestNaturalParent> dao = 
        factory.getDelete(new com.poesys.db.test.sql.DeleteTestNaturalParent());
      dao.delete(connection, dto);
    }

    @Override
    protected void doNew(java.sql.Connection connection, com.poesys.db.test.ITestNaturalParent dto)
        throws java.sql.SQLException, com.poesys.db.BatchException, com.poesys.db.ConstraintViolationException,
        com.poesys.db.dto.DtoStatusException {
      com.poesys.db.dao.IDaoManager manager = 
        com.poesys.db.dao.DaoManagerFactory.getManager(subsystem);
      com.poesys.db.dao.IDaoFactory<com.poesys.db.test.ITestNaturalParent> factory = 
        manager.getFactory(com.poesys.db.test.TestNaturalParent.class.getName(), subsystem, 2147483647);
      com.poesys.db.dao.insert.IInsert<com.poesys.db.test.ITestNaturalParent> inserter =
        factory.getInsert(new com.poesys.db.test.sql.InsertTestNaturalParent(), createKey());


      // Insert the superclass objects from the root down. Suppress nested
      // inserts for the superclasses, wait until the concrete class. Also set 
      // pre-insert suppression off to have the root insert linked, to-one class
      // objects.
      dto.setSuppressNestedInserts(true);
      dto.setSuppressNestedPreInserts(false);

      // Suppress inserts in concrete class.
      dto.setSuppressNestedPreInserts(true);
      
      // Insert the object of the current class after enabling nested inserts,
      // which will allow connecting up linked objects to any of the inserted
      // classes.
      dto.setSuppressNestedInserts(false);
      inserter.insert(connection, dto);
    }

    @Override
    protected com.poesys.db.test.ITestNaturalParent getDto() {
      return naturalParent;
    }
    
    @Override
    protected String getClassName() {
      return com.poesys.db.test.TestNaturalParent.class.getName();
    }

    @Override
    protected boolean createKey() {
      // Key type: NaturalKey
      return true;
    }
  }

  /**
   * Foreign key object used by QueryNaturalParentSetter nested class to query object
   */
  private IPrimaryKey naturalParentKey;
  
  /**
   * Set the foreign key naturalParentKey. This has package access to enable
   * the subsystem factory getData method to call this method to set the key
   * by creating it from the queried result set.
   *
   * @param naturalParentKey the foreign key
   */
  void setNaturalParentKey(IPrimaryKey naturalParentKey) {
    this.naturalParentKey = naturalParentKey;
  }


  /**
   * Create an empty TestNaturalChild for use in building a new object. The 
   * concrete subclass must call this constructor.
   */
  public AbstractTestNaturalChild() {
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

    // Add the setters for the naturalParent property.
    querySetters.add(new QueryNaturalParentSetter());
    preSetters.add(new InsertNaturalParentSetter());
  }

  /**
   * Create a TestNaturalChild. The concrete subclass must call this constructor.
   *
   * @param key the primary key of the TestNaturalChild
   * @param key1 composite super-key attribute that uniquely identifies child combined with child sub-key and any other parent super-keys
   * @param key2 composite super-key attribute that uniquely identifies child combined with child sub-key and any other parent super-keys
   * @param subkey 
   */
  public AbstractTestNaturalChild(IPrimaryKey key, java.lang.Long key1, java.lang.Long key2, java.lang.Long subkey) {
    this.key = key;

    this.key1 = key1;

    if (key1 == null) {
      throw new com.poesys.db.InvalidParametersException("key1 is required for " + key.getValueList());
    }
    
    this.key2 = key2;

    if (key2 == null) {
      throw new com.poesys.db.InvalidParametersException("key2 is required for " + key.getValueList());
    }
    
    this.subkey = subkey;

    if (subkey == null) {
      throw new com.poesys.db.InvalidParametersException("subkey is required for " + key.getValueList());
    }
    
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
    
    // Add the setters for the naturalParent property.
    querySetters.add(new QueryNaturalParentSetter());
    insertSetters.add(new InsertNaturalParentSetter());
    preSetters.add(new InsertNaturalParentSetter());
    
    abstractClass = false;
    createInserters();
  }

  @Override
  public boolean equals(Object arg0) {
    AbstractTestNaturalChild other = (AbstractTestNaturalChild)arg0;
    return other.key.equals(key);
  }

  @Override
  public int hashCode() {
    return key.hashCode();
  }

  @Override
  public int compareTo(com.poesys.db.dto.IDbDto o) {
    AbstractTestNaturalChild other = (AbstractTestNaturalChild)o;
    // Sort on the key. Same semantics as equals and hashCode().
    return other.key.compareTo(key);
  }
  
  @Override
  public String getSubsystem() {
    return "com.poesys.db.test";
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
	  java.util.ResourceBundle.getBundle("com.poesys.db.test");
    String subsystem =
      "com.poesys.db.test"
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
   * Nested property key1
   *
   * <p>
   * Composite super-key attribute that uniquely identifies child combined with child sub-key and any other parent super-keys
   * </p>
   *
   * Added by AddNaturalKeyProperties + AddParentKeyAttributes
   * Class is read/write: true
   * Class is immutable: true
   * Property is read/write: false
   * Property is lazy: false
   */
  private java.lang.Long key1;
  
  /**
   * Get an object of java.lang.Long.
   *
   * Source: AddNaturalKeyProperties + AddParentKeyAttributes
   * 
   * @return a java.lang.Long
   */

  public java.lang.Long getKey1() {
    return key1;
  }

  /**
   * Clear the key1 data member; override in proxy if lazily loaded,
   * otherwise this method does nothing.
   */
  public void clearKey1() {
    // Override in proxy if lazily loaded; otherwise does nothing
  }
  /**
   * Nested property key2
   *
   * <p>
   * Composite super-key attribute that uniquely identifies child combined with child sub-key and any other parent super-keys
   * </p>
   *
   * Added by AddNaturalKeyProperties + AddParentKeyAttributes
   * Class is read/write: true
   * Class is immutable: true
   * Property is read/write: false
   * Property is lazy: false
   */
  private java.lang.Long key2;
  
  /**
   * Get an object of java.lang.Long.
   *
   * Source: AddNaturalKeyProperties + AddParentKeyAttributes
   * 
   * @return a java.lang.Long
   */

  public java.lang.Long getKey2() {
    return key2;
  }

  /**
   * Clear the key2 data member; override in proxy if lazily loaded,
   * otherwise this method does nothing.
   */
  public void clearKey2() {
    // Override in proxy if lazily loaded; otherwise does nothing
  }
  /**
   * Nested property subkey
   *
   * 
   *
   * Added by AddExplicitSubKeyProperties + addNaturalSubkeyOnClass
   * Class is read/write: true
   * Class is immutable: true
   * Property is read/write: false
   * Property is lazy: false
   */
  private java.lang.Long subkey;
  
  /**
   * Get an object of java.lang.Long.
   *
   * Source: AddExplicitSubKeyProperties + addNaturalSubkeyOnClass
   * 
   * @return a java.lang.Long
   */

  public java.lang.Long getSubkey() {
    return subkey;
  }

  /**
   * Clear the subkey data member; override in proxy if lazily loaded,
   * otherwise this method does nothing.
   */
  public void clearSubkey() {
    // Override in proxy if lazily loaded; otherwise does nothing
  }
  /**
   * Nested property naturalParent
   *
   * 
   *
   * Added by AddToOneAssociationRequiredObjectProperties
   * Class is read/write: true
   * Class is immutable: true
   * Property is read/write: true
   * Property is lazy: false
   */
  private com.poesys.db.test.ITestNaturalParent naturalParent;
  
  /**
   * Get an object of com.poesys.db.test.ITestNaturalParent.
   *
   * Source: AddToOneAssociationRequiredObjectProperties
   * 
   * @return a com.poesys.db.test.ITestNaturalParent
   */

  public com.poesys.db.test.ITestNaturalParent getNaturalParent() {
    return naturalParent;
  }

  /**
   * Clear the naturalParent data member; override in proxy if lazily loaded,
   * otherwise this method does nothing.
   */
  public void clearNaturalParent() {
    // Override in proxy if lazily loaded; otherwise does nothing
  }

  @Override
  public void update(com.poesys.db.dto.ISubject subject,
                     com.poesys.db.dao.DataEvent event)
      throws com.poesys.db.dto.DtoStatusException {
  }

  /**
   * Create the inserters for the TestNaturalChild and its superclasses.
   */
  private void createInserters() {
    com.poesys.db.dao.IDaoManager manager =
      com.poesys.db.dao.DaoManagerFactory.getManager(getSubsystem());
    final com.poesys.db.dao.IDaoFactory<com.poesys.db.test.ITestNaturalChild> testNaturalChildFactory =
      manager.getFactory("com.poesys.db.test.TestNaturalChild",
                         getSubsystem(),
                         2147483647);
    com.poesys.db.dao.insert.IInsertSql<ITestNaturalChild> sql =
      new com.poesys.db.test.sql.InsertTestNaturalChild();
    com.poesys.db.dao.insert.IInsert<ITestNaturalChild> inserter =
      testNaturalChildFactory.getInsert(sql, true);
    inserters.add(inserter);
  }
}