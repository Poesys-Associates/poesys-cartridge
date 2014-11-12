/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: AbstractQueryAssociatedLinks.vsl

package com.poesys.db.test.sql;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.poesys.db.ConstraintViolationException;
import com.poesys.db.InvalidParametersException;
import com.poesys.db.dao.query.IParameterizedQuerySql;
import com.poesys.db.pk.IPrimaryKey;

import com.poesys.db.test.TestFactory;


/**
 * <p>
 * A query Command pattern object that implements a SQL query of a collection of 
 * Self4Self objects using the primary key of the associated
 * class Self4. These objects are the linking objects from the SQL 
 * table Self4Self rather than the objects associated through
 * the link (Self4), which have a separate representation.
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractQueryParents4Self4SelfLinksBySelf4 
    implements IParameterizedQuerySql<com.poesys.db.test.ISelf4Self, com.poesys.db.test.ISelf4> {
  /** SQL query statement for Self4Self */
  private static final String SQL =
    "SELECT children4Child, parents4Parent, children4Child, parents4Parent FROM Self4Self WHERE parents4Parent = ? AND parents4Parent = ?";

  public void bindParameters(PreparedStatement stmt, com.poesys.db.test.ISelf4 parameters)
      throws SQLException {
    // Set the parameters starting with the first parameter.
    parameters.getPrimaryKey().setParams(stmt, 1);
  }

  public String getParameterValues(com.poesys.db.test.ISelf4 parameters) {
    // Create the output string with the key parameters.
    return parameters.getPrimaryKey().getValueList();
  }

  public com.poesys.db.test.ISelf4Self getData(ResultSet rs) throws SQLException,
      ConstraintViolationException {
    return TestFactory.getSelf4SelfData(getPrimaryKey(rs), rs);
  }

  public IPrimaryKey getPrimaryKey(ResultSet rs) throws SQLException,
      InvalidParametersException {
    return TestFactory.getSelf4SelfPrimaryKey(rs, "");
  }

  public String getSql() {
    return SQL;
  }
}