/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: DelegateTest.vsl
// Poesys modified

package com.poesys.bs.memcached_test;

import com.poesys.bs.memcached_test.BsSelf4;
import com.poesys.bs.memcached_test.BsSelf4Self;


/**
 * <p>
 * A test class for the Self4Delegate class. This class
 * is the concrete subclass of the generated abstract class. Make any changes
 * to DTO behavior by overriding methods here rather than changing the abstract
 * superclass; AndroMDA will overwrite that class when you run it but will
 * never overwrite this concrete subclass.
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public class Self4DelegateTest extends com.poesys.bs.memcached_test.AbstractSelf4DelegateTest {

  @Override
  protected java.util.List<com.poesys.bs.memcached_test.BsSelf4Self> createMemcached_testSelf4Self(java.util.List<com.poesys.db.memcached_test.ISelf4> children4List, java.util.List<com.poesys.db.memcached_test.ISelf4> parents4List, int count) 
      throws com.poesys.bs.delegate.DelegateException, 
             com.poesys.db.InvalidParametersException, 
             com.poesys.db.dto.DtoStatusException {
    java.util.List<BsSelf4Self> objects = 
      new java.util.concurrent.CopyOnWriteArrayList<BsSelf4Self>();
    
    // Check the input requirements. The input lists must each have at
    // least count objects as they must map objects one-to-one.
    if (children4List.size() < count) {
      org.junit.Assert.fail("createMemcached_testSelf4Self children4List list has " + children4List.size() + 
        " elements but needs at least " + count);
    }
    if (parents4List.size() < count) {
      org.junit.Assert.fail("createMemcached_testSelf4Self parents4List list has " + parents4List.size() + 
        " elements but needs at least " + count);
    }

    // implement child creation by mapping the objects in the input lists one-to-one
    
    Self4Delegate delegate =
       Memcached_testDelegateFactory.getSelf4Delegate();

    for (int i = 0; i < count; i++) {
      com.poesys.db.memcached_test.ISelf4 children2Dto = children4List.get(i);
      BsSelf4 children4Object = new BsSelf4(children2Dto);
      Long children4Key1 = children2Dto.getKey1();
      Long children4Key2 = children2Dto.getKey2();
      com.poesys.db.memcached_test.ISelf4 parents2Dto = parents4List.get(i);
      BsSelf4 parents4Object = new BsSelf4(parents2Dto);
      Long parents4Key1 = parents2Dto.getKey1();
      Long parents4Key2 = parents2Dto.getKey2();
      BsSelf4Self link =
        delegate.createSelf4Self(children4Object,
                                 parents4Object,
                                 parents4Key1,
                                 children4Key1,
                                 parents4Key2,
                                 children4Key2);
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