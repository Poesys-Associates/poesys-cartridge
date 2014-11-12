/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: DbDto.vsl

package com.poesys.db.test;


import com.poesys.db.pk.IPrimaryKey;


/**
 * <p>
 * A data-access layer data-transfer object for the TestZ. This class
 * is the concrete subclass of the generated abstract class. Make any changes
 * to DTO behavior by overriding methods here rather than changing the abstract
 * superclass; AndroMDA will overwrite that class when you run it but will
 * never overwrite this concrete subclass.
 * </p>
 * <p>
 * The TestZ object is the assocation class that represents the many-to-many
 * association of TestX and TestY objects.
 * </p>
 * <p>
 * Stereotypes:
 * </p>
 * <ul>
 *     <li>AssociationKey</li>
 *     <li>Persistent</li>
 * </ul>
 * 
 * @author Poesys/DB Cartridge
 */
public class TestZ extends AbstractTestZ {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;

  /**
   * <p>
   * Create a TestZ as a new object. This constructor calls the abstract 
   * superclass constructor.
   * </p>
   *
   */
  public TestZ() {
    super(); 
  }

  /**
   * <p>
   * Create a TestZ. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the TestZ
   * @param xsObject wraps an associated ITestX object
   * @param ysObject wraps an associated ITestY object
   * @param testXId Attribute that is part of the association key
   * @param testYId Attribute that is part of the association key
   * @param zAttr 
   */
  public TestZ(IPrimaryKey key, com.poesys.db.test.ITestX xsObject, com.poesys.db.test.ITestY ysObject, java.math.BigInteger testXId, java.math.BigInteger testYId, java.lang.String zAttr) {
    super(key, xsObject, ysObject, testXId, testYId, zAttr); 
  }
}