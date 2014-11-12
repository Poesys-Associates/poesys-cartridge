/*
 * Copyright (c) 2008 Poesys Associates. All rights reserved.
 */
package com.poesys.cartridges.db.utilities;


import java.util.Collection;
import java.util.List;

import org.andromda.metafacades.uml.UMLProfile;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.poesys.cartridges.db.psm.db.Dto;
import com.poesys.cartridges.db.psm.db.Property;


/**
 * A collection of static methods related to database code generation
 * 
 * @author Robert J. Muller
 */
public class DatabaseUtilities {
  /** Logger for this class */
  static Logger logger = Logger.getLogger(DatabaseUtilities.class);
  // AndroMDA datatype namespace data type strings
  private static final String STRING = "datatype::String";
  private static final String DATE = "datatype::Date";
  private static final String TIME = "datatype::Time";
  private static final String DATETIME = "datatype::DateTime";
  private static final String TIMESTAMP = "datatype::Timestamp";
  private static final String CHAR = "datatype::char";
  private static final String CHARACTER = "datatype::Character";
  private static final String BYTE_P = "datatype::byte";
  private static final String BYTE = "datatype::Byte";
  private static final String SHORT_P = "datatype::short";
  private static final String SHORT = "datatype::Short";
  private static final String INT_P = "datatype::int";
  private static final String INTEGER = "datatype::Integer";
  private static final String LONG_P = "datatype::long";
  private static final String LONG = "datatype::Long";
  private static final String FLOAT_P = "datatype::float";
  private static final String FLOAT = "datatype::Float";
  private static final String DOUBLE_P = "datatype::double";
  private static final String DOUBLE = "datatype::Double";
  private static final String MONEY = "datatype::Money";
  private static final String BOOLEAN_P = "datatype::boolean";
  private static final String BOOLEAN = "datatype::Boolean";
  private static final String DECIMAL = "datatype::Decimal";
  private static final String DECIMAL_P = "datatype::decimal";
  private static final String CLOB = "datatype::Clob";
  private static final String BLOB = "datatype::Blob";
  private static final String BIG_INTEGER = "datatype::BigInteger";
  private static final String BIG_DECIMAL = "datatype::BigDecimal";

  // Java namespace data type strings
  private static final String JAVA_STRING = "java.lang.String";
  private static final String UUID = "java.util.UUID";
  private static final String JAVA_BIG_INTEGER = "java.math.BigInteger";
  private static final String JAVA_BIG_DECIMAL = "java.math.BigDecimal";

  /**
   * <p>
   * Get the JDBC getter method call appropriate to the type of the property.
   * This is usually a call in the format <code>rs.getType("column_name")</code>
   * . The variable rs must be present and must be a JDBC ResultSet object; this
   * is required because the variable name must be embedded within the call for
   * objects that need to check nulls. This method supports the following data
   * types:
   * </p>
   * <ul>
   * <li>BigDecimal--getBigDecimal()</li>
   * <li>BigInteger--getBigDecimal().toBigInteger()</li>
   * <li>Integer--getInt()</li>
   * <li>Long--getLong()</li>
   * <li>String--getString()</li>
   * <li>Blob--getBlob()</li>
   * <li>Boolean--getBoolean()</li>
   * <li>Date--getDate()</li>
   * <li>Time--getTime()</li>
   * <li>Timestamp--getTimestamp()</li>
   * </ul>
   * 
   * @param type the fully qualified Java data type of the attribute
   * @param col the SQL column name of the attribute
   * 
   * @return a string representation of a JDBC getter method call
   */
  public static String getJdbcGetCall(String type, String col) {
    if (type == null) {
      throw new RuntimeException("Null data type while getting JDBC get call");
    }
    if (col == null) {
      throw new RuntimeException("Null SQL column name while getting JDBC get call");
    }

    // Implement all supported types here. Any type that requires a dereference
    // must check for null with a conditional operator ?:.
    String call = null;
    if (type.equals("java.math.BigInteger")) {
      // Check for null before dereferencing.
      call =
        "rs.getBigDecimal(\""
            + col
            + "\") == null ? null : rs.getBigDecimal(\""
            + col
            + "\").toBigInteger()";
    } else if (type.equals("java.lang.String")) {
      call = "rs.getString(\"" + col + "\")";
    } else if (type.equals(UUID)) {
      call = "java.util.UUID.fromString(rs.getString(\"" + col + "\"))";
    } else if (type.equals(BIG_DECIMAL)) {
      call = "rs.getBigDecimal(\"" + col + "\")";
    } else if (type.equals("java.lang.Float") || type.equals("java.lang.float")) {
      call = "rs.getDouble(\"" + col + "\")"; // JDBC "float" is really DOUBLE
    } else if (type.equals("java.lang.Double")
               || type.equals("java.lang.double")) {
      call = "rs.getDouble(\"" + col + "\")";
    } else if (type.equalsIgnoreCase("java.lang.Integer")) {
      call = "rs.getInt(\"" + col + "\")";
    } else if (type.equalsIgnoreCase("java.lang.Long")) {
      call = "rs.getLong(\"" + col + "\")";
    } else if (type.equalsIgnoreCase("java.lang.Boolean")) {
      call = "rs.getBoolean(\"" + col + "\")";
    } else if (type.equals("java.sql.Blob")) {
      call = "rs.getBlob(\"" + col + "\")";
    } else if (type.equals("java.sql.Date")) {
      call = "rs.getDate(\"" + col + "\")";
    } else if (type.equals("java.sql.Time")) {
      call = "rs.getTime(\"" + col + "\")";
    } else if (type.equals("java.sql.Timestamp")) {
      call = "rs.getTimestamp(\"" + col + "\")";
    } else if (StringUtils.contains(type, "java.util.List")) {
      // to-many attributes of List don't come from result set
      call = null;
    } else if (StringUtils.contains(type, "java.util.Collection")) {
      // to-many attributes of Collection don't come from result set
      call = null;
    } else {
      logger.debug("Unsupported JDBC type: " + type + " on property " + col);
    }
    return call;
  }

