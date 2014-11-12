/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: AbstractDbDtoProxy.vsl

package com.poesys.db.test;


import com.poesys.db.dto.AbstractLazyLoadingDtoProxy;


/**
 * <p>
 * A data-access layer data-transfer object (DTO) lazy-loading proxy for the 
 * TestParent. This class is an abstract class that contains AndroMDA 
 * generated code; change nothing in this class. Instead, override any methods 
 * in the concrete subclass generated in the same package. AndroMDA will 
 * overwrite this class each time you run it but will never overwrite the concrete subclass.
 * </p>
 * 
 *
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractTestParentProxy extends AbstractLazyLoadingDtoProxy implements ITestParent {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;
  


  /**
   * Read-Object setter for de-serializing nested children list
   *
   * Source: AddToManyChildCollectionProperties
   *
   * @see com.poesys.db.test.sql.QueryTestChild
   */
  private class ReadChildrenSetter 
      extends com.poesys.db.dto.AbstractListReadSetter<com.poesys.db.test.ITestChild> {
    private static final long serialVersionUID = 1L;

    /**
     * Create a ReadChildrenSetter object to read the children list.
     */
    public ReadChildrenSetter() {
      super("com.poesys.db.test", 2147483647);
    }

    @Override
    protected String getClassName() {
      return com.poesys.db.test.TestChild.class.getName();
    }

    @Override
    protected java.util.List<com.poesys.db.test.ITestChild> getObjectList() {
      java.util.List<com.poesys.db.test.ITestChild> children =  ((com.poesys.db.test.TestParent)dto).getChildren();
      return children;
    }

    @Override
    protected java.util.List<com.poesys.db.pk.IPrimaryKey> getPrimaryKeys() {
      return ((com.poesys.db.test.TestParent)dto).childrenKeys;
    }

    @Override
    protected com.poesys.db.dao.query.IKeyQuerySql<com.poesys.db.test.ITestChild> getSql() {
      return new com.poesys.db.test.sql.QueryTestChild();
    }

    @Override
    protected void set(java.util.List<com.poesys.db.test.ITestChild> list) {
      ((com.poesys.db.test.TestParent)dto).children = list;
    }
  }

  /**
   * Add a com.poesys.db.test.ITestChild object to the Children collection. The method
   * loads the collection if it is not already in memory.
   *
   * add method #1 (collection property)
   *
   * Source: AddToManyChildCollectionProperties
   * 
   * @param object the com.poesys.db.test.ITestChild object to add to the collection
   */
  public void addChildrenTestChild(com.poesys.db.test.ITestChild object) {
    ((TestParent)dto).addChildrenTestChild(object);
  }



  /**
   * Read-Object setter for de-serializing nested explicitChildren collection
   *
   * Source: AddToManyChildCollectionProperties
   *
   * @see com.poesys.db.test.sql.QueryTestExplicitKeyChild
   */
  private class ReadExplicitChildrenSetter 
      extends com.poesys.db.dto.AbstractCollectionReadSetter<com.poesys.db.test.ITestExplicitKeyChild> {
    private static final long serialVersionUID = 1L;

    /**
     * Create a ReadExplicitChildrenSetter object to read the explicitChildren collection.
     */
    public ReadExplicitChildrenSetter() {
      super("com.poesys.db.test", 2147483647);
    }

    @Override
    protected String getClassName() {
      return com.poesys.db.test.TestExplicitKeyChild.class.getName();
    }

    @Override
    protected java.util.Collection<com.poesys.db.test.ITestExplicitKeyChild> getObjectCollection() {
      java.util.Collection<com.poesys.db.test.ITestExplicitKeyChild> explicitChildren =  ((com.poesys.db.test.TestParent)dto).getExplicitChildren();
      return explicitChildren;
    }

    @Override
    protected java.util.List<com.poesys.db.pk.IPrimaryKey> getPrimaryKeys() {
      return ((com.poesys.db.test.TestParent)dto).explicitChildrenKeys;
    }

    @Override
    protected com.poesys.db.dao.query.IKeyQuerySql<com.poesys.db.test.ITestExplicitKeyChild> getSql() {
      return new com.poesys.db.test.sql.QueryTestExplicitKeyChild();
    }

    @Override
    protected void set(java.util.Collection<com.poesys.db.test.ITestExplicitKeyChild> collection) {
      ((com.poesys.db.test.TestParent)dto).explicitChildren = collection;
    }
  }


  /**
   * Add a com.poesys.db.test.ITestExplicitKeyChild object to the ExplicitChildren collection. The method
   * loads the collection if it is not already in memory.
   *
   * add method #1 (collection property)
   *
   * Source: AddToManyChildCollectionProperties
   * 
   * @param object the com.poesys.db.test.ITestExplicitKeyChild object to add to the collection
   */
  public void addExplicitChildrenTestExplicitKeyChild(com.poesys.db.test.ITestExplicitKeyChild object) {
    ((TestParent)dto).addExplicitChildrenTestExplicitKeyChild(object);
  }

  /**
   * Create a TestParentProxy. The concrete subclass must call this constructor.
   *
   * @param dto the DTO to proxy
   */
  public AbstractTestParentProxy(TestParent dto) {
    super(dto);

    // Setter arrays
    readObjectSetters = new java.util.concurrent.CopyOnWriteArrayList<com.poesys.db.dto.ISet>();
    readObjectSetters.add(new ReadChildrenSetter());
    readObjectSetters.add(new ReadExplicitChildrenSetter());
  }

  @Override
  public boolean equals(Object obj) {
    AbstractTestParentProxy otherProxy = (AbstractTestParentProxy)obj;
    return ((TestParent)dto).equals(otherProxy.dto);
  }

  @Override
  public int hashCode() {
    return ((TestParent)dto).hashCode();
  }

  // Local properties (attributes, associations, and association classes)

  /**
   * Get an object of java.math.BigInteger
   *
   * Source: AddGeneratedKeyProperties
   * Lazy: false
   * 
   * @return a java.math.BigInteger
   */
  public java.math.BigInteger getTestParentId() {
    return ((TestParent)dto).getTestParentId();
  }

  /**
   * Get an object of java.lang.String
   *
   * Source: AddLocalAttributeProperties
   * Lazy: false
   * 
   * @return a java.lang.String
   */
  public java.lang.String getParentOccupation() {
    return ((TestParent)dto).getParentOccupation();
  }

  public void setParentOccupation(java.lang.String parentOccupation)
      {
    ((TestParent)dto).setParentOccupation(parentOccupation);
  }

  /**
   * Get a list of com.poesys.db.test.ITestChild
   *
   * Source: AddToManyChildCollectionProperties
   * Lazy: false
   * 
   * @return a java.util.List<com.poesys.db.test.ITestChild>
   */
  public java.util.List<com.poesys.db.test.ITestChild> getChildren() {
    return ((TestParent)dto).getChildren();
  }

  /**
   * Get a collection of com.poesys.db.test.ITestExplicitKeyChild
   *
   * Source: AddToManyChildCollectionProperties
   * Lazy: false
   * 
   * @return a java.util.Collection<com.poesys.db.test.ITestExplicitKeyChild>
   */
  public java.util.Collection<com.poesys.db.test.ITestExplicitKeyChild> getExplicitChildren() {
    return ((TestParent)dto).getExplicitChildren();
  }

  public void markChildrenDeleted() throws com.poesys.db.dto.DtoStatusException {
  	((TestParent)dto).markChildrenDeleted();
  }
}