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
 * Test the TestBDelegate class.
 * </p>
 * <p>
 * Note that all the test cases defined here run independently, because JUnit
 * does not guarantee the execution order of the test cases in any way.
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractTestBDelegateTest {
  /** Define a class logger. */
  protected static Logger logger = Logger.getLogger(AbstractTestBDelegateTest.class);
  /** Subsystem name in database.properties file */
  protected String subsystem = "com.poesys.db.test";
  /** Boolean saved off for later comparison in update tests */
  protected Boolean originalBooleanValue = null;
  /** Delegate created at class level for sharing between methods */
  protected TestBDelegate delegate = null;

  /**
   * Set up the test by creating the class under test (CUT).
   * 
   * @throws InvalidParametersException when an invalid parameter prevents the
   *       creation of an object
   * @throws DelegateException when a problem occurs during object creation
   */
  @Before
  public void setUp() {
    delegate = TestDelegateFactory.getTestBDelegate();
    // Clear any existing caches of objects using the memory manager set in
    // the creation of the delegate.
    clearCaches();
    assertTrue("No delegate created", delegate != null);
  }
  
  /**
   * Helper method to clear caches for any objects used in the test
   */
  protected void clearCaches() {
    // Primary class TestB cache
    IDaoManager manager = DaoManagerFactory.getManager(getSubsystem());
    if (manager != null) {
      manager.clearCache(com.poesys.db.test.TestB.class.getName());
    }
  }

  /**
   * Create some number of new TestB objects and return them in a list.  
   * This is a helper method for writable children DTOs.
   * 
   * @param count the number of objects to create
   * @return the stored object
   * @throws InvalidParametersException when an invalid parameter prevents the
   *       creation of an object
   * @throws DelegateException when a problem occurs during object creation
   */
  protected List<com.poesys.bs.test.BsTestB> createTestTestB(int count) 
      throws DelegateException, InvalidParametersException {
    List<com.poesys.bs.test.BsTestB> objects = 
      new CopyOnWriteArrayList<com.poesys.bs.test.BsTestB>();
    @SuppressWarnings("unused")
    java.util.Random r = new java.util.Random();

    // Query the required ATestTestA objects.
    List<com.poesys.bs.test.BsTestA> aTestTestAObjects = createATestTestA(count);    
    
    for (int i = 0; i < count; i++) {
      java.math.BigInteger testAId = aTestTestAObjects.get(i).getTestAId();
      java.math.BigInteger testBId = null;

      // Create the object.
      BsTestB object = null;
      try {
        object = delegate.createTestB(testBId, testAId);
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
   * Create some number of new TestA objects and return them in a list.  
   * This is a helper method that the concrete subclass needs to implement. It
   * returns the "child" corresponding to the superclass.
   * </p>
   * <ul>
   * <li>Create the list to return as a CopyOnWriteArrayList&lt;BsTestA&gt;</li>
   * <li>Generate random values for any primitive data members</li>
   * <li>Create the BsTestA by calling delegate.createBs${foreignDto.name}</li>
   * <li>Add the new child to the list</li>
   * </ul>
   * <p>
   * Key type: SequenceKey
   * </p>
   * @param count the number of objects to create
   * @return the stored object
   * @throws DelegateException when there is a problem creating an object
   * @throws InvalidParametersException when there is some problem with the 
   *                                    input parameters for creating an object
   */
  abstract protected List<com.poesys.bs.test.BsTestA> createATestTestA(int count) 
      throws DelegateException, InvalidParametersException; // create 4
      
  /**
   * Test method for delegate insert
   */
  @Test
  public void testInsert() {
    // Create a new TestB object to perform the test.
    List<BsTestB> objects = createTestTestB(1);
    assertTrue("No object created", objects.get(0) != null);
    delegate.insert(objects);
    com.poesys.db.pk.SequencePrimaryKey key = 
      (com.poesys.db.pk.SequencePrimaryKey)objects.get(0).getPrimaryKey();
    assertTrue("No key for inserted object", key != null);

    BsTestB queriedObject = queryStoredObject(key);
    assertTrue("No queried inserted object", queriedObject != null);
    assertTrue("Wrong object", objects.get(0).equals(queriedObject));
    // Delete the inserted objects to clean up.
    // Mark all the objects for delete.
    for (BsTestB object : objects) {
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
    // Create a new TestB object to perform the test.
    List<BsTestB> objects = createTestTestB(1);
    assertTrue("No object created", objects.get(0) != null);
    delegate.insert(objects);
    com.poesys.db.pk.SequencePrimaryKey key = 
      (com.poesys.db.pk.SequencePrimaryKey)objects.get(0).getPrimaryKey();
    assertTrue("No key generated from concrete implementation", key != null);
    BsTestB insertedObject = objects.get(0);
    assertTrue("No comparison object for object query", insertedObject != null);
    
    // Query the object.
    BsTestB object = delegate.getObject(key);
    assertTrue("Couldn't get object", object != null);
    assertTrue("Wrong object", insertedObject.equals(object));
    // Delete the inserted objects to clean up.
    // Mark all the objects for delete.
    for (BsTestB o : objects) {
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
    // Create a new TestB object to perform the test.
    List<BsTestB> insertedObjects = createTestTestB(1);
    assertTrue("No object created", insertedObjects.get(0) != null);
    delegate.insert(insertedObjects);
    
    // Query all the objects.
    List<BsTestB> objects = delegate.getAllObjects(100);
    assertTrue("Couldn't create list", objects != null);
    // The list should not be empty.
    assertTrue("List of all objects empty", objects.size() != 0);
    // Delete the inserted objects to clean up.
    // Mark all the objects for delete.
    for (BsTestB o : insertedObjects) {
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
  protected BsTestB queryStoredObject(com.poesys.db.pk.SequencePrimaryKey key)
      throws DelegateException {
    // Clear the caches, then query the saved object and test it.
    clearCaches();

    BsTestB queriedObject = delegate.getDatabaseObject(key);
    assertTrue("Object not found", queriedObject != null);
    return queriedObject;
  }

  /**
   * A helper method that tests the stored existence of the object identified
   * by the pre-existing, saved primary key.
   * 
   * @param key the primary key (com.poesys.db.pk.SequencePrimaryKey) of the object to check
   * @return true if the object is in the database, false if not
   * @throws DelegateException when there is a problem querying the object
   */
  protected boolean exists(com.poesys.db.pk.SequencePrimaryKey key) throws DelegateException {
    // Clear the caches, then query the saved object and test it.
    clearCaches();

    BsTestB queriedObject = delegate.getObject(key);
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
    // Create a new TestB object to perform the test.
    List<BsTestB> objects = createTestTestB(1);
    assertTrue("No object created", objects.get(0) != null);
    delegate.insert(objects);
    com.poesys.db.pk.SequencePrimaryKey key = 
      (com.poesys.db.pk.SequencePrimaryKey)objects.get(0).getPrimaryKey();
    assertTrue("No key for inserted object", key != null);
    BsTestB insertedObject = objects.get(0);
    assertTrue("No inserted object to delete", insertedObject != null);
    
    delegate.delete(insertedObject);
    assertTrue("object not deleted", !exists(key));
    // Delete the inserted objects to clean up.
    // Mark all the objects for delete.
    for (BsTestB object : objects) {
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
    List<BsTestB> objects = createTestTestB(2);
    delegate.insert(objects);
    
    // Mark all the objects for delete.
    for (BsTestB object : objects) {
      object.delete();
    }

    delegate.deleteBatch(objects);

    for (BsTestB object : objects) {
      assertTrue("object not deleted", !exists((com.poesys.db.pk.SequencePrimaryKey)object.getPrimaryKey()));
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
    List<BsTestB> allObjects = createTestTestB(3);
    // Insert the first two rows to later update and delete.
    List<BsTestB> existingObjects = 
      new CopyOnWriteArrayList<BsTestB>();
    existingObjects.add(allObjects.get(0));
    existingObjects.add(allObjects.get(1));
    delegate.insert(existingObjects);
    existingObjects.get(1).delete();

    // Set the third object as the object to insert.
    List<BsTestB> insertObject = 
      new CopyOnWriteArrayList<BsTestB>();
    insertObject.add(allObjects.get(2));
    
    // Put it all together.
    List<BsTestB> objects = 
      new CopyOnWriteArrayList<BsTestB>(existingObjects);
    objects.addAll(insertObject);

    // Test the process method
    delegate.process(objects);

    // Verify the delete
    assertTrue("object not deleted", !exists((com.poesys.db.pk.SequencePrimaryKey)existingObjects.get(1).getPrimaryKey()));

    // Verify the insert
    assertTrue("object not inserted", exists((com.poesys.db.pk.SequencePrimaryKey)insertObject.get(0).getPrimaryKey()));
    
    // Delete the inserted objects to clean up.
    // Mark all the objects for delete.
    for (BsTestB object : allObjects) {
      object.delete();
    }

    delegate.deleteBatch(allObjects);
  }

  /**
   * Test method for truncateTable
   */
  @Test
  public void testTruncateTable() {
    delegate.truncateTable("TestB");
    List<BsTestB> objects = delegate.getAllObjects(100);
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
