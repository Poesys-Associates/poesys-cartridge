/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: AbstractDelegate.vsl

package com.poesys.bs.test;


import com.poesys.bs.delegate.AbstractIdentityDataDelegate;
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
 * Poly objects and their dependents. You should not modify this class;
 * instead, override or add methods in the class PolyDelegate, which
 * specializes this class.
 * </p>
 * <p>
 * a difference from a specific sequence in the reference species variant
 * </p>
 * @see PolyDelegate
 * 
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractPolyDelegate
    extends AbstractIdentityDataDelegate<BsPoly, com.poesys.db.test.IPoly, com.poesys.db.pk.IdentityPrimaryKey> {
  /**
   * Create an AbstractPolyDelegate object by supplying the database
   * subsystem in the database.properties file.
   * 
   * @param subsystem the database subsystem to use in database.properties
   */
  public AbstractPolyDelegate(String subsystem) {
    super(subsystem, 2147483647);
  }

  /**
   * Create an AbstractPolyDelegate object by supplying the database
   * subsystem in the database.properties file and a specific DBMS, usually
   * IConnectionFactory.DBMS.JNDI.
   * 
   * @param subsystem the database subsystem to use in database.properties
   * @param dbms the DBMS to use (usually IConnectionFactory.DBMS.JNDI)
   */
  public AbstractPolyDelegate(String subsystem, DBMS dbms) {
    super(subsystem, dbms, 2147483647);
  }

  @Override
  protected String getClassName() {
    return com.poesys.db.test.Poly.class.getName();
  }

  @Override
  protected IInsertSql<com.poesys.db.test.IPoly> getInsertSql() {
    return new com.poesys.db.test.sql.InsertPoly();
  }

  @Override
  protected IDeleteSql<com.poesys.db.test.IPoly> getDeleteSql() {
    return new com.poesys.db.test.sql.DeletePoly();
  }

  @Override
  protected IUpdateSql<com.poesys.db.test.IPoly> getUpdateSql() {
    return new com.poesys.db.test.sql.UpdatePoly();
  }

  @Override
  protected IKeyQuerySql<com.poesys.db.test.IPoly> getQueryByKeySql() {
    return new com.poesys.db.test.sql.QueryPoly();
  }

  @Override
  protected IQuerySql<com.poesys.db.test.IPoly> getQueryListSql() {
    // Query-All method not required
    return new com.poesys.db.test.sql.QueryAllPoly();
  }

  @Override
  protected com.poesys.bs.test.BsPoly wrapData(com.poesys.db.test.IPoly dto) {
    return new com.poesys.bs.test.BsPoly(dto);
  }

  /**
   * <p>
   * Create a new Poly with data fields.
   * </p>
   * <p>
   * The Poly class has an identity key; this method does nothing with
   * respect to the key, which the database will generate when the new Poly
   * inserts into the database.
   * </p>
   * 
   * @param polyId primary key attribute
   * @param sequence 
   * @return the new Poly object
   * @throws DelegateException when there is a problem generating the key or
   *             creating the object
   */
  public com.poesys.bs.test.BsPoly createPoly(java.math.BigInteger polyId, java.lang.String sequence)
      throws DelegateException {
    com.poesys.db.pk.IPrimaryKey key = 
      com.poesys.db.test.TestFactory.getPolyPrimaryKey(polyId);
      
    // Create a data-access DTO for direct access, no proxy required.
    com.poesys.db.test.IPoly dto =
      new com.poesys.db.test.Poly(key, polyId, sequence);

    // Create the business DTO.
    return new com.poesys.bs.test.BsPoly(dto);
  }


  @Override
  public void truncateTable(String tableName) throws DelegateException {
    java.sql.Connection c = getConnection();
    com.poesys.db.dao.ddl.ISql sql = null;
    com.poesys.db.dao.ddl.IExecuteSql executive = null;

    // First truncate any child tables.
    sql = new com.poesys.db.dao.ddl.TruncateTableSql("MapElement");
    executive = new com.poesys.db.dao.ddl.ExecuteSql(sql);
    try {
      executive.execute(c);
    } catch (java.sql.SQLException e) {
      throw new DelegateException(e.getMessage() + ": " + "MapElement", e);
    }

    // Now truncate the current table.
    sql = new com.poesys.db.dao.ddl.TruncateTableSql(tableName);
    executive = new com.poesys.db.dao.ddl.ExecuteSql(sql);
    try {
      executive.execute(c);
    } catch (java.sql.SQLException e) {
      throw new DelegateException(e.getMessage() + ": " + "Poly", e);
    }
  }
}