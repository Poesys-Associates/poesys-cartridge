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
 * A data-access layer data-transfer object for the TestNaturalParent. This class
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
 *     <li>Immutable</li>
 *     <li>NaturalKey</li>
 *     <li>Persistent</li>
 * </ul>
 * <p>
 * Class is read/write: true
 * </p>
 *
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractTestNaturalParent extends AbstractDto implements ITestNaturalParent {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;
  
  // Setter strategy nested classes for multiple-object associations

  /**
   * Query setter for querying nested children
   *
   * Source: AddToManyChildCollectionProperties
   *
   * @see com.poesys.db.test.sql.QueryChildrenByTestNaturalParent
   */
  private class QueryChildrenSetter 
      extends com.poesys.db.dto.AbstractListSetter<com.poesys.db.test.ITestNaturalChild, ITestNaturalParent, java.util.Collection<com.poesys.db.test.ITestNaturalChild>> {
    private static final long serialVersionUID = 1L;
    private static final int FETCH_SIZE = 10;

    /**
     * Create a QueryChildrenSetter object.
     */
    public QueryChildrenSetter() {
      super("com.poesys.db.test", 2147483647);
    }

    @Override
    protected String getClassName() {
      return com.poesys.db.test.TestNaturalChild.class.getName();
    }

    @Override
    protected int getFetchSize() {
      return FETCH_SIZE;
    }

    @Override
    protected ITestNaturalParent getParametersDto() {
      return AbstractTestNaturalParent.this;
    }

    @Override
    protected com.poesys.db.dao.query.IParameterizedQuerySql<com.poesys.db.test.ITestNaturalChild, ITestNaturalParent> getSql() {
      return new com.poesys.db.test.sql.QueryChildrenByTestNaturalParent();
    }

    @Override
    protected void set(java.util.Collection<com.poesys.db.test.ITestNaturalChild> list) {
      // No status change; this is just filling in the object data.
      children = list;
      // Add the primary keys to the serialized key list if there are any.
      if (children != null) {
        for (com.poesys.db.dto.IDbDto object : children) {
          childrenKeys.add(object.getPrimaryKey());
        }
      }
    }

    @Override
    public boolean isSet() {
      // Object is set if the associated children list is not null
      return children != null;
    }
  }

  /**
   * Read-Object setter for de-serializing nested children collection
   *
   * Source: AddToManyChildCollectionProperties
   *
   * @see com.poesys.db.test.sql.QueryTestNaturalChild
   */
  private class ReadChildrenSetter 
      extends com.poesys.db.dto.AbstractCollectionReadSetter<com.poesys.db.test.ITestNaturalChild> {
    private static final long serialVersionUID = 1L;

    /**
     * Create a ReadChildrenSetter object to read the children collection.
     */
    public ReadChildrenSetter() {
      super("com.poesys.db.test", 2147483647);
    }

    @Override
    protected String getClassName() {
      return com.poesys.db.test.TestNaturalChild.class.getName();
    }

    @Override
    protected java.util.Collection<com.poesys.db.test.ITestNaturalChild> getObjectCollection() {
      return children;
    }

    @Override
    protected java.util.List<IPrimaryKey> getPrimaryKeys() {
      return childrenKeys;
    }

    @Override
    protected com.poesys.db.dao.query.IKeyQuerySql<com.poesys.db.test.ITestNaturalChild> getSql() {
      return new com.poesys.db.test.sql.QueryTestNaturalChild();
    }

    @Override
    protected void set(java.util.Collection<com.poesys.db.test.ITestNaturalChild> collection) {
     children = collection;
    }
  }

  /**
   * Insert setter for inserting nested to-many association children.
   *
   * @see com.poesys.db.test.sql.InsertTestNaturalChild
   */
  private class InsertChildrenSetter extends com.poesys.db.dto.AbstractInsertSetter {
    private static final long serialVersionUID = 1L;
    
    // Association children source: AddToManyChildCollectionProperties

    /**
     * Create an InsertChildrenSetter object.
     */
    public InsertChildrenSetter() {
      super("com.poesys.db.test", 2147483647);
    }

    @Override
    protected String getClassName() {
      return com.poesys.db.test.TestNaturalChild.class.getName();
    }

    @SuppressWarnings("unchecked")
    @Override
    protected java.util.Collection<com.poesys.db.dto.IDbDto> getDtos() {
      java.util.Collection<? extends com.poesys.db.dto.IDbDto> dtos = children;
      return (java.util.Collection<com.poesys.db.dto.IDbDto>)dtos;
    }

    @Override
    protected boolean createKey() {
      return true;
    }
  }

  /**
   * Setter for processing added children and updated children.
   * Deleted children are deleted in the database cascade delete.
   */
   
  /**
   * Query setter for querying nested assocChildren
   *
   * Source: AddToManyChildCollectionProperties
   *
   * @see com.poesys.db.test.sql.QueryAssocChildrenByTestNaturalParent
   */
  private class QueryAssocChildrenSetter 
      extends com.poesys.db.dto.AbstractListSetter<com.poesys.db.test.ITestAssociationChild, ITestNaturalParent, java.util.Collection<com.poesys.db.test.ITestAssociationChild>> {
    private static final long serialVersionUID = 1L;
    private static final int FETCH_SIZE = 10;

    /**
     * Create a QueryAssocChildrenSetter object.
     */
    public QueryAssocChildrenSetter() {
      super("com.poesys.db.test", 2147483647);
    }

    @Override
    protected String getClassName() {
      return com.poesys.db.test.TestAssociationChild.class.getName();
    }

    @Override
    protected int getFetchSize() {
      return FETCH_SIZE;
    }

    @Override
    protected ITestNaturalParent getParametersDto() {
      return AbstractTestNaturalParent.this;
    }

    @Override
    protected com.poesys.db.dao.query.IParameterizedQuerySql<com.poesys.db.test.ITestAssociationChild, ITestNaturalParent> getSql() {
      return new com.poesys.db.test.sql.QueryAssocChildrenByTestNaturalParent();
    }

    @Override
    protected void set(java.util.Collection<com.poesys.db.test.ITestAssociationChild> list) {
      // No status change; this is just filling in the object data.
      assocChildren = list;
      // Add the primary keys to the serialized key list if there are any.
      if (assocChildren != null) {
        for (com.poesys.db.dto.IDbDto object : assocChildren) {
          assocChildrenKeys.add(object.getPrimaryKey());
        }
      }
    }

    @Override
    public boolean isSet() {
      // Object is set if the associated assocChildren list is not null
      return assocChildren != null;
    }
  }

  /**
   * Read-Object setter for de-serializing nested assocChildren collection
   *
   * Source: AddToManyChildCollectionProperties
   *
   * @see com.poesys.db.test.sql.QueryTestAssociationChild
   */
  private class ReadAssocChildrenSetter 
      extends com.poesys.db.dto.AbstractCollectionReadSetter<com.poesys.db.test.ITestAssociationChild> {
    private static final long serialVersionUID = 1L;

    /**
     * Create a ReadAssocChildrenSetter object to read the assocChildren collection.
     */
    public ReadAssocChildrenSetter() {
      super("com.poesys.db.test", 2147483647);
    }

    @Override
    protected String getClassName() {
      return com.poesys.db.test.TestAssociationChild.class.getName();
    }

    @Override
    protected java.util.Collection<com.poesys.db.test.ITestAssociationChild> getObjectCollection() {
      return assocChildren;
    }

    @Override
    protected java.util.List<IPrimaryKey> getPrimaryKeys() {
      return assocChildrenKeys;
    }

    @Override
    protected com.poesys.db.dao.query.IKeyQuerySql<com.poesys.db.test.ITestAssociationChild> getSql() {
      return new com.poesys.db.test.sql.QueryTestAssociationChild();
    }

    @Override
    protected void set(java.util.Collection<com.poesys.db.test.ITestAssociationChild> collection) {
     assocChildren = collection;
    }
  }

  /**
   * Insert setter for inserting nested to-many association assocChildren.
   *
   * @see com.poesys.db.test.sql.InsertTestAssociationChild
   */
  private class InsertAssocChildrenSetter extends com.poesys.db.dto.AbstractInsertSetter {
    private static final long serialVersionUID = 1L;
    
    // Association assocChildren source: AddToManyChildCollectionProperties

    /**
     * Create an InsertAssocChildrenSetter object.
     */
    public InsertAssocChildrenSetter() {
      super("com.poesys.db.test", 2147483647);
    }

    @Override
    protected String getClassName() {
      return com.poesys.db.test.TestAssociationChild.class.getName();
    }

    @SuppressWarnings("unchecked")
    @Override
    protected java.util.Collection<com.poesys.db.dto.IDbDto> getDtos() {
      java.util.Collection<? extends com.poesys.db.dto.IDbDto> dtos = assocChildren;
      return (java.util.Collection<com.poesys.db.dto.IDbDto>)dtos;
    }

    @Override
    protected boolean createKey() {
      return true;
    }
  }

  /**
   * Setter for processing added assocChildren and updated assocChildren.
   * Deleted assocChildren are deleted in the database cascade delete.
   */
   
  private class UpdateAssocChildrenSetter 
      extends com.poesys.db.dto.AbstractProcessNestedObjects<com.poesys.db.test.ITestAssociationChild, java.util.Collection<com.poesys.db.test.ITestAssociationChild>> {
    private static final long serialVersionUID = 1L;
    private static final int BATCH_SIZE = 100;

    /**
     * Create an UpdateAssocChildrenSetter object.
     */
    public UpdateAssocChildrenSetter() {
      super("com.poesys.db.test", 2147483647);
    }

    @Override
    protected void doChanged(java.sql.Connection connection, java.util.Collection<com.poesys.db.test.ITestAssociationChild> dtos)
        throws java.sql.SQLException, com.poesys.db.BatchException, com.poesys.db.ConstraintViolationException,
        com.poesys.db.dto.DtoStatusException {
      // assocChildren source: AddToManyChildCollectionProperties
      // Immutable: false
      com.poesys.db.dao.IDaoManager manager = 
        com.poesys.db.dao.DaoManagerFactory.getManager(subsystem);

      com.poesys.db.dao.IDaoFactory<com.poesys.db.test.ITestAssociationChild> factory = 
        manager.getFactory(com.poesys.db.test.TestAssociationChild.class.getName(), subsystem, 2147483647);
      com.poesys.db.dao.update.IUpdateBatch<com.poesys.db.test.ITestAssociationChild> updater =
        factory.getUpdateBatch(new com.poesys.db.test.sql.UpdateTestAssociationChild());

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
    protected void doDeleted(java.sql.Connection connection, java.util.Collection<com.poesys.db.test.ITestAssociationChild> dtos)
        throws java.sql.SQLException, com.poesys.db.BatchException, com.poesys.db.ConstraintViolationException,
        com.poesys.db.dto.DtoStatusException {
      com.poesys.db.dao.IDaoManager manager = 
        com.poesys.db.dao.DaoManagerFactory.getManager(subsystem);
      com.poesys.db.dao.IDaoFactory<com.poesys.db.test.ITestAssociationChild> factory = 
        manager.getFactory(com.poesys.db.test.TestAssociationChild.class.getName(), subsystem, 2147483647);
      com.poesys.db.dao.delete.IDeleteBatch<com.poesys.db.test.ITestAssociationChild> dao = 
        factory.getDeleteBatch(new com.poesys.db.test.sql.DeleteTestAssociationChild());
      dao.delete(connection, dtos, BATCH_SIZE);
    }

    @Override
    protected void doNew(java.sql.Connection connection, java.util.Collection<com.poesys.db.test.ITestAssociationChild> dtos)
        throws java.sql.SQLException, com.poesys.db.BatchException, com.poesys.db.ConstraintViolationException,
        com.poesys.db.dto.DtoStatusException {
      com.poesys.db.dao.IDaoManager manager = 
        com.poesys.db.dao.DaoManagerFactory.getManager(subsystem);


      com.poesys.db.dao.IDaoFactory<com.poesys.db.test.ITestAssociationChild> factory = 
        manager.getFactory(com.poesys.db.test.TestAssociationChild.class.getName(), subsystem, 2147483647);
      com.poesys.db.dao.insert.IInsertBatch<com.poesys.db.test.ITestAssociationChild> inserter =
        factory.getInsertBatch(new com.poesys.db.test.sql.InsertTestAssociationChild());


      // Insert the object of the current class after enabling nested inserts,
      // which will allow connecting up linked objects to any of the inserted
      // classes.
      for (com.poesys.db.dto.IDbDto dto : dtos) {
        dto.setSuppressNestedInserts(false);
      }
      inserter.insert(connection, dtos, dtos.size() / 2);
    }

    @Override
    protected java.util.Collection<com.poesys.db.test.ITestAssociationChild> getDtos() {
      return assocChildren;
    }
    
    @Override
    protected String getClassName() {
      return com.poesys.db.test.TestAssociationChild.class.getName();
    }
  }

  /**
   * Create an empty TestNaturalParent for use in building a new object. The 
   * concrete subclass must call this constructor.
   */
  public AbstractTestNaturalParent() {
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
    
    // Add the many-to-many collection setters for the children property.
    querySetters.add(new QueryChildrenSetter());
    readObjectSetters.add(new ReadChildrenSetter());
    insertSetters.add(new InsertChildrenSetter());
    
    // Add the many-to-many collection setters for the assocChildren property.
    querySetters.add(new QueryAssocChildrenSetter());
    readObjectSetters.add(new ReadAssocChildrenSetter());
    insertSetters.add(new InsertAssocChildrenSetter());
    postSetters.add(new UpdateAssocChildrenSetter());
  }

  /**
   * Create a TestNaturalParent. The concrete subclass must call this constructor.
   *
   * @param key the primary key of the TestNaturalParent
   * @param key1 
   * @param key2 
   */
  public AbstractTestNaturalParent(IPrimaryKey key, java.lang.Long key1, java.lang.Long key2) {
    this.key = key;

    this.key1 = key1;

    if (key1 == null) {
      throw new com.poesys.db.InvalidParametersException("key1 is required for " + key.getValueList());
    }
    
    this.key2 = key2;

    if (key2 == null) {
      throw new com.poesys.db.InvalidParametersException("key2 is required for " + key.getValueList());
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
    
    // Add the many-to-many collection setters for the children property.
    querySetters.add(new QueryChildrenSetter());
    readObjectSetters.add(new ReadChildrenSetter());
    insertSetters.add(new InsertChildrenSetter());
    
    // Add the many-to-many collection setters for the assocChildren property.
    querySetters.add(new QueryAssocChildrenSetter());
    readObjectSetters.add(new ReadAssocChildrenSetter());
    insertSetters.add(new InsertAssocChildrenSetter());
    postSetters.add(new UpdateAssocChildrenSetter());
    abstractClass = false;
    createInserters();
  }

  @Override
  public boolean equals(Object arg0) {
    AbstractTestNaturalParent other = (AbstractTestNaturalParent)arg0;
    return other.key.equals(key);
  }

  @Override
  public int hashCode() {
    return key.hashCode();
  }

  @Override
  public int compareTo(com.poesys.db.dto.IDbDto o) {
    AbstractTestNaturalParent other = (AbstractTestNaturalParent)o;
    // Sort on the key. Same semantics as equals and hashCode().
    return other.key.compareTo(key);
  }
  
  @Override
  public String getSubsystem() {
    return "com.poesys.db.test";
  }

  @Override
  public void markChildrenDeleted() throws DtoStatusException {
    // Only process deletes if child exists.
    if (children != null) {
      for (com.poesys.db.dto.IDbDto dto : children) {
        dto.cascadeDelete();
      }
    }
    // Only process deletes if child exists.
    if (assocChildren != null) {
      for (com.poesys.db.dto.IDbDto dto : assocChildren) {
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
   * 
   *
   * Added by AddNaturalKeyProperties
   * Class is read/write: true
   * Class is immutable: true
   * Property is read/write: false
   * Property is lazy: false
   */
  private java.lang.Long key1;
  
  /**
   * Get an object of java.lang.Long.
   *
   * Source: AddNaturalKeyProperties
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
   * 
   *
   * Added by AddNaturalKeyProperties
   * Class is read/write: true
   * Class is immutable: true
   * Property is read/write: false
   * Property is lazy: false
   */
  private java.lang.Long key2;
  
  /**
   * Get an object of java.lang.Long.
   *
   * Source: AddNaturalKeyProperties
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
   * Nested property children
   *
   * 
   *
   * Added by AddToManyChildCollectionProperties
   * Class is read/write: true
   * Class is immutable: true
   * Property is read/write: true
   * Property is lazy: false
   */
  // Doesn't serialize; package access allows proxy to set on readObject()
  transient java.util.Collection<com.poesys.db.test.ITestNaturalChild> children;
  // Ordered list of keys of the objects in the children list
  java.util.List<com.poesys.db.pk.IPrimaryKey> childrenKeys = 
    new java.util.ArrayList<com.poesys.db.pk.IPrimaryKey>();
  
  /**
   * Get a collection of com.poesys.db.test.ITestNaturalChild.
   *
   * Source: AddToManyChildCollectionProperties
   * 
   * @return a java.util.Collection<com.poesys.db.test.ITestNaturalChild>
   */

  public java.util.Collection<com.poesys.db.test.ITestNaturalChild> getChildren() {
    return children;
  }

  /**
   * Clear the children data member; override in proxy if lazily loaded,
   * otherwise this method does nothing.
   */
  public void clearChildren() {
    // Override in proxy if lazily loaded; otherwise does nothing
  }
  /**
   * Nested property assocChildren
   *
   * 
   *
   * Added by AddToManyChildCollectionProperties
   * Class is read/write: true
   * Class is immutable: true
   * Property is read/write: true
   * Property is lazy: false
   */
  // Doesn't serialize; package access allows proxy to set on readObject()
  transient java.util.Collection<com.poesys.db.test.ITestAssociationChild> assocChildren;
  // Ordered list of keys of the objects in the assocChildren list
  java.util.List<com.poesys.db.pk.IPrimaryKey> assocChildrenKeys = 
    new java.util.ArrayList<com.poesys.db.pk.IPrimaryKey>();
  
  /**
   * Get a collection of com.poesys.db.test.ITestAssociationChild.
   *
   * Source: AddToManyChildCollectionProperties
   * 
   * @return a java.util.Collection<com.poesys.db.test.ITestAssociationChild>
   */

  public java.util.Collection<com.poesys.db.test.ITestAssociationChild> getAssocChildren() {
    return assocChildren;
  }

  /**
   * Clear the assocChildren data member; override in proxy if lazily loaded,
   * otherwise this method does nothing.
   */
  public void clearAssocChildren() {
    // Override in proxy if lazily loaded; otherwise does nothing
  }

  @Override
  public void update(com.poesys.db.dto.ISubject subject,
                     com.poesys.db.dao.DataEvent event)
      throws com.poesys.db.dto.DtoStatusException {

    // Clean up children.
    if (subject != null && subject instanceof com.poesys.db.test.ITestNaturalChild && 
        event == com.poesys.db.dao.DataEvent.DELETE &&
        children != null) {
      // Delete to-many children child from collection
      children.remove(subject);
      subject.detach(this, com.poesys.db.dao.DataEvent.MARKED_DELETED);
      subject.detach(this, com.poesys.db.dao.DataEvent.DELETE);
    }

    // Clean up assocChildren.
    if (subject != null && subject instanceof com.poesys.db.test.ITestAssociationChild && 
        event == com.poesys.db.dao.DataEvent.DELETE &&
        assocChildren != null) {
      // Delete to-many assocChildren child from collection
      assocChildren.remove(subject);
      subject.detach(this, com.poesys.db.dao.DataEvent.MARKED_DELETED);
      subject.detach(this, com.poesys.db.dao.DataEvent.DELETE);
    }
  }

  /**
   * Create the inserters for the TestNaturalParent and its superclasses.
   */
  private void createInserters() {
    com.poesys.db.dao.IDaoManager manager =
      com.poesys.db.dao.DaoManagerFactory.getManager(getSubsystem());
    final com.poesys.db.dao.IDaoFactory<com.poesys.db.test.ITestNaturalParent> testNaturalParentFactory =
      manager.getFactory("com.poesys.db.test.TestNaturalParent",
                         getSubsystem(),
                         2147483647);
    com.poesys.db.dao.insert.IInsertSql<ITestNaturalParent> sql =
      new com.poesys.db.test.sql.InsertTestNaturalParent();
    com.poesys.db.dao.insert.IInsert<ITestNaturalParent> inserter =
      testNaturalParentFactory.getInsert(sql, true);
    inserters.add(inserter);
  }
}