  /**
   * <p>
   * Get the JDBC setter method call appropriate to the type of the property.
   * This is usually a call in the format
   * <code>setType(index, object.get&lt;Property&gt;)</code>, where
   * &lt;Property&gt; is the property name. This method supports the following
   * data types:
   * </p>
   * <ul>
   * <li>BigDecimal--setBigDecimal()</li>
   * <li>BigInteger--setBigDecimal(new BigDecimal(BigInteger))</li>
   * <li>Integer--setInt()</li>
   * <li>Long--setLong()</li>
   * <li>String--setString()</li>
   * <li>Blob--setBlob()</li>
   * <li>Boolean--setInt() (1 == true, 0 == false)</li>
   * <li>Date--setDate()</li>
   * <li>Time--setTime()</li>
   * <li>Timestamp--setTimestamp()</li>
   * </ul>
   * 
   * @param type the fully qualified Java data type of the property
   * @param getterName the property name
   * @param objectVariable the name of the variable containing the DTO
   * @param indexVariable the name of the variable containing the index number
   * 
   * @return a string representation of a JDBC setter method call
   */
  public static String getJdbcSetCall(String type, String getterName,
                                      String objectVariable,
                                      String indexVariable) {
    if (type == null || type.length() == 0) {
      throw new RuntimeException("Null Java data type while getting JDBC set call");
    }
    if (getterName == null || getterName.length() == 0) {
      throw new RuntimeException("Null property name while getting JDBC set call");
    }
    if (objectVariable == null || objectVariable.length() == 0) {
      objectVariable = "object"; // default
    }
    if (indexVariable == null || indexVariable.length() == 0) {
      indexVariable = "index"; // default
    }

    // Implement all supported types here.
    String call = null;
    if (type.equals("java.math.BigInteger")) {
      // Check for null value
      call =
        "setBigDecimal("
            + indexVariable
            + ", "
            + objectVariable
            + "."
            + getterName
            + "() == null ? null : new java.math.BigDecimal("
            + objectVariable
            + "."
            + getterName
            + "()))";
    } else if (type.equals("java.lang.String")) {
      call =
        "setString("
            + indexVariable
            + ", "
            + objectVariable
            + "."
            + getterName
            + "())";
    } else if (type.equals(UUID)) {
      call =
        "setString("
            + indexVariable
            + ", "
            + objectVariable
            + "."
            + getterName
            + "())";
    } else if (type.equals("java.math.BigDecimal")) {
      call =
        "setBigDecimal("
            + indexVariable
            + ", "
            + objectVariable
            + "."
            + getterName
            + "())";
    } else if (type.equalsIgnoreCase("java.lang.Integer")) {
      call =
        "setInt("
            + indexVariable
            + ", "
            + objectVariable
            + "."
            + getterName
            + "())";
    } else if (type.equalsIgnoreCase("java.lang.Double")) {
      call =
        "setDouble("
            + indexVariable
            + ", "
            + objectVariable
            + "."
            + getterName
            + "())";
    } else if (type.equalsIgnoreCase("java.lang.Float")) {
      // SQL "float" is really double
      call =
        "setDouble("
            + indexVariable
            + ", "
            + objectVariable
            + "."
            + getterName
            + "())";
    } else if (type.equalsIgnoreCase("java.lang.Long")) {
      call =
        "setLong("
            + indexVariable
            + ", "
            + objectVariable
            + "."
            + getterName
            + "())";
    } else if (type.equalsIgnoreCase("java.lang.Boolean")) {
      call =
        "setInt("
            + indexVariable
            + ", "
            + objectVariable
            + "."
            + getterName
            + "() ? 1 : 0)";
    } else if (type.equals("java.sql.Blob")) {
      call =
        "setBlob("
            + indexVariable
            + ", "
            + objectVariable
            + "."
            + getterName
            + "())";
    } else if (type.equals("java.sql.Date")) {
      call =
        "setDate("
            + indexVariable
            + ", "
            + objectVariable
            + "."
            + getterName
            + "())";
    } else if (type.equals("java.sql.Time")) {
      call =
        "setTime("
            + indexVariable
            + ", "
            + objectVariable
            + "."
            + getterName
            + "())";
    } else if (type.equals("java.sql.Timestamp")) {
      call =
        "setTimestamp("
            + indexVariable
            + ", "
            + objectVariable
            + "."
            + getterName
            + "())";
    } else if (StringUtils.contains(type, "java.util.List")) {
      // to-many attributes of List don't come from result set
    } else if (StringUtils.contains(type, "java.util.Collection")) {
      // to-many attributes of Collection don't come from result set
    } else {
      logger.debug("Unsupported JDBC type: "
                   + type
                   + " on property getter "
                   + getterName);
    }
    return call;
  }

