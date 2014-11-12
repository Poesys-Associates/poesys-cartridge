/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: DelegateTest.vsl
// Poesys modified

package com.poesys.bs.memcached_test;

import com.poesys.bs.memcached_test.BsSelf2;
import com.poesys.bs.memcached_test.BsSelf2Self;


/**
 * <p>
 * A test class for the Self2Delegate class. This class
 * is the concrete subclass of the generated abstract class. Make any changes
 * to DTO behavior by overriding methods here rather than changing the abstract
 * superclass; AndroMDA will overwrite that class when you run it but will
 * never overwrite this concrete subclass.
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public class Self2DelegateTest extends com.poesys.bs.memcached_test.AbstractSelf2DelegateTest {

  @Override
  protected java.util.List<com.poesys.bs.memcached_test.BsSelf2Self> createMemcached_testSelf2Self(java.util.List<com.poesys.db.memcached_test.ISelf2> children2List, java.util.List<com.poesys.db.memcached_test.ISelf2> parents2List, int count) 
      throws com.poesys.bs.delegate.DelegateException, 
             com.poesys.db.InvalidParametersException, 
             com.poesys.db.dto.DtoStatusException {
    java.util.List<BsSelf2Self> objects = 
      new java.util.concurrent.CopyOnWriteArrayList<BsSelf2Self>();
    
    // Check the input requirements. The input lists must each have at
    // least count objects as they must map objects one-to-one.
    if (children2List.size() < count) {
      org.junit.Assert.fail("createMemcached_testSelf2Self children2List list has " + children2List.size() + 
        " elements but needs at least " + count);
    }
    if (parents2List.size() < count) {
      org.junit.Assert.fail("createMemcached_testSelf2Self parents2List list has " + parents2List.size() + 
        " elements but needs at least " + count);
    }

    // implement child creation by mapping the objects in the input lists one-to-one
    
    Self2Delegate delegate =
       Memcached_testDelegateFactory.getSelf2Delegate();

    for (int i = 0; i < count; i++) {
      com.poesys.db.memcached_test.ISelf2 children2Dto = children2List.get(i);
      BsSelf2 children2Object = new BsSelf2(children2Dto);
      com.poesys.db.memcached_test.ISelf2 parents2Dto = parents2List.get(i);
      BsSelf2 parents2Object = new BsSelf2(parents2Dto);
      BsSelf2Self link =
        delegate.createSelf2Self(children2Object,
                                 parents2Object,
                                 parents2Dto.getSelf2Id(),
                                 children2Dto.getSelf2Id());
      objects.add(link);
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