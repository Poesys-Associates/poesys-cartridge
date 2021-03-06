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
 * Self2. This class is an abstract class that contains AndroMDA 
 * generated code; change nothing in this class. Instead, override any methods 
 * in the concrete subclass generated in the same package. AndroMDA will 
 * overwrite this class each time you run it but will never overwrite the concrete subclass.
 * </p>
 * 
 *
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractSelf2Proxy extends AbstractLazyLoadingDtoProxy implements ISelf2 {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;
  


  /**
   * Read-Object setter for de-serializing nested parents2 collection
   *
   * Source: TransformToProperty + AddToManyAssociationCollectionProperties
   *
   * @see com.poesys.db.memcached_test.sql.QuerySelf2
   */
  private class ReadParents2Setter 
      extends com.poesys.db.dto.AbstractCollectionReadSetter<com.poesys.db.memcached_test.ISelf2> {
    private static final long serialVersionUID = 1L;

    /**
     * Create a ReadParents2Setter object to read the parents2 collection.
     */
    public ReadParents2Setter() {
      super("com.poesys.db.memcached_test", 2147483647);
    }

    @Override
    protected String getClassName() {
      return com.poesys.db.memcached_test.Self2.class.getName();
    }

    @Override
    protected java.util.Collection<com.poesys.db.memcached_test.ISelf2> getObjectCollection() {
      java.util.Collection<com.poesys.db.memcached_test.ISelf2> parents2 =  ((com.poesys.db.memcached_test.Self2)dto).getParents2();
      return parents2;
    }

    @Override
    protected java.util.List<com.poesys.db.pk.IPrimaryKey> getPrimaryKeys() {
      return ((com.poesys.db.memcached_test.Self2)dto).parents2Keys;
    }

    @Override
    protected com.poesys.db.dao.query.IKeyQuerySql<com.poesys.db.memcached_test.ISelf2> getSql() {
      return new com.poesys.db.memcached_test.sql.QuerySelf2();
    }

    @Override
    protected void set(java.util.Collection<com.poesys.db.memcached_test.ISelf2> collection) {
      ((com.poesys.db.memcached_test.Self2)dto).parents2 = collection;
    }
  }



  /**
   * Read-Object setter for de-serializing nested children2 collection
   *
   * Source: TransformToProperty + AddToManyAssociationCollectionProperties
   *
   * @see com.poesys.db.memcached_test.sql.QuerySelf2
   */
  private class ReadChildren2Setter 
      extends com.poesys.db.dto.AbstractCollectionReadSetter<com.poesys.db.memcached_test.ISelf2> {
    private static final long serialVersionUID = 1L;

    /**
     * Create a ReadChildren2Setter object to read the children2 collection.
     */
    public ReadChildren2Setter() {
      super("com.poesys.db.memcached_test", 2147483647);
    }

    @Override
    protected String getClassName() {
      return com.poesys.db.memcached_test.Self2.class.getName();
    }

    @Override
    protected java.util.Collection<com.poesys.db.memcached_test.ISelf2> getObjectCollection() {
      java.util.Collection<com.poesys.db.memcached_test.ISelf2> children2 =  ((com.poesys.db.memcached_test.Self2)dto).getChildren2();
      return children2;
    }

    @Override
    protected java.util.List<com.poesys.db.pk.IPrimaryKey> getPrimaryKeys() {
      return ((com.poesys.db.memcached_test.Self2)dto).children2Keys;
    }

    @Override
    protected com.poesys.db.dao.query.IKeyQuerySql<com.poesys.db.memcached_test.ISelf2> getSql() {
      return new com.poesys.db.memcached_test.sql.QuerySelf2();
    }

    @Override
    protected void set(java.util.Collection<com.poesys.db.memcached_test.ISelf2> collection) {
      ((com.poesys.db.memcached_test.Self2)dto).children2 = collection;
    }
  }



  /**
   * Read-Object setter for de-serializing nested parents2Self2SelfLinks collection
   *
   * Source: AddAssociationClassCollectionProperties
   *
   * @see com.poesys.db.memcached_test.sql.QuerySelf2Self
   */
  private class ReadParents2Self2SelfLinksSetter 
      extends com.poesys.db.dto.AbstractCollectionReadSetter<com.poesys.db.memcached_test.ISelf2Self> {
    private static final long serialVersionUID = 1L;

    /**
     * Create a ReadParents2Self2SelfLinksSetter object to read the parents2Self2SelfLinks collection.
     */
    public ReadParents2Self2SelfLinksSetter() {
      super("com.poesys.db.memcached_test", 2147483647);
    }

    @Override
    protected String getClassName() {
      return com.poesys.db.memcached_test.Self2Self.class.getName();
    }

    @Override
    protected java.util.Collection<com.poesys.db.memcached_test.ISelf2Self> getObjectCollection() {
      java.util.Collection<com.poesys.db.memcached_test.ISelf2Self> parents2Self2SelfLinks =  ((com.poesys.db.memcached_test.Self2)dto).getParents2Self2SelfLinks();
      return parents2Self2SelfLinks;
    }

    @Override
    protected java.util.List<com.poesys.db.pk.IPrimaryKey> getPrimaryKeys() {
      return ((com.poesys.db.memcached_test.Self2)dto).parents2Self2SelfLinksKeys;
    }

    @Override
    protected com.poesys.db.dao.query.IKeyQuerySql<com.poesys.db.memcached_test.ISelf2Self> getSql() {
      return new com.poesys.db.memcached_test.sql.QuerySelf2Self();
    }

    @Override
    protected void set(java.util.Collection<com.poesys.db.memcached_test.ISelf2Self> collection) {
      ((com.poesys.db.memcached_test.Self2)dto).parents2Self2SelfLinks = collection;
    }
  }



  /**
   * Read-Object setter for de-serializing nested children2Self2SelfLinks collection
   *
   * Source: AddAssociationClassCollectionProperties
   *
   * @see com.poesys.db.memcached_test.sql.QuerySelf2Self
   */
  private class ReadChildren2Self2SelfLinksSetter 
      extends com.poesys.db.dto.AbstractCollectionReadSetter<com.poesys.db.memcached_test.ISelf2Self> {
    private static final long serialVersionUID = 1L;

    /**
     * Create a ReadChildren2Self2SelfLinksSetter object to read the children2Self2SelfLinks collection.
     */
    public ReadChildren2Self2SelfLinksSetter() {
      super("com.poesys.db.memcached_test", 2147483647);
    }

    @Override
    protected String getClassName() {
      return com.poesys.db.memcached_test.Self2Self.class.getName();
    }

    @Override
    protected java.util.Collection<com.poesys.db.memcached_test.ISelf2Self> getObjectCollection() {
      java.util.Collection<com.poesys.db.memcached_test.ISelf2Self> children2Self2SelfLinks =  ((com.poesys.db.memcached_test.Self2)dto).getChildren2Self2SelfLinks();
      return children2Self2SelfLinks;
    }

    @Override
    protected java.util.List<com.poesys.db.pk.IPrimaryKey> getPrimaryKeys() {
      return ((com.poesys.db.memcached_test.Self2)dto).children2Self2SelfLinksKeys;
    }

    @Override
    protected com.poesys.db.dao.query.IKeyQuerySql<com.poesys.db.memcached_test.ISelf2Self> getSql() {
      return new com.poesys.db.memcached_test.sql.QuerySelf2Self();
    }

    @Override
    protected void set(java.util.Collection<com.poesys.db.memcached_test.ISelf2Self> collection) {
      ((com.poesys.db.memcached_test.Self2)dto).children2Self2SelfLinks = collection;
    }
  }

  /**
   * Create a Self2Proxy. The concrete subclass must call this constructor.
   *
   * @param dto the DTO to proxy
   */
  public AbstractSelf2Proxy(Self2 dto) {
    super(dto);

    // Setter arrays
    readObjectSetters = new java.util.concurrent.CopyOnWriteArrayList<com.poesys.db.dto.ISet>();
    readObjectSetters.add(new ReadParents2Setter());
    readObjectSetters.add(new ReadChildren2Setter());
    readObjectSetters.add(new ReadParents2Self2SelfLinksSetter());
    readObjectSetters.add(new ReadChildren2Self2SelfLinksSetter());
  }

  @Override
  public boolean equals(Object obj) {
    AbstractSelf2Proxy otherProxy = (AbstractSelf2Proxy)obj;
    return ((Self2)dto).equals(otherProxy.dto);
  }

  @Override
  public int hashCode() {
    return ((Self2)dto).hashCode();
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
  public java.math.BigInteger getSelf2Id() {
    return ((Self2)dto).getSelf2Id();
  }

  /**
   * Get a collection of com.poesys.db.memcached_test.ISelf2
   *
   * Source: TransformToProperty + AddToManyAssociationCollectionProperties
   * Lazy: false
   * 
   * @return a java.util.Collection<com.poesys.db.memcached_test.ISelf2>
   */
  public java.util.Collection<com.poesys.db.memcached_test.ISelf2> getParents2() {
    return ((Self2)dto).getParents2();
  }

  /**
   * Get a collection of com.poesys.db.memcached_test.ISelf2
   *
   * Source: TransformToProperty + AddToManyAssociationCollectionProperties
   * Lazy: false
   * 
   * @return a java.util.Collection<com.poesys.db.memcached_test.ISelf2>
   */
  public java.util.Collection<com.poesys.db.memcached_test.ISelf2> getChildren2() {
    return ((Self2)dto).getChildren2();
  }

  /**
   * Get a collection of com.poesys.db.memcached_test.ISelf2Self
   *
   * Source: AddAssociationClassCollectionProperties
   * Lazy: false
   * 
   * @return a java.util.Collection<com.poesys.db.memcached_test.ISelf2Self>
   */
  public java.util.Collection<com.poesys.db.memcached_test.ISelf2Self> getParents2Self2SelfLinks() {
    return ((Self2)dto).getParents2Self2SelfLinks();
  }

  /**
   * Get a collection of com.poesys.db.memcached_test.ISelf2Self
   *
   * Source: AddAssociationClassCollectionProperties
   * Lazy: false
   * 
   * @return a java.util.Collection<com.poesys.db.memcached_test.ISelf2Self>
   */
  public java.util.Collection<com.poesys.db.memcached_test.ISelf2Self> getChildren2Self2SelfLinks() {
    return ((Self2)dto).getChildren2Self2SelfLinks();
  }

  public void markChildrenDeleted() throws com.poesys.db.dto.DtoStatusException {
  	((Self2)dto).markChildrenDeleted();
  }
}