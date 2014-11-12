/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: DelegateTest.vsl
// Poesys modified

package com.poesys.bs.memcached_test;


import java.math.BigInteger;

import com.poesys.bs.memcached_test.BsTC2TC2TC2TC;
import com.poesys.bs.memcached_test.BsTestChild2TestChild;
import com.poesys.bs.memcached_test.BsTestChild;
import com.poesys.bs.memcached_test.BsTestChildChild;
import com.poesys.bs.memcached_test.BsTestParent;
import com.poesys.bs.memcached_test.Memcached_testDelegateFactory;
import com.poesys.bs.memcached_test.BsTestExplicitKeyChild;


/**
 * <p>
 * A test class for the TestParentDelegate class. This class is the concrete
 * subclass of the generated abstract class. Make any changes to DTO behavior by
 * overriding methods here rather than changing the abstract superclass;
 * AndroMDA will overwrite that class when you run it but will never overwrite
 * this concrete subclass.
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public class TestParentDelegateTest extends
    com.poesys.bs.memcached_test.AbstractTestParentDelegateTest {

  @Override
  protected java.util.List<BsTestChildChild> createMemcached_testTestChildChild(
                                                                                com.poesys.db.memcached_test.ITestParent parent,
                                                                                int count)
      throws com.poesys.bs.delegate.DelegateException,
      com.poesys.db.InvalidParametersException,
      com.poesys.db.dto.DtoStatusException {
    java.util.List<BsTestChildChild> objects =
      new java.util.concurrent.CopyOnWriteArrayList<BsTestChildChild>();

    for (int i = 0; i < count; i++) {
      java.lang.String testChildChildId =
        com.poesys.cartridges.db.utilities.StringUtilities.generateString(10);
      BsTestChildChild bsObject =
        Memcached_testDelegateFactory.getTestParentDelegate().createTestChildChild(new BsTestParent(parent),
                                                                                   new BigInteger(new Long(i).toString()),
                                                                                   parent.getTestParentId(),
                                                                                   testChildChildId);
      objects.add(bsObject);
    }

    return objects;
  }

  @Override
  protected java.util.List<com.poesys.bs.memcached_test.BsTC2TC2TC2TC> createMemcached_testTC2TC2TC2TC(
                                                                                                       java.util.List<com.poesys.db.memcached_test.ITestChild2TestChild> tc1List,
                                                                                                       java.util.List<com.poesys.db.memcached_test.ITestChild2TestChild> tc2List,
                                                                                                       int count)
      throws com.poesys.bs.delegate.DelegateException,
      com.poesys.db.InvalidParametersException,
      com.poesys.db.dto.DtoStatusException {
    java.util.List<BsTC2TC2TC2TC> objects =
      new java.util.concurrent.CopyOnWriteArrayList<BsTC2TC2TC2TC>();

    // Check the input requirements. The input lists must each have at
    // least count objects as they must map objects one-to-one.
    if (tc1List.size() < count) {
      org.junit.Assert.fail("createMemcached_testTC2TC2TC2TC tc1List list has "
                            + tc1List.size() + " elements but needs at least "
                            + count);
    }
    if (tc2List.size() < count) {
      org.junit.Assert.fail("createMemcached_testTC2TC2TC2TC tc2List list has "
                            + tc2List.size() + " elements but needs at least "
                            + count);
    }

    // implement child creation by mapping the objects in the input lists
    // one-to-one

    TestParentDelegate delegate =
      Memcached_testDelegateFactory.getTestParentDelegate();

    for (int i = 0; i < count; i++) {
      BsTestChild2TestChild tc1Object =
        new BsTestChild2TestChild(tc1List.get(i));
      BsTestChild2TestChild tc2Object =
        new BsTestChild2TestChild(tc2List.get(i));
      BigInteger tc1UpChildNo = tc1List.get(i).getUpChildNo();
      BigInteger tc1DownChildNo = tc1List.get(i).getDownChildNo();
      BigInteger tc2UpChildNo = tc2List.get(i).getUpChildNo();
      BigInteger tc2DownChildNo = tc2List.get(i).getDownChildNo();
      BigInteger tc1UpTestParentId = tc1List.get(i).getUpTestParentId();
      BigInteger tc1DownTestParentId = tc1List.get(i).getDownTestParentId();
      BigInteger tc2UpTestParentId = tc2List.get(i).getUpTestParentId();
      BigInteger tc2DownTestParentId = tc1List.get(i).getDownTestParentId();

      BsTC2TC2TC2TC link =
        delegate.createTC2TC2TC2TC(tc1Object,
                                   tc2Object,
                                   tc1UpChildNo,
                                   tc1DownChildNo,
                                   tc2UpChildNo,
                                   tc2DownChildNo,
                                   tc1UpTestParentId,
                                   tc1DownTestParentId,
                                   tc2UpTestParentId,
                                   tc2DownTestParentId);
      objects.add(link);
    }

    return objects;
  }

  @Override
  protected java.util.List<com.poesys.bs.memcached_test.BsTestChild2TestChild> createMemcached_testTestChild2TestChild(
                                                                                                                       java.util.List<com.poesys.db.memcached_test.ITestChild> downList,
                                                                                                                       java.util.List<com.poesys.db.memcached_test.ITestChild> upList,
                                                                                                                       int count)
      throws com.poesys.bs.delegate.DelegateException,
      com.poesys.db.InvalidParametersException,
      com.poesys.db.dto.DtoStatusException {
    java.util.List<BsTestChild2TestChild> objects =
      new java.util.concurrent.CopyOnWriteArrayList<BsTestChild2TestChild>();

    // Check the input requirements. The input lists must each have at
    // least count objects as they must map objects one-to-one.
    if (downList.size() < count) {
      org.junit.Assert.fail("createMemcached_testTestChild2TestChild downList list has "
                            + downList.size()
                            + " elements but needs at least "
                            + count);
    }
    if (upList.size() < count) {
      org.junit.Assert.fail("createMemcached_testTestChild2TestChild upList list has "
                            + upList.size()
                            + " elements but needs at least "
                            + count);
    }

    // implement child creation by mapping the objects in the input lists
    // one-to-one

    TestParentDelegate delegate =
      Memcached_testDelegateFactory.getTestParentDelegate();

    for (int i = 0; i < count; i++) {
      BsTestChild downObject = new BsTestChild(downList.get(i));
      BsTestChild upObject = new BsTestChild(upList.get(i));
      BigInteger upChildNo = upList.get(i).getChildNo();
      BigInteger downChildNo = downList.get(i).getChildNo();
      BigInteger upTestParentId = upList.get(i).getTestParentId();
      BigInteger downTestParentId = downList.get(i).getTestParentId();

      BsTestChild2TestChild link =
        delegate.createTestChild2TestChild(downObject,
                                           upObject,
                                           upChildNo,
                                           downChildNo,
                                           upTestParentId,
                                           downTestParentId);
      objects.add(link);
    }

    return objects;
  }

  @Override
  protected java.util.List<BsTestChild> createMemcached_testTestChild(
                                                                      com.poesys.db.memcached_test.ITestParent parent,
                                                                      int count)
      throws com.poesys.bs.delegate.DelegateException,
      com.poesys.db.InvalidParametersException,
      com.poesys.db.dto.DtoStatusException {
    java.util.List<BsTestChild> objects =
      new java.util.concurrent.CopyOnWriteArrayList<BsTestChild>();

    for (int i = 0; i < count; i++) {
      BsTestChild bsObject =
        Memcached_testDelegateFactory.getTestParentDelegate().createTestChild(new BsTestParent(parent),
                                                                              parent.getTestParentId(),
                                                                              new BigInteger(new Long(i).toString()));
      objects.add(bsObject);
    }

    return objects;
  }

  @Override
  protected java.util.List<BsTestExplicitKeyChild> createMemcached_testTestExplicitKeyChild(
                                                                                            com.poesys.db.memcached_test.ITestParent parent,
                                                                                            int count)
      throws com.poesys.bs.delegate.DelegateException,
      com.poesys.db.InvalidParametersException,
      com.poesys.db.dto.DtoStatusException {
    java.util.List<BsTestExplicitKeyChild> objects =
      new java.util.concurrent.CopyOnWriteArrayList<BsTestExplicitKeyChild>();

    for (int i = 0; i < count; i++) {
      BsTestExplicitKeyChild bsObject =
        Memcached_testDelegateFactory.getTestParentDelegate().createTestExplicitKeyChild(new BsTestParent(parent),
                                                                               parent.getTestParentId(),
                                                                               new Long(i));
      objects.add(bsObject);
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