/*
 * Copyright (c) 2009 Poesys Associates. All rights reserved.
 */
package com.poesys.cartridges.db.metafacades;


import java.util.List;

import com.poesys.cartridges.db.psm.db.Property;


/**
 * Type interface for a Strategy pattern implementation that adds a primary key
 * to a list of properties. Use this interface to instantiate a nested class in
 * a metafacade that fills in appropriate data in the properties it's producing.
 * 
 * @author Robert J. Muller
 */
public interface IAddPropertyStrategy {
  /**
   * Add an appropriate set of properties to a list of properties, adding the
   * new properties at the end of the list. If the list is null, the method does
   * nothing and returns.
   * 
   * @param list the required list of properties
   */
  void addProperties(List<Property> list);
}
