/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: DelegateTest.vsl
// Poesys modified

package com.poesys.bs.memcached_test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * A test class for the TestBDelegate class. This class
 * is the concrete subclass of the generated abstract class. Make any changes
 * to DTO behavior by overriding methods here rather than changing the abstract
 * superclass; AndroMDA will overwrite that class when you run it but will
 * never overwrite this concrete subclass.
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public class TestBDelegateTest extends com.poesys.bs.memcached_test.AbstractTestBDelegateTest {


  @Override
  protected java.util.List<com.poesys.bs.memcached_test.BsTestA> createAMemcached_testTestA(int count)
      throws com.poesys.bs.delegate.DelegateException, 
             com.poesys.db.InvalidParametersException, 
             com.poesys.db.dto.DtoStatusException {
    
    TestADelegate aDelegate = Memcached_testDelegateFactory.getTestADelegate();
    List<com.poesys.bs.memcached_test.BsTestA> objects = 
      new ArrayList<com.poesys.bs.memcached_test.BsTestA>();
    
    for (int i = 0 ; i < count; i++) {
      BigInteger testAId = null;
      BsTestA object = aDelegate.createTestA(testAId);
      objects.add(object);
    }
    
    // Test A and Test B are immutable, need to insert here.
    aDelegate.insert(objects);

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