  /**
   * Get the getter method name for a property name of a given type. The method
   * name does not include the parentheses for a function call.
   * 
   * @param name the property name
   * @param typeName the data type name of the property
   * @return the getter method name
   */
  public static String getGetterName(String name, String typeName) {
    String prefix = "get";
    if (typeName.equalsIgnoreCase(UMLProfile.BOOLEAN_TYPE_NAME)) {
      prefix = "is";
    }
    return StringUtilities.buildVariableName(name, prefix, null);
  }

  /**
   * <p>
   * Get the ANSI SQL data type most appropriate for a given datatype namespace
   * data type or Java type. This is the SQL data type you use to create a
   * column in a table. If a standard datatype type has no SQL type, this method
   * throws a RuntimeException.
   * </p>
   * <ul>
   * <li>datatype::String--VARCHAR</li>
   * <li>datatype::Date--DATE</li>
   * <li>datatype::Time--TIME</li>
   * <li>datatype::DateTime--TIMESTAMP</li>
   * <li>datatype::Timestamp--TIMESTAMP</li>
   * <li>datatype::char--CHARACTER</li>
   * <li>datatype::Character--CHARACTER</li>
   * <li>datatype::byte--SMALLINT</li>
   * <li>datatype::Byte--SMALLINT</li>
   * <li>datatype::short--SMALLINT</li>
   * <li>datatype::Short--SMALLINT</li>
   * <li>datatype::int--INTEGER</li>
   * <li>datatype::Integer--INTEGER</li>
   * <li>datatype::long--INTEGER(12)</li>
   * <li>datatype::Long--INTEGER(12)</li>
   * <li>datatype::float--FLOAT</li>
   * <li>datatype::Float--FLOAT</li>
   * <li>datatype::double--DOUBLE</li>
   * <li>datatype::Double--DOUBLE</li>
   * <li>datatype::Money--NUMERIC</li>
   * <li>datatype::boolean--BOOLEAN</li>
   * <li>datatype::Boolean--BOOLEAN</li>
   * <li>datatype::Decimal--DECIMAL</li>
   * <li>datatype::decimal--DECIMAL</li>
   * <li>datatype::Clob--TEXT (not really supported)</li>
   * <li>datatype::Blob--BIT VARYING</li>
   * <li>java.math.BigInteger--NUMERIC</li>
   * <li>java.math.BigDecimal--NUMERIC</li>
   * <li>java.lang.String--VARCHAR</li>
   * <li>java.util.UUID--VARCHAR</li>
   * </ul>
   * <p>
   * Note that this mapping function replaces the AndroMDA mapping file for the
   * sqlMappingsUri to permit a code generation run to generate SQL output for
   * multiple output targets at once driven by template rather than by the
   * single SQL mapping.
   * </p>
   * 
   * @param dataType the datatype namespace typename
   * 
   * @return the ANSI SQL data type preferred for the datatype type or null if
   *         the data type is not in the above list
   */
  public static String getAnsiSqlType(String dataType) {
    if (dataType == null) {
      throw new RuntimeException("Null data type while getting ANSI SQL data type");
    }

    // Implement all supported types here.
    String sql = null;
    if (STRING.equals(dataType) || JAVA_STRING.equals(dataType)) {
      sql = "VARCHAR";
    } else if (UUID.equals(dataType)) {
      sql = "VARCHAR(50)";
    } else if (DATE.equals(dataType)) {
      sql = "DATE";
    } else if (TIME.equals(dataType)) {
      sql = "TIME";
    } else if (DATETIME.equals(dataType)) {
      sql = "TIMESTAMP";
    } else if (TIMESTAMP.equals(dataType)) {
      sql = "TIMESTAMP";
    } else if (CHAR.equals(dataType)) {
      sql = "CHARACTER";
    } else if (CHARACTER.equals(dataType)) {
      sql = "CHARACTER";
    } else if (BYTE_P.equals(dataType)) {
      sql = "SMALLINT";
    } else if (BYTE.equals(dataType)) {
      sql = "SMALLINT";
    } else if (SHORT_P.equals(dataType)) {
      sql = "SMALLINT";
    } else if (SHORT.equals(dataType)) {
      sql = "SMALLINT";
    } else if (INT_P.equals(dataType)) {
      sql = "INTEGER";
    } else if (INTEGER.equals(dataType)) {
      sql = "INTEGER";
    } else if (LONG_P.equals(dataType)) {
      sql = "INTEGER";
    } else if (LONG.equals(dataType)) {
      sql = "INTEGER";
    } else if (JAVA_BIG_INTEGER.equals(dataType)
               || BIG_INTEGER.equals(dataType)) {
      sql = "INTEGER";
    } else if (FLOAT_P.equals(dataType)) {
      sql = "FLOAT";
    } else if (FLOAT.equals(dataType)) {
      sql = "FLOAT";
    } else if (DOUBLE_P.equals(dataType)) {
      sql = "NUMERIC";
    } else if (DOUBLE.equals(dataType)) {
      sql = "NUMERIC";
    } else if (MONEY.equals(dataType)) {
      sql = "NUMERIC";
    } else if (JAVA_BIG_DECIMAL.equals(dataType)
        || BIG_DECIMAL.equals(dataType)) {
sql = "INTEGER";
    } else if (BOOLEAN_P.equals(dataType)) {
      sql = "BOOLEAN";
    } else if (BOOLEAN.equals(dataType)) {
      sql = "BOOLEAN";
    } else if (DECIMAL.equals(dataType)) {
      sql = "DECIMAL";
    } else if (DECIMAL_P.equals(dataType)) {
      sql = "DECIMAL";
    } else if (CLOB.equals(dataType)) {
      sql = "TEXT"; // probably not there for ANSI
    } else if (BLOB.equals(dataType)) {
      sql = "BIT VARYING";
    } else if (BIG_INTEGER.equals(dataType)) {
      sql = "NUMERIC";
    } else if (BIG_DECIMAL.equals(dataType)) {
      sql = "NUMERIC";
    }
    return sql;
  }

