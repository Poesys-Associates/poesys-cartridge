/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: AbstractBsDto.vsl


package com.poesys.bs.test;


import com.poesys.bs.delegate.DelegateException;
import com.poesys.bs.dto.IDto;
import com.poesys.db.dto.DtoStatusException;
import com.poesys.db.pk.IPrimaryKey;
import com.poesys.bs.dto.AbstractDto;


import com.poesys.db.test.ITestChild;
import com.poesys.db.test.TestChildProxy;
import com.poesys.db.test.TestChild;


/**
 * <p>
 * A business layer data-transfer object (DTO) for the TestChild. This class
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
public abstract class AbstractBsTestChild 
    extends AbstractDto<com.poesys.db.test.ITestChild> {

  /**
   * A Collection builder helper class for building a business-layer Collection 
   * of BsTestChild objects from a Collection up of data-access-layer 
   * com.poesys.db.test.ITestChild objects
   */
  private class BsUpCollectionBuilder 
      extends com.poesys.bs.dto.CollectionBuilder<com.poesys.db.test.ITestChild, com.poesys.bs.test.BsTestChild> {
    @Override
    public com.poesys.bs.test.BsTestChild get(com.poesys.db.test.ITestChild dto) {
      return new com.poesys.bs.test.BsTestChild(dto);
    }
  }

  /**
   * A Collection builder helper class for building a business-layer Collection 
   * of BsTestChild objects from a Collection down of data-access-layer 
   * com.poesys.db.test.ITestChild objects
   */
  private class BsDownCollectionBuilder 
      extends com.poesys.bs.dto.CollectionBuilder<com.poesys.db.test.ITestChild, com.poesys.bs.test.BsTestChild> {
    @Override
    public com.poesys.bs.test.BsTestChild get(com.poesys.db.test.ITestChild dto) {
      return new com.poesys.bs.test.BsTestChild(dto);
    }
  }

  /**
   * A Collection builder helper class for building a business-layer Collection 
   * of BsTestChildChild objects from a Collection grandchildren of data-access-layer 
   * com.poesys.db.test.ITestChildChild objects
   */
  private class BsGrandchildrenCollectionBuilder 
      extends com.poesys.bs.dto.CollectionBuilder<com.poesys.db.test.ITestChildChild, com.poesys.bs.test.BsTestChildChild> {
    @Override
    public com.poesys.bs.test.BsTestChildChild get(com.poesys.db.test.ITestChildChild dto) {
      return new com.poesys.bs.test.BsTestChildChild(dto);
    }
  }

  /**
   * A Collection builder helper class for building a business-layer Collection 
   * of BsTestChild2TestChild objects from a Collection upTest2Child2TestChildLinks of data-access-layer 
   * com.poesys.db.test.ITestChild2TestChild objects
   */
  private class BsUpTest2Child2TestChildLinksCollectionBuilder 
      extends com.poesys.bs.dto.CollectionBuilder<com.poesys.db.test.ITestChild2TestChild, com.poesys.bs.test.BsTestChild2TestChild> {
    @Override
    public com.poesys.bs.test.BsTestChild2TestChild get(com.poesys.db.test.ITestChild2TestChild dto) {
      return new com.poesys.bs.test.BsTestChild2TestChild(dto);
    }
  }

  /**
   * A Collection builder helper class for building a business-layer Collection 
   * of BsTestChild2TestChild objects from a Collection downTest2Child2TestChildLinks of data-access-layer 
   * com.poesys.db.test.ITestChild2TestChild objects
   */
  private class BsDownTest2Child2TestChildLinksCollectionBuilder 
      extends com.poesys.bs.dto.CollectionBuilder<com.poesys.db.test.ITestChild2TestChild, com.poesys.bs.test.BsTestChild2TestChild> {
    @Override
    public com.poesys.bs.test.BsTestChild2TestChild get(com.poesys.db.test.ITestChild2TestChild dto) {
      return new com.poesys.bs.test.BsTestChild2TestChild(dto);
    }
  }

  /**
   * Create a BsTestChild object from a TestChild object.
   * 
   * @param dto the data-access layer TestChild DTO
   * @throws DelegateException when there is a problem creating the TestChild
   */
  public AbstractBsTestChild(ITestChild dto) throws DelegateException {
    super(dto);
  }

  /**
   * Create a TestChild from new data.
   *
   * @param key the primary key of the TestChild
   * @param testParentId composite super-key attribute that uniquely identifies child combined with child sub-key and any other parent super-keys
   * @param childNo composite subkey attribute that uniquely identifies child combined with parent key
   */
  public AbstractBsTestChild(IPrimaryKey key, java.math.BigInteger testParentId, java.math.BigInteger childNo) {
    super(new TestChildProxy(new TestChild(key, testParentId, childNo)));
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean equals(Object arg0) {
    // Unchecked cast here
    IDto<ITestChild> other = (IDto<ITestChild>)arg0;
    return dto.equals(other.toDto());
  }

  @Override
  public int hashCode() {
    return dto.hashCode();
  }

  public int compareTo(IDto<ITestChild> o) {
    return dto.compareTo(o.toDto());
  }

  public void markChildrenDeleted() throws DtoStatusException {
    dto.markChildrenDeleted();
  }
  
  public IPrimaryKey getPrimaryKey() {
    return dto.getPrimaryKey();
  }

  // Data member properties

  /**
   * <p>
   * Composite super-key attribute that uniquely identifies child combined with child sub-key and any other parent super-keys
   * </p>
   * <p>
   * Added by AddGeneratedKeyProperties + AddParentKeyAttributes as data member
   * </p>
   * <p>
   * <ul>
   * <li>Property is read/write: false</li>
   * <li>Property is defined in the data-access object TestChild</li>
   * </ul>
   * </p>
   * @return a java.math.BigInteger testParentId
   */
  public java.math.BigInteger getTestParentId() {
    return dto.getTestParentId();
  }

  /**
   * <p>
   * Composite subkey attribute that uniquely identifies child combined with parent key
   * </p>
   * <p>
   * Added by AddOrderedSubKeyProperties as data member
   * </p>
   * <p>
   * <ul>
   * <li>Property is read/write: true</li>
   * <li>Property is defined in the data-access object TestChild</li>
   * </ul>
   * </p>
   * @return a java.math.BigInteger childNo
   */
  public java.math.BigInteger getChildNo() {
    return dto.getChildNo();
  }

  /**
   * <p>
   * The parent of all the test children
   * </p>
   * <p>
   * Added by AddToOneAssociationRequiredObjectProperties as data member
   * </p>
   * <p>
   * <ul>
   * <li>Property is read/write: true</li>
   * <li>Property is defined in the data-access object TestChild</li>
   * </ul>
   * </p>
   * @return a com.poesys.bs.test.BsTestParent testParent
   */
  public com.poesys.bs.test.BsTestParent getTestParent() {
    // Return 4
    return new com.poesys.bs.test.BsTestParent(dto.getTestParent());
  }

  /**
   * 
   * <p>
   * Added by TransformToProperty + AddToManyAssociationCollectionProperties as data member
   * </p>
   * <p>
   * <ul>
   * <li>Property is read/write: true</li>
   * <li>Property is defined in the data-access object TestChild</li>
   * </ul>
   * </p>
   * @return a com.poesys.bs.test.BsTestChild testParent
   */
  public java.util.Collection<com.poesys.bs.test.BsTestChild> getUp() {
    BsUpCollectionBuilder builder = new BsUpCollectionBuilder();
    return builder.getCollection(dto.getUp());
  }

  /**
   * 
   * <p>
   * Added by TransformToProperty + AddToManyAssociationCollectionProperties as data member
   * </p>
   * <p>
   * <ul>
   * <li>Property is read/write: true</li>
   * <li>Property is defined in the data-access object TestChild</li>
   * </ul>
   * </p>
   * @return a com.poesys.bs.test.BsTestChild testParent
   */
  public java.util.Collection<com.poesys.bs.test.BsTestChild> getDown() {
    BsDownCollectionBuilder builder = new BsDownCollectionBuilder();
    return builder.getCollection(dto.getDown());
  }

  /**
   * 
   * <p>
   * Added by AddToManyChildCollectionProperties as data member
   * </p>
   * <p>
   * <ul>
   * <li>Property is read/write: true</li>
   * <li>Property is defined in the data-access object TestChild</li>
   * </ul>
   * </p>
   * @return a com.poesys.bs.test.BsTestChildChild testParent
   */
  public java.util.Collection<com.poesys.bs.test.BsTestChildChild> getGrandchildren() {
    BsGrandchildrenCollectionBuilder builder = new BsGrandchildrenCollectionBuilder();
    return builder.getCollection(dto.getGrandchildren());
  }

  /**
   * <p>
   * Collection of association class objects (not the associated objects)
   * </p>
   * <p>
   * Added by AddAssociationClassCollectionProperties as data member
   * </p>
   * <p>
   * <ul>
   * <li>Property is read/write: true</li>
   * <li>Property is defined in the data-access object TestChild</li>
   * </ul>
   * </p>
   * @return a com.poesys.bs.test.BsTestChild2TestChild testParent
   */
  public java.util.Collection<com.poesys.bs.test.BsTestChild2TestChild> getUpTest2Child2TestChildLinks() {
    BsUpTest2Child2TestChildLinksCollectionBuilder builder = new BsUpTest2Child2TestChildLinksCollectionBuilder();
    return builder.getCollection(dto.getUpTest2Child2TestChildLinks());
  }

  /**
   * <p>
   * Collection of association class objects (not the associated objects)
   * </p>
   * <p>
   * Added by AddAssociationClassCollectionProperties as data member
   * </p>
   * <p>
   * <ul>
   * <li>Property is read/write: true</li>
   * <li>Property is defined in the data-access object TestChild</li>
   * </ul>
   * </p>
   * @return a com.poesys.bs.test.BsTestChild2TestChild testParent
   */
  public java.util.Collection<com.poesys.bs.test.BsTestChild2TestChild> getDownTest2Child2TestChildLinks() {
    BsDownTest2Child2TestChildLinksCollectionBuilder builder = new BsDownTest2Child2TestChildLinksCollectionBuilder();
    return builder.getCollection(dto.getDownTest2Child2TestChildLinks());
  }
}