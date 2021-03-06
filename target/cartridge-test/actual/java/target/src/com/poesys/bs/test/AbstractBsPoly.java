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


import com.poesys.db.test.IPoly;
import com.poesys.db.test.PolyProxy;
import com.poesys.db.test.Poly;


/**
 * <p>
 * A business layer data-transfer object (DTO) for the Poly. This class
 * is an abstract class that contains AndroMDA generated code; change nothing
 * in this class. Instead, override any methods in the concrete subclass
 * generated in the same package. AndroMDA will overwrite this class each time
 * you run it but will never overwrite the concrete subclass.
 * </p>
 * <p>
 * a difference from a specific sequence in the reference species variant
 * </p>
 * <p>
 * Stereotypes:
 * </p>
 * <ul>
 *     <li>IdentityKey</li>
 *     <li>Persistent</li>
 * </ul>
 * <p>
 * Class is read/write: true
 * </p>
 *
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractBsPoly 
    extends AbstractDto<com.poesys.db.test.IPoly> {

  /**
   * A List builder helper class for building a business-layer List 
   * of BsMap objects from a List maps of data-access-layer 
   * com.poesys.db.test.IMap objects
   */
  private class BsMapsListBuilder 
      extends com.poesys.bs.dto.ListBuilder<com.poesys.db.test.IMap, com.poesys.bs.test.BsMap> {
    @Override
    public com.poesys.bs.test.BsMap get(com.poesys.db.test.IMap dto) {
      return new com.poesys.bs.test.BsMap(dto);
    }
  }

  /**
   * A List builder helper class for building a data-access-layer List 
   * of Map objects from an input List maps of 
   * business-layer BsMap objects
   */
  private class MapsListBuilder 
      extends com.poesys.bs.dto.ListBuilder<com.poesys.bs.test.BsMap, com.poesys.db.test.IMap> {
    @Override
    public com.poesys.db.test.IMap get(com.poesys.bs.test.BsMap dto) {
      return dto.toDto();
    }
  }

  /**
   * A Collection builder helper class for building a business-layer Collection 
   * of BsMapElement objects from a Collection mapElement of data-access-layer 
   * com.poesys.db.test.IMapElement objects
   */
  private class BsMapElementCollectionBuilder 
      extends com.poesys.bs.dto.CollectionBuilder<com.poesys.db.test.IMapElement, com.poesys.bs.test.BsMapElement> {
    @Override
    public com.poesys.bs.test.BsMapElement get(com.poesys.db.test.IMapElement dto) {
      return new com.poesys.bs.test.BsMapElement(dto);
    }
  }

  /**
   * A Collection builder helper class for building a data-access-layer Collection 
   * of MapElement objects from an input Collection mapElement of 
   * business-layer BsMapElement objects
   */
  private class MapElementCollectionBuilder 
      extends com.poesys.bs.dto.CollectionBuilder<com.poesys.bs.test.BsMapElement, com.poesys.db.test.IMapElement> {
    @Override
    public com.poesys.db.test.IMapElement get(com.poesys.bs.test.BsMapElement dto) {
      return dto.toDto();
    }
  }

  /**
   * Create a BsPoly object from a Poly object.
   * 
   * @param dto the data-access layer Poly DTO
   * @throws DelegateException when there is a problem creating the Poly
   */
  public AbstractBsPoly(IPoly dto) throws DelegateException {
    super(dto);
  }

  /**
   * Create a Poly from new data.
   *
   * @param key the primary key of the Poly
   * @param polyId primary key attribute
   * @param sequence 
   */
  public AbstractBsPoly(IPrimaryKey key, java.math.BigInteger polyId, java.lang.String sequence) {
    super(new PolyProxy(new Poly(key, polyId, sequence)));
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean equals(Object arg0) {
    // Unchecked cast here
    IDto<IPoly> other = (IDto<IPoly>)arg0;
    return dto.equals(other.toDto());
  }

  @Override
  public int hashCode() {
    return dto.hashCode();
  }

  public int compareTo(IDto<IPoly> o) {
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
   * Primary key attribute
   * </p>
   * <p>
   * Added by AddGeneratedKeyProperties as data member
   * </p>
   * <p>
   * <ul>
   * <li>Property is read/write: false</li>
   * <li>Property is defined in the data-access object Poly</li>
   * </ul>
   * </p>
   * @return a java.math.BigInteger polyId
   */
  public java.math.BigInteger getPolyId() {
    return dto.getPolyId();
  }

  /**
   * 
   * <p>
   * Added by AddLocalAttributeProperties as data member
   * </p>
   * <p>
   * <ul>
   * <li>Property is read/write: true</li>
   * <li>Property is defined in the data-access object Poly</li>
   * </ul>
   * </p>
   * @return a java.lang.String sequence
   */
  public java.lang.String getSequence() {
    return dto.getSequence();
  }

  /**
   * <p>
   * Set the sequence.
   * </p>
   * 
   * <p>
   * Added by AddLocalAttributeProperties
   * </p>
   * <p>
   * <ul>
   * <li>Property is read/write: true</li>
   * <li>Property is defined in the data-access object Poly</li>
   * </ul>
   * </p>
   * @param sequence the associated business object
   * @throws com.poesys.db.dto.DtoStatusException when the system can't set
   *                 the data-access status to CHANGED
   * @throws com.poesys.db.InvalidParametersException when the property is
   *                 required but the input parameter sequence is null
   */
  public void setSequence(java.lang.String sequence) 
      throws com.poesys.db.dto.DtoStatusException , com.poesys.db.InvalidParametersException {
    dto.setSequence(sequence);
  }

  /**
   * 
   * <p>
   * Added by TransformToProperty + AddToManyAssociationCollectionProperties as data member
   * </p>
   * <p>
   * <ul>
   * <li>Property is read/write: true</li>
   * <li>Property is defined in the data-access object Poly</li>
   * </ul>
   * </p>
   * @return a com.poesys.bs.test.BsMap sequence
   */
  public java.util.List<com.poesys.bs.test.BsMap> getMaps() {
    BsMapsListBuilder builder = new BsMapsListBuilder();
    return builder.getList(dto.getMaps());
  }

  /**
   * <p>
   * Set the map.
   * </p>
   * 
   * <p>
   * Added by TransformToProperty + AddToManyAssociationCollectionProperties
   * </p>
   * <p>
   * <ul>
   * <li>Property is read/write: true</li>
   * <li>Property is defined in the data-access object Poly</li>
   * </ul>
   * </p>
   * @param sequence the associated business object
   * @throws com.poesys.db.dto.DtoStatusException when the system can't set
   *                 the data-access status to CHANGED
   */
  public void setMap(java.util.List<com.poesys.bs.test.BsMap> sequence) 
      throws com.poesys.db.dto.DtoStatusException{
    MapsListBuilder builder = new MapsListBuilder();
      dto.setMap(builder.getList(sequence));
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
   * <li>Property is defined in the data-access object Poly</li>
   * </ul>
   * </p>
   * @return a com.poesys.bs.test.BsMapElement sequence
   */
  public java.util.Collection<com.poesys.bs.test.BsMapElement> getMapElement() {
    BsMapElementCollectionBuilder builder = new BsMapElementCollectionBuilder();
    return builder.getCollection(dto.getMapElement());
  }

  /**
   * <p>
   * Set the mapElement.
   * </p>
   * <p>
   * Collection of association class objects (not the associated objects)
   * </p>
   * <p>
   * Added by AddAssociationClassCollectionProperties
   * </p>
   * <p>
   * <ul>
   * <li>Property is read/write: true</li>
   * <li>Property is defined in the data-access object Poly</li>
   * </ul>
   * </p>
   * @param sequence the associated business object
   * @throws com.poesys.db.dto.DtoStatusException when the system can't set
   *                 the data-access status to CHANGED
   * @throws com.poesys.db.InvalidParametersException when the property is
   *                 required but the input parameter sequence is null
   */
  public void setMapElement(java.util.Collection<com.poesys.bs.test.BsMapElement> sequence) 
      throws com.poesys.db.dto.DtoStatusException, com.poesys.db.InvalidParametersException {
    MapElementCollectionBuilder builder = new MapElementCollectionBuilder();
      dto.setMapElement(builder.getCollection(sequence));
  }

  /**
   * Add a Map object to the maps collection.
   *
   * @param object the object to add to the collection
   */
  public void addMapsMap(com.poesys.bs.test.BsMap object) {
    dto.addMapsMap(object.toDto());
  }

  /**
   * Add a MapElement object to the mapElement collection.
   *
   * @param object the object to add to the collection
   */
  public void addMapElementMapElement(com.poesys.bs.test.BsMapElement object) {
    dto.addMapElementMapElement(object.toDto());
  }
}