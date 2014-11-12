package com.poesys.cartridges.db.profile;


import org.andromda.core.profile.Profile;


/**
 * Provides the Poesys/DB stereotypes and tagged values as Java constants
 * 
 * @author Robert J. Muller
 */
public class PoesysDbProfile {
  /**
   * The Profile instance from which we retrieve the mapped profile names.
   */
  private static final Profile profile = Profile.instance();

  // ----------------- UML Namespaces ---------------------
  /**
   * The primary key stereotype namespace
   */
  public static final String NAMESPACE_KEY = "key::";

  /**
   * The persistence stereotype namespace
   */
  public static final String NAMESPACE_PERSISTENCE = "persistence::";

  /**
   * The memory stereotype namespace
   */
  public static final String NAMESPACE_MEMORY = "memory::";

  // ----------------- Key Stereotypes --------------------

  /**
   * Stereotype indicating attribute is part of alternate key
   */
  public static final String STEREOTYPE_AK = profile.get("AK");

  /**
   * Stereotype indicating primary key for association
   */
  public static final String STEREOTYPE_ASSOCIATION_KEY =
    profile.get("ASSOCIATION_KEY");

  /**
   * Stereotype indicating primary key for composite class (parent plus child)
   */
  public static final String STEREOTYPE_COMPOSITE_KEY =
    profile.get("COMPOSITE_KEY");

  /**
   * Stereotype indicating primary key using GUID column value
   */
  public static final String STEREOTYPE_GUID_KEY = profile.get("GUID_KEY");

  /**
   * Stereotype indicating primary key using identity column value
   */
  public static final String STEREOTYPE_IDENTITY_KEY =
    profile.get("IDENTITY_KEY");

  /**
   * Stereotype indicating primary key comprising one or more data members
   */
  public static final String STEREOTYPE_NATURAL_KEY =
    profile.get("NATURAL_KEY");

  /**
   * Stereotype indicating an attribute is part of the primary key, either in a
   * natural key or a composite key
   */
  public static final String STEREOTYPE_PK = profile.get("PK");

  /**
   * Stereotype indicating primary key using sequence-generated values
   */
  public static final String STEREOTYPE_SEQUENCE_KEY =
    profile.get("SEQUENCE_KEY");

  // ----------------- Persistence Stereotypes --------------------

  /**
   * Stereotype indicating that a class is persistent
   */
  public static final String STEREOTYPE_PERSISTENT = profile.get("PERSISTENT");

  /**
   * Stereotype indicating that a persistent object of a class cannot be changed
   * after creation
   */
  public static final String STEREOTYPE_IMMUTABLE = profile.get("IMMUTABLE");

  /**
   * Stereotype indicating that a persistent object of a class cannot be removed
   * from the persistent store after creation
   */
  public static final String STEREOTYPE_UNREMOVABLE =
    profile.get("UNREMOVABLE");

  /**
   * Stereotype indicating that a property is to load lazily rather than
   * immediately
   */
  public static final String STEREOTYPE_LAZY = profile.get("LAZY");

  /**
   * Stereotype indicating that a property is a number
   */
  public static final String STEREOTYPE_NUMBER = profile.get("NUMBER");

  /**
   * Stereotype indicating that a property is a boolean with a string
   * representation in the persistent store
   */
  public static final String STEREOTYPE_BOOLEAN_STRING =
    profile.get("BOOLEAN_STRING");

  /**
   * Stereotype indicating that a property is a multiple-unit object with a size
   * or length in units
   */
  public static final String STEREOTYPE_SIZED_PROPERTY =
    profile.get("SIZED_PROPERTY");

  /**
   * Stereotype indicating that a package is a subsystem of persistent classes
   * that contains a factory for helping to generate objects of those classes
   */
  public static final String STEREOTYPE_SUBSYSTEM = profile.get("SUBSYSTEM");

  /**
   * Stereotype indicating that a generalization is between classes that are
   * implemented in different databases and hence cannot have foreign key
   * constraints that enforce the association.
   */
  public static final String STEREOTYPE_CROSS_DATABASE =
    profile.get("CROSS_DATABASE");

  // ----------------- Memory Stereotypes --------------------

  /**
   * Stereotype indicating that a subsystem uses direct memory management
   * without caching.
   */
  public static final String STEREOTYPE_DIRECT = profile.get("DIRECT");

  /**
   * Stereotype indicating that a subsystem uses direct memory management
   * without caching.
   */
  public static final String STEREOTYPE_CACHED = profile.get("CACHED");

  /**
   * Stereotype indicating that a subsystem uses direct memory management
   * without caching.
   */
  public static final String STEREOTYPE_CLUSTERED = profile.get("CLUSTERED");