  /**
   * <p>
   * Get the MySql 5.1 SQL data type most appropriate for a given datatype
   * namespace data type or Java type. This is the SQL data type you use to
   * create a column in a table. If a standard datatype type has no SQL type,
   * this method throws a RuntimeException.
   * </p>
   * <ul>
   * <li>datatype::String--VARCHAR</li>
   * <li>datatype::Date--DATE</li>
   * <li>datatype::Time--TIME</li>
   * <li>datatype::DateTime--DATETIME</li>
   * <li>datatype::Timestamp--TIMESTAMP</li>
   * <li>datatype::char--CHAR</li>
   * <li>datatype::Character--CHAR</li>
   * <li>datatype::byte--TINYINT</li>
   * <li>datatype::Byte--TINYINT</li>
   * <li>datatype::short--SMALLINT</li>
   * <li>datatype::Short--SMALLINT</li>
   * <li>datatype::int--INTEGER</li>
   * <li>datatype::Integer--INTEGER</li>
   * <li>datatype::long--BIGINT</li>
   * <li>datatype::Long--BIGINT</li>
   * <li>datatype::float--FLOAT</li>
   * <li>datatype::Float--FLOAT</li>
   * <li>datatype::double--NUMERIC (DOUBLE takes no precision or scale)</li>
   * <li>datatype::Double--NUMERIC (DOUBLE takes no precision or scale)</li>
   * <li>datatype::Money--NUMERIC</li>
   * <li>datatype::boolean--BOOLEAN</li>
   * <li>datatype::Boolean--BOOLEAN</li>
   * <li>datatype::Decimal--DECIMAL</li>
   * <li>datatype::decimal--DECIMAL</li>
   * <li>datatype::Clob--TEXT</li>
   * <li>datatype::Blob--BIT VARYING</li>
   * <li>java.math.BigInteger--BIGINT</li>
   * <li>java.math.BigDecimal--NUMERIC</li>
   * <li>java.lang.String--VARCHAR</li>
   * <li>java.util.UUID--VARCHAR</li>
   * </ul>
   * <p>
   * Note that this mapping function replaces the AndroMDA mapping file for the
   * sqlMappingsUri to permit a code generation run to generate SQL output for
   * multiple output targets at once driven by template rather than by the
   * single SQL mapping.
   * </p>
   * 
   * @param dataType the datatype namespace typename
   * 
   * @return the MySQL 5.1 SQL data type preferred for the datatype type or null
   *         if the data type is not in the above list
   */
  public static String getMySql51SqlType(String dataType) {
    if (dataType == null) {
      throw new RuntimeException("Null data type while getting MySql 5.1 SQL data type");
    }

    // Implement all supported types here.
    String sql = null;
    if (STRING.equals(dataType) || JAVA_STRING.equals(dataType)) {
      sql = "VARCHAR";
    } else if (UUID.equals(dataType)) {
      sql = "VARCHAR(50)";
    } else if (DATE.equals(dataType)) {
      sql = "DATE";
    } else if (TIME.equals(dataType)) {
      sql = "TIME";
    } else if (DATETIME.equals(dataType)) {
      sql = "DATETIME";
    } else if (TIMESTAMP.equals(dataType)) {
      sql = "TIMESTAMP";
    } else if (CHAR.equals(dataType)) {
      sql = "CHAR";
    } else if (CHARACTER.equals(dataType)) {
      sql = "CHAR";
    } else if (BYTE_P.equals(dataType)) {
      sql = "TINYINT";
    } else if (BYTE.equals(dataType)) {
      sql = "TINYINT";
    } else if (SHORT_P.equals(dataType)) {
      sql = "SMALLINT";
    } else if (SHORT.equals(dataType)) {
      sql = "SMALLINT";
    } else if (INT_P.equals(dataType)) {
      sql = "INT";
    } else if (INTEGER.equals(dataType)) {
      sql = "INT";
    } else if (LONG_P.equals(dataType)) {
      sql = "BIGINT";
    } else if (LONG.equals(dataType)) {
      sql = "BIGINT";
    } else if (BIG_INTEGER.equals(dataType) ) {
      sql = "BIGINT";
    } else if (FLOAT_P.equals(dataType)) {
      sql = "FLOAT";
    } else if (FLOAT.equals(dataType)) {
      sql = "FLOAT";
    } else if (DOUBLE_P.equals(dataType)) {
      sql = "NUMERIC";
    } else if (DOUBLE.equals(dataType)) {
      sql = "NUMERIC";
    } else if (MONEY.equals(dataType)) {
      sql = "NUMERIC"; // Synonym for DECIMAL
    } else if (BOOLEAN_P.equals(dataType)) {
      sql = "BOOLEAN"; // Synonym for TINYINT(1)
    } else if (BOOLEAN.equals(dataType)) {
      sql = "BOOLEAN"; // Synonym for TINYINT(1)
    } else if (DECIMAL.equals(dataType)) {
      sql = "DECIMAL";
    } else if (DECIMAL_P.equals(dataType)) {
      sql = "DECIMAL";
    } else if (CLOB.equals(dataType)) {
      sql = "TEXT"; // probably not there for ANSI
    } else if (BLOB.equals(dataType)) {
      sql = "BLOB";
    } else if (BIG_INTEGER.equals(dataType)) {
      sql = "BIGINT";
    } else if (BIG_DECIMAL.equals(dataType)) {
      sql = "NUMERIC";
    }
    return sql;
  }

