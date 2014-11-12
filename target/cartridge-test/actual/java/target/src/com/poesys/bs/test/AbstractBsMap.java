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


import com.poesys.db.test.IMap;
import com.poesys.db.test.MapProxy;
import com.poesys.db.test.Map;


/**
 * <p>
 * A business layer data-transfer object (DTO) for the Map. This class
 * is an abstract class that contains AndroMDA generated code; change nothing
 * in this class. Instead, override any methods in the concrete subclass
 * generated in the same package. AndroMDA will overwrite this class each time
 * you run it but will never overwrite the concrete subclass.
 * </p>
 * <p>
 * a map of objects to a genome
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
public abstract class AbstractBsMap 
    extends AbstractDto<com.poesys.db.test.IMap> {

  /**
   * A List builder helper class for building a business-layer List 
   * of BsPoly objects from a List polys of data-access-layer 
   * com.poesys.db.test.IPoly objects
   */
  private class BsPolysListBuilder 
      extends com.poesys.bs.dto.ListBuilder<com.poesys.db.test.IPoly, com.poesys.bs.test.BsPoly> {
    @Override
    public com.poesys.bs.test.BsPoly get(com.poesys.db.test.IPoly dto) {
      return new com.poesys.bs.test.BsPoly(dto);
    }
  }

  /**
   * A List builder helper class for building a data-access-layer List 
   * of Poly objects from an input List polys of 
   * business-layer BsPoly objects
   */
  private class PolysListBuilder 
      extends com.poesys.bs.dto.ListBuilder<com.poesys.bs.test.BsPoly, com.poesys.db.test.IPoly> {
    @Override
    public com.poesys.db.test.IPoly get(com.poesys.bs.test.BsPoly dto) {
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
   * Create a BsMap object from a Map object.
   * 
   * @param dto the data-access layer Map DTO
   * @throws DelegateException when there is a problem creating the Map
   */
  public AbstractBsMap(IMap dto) throws DelegateException {
    super(dto);
  }

  /**
   * Create a Map from new data.
   *
   * @param key the primary key of the Map
   * @param mapId primary key attribute
   * @param location 
   */
  public AbstractBsMap(IPrimaryKey key, java.math.BigInteger mapId, java.lang.Long location) {
    super(new MapProxy(new Map(key, mapId, location)));
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean equals(Object arg0) {
    // Unchecked cast here
    IDto<IMap> other = (IDto<IMap>)arg0;
    return dto.equals(other.toDto());
  }

  @Override
  public int hashCode() {
    return dto.hashCode();
  }

  public int compareTo(IDto<IMap> o) {
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
   * <li>Property is defined in the data-access object Map</li>
   * </ul>
   * </p>
   * @return a java.math.BigInteger mapId
   */
  public java.math.BigInteger getMapId() {
    return dto.getMapId();
  }

  /**
   * 
   * <p>
   * Added by AddLocalAttributeProperties as data member
   * </p>
   * <p>
   * <ul>
   * <li>Property is read/write: true</li>
   * <li>Property is defined in the data-access object Map</li>
   * </ul>
   * </p>
   * @return a java.lang.Long location
   */
  public java.lang.Long getLocation() {
    return dto.getLocation();
  }

  /**
   * <p>
   * Set the location.
   * </p>
   * 
   * <p>
   * Added by AddLocalAttributeProperties
   * </p>
   * <p>
   * <ul>
   * <li>Property is read/write: true</li>
   * <li>Property is defined in the data-access object Map</li>
   * </ul>
   * </p>
   * @param location the associated business object
   * @throws com.poesys.db.dto.DtoStatusException when the system can't set
   *                 the data-access status to CHANGED
   * @throws com.poesys.db.InvalidParametersException when the property is
   *                 required but the input parameter location is null
   */
  public void setLocation(java.lang.Long location) 
      throws com.poesys.db.dto.DtoStatusException , com.poesys.db.InvalidParametersException {
    dto.setLocation(location);
  }

  /**
   * 
   * <p>
   * Added by TransformToProperty + AddToManyAssociationCollectionProperties as data member
   * </p>
   * <p>
   * <ul>
   * <li>Property is read/write: true</li>
   * <li>Property is defined in the data-access object Map</li>
   * </ul>
   * </p>
   * @return a com.poesys.bs.test.BsPoly location
   */
  public java.util.List<com.poesys.bs.test.BsPoly> getPolys() {
    BsPolysListBuilder builder = new BsPolysListBuilder();
    return builder.getList(dto.getPolys());
  }

  /**
   * <p>
   * Set the poly.
   * </p>
   * 
   * <p>
   * Added by TransformToProperty + AddToManyAssociationCollectionProperties
   * </p>
   * <p>
   * <ul>
   * <li>Property is read/write: true</li>
   * <li>Property is defined in the data-access object Map</li>
   * </ul>
   * </p>
   * @param location the associated business object
   * @throws com.poesys.db.dto.DtoStatusException when the system can't set
   *                 the data-access status to CHANGED
   */
  public void setPoly(java.util.List<com.poesys.bs.test.BsPoly> location) 
      throws com.poesys.db.dto.DtoStatusException{
    PolysListBuilder builder = new PolysListBuilder();
      dto.setPoly(builder.getList(location));
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
   * <li>Property is defined in the data-access object Map</li>
   * </ul>
   * </p>
   * @return a com.poesys.bs.test.BsMapElement location
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
   * <li>Property is defined in the data-access object Map</li>
   * </ul>
   * </p>
   * @param location the associated business object
   * @throws com.poesys.db.dto.DtoStatusException when the system can't set
   *                 the data-access status to CHANGED
   * @throws com.poesys.db.InvalidParametersException when the property is
   *                 required but the input parameter location is null
   */
  public void setMapElement(java.util.Collection<com.poesys.bs.test.BsMapElement> location) 
      throws com.poesys.db.dto.DtoStatusException, com.poesys.db.InvalidParametersException {
    MapElementCollectionBuilder builder = new MapElementCollectionBuilder();
      dto.setMapElement(builder.getCollection(location));
  }

  /**
   * Add a Poly object to the polys collection.
   *
   * @param object the object to add to the collection
   */
  public void addPolysPoly(com.poesys.bs.test.BsPoly object) {
    dto.addPolysPoly(object.toDto());
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