/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: DelegateTest.vsl
// Poesys modified

package com.poesys.bs.memcached_test;

import java.math.BigInteger;
import java.util.ArrayList;


/**
 * <p>
 * A test class for the PolyDelegate class. This class
 * is the concrete subclass of the generated abstract class. Make any changes
 * to DTO behavior by overriding methods here rather than changing the abstract
 * superclass; AndroMDA will overwrite that class when you run it but will
 * never overwrite this concrete subclass.
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public class PolyDelegateTest extends com.poesys.bs.memcached_test.AbstractPolyDelegateTest {

  @Override
  protected java.util.List<BsMapElement> createMemcached_testMapElement(com.poesys.db.memcached_test.IPoly parent, int count)
      throws com.poesys.bs.delegate.DelegateException, 
             com.poesys.db.InvalidParametersException, 
             com.poesys.db.dto.DtoStatusException {
    java.util.List<BsMapElement> objects = new ArrayList<BsMapElement>();
    
    for (int i = 0; i < count; i++) {
    // Create a poly and link it to the map to create a MapElement link.
      MapDelegate del = Memcached_testDelegateFactory.getMapDelegate();
      Long location = 12345L;
      BigInteger mapId = null;
      BsMap map = del.createMap(mapId, location);
      // Create the MapElement link.
      BsMapElement element = delegate.createMapElement(map, new BsPoly(parent), 1, "map-poly");
      objects.add(element);
    }

    return objects;
  }

  @Override
  public void testTruncateTable() {
    // Truncate usually is hard; super.testTruncateTable() if desired
  }

  @Override
  public void testGetAllObjects() {
    // Too many objects to get, super.testGetAllObjects() if desired
  }
  
  @Override
  protected String getSubsystem() {
    // return explicit subsystem here if needed, super.getSubsystem() if not
    return "com.poesys.db.memcached_test";
  }
}