  /**
   * <p>
   * Get the Sybase ASE 12.5 SQL data type most appropriate for a given datatype
   * namespace data type or Java type. This is the SQL data type you use to
   * create a column in a table. If a standard datatype type has no SQL type,
   * this method throws a RuntimeException.
   * </p>
   * <ul>
   * <li>datatype::String--VARCHAR</li>
   * <li>datatype::Date--DATETIME</li>
   * <li>datatype::Time--DATETIME</li>
   * <li>datatype::DateTime--DATETIME</li>
   * <li>datatype::Timestamp--TIMESTAMP</li>
   * <li>datatype::char--CHAR</li>
   * <li>datatype::Character--CHAR</li>
   * <li>datatype::byte--TINYINT</li>
   * <li>datatype::Byte--TINYINT</li>
   * <li>datatype::short--SMALLINT</li>
   * <li>datatype::Short--SMALLINT</li>
   * <li>datatype::int--INTEGER</li>
   * <li>datatype::Integer--INTEGER</li>
   * <li>datatype::long--INTEGER</li>
   * <li>datatype::Long--INTEGER</li>
   * <li>datatype::float--FLOAT</li>
   * <li>datatype::Float--FLOAT</li>
   * <li>datatype::double--DOUBLE PRECISION</li>
   * <li>datatype::Double--DOUBLE PRECISION</li>
   * <li>datatype::Money--MONEY</li>
   * <li>datatype::boolean--BIT</li>
   * <li>datatype::Boolean--BIT</li>
   * <li>datatype::Decimal--DECIMAL</li>
   * <li>datatype::decimal--DECIMAL</li>
   * <li>datatype::Clob--TEXT</li>
   * <li>datatype::Blob--BINARY</li>
   * <li>datatype::BigInteger--NUMERIC</li>
   * <li>datatype::BigDecimal--NUMERIC</li>
   * <li>java.lang.String--VARCHAR</li>
   * <li>java.util.UUID--VARCHAR</li>
   * </ul>
   * <p>
   * Note that this mapping function replaces the AndroMDA mapping file for the
   * sqlMappingsUri to permit a code generation run to generate SQL output for
   * multiple output targets at once driven by template rather than by the
   * single SQL mapping.
   * </p>
   * 
   * @param dataType the datatype namespace typename
   * 
   * @return the Sybase ASE 12.5 SQL data type preferred for the datatype type
   *         or null if the data type is not in the above list
   */
  public static String getSybaseAse125SqlType(String dataType) {
    if (dataType == null) {
      throw new RuntimeException("Null data type while getting Sybase ASE 12.5 SQL data type");
    }

    // Implement all supported types here.
    String sql = null;
    if (STRING.equals(dataType) || JAVA_STRING.equals(dataType)) {
      sql = "VARCHAR";
    } else if (UUID.equals(dataType)) {
      sql = "VARCHAR(50)";
    } else if (DATE.equals(dataType)) {
      sql = "DATETIME";
    } else if (TIME.equals(dataType)) {
      sql = "DATETIME";
    } else if (DATETIME.equals(dataType)) {
      sql = "DATETIME";
    } else if (TIMESTAMP.equals(dataType)) {
      sql = "TIMESTAMP";
    } else if (CHAR.equals(dataType)) {
      sql = "CHAR";
    } else if (CHARACTER.equals(dataType)) {
      sql = "CHAR";
    } else if (BYTE_P.equals(dataType)) {
      sql = "TINYINT";
    } else if (BYTE.equals(dataType)) {
      sql = "TINYINT";
    } else if (SHORT_P.equals(dataType)) {
      sql = "SMALLINT";
    } else if (SHORT.equals(dataType)) {
      sql = "SMALLINT";
    } else if (INT_P.equals(dataType)) {
      sql = "INTEGER";
    } else if (INTEGER.equals(dataType)) {
      sql = "INTEGER";
    } else if (LONG_P.equals(dataType)) {
      sql = "INTEGER";
    } else if (LONG.equals(dataType)) {
      sql = "INTEGER";
    } else if (BIG_INTEGER.equals(dataType) || JAVA_BIG_INTEGER.equals(dataType)) {
      sql = "INTEGER";
    } else if (FLOAT_P.equals(dataType)) {
      sql = "FLOAT";
    } else if (FLOAT.equals(dataType)) {
      sql = "FLOAT";
    } else if (DOUBLE_P.equals(dataType)) {
      sql = "DOUBLE PRECISION";
    } else if (DOUBLE.equals(dataType)) {
      sql = "DOUBLE PRECISION";
    } else if (BIG_DECIMAL.equals(dataType) || JAVA_BIG_DECIMAL.equals(dataType)) {
      sql = "DOUBLE PRECISION";
    } else if (MONEY.equals(dataType)) {
      sql = "MONEY";
    } else if (BOOLEAN_P.equals(dataType)) {
      sql = "BIT";
    } else if (BOOLEAN.equals(dataType)) {
      sql = "BIT";
    } else if (DECIMAL.equals(dataType)) {
      sql = "DECIMAL";
    } else if (DECIMAL_P.equals(dataType)) {
      sql = "DECIMAL";
    } else if (CLOB.equals(dataType)) {
      sql = "TEXT"; // probably not there for ANSI
    } else if (BLOB.equals(dataType)) {
      sql = "BINARY";
    } else if (BIG_INTEGER.equals(dataType)) {
      sql = "NUMERIC";
    } else if (BIG_DECIMAL.equals(dataType)) {
      sql = "NUMERIC";
    }
    return sql;
  }

