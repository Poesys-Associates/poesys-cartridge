/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: AbstractDelegate.vsl

package com.poesys.bs.test;


import com.poesys.bs.delegate.AbstractDataDelegate;
import com.poesys.bs.delegate.DelegateException;
import com.poesys.db.connection.IConnectionFactory.DBMS;
import com.poesys.db.dao.insert.IInsertSql;
import com.poesys.db.dao.update.IUpdateSql;
import com.poesys.db.dao.delete.IDeleteSql;
import com.poesys.db.dao.query.IKeyQuerySql;
import com.poesys.db.dao.query.IQuerySql;


/**
 * <p>
 * A business delegate that provides an application programming interface for
 * ConcreteMiddle objects and their dependents. You should not modify this class;
 * instead, override or add methods in the class ConcreteMiddleDelegate, which
 * specializes this class.
 * </p>
 * 
 * @see ConcreteMiddleDelegate
 * 
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractConcreteMiddleDelegate
    extends AbstractDataDelegate<BsConcreteMiddle, com.poesys.db.test.IConcreteMiddle, com.poesys.db.pk.SequencePrimaryKey> {
  /**
   * Create an AbstractConcreteMiddleDelegate object by supplying the database
   * subsystem in the database.properties file.
   * 
   * @param subsystem the database subsystem to use in database.properties
   */
  public AbstractConcreteMiddleDelegate(String subsystem) {
    super(subsystem, 2147483647);
  }

  /**
   * Create an AbstractConcreteMiddleDelegate object by supplying the database
   * subsystem in the database.properties file and a specific DBMS, usually
   * IConnectionFactory.DBMS.JNDI.
   * 
   * @param subsystem the database subsystem to use in database.properties
   * @param dbms the DBMS to use (usually IConnectionFactory.DBMS.JNDI)
   */
  public AbstractConcreteMiddleDelegate(String subsystem, DBMS dbms) {
    super(subsystem, dbms, 2147483647);
  }

  @Override
  protected String getClassName() {
    return com.poesys.db.test.ConcreteMiddle.class.getName();
  }

  @Override
  protected IInsertSql<com.poesys.db.test.IConcreteMiddle> getInsertSql() {
    return new com.poesys.db.test.sql.InsertConcreteMiddle();
  }

  @Override
  protected IDeleteSql<com.poesys.db.test.IConcreteMiddle> getDeleteSql() {
    return new com.poesys.db.test.sql.DeleteConcreteMiddle();
  }

  @Override
  protected IUpdateSql<com.poesys.db.test.IConcreteMiddle> getUpdateSql() {
    return new com.poesys.db.test.sql.UpdateConcreteMiddle();
  }

  @Override
  protected IKeyQuerySql<com.poesys.db.test.IConcreteMiddle> getQueryByKeySql() {
    return new com.poesys.db.test.sql.QueryConcreteMiddle();
  }

  @Override
  protected IQuerySql<com.poesys.db.test.IConcreteMiddle> getQueryListSql() {
    // Query-All method not required
    return new com.poesys.db.test.sql.QueryAllConcreteMiddle();
  }

  @Override
  protected com.poesys.bs.test.BsConcreteMiddle wrapData(com.poesys.db.test.IConcreteMiddle dto) {
    return new com.poesys.bs.test.BsConcreteMiddle(dto);
  }

  /**
   * <p>
   * Create a new ConcreteMiddle with data fields.
   * </p>
   * <p>
   * The ConcreteMiddle class has no key of its own but inherits from a superclass
   * that has a SequenceKey. When you create an instance of this
   * class, the delegate creates a key of that type in the superclass.
   * </p>
   * 
   * @param baseId primary key attribute
   * @param baseString 
   * @param middleString 
   * @return the new ConcreteMiddle object
   * @throws DelegateException when there is a problem generating the key or
   *             creating the object
   */
  public com.poesys.bs.test.BsConcreteMiddle createConcreteMiddle(java.math.BigInteger baseId, java.lang.String baseString, java.lang.String middleString)
      throws DelegateException {
      com.poesys.db.pk.SequencePrimaryKey key = null;

    // Generate a new ConcreteMiddle id if the input key is null.
    if (baseId == null) {
      java.sql.Connection connection = null;
      try {
        connection = getConnection();
        if (connection == null) {
          throw new DelegateException("Could not get database connection to generate sequence");
        }
        
        if (dbms.equals(DBMS.MYSQL) || dbms.equals(DBMS.JNDI_MYSQL)) {
          key =
            com.poesys.db.pk.PrimaryKeyFactory.createMySqlSequenceKey(connection,
                                                                      "Base",
                                                                      "baseId",
                                                                      "com.poesys.db.test.ConcreteMiddle");
        } else if (dbms.equals(DBMS.ORACLE) || dbms.equals(DBMS.JNDI_ORACLE)) {
          // Create key with sequence Base
          key =
            com.poesys.db.pk.PrimaryKeyFactory.createOracleSequenceKey(connection,
                                                                       "Base",
                                                                       "baseId",
                                                                       "com.poesys.db.test.ConcreteMiddle");
        } else {
          throw new DelegateException("com.poesys.bs.delegate.msg.noDbms");
        }
        // Get the sequence number for use as an attribute value.
        baseId = key.getValue();
      } catch (com.poesys.db.InvalidParametersException e) {
        Object[] args = e.getParameters().toArray();
        String message = com.poesys.db.Message.getMessage(e.getMessage(), args);
        throw new DelegateException(message, e);
      } catch (com.poesys.db.NoPrimaryKeyException e) {
        Object[] args = e.getParameters().toArray();
        String message = com.poesys.db.Message.getMessage(e.getMessage(), args);
        throw new DelegateException(message, e);
      } catch (java.sql.SQLException e) {
        throw new DelegateException(e.getMessage(), e);
      } finally {
        // Done with this connection, close it and return it to the pool.
        if (connection != null) {
          try {
            connection.close();
          } catch (java.sql.SQLException e) {
            throw new DelegateException(e.getMessage(), e);
          }
        }
      }
    } else {
      key = com.poesys.db.pk.PrimaryKeyFactory.createSequenceKey("baseId", baseId, "com.poesys.db.test.ConcreteMiddle");
    }

    // Create a data-access DTO for direct access, no proxy required.
    com.poesys.db.test.IConcreteMiddle dto =
      new com.poesys.db.test.ConcreteMiddle(key, baseId, baseString, middleString);

    // Create the business DTO.
    return new com.poesys.bs.test.BsConcreteMiddle(dto);
  }


  @Override
  public void insert(java.util.List<BsConcreteMiddle> list) throws DelegateException {
    java.sql.Connection c = getConnection();
    
    // Create superclass inserters.
    
    // Base
    final com.poesys.db.dao.IDaoFactory<com.poesys.db.test.IBase> baseFactory =
      manager.getFactory("com.poesys.db.test.Base", "com.poesys.db.test", 2147483647);
    // Key type: SequenceKey, Inherited Key type: None
    com.poesys.db.dao.insert.IInsertBatch<com.poesys.db.test.IBase> baseInserter =
      baseFactory.getInsertBatch(new com.poesys.db.test.sql.InsertBase());
    java.util.Collection<com.poesys.db.test.IBase> baseDtos = convertDtoList(list);
    
    // ConcreteMiddle (uses standard class factory from AbstractDataDelegate) None SequenceKey
    com.poesys.db.dao.insert.IInsertBatch<com.poesys.db.test.IConcreteMiddle> concreteMiddleInserter =
      factory.getInsertBatch(getInsertSql());
    java.util.Collection<com.poesys.db.test.IConcreteMiddle> concreteMiddleDtos = convertDtoList(list);

    try {
      // Insert the superclass objects from the root down. Suppress nested
      // inserts for the superclasses, wait until the concrete class. Also set 
      // pre-insert suppression off to have the root insert linked, to-one class
      // objects.
      for (com.poesys.db.dto.IDbDto dto : concreteMiddleDtos) {
        dto.setSuppressNestedInserts(true);
        dto.setSuppressNestedPreInserts(false);
      }
      baseInserter.insert(c, baseDtos, baseDtos.size() / 2);
      // Insert the object of the current class after enabling nested inserts,
      // which will allow connecting up linked objects to any of the inserted
      // classes.
      for (com.poesys.db.dto.IDbDto dto : concreteMiddleDtos) {
        dto.setSuppressNestedInserts(false);
      }
      concreteMiddleInserter.insert(c, concreteMiddleDtos, concreteMiddleDtos.size() / 2);
      
    } catch (com.poesys.db.ConstraintViolationException e) {
      rollBack(c, e.getMessage(), e);
    } catch (java.sql.SQLException e) {
      rollBack(c, e.getMessage(), e);
    } catch (com.poesys.db.BatchException e) {
      // Don't roll back the whole transaction; the DBMS rolls back the
      // individual inserts that failed, but the rest should be committed.
      String message = com.poesys.db.Message.getMessage(e.getMessage(), null);
      throw new DelegateException(message, e);
    } catch (com.poesys.db.dto.DtoStatusException e) {
      rollBack(c, e.getMessage(), e);
    } finally {
      commit(c);
      close(c);
      finalizeStatus(concreteMiddleDtos, com.poesys.db.dto.IDbDto.Status.EXISTING);
    }
  }

  @Override
  public void process(java.util.List<BsConcreteMiddle> list) throws DelegateException {
    java.sql.Connection c = getConnection();

    // Create the 3 DAOs for inserting, updating, and deleting the concrete object.
    com.poesys.db.dao.insert.IInsertBatch<com.poesys.db.test.IConcreteMiddle> inserter = 
      factory.getInsertBatch(getInsertSql());
    com.poesys.db.dao.update.IUpdateBatch<com.poesys.db.test.IConcreteMiddle> updater = 
      factory.getUpdateBatch(getUpdateSql());
    java.util.Collection<com.poesys.db.test.IConcreteMiddle> dtos = convertDtoList(list);

    // Create the DAOs for inserting or updating the superclasses.
    try {
      // Base
      final com.poesys.db.dao.IDaoFactory<com.poesys.db.test.IBase> baseFactory =
        manager.getFactory("com.poesys.db.test.Base", "com.poesys.db.test.Base", 2147483647);
      com.poesys.db.dao.insert.IInsertBatch<com.poesys.db.test.IBase> baseInserter =
        baseFactory.getInsertBatch(new com.poesys.db.test.sql.InsertBase());
      com.poesys.db.dao.update.IUpdateBatch<com.poesys.db.test.IBase> baseUpdater =
        baseFactory.getUpdateBatch(new com.poesys.db.test.sql.UpdateBase());
      java.util.Collection<com.poesys.db.test.IBase> baseDtos = convertDtoList(list);
      // If there is a root superclass, create a deleter for the root class.
      // The delete will cascade through foreign key constraints.
      com.poesys.db.dao.delete.IDeleteBatch<com.poesys.db.test.IBase> deleter = 
        baseFactory.getDeleteBatch(new com.poesys.db.test.sql.DeleteBase());
      if (deleter != null) {
        deleter.delete(c, baseDtos, baseDtos.size()/2);
      }
    
      // Insert or update the superclass objects from the root down after 
      // turning off nested inserts.
      for (com.poesys.db.dto.IDbDto dto : dtos) {
        dto.setSuppressNestedInserts(true);
        dto.setSuppressNestedPreInserts(false);
      }
      // Process any inserts for base.
      if (baseInserter != null) {
        baseInserter.insert(c, baseDtos, baseDtos.size() / 2);
      }

      // Process any updates for base.
      if (baseUpdater != null) {
        baseUpdater.update(c, baseDtos, baseDtos.size() / 2);
      }
      
      // Insert the leaf object after resetting the nested object insertion to
      // insert any nested objects connecting to any of the inserted classes.
      if (inserter != null) {
        for (com.poesys.db.dto.IDbDto dto : dtos) {
          dto.setSuppressNestedInserts(false);
        }
        inserter.insert(c, dtos, dtos.size() / 2);
      }
      
      // Update the object.
      if (updater != null) {
        updater.update(c, dtos, dtos.size() / 2);
      }
    } catch (com.poesys.db.ConstraintViolationException e) {
      rollBack(c, e.getMessage(), e);
    } catch (java.sql.SQLException e) {
      rollBack(c, e.getMessage(), e);
    } catch (com.poesys.db.BatchException e) {
      // Don't roll back the whole transaction; the DBMS rolls back the
      // individual operations that failed, but the rest should be committed.
      String message = com.poesys.db.Message.getMessage(e.getMessage(), null);
      throw new DelegateException(message, e);
    } catch (com.poesys.db.dto.DtoStatusException e) {
      String message = com.poesys.db.Message.getMessage(e.getMessage(), null);
      rollBack(c, message, e);
    } finally {
      commit(c);
      close(c);
      updateChangedToExisting(dtos);
      finalizeStatus(dtos, com.poesys.db.dto.IDbDto.Status.EXISTING);
      finalizeStatus(dtos, com.poesys.db.dto.IDbDto.Status.DELETED);
    }
  }
}