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
 * Self4 objects and their dependents. You should not modify this class;
 * instead, override or add methods in the class Self4Delegate, which
 * specializes this class.
 * </p>
 * 
 * @see Self4Delegate
 * 
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractSelf4Delegate
    extends AbstractDataDelegate<BsSelf4, com.poesys.db.test.ISelf4, com.poesys.db.pk.NaturalPrimaryKey> {
  /**
   * Create an AbstractSelf4Delegate object by supplying the database
   * subsystem in the database.properties file.
   * 
   * @param subsystem the database subsystem to use in database.properties
   */
  public AbstractSelf4Delegate(String subsystem) {
    super(subsystem, 2147483647);
  }

  /**
   * Create an AbstractSelf4Delegate object by supplying the database
   * subsystem in the database.properties file and a specific DBMS, usually
   * IConnectionFactory.DBMS.JNDI.
   * 
   * @param subsystem the database subsystem to use in database.properties
   * @param dbms the DBMS to use (usually IConnectionFactory.DBMS.JNDI)
   */
  public AbstractSelf4Delegate(String subsystem, DBMS dbms) {
    super(subsystem, dbms, 2147483647);
  }

  @Override
  protected String getClassName() {
    return com.poesys.db.test.Self4.class.getName();
  }

  @Override
  protected IInsertSql<com.poesys.db.test.ISelf4> getInsertSql() {
    return new com.poesys.db.test.sql.InsertSelf4();
  }

  @Override
  protected IDeleteSql<com.poesys.db.test.ISelf4> getDeleteSql() {
    return new com.poesys.db.test.sql.DeleteSelf4();
  }

  @Override
  protected IUpdateSql<com.poesys.db.test.ISelf4> getUpdateSql() {
    return null;
  }

  @Override
  protected IKeyQuerySql<com.poesys.db.test.ISelf4> getQueryByKeySql() {
    return new com.poesys.db.test.sql.QuerySelf4();
  }

  @Override
  protected IQuerySql<com.poesys.db.test.ISelf4> getQueryListSql() {
    // Query-All method not required
    return new com.poesys.db.test.sql.QueryAllSelf4();
  }

  @Override
  protected com.poesys.bs.test.BsSelf4 wrapData(com.poesys.db.test.ISelf4 dto) {
    return new com.poesys.bs.test.BsSelf4(dto);
  }

  /**
   * <p>
   * Create a new Self4 with data fields.
   * </p>
   * <p>
   * The Self4 class has a natural key; this method creates the primary
   * key from the appropriate input properties.
   * </p>
   * 
   * @param key1 
   * @param key2 
   * @return the new Self4 object
   * @throws DelegateException when there is a problem generating the key or
   *             creating the object
   */
  public com.poesys.bs.test.BsSelf4 createSelf4(java.lang.Long key1, java.lang.Long key2)
      throws DelegateException {
    com.poesys.db.pk.NaturalPrimaryKey key = null;
    try {
      java.util.ArrayList<com.poesys.db.col.AbstractColumnValue> list =
        new java.util.ArrayList<com.poesys.db.col.AbstractColumnValue>();
      list.add(new com.poesys.db.col.LongColumnValue("key1", key1));
      list.add(new com.poesys.db.col.LongColumnValue("key2", key2));
      key = 
        com.poesys.db.pk.PrimaryKeyFactory.createNaturalKey(list, "com.poesys.db.test.Self4");
    } catch (com.poesys.db.InvalidParametersException e) {
      Object[] args = e.getParameters().toArray();
      String message = com.poesys.db.Message.getMessage(e.getMessage(), args);
      throw new DelegateException(message, e);
    } catch (com.poesys.db.DuplicateKeyNameException e) {
      Object[] args = e.getParameters().toArray();
      String message = com.poesys.db.Message.getMessage(e.getMessage(), args);
      throw new DelegateException(message, e);
    }

    // Create a data-access DTO for direct access, no proxy required.
    com.poesys.db.test.ISelf4 dto =
      new com.poesys.db.test.Self4(key, key1, key2);

    // Create the business DTO.
    return new com.poesys.bs.test.BsSelf4(dto);
  }

  /**
   * Create a new Self4Self association class child of Self4 with 
   * an association key. This class links the input objects.
   * 
   * @param children4Object associated Self4 object (part of the key)
   * @param parents4Object associated Self4 object (part of the key)
   * @param children4Key1 Attribute that is part of the association key
   * @param parents4Key1 Attribute that is part of the association key
   * @param children4Key2 Attribute that is part of the association key
   * @param parents4Key2 Attribute that is part of the association key
   * @return a new Self4Self business layer DTO
   * @throws DelegateException when a parameter causes a problem
   */
  public com.poesys.bs.test.BsSelf4Self createSelf4Self(com.poesys.bs.test.BsSelf4 children4Object, com.poesys.bs.test.BsSelf4 parents4Object, java.lang.Long children4Key1, java.lang.Long parents4Key1, java.lang.Long children4Key2, java.lang.Long parents4Key2) throws DelegateException {
    // Create the key.
    com.poesys.db.pk.AssociationPrimaryKey key = null;
    try {
      java.util.ArrayList<com.poesys.db.pk.IPrimaryKey> list =
        new java.util.ArrayList<com.poesys.db.pk.IPrimaryKey>();
      list.add(children4Object.getPrimaryKey());
      list.add(parents4Object.getPrimaryKey());
      key = 
        com.poesys.db.pk.PrimaryKeyFactory.createAssociationKey(list, "com.poesys.db.test.Self4Self");
    } catch (com.poesys.db.InvalidParametersException e) {
      Object[] args = e.getParameters().toArray();
      String message = com.poesys.db.Message.getMessage(e.getMessage(), args);
      throw new DelegateException(message, e);
    } catch (com.poesys.db.DuplicateKeyNameException e) {
      Object[] args = e.getParameters().toArray();
      String message = com.poesys.db.Message.getMessage(e.getMessage(), args);
      throw new DelegateException(message, e);
    }

    // Create an association-key child data-access Self4Self DTO for direct access, no proxy required.
    com.poesys.db.test.ISelf4Self dto =
      new com.poesys.db.test.Self4Self(key, children4Object.toDto(), parents4Object.toDto(), children4Key1, parents4Key1, children4Key2, parents4Key2);

    // Create the business DTO.
    return new com.poesys.bs.test.BsSelf4Self(dto);
  }

  @Override
  public void truncateTable(String tableName) throws DelegateException {
    java.sql.Connection c = getConnection();
    com.poesys.db.dao.ddl.ISql sql = null;
    com.poesys.db.dao.ddl.IExecuteSql executive = null;

    // First truncate any child tables.
    sql = new com.poesys.db.dao.ddl.TruncateTableSql("Self4Self");
    executive = new com.poesys.db.dao.ddl.ExecuteSql(sql);
    try {
      executive.execute(c);
    } catch (java.sql.SQLException e) {
      throw new DelegateException(e.getMessage() + ": " + "Self4Self", e);
    }

    // Now truncate the current table.
    sql = new com.poesys.db.dao.ddl.TruncateTableSql(tableName);
    executive = new com.poesys.db.dao.ddl.ExecuteSql(sql);
    try {
      executive.execute(c);
    } catch (java.sql.SQLException e) {
      throw new DelegateException(e.getMessage() + ": " + "Self4", e);
    }
  }
}