  /**
   * <p>
   * Get the Oracle11g SQL data type most appropriate for a given datatype
   * namespace data type or Java type. This is the SQL data type you use to
   * create a column in a table. If a standard datatype type has no SQL type,
   * this method throws a RuntimeException.
   * </p>
   * <ul>
   * <li>datatype::String--VARCHAR2</li>
   * <li>datatype::Date--DATE</li>
   * <li>datatype::Time--DATE (not really supported)</li>
   * <li>datatype::DateTime--DATE</li>
   * <li>datatype::Timestamp--TIMESTAMP WITH TIME ZONE</li>
   * <li>datatype::char--CHAR</li>
   * <li>datatype::Character--CHAR</li>
   * <li>datatype::byte--NUMBER(4)</li>
   * <li>datatype::Byte--NUMBER(4)</li>
   * <li>datatype::short--NUMBER(4)</li>
   * <li>datatype::Short--NUMBER(4)</li>
   * <li>datatype::int--NUMBER(12)</li>
   * <li>datatype::Integer--NUMBER(12)</li>
   * <li>datatype::long--NUMBER(18)</li>
   * <li>datatype::Long--NUMBER(18)</li>
   * <li>datatype::float--BINARY_FLOAT</li>
   * <li>datatype::Float--BINARY_FLOAT</li>
   * <li>datatype::double--BINARY_DOUBLE</li>
   * <li>datatype::Double--BINARY_DOUBLE</li>
   * <li>datatype::Money--NUMBER</li>
   * <li>datatype::boolean--NUMBER(1)</li>
   * <li>datatype::Boolean--NUMBER(1)</li>
   * <li>datatype::Decimal--NUMBER</li>
   * <li>datatype::decimal--NUMBER</li>
   * <li>datatype::Clob--CLOB</li>
   * <li>datatype::Blob--BLOB</li>
   * <li>java.math.BigInteger--NUMBER(38)</li>
   * <li>java.math.BigDecimal--NUMBER</li>
   * <li>java.lang.String--VARCHAR2</li>
   * <li>java.util.UUID--VARCHAR2</li>
   * </ul>
   * <p>
   * Note that this mapping function replaces the AndroMDA mapping file for the
   * sqlMappingsUri to permit a code generation run to generate SQL output for
   * multiple output targets at once driven by template rather than by the
   * single SQL mapping.
   * </p>
   * 
   * @param dataType the datatype namespace typename
   * 
   * @return the Oracle11g SQL data type preferred for the datatype type or null
   *         if the data type is not in the above list
   */
  public static String getOracle11SqlType(String dataType) {
    if (dataType == null) {
      throw new RuntimeException("Null data type while getting Oracle11g SQL data type");
    }

    // Implement all supported types here.
    String sql = null;
    if (STRING.equals(dataType) || JAVA_STRING.equals(dataType)) {
      sql = "VARCHAR2";
    } else if (UUID.equals(dataType)) {
      sql = "VARCHAR2(50)";
    } else if (DATE.equals(dataType)) {
      sql = "DATE";
    } else if (TIME.equals(dataType)) {
      sql = "DATE";
    } else if (DATETIME.equals(dataType)) {
      sql = "DATE";
    } else if (TIMESTAMP.equals(dataType)) {
      sql = "TIMESTAMP WITH TIME ZONE";
    } else if (CHAR.equals(dataType)) {
      sql = "CHAR";
    } else if (CHARACTER.equals(dataType)) {
      sql = "CHAR";
    } else if (BYTE_P.equals(dataType)) {
      sql = "NUMBER(4)";
    } else if (BYTE.equals(dataType)) {
      sql = "NUMBER(4)";
    } else if (SHORT_P.equals(dataType)) {
      sql = "NUMBER(4)";
    } else if (SHORT.equals(dataType)) {
      sql = "NUMBER(4)";
    } else if (INT_P.equals(dataType)) {
      sql = "NUMBER(12)";
    } else if (INTEGER.equals(dataType)) {
      sql = "NUMBER(12)";
    } else if (BIG_INTEGER.equals(dataType) || JAVA_BIG_INTEGER.equals(dataType)) {
      sql = "NUMBER(38)";
    } else if (LONG_P.equals(dataType)) {
      sql = "NUMBER(18)";
    } else if (LONG.equals(dataType)) {
      sql = "NUMBER(18)";
    } else if (FLOAT_P.equals(dataType)) {
      sql = "BINARY_FLOAT";
    } else if (FLOAT.equals(dataType)) {
      sql = "BINARY_FLOAT";
    } else if (DOUBLE_P.equals(dataType)) {
      sql = "BINARY_DOUBLE";
    } else if (DOUBLE.equals(dataType)) {
      sql = "BINARY_DOUBLE";
    } else if (MONEY.equals(dataType)) {
      sql = "NUMBER";
    } else if (BOOLEAN_P.equals(dataType)) {
      sql = "NUMBER(1)";
    } else if (BOOLEAN.equals(dataType)) {
      sql = "NUMBER(1)";
    } else if (DECIMAL.equals(dataType)) {
      sql = "NUMBER";
    } else if (DECIMAL_P.equals(dataType)) {
      sql = "NUMBER";
    } else if (CLOB.equals(dataType)) {
      sql = "CLOB"; // probably not there for ANSI
    } else if (BLOB.equals(dataType)) {
      sql = "BLOB";
    } else if (BIG_DECIMAL.equals(dataType) || JAVA_BIG_DECIMAL.equals(dataType)) {
      sql = "NUMBER";
    }
    return sql;
  }

