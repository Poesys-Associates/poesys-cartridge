/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: DbDto.vsl

package com.poesys.db.test;


import com.poesys.db.pk.IPrimaryKey;


/**
 * <p>
 * A data-access layer data-transfer object for the TestChild2TestChild. This class
 * is the concrete subclass of the generated abstract class. Make any changes
 * to DTO behavior by overriding methods here rather than changing the abstract
 * superclass; AndroMDA will overwrite that class when you run it but will
 * never overwrite this concrete subclass.
 * </p>
 * 
 * <p>
 * Stereotypes:
 * </p>
 * <ul>
 *     <li>AssociationKey</li>
 *     <li>Immutable</li>
 *     <li>Persistent</li>
 * </ul>
 * 
 * @author Poesys/DB Cartridge
 */
public class TestChild2TestChild extends AbstractTestChild2TestChild {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;

  /**
   * <p>
   * Create a TestChild2TestChild as a new object. This constructor calls the abstract 
   * superclass constructor.
   * </p>
   *
   */
  public TestChild2TestChild() {
    super(); 
  }

  /**
   * <p>
   * Create a TestChild2TestChild. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the TestChild2TestChild
   * @param downObject wraps an associated ITestChild object
   * @param upObject wraps an associated ITestChild object
   * @param upChildNo Attribute that is part of the association key
   * @param downChildNo Attribute that is part of the association key
   * @param upTestParentId Attribute that is part of the association key
   * @param downTestParentId Attribute that is part of the association key
   */
  public TestChild2TestChild(IPrimaryKey key, com.poesys.db.test.ITestChild downObject, com.poesys.db.test.ITestChild upObject, java.math.BigInteger upChildNo, java.math.BigInteger downChildNo, java.math.BigInteger upTestParentId, java.math.BigInteger downTestParentId) {
    super(key, downObject, upObject, upChildNo, downChildNo, upTestParentId, downTestParentId); 
  }
}