  /**
   * Stereotype indicating that a subsystem uses direct memory management
   * without caching.
   */
  public static final String STEREOTYPE_DISTRIBUTED =
    profile.get("DISTRIBUTED");

  // ----------------- Tagged Values --------------------

  /**
   * AK stereotype: The name of a group of alternate key attributes to which the
   * current attribute belongs; corresponds to a UNIQUE constraint in a SQL
   * database
   */
  public static final String TAGGEDVALUE_UNIQUE_GROUP =
    profile.get("UNIQUE_GROUP");

  /**
   * Number stereotype: The number of digits possible for a number property
   */
  public static final String TAGGEDVALUE_PRECISION = profile.get("PRECISION");

  /**
   * Persistent stereotype: The number of digits after the decimal point for a
   * number property
   */
  public static final String TAGGEDVALUE_FETCH_SIZE = profile.get("FETCH_SIZE");

  /**
   * Persistent stereotype: The number of digits after the decimal point for a
   * number property
   */
  public static final String TAGGEDVALUE_BATCH_SIZE = profile.get("BATCH_SIZE");

  /**
   * Number stereotype: The number of digits after the decimal point for a
   * number property
   */
  public static final String TAGGEDVALUE_SCALE = profile.get("SCALE");

  /**
   * <ul>
   * <li>Persistent stereotype: The database name of a persistent class or
   * property</li>
   * <li>AssocationKey stereotype: The name of the collection of association
   * objects in the associated classes</li>
   * </ul>
   */
  public static final String TAGGEDVALUE_NAME = profile.get("NAME");

  /**
   * <ul>
   * <li>Persistent stereotype: The name of the object in the association class
   * that corresponds to the association end to which the tagged value is
   * attached; allows naming of the association-class object separately from the
   * association collections in the associated classes.</li>
   * </ul>
   */
  public static final String TAGGEDVALUE_OBJECT_NAME =
    profile.get("OBJECT_NAME");

  /**
   * <ul>
   * <li>Identity stereotype: The column name for the generated primary key for
   * an identity-key class</li>
   * <li>Sequence stereotype: The column name for the generated primary key for
   * a sequence-key class</li>
   * <li>Guid stereotype: The column name for the generated primary key for a
   * GUID-key class</li>
   * </ul>
   */
  public static final String TAGGEDVALUE_GENERATED_NAME =
    profile.get("GENERATED_NAME");

  /**
   * <ul>
   * <li>Sequence stereotype: The name of the Oracle or MySQL sequence that
   * generates primary key values for a sequence-key class</li>
   * </ul>
   */
  public static final String TAGGEDVALUE_SEQUENCE_NAME =
    profile.get("SEQUENCE_NAME");

  /**
   * Persistent stereotype: The database name of a persistent class or property
   */
  public static final String TAGGEDVALUE_READ_ONLY = profile.get("READ_ONLY");

  /**
   * Persistent stereotype: expiration time in milliseconds of an object in a
   * cache that supports object expiration; default (null or 0) is
   * Integer.MAX_VALUE; -1 means immediate expiration, no caching
   */
  public static final String TAGGEDVALUE_EXPIRATION = profile.get("EXPIRATION");

  /**
   * SizedProperty stereotype: The number of characters permitted for a String
   * or Blob property
   */
  public static final String TAGGEDVALUE_LENGTH = profile.get("LENGTH");

  /**
   * BooleanString stereotype: The two-valued string representation for a
   * boolean value, with the first string representing true and the second
   * representing false
   */
  public static final String TAGGEDVALUE_REP = profile.get("REPRESENTATION");

  /**
   * AssociationKey stereotype: whether to load the collection of association
   * class objects (link objects, as opposed to the association-end collections
   * of associated objects) lazily or eagerly
   */
  public static final String TAGGEDVALUE_LAZY = profile.get("LAZY_LINKS");

  /**
   * Subsystem stereotype: the full package name of the business layer package
   * that corresponds to the database package identified by the Subsystem
   * object.
   */
  public static final String TAGGEDVALUE_BS_PACKAGE =
    profile.get("BUSINESS_PACKAGE");

  /**
   * PK stereotype: whether to treat the class to which the association end
   * belongs as a child; set to false when the two classes are in different
   * subsystems that will be implemented in different databases.
   */
  public static final String TAGGEDVALUE_PK_CHILD = profile.get("PK_CHILD");

  /**
   * Distributed stereotype: the name of the software package that implements
   * distributed caching, such as "memcached"
   */
  public static final String TAGGEDVALUE_SOFTWARE =
    profile.get("SOFTWARE");

  // ----------------- Default Values --------------------

  /**
   * Default subsystem name, supplied when no subsystem designated
   */
  public static final String DEFAULT_SUBSYSTEM = "default";

  /**
   * Disable default constructor.
   */
  private PoesysDbProfile() {
  }
}