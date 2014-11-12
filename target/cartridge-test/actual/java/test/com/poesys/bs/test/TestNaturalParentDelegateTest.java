/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: DelegateTest.vsl
// Poesys modified

package com.poesys.bs.test;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * A test class for the TestNaturalParentDelegate class. This class is the
 * concrete subclass of the generated abstract class. Make any changes to DTO
 * behavior by overriding methods here rather than changing the abstract
 * superclass; AndroMDA will overwrite that class when you run it but will never
 * overwrite this concrete subclass.
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public class TestNaturalParentDelegateTest extends
    com.poesys.bs.test.AbstractTestNaturalParentDelegateTest {

  @Override
  public java.util.List<BsTestNaturalChild> createTestTestNaturalChild(
                                                                       com.poesys.db.test.ITestNaturalParent parent,
                                                                       int count)
      throws com.poesys.bs.delegate.DelegateException,
      com.poesys.db.InvalidParametersException,
      com.poesys.db.dto.DtoStatusException {
    List<BsTestNaturalChild> objects = new ArrayList<BsTestNaturalChild>();

    for (int i = 0; i < count; i++) {
      Long subkey = new Long(i);
      delegate.createTestNaturalChild(new BsTestNaturalParent(parent),
                                      parent.getKey1(),
                                      parent.getKey2(),
                                      subkey);
    }

    return objects;
  }

  @Override
  protected java.util.List<BsTestAssociationChild> createTestTestAssociationChild(
                                                                                  com.poesys.db.test.ITestNaturalParent parent,
                                                                                  int count)
      throws com.poesys.bs.delegate.DelegateException,
      com.poesys.db.InvalidParametersException,
      com.poesys.db.dto.DtoStatusException {
    List<BsTestAssociationChild> objects =
      new ArrayList<BsTestAssociationChild>();

    for (int i = 0; i < count; i++) {
      Long key1 = parent.getKey1();
      Long key2 = parent.getKey2();
      String xAttr = "An X String";
      BsTestX x = createX(xAttr);
      String yAttr = "A Y String";
      BsTestY y = createY(yAttr);
      BigInteger testXId = x.getTestXId();
      BigInteger testYId = y.getTestYId();
      Long tsACAttr = new Long(10L);
      delegate.createTestAssociationChild(new BsTestNaturalParent(parent),
                                          key1,
                                          key2,
                                          testXId,
                                          testYId,
                                          tsACAttr);
    }

    return objects;
  }

  /**
   * Create a BsTestX object and insert it to create the primary key.
   * 
   * @param xAttr the string attribute for the object
   * @return a BsTestX object
   */
  private BsTestX createX(String xAttr) {
    TestXDelegate delegate = TestDelegateFactory.getTestXDelegate();
    BigInteger testXId = null;
    BsTestX x = delegate.createTestX(testXId, xAttr);
    List<BsTestX> list = new ArrayList<BsTestX>(1);
    list.add(x);
    delegate.insert(list);
    return x;
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
    list.add(y);
    delegate.insert(list);
    return y;
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