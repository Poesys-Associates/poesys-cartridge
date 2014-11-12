/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: DelegateTest.vsl
// Poesys modified

package com.poesys.bs.test;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.poesys.cartridges.db.utilities.StringUtilities;


/**
 * <p>
 * A test class for the TestXDelegate class. This class is the concrete subclass
 * of the generated abstract class. Make any changes to DTO behavior by
 * overriding methods here rather than changing the abstract superclass;
 * AndroMDA will overwrite that class when you run it but will never overwrite
 * this concrete subclass.
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public class TestXDelegateTest extends
    com.poesys.bs.test.AbstractTestXDelegateTest {

  @Override
  protected java.util.List<BsTestAssociationChild> createTestTestAssociationChild(
                                                                                  com.poesys.db.test.ITestX parent,
                                                                                  int count)
      throws com.poesys.bs.delegate.DelegateException,
      com.poesys.db.InvalidParametersException,
      com.poesys.db.dto.DtoStatusException {
    List<BsTestAssociationChild> objects =
      new ArrayList<BsTestAssociationChild>();

    for (int i = 0; i < count; i++) {
      BsTestNaturalParent natParent = createNaturalParent();
      Long key1 = natParent.getKey1();
      Long key2 = natParent.getKey2();
      String yAttr = "A String";
      BsTestY y = createY(yAttr);
      BigInteger testYId = y.getTestYId();
      Long tsACAttr = 10L;
      delegate.createTestAssociationChild(new BsTestX(parent),
                                          key1,
                                          key2,
                                          parent.getTestXId(),
                                          testYId,
                                          tsACAttr);
    }

    return objects;
  }

  /**
   * Create a TestNaturalParent test object with two random Long keys.
   * 
   * @return the TestNaturalParent object
   */
  private BsTestNaturalParent createNaturalParent() {
    java.util.Random r = new java.util.Random();
    TestNaturalParentDelegate delegate =
      TestDelegateFactory.getTestNaturalParentDelegate();
    Long key1 = r.nextLong();
    Long key2 = r.nextLong();
    BsTestNaturalParent parent = delegate.createTestNaturalParent(key1, key2);
    return parent;
  }

  /**
   * Create a BsTestY object and insert it to create the primary key.
   * 
   * @param yAttr the string attribute for the object
   * @return a BsTestY object
   */
  private BsTestY createY(String yAttr) {
    TestYDelegate delegate = TestDelegateFactory.getTestYDelegate();
    BigInteger testYId = null;
    BsTestY y = delegate.createTestY(testYId, yAttr);
    List<BsTestY> list = new ArrayList<BsTestY>(1);
    delegate.insert(list);
    list.add(y);
    return y;
  }

  @Override
  protected java.util.List<com.poesys.bs.test.BsTestZ> createTestTestZ(
                                                                       java.util.List<com.poesys.db.test.ITestX> xsList,
                                                                       java.util.List<com.poesys.db.test.ITestY> ysList,
                                                                       int count)
      throws com.poesys.bs.delegate.DelegateException,
      com.poesys.db.InvalidParametersException,
      com.poesys.db.dto.DtoStatusException {
    java.util.List<BsTestZ> objects =
      new java.util.concurrent.CopyOnWriteArrayList<BsTestZ>();

    // Check the input requirements. The input lists must each have at
    // least count objects as they must map objects one-to-one.
    if (xsList.size() < count) {
      org.junit.Assert.fail("createTestTestZ xsList list has " + xsList.size()
                            + " elements but needs at least " + count);
    }
    if (ysList.size() < count) {
      org.junit.Assert.fail("createTestTestZ ysList list has " + ysList.size()
                            + " elements but needs at least " + count);
    }

    // implement child creation by mapping the objects in the input lists
    // one-to-one

    TestXDelegate delegate = TestDelegateFactory.getTestXDelegate();

    for (int i = 0; i < count; i++) {
      BsTestX xsObject = new BsTestX(xsList.get(i));
      BsTestY ysObject = new BsTestY(ysList.get(i));
      BigInteger testXId = xsList.get(i).getTestXId();
      BigInteger testYId = ysList.get(i).getTestYId();
      String zAttr = StringUtilities.generateString(20);

      BsTestZ link =
        delegate.createTestZ(xsObject, ysObject, testXId, testYId, zAttr);
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
    return "com.poesys.db.test";
  }
}