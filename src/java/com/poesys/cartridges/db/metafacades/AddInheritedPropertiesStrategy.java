/*
 * Copyright (c) 2008 Carnegie Institution for Science. All rights reserved.
 */
package com.poesys.cartridges.db.metafacades;


import java.util.List;

import com.poesys.cartridges.db.psm.db.Property;


/**
 * <p>
 * An implementation of the IAddPropertyStrategy interface that provides a
 * strategy for adding a set of inherited properties to a property list. The
 * strategy walks the inheritence tree provided by a bridge to the AndroMDA
 * getGeneralizations method run on the implementing class.
 * </p>
 * <p>
 * The implementation of this class must implement three abstract methods:
 * <ul>
 * <li><strong>getCurrentFacade:</strong> returns the outer metafacade class
 * that contains the implementation, which must be a PersistentClassFacade</li>
 * <li><strong>addCurrentProperties:</strong> adds the appropriate set of
 * properties extracted from the superclass facade to the list being built,
 * setting a new source for each property.
 * <li><strong>getSource:</strong> return the source string, usually the name
 * of the implementing nested class that extends this class</li>
 * <li><strong>handleDuplicateProperties:</strong> handle any duplicate
 * properties in the complete list
 * </ul>
 * </p>
 * 
 * @author Robert J. Muller
 */
abstract public class AddInheritedPropertiesStrategy implements
    IAddPropertyStrategy {

  /*
   * (non-Javadoc)
   * 
   * @see com.poesys.cartridges.db.metafacades.IAddPropertyStrategy#addProperties(java.util.List)
   */
  public void addProperties(List<Property> list) {
    PersistentClassFacadeLogicImpl facade =
      getSuperclassFacade(getCurrentFacade());
    if (facade != null) {
      // There is an immediate superclass, so start the tree climb.
      addSuperclassProperties(facade, list);
      handleDuplicateProperties(list);
    }
  }

  /**
   * Given a complete list of properties, handle any duplicate properties in a
   * way appropriate to the specific list (rename properties, override
   * properties, remove properties, and so on). This is not duplicate
   * elimination per se but rather handling name polymorphism through overriding
   * (eliminating the higher-level property).
   * 
   * @param list the complete list of inherited properties
   */
  abstract protected void handleDuplicateProperties(List<Property> list);

  /**
   * Add the superclass properties from the superclass facade to the list being
   * built. This method calls itself recursively to perform the tree climb. It
   * adds the properties from the current superclass as the last part of the
   * method, so the properties are added in top-down, depth-first traversal.
   * @param superFacade the metafacade representing the superclass
   * @param list the properties to add
   */
  private void addSuperclassProperties(PersistentClassFacade superFacade,
                                       List<Property> list) {
    PersistentClassFacadeLogicImpl superSuperFacade =
      getSuperclassFacade(superFacade);
    if (superSuperFacade != null) {
      // There is a superclass, so recurse up the tree.
      addSuperclassProperties(superSuperFacade, list);
    }
    // Now add the properties for the current superclass.
    addCurrentProperties(superFacade, list, getSource());
  }

  /**
   * Get the superclass metafacade if this is a subclass.
   * @param facade the facade for which to get the superclass facade
   * 
   * @return the superclass facade or null if there is no superclass
   */
  protected PersistentClassFacadeLogicImpl getSuperclassFacade(
                                                             PersistentClassFacade facade) {
    PersistentClassFacadeLogicImpl superFacade = null;
    for (Object o : facade.getGeneralizations()) {
      if (o instanceof PersistentClassFacadeLogicImpl) {
        superFacade = (PersistentClassFacadeLogicImpl)o;
        break; // single inheritance, take first class found
      }
    }
    return superFacade;
  }

  /**
   * Get the current persistent class facade. The implementation must return the
   * current facade.
   * 
   * @return the current persistent class facade
   */
  abstract protected PersistentClassFacade getCurrentFacade();

  /**
   * Add the current set of properties from the superclass facade to the list.
   * The implementation must extract the relevant set of properties, set the
   * appropriate source string in each property, then add it to the list.
   * 
   * @param superFacade the metafacade for the superclass
   * @param list the list of inherited properties being built
   * @param source the source string that overwrites the property source
   */
  abstract protected void addCurrentProperties(
                                               PersistentClassFacade superFacade,
                                               List<Property> list,
                                               String source);

  /**
   * Get the source string for the implementation. The implementation must
   * 
   * @return the source string, usually the name of the implementing class
   */
  abstract protected String getSource();

}