  /**
   * <p>
   * Get the concrete column value type for a standard data type. The concrete
   * column type is a concrete subclass of AbstractColumn and provides column
   * support for primary keys. This method supports the following data types:
   * </p>
   * <ul>
   * <li>BigDecimal--BigDecimalColumnValue</li>
   * <li>BigInteger--BigIntegerColumnValue</li>
   * <li>Integer--IntegerColumnValue</li>
   * <li>Long--LongColumnValue</li>
   * <li>String--StringColumnValue</li>
   * <li>Date--DateColumnValue</li>
   * <li>Timestamp--TimestampColumnValue</li>
   * <li>UUID--UuidColumnValue</li>
   * </ul>
   * 
   * @param type the fully qualified Java data type of the attribute
   * 
   * @return a string representation of a JDBC method call
   */
  public static String getColumnType(String type) {
    if (type == null) {
      throw new RuntimeException("Null data type while getting JDBC get call");
    }

    // Implement all supported types here.
    String valueType = null;
    if (type.equals(JAVA_BIG_INTEGER)) {
      valueType = "BigIntegerColumnValue";
    } else if (type.equals(JAVA_STRING)) {
      valueType = "StringColumnValue";
    } else if (type.equals(JAVA_BIG_DECIMAL)) {
      valueType = "BigDecimalColumnValue";
    } else if (type.equalsIgnoreCase("java.lang.Integer")) {
      valueType = "IntegerColumnValue";
    } else if (type.equalsIgnoreCase("java.lang.Long")) {
      valueType = "LongColumnValue";
    } else if (type.equals("java.sql.Date")) {
      valueType = "DateColumnValue";
    } else if (type.equals("java.sql.Timestamp")) {
      valueType = "TimestampColumnValue";
    } else if (type.equals(UUID)) {
      valueType = "UuidColumnValue";
    } else if (type.contains("java.util.List")
               || type.contains("java.util.Collection")) {
      // to-many attributes of List and Collection don't come from result set
      valueType = null;
    } else {
      throw new RuntimeException("Unsupported Java type "
                                 + type
                                 + " for key column value type ");
    }
    return "com.poesys.db.col." + valueType;
  }

