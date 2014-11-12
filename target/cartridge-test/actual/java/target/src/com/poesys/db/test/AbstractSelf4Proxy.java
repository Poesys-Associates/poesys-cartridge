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
 * Self4. This class is an abstract class that contains AndroMDA 
 * generated code; change nothing in this class. Instead, override any methods 
 * in the concrete subclass generated in the same package. AndroMDA will 
 * overwrite this class each time you run it but will never overwrite the concrete subclass.
 * </p>
 * 
 *
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractSelf4Proxy extends AbstractLazyLoadingDtoProxy implements ISelf4 {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;
  


  /**
   * Read-Object setter for de-serializing nested parents4 collection
   *
   * Source: TransformToProperty + AddToManyAssociationCollectionProperties
   *
   * @see com.poesys.db.test.sql.QuerySelf4
   */
  private class ReadParents4Setter 
      extends com.poesys.db.dto.AbstractCollectionReadSetter<com.poesys.db.test.ISelf4> {
    private static final long serialVersionUID = 1L;

    /**
     * Create a ReadParents4Setter object to read the parent4 collection.
     */
    public ReadParents4Setter() {
      super("com.poesys.db.test", 2147483647);
    }

    @Override
    protected String getClassName() {
      return com.poesys.db.test.Self4.class.getName();
    }

    @Override
    protected java.util.Collection<com.poesys.db.test.ISelf4> getObjectCollection() {
      java.util.Collection<com.poesys.db.test.ISelf4> parents4 =  ((com.poesys.db.test.Self4)dto).getParents4();
      return parents4;
    }

    @Override
    protected java.util.List<com.poesys.db.pk.IPrimaryKey> getPrimaryKeys() {
      return ((com.poesys.db.test.Self4)dto).parent4Keys;
    }

    @Override
    protected com.poesys.db.dao.query.IKeyQuerySql<com.poesys.db.test.ISelf4> getSql() {
      return new com.poesys.db.test.sql.QuerySelf4();
    }

    @Override
    protected void set(java.util.Collection<com.poesys.db.test.ISelf4> collection) {
      ((com.poesys.db.test.Self4)dto).parents4 = collection;
    }
  }



  /**
   * Read-Object setter for de-serializing nested children4 collection
   *
   * Source: TransformToProperty + AddToManyAssociationCollectionProperties
   *
   * @see com.poesys.db.test.sql.QuerySelf4
   */
  private class ReadChildren4Setter 
      extends com.poesys.db.dto.AbstractCollectionReadSetter<com.poesys.db.test.ISelf4> {
    private static final long serialVersionUID = 1L;

    /**
     * Create a ReadChildren4Setter object to read the child4 collection.
     */
    public ReadChildren4Setter() {
      super("com.poesys.db.test", 2147483647);
    }

    @Override
    protected String getClassName() {
      return com.poesys.db.test.Self4.class.getName();
    }

    @Override
    protected java.util.Collection<com.poesys.db.test.ISelf4> getObjectCollection() {
      java.util.Collection<com.poesys.db.test.ISelf4> children4 =  ((com.poesys.db.test.Self4)dto).getChildren4();
      return children4;
    }

    @Override
    protected java.util.List<com.poesys.db.pk.IPrimaryKey> getPrimaryKeys() {
      return ((com.poesys.db.test.Self4)dto).child4Keys;
    }

    @Override
    protected com.poesys.db.dao.query.IKeyQuerySql<com.poesys.db.test.ISelf4> getSql() {
      return new com.poesys.db.test.sql.QuerySelf4();
    }

    @Override
    protected void set(java.util.Collection<com.poesys.db.test.ISelf4> collection) {
      ((com.poesys.db.test.Self4)dto).children4 = collection;
    }
  }



  /**
   * Read-Object setter for de-serializing nested parents4Self4SelfLinks collection
   *
   * Source: AddAssociationClassCollectionProperties
   *
   * @see com.poesys.db.test.sql.QuerySelf4Self
   */
  private class ReadParents4Self4SelfLinksSetter 
      extends com.poesys.db.dto.AbstractCollectionReadSetter<com.poesys.db.test.ISelf4Self> {
    private static final long serialVersionUID = 1L;

    /**
     * Create a ReadParents4Self4SelfLinksSetter object to read the parents4Self4SelfLinks collection.
     */
    public ReadParents4Self4SelfLinksSetter() {
      super("com.poesys.db.test", 2147483647);
    }

    @Override
    protected String getClassName() {
      return com.poesys.db.test.Self4Self.class.getName();
    }

    @Override
    protected java.util.Collection<com.poesys.db.test.ISelf4Self> getObjectCollection() {
      java.util.Collection<com.poesys.db.test.ISelf4Self> parents4Self4SelfLinks =  ((com.poesys.db.test.Self4)dto).getParents4Self4SelfLinks();
      return parents4Self4SelfLinks;
    }

    @Override
    protected java.util.List<com.poesys.db.pk.IPrimaryKey> getPrimaryKeys() {
      return ((com.poesys.db.test.Self4)dto).parents4Self4SelfLinksKeys;
    }

    @Override
    protected com.poesys.db.dao.query.IKeyQuerySql<com.poesys.db.test.ISelf4Self> getSql() {
      return new com.poesys.db.test.sql.QuerySelf4Self();
    }

    @Override
    protected void set(java.util.Collection<com.poesys.db.test.ISelf4Self> collection) {
      ((com.poesys.db.test.Self4)dto).parents4Self4SelfLinks = collection;
    }
  }



  /**
   * Read-Object setter for de-serializing nested children4Self4SelfLinks collection
   *
   * Source: AddAssociationClassCollectionProperties
   *
   * @see com.poesys.db.test.sql.QuerySelf4Self
   */
  private class ReadChildren4Self4SelfLinksSetter 
      extends com.poesys.db.dto.AbstractCollectionReadSetter<com.poesys.db.test.ISelf4Self> {
    private static final long serialVersionUID = 1L;

    /**
     * Create a ReadChildren4Self4SelfLinksSetter object to read the children4Self4SelfLinks collection.
     */
    public ReadChildren4Self4SelfLinksSetter() {
      super("com.poesys.db.test", 2147483647);
    }

    @Override
    protected String getClassName() {
      return com.poesys.db.test.Self4Self.class.getName();
    }

    @Override
    protected java.util.Collection<com.poesys.db.test.ISelf4Self> getObjectCollection() {
      java.util.Collection<com.poesys.db.test.ISelf4Self> children4Self4SelfLinks =  ((com.poesys.db.test.Self4)dto).getChildren4Self4SelfLinks();
      return children4Self4SelfLinks;
    }

    @Override
    protected java.util.List<com.poesys.db.pk.IPrimaryKey> getPrimaryKeys() {
      return ((com.poesys.db.test.Self4)dto).children4Self4SelfLinksKeys;
    }

    @Override
    protected com.poesys.db.dao.query.IKeyQuerySql<com.poesys.db.test.ISelf4Self> getSql() {
      return new com.poesys.db.test.sql.QuerySelf4Self();
    }

    @Override
    protected void set(java.util.Collection<com.poesys.db.test.ISelf4Self> collection) {
      ((com.poesys.db.test.Self4)dto).children4Self4SelfLinks = collection;
    }
  }

  /**
   * Create a Self4Proxy. The concrete subclass must call this constructor.
   *
   * @param dto the DTO to proxy
   */
  public AbstractSelf4Proxy(Self4 dto) {
    super(dto);

    // Setter arrays
    readObjectSetters = new java.util.concurrent.CopyOnWriteArrayList<com.poesys.db.dto.ISet>();
    readObjectSetters.add(new ReadParents4Setter());
    readObjectSetters.add(new ReadChildren4Setter());
    readObjectSetters.add(new ReadParents4Self4SelfLinksSetter());
    readObjectSetters.add(new ReadChildren4Self4SelfLinksSetter());
  }

  @Override
  public boolean equals(Object obj) {
    AbstractSelf4Proxy otherProxy = (AbstractSelf4Proxy)obj;
    return ((Self4)dto).equals(otherProxy.dto);
  }

  @Override
  public int hashCode() {
    return ((Self4)dto).hashCode();
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
    return ((Self4)dto).getKey1();
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
    return ((Self4)dto).getKey2();
  }

  /**
   * Get a collection of com.poesys.db.test.ISelf4
   *
   * Source: TransformToProperty + AddToManyAssociationCollectionProperties
   * Lazy: false
   * 
   * @return a java.util.Collection<com.poesys.db.test.ISelf4>
   */
  public java.util.Collection<com.poesys.db.test.ISelf4> getParents4() {
    return ((Self4)dto).getParents4();
  }

  /**
   * Get a collection of com.poesys.db.test.ISelf4
   *
   * Source: TransformToProperty + AddToManyAssociationCollectionProperties
   * Lazy: false
   * 
   * @return a java.util.Collection<com.poesys.db.test.ISelf4>
   */
  public java.util.Collection<com.poesys.db.test.ISelf4> getChildren4() {
    return ((Self4)dto).getChildren4();
  }

  /**
   * Get a collection of com.poesys.db.test.ISelf4Self
   *
   * Source: AddAssociationClassCollectionProperties
   * Lazy: false
   * 
   * @return a java.util.Collection<com.poesys.db.test.ISelf4Self>
   */
  public java.util.Collection<com.poesys.db.test.ISelf4Self> getParents4Self4SelfLinks() {
    return ((Self4)dto).getParents4Self4SelfLinks();
  }

  /**
   * Get a collection of com.poesys.db.test.ISelf4Self
   *
   * Source: AddAssociationClassCollectionProperties
   * Lazy: false
   * 
   * @return a java.util.Collection<com.poesys.db.test.ISelf4Self>
   */
  public java.util.Collection<com.poesys.db.test.ISelf4Self> getChildren4Self4SelfLinks() {
    return ((Self4)dto).getChildren4Self4SelfLinks();
  }

  public void markChildrenDeleted() throws com.poesys.db.dto.DtoStatusException {
  	((Self4)dto).markChildrenDeleted();
  }
}