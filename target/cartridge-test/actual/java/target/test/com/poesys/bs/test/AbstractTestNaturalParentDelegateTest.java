/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: AbstractDelegateTest.vsl

package com.poesys.bs.test;


import static org.junit.Assert.assertTrue;
import org.junit.After;

import com.poesys.db.connection.ConnectionException;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.poesys.bs.delegate.DelegateException;
import com.poesys.db.InvalidParametersException;
import com.poesys.db.dao.DaoManagerFactory;
import com.poesys.db.dao.IDaoManager;


/**
 * <p>
 * Test the TestNaturalParentDelegate class.
 * </p>
 * <p>
 * Note that all the test cases defined here run independently, because JUnit
 * does not guarantee the execution order of the test cases in any way.
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractTestNaturalParentDelegateTest {
  /** Define a class logger. */
  protected static Logger logger = Logger.getLogger(AbstractTestNaturalParentDelegateTest.class);
  /** Subsystem name in database.properties file */
  protected String subsystem = "com.poesys.db.test";
  /** Boolean saved off for later comparison in update tests */
  protected Boolean originalBooleanValue = null;
  /** Delegate created at class level for sharing between methods */
  protected TestNaturalParentDelegate delegate = null;

  /**
   * Set up the test by creating the class under test (CUT).
   * 
   * @throws InvalidParametersException when an invalid parameter prevents the
   *       creation of an object
   * @throws DelegateException when a problem occurs during object creation
   */
  @Before
  public void setUp() {
    delegate = TestDelegateFactory.getTestNaturalParentDelegate();
    // Clear any existing caches of objects using the memory manager set in
    // the creation of the delegate.
    clearCaches();
    assertTrue("No delegate created", delegate != null);
  }
  
  /**
   * Helper method to clear caches for any objects used in the test
   */
  protected void clearCaches() {
    // Primary class TestNaturalParent cache
    IDaoManager manager = DaoManagerFactory.getManager(getSubsystem());
    if (manager != null) {
      manager.clearCache(com.poesys.db.test.TestNaturalParent.class.getName());
    }

    // Clear nested object caches
    
    // Child TestNaturalChild cache
    IDaoManager TestNaturalChildManager = DaoManagerFactory.getManager("com.poesys.db.test");
    if (TestNaturalChildManager != null) {
      TestNaturalChildManager.clearCache(com.poesys.db.test.TestNaturalChild.class.getName());
    }
    
    // Child TestAssociationChild cache
    IDaoManager TestAssociationChildManager = DaoManagerFactory.getManager("com.poesys.db.test");
    if (TestAssociationChildManager != null) {
      TestAssociationChildManager.clearCache(com.poesys.db.test.TestAssociationChild.class.getName());
    }
  }

  /**
   * Create some number of new TestNaturalParent objects and return them in a list.  
   * This is a helper method for writable children DTOs.
   * 
   * @param count the number of objects to create
   * @return the stored object
   * @throws InvalidParametersException when an invalid parameter prevents the
   *       creation of an object
   * @throws DelegateException when a problem occurs during object creation
   */
  protected List<com.poesys.bs.test.BsTestNaturalParent> createTestTestNaturalParent(int count) 
      throws DelegateException, InvalidParametersException {
    List<com.poesys.bs.test.BsTestNaturalParent> objects = 
      new CopyOnWriteArrayList<com.poesys.bs.test.BsTestNaturalParent>();
    @SuppressWarnings("unused")
    java.util.Random r = new java.util.Random();
    
    for (int i = 0; i < count; i++) {
      java.lang.Long key1 = r.nextLong();
      java.lang.Long key2 = r.nextLong();

      // Create the object.
      BsTestNaturalParent object = null;
      try {
        object = delegate.createTestNaturalParent(key1, key2);
      } catch (InvalidParametersException e) {
        Object[] args = e.getParameters().toArray();
        String message = com.poesys.db.Message.getMessage(e.getMessage(), args);
        throw new RuntimeException(message, e);
      } catch (DelegateException e) {
        throw new RuntimeException(e.getMessage(), e);
      }
        
      objects.add(object);
    }
    
    return objects;
  }

  /**
   * <p>
   * Create some number of new TestNaturalChild objects and return them in a list.  
   * This is a helper method that the concrete subclass needs to implement. It
   * returns a child object.
   * </p>
   * <ul>
   * <li>Create the list to return as a CopyOnWriteArrayList&lt;BsTestNaturalChild&gt;</li>
   * <li>Generate any required objects linked to this object other than the 
   * parent by calling the appropriate delegate's create method</li>
   * <li>Create the sub-key as an integer for ordered subkeys or a random string
   * for unordered subkeys</li>
   * <li>Generate random values for any primitive data members</li>
   * <li>Create the BsTestNaturalChild by calling delegate.createBsTestNaturalChild</li>
   * <li>Add the new child to the list</li>
   * </ul>
   * <p>
   * Key type: CompositeKey
   * </p>
   * @param parent the parent TestNaturalParent
   * @param count the number of objects to create
   * @return the stored object
   * @throws DelegateException when there is a problem creating an object
   * @throws InvalidParametersException when there is some problem with the 
   *                                    input parameters for creating an object
   */
  abstract protected List<com.poesys.bs.test.BsTestNaturalChild> createTestTestNaturalChild(com.poesys.db.test.ITestNaturalParent parent, 
                                                       int count) 
      throws DelegateException, InvalidParametersException; // create 2
      
  /**
   * <p>
   * Create some number of new TestAssociationChild objects and return them in a list.  
   * This is a helper method that the concrete subclass needs to implement. It
   * returns a child object.
   * </p>
   * <ul>
   * <li>Create the list to return as a CopyOnWriteArrayList&lt;BsTestAssociationChild&gt;</li>
   * <li>Generate any required objects linked to this object other than the 
   * parent by calling the appropriate delegate's create method</li>
   * <li>Create the sub-key as an integer for ordered subkeys or a random string
   * for unordered subkeys</li>
   * <li>Generate random values for any primitive data members</li>
   * <li>Create the BsTestAssociationChild by calling delegate.createBsTestAssociationChild</li>
   * <li>Add the new child to the list</li>
   * </ul>
   * <p>
   * Key type: CompositeKey
   * </p>
   * @param parent the parent TestNaturalParent
   * @param count the number of objects to create
   * @return the stored object
   * @throws DelegateException when there is a problem creating an object
   * @throws InvalidParametersException when there is some problem with the 
   *                                    input parameters for creating an object
   */
  abstract protected List<com.poesys.bs.test.BsTestAssociationChild> createTestTestAssociationChild(com.poesys.db.test.ITestNaturalParent parent, 
                                                       int count) 
      throws DelegateException, InvalidParametersException; // create 2
      
  /**
   * Test method for delegate insert
   */
  @Test
  public void testInsert() {
    // Create a new TestNaturalParent object to perform the test.
    List<BsTestNaturalParent> objects = createTestTestNaturalParent(1);
    assertTrue("No object created", objects.get(0) != null);
    delegate.insert(objects);
    com.poesys.db.pk.NaturalPrimaryKey key = 
      (com.poesys.db.pk.NaturalPrimaryKey)objects.get(0).getPrimaryKey();
    assertTrue("No key for inserted object", key != null);

    BsTestNaturalParent queriedObject = queryStoredObject(key);
    assertTrue("No queried inserted object", queriedObject != null);
    assertTrue("Wrong object", objects.get(0).equals(queriedObject));
    // Delete the inserted objects to clean up.
    // Mark all the objects for delete.
    for (BsTestNaturalParent object : objects) {
      object.delete();
    }

    delegate.deleteBatch(objects);
  }

  /**
   * Test method for getObject
   * 
   * @throws java.sql.SQLException when there is a problem querying a collection 
   *                               property
   */
  @Test
  public void testGetObject() throws java.sql.SQLException {
    // Create a new TestNaturalParent object to perform the test.
    List<BsTestNaturalParent> objects = createTestTestNaturalParent(1);
    assertTrue("No object created", objects.get(0) != null);
    delegate.insert(objects);
    com.poesys.db.pk.NaturalPrimaryKey key = 
      (com.poesys.db.pk.NaturalPrimaryKey)objects.get(0).getPrimaryKey();
    assertTrue("No key generated from concrete implementation", key != null);
    BsTestNaturalParent insertedObject = objects.get(0);
    assertTrue("No comparison object for object query", insertedObject != null);
    
    // Query the object.
    BsTestNaturalParent object = delegate.getObject(key);
    assertTrue("Couldn't get object", object != null);
    assertTrue("Wrong object", insertedObject.equals(object));
    // Delete the inserted objects to clean up.
    // Mark all the objects for delete.
    for (BsTestNaturalParent o : objects) {
      o.delete();
    }

    delegate.deleteBatch(objects);
  }

  /**
   * <p>
   * Test method for getAllObjects
   * </p>
   * <p>
   * For this read/write class, the method ensures that there are objects to
   * query by inserting an object, querying, then deleting the object.
   * </p>
   *
   * @throws InvalidParametersException when an invalid parameter prevents the
   *       creation of an object
   * @throws DelegateException when a problem occurs during object creation
   */
  @Test
  public void testGetAllObjects() 
       throws InvalidParametersException, DelegateException {
    // Create a new TestNaturalParent object to perform the test.
    List<BsTestNaturalParent> insertedObjects = createTestTestNaturalParent(1);
    assertTrue("No object created", insertedObjects.get(0) != null);
    delegate.insert(insertedObjects);
    
    // Query all the objects.
    List<BsTestNaturalParent> objects = delegate.getAllObjects(100);
    assertTrue("Couldn't create list", objects != null);
    // The list should not be empty.
    assertTrue("List of all objects empty", objects.size() != 0);
    // Delete the inserted objects to clean up.
    // Mark all the objects for delete.
    for (BsTestNaturalParent o : insertedObjects) {
      o.delete();
    }

    delegate.deleteBatch(insertedObjects);
  }

  /**
   * A helper method that queried a previously stored object identified by its
   * primary key. The method returns the queried object for further testing
   * after performing structural tests on the object.
   * 
   * @param key the primary key of the object to retrieve
   * @return the object
   * @throws DelegateException when there is a problem querying the object
   */
  protected BsTestNaturalParent queryStoredObject(com.poesys.db.pk.NaturalPrimaryKey key)
      throws DelegateException {
    // Clear the caches, then query the saved object and test it.
    clearCaches();

    BsTestNaturalParent queriedObject = delegate.getDatabaseObject(key);
    assertTrue("Object not found", queriedObject != null);
    return queriedObject;
  }

  /**
   * A helper method that tests the stored existence of the object identified
   * by the pre-existing, saved primary key.
   * 
   * @param key the primary key (com.poesys.db.pk.NaturalPrimaryKey) of the object to check
   * @return true if the object is in the database, false if not
   * @throws DelegateException when there is a problem querying the object
   */
  protected boolean exists(com.poesys.db.pk.NaturalPrimaryKey key) throws DelegateException {
    // Clear the caches, then query the saved object and test it.
    clearCaches();

    BsTestNaturalParent queriedObject = delegate.getObject(key);
    return queriedObject != null;
  }

  /**
   * Test method for delete
   * 
   * @throws InvalidParametersException when an invalid parameter prevents the
   *       creation of an object
   * @throws DelegateException when a problem occurs during object creation
   */
  @Test
  public void testDelete() throws InvalidParametersException,
      DelegateException {
    // Create a new TestNaturalParent object to perform the test.
    List<BsTestNaturalParent> objects = createTestTestNaturalParent(1);
    assertTrue("No object created", objects.get(0) != null);
    delegate.insert(objects);
    com.poesys.db.pk.NaturalPrimaryKey key = 
      (com.poesys.db.pk.NaturalPrimaryKey)objects.get(0).getPrimaryKey();
    assertTrue("No key for inserted object", key != null);
    BsTestNaturalParent insertedObject = objects.get(0);
    assertTrue("No inserted object to delete", insertedObject != null);
    
    delegate.delete(insertedObject);
    assertTrue("object not deleted", !exists(key));
    // Delete the inserted objects to clean up.
    // Mark all the objects for delete.
    for (BsTestNaturalParent object : objects) {
      object.delete();
    }

    delegate.deleteBatch(objects);
  }

  /**
   * Test method for deleteBatch
   * 
   * @throws InvalidParametersException when an invalid parameter prevents the
   *       creation of an object
   * @throws DelegateException when a problem occurs during object creation
   */
  @Test
  public void testDeleteBatch() throws InvalidParametersException,
      DelegateException {
    List<BsTestNaturalParent> objects = createTestTestNaturalParent(2);
    delegate.insert(objects);
    
    // Mark all the objects for delete.
    for (BsTestNaturalParent object : objects) {
      object.delete();
    }

    delegate.deleteBatch(objects);

    for (BsTestNaturalParent object : objects) {
      assertTrue("object not deleted", !exists((com.poesys.db.pk.NaturalPrimaryKey)object.getPrimaryKey()));
    }
  }

  /**
   * Test method for process()
   * 
   * @throws InvalidParametersException when an invalid parameter prevents the
   *       creation of an object
   * @throws DelegateException when a problem occurs during object creation
   */
  @Test
  public void testProcess() throws InvalidParametersException,
      DelegateException {
    // Create 3 rows--one to insert, one to update, one to delete.
    List<BsTestNaturalParent> allObjects = createTestTestNaturalParent(3);
    // Insert the first two rows to later update and delete.
    List<BsTestNaturalParent> existingObjects = 
      new CopyOnWriteArrayList<BsTestNaturalParent>();
    existingObjects.add(allObjects.get(0));
    existingObjects.add(allObjects.get(1));
    delegate.insert(existingObjects);
    existingObjects.get(1).delete();

    // Set the third object as the object to insert.
    List<BsTestNaturalParent> insertObject = 
      new CopyOnWriteArrayList<BsTestNaturalParent>();
    insertObject.add(allObjects.get(2));
    
    // Put it all together.
    List<BsTestNaturalParent> objects = 
      new CopyOnWriteArrayList<BsTestNaturalParent>(existingObjects);
    objects.addAll(insertObject);

    // Test the process method
    delegate.process(objects);

    // Verify the delete
    assertTrue("object not deleted", !exists((com.poesys.db.pk.NaturalPrimaryKey)existingObjects.get(1).getPrimaryKey()));

    // Verify the insert
    assertTrue("object not inserted", exists((com.poesys.db.pk.NaturalPrimaryKey)insertObject.get(0).getPrimaryKey()));
    
    // Delete the inserted objects to clean up.
    // Mark all the objects for delete.
    for (BsTestNaturalParent object : allObjects) {
      object.delete();
    }

    delegate.deleteBatch(allObjects);
  }

  /**
   * Test method for truncateTable
   */
  @Test
  public void testTruncateTable() {
    delegate.truncateTable("TestNaturalParent");
    List<BsTestNaturalParent> objects = delegate.getAllObjects(100);
    assertTrue("Couldn't create list", objects != null);
    // The list should be empty.
    assertTrue("Table not truncated", objects.size() == 0);
  }

  /**
   * Return the database access subsystem name to use to look up access
   * properties in the database.properties file. The concrete implementation
   * should override this method to provide a different subsystem name if
   * needed.
   * 
   * @return the database access subsystem name
   */
  protected String getSubsystem() {
    return subsystem;
  }
  
  /**
   * Flush all resources associated with connections.
   * 
   * @throws ConnectionException when there is a problem flushing resources
   */
  @After
  public void tearDown() throws ConnectionException {
    delegate.flush();
  }
}