  /**
   * <p>
   * Get the SQL column name defined for a property. This is a default property
   * name unless there is a Persistent stereotype with a name tagged value for
   * the property that specifies a different column name.
   * </p>
   * <p>
   * </p>
   * 
   * @param defaultName the name to which to default if there is no tagged value
   * @param property the UML property from which to extract the tagged value
   * 
   * @see DatabaseUtilities#getPrefixedSqlColumnName(String, String,
   *      org.eclipse.uml2.Property)
   * @return the column name
   */
  public static String getSqlColumnName(String defaultName,
                                        org.eclipse.uml2.Property property) {
    String col = defaultName;

    // Get the name tagged value on the Persistent stereotype, if any.
    String tag = StringUtilities.getExplicitPropertyName(property);
    if (tag != null && tag.length() > 0) {
      col = tag;
    }
    // Truncate to 30 characters, the ANSI standard maximum
    if (col.length() > 30) {
      col = col.substring(0, 29);
    }
    return col;
  }

  /**
   * <p>
   * Get the SQL column name defined for a property in a context that requires a
   * prefix to make the name unique (such as a recursive association). The name
   * is the default name unless there is a Persistent name associated with the
   * property end, in which case the name is the base name prefixed with the
   * Persistent name.
   * </p>
   * <p>
   * See the getSqlColumnName() method for situations that do not require unique
   * prefixing. The Persistent name in that situation becomes the column name.
   * </p>
   * 
   * @param defaultName the name to which to default if there is no tagged value
   * @param baseName the name to prefix with a tagged value
   * @param property the UML property from which to extract tagged value
   * 
   * @see #getSqlColumnName(String, org.eclipse.uml2.Property)
   * @return the column name
   */
  public static String getPrefixedSqlColumnName(String defaultName,
                                                String baseName,
                                                org.eclipse.uml2.Property property) {
    String col = defaultName;
    String prefix = property.getName();
    if (prefix != null && prefix.length() > 0) {
      col = StringUtilities.buildVariableName(baseName, prefix, null);
    }

    return col;
  }

  /**
   * <p>
   * Add a discriminant expression to the SQL select list in a string builder
   * instance.
   * </p>
   * <p>
   * A discriminant expression returns the name of the class that is the
   * lowest-level concrete class in a class hierarchy query. For example, say
   * you have classes A, B, C, and D. C and D are subclasses of B, which is a
   * subclass of A. A class hierarchy query of A outer-joins B and C to A using
   * the shared keys. The discriminant determines which concrete class is not
   * null. The discriminant tests for NOT NULL in the order the classes appear
   * in the subclass list and take into account only those classes that are
   * concrete. If all the classes are null, the discriminant will be null.
   * </p>
   * 
   * <pre>
   * SELECT a.AId, a.ACol, b.BCol, c.CCol, d.dCol, 
   *        CASE 
   *          WHEN c.AId IS NOT NULL THEN 'C'
   *          WHEN d.AId IS NOT NULL THEN 'D'
   *          WHEN b.AId IS NOT NULL THEN 'B'
   *          ELSE NULL
   *        END AS discriminant
   *   FROM A AS a LEFT OUTER JOIN 
   *        B as b ON a.AId = b.AId LEFT OUTER JOIN
   *        C as c ON a.AId = c.AId LEFT OUTER JOIN
   *        D as d ON a.AId = d.AId
   * </pre>
   * 
   * @param builder the string builder containing the preliminary expression
   *          list
   * @param subclasses a depth-first, ordered list of subclass DTOs for which to
   *          produce discriminant tests in the CASE statement
   * @param keyProperties an unordered collection of key properties from which
   *          to extract the key columns in the WHEN expressions
   */
  @SuppressWarnings("unchecked")
  public static void addDiscriminant(StringBuilder builder, List subclasses,
                                     Collection keyProperties) {
    builder.append(", CASE");
    for (Object o : subclasses) {
      Dto dto = (Dto)o;
      if (!dto.isAbstractClass()) {
        for (Object k : keyProperties) {
          Property key = (Property)k;
          builder.append(" WHEN ");
          builder.append(dto.getName());
          builder.append(".");
          builder.append(key.getSqlColumnName());
          builder.append(" IS NOT NULL THEN ");
          builder.append("'");
          builder.append(dto.getName());
          builder.append("'");
        }
      }
    }
    builder.append(" ELSE NULL END AS discriminant");
  }
}
