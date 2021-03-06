/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: Delegate.vsl

package com.poesys.bs.memcached_test;


import com.poesys.db.connection.IConnectionFactory;


/**
 * <p>
 * A business delegate that provides an application programming interface for
 * TestX objects and their dependents. This class delegates to an abstract
 * class, AbstractTestXDelegate. You can modify this class to override
 * methods in that class or to add operations to the API.
 * </p>
 * <p>
 * The TestX object is the "this" side of a many-to-many association.
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public class TestXDelegate extends AbstractTestXDelegate {
  /**
   * Create a TestXDelegate object with a supplied subsystem, using the
   * default database properties (usually for testing).
   * 
   * @param subsystem the subsystem to use
   */
  public TestXDelegate(String subsystem) {
    super(subsystem);
  }
  
  /**
   * Create a TestXDelegate object with a supplied subsystem and DBMS,
   * usually JNDI for production/test usage with an application server.
   * 
   * @param subsystem the subsystem to use
   * @param dbms the DBMS to use (usually IConnectionFactory.DBMS.JNDI)
   */
  public TestXDelegate(String subsystem, IConnectionFactory.DBMS dbms) {
    super(subsystem, dbms);
  }
  
}