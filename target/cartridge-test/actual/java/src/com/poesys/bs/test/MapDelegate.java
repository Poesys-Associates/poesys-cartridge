/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: Delegate.vsl

package com.poesys.bs.test;


import java.math.BigInteger;

import com.poesys.db.connection.IConnectionFactory;
import com.poesys.db.pk.IPrimaryKey;
import com.poesys.db.test.IMapElement;
import com.poesys.db.test.MapElement;


/**
 * <p>
 * A business delegate that provides an application programming interface for
 * Map objects and their dependents. This class delegates to an abstract
 * class, AbstractMapDelegate. You can modify this class to override
 * methods in that class or to add operations to the API.
 * </p>
 * <p>
 * a map of objects to a genome
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public class MapDelegate extends AbstractMapDelegate {
  /**
   * Create a MapDelegate object with a supplied subsystem, using the
   * default database properties (usually for testing).
   * 
   * @param subsystem the subsystem to use
   */
  public MapDelegate(String subsystem) {
    super(subsystem);
  }
  
  /**
   * Create a MapDelegate object with a supplied subsystem and DBMS,
   * usually JNDI for production/test usage with an application server.
   * 
   * @param subsystem the subsystem to use
   * @param dbms the DBMS to use (usually IConnectionFactory.DBMS.JNDI)
   */
  public MapDelegate(String subsystem, IConnectionFactory.DBMS dbms) {
    super(subsystem, dbms);
  }
  
  /**
   * Create a MapElement link between a map and a poly. Creating the map 
   * element will also produce the underlying objects in the map and poly.
   * 
   * @param map the map to link
   * @param poly the poly to link
   * @param version the version number of the tair object
   * @param elementType the element type of the MapElement
   * @return
   */
  public BsMapElement createMapElement(BsMap map, BsPoly poly, Integer version, String elementType) {
    BigInteger tairObjectId = null;
    IPrimaryKey key = null;
    IMapElement dbElement = new MapElement(key, map.toDto(), poly.toDto(), tairObjectId, poly.getPolyId(), map.getMapId(), version, elementType);
    BsMapElement mapElement = new BsMapElement(dbElement);
    
    // Add the poly and the map element to the map.
    map.toDto().addMapElementMapElement(dbElement);
    map.toDto().addPolysPoly(poly.toDto());
    
    // Add the map and the map element to the poly.
    poly.toDto().addMapElementMapElement(dbElement);
    poly.toDto().addMapsMap(map.toDto());
    
    return mapElement;
  }
}