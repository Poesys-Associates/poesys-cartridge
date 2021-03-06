/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: AbstractDbDtoProxy.vsl

package com.poesys.db.memcached_test;


import com.poesys.db.dto.AbstractLazyLoadingDtoProxy;


/**
 * <p>
 * A data-access layer data-transfer object (DTO) lazy-loading proxy for the 
 * TestNaturalParent. This class is an abstract class that contains AndroMDA 
 * generated code; change nothing in this class. Instead, override any methods 
 * in the concrete subclass generated in the same package. AndroMDA will 
 * overwrite this class each time you run it but will never overwrite the concrete subclass.
 * </p>
 * 
 *
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractTestNaturalParentProxy extends AbstractLazyLoadingDtoProxy implements ITestNaturalParent {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;
  


  /**
   * Read-Object setter for de-serializing nested children collection
   *
   * Source: AddToManyChildCollectionProperties
   *
   * @see com.poesys.db.memcached_test.sql.QueryTestNaturalChild
   */
  private class ReadChildrenSetter 
      extends com.poesys.db.dto.AbstractCollectionReadSetter<com.poesys.db.memcached_test.ITestNaturalChild> {
    private static final long serialVersionUID = 1L;

    /**
     * Create a ReadChildrenSetter object to read the children collection.
     */
    public ReadChildrenSetter() {
      super("com.poesys.db.memcached_test", 2147483647);
    }

    @Override
    protected String getClassName() {
      return com.poesys.db.memcached_test.TestNaturalChild.class.getName();
    }

    @Override
    protected java.util.Collection<com.poesys.db.memcached_test.ITestNaturalChild> getObjectCollection() {
      java.util.Collection<com.poesys.db.memcached_test.ITestNaturalChild> children =  ((com.poesys.db.memcached_test.TestNaturalParent)dto).getChildren();
      return children;
    }

    @Override
    protected java.util.List<com.poesys.db.pk.IPrimaryKey> getPrimaryKeys() {
      return ((com.poesys.db.memcached_test.TestNaturalParent)dto).childrenKeys;
    }

    @Override
    protected com.poesys.db.dao.query.IKeyQuerySql<com.poesys.db.memcached_test.ITestNaturalChild> getSql() {
      return new com.poesys.db.memcached_test.sql.QueryTestNaturalChild();
    }

    @Override
    protected void set(java.util.Collection<com.poesys.db.memcached_test.ITestNaturalChild> collection) {
      ((com.poesys.db.memcached_test.TestNaturalParent)dto).children = collection;
    }
  }



  /**
   * Read-Object setter for de-serializing nested assocChildren collection
   *
   * Source: AddToManyChildCollectionProperties
   *
   * @see com.poesys.db.memcached_test.sql.QueryTestAssociationChild
   */
  private class ReadAssocChildrenSetter 
      extends com.poesys.db.dto.AbstractCollectionReadSetter<com.poesys.db.memcached_test.ITestAssociationChild> {
    private static final long serialVersionUID = 1L;

    /**
     * Create a ReadAssocChildrenSetter object to read the assocChildren collection.
     */
    public ReadAssocChildrenSetter() {
      super("com.poesys.db.memcached_test", 2147483647);
    }

    @Override
    protected String getClassName() {
      return com.poesys.db.memcached_test.TestAssociationChild.class.getName();
    }

    @Override
    protected java.util.Collection<com.poesys.db.memcached_test.ITestAssociationChild> getObjectCollection() {
      java.util.Collection<com.poesys.db.memcached_test.ITestAssociationChild> assocChildren =  ((com.poesys.db.memcached_test.TestNaturalParent)dto).getAssocChildren();
      return assocChildren;
    }

    @Override
    protected java.util.List<com.poesys.db.pk.IPrimaryKey> getPrimaryKeys() {
      return ((com.poesys.db.memcached_test.TestNaturalParent)dto).assocChildrenKeys;
    }

    @Override
    protected com.poesys.db.dao.query.IKeyQuerySql<com.poesys.db.memcached_test.ITestAssociationChild> getSql() {
      return new com.poesys.db.memcached_test.sql.QueryTestAssociationChild();
    }

    @Override
    protected void set(java.util.Collection<com.poesys.db.memcached_test.ITestAssociationChild> collection) {
      ((com.poesys.db.memcached_test.TestNaturalParent)dto).assocChildren = collection;
    }
  }

  /**
   * Create a TestNaturalParentProxy. The concrete subclass must call this constructor.
   *
   * @param dto the DTO to proxy
   */
  public AbstractTestNaturalParentProxy(TestNaturalParent dto) {
    super(dto);

    // Setter arrays
    readObjectSetters = new java.util.concurrent.CopyOnWriteArrayList<com.poesys.db.dto.ISet>();
    readObjectSetters.add(new ReadChildrenSetter());
    readObjectSetters.add(new ReadAssocChildrenSetter());
  }

  @Override
  public boolean equals(Object obj) {
    AbstractTestNaturalParentProxy otherProxy = (AbstractTestNaturalParentProxy)obj;
    return ((TestNaturalParent)dto).equals(otherProxy.dto);
  }

  @Override
  public int hashCode() {
    return ((TestNaturalParent)dto).hashCode();
  }

  // Local properties (attributes, associations, and association classes)

  /**
   * Get an object of java.lang.Long
   *
   * Source: AddNaturalKeyProperties
   * Lazy: false
   * 
   * @return a java.lang.Long
   */
  public java.lang.Long getKey1() {
    return ((TestNaturalParent)dto).getKey1();
  }

  /**
   * Get an object of java.lang.Long
   *
   * Source: AddNaturalKeyProperties
   * Lazy: false
   * 
   * @return a java.lang.Long
   */
  public java.lang.Long getKey2() {
    return ((TestNaturalParent)dto).getKey2();
  }

  /**
   * Get a collection of com.poesys.db.memcached_test.ITestNaturalChild
   *
   * Source: AddToManyChildCollectionProperties
   * Lazy: false
   * 
   * @return a java.util.Collection<com.poesys.db.memcached_test.ITestNaturalChild>
   */
  public java.util.Collection<com.poesys.db.memcached_test.ITestNaturalChild> getChildren() {
    return ((TestNaturalParent)dto).getChildren();
  }

  /**
   * Get a collection of com.poesys.db.memcached_test.ITestAssociationChild
   *
   * Source: AddToManyChildCollectionProperties
   * Lazy: false
   * 
   * @return a java.util.Collection<com.poesys.db.memcached_test.ITestAssociationChild>
   */
  public java.util.Collection<com.poesys.db.memcached_test.ITestAssociationChild> getAssocChildren() {
    return ((TestNaturalParent)dto).getAssocChildren();
  }

  public void markChildrenDeleted() throws com.poesys.db.dto.DtoStatusException {
  	((TestNaturalParent)dto).markChildrenDeleted();
  }
}