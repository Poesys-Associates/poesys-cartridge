/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: Delegate.vsl

package com.poesys.bs.test;


import com.poesys.db.connection.IConnectionFactory;


/**
 * <p>
 * A business delegate that provides an application programming interface for
 * TestY objects and their dependents. This class delegates to an abstract
 * class, AbstractTestYDelegate. You can modify this class to override
 * methods in that class or to add operations to the API.
 * </p>
 * <p>
 * The TestY object is the "other" side of a many-to-many association.
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public class TestYDelegate extends AbstractTestYDelegate {
  /**
   * Create a TestYDelegate object with a supplied subsystem, using the
   * default database properties (usually for testing).
   * 
   * @param subsystem the subsystem to use
   */
  public TestYDelegate(String subsystem) {
    super(subsystem);
  }
  
  /**
   * Create a TestYDelegate object with a supplied subsystem and DBMS,
   * usually JNDI for production/test usage with an application server.
   * 
   * @param subsystem the subsystem to use
   * @param dbms the DBMS to use (usually IConnectionFactory.DBMS.JNDI)
   */
  public TestYDelegate(String subsystem, IConnectionFactory.DBMS dbms) {
    super(subsystem, dbms);
  }
  
}