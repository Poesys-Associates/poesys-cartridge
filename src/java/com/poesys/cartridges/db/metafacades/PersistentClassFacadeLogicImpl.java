package com.poesys.cartridges.db.metafacades;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.andromda.core.metafacade.MetafacadeBase;
import org.andromda.metafacades.emf.uml2.AssociationEnd;
import org.andromda.metafacades.uml.ClassifierFacade;
import org.andromda.metafacades.uml.GeneralizableElementFacade;
import org.andromda.metafacades.uml.TypeMappings;
import org.andromda.metafacades.uml.UMLMetafacadeProperties;
import org.andromda.metafacades.uml.UMLProfile;
import org.andromda.utils.StringUtilsHelper;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.uml2.AggregationKind;
import org.eclipse.uml2.Association;
import org.eclipse.uml2.AssociationClass;
import org.eclipse.uml2.Class;
import org.eclipse.uml2.Classifier;
import org.eclipse.uml2.Comment;
import org.eclipse.uml2.Package;
import org.eclipse.uml2.Stereotype;
import org.eclipse.uml2.Type;
import org.eclipse.uml2.impl.AssociationClassImpl;
import org.eclipse.uml2.impl.ClassImpl;
import org.eclipse.uml2.impl.ModelImpl;

import com.poesys.cartridges.db.InvalidParametersException;
import com.poesys.cartridges.db.profile.PoesysDbProfile;
import com.poesys.cartridges.db.psm.db.AssociatedKey;
import com.poesys.cartridges.db.psm.db.AssociatedKeyComparator;
import com.poesys.cartridges.db.psm.db.AssociatedKeyImpl;
import com.poesys.cartridges.db.psm.db.Dto;
import com.poesys.cartridges.db.psm.db.DtoImpl;
import com.poesys.cartridges.db.psm.db.KeyType;
import com.poesys.cartridges.db.psm.db.ParameterizedQuery;
import com.poesys.cartridges.db.psm.db.ParameterizedQueryImpl;
import com.poesys.cartridges.db.psm.db.Property;
import com.poesys.cartridges.db.psm.db.PropertyImpl;
import com.poesys.cartridges.db.psm.db.Subsystem;
import com.poesys.cartridges.db.utilities.DatabaseUtilities;
import com.poesys.cartridges.db.utilities.KeyPropertyComparator;
import com.poesys.cartridges.db.utilities.StereotypeUtilities;
import com.poesys.cartridges.db.utilities.StringUtilities;


/**
 * <p>
 * MetafacadeLogic implementation for
 * com.poesys.cartridges.db.metafacades.PersistentClassFacade.
 * </p>
 * <p>
 * Note that many of the static-final constants and internal methods have
 * <strong>package</strong> visibility. This allows the
 * PersistentAssociationClassFacadeLogicImpl class to access the methods to
 * support "inheritance" through wrapping of an instance of this class within
 * the association class metafacade.
 * </p>
 * 
 * @see com.poesys.cartridges.db.metafacades.PersistentClassFacade
 */
public class PersistentClassFacadeLogicImpl extends PersistentClassFacadeLogic {
  /** Logger for this class */
  Logger logger = Logger.getLogger(PersistentClassFacadeLogicImpl.class);

  /**
   * Cache for DTOs. The cache optimizes DTO creation so that a DTO gets created
   * precisely once in each run. It also enables the use of two-way navigable
   * associations that otherwise would create infinite loops of nested DTOs and
   * subsystems. This is package scoped so the association class metafacade can
   * access it.
   */
  static final ConcurrentHashMap<String, Dto> cache =
    new ConcurrentHashMap<String, Dto>();

  // Note that all the constants are package scoped to allow the other
  // metafacades to use them.

  /**
   * The data type for an identity or sequence key data member
   */
  static final String GENERATED_KEY_TYPE = "java.math.BigInteger";
  /**
   * The comment text for an identity or sequence key data member
   */
  static final String GENERATED_KEY_COMMENT =
    "   * <p>\n   * Primary key attribute\n   * </p>";
  /**
   * The data type for an identity or sequence key data member
   */
  static final String GENERATED_UUID_KEY_TYPE = "java.util.UUID";
  /**
   * The param tag text for an identity or sequence key data member
   */
  static final String GENERATED_KEY_TAG = "primary key attribute";
  /**
   * The comment text for a composite subkey data member
   */
  private static final String COMPOSITE_SUPERKEY_COMMENT =
    "   * <p>\n   * Composite super-key attribute that uniquely identifies child combined with child sub-key and any other parent super-keys\n   * </p>";
  /**
   * The param tag text for a composite subkey data member
   */
  private static final String COMPOSITE_SUPERKEY_TAG =
    "composite super-key attribute that uniquely identifies child combined with child sub-key and any other parent super-keys";
  /**
   * The data type for an ordered composite subkey data member
   */
  private static final String COMPOSITE_ORDERED_SUBKEY_TYPE =
    "java.math.BigInteger";
  /**
   * The data type for an unordered composite subkey data member
   */
  private static final String COMPOSITE_UNORDERED_SUBKEY_TYPE =
    "java.lang.String";
  /**
   * The comment text for a composite subkey data member
   */
  private static final String COMPOSITE_SUBKEY_COMMENT =
    "   * <p>\n   * Composite subkey attribute that uniquely identifies child combined with parent key\n   * </p>";
  /**
   * The param tag text for a composite subkey data member
   */
  private static final String COMPOSITE_SUBKEY_TAG =
    "composite subkey attribute that uniquely identifies child combined with parent key";
  /**
   * The comment text for a composite subkey data member
   */
  static final String ASSOCIATION_CLASS_COMMENT =
    "   * <p>\n   * Collection of association class objects (not the associated objects)\n   * </p>";
  /**
   * The param tag text for a composite subkey data member
   */
  static final String ASSOCIATION_CLASS_TAG =
    "collection of association class objects (not the associated objects)";
  /**
   * The comment text for a to-one association data member
   */
  static final String TO_ONE_COMMENT =
    "   * <p>\n   * Foreign key used by setter to query associated object\n   * </p>";
  /**
   * The parameter tag text for a to-one association data member
   */
  static final String TO_ONE_TAG =
    "foreign key used by setter to query associated object";
  /**
   * The comment text for a to-one association data member used as a subkey
   */
  static final String FOREIGN_SUBKEY_COMMENT =
    "   * <p>\n   * Foreign key used as primary sub-key in composite\n   * </p>";
  /**
   * The comment text for a to-one association data member used as a subkey
   */
  static final String FOREIGN_PRIMARY_KEY_COMMENT =
    "   * <p>\n   * Foreign key used as primary key in association class\n   * </p>";

  /**
   * The comment text for a to-one association data member used as a subkey
   */
  static final String FOREIGN_PRIMARY_KEY_TAG =
    "foreign key used as primary key in association class";

  /** The ColumnValue type for BigInteger columns */
  static final String BIG_INT_COL_TYPE =
    "com.poesys.db.col.BigIntegerColumnValue";
  /** The ColumnValue type for String columns */
  static final String STRING_COL_TYPE = "com.poesys.db.col.StringColumnValue";
  /** The fully qualified BigInteger type as a string */
  static final String BIG_INTEGER = "java.math.BigInteger";
  /** The fully qualified BigDecimal type as a string */
  static final String BIG_DECIMAL = "java.math.BigDecimal";
  /** The fully qualified String type as a string */
  static final String STRING = "java.lang.String";
  /** The fully qualified Uuid type as a string */
  static final String UUID = "java.util.Uuid";

  /** Lookup string for use-interface-for-association option */
  private static final String INTERFACE = "useInterfaceForAssociation";

  /** PK stereotype name */
  private static final String PK = PoesysDbProfile.NAMESPACE_KEY
                                   + PoesysDbProfile.STEREOTYPE_PK;
  /** CompositeKey stereotype name */
  private static final String COMPOSITE =
    PoesysDbProfile.NAMESPACE_KEY + PoesysDbProfile.STEREOTYPE_COMPOSITE_KEY;
  /** IdentityKey stereotype name */
  private static final String IDENTITY =
    PoesysDbProfile.NAMESPACE_KEY + PoesysDbProfile.STEREOTYPE_IDENTITY_KEY;
  /** IdentityKey stereotype name */
  private static final String SEQUENCE =
    PoesysDbProfile.NAMESPACE_KEY + PoesysDbProfile.STEREOTYPE_SEQUENCE_KEY;
  /** GuidKey stereotype name */
  private static final String GUID = PoesysDbProfile.NAMESPACE_KEY
                                     + PoesysDbProfile.STEREOTYPE_GUID_KEY;
  /** AssociationKey stereotype name */
  private static final String ASSOCIATION =
    PoesysDbProfile.NAMESPACE_KEY + PoesysDbProfile.STEREOTYPE_ASSOCIATION_KEY;
  /** Persistent stereotype name */
  private static final String PERSISTENT =
    PoesysDbProfile.NAMESPACE_PERSISTENCE
        + PoesysDbProfile.STEREOTYPE_PERSISTENT;
  /** Lazy stereotype name */
  private static final String LAZY = PoesysDbProfile.NAMESPACE_PERSISTENCE
                                     + PoesysDbProfile.STEREOTYPE_LAZY;
  /** Immutable stereotype name */
  private static final String IMMUTABLE =
    PoesysDbProfile.NAMESPACE_PERSISTENCE
        + PoesysDbProfile.STEREOTYPE_IMMUTABLE;
  /** Unremovable stereotype name */
  private static final String UNREMOVABLE =
    PoesysDbProfile.NAMESPACE_PERSISTENCE
        + PoesysDbProfile.STEREOTYPE_UNREMOVABLE;
  /** Subsystem stereotype name */
  private static final String SUBSYSTEM =
    PoesysDbProfile.NAMESPACE_PERSISTENCE
        + PoesysDbProfile.STEREOTYPE_SUBSYSTEM;
  /** CrossDatabase stereotype name */
  private static final String CROSS_DATABASE =
    PoesysDbProfile.NAMESPACE_PERSISTENCE
        + PoesysDbProfile.STEREOTYPE_CROSS_DATABASE;
  /** BooleanString stereotype name */
  private static final String BOOLEAN_STRING =
    PoesysDbProfile.NAMESPACE_PERSISTENCE
        + PoesysDbProfile.STEREOTYPE_BOOLEAN_STRING;

  /** The metafacade context for instantiated metafacades */
  private static final String CONTEXT =
    PersistentClassFacadeLogicImpl.class.getName();

  // List of associated DTOs from PersistentAssociationClassFacade
  List<Dto> associatedDtos = null;
  // List of association keys from PersistentAssociationClassFacade
  List<AssociatedKey> associatedKeys = null;
  // List of all foreign keys
  List<AssociatedKey> foreignKeys = null;

  /*
   * Property creation strategies for primary key properties. These strategies
   * correspond to the associations from Dto to Property in the metafacade PSM
   * UML diagram. These lists are all package scoped to be accessible to other
   * metafacades that delegate to this one. You must add any new strategy you
   * create to the allStrategies list as well as to the relevant other lists.
   */

  /**
   * List of strategies for deriving all the DTO properties
   */
  List<IAddPropertyStrategy> allDtoPropertyStrategies =
    new ArrayList<IAddPropertyStrategy>(22);

  /**
   * List of strategies for deriving key properties of the DTO
   */
  List<IAddPropertyStrategy> keyStrategies =
    new ArrayList<IAddPropertyStrategy>(8);

  /**
   * List of strategies for deriving sub-key properties of a composite
   * aggregation DTO
   */
  List<IAddPropertyStrategy> subKeyStrategies =
    new ArrayList<IAddPropertyStrategy>(3);

  /**
   * List of strategies for deriving the properties used in the constructor
   * method of the DTO
   */
  List<IAddPropertyStrategy> constructorArgStrategies =
    new ArrayList<IAddPropertyStrategy>(12);

  /**
   * List of strategies for deriving the properties used as randomly generated
   * test variables in the JUnit test classes create() methods
   */
  List<IAddPropertyStrategy> testVarStrategies =
    new ArrayList<IAddPropertyStrategy>(11);

  /**
   * List of strategies for deriving the SQL columns of the DTO table
   */
  List<IAddPropertyStrategy> columnStrategies =
    new ArrayList<IAddPropertyStrategy>(10);

  /**
   * List of strategies for deriving the properties used in the constructor
   * method of the DTO, which assigns the constructor args to data members
   */
  List<IAddPropertyStrategy> localMemberStrategies =
    new ArrayList<IAddPropertyStrategy>(9);

  /**
   * List of strategies for deriving the data members of the DTO and their
   * accessors
   */
  List<IAddPropertyStrategy> dataMemberStrategies =
    new ArrayList<IAddPropertyStrategy>(15);

  /**
   * List of strategies for deriving the single-object data members of the DTO
   * and their set strategies
   */
  List<IAddPropertyStrategy> objectStrategies =
    new ArrayList<IAddPropertyStrategy>(2);

  /**
   * List of strategies for deriving the multiple-object data members of the DTO
   * and their set strategies
   */
  List<IAddPropertyStrategy> collectionStrategies =
    new ArrayList<IAddPropertyStrategy>(4);

  /**
   * List of strategies for deriving the inherited properties of the proxy and
   * business DTO wrappers.
   */
  List<IAddPropertyStrategy> inheritedPropertyStrategies =
    new ArrayList<IAddPropertyStrategy>(4);

  /**
   * List of strategies for deriving the inherited properties of the DTO; used
   * in the DTO constructor and super() call.
   */
  List<IAddPropertyStrategy> inheritedConstructorArgStrategies =
    new ArrayList<IAddPropertyStrategy>(3);

  /**
   * List of strategies for deriving the child properties of the DTO; these are
   * the properties corresponding to composite aggregations (children)
   */
  List<IAddPropertyStrategy> childPropertyStrategies =
    new ArrayList<IAddPropertyStrategy>(4);

  /**
   * List of strategies for deriving the properties used in an INSERT or UPDATE
   * statement
   */
  List<IAddPropertyStrategy> insertPropertyStrategies =
    new ArrayList<IAddPropertyStrategy>(10);

  /*
   * The following nested classes provide the mutually exclusive sets of
   * properties that you combine into the various property lists that the
   * templates use. Each class contains a strategy for adding a specific set of
   * properties to an input list. All of these classes have package visibility
   * to enable the PersistentAssociationClassFacade to use them.
   */

  /**
   * Add the natural-key properties to a property list. The natural-key
   * properties are the properties explicitly marked with a &lt;&lt;PK&gt;&gt;
   * stereotype in the set of attributes in the UML diagram (excluding to-one
   * association attributes and explicit sub-key attributes).
   * 
   * @see PersistentClassFacadeLogicImpl.AddExplicitAssociationKeyProperties
   * @see PersistentClassFacadeLogicImpl.AddExplicitSubKeyProperties
   */
  class AddNaturalKeyProperties implements IAddPropertyStrategy {

    public void addProperties(List<Property> list) {
      // Exclude composite keys.
      if (!hasKeyType(KeyType.COMPOSITE)) {
        for (Object o : metaObject.getOwnedAttributes()) {
          org.eclipse.uml2.Property p = (org.eclipse.uml2.Property)o;
          PersistentPropertyFacadeLogicImpl facade = null;
          facade = new PersistentPropertyFacadeLogicImpl(p, CONTEXT);
          // Add <<PK>> properties that are not associations.
          if (p.getAppliedStereotype(PK) != null && p.getAssociation() == null) {
            Property property = facade.handleTransformToProperty();
            property.setSource("AddNaturalKeyProperties");
            list.add(property);
            // Set the owner type to be the current DTO type.
            ((PropertyImpl)property).setOwnerType(metaObject);
          }
        }
      }
    }
  }

  /**
   * <p>
   * Add the inherited-key properties to a property list. Inherited-key
   * properties are the attributes defined in the top-level superclass of the
   * current class that comprise the primary key of the class hierarchy. All
   * subclasses have the same primary key attributes inherited from the root
   * persistent superclass. The subclasses must not have any key stereotype. You
   * can thus override inherited keys by adding a key stereotype to a subclass.
   * As well, the subclasses inherit any sequenceName tagged value for the
   * SequenceKey stereotype on the root class.
   * </p>
   * <p>
   * Note that this nested class does not extend the
   * AddInheritedPropertyStrategy implementation of IAddPropertyStrategy, which
   * gets properties from all the superclasses up to the root rather than just
   * from the root as this one does.
   * </p>
   */
  class AddInheritedKeyProperties implements IAddPropertyStrategy {

    @SuppressWarnings("unchecked")
    public void addProperties(List<com.poesys.cartridges.db.psm.db.Property> list) {
      if (getKeyType().equals(KeyType.NONE)) {
        Dto dto = PersistentClassFacadeLogicImpl.this.getRootSuperclass();
        if (dto != null) {
          // Set the inherited key properties.
          Collection<Property> properties = dto.getKeyProperties();
          for (Property p : properties) {
            // Copy the property.
            try {
              PropertyImpl copy = new PropertyImpl(p);
              copy.setSource(p.getSource() + " + AddInheritedKeyProperties");
              // Set the owner type to the current DTO type.
              copy.setOwnerType(metaObject);
              // Note: property type remains the same, no change needed.
              list.add(copy);
            } catch (InvalidParametersException e) {
              logger.error("Cannot copy null property", e);
              throw new RuntimeException(e.getMessage(), e);
            }
          }
        }
      }
    }
  }

  /**
   * Add the generated-key properties to a property list. The generated keys are
   * the sequence or identity keys, named with the class name plus "Id". These
   * are BigInteger/Long data elements to allow for the maximum possible number
   * of ids, or a .
   */
  class AddGeneratedKeyProperties implements IAddPropertyStrategy {

    public void addProperties(List<com.poesys.cartridges.db.psm.db.Property> list) {
      Stereotype stereotype = getGeneratedIntegerKeyStereotype();
      // Proceed only for generated-key stereotypes.
      if (stereotype != null) {
        String name = StringUtilities.buildVariableName(getName(), null, "Id");
        // Set the stereotype.
        // Get any explicit name on the superclass.
        String explicitName =
          StringUtilities.getExplicitGeneralizationName(stereotype, metaObject);
        PropertyImpl property =
          new PropertyImpl(name,
                           null,
                           null,
                           null,
                           null,
                           false,
                           true,
                           GENERATED_KEY_TYPE,
                           GENERATED_KEY_COMMENT,
                           "public",
                           false,
                           true,
                           null, // no default value for key
                           GENERATED_KEY_TAG,
                           getSqlMappings().getTo("datatype::Long"),
                           DatabaseUtilities.getColumnType(GENERATED_KEY_TYPE),
                           getSubsystem(findSubsystemPackage()).getName(),
                           false,
                           "AddGeneratedKeyProperties",
                           getTableName(metaObject),
                           false,
                           false,
                           GENERATED_KEY_TYPE,
                           true,
                           false,
                           null,
                           false,
                           name,
                           10,
                           100,
                           GENERATED_KEY_TYPE,
                           DatabaseUtilities.getAnsiSqlType(GENERATED_KEY_TYPE),
                           DatabaseUtilities.getMySql51SqlType(GENERATED_KEY_TYPE),
                           DatabaseUtilities.getOracle11SqlType(GENERATED_KEY_TYPE),
                           DatabaseUtilities.getSybaseAse125SqlType(GENERATED_KEY_TYPE),
                           false,
                           explicitName,
                           null,
                           false,
                           null,
                           null,
                           null);

        // Set the property type to be null as there is no object type.
        property.setPropertyType(null);
        // Set the owner type to be the current DTO type.
        property.setOwnerType(metaObject);

        list.add(property);
      }

    }

    /**
     * Get the applied stereotype for the current class if it is one of the
     * three generated-key stereotypes: &laquo;Identity&raquo;,
     * &laquo;Sequence&raquo;, or &laquo;Guid&raquo;.
     * 
     * @return the UML stereotype or null if none of the three stereotypes is
     *         present
     */
    private Stereotype getGeneratedIntegerKeyStereotype() {
      Stereotype stereotype = metaObject.getAppliedStereotype(IDENTITY);
      if (stereotype == null) {
        stereotype = metaObject.getAppliedStereotype(SEQUENCE);
      }
      return stereotype;
    }
  }

  /**
   * Add the generated-GUID-key properties to a property list. The generated
   * GUID keys are the GUID keys, named with the class name plus "Id". These are
   * Uuid/String data elements that allow a very large number of ids.
   */
  class AddGeneratedGuidKeyProperties implements IAddPropertyStrategy {

    public void addProperties(List<com.poesys.cartridges.db.psm.db.Property> list) {
      Stereotype stereotype = getGeneratedGuidKeyStereotype();
      // Proceed only for GUID-key stereotypes.
      if (stereotype != null) {
        String name = StringUtilities.buildVariableName(getName(), null, "Id");
        // Set the stereotype.
        // Get any explicit name on the superclass.
        String explicitName =
          StringUtilities.getExplicitGeneralizationName(stereotype, metaObject);
        PropertyImpl property =
          new PropertyImpl(name,
                           null,
                           null,
                           null,
                           null,
                           false,
                           true,
                           GENERATED_UUID_KEY_TYPE,
                           GENERATED_KEY_COMMENT,
                           "public",
                           false,
                           true,
                           null, // no default value for key
                           GENERATED_KEY_TAG,
                           getSqlMappings().getTo("datatype::String"),
                           DatabaseUtilities.getColumnType(GENERATED_UUID_KEY_TYPE),
                           getSubsystem(findSubsystemPackage()).getName(),
                           false,
                           "AddGeneratedGuidKeyProperties",
                           getTableName(metaObject),
                           false,
                           false,
                           GENERATED_UUID_KEY_TYPE,
                           true,
                           false,
                           null,
                           false,
                           name,
                           10,
                           100,
                           GENERATED_UUID_KEY_TYPE,
                           DatabaseUtilities.getAnsiSqlType(GENERATED_UUID_KEY_TYPE),
                           DatabaseUtilities.getMySql51SqlType(GENERATED_UUID_KEY_TYPE),
                           DatabaseUtilities.getOracle11SqlType(GENERATED_UUID_KEY_TYPE),
                           DatabaseUtilities.getSybaseAse125SqlType(GENERATED_UUID_KEY_TYPE),
                           false,
                           explicitName,
                           null,
                           false,
                           null,
                           null,
                           null);

        // Set the property type to be null as there is no object type.
        property.setPropertyType(null);
        // Set the owner type to be the current DTO type.
        property.setOwnerType(metaObject);

        list.add(property);
      }

    }

    /**
     * Get the applied stereotype for the current class if it is a generated
     * &laquo;Guid&raquo; key.
     * 
     * @return the UML stereotype or null if none of the three stereotypes is
     *         present
     */
    private Stereotype getGeneratedGuidKeyStereotype() {
      return metaObject.getAppliedStereotype(GUID);
    }
  }

  /**
   * Add the parent-key attributes to a property list. The parent-key attributes
   * are the key properties that represent the parent component of a composite
   * aggregation key as attributes (as opposed to the key object itself). See
   * the various sub-key classes to get the child component of the key.
   * 
   * @see PersistentClassFacadeLogicImpl.AddExplicitSubKeyProperties
   * @see PersistentClassFacadeLogicImpl.AddOrderedSubKeyProperties
   * @see PersistentClassFacadeLogicImpl.AddUnorderedSubKeyProperties
   */
  class AddParentKeyAttributes implements IAddPropertyStrategy {

    public void addProperties(List<Property> list) {
      // Proceed only for composite keys.
      if (metaObject.getAppliedStereotype(COMPOSITE) != null) {
        // Add the key properties of the parent for the super-key.
        Collection<Property> parentProperties = getParentClassKeyAttributes();
        if (parentProperties == null || parentProperties.size() == 0) {
          // No parent keys, something's wrong.
          String errMsg =
            "Class "
                + getName()
                + " is composite but has no parent key from parent class";
          logger.error(errMsg);
          throw new RuntimeException(errMsg);
        }
        for (Property property : parentProperties) {
          if (property.isKey()) {
            // Copy the property and set the comments.
            PropertyImpl copy = null;
            try {
              copy = new PropertyImpl(property);
            } catch (InvalidParametersException e) {
              // Iterated property cannot be null
              throw new RuntimeException("Cannot copy null property from parent collection");
            }
            copy.setDocumentation(COMPOSITE_SUPERKEY_COMMENT);
            copy.setParamTag(COMPOSITE_SUPERKEY_TAG);
            copy.setParentKey(true);
            copy.setSource(copy.getSource() + " + AddParentKeyAttributes");
            // Reset the table name to the new table.
            copy.setTableName(getTableName(metaObject));
            // Set the owner type to the current DTO type.
            copy.setOwnerType(metaObject);
            // Note: Leave the parent key property type the same.
            list.add(copy);
          }
        }
      }
    }
  }

  /**
   * Sort the input list of properties alphabetically for a composite key. This
   * strategy, used in a sequence of add-property strategies after adding an
   * initial set of properties, sorts those properties in alphabetical order.
   * You can thus use several distinct strategies to build a primary key set,
   * then sort the key properties into alphabetical order, which is the required
   * order for matching up to various lists of primary keys. Currently, this
   * sort is required only for composite keys.
   */
  class SortCompositeKeyProperties implements IAddPropertyStrategy {

    public void addProperties(List<Property> list) {
      // Proceed only for composite keys.
      if (metaObject.getAppliedStereotype(COMPOSITE) != null) {
        // Sort the list alphabetically.
        Collections.sort(list, new KeyPropertyComparator());
      }
    }
  }

  /**
   * Add the ordered sub-key properties to a property list. The ordered sub-key
   * property is the generated BigInteger attribute that serves as the second
   * component (the child) of the composite key for an ordered composite
   * aggregation.
   * 
   * @see PersistentClassFacadeLogicImpl.AddExplicitSubKeyProperties
   * @see PersistentClassFacadeLogicImpl.AddParentKeyAttributes
   * @see PersistentClassFacadeLogicImpl.AddUnorderedSubKeyProperties
   */
  class AddOrderedSubKeyProperties implements IAddPropertyStrategy {

    public void addProperties(List<Property> list) {
      // Proceed only for ordered, non-explicit, composite keys.
      if (metaObject.getAppliedStereotype(COMPOSITE) != null
          && isOrderedComposite()
          && !isExplicitKey()) {
        // If child is ordered composite and there is no explicit key, add the
        // sub-key property, an integer counter named <prop>No, where <prop>
        // is the property name.
        String name =
          StringUtilities.buildVariableName(getTableName(metaObject),
                                            null,
                                            "No");
        // readWrite set true because you need to be able to update to reorder
        // immutable set false ditto
        // TODO test this to work out issues with updating primary key
        PropertyImpl property =
          new PropertyImpl(name,
                           null,
                           null,
                           null,
                           null,
                           true,
                           true,
                           COMPOSITE_ORDERED_SUBKEY_TYPE,
                           COMPOSITE_SUBKEY_COMMENT,
                           "public",
                           false,
                           true,
                           "1",
                           COMPOSITE_SUBKEY_TAG,
                           getSqlMappings().getTo("datatype::Long"),
                           BIG_INT_COL_TYPE,
                           getSubsystem(findSubsystemPackage()).getName(),
                           false,
                           "AddOrderedSubKeyProperties",
                           getTableName(metaObject),
                           false,
                           true,
                           COMPOSITE_ORDERED_SUBKEY_TYPE,
                           false,
                           false,
                           null,
                           false,
                           name,
                           10,
                           100,
                           COMPOSITE_ORDERED_SUBKEY_TYPE,
                           DatabaseUtilities.getAnsiSqlType(BIG_INTEGER),
                           DatabaseUtilities.getMySql51SqlType(BIG_INTEGER),
                           DatabaseUtilities.getOracle11SqlType(BIG_INTEGER),
                           DatabaseUtilities.getSybaseAse125SqlType(BIG_INTEGER),
                           false,
                           null,
                           null,
                           false,
                           null,
                           null,
                           null);

        // Set the property type to null as this is a primitive, not an object.
        property.setPropertyType(null);
        // Set the owner type to be the current DTO type.
        property.setOwnerType(metaObject);

        list.add(property);
      }
    }
  }

  /**
   * Add the unordered sub-key properties to a property list. The unordered
   * sub-key property is the generated attribute that serves as the second
   * component (the child) of the composite key for an ordered composite
   * aggregation. The generated attribute is named <code>&lt;Class&gt;Id</code>,
   * where <code>&lt;Class&gt;</code> is the name of the DTO class.
   * 
   * @see PersistentClassFacadeLogicImpl.AddExplicitSubKeyProperties
   * @see PersistentClassFacadeLogicImpl.AddParentKeyAttributes
   * @see PersistentClassFacadeLogicImpl.AddOrderedSubKeyProperties
   */
  class AddUnorderedSubKeyProperties implements IAddPropertyStrategy {

    public void addProperties(List<Property> list) {
      // Proceed only for unordered, non-explicit, composite keys.
      if (metaObject.getAppliedStereotype(COMPOSITE) != null
          && isUnorderedComposite()
          && !isExplicitKey()) {
        String name =
          StringUtilities.buildVariableName(getTableName(metaObject),
                                            null,
                                            "Id");
        PropertyImpl property =
          new PropertyImpl(name,
                           null,
                           null,
                           null,
                           null,
                           false,
                           true,
                           COMPOSITE_UNORDERED_SUBKEY_TYPE,
                           COMPOSITE_SUBKEY_COMMENT,
                           "public",
                           false,
                           true,
                           "subkey",
                           COMPOSITE_SUBKEY_TAG,
                           getSqlMappings().getTo(UMLProfile.STRING_TYPE_NAME),
                           STRING_COL_TYPE,
                           getSubsystem(findSubsystemPackage()).getName(),
                           false,
                           "AddUnorderedSubKeyProperties",
                           getTableName(metaObject),
                           false,
                           true,
                           COMPOSITE_UNORDERED_SUBKEY_TYPE,
                           true,
                           false,
                           null,
                           false,
                           name,
                           10,
                           100,
                           COMPOSITE_UNORDERED_SUBKEY_TYPE,
                           DatabaseUtilities.getAnsiSqlType(STRING),
                           DatabaseUtilities.getMySql51SqlType(STRING),
                           DatabaseUtilities.getOracle11SqlType(STRING),
                           DatabaseUtilities.getSybaseAse125SqlType(STRING),
                           false,
                           null,
                           null,
                           false,
                           null,
                           null,
                           null);

        // Set the owner type to the current type of the DTO.
        property.setOwnerType(metaObject);
        // Set the property type to null as this is a primitive, not an object.
        property.setPropertyType(null);

        list.add(property);
      }
    }
  }

  /**
   * Add the explicit sub-key properties to a property list. The explicit
   * sub-key properties are the properties that constitute the second part of
   * the primary key of a composite child class and that are declared explicitly
   * rather than generated. They can be explicit primary key properties (PK)
   * defined on the class or the key attributes of an associated object marked
   * explicitly as a primary key, or both.
   */
  class AddExplicitSubKeyProperties implements IAddPropertyStrategy {

    public void addProperties(List<Property> list) {
      // Proceed only if this class has a composite key.
      if (hasKeyType(KeyType.COMPOSITE)) {
        // Add the explicit key properties, including associations.
        for (Object o : metaObject.getOwnedAttributes()) {
          org.eclipse.uml2.Property p = (org.eclipse.uml2.Property)o;
          boolean isPk = p.getAppliedStereotype(PK) != null;
          if (p.getAssociation() == null && isPk) {
            // Explicit natural key on class
            addNaturalSubkeyOnClass(list, p);
          } else if (p.getAssociation() != null && p.getUpper() == 1 && isPk) {
            // Explicit key on association to class, get the other class.
            addExplicitSubkeyOnAssociation(list, p);
          }
        }
      }
    }

    /**
     * Add a property to a list that represents an explicit primary sub-key that
     * derives from an association rather than being an attribute of the
     * composite DTO.
     * 
     * @param list the list of properties to which to add the property
     * @param p the property to add
     * @throws RuntimeException when there is a problem add the subkey
     */
    @SuppressWarnings("unchecked")
    private void addExplicitSubkeyOnAssociation(List<Property> list,
                                                org.eclipse.uml2.Property p) {
      Type t = p.getType();

      Dto dto = null;
      if (t instanceof AssociationClass) {
        PersistentAssociationClassFacadeLogicImpl f =
          new PersistentAssociationClassFacadeLogicImpl((AssociationClass)t,
                                                        CONTEXT);
        dto = f.transformToDto(null);
      } else if (t instanceof ClassImpl) {
        PersistentClassFacadeLogicImpl f =
          new PersistentClassFacadeLogicImpl((ClassImpl)t, CONTEXT);
        dto = f.transformToDto(null);
      }

      Collection<Property> propList = dto.getKeyProperties();
      // Extract the primary key properties of the other class.
      for (Property key : propList) {
        // Copy the property.
        Property toOne = null;
        try {
          toOne = new PropertyImpl(key);
        } catch (InvalidParametersException e) {
          throw new RuntimeException("Cannot copy null property", e);
        }

        // Set the documentation to reflect status as subkey.
        toOne.setDocumentation(FOREIGN_SUBKEY_COMMENT);
        // Since the property is part of the primary key, make it read only.
        toOne.setReadWrite(false);
        toOne.setSource(toOne.getSource() + " + addExplicitSubkeyOnAssociation");

        // Set the type to that of the current DTO class.
        ((PropertyImpl)toOne).setOwnerType(metaObject);

        list.add(toOne);
      }
    }

    /**
     * Add a natural sub-key attribute property defined on the composite DTO to
     * a list of the DTO properties.
     * 
     * @param list the list of properties to which to add the property
     * @param p the natural key property to add
     */
    private void addNaturalSubkeyOnClass(List<Property> list,
                                         org.eclipse.uml2.Property p) {
      Property property =
        new PersistentPropertyFacadeLogicImpl(p, CONTEXT).handleTransformToProperty();
      property.setSource("AddExplicitSubKeyProperties + addNaturalSubkeyOnClass");

      // Set the owner type to that of the current DTO class.
      ((PropertyImpl)property).setOwnerType(metaObject);

      list.add(property);
    }
  }

  /**
   * <p>
   * Add the to-one association optional object properties to a property list.
   * The to-one association optional object properties are the persistent
   * to-one, non-composite-aggregation association ends on the class that have a
   * lower bound of 0 (are optional). These objects get queried by setter
   * strategies based on the to-one association attributes queried from the
   * database for the DTO.
   * </p>
   * 
   * @see PersistentClassFacadeLogicImpl.AddToOneAssociationAttributeProperties
   * @see PersistentClassFacadeLogicImpl.AddToOneAssociationRequiredObjectProperties
   */
  class AddToOneAssociationOptionalObjectProperties implements
      IAddPropertyStrategy {

    public void addProperties(List<Property> list) {
      for (Object o : metaObject.getOwnedAttributes()) {
        org.eclipse.uml2.Property p = (org.eclipse.uml2.Property)o;

        // Only interested in persistent properties
        boolean persistent = p.getAppliedStereotype(PERSISTENT) != null;

        /*
         * A to-one association optional property must be: persistent, upper
         * bound 1 (to-one), lower bound 0 (optional), and the property is not a
         * composite
         */
        if (persistent
            && p.getAssociation() != null
            && p.upper() == 1
            && p.lower() == 0
            && !p.isComposite()) {
          // persistent, to-one, optional, non-composite association
          PersistentPropertyFacadeLogicImpl facade =
            new PersistentPropertyFacadeLogicImpl(p, CONTEXT);
          Property property = facade.handleTransformToProperty();
          property.setSource("AddToOneAssociationOptionalObjectProperties");

          // Remove any explicit name from the property, as these apply only
          // to attributes, not association objects.
          property.setExplicitName(null);

          // Set the owner to be the current DTO type.
          ((PropertyImpl)property).setOwnerType(metaObject);

          list.add(property);
        }
      }
    }
  }

  /**
   * <p>
   * Add the to-one association required object properties to a property list.
   * The to-one association required object properties are the persistent,
   * to-one, non-composite-aggregation association ends on the class that have a
   * lower bound of 1 (are required). These objects get queried by setter
   * strategies based on the to-one association attributes queried from the
   * database for the DTO.
   * </p>
   * 
   * @see PersistentClassFacadeLogicImpl.AddToOneAssociationAttributeProperties
   * @see PersistentClassFacadeLogicImpl.AddToOneAssociationOptionalObjectProperties
   */
  class AddToOneAssociationRequiredObjectProperties implements
      IAddPropertyStrategy {

    public void addProperties(List<Property> list) {

      for (Object o : metaObject.getOwnedAttributes()) {
        org.eclipse.uml2.Property p = (org.eclipse.uml2.Property)o;

        // Only interested in persistent properties
        boolean persistent = p.getAppliedStereotype(PERSISTENT) != null;

        if (persistent
            && p.getAssociation() != null
            && p.upper() == 1
            && p.lower() == 1
            && !p.isComposite()) {
          // to-one, required, non-composite association
          PersistentPropertyFacadeLogicImpl facade =
            new PersistentPropertyFacadeLogicImpl(p, CONTEXT);
          Property property = facade.handleTransformToProperty();
          property.setSource("AddToOneAssociationRequiredObjectProperties");

          // Remove any explicit name from the property, as these apply only
          // to attributes, not association objects.
          property.setExplicitName(null);

          // Set the owner type to be the current type.
          ((PropertyImpl)property).setOwnerType(metaObject);

          list.add(property);
        }
      }
    }
  }

  /**
   * <p>
   * Add the to-one association attribute properties to a property list. The
   * to-one association attribute properties are the non-composite-aggregation
   * foreign-key properties the system queries from the database in a SELECT
   * statement and therefore correspond to database columns that participate in
   * a foreign key constraint and that must be included in the SELECT list for
   * the class queries. In the DTO, these properties are data items that
   * represent objects associated to the current class through the to-one side
   * of an association. A setter strategy uses these key data fields to query
   * the object, either eagerly or lazily, into the to-one association object
   * property.
   * </p>
   * 
   * @see PersistentClassFacadeLogicImpl.AddToOneAssociationOptionalObjectProperties
   * @see PersistentClassFacadeLogicImpl.AddToOneAssociationRequiredObjectProperties
   */
  class AddToOneAssociationAttributeProperties implements IAddPropertyStrategy {

    public void addProperties(List<Property> list) {
      addAssociationAttributes(metaObject,
                               list,
                               "AddToOneAssociationAttributeProperties");
    }
  }

  /**
   * <p>
   * Add the explicit association key properties to a property list. The
   * explicit association key properties are the foreign-key properties the DAO
   * queries from the database in a SELECT statement that are also part of the
   * natural primary key, indicated by an explicit primary key stereotype on the
   * association end attached to a class with a NaturalKey stereotype or an
   * AssociationKey stereotype.
   * </p>
   * <p>
   * If the class is an association class with an AssociationKey stereotype, the
   * presence of an explicit &laquo;PK&raquo; on an association to the class
   * means that the association class really represents an n-ary association.
   * The present UML2 specification (and XMI) does not support this construct in
   * a meaningful way directly, so this method of adding to the normal,
   * generated key comprising the keys of the two associated classes is a way of
   * emulating the explicit notation for n-ary associations.
   * </p>
   * <p>
   * These properties are data items that represent objects associated to the
   * current class through the to-one side of an association. Primary key
   * generation uses these fields, and also a setter strategy uses these key
   * data fields to query the object, either eagerly or lazily.
   * </p>
   */
  class AddExplicitAssociationKeyProperties implements IAddPropertyStrategy {

    @SuppressWarnings("unchecked")
    public void addProperties(List<Property> list) {
      if (hasKeyType(KeyType.NATURAL) || hasKeyType(KeyType.ASSOCIATION)) {
        for (Object o : metaObject.getOwnedAttributes()) {
          org.eclipse.uml2.Property p = (org.eclipse.uml2.Property)o;
          String tableName = null;
          if (p.getAssociation() != null && p.upper() == 1) {
            // to-one association, get keys of associated class
            Type t = p.getType();
            // Proceed only for explicit primary keys.
            if (p.getAppliedStereotype(PK) != null) {
              // Property is explicit primary key, get key properties from class
              Collection<Property> keyProperties = null;
              if (t instanceof AssociationClass) {
                PersistentAssociationClassFacadeLogicImpl classFacade =
                  new PersistentAssociationClassFacadeLogicImpl((AssociationClass)t,
                                                                CONTEXT);
                keyProperties = classFacade.getPrimaryKeyProperties();
                tableName = classFacade.getTableName();
              } else if (t instanceof ClassImpl) {
                PersistentClassFacadeLogicImpl classFacade =
                  new PersistentClassFacadeLogicImpl((ClassImpl)t, CONTEXT);
                Dto dto = classFacade.transformToDto(null);
                keyProperties = dto.getKeyProperties();
                tableName = dto.getSqlTableName();
              }
              for (Property key : keyProperties) {
                // Copy the property and change the name and comment.
                Property toOne = null;
                try {
                  toOne = new PropertyImpl(key);
                } catch (InvalidParametersException e) {
                  throw new RuntimeException("Cannot copy null property", e);
                }
                // Update the documentation.
                updateProperty(toOne,
                               FOREIGN_PRIMARY_KEY_COMMENT,
                               FOREIGN_PRIMARY_KEY_TAG,
                               tableName);
                toOne.setSource(toOne.getSource()
                                + " + AddExplicitAssociationKeyProperties");

                // Set the type to the type of the associated class.
                ((PropertyImpl)key).setPropertyType(t);
                // Set the owner type to this class.
                ((PropertyImpl)key).setOwnerType(metaObject);

                list.add(toOne);
              }
            }
          }
        }
      }
    }
  }

  /**
   * <p>
   * Add the association class properties to a property list. The association
   * class properties are the properties that represent the collection of
   * association objects, as opposed to the properties for the associated
   * objects themselves. For example, if there is an association between A and B
   * called AB, A has the association collection of B objects and the
   * association class collection of AB objects. This set of properties enables
   * the class to iterate over the associations as objects rather than the
   * associated objects. If the diagram has a name tag on the AssociationKey
   * stereotype for the class, that's the name of the collection in the
   * associated classes rather than the class name.
   * </p>
   * <p>
   * <strong>Note:</strong> this strategy handles recursive association classes,
   * which require creating two properties with the same name, through having
   * different prefixes. Duplicate elimination will not eliminate these separate
   * properties because they have different prefixes, meaning they are actually
   * distinct, uniquely named properties.
   * </p>
   * 
   * @see PersistentClassFacadeLogicImpl.AddToManyAssociationCollectionProperties
   * @see PersistentClassFacadeLogicImpl.AddToManyAssociatedKeyCollectionProperties
   */
  class AddAssociationClassCollectionProperties implements IAddPropertyStrategy {

    @Override
    public void addProperties(List<Property> list) {
      for (Object o : metaObject.getOwnedAttributes()) {
        org.eclipse.uml2.Property p = (org.eclipse.uml2.Property)o;
        Association a = p.getAssociation();
        if (a != null && a instanceof AssociationClass) {
          createClassCollectionProperty(list,
                                        p,
                                        "AddAssociationClassCollectionProperties");
        }
      }
    }
  }

  /**
   * Add the inherited association class properties to an property list. The
   * inherited association class properties are the properties that represent
   * the collection of association objects, as opposed to the properties for the
   * associated objects themselves, that the current class inherits from all its
   * superclasses. If the association links directly to the current class, it
   * means that it is a link from a superclass to this class; in that case, the
   * association class gets created as a direct link that overrides the
   * inherited link, so this method excludes it from the set of inherited
   * properties.
   * 
   * @see PersistentClassFacadeLogicImpl.AddAssociationClassCollectionProperties
   */
  class AddInheritedAssociationClassCollectionProperties extends
      AddInheritedPropertiesStrategy {
    @Override
    protected void addCurrentProperties(PersistentClassFacade superFacade,
                                        List<Property> list, String source) {
      // Extract the superclass.
      org.eclipse.uml2.Class superclass =
        ((PersistentClassFacadeLogic)superFacade).metaObject;

      EList attrs = superclass.getOwnedAttributes();

      for (Object o : attrs) {
        org.eclipse.uml2.Property p = (org.eclipse.uml2.Property)o;
        // Check for association class and not associated directly to this class
        // and not recursive in superclass
        if (p.getAssociation() != null
            && p.getAssociation() instanceof AssociationClass
            && !toThisClass(p.getAssociation())
            && !isRecursiveAssociation(p.getAssociation())) {
          Property property = createClassCollectionProperty(list, p, source);
          // Set the inherited type to be the original superclass.
          ((PropertyImpl)property).setInheritedFromType(superclass);
        }
      }
    }

    @Override
    protected PersistentClassFacade getCurrentFacade() {
      return PersistentClassFacadeLogicImpl.this;
    }

    @Override
    protected String getSource() {
      return "AddInheritedAssociationClassCollectionProperties";
    }

    @Override
    protected void handleDuplicateProperties(List<Property> list) {
      overrideDuplicateInheritedProperties(list);
    }
  }

  /**
   * Add the inherited recursive association class properties to an property
   * list. The inherited recursive association class properties are the
   * properties that represent the collection of association objects, as opposed
   * to the properties for the associated objects themselves, that the current
   * class inherits from all its superclasses that are recursive (that is, that
   * have two association ends on the same class). These properties must include
   * a prefix that gets added to distinguish association class objects from one
   * side or the other of the recursive link. Unlike the non-recursive case,
   * there are two separate properties, one for each side of the recursive link.
   * 
   * @see AddInheritedAssociationClassCollectionProperties
   * @see PersistentClassFacadeLogicImpl.AddAssociationClassCollectionProperties
   */
  class AddInheritedRecursiveAssociationClassCollectionProperties extends
      AddInheritedPropertiesStrategy {
    @Override
    protected void addCurrentProperties(PersistentClassFacade superFacade,
                                        List<Property> list, String source) {
      // Extract the superclass.
      org.eclipse.uml2.Class superclass =
        ((PersistentClassFacadeLogic)superFacade).metaObject;

      EList attrs = superclass.getOwnedAttributes();

      for (Object o : attrs) {
        org.eclipse.uml2.Property p = (org.eclipse.uml2.Property)o;
        Association association = p.getAssociation();

        // Check for association class and recursive
        if (association != null
            && association instanceof AssociationClass
            && !toThisClass(association)
            && isRecursiveAssociation(association)) {
          Property property = createClassCollectionProperty(list, p, source);
          // Set the inherited type to be the original superclass.
          ((PropertyImpl)property).setInheritedFromType(superclass);
        }
      }
    }

    @Override
    protected PersistentClassFacade getCurrentFacade() {
      return PersistentClassFacadeLogicImpl.this;
    }

    @Override
    protected String getSource() {
      return "AddInheritedRecursiveAssociationClassCollectionProperties";
    }

    @Override
    protected void handleDuplicateProperties(List<Property> list) {
      overrideDuplicateInheritedProperties(list);
    }
  }

  /**
   * Add the local attribute properties to a property list. The local attribute
   * properties are the non-key, explicit properties queried from the database.
   * These attributes do not include either primary or foreign key (association)
   * attributes or inherited attributes.
   */
  class AddLocalAttributeProperties implements IAddPropertyStrategy {

    public void addProperties(List<Property> list) {
      for (Object o : metaObject.getOwnedAttributes()) {
        org.eclipse.uml2.Property p = (org.eclipse.uml2.Property)o;
        // No primary keys or association ends
        if (p.getAppliedStereotype(PK) == null && p.getAssociation() == null) {
          PersistentPropertyFacade propertyFacade =
            new PersistentPropertyFacadeLogicImpl(p, CONTEXT);
          Property property = propertyFacade.transformToProperty();
          property.setSource("AddLocalAttributeProperties");
          list.add(property);
          // Set the owner type to be the current DTO type.
          ((PropertyImpl)property).setOwnerType(metaObject);
          // Set property type to null as this is a primitive, not an object.
          ((PropertyImpl)property).setPropertyType(null);
        }
      }
    }
  }

  /**
   * Add the inherited attribute properties to a property list. The inherited
   * attribute properties are the non-key, non-association properties the
   * superclass and its own superclasses query from the database in a SELECT
   * statement. The method adds the properties in order from the root superclass
   * down to the immediate superclass of the current class.
   */
  class AddInheritedAttributeProperties implements IAddPropertyStrategy {

    public void addProperties(List<Property> list) {
      // Get the superclass facade.
      PersistentClassFacadeLogicImpl facade = getSuperclassFacade();
      if (facade != null) {
        // There is an immediate superclass, so start the tree climb.
        addSuperclassProperties(facade, list);
      }
    }

    /**
     * Recursively walk up the tree starting at the superclass, then fill the
     * list with non-key, non-association properties as you come back down.
     * 
     * @param superFacade the facade of the superclass at which to start
     *          climbing
     * @param list the list of properties to fill
     */
    private void addSuperclassProperties(PersistentClassFacadeLogicImpl superFacade,
                                         List<Property> list) {
      PersistentClassFacadeLogicImpl superSuperFacade =
        superFacade.getSuperclassFacade();
      if (superSuperFacade != null) {
        // There is a superclass, so recurse up the tree.
        addSuperclassProperties(superSuperFacade, list);
      }
      // Now add the non-key properties for the current superclass.
      for (Object o : superFacade.metaObject.getOwnedAttributes()) {
        org.eclipse.uml2.Property p = (org.eclipse.uml2.Property)o;
        PersistentPropertyFacade propertyFacade =
          new PersistentPropertyFacadeLogicImpl(p, CONTEXT);
        Property property = propertyFacade.transformToProperty();

        if (!property.isKey() && !property.isAssociationEnd()) {
          property.setSource(property.getSource()
                             + " + AddInheritedAttributeProperties");
          // Set the owning type to be the current DTO type.
          ((PropertyImpl)property).setOwnerType(metaObject);
          // Note: leave property type as is, no change needed.
          list.add(property);
        }
      }
    }
  }

  /**
   * Add the inherited to-many association properties to a property list. The
   * inherited to-many association properties are the non-key, to-many
   * association properties inherited from a superclass. The properties are used
   * in creating proxy and business DTO wrappers for the inherited associations
   * in the DTOs. The method adds the properties in order from the root
   * superclass down to the immediate superclass of the current class.
   */
  class AddInheritedToManyAssociationProperties extends
      AddInheritedPropertiesStrategy {
    @Override
    protected void addCurrentProperties(PersistentClassFacade superFacade,
                                        List<Property> list, String source) {
      // Extract the superclass.
      org.eclipse.uml2.Class superclass =
        ((PersistentClassFacadeLogic)superFacade).metaObject;
      // Now add the non-key, to-many associations for the current superclass.
      for (Object o : superclass.getOwnedAttributes()) {
        org.eclipse.uml2.Property p = (org.eclipse.uml2.Property)o;
        PersistentPropertyFacade propertyFacade =
          new PersistentPropertyFacadeLogicImpl(p, CONTEXT);
        Property property = propertyFacade.transformToProperty();

        if (!property.isKey()
            && property.isAssociationEnd()
            && property.isToMany()) {
          property.setSource(source);
          list.add(property);
          // Set the owner type to be the current DTO type.
          ((PropertyImpl)property).setOwnerType(metaObject);
          // Set the inherited type to be the original superclass.
          ((PropertyImpl)property).setInheritedFromType(superclass);
          // Leave the property type the same, no change needed.
        }
      }
    }

    @Override
    protected PersistentClassFacade getCurrentFacade() {
      return PersistentClassFacadeLogicImpl.this;
    }

    @Override
    protected String getSource() {
      return "AddInheritedToManyAssociationProperties";
    }

    @Override
    protected void handleDuplicateProperties(List<Property> list) {
      Map<String, Property> names = new HashMap<String, Property>(list.size());
      for (Property p : list) {
        if (names.get(p.getName()) == null) {
          // Not duplicate, add the name to the map.
          names.put(p.getName(), p);
        } else {
          PersistentClassFacadeLogicImpl facade =
            getSuperclassFacade(getCurrentFacade());
          // Inherited to-many association property has the same name as a
          // to-man association property in this class, log for debugging.
          // The inherited property is overridden.
          logger.debug("Hidden inherited to-many association name in class "
                       + metaObject.getQualifiedName()
                       + ": "
                       + p.getName()
                       + " (inherited from "
                       + facade.getFullyQualifiedName()
                       + ")");
        }
      }
    }
  }

  /**
   * Add the inherited association attribute properties to a property list. The
   * inherited association attribute properties are the non-key,
   * association-attribute properties the superclass and its own superclasses
   * query from the database in a SELECT statement (foreign key attributes). The
   * method adds the properties in order from the root superclass down to the
   * immediate superclass of the current class.
   */
  class AddInheritedAssociationAttributeProperties extends
      AddInheritedPropertiesStrategy {

    @Override
    protected void addCurrentProperties(PersistentClassFacade superFacade,
                                        List<Property> list, String source) {
      addAssociationAttributes(((PersistentClassFacadeLogic)superFacade).metaObject,
                               list,
                               source);
    }

    @Override
    protected PersistentClassFacade getCurrentFacade() {
      return PersistentClassFacadeLogicImpl.this;
    }

    @Override
    protected String getSource() {
      return "AddInheritedAssociationAttributeProperties";
    }

    @Override
    protected void handleDuplicateProperties(List<Property> list) {
      Map<String, Property> names = new HashMap<String, Property>(list.size());
      for (Property p : list) {
        if (names.get(p.getName()) == null) {
          // Not duplicate, add the name to the map.
          names.put(p.getName(), p);
        } else {
          PersistentClassFacadeLogicImpl facade =
            getSuperclassFacade(getCurrentFacade());
          // Inherited to-one association property has the same name as a
          // to-one association property in this class, log for debugging.
          // The inherited property is overridden or it may possibly be
          // a recursive link to the same superclass, thus the inherited
          // name is the same as the primary key of the current class.
          logger.warn("Hidden inherited to-one association name or inherited recursive assocation attribute in class "
                      + metaObject.getQualifiedName()
                      + ": "
                      + p.getName()
                      + " (inherited from "
                      + facade.getFullyQualifiedName()
                      + ")");
        }
      }
    }
  }

  /**
   * Add the inherited association object properties to a property list. The
   * inherited association object properties are the association-object
   * properties representing a to-one association. The method adds the
   * properties in order from the root superclass down to the immediate
   * superclass of the current class.
   */
  class AddInheritedAssociationObjectProperties extends
      AddInheritedPropertiesStrategy {

    @Override
    protected void addCurrentProperties(PersistentClassFacade superFacade,
                                        List<Property> list, String source) {
      addAssociationObjects(((PersistentClassFacadeLogic)superFacade).metaObject,
                            list,
                            source);
    }

    @Override
    protected PersistentClassFacade getCurrentFacade() {
      return PersistentClassFacadeLogicImpl.this;
    }

    @Override
    protected String getSource() {
      return "AddInheritedAssociationObjectProperties";
    }

    @Override
    protected void handleDuplicateProperties(List<Property> list) {
      Map<String, Property> names = new HashMap<String, Property>(list.size());
      for (Property p : list) {
        if (names.get(p.getName()) == null) {
          // Not duplicate, add the name to the map.
          names.put(p.getName(), p);
        } else {
          PersistentClassFacadeLogicImpl facade =
            getSuperclassFacade(getCurrentFacade());
          // Inherited to-one association object has the same name as a
          // to-one association property in this class, log for debugging.
          // The inherited property is overridden.
          logger.debug("Hidden inherited to-one association name in class "
                       + metaObject.getQualifiedName()
                       + ": "
                       + p.getName()
                       + " (inherited from "
                       + facade.getFullyQualifiedName()
                       + ")");
        }
      }
    }
  }

  /**
   * <p>
   * Add the to-many association collection properties to a list.
   * </p>
   * <p>
   * The DTO setters use their own key to query these collections either eagerly
   * or lazily. Note that many-to-many associations are composites with respect
   * to the association class but not with respect to the associated objects
   * (association ends).
   * </p>
   * 
   * @see PersistentClassFacadeLogicImpl.AddAssociationClassCollectionProperties
   * @see PersistentClassFacadeLogicImpl.AddToManyAssociatedKeyCollectionProperties
   */
  class AddToManyAssociationCollectionProperties implements
      IAddPropertyStrategy {

    public void addProperties(List<Property> list) {
      for (Object o : metaObject.getOwnedAttributes()) {
        org.eclipse.uml2.Property p = (org.eclipse.uml2.Property)o;
        addToManyAssociationCollectionProperty(list,
                                               p,
                                               "AddToManyAssociationCollectionProperties");
      }
    }
  }

  /**
   * <p>
   * Add the to-many association collection properties inherited from any
   * superclasses to a list.
   * </p>
   * <p>
   * The inherited properties appear only in proxy and business-DTO wrappers.
   * </p>
   * 
   * @see PersistentClassFacadeLogicImpl.AddInheritedAssociationClassCollectionProperties
   */
  class AddInheritedToManyAssociationCollectionProperties extends
      AddInheritedPropertiesStrategy {

    @Override
    protected void addCurrentProperties(PersistentClassFacade superFacade,
                                        List<Property> list, String source) {
      PersistentClassFacadeLogic l = (PersistentClassFacadeLogic)superFacade;
      for (Object o : l.metaObject.getOwnedAttributes()) {
        org.eclipse.uml2.Property p = (org.eclipse.uml2.Property)o;
        addToManyAssociationCollectionProperty(list, p, getSource());
      }
    }

    @Override
    protected PersistentClassFacade getCurrentFacade() {
      return PersistentClassFacadeLogicImpl.this;
    }

    @Override
    protected String getSource() {
      return "AddInheritedToManyAssociationCollectionProperties";
    }

    @Override
    protected void handleDuplicateProperties(List<Property> list) {
      overrideDuplicateInheritedProperties(list);
    }
  }

  /**
   * <p>
   * Add the associated-key collection properties to a list. The associated-key
   * collection properties are the non-composite, to-many association-end
   * collection or list objects that have a PK stereotype on the opposite
   * association end. This is the special case where the object serves as part
   * of the primary key in the other object but is not a composite aggregation
   * (parent) object. This needs to have deletes cascaded to the associated
   * objects. Association collections of objects with no PK gets added
   * separately by AddToManyAssociationCollectionProperties.
   * </p>
   * <p>
   * The DTO setters use their own key to query these collections either eagerly
   * or lazily. Note that many-to-many associations are composites with respect
   * to the association class but not with respect to the associated objects
   * (association ends).
   * </p>
   * 
   * @see PersistentClassFacadeLogicImpl.AddAssociationClassCollectionProperties
   * @see PersistentClassFacadeLogicImpl.AddToManyAssociationCollectionProperties
   */
  class AddToManyAssociatedKeyCollectionProperties implements
      IAddPropertyStrategy {

    public void addProperties(List<Property> list) {
      for (Object o : metaObject.getOwnedAttributes()) {
        org.eclipse.uml2.Property p = (org.eclipse.uml2.Property)o;
        // We want non-composite, to-many association properties. Upper bound -1
        // is *, unlimited. No PK on opposite side is excluded.
        boolean isKey =
          p.getAssociation() != null
              && p.getOpposite() != null
              && p.getOpposite().getAppliedStereotype(PK) != null;
        if (p.getAssociation() != null
            && p.getUpper() != 1
            && !p.isComposite()
            && isKey) {
          PersistentPropertyFacadeLogicImpl facade =
            new PersistentPropertyFacadeLogicImpl(p, CONTEXT);
          Property property = facade.handleTransformToProperty();
          property.setSource("AddToManyAssociatedKeyCollectionProperties");
          list.add(property);
          // Set the owner type to be the current type.
          ((PropertyImpl)property).setPropertyType(metaObject);
          // Set the type to be the associated DTO type (not the collection
          // type but the collected object type).
          ((PropertyImpl)property).setPropertyType(p.getType());
        }
      }
    }
  }

  /**
   * Add the child collection properties to a list. The child collection
   * properties are the to-many composite aggregation association-end collection
   * or list objects. The DTO setters use their own key to query these
   * collections either eagerly or lazily. The DTO also uses these collections
   * to mark cascaded deletes of the child objects.
   * 
   * @see PersistentClassFacadeLogicImpl.AddAssociationClassCollectionProperties
   */
  class AddToManyChildCollectionProperties implements IAddPropertyStrategy {

    public void addProperties(List<Property> list) {
      for (Object o : metaObject.getOwnedAttributes()) {
        org.eclipse.uml2.Property p = (org.eclipse.uml2.Property)o;
        // We want to-many child properties. Upper bound -1 is *, unlimited.
        if (p.getAssociation() != null && p.isComposite() && p.getUpper() != 1) {
          PersistentPropertyFacadeLogicImpl facade =
            new PersistentPropertyFacadeLogicImpl(p, CONTEXT);
          Property property = facade.handleTransformToProperty();
          property.setSource("AddToManyChildCollectionProperties");
          list.add(property);
          // Set the type to be the current child DTO type.
          ((PropertyImpl)property).setPropertyType(p.getType());
          // Set the owner type to be the curren type.
          ((PropertyImpl)property).setOwnerType(metaObject);
        }
      }
    }
  }

  /**
   * Add the to-one child properties to a property list. The to-one child
   * properties are the persistent properties that represent composite
   * aggregations or parent-child relationships where the current class is the
   * parent and the object is a single object (a one-to-one composite
   * aggregation). The DTO setters use their own key to query these objects
   * either eagerly or lazily, so there is no corresponding "attribute" in the
   * DTO for the foreign key. The DTO also uses these objects to mark cascaded
   * deletes of the child object.
   */
  class AddToOneChildProperties implements IAddPropertyStrategy {

    public void addProperties(List<Property> list) {
      for (Object o : metaObject.getOwnedAttributes()) {
        org.eclipse.uml2.Property p = (org.eclipse.uml2.Property)o;
        // Only interested in persistent properties
        boolean persistent = p.getAppliedStereotype(PERSISTENT) != null;
        // We want to-one composite aggregation properties: single-object
        // children.
        if (persistent
            && p.getAssociation() != null
            && p.isComposite()
            && p.upper() == 1) {
          PersistentPropertyFacadeLogicImpl facade =
            new PersistentPropertyFacadeLogicImpl(p, CONTEXT);
          Property property = facade.handleTransformToProperty();
          property.setSource("AddToOneChildProperties");
          // Set the type to be the child property type.
          ((PropertyImpl)property).setPropertyType(p.getType());
          // Set the type to be the current DTO type.
          ((PropertyImpl)property).setOwnerType(metaObject);
          list.add(property);
        }
      }
    }
  }

  /*
   * The next several methods initalize the strategy arrays for the class with
   * instances of the nested IAddPropertyStrategy classes.
   */

  /**
   * <p>
   * Initialize the strategy list for all DTO properties. This list includes all
   * the the strategy classes. When you create a new strategy class, you must
   * add the class instantiation here. The ordering here is important.
   * </p>
   */
  private void initAllDtoProperties() {
    allDtoPropertyStrategies.add(new AddNaturalKeyProperties());
    allDtoPropertyStrategies.add(new AddExplicitAssociationKeyProperties());
    allDtoPropertyStrategies.add(new AddGeneratedGuidKeyProperties());
    allDtoPropertyStrategies.add(new AddGeneratedKeyProperties());
    allDtoPropertyStrategies.add(new AddParentKeyAttributes());
    allDtoPropertyStrategies.add(new AddOrderedSubKeyProperties());
    allDtoPropertyStrategies.add(new AddUnorderedSubKeyProperties());
    allDtoPropertyStrategies.add(new AddExplicitSubKeyProperties());
    // Inherited go here in this order to avoid removing duplicates.
    allDtoPropertyStrategies.add(new AddInheritedToManyAssociationCollectionProperties());
    allDtoPropertyStrategies.add(new AddInheritedKeyProperties());
    allDtoPropertyStrategies.add(new AddInheritedToManyAssociationProperties());
    allDtoPropertyStrategies.add(new AddInheritedAssociationClassCollectionProperties());
    allDtoPropertyStrategies.add(new AddInheritedAttributeProperties());
    allDtoPropertyStrategies.add(new AddInheritedAssociationAttributeProperties());
    allDtoPropertyStrategies.add(new AddLocalAttributeProperties());
    allDtoPropertyStrategies.add(new AddToOneAssociationOptionalObjectProperties());
    allDtoPropertyStrategies.add(new AddToOneAssociationRequiredObjectProperties());
    allDtoPropertyStrategies.add(new AddToOneAssociationAttributeProperties());
    allDtoPropertyStrategies.add(new AddToManyAssociationCollectionProperties());
    allDtoPropertyStrategies.add(new AddToManyAssociatedKeyCollectionProperties());
    allDtoPropertyStrategies.add(new AddToOneChildProperties());
    allDtoPropertyStrategies.add(new AddToManyChildCollectionProperties());
    allDtoPropertyStrategies.add(new AddAssociationClassCollectionProperties());
    allDtoPropertyStrategies.add(new AddInheritedRecursiveAssociationClassCollectionProperties());
  }

  /**
   * <p>
   * Initialize the strategy list for constructor args. The DTO constructor
   * argument list contains "key" plus these properties, and various other
   * templates build constructor calls from this list as well. This list
   * includes the following property-addition strategies:
   * </p>
   * <ol>
   * <li>key attributes of the appropriate type</li>
   * <li>inherited attributes</li>
   * <li>inherited association attributes</li>
   * <li>local attributes</li>
   * <li>to-one association attributes</li>
   * </ol>
   */
  private void initConstructorArgs() {
    constructorArgStrategies.add(new AddNaturalKeyProperties());
    constructorArgStrategies.add(new AddExplicitAssociationKeyProperties());
    constructorArgStrategies.add(new AddInheritedKeyProperties());
    constructorArgStrategies.add(new AddGeneratedGuidKeyProperties());
    constructorArgStrategies.add(new AddGeneratedKeyProperties());
    constructorArgStrategies.add(new AddParentKeyAttributes());
    constructorArgStrategies.add(new AddOrderedSubKeyProperties());
    constructorArgStrategies.add(new AddUnorderedSubKeyProperties());
    constructorArgStrategies.add(new AddExplicitSubKeyProperties());
    constructorArgStrategies.add(new AddInheritedAttributeProperties());
    constructorArgStrategies.add(new AddInheritedAssociationAttributeProperties());
    constructorArgStrategies.add(new AddLocalAttributeProperties());
    constructorArgStrategies.add(new AddToOneAssociationAttributeProperties());
  }

  /**
   * <p>
   * Initialize the strategy list for test variables. The test variables are the
   * local variables in the create() method of the JUnit test for a business
   * delegate. They include all the constructor arguments for the object minus
   * the to-one, required key attributes and the explicit association key
   * attributes, which the test case supplies by creating the parent test object
   * and supplying its key. This list includes the following property-addition
   * strategies:
   * </p>
   * <ol>
   * <li>key attributes of the appropriate type (excluding explicit association
   * keys, which get created separately)</li>
   * <li>inherited attributes</li>
   * <li>inherited association attributes</li>
   * <li>local attributes</li>
   * </ol>
   */
  private void initTestVars() {
    testVarStrategies.add(new AddNaturalKeyProperties());
    testVarStrategies.add(new AddInheritedKeyProperties());
    testVarStrategies.add(new AddGeneratedGuidKeyProperties());
    testVarStrategies.add(new AddGeneratedKeyProperties());
    testVarStrategies.add(new AddParentKeyAttributes());
    testVarStrategies.add(new AddOrderedSubKeyProperties());
    testVarStrategies.add(new AddUnorderedSubKeyProperties());
    testVarStrategies.add(new AddExplicitSubKeyProperties());
    testVarStrategies.add(new AddInheritedAttributeProperties());
    testVarStrategies.add(new AddInheritedAssociationAttributeProperties());
    testVarStrategies.add(new AddLocalAttributeProperties());
  }

  /**
   * <p>
   * Initialize the strategy list for table columns. This list includes the
   * following property-addition strategies:
   * </p>
   * <ol>
   * <li>key attributes of the appropriate type</li>
   * <li>local attributes</li>
   * <li>to-one association attributes</li>
   * </ol>
   */
  private void initColumns() {
    columnStrategies.add(new AddNaturalKeyProperties());
    columnStrategies.add(new AddExplicitAssociationKeyProperties());
    columnStrategies.add(new AddInheritedKeyProperties());
    columnStrategies.add(new AddGeneratedGuidKeyProperties());
    columnStrategies.add(new AddGeneratedKeyProperties());
    columnStrategies.add(new AddParentKeyAttributes());
    columnStrategies.add(new AddOrderedSubKeyProperties());
    columnStrategies.add(new AddUnorderedSubKeyProperties());
    columnStrategies.add(new AddExplicitSubKeyProperties());
    columnStrategies.add(new AddLocalAttributeProperties());
    columnStrategies.add(new AddToOneAssociationAttributeProperties());
  }

  /**
   * <p>
   * Initialize the strategy list for local data members. A local data member is
   * a key attribute for any type of key (excluding inherited keys) or a local
   * non-key attribute or a to-one association member but no inherited
   * attributes or to-many association members. This list is a subset of the
   * constructor args that consists of the arguments that get assigned to local
   * data members in the constructor. This list includes the following
   * property-addition strategies:
   * </p>
   * <ol>
   * <li>key attributes of the appropriate type (no inherited)</li>
   * <li>local attributes</li>
   * <li>to-one association attributes</li>
   * </ol>
   */
  private void initLocalMembers() {
    localMemberStrategies.add(new AddNaturalKeyProperties());
    localMemberStrategies.add(new AddExplicitAssociationKeyProperties());
    localMemberStrategies.add(new AddGeneratedGuidKeyProperties());
    localMemberStrategies.add(new AddGeneratedKeyProperties());
    localMemberStrategies.add(new AddParentKeyAttributes());
    localMemberStrategies.add(new AddOrderedSubKeyProperties());
    localMemberStrategies.add(new AddUnorderedSubKeyProperties());
    localMemberStrategies.add(new AddExplicitSubKeyProperties());
    localMemberStrategies.add(new AddLocalAttributeProperties());
    localMemberStrategies.add(new AddToOneAssociationAttributeProperties());
  }

  /**
   * <p>
   * Initialize the strategy list for data members. The data members are the
   * actual data members of the AbstractDbDto that hold the data queried from
   * the database either in primitive form or in object form. This list includes
   * the following property-addition strategies:
   * </p>
   * <ol>
   * <li>key attributes of the appropriate type (no inherited)</li>
   * <li>local attributes</li>
   * <li>to-one association attributes and objects</li>
   * <li>to-many association collections and association class collections</li>
   * <li>to-one and to-many child composite aggregations (objects or
   * collections)</li>
   * </ol>
   */
  private void initDataMembers() {
    dataMemberStrategies.add(new AddNaturalKeyProperties());
    dataMemberStrategies.add(new AddExplicitAssociationKeyProperties());
    dataMemberStrategies.add(new AddGeneratedGuidKeyProperties());
    dataMemberStrategies.add(new AddGeneratedKeyProperties());
    dataMemberStrategies.add(new AddParentKeyAttributes());
    dataMemberStrategies.add(new AddOrderedSubKeyProperties());
    dataMemberStrategies.add(new AddUnorderedSubKeyProperties());
    dataMemberStrategies.add(new AddExplicitSubKeyProperties());
    dataMemberStrategies.add(new AddLocalAttributeProperties());
    dataMemberStrategies.add(new AddToOneAssociationOptionalObjectProperties());
    dataMemberStrategies.add(new AddToOneAssociationRequiredObjectProperties());
    dataMemberStrategies.add(new AddToOneAssociationAttributeProperties());
    dataMemberStrategies.add(new AddToManyAssociationCollectionProperties());
    dataMemberStrategies.add(new AddToManyAssociatedKeyCollectionProperties());
    dataMemberStrategies.add(new AddToOneChildProperties());
    dataMemberStrategies.add(new AddToManyChildCollectionProperties());
    dataMemberStrategies.add(new AddAssociationClassCollectionProperties());
  }

  /**
   * <p>
   * Initialize the strategy list for object properties. The object properties
   * are the actual data members of the AbstractDbDto that hold associated DTOs
   * in object form. Each object property has a corresponding group of setters
   * in the DbDto class that use the corresponding key attributes to query the
   * object or collection using a SQL specification. The strategies are a subset
   * of the data member strategies. This list includes the following
   * property-addition strategies:
   * </p>
   * <ol>
   * <li>to-one association objects</li>
   * <li>to-one child composite aggregations (objects or collections)</li>
   * </ol>
   */
  private void initObjectProperties() {
    objectStrategies.add(new AddToOneAssociationOptionalObjectProperties());
    objectStrategies.add(new AddToOneAssociationRequiredObjectProperties());
    objectStrategies.add(new AddToOneChildProperties());
  }

  /**
   * <p>
   * Initialize the strategy list for collection properties. The collection
   * properties are the actual data members of the AbstractDbDto that hold
   * associated DTOs in object form in a collection or list. Each collection
   * property has a corresponding group of setters in the DbDto class that use
   * the corresponding key attributes to query the object or collection using a
   * SQL specification. The strategies are a subset of the data member
   * strategies. This list includes the following property-addition strategies:
   * </p>
   * <ol>
   * <li>to-many association collections and association class collections</li>
   * <li>to-many child composite aggregations (objects or collections)</li>
   * </ol>
   */
  private void initCollectionProperties() {
    collectionStrategies.add(new AddToManyAssociationCollectionProperties());
    collectionStrategies.add(new AddToManyAssociatedKeyCollectionProperties());
    collectionStrategies.add(new AddToManyChildCollectionProperties());
    collectionStrategies.add(new AddAssociationClassCollectionProperties());
  }

  /**
   * <p>
   * Initialize the strategy list for inherited attributes. The inherited
   * attributes are the attributes supplied to a DTO constructor that get passed
   * to the constructor of the superclass. This list includes the following
   * property-addition strategies:
   * </p>
   * <ol>
   * <li>inherited key attributes</li>
   * <li>inherited attributes</li>
   * <li>inherited association attributes</li>
   * <li>inherited to-many associations</li>
   * <li>inherited association-class collection properties</li>
   * </ol>
   * <p>
   * This list is a subset of the constructor arg list.
   * </p>
   * 
   * @see #initConstructorArgs()
   */
  private void initInheritedProperties() {
    // ordering here is important to avoid eliminating duplicates that aren't
    // really duplicates.
    inheritedPropertyStrategies.add(new AddInheritedToManyAssociationCollectionProperties());
    inheritedPropertyStrategies.add(new AddInheritedKeyProperties());
    inheritedPropertyStrategies.add(new AddInheritedAssociationObjectProperties());
    inheritedPropertyStrategies.add(new AddInheritedToManyAssociationProperties());
    inheritedPropertyStrategies.add(new AddInheritedAssociationClassCollectionProperties());
    inheritedPropertyStrategies.add(new AddInheritedAttributeProperties());
    inheritedPropertyStrategies.add(new AddInheritedAssociationAttributeProperties());
    inheritedPropertyStrategies.add(new AddInheritedRecursiveAssociationClassCollectionProperties());
  }

  /**
   * <p>
   * Initialize the strategy list for inherited constructor arg attributes. The
   * inherited constructor arg attributes are the queried attributes supplied to
   * a DTO constructor that get passed to the constructor of the superclass.
   * This list includes the following property-addition strategies:
   * </p>
   * <ol>
   * <li>inherited key attributes</li>
   * <li>inherited attributes</li>
   * <li>inherited association attributes</li>
   * </ol>
   * <p>
   * This list is a subset of the constructor arg list.
   * </p>
   * 
   * @see #initConstructorArgs()
   */
  private void initInheritedConstructorArgs() {
    inheritedConstructorArgStrategies.add(new AddInheritedKeyProperties());
    inheritedConstructorArgStrategies.add(new AddInheritedAttributeProperties());
    inheritedConstructorArgStrategies.add(new AddInheritedAssociationAttributeProperties());
  }

  /**
   * <p>
   * Initialize the strategy list for child properties. The child properties are
   * the objects and collections for composite aggregations in which the current
   * class is the parent aggregator as well as the collections of association
   * classes (as opposed to collections of the associated objects) and
   * collections of associated primary key objects (objects that serve as part
   * of the primary key of the associated class). The DTO uses this list to
   * create the mark-children-deleted code. This list includes the following
   * property-addition strategies:
   * </p>
   * <ol>
   * <li>to-one child composite aggregations</li>
   * <li>to-many child composite aggregations</li>
   * <li>to-many association class aggregations</li>
   * <li>to-many associated-key class aggregations</li>
   * </ol>
   * <p>
   * This list is a subset of the data member list.
   * </p>
   * 
   * @see #initDataMembers()
   */
  private void initChildProperties() {
    childPropertyStrategies.add(new AddToOneChildProperties());
    childPropertyStrategies.add(new AddToManyChildCollectionProperties());
    childPropertyStrategies.add(new AddToManyAssociatedKeyCollectionProperties());
    childPropertyStrategies.add(new AddAssociationClassCollectionProperties());
  }

  /**
   * <p>
   * Initialize the strategy list for insert attributes. The insert attributes
   * are the attributes in the SQL INSERT statement for a DTO. This list
   * includes the following property-addition strategies:
   * </p>
   * <ol>
   * <li>key properties appropriate to the key type</li>
   * <li>local attributes</li>
   * <li>to-one association attributes</li>
   * </ol>
   * <p>
   * This list is a subset of the constructor arg list that excludes the
   * inherited properties.
   * </p>
   * 
   * @see #initConstructorArgs()
   */
  private void initInsertProperties() {
    insertPropertyStrategies.add(new AddNaturalKeyProperties());
    insertPropertyStrategies.add(new AddExplicitAssociationKeyProperties());
    insertPropertyStrategies.add(new AddInheritedKeyProperties());
    insertPropertyStrategies.add(new AddGeneratedGuidKeyProperties());
    insertPropertyStrategies.add(new AddGeneratedKeyProperties());
    insertPropertyStrategies.add(new AddParentKeyAttributes());
    insertPropertyStrategies.add(new AddOrderedSubKeyProperties());
    insertPropertyStrategies.add(new AddUnorderedSubKeyProperties());
    insertPropertyStrategies.add(new AddExplicitSubKeyProperties());
    insertPropertyStrategies.add(new SortCompositeKeyProperties());
    insertPropertyStrategies.add(new AddLocalAttributeProperties());
    insertPropertyStrategies.add(new AddToOneAssociationAttributeProperties());
  }

  /**
   * <p>
   * Initialize the strategy list for key attributes. The key attributes are the
   * attributes in the SQL WHERE clause. The subsystem factory uses this list to
   * build primary keys. This list includes the following property-addition
   * strategies:
   * </p>
   * <ol>
   * <li>key attributes of the appropriate type</li>
   * </ol>
   * <p>
   * This list is a subset of the constructor arg list.
   * </p>
   * 
   * @see #initConstructorArgs()
   */
  private void initKeyProperties() {
    keyStrategies.add(new AddNaturalKeyProperties());
    keyStrategies.add(new AddExplicitAssociationKeyProperties());
    keyStrategies.add(new AddInheritedKeyProperties());
    keyStrategies.add(new AddGeneratedGuidKeyProperties());
    keyStrategies.add(new AddGeneratedKeyProperties());
    keyStrategies.add(new AddParentKeyAttributes());
    keyStrategies.add(new AddOrderedSubKeyProperties());
    keyStrategies.add(new AddUnorderedSubKeyProperties());
    keyStrategies.add(new AddExplicitSubKeyProperties());
  }

  /**
   * <p>
   * Initialize the strategy list for sub-key attributes. The sub-key attributes
   * are the attributes comprising the second part of the two-part composite
   * aggregation key. The subsystem factory uses this list to build composite
   * keys. This list includes the following property-addition strategies:
   * </p>
   * <ol>
   * <li>ordered sub-key properties</li>
   * <li>unordered sub-key properties</li>
   * <li>explicit sub-key properties</li>
   * </ol>
   * <p>
   * This list is a subset of the constructor arg list and the key list.
   * </p>
   * 
   * @see #initConstructorArgs()
   * @see #initKeyProperties()
   */
  private void initSubKeyProperties() {
    subKeyStrategies.add(new AddOrderedSubKeyProperties());
    subKeyStrategies.add(new AddUnorderedSubKeyProperties());
    subKeyStrategies.add(new AddExplicitSubKeyProperties());
  }

  /* Constructor */

  /**
   * Create a PersistentClassFacadeLogicImpl object. This constructor builds the
   * various sets of strategies used to produce the DTO property lists.
   * 
   * @param metaObject the UML class object the metafacade wraps
   * @param context the AndroMDA content string
   */
  public PersistentClassFacadeLogicImpl(org.eclipse.uml2.Class metaObject,
                                        String context) {
    // Set up the parent metafacade.
    super(metaObject, context);

    // Initialize the various property lists.
    initAllDtoProperties();
    initConstructorArgs();
    initTestVars();
    initColumns();
    initLocalMembers();
    initDataMembers();
    initObjectProperties();
    initCollectionProperties();
    initInheritedProperties();
    initInheritedConstructorArgs();
    initChildProperties();
    initInsertProperties();
    initKeyProperties();
    initSubKeyProperties();
  }

  /* API method implementations and helpers */

  /*
   * (non-Javadoc)
   * 
   * This method implements the transformation of the metafacade into the DTO.
   * 
   * @seecom.poesys.cartridges.db.metafacades.PersistentClassFacadeLogic#
   * handleTransformToDto()
   */
  protected Dto handleTransformToDto(Subsystem subsystem) {
    Dto dto = null;
    try {
      // Get the DTO from the cache if it's there.
      dto = cache.get(getFullyQualifiedName());

      String name = getName();

      if (!hasStereotype(PoesysDbProfile.STEREOTYPE_PERSISTENT)) {
        // Not persistent, transient object, no DTO required
        logger.warn("Not a persistent class: " + getFullyQualifiedName());
      } else if (dto == null) {
        // Nothing in the cache, proceed to create new DTO.
        logger.debug("Transforming " + name + " to DTO.");

        if (subsystem == null) {
          // No subsystem passed in, derive it from namespace.
          // Do not add DTOs and queries here, need to create DTO first.
          subsystem = getSubsystem(findSubsystemPackage());
        }

        String readOnlyStr =
          (String)findTaggedValue(PoesysDbProfile.TAGGEDVALUE_READ_ONLY);
        Boolean readOnly = new Boolean(readOnlyStr);

        String lazyStr =
          (String)findTaggedValue(PoesysDbProfile.TAGGEDVALUE_LAZY);
        Boolean lazy = new Boolean(lazyStr);

        // Set the inherited or explicit sequence name.
        String sequenceName = null;
        // If there is a root superclass, set the sequence name to the sequence
        // name from that class. If not, set the sequence name to the sequence
        // name from the current class.
        Dto root = getRootSuperclass();
        if (root != null) {
          sequenceName = root.getSequenceName();
        } else {
          sequenceName = StringUtilities.getExplicitSequenceName(metaObject);
        }
        Integer expiration = StereotypeUtilities.getExpiration(metaObject);

        // Fill in child DTOs below after creating property nested DTOs.
        dto =
          new DtoImpl(name,
                      getKeyType().toString(),
                      !readOnly,
                      getSuperpackage(),
                      getPackageName(),
                      getStereotypeNameList(),
                      getDocumentation(" * ", 80, true),
                      getTableName(metaObject),
                      isAbstract(),
                      isImmutable(),
                      !isUnremovable(),
                      isChild(),
                      sequenceName,
                      expiration,
                      lazy,
                      null,
                      subsystem,
                      getChildProperties(),
                      null,
                      getSuperclasses(),
                      getInheritedProperties(),
                      getKeyProperties(),
                      null,
                      getSubkeyProperties(),
                      associatedDtos,
                      associatedKeys,
                      null,
                      null,
                      null,
                      null,
                      null,
                      foreignKeys,
                      null,
                      null,
                      null,
                      null,
                      null);

        // Cache the DTO.
        cache.put(getFullyQualifiedName(), dto);

        // Create the properties of the DTO (after caching the created DTO to
        // avoid loops).
        createProperties(dto);

        // If there is a composite parent, get its facade and DTO and assign it.
        // This avoids an infinite loop by taking DTO creation out of the DTO
        // construction above.
        PersistentClassFacadeLogicImpl facade = getCompositeParentFacade();
        if (facade != null) {
          Dto parent = facade.handleTransformToDto(subsystem);
          dto.setCompositeParent(parent);
        }

        // Build the list of foreign keys if it's not already there.
        if (foreignKeys == null) {
          foreignKeys = getForeignKeys();
          // Sort the keys by name so they always come out in the same order.
          Collections.sort(foreignKeys, new AssociatedKeyComparator());
          dto.setForeignKeys(foreignKeys);
        }

        // Create the nested DTOs for this DTO.
        createDtos(dto);

        // Create a collection of the child properties.
        Collection<Property> children = new ArrayList<Property>();
        for (Object c : dto.getChildProperties()) {
          PropertyImpl property = (PropertyImpl)c;
          children.add(property);
        }

        // Now create the list of child DTOs, which uses the nested DTOs.
        dto.setChildDtos(getChildDtos(children));

        // Add the subclasses.
        dto.setSubclasses(getSubclasses(this));

        // Add the subsystem DTOs and queries.
        addDtosAndQueriesToSubsystem(subsystem);

        // Finally, fill in the subsystem parameterized queries.
        for (Object o : subsystem.getQueries()) {
          ParameterizedQueryImpl query = (ParameterizedQueryImpl)o;
          query.generateColumnLists();
        }

        logger.debug("Completed transformation of " + getName() + " to DTO");
      }
    } catch (RuntimeException e) {
      logger.error("Transforming " + getName() + " to DTO: ", e);
      throw e;
    } catch (InvalidParametersException e) {
      logger.error("Cannot copy null property", e);
      throw new RuntimeException(e);
    } catch (Exception e) {
      logger.error("Exception transforming "
                       + getName()
                       + " to DTO: "
                       + e.getMessage(),
                   e);
      throw new RuntimeException(e);
    }

    return dto;
  }

  /**
   * Add property-list creation for the several property lists. This takes
   * subsystem DTO creation out of the initial DTO creation and prevents
   * infinite loops.
   * 
   * @param dto the DTO to which to add properties
   */
  private void createProperties(Dto dto) {
    // Data members
    if (dto.getDataMembers() == null) {
      dto.setDataMembers(getDataMemberProperties());
    }

    // Constructor arguments.
    if (dto.getConstructorArgs() == null) {
      dto.setConstructorArgs(getConstructorArgProperties());
    }

    // Insert properties.
    if (dto.getInsertProperties() == null) {
      dto.setInsertProperties(getInsertProperties());
    }

    // Local member properties.
    if (dto.getLocalMembers() == null) {
      dto.setLocalMembers(getLocalMemberProperties());
    }

    // Object properties.
    if (dto.getObjectProperties() == null) {
      dto.setObjectProperties(getObjectProperties());
    }

    // getColumns()
    if (dto.getColumns() == null) {
      dto.setColumns(getColumns());
    }

    // Collection properties.
    if (dto.getCollectionProperties() == null) {
      dto.setCollectionProperties(getCollectionProperties());
    }

    // Test Variables.
    if (dto.getTestVars() == null) {
      dto.setTestVars(getTestVars());
    }

    // Inherited constructor arguments.
    if (dto.getInheritedConstructorArgs() == null) {
      dto.setInheritedConstructorArgs(getInheritedConstructorArgs());
    }

    // getAllDtoProperties()
    if (dto.getAllDtoProperties() == null) {
      dto.setAllDtoProperties(getAllDtoProperties());
    }
  }

  /**
   * Get the list of UML stereotype names for the class in an alphabetically
   * ordered list.
   * 
   * @return an alphabetically ordered list of UML stereotype names
   */
  private List<String> getStereotypeNameList() {
    List<String> list = new ArrayList<String>();
    for (Object o : getStereotypeNames()) {
      String name = (String)o;
      list.add(name);
    }
    java.util.Collections.sort(list);
    return list;
  }

  /**
   * Fill in nested DTOs. This must be done after caching to avoid an infinite
   * loop for recursive associations and after the dto gets created to avoid
   * problems with references to that DTO from the referring DTO. Note also that
   * this loops over the properties created within the new DTO rather than
   * calling the getXxxProperties() method on the metafacade, as the properties
   * from that would be new properties, not the ones in the DTO. Also, all
   * property lists must be processed separately, as each contains a distinct
   * set of property objects; in other words, the conceptual properties overlap
   * between the lists, but the actual property objects are completely distinct.
   * Note that this method also processes the associated keys and foreign keys,
   * which also contain lists of properties (keys and parent keys).
   * 
   * @param dto the DTO for which to process the properties
   */
  @SuppressWarnings("unchecked")
  private void createDtos(Dto dto) {
    // Process the all-properties list, which contains the complete set of
    // distinct properties in one list. These are distinct property objects
    // from the other lists, however.
    Collection<PropertyImpl> allProperties = dto.getAllDtoProperties();
    for (PropertyImpl property : allProperties) {
      property.createDto();
      property.createOwner();
      property.createInheritedFrom();
    }

    // Now, process a collection of all properties from the separate
    // property lists and create the owning DTO in them.
    allProperties = new ArrayList<PropertyImpl>(dto.getConstructorArgs());
    allProperties.addAll(dto.getConstructorArgs());
    allProperties.addAll(dto.getLocalMembers());
    allProperties.addAll(dto.getDataMembers());
    allProperties.addAll(dto.getObjectProperties());
    allProperties.addAll(dto.getCollectionProperties());
    allProperties.addAll(dto.getInheritedConstructorArgs());
    allProperties.addAll(dto.getInheritedProperties());
    allProperties.addAll(dto.getChildProperties());
    allProperties.addAll(dto.getInsertProperties());
    allProperties.addAll(dto.getKeyProperties());
    allProperties.addAll(dto.getSubKeyProperties());
    allProperties.addAll(dto.getColumns());
    allProperties.addAll(dto.getTestVars());

    for (PropertyImpl property : allProperties) {
      property.createDto();
      property.createOwner();
      property.createInheritedFrom();
    }

    // Create the DTOs for the associated keys.
    Collection<AssociatedKey> associatedKeys = dto.getAssociatedKeys();
    if (associatedKeys != null) {
      for (AssociatedKey key : associatedKeys) {
        Collection<PropertyImpl> keys = key.getKeyProperties();
        if (keys != null) {
          for (PropertyImpl property : keys) {
            property.createDto();
            property.createOwner();
            property.createInheritedFrom();
          }
        }
        Collection<PropertyImpl> parentKeys = key.getParentKeyProperties();
        if (parentKeys != null) {
          for (PropertyImpl property : parentKeys) {
            property.createDto();
            property.createOwner();
            property.createInheritedFrom();
          }
        }
      }
    }

    // Create the DTOs for the foreign keys.
    Collection<AssociatedKey> foreignKeys = dto.getForeignKeys();
    if (foreignKeys != null) {
      for (AssociatedKey key : foreignKeys) {
        Collection<PropertyImpl> keys = key.getKeyProperties();
        if (keys != null) {
          for (PropertyImpl property : keys) {
            property.createDto();
            property.createOwner();
            property.createInheritedFrom();
          }
        }
        Collection<PropertyImpl> parentKeys = key.getParentKeyProperties();
        if (parentKeys != null) {
          for (PropertyImpl property : parentKeys) {
            property.createDto();
            property.createOwner();
            property.createInheritedFrom();
          }
        }
      }
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.poesys.cartridges.db.metafacades.PersistentClassFacadeLogic#handleIsChild
   * ()
   */
  @Override
  protected boolean handleIsChild() {
    boolean child = false;

    // Is the object an association class?
    if (metaObject instanceof AssociationClass) {
      child = true;
    } else {
      // Is the object the target of a composite aggregation or a PK
      // association?
      for (Object o : metaObject.getOwnedAttributes()) {
        org.eclipse.uml2.Property p = (org.eclipse.uml2.Property)o;
        if (p.getAssociation() != null
            && p.getOpposite() != null
            && p.getOpposite().isComposite()) {
          // Target of composite aggregation
          child = true;
          break;
        } else if (p.getAppliedStereotype(PERSISTENT) != null
                   && p.getAssociation() != null
                   && p.getAppliedStereotype(PK) != null) {
          /*
           * The association end is persistent and has a PK stereotype
           * indicating the variable is part of the primary key of this class.
           * Since the primary key depends on the primary key of another class,
           * it means the current class should be treated as a child of the
           * other class. However, if the two classes are in different
           * subsystems, and those subsystems are implemented in different
           * databases, this assumption causes big problems. So the child tagged
           * value specifies whether to treat the class as a child.
           */

          child = getPkChild(p);
        }
      }
    }
    return child;
  }

  /**
   * Get the child flag on a persistent association end treated as a primary key
   * in the embedding class. Check for an explicit flag in the PK stereotype on
   * the property and default to true.
   * 
   * @param p a persistent association end property
   * @return true if the owner class is a child, false if not
   */
  private boolean getPkChild(org.eclipse.uml2.Property p) {
    boolean child = true; // default to true

    // Get the child tag of the persistent association end PK stereotype.
    Stereotype s = p.getAppliedStereotype(PK);
    if (s != null) {
      Boolean childTag =
        (Boolean)p.getValue(s, PoesysDbProfile.TAGGEDVALUE_PK_CHILD);
      if (childTag != null) {
        // Tagged, set the return to the tagged value
        child = childTag;
      }
    }
    return child;
  }

  /*
   * (non-Javadoc)
   * 
   * This method implements the isReadOnly method on the metafacade.
   * 
   * @seecom.poesys.cartridges.db.metafacades.PersistentClassFacadeLogic#
   * handleIsReadOnly()
   */
  @Override
  protected boolean handleIsReadOnly() {
    boolean readOnly = false;
    Stereotype stereotype = metaObject.getAppliedStereotype(PERSISTENT);
    if (stereotype != null) {
      Boolean taggedValue =
        (Boolean)metaObject.getValue(stereotype,
                                     PoesysDbProfile.TAGGEDVALUE_READ_ONLY);
      if (taggedValue != null) {
        readOnly = taggedValue;
      }
    }
    return readOnly;
  }

  /*
   * (non-Javadoc)
   * 
   * Implements the isImmutable metafacade method
   * 
   * @seecom.poesys.cartridges.db.metafacades.PersistentClassFacadeLogic#
   * handleIsImmutable()
   */
  @Override
  protected boolean handleIsImmutable() {
    Stereotype stereotype = metaObject.getAppliedStereotype(IMMUTABLE);
    return stereotype != null ? true : false;
  }

  /*
   * (non-Javadoc)
   * 
   * Implements the isUnremovable metafacade method
   * 
   * @seecom.poesys.cartridges.db.metafacades.PersistentClassFacadeLogic#
   * handleIsUnremovable()
   */
  @Override
  protected boolean handleIsUnremovable() {
    Stereotype stereotype = metaObject.getAppliedStereotype(UNREMOVABLE);
    return stereotype != null ? true : false;
  }

  /*
   * The following series of methods get the various lists of properties that
   * the templates use in foreach loops. Properties overlap between these lists,
   * which appear in the metafacade UML as associations between the Dto and
   * Property classes. The methods use the various mutually exclusive strategies
   * for adding properties to build the set of properties relevant for the list.
   * Order of use of the strategies is important, as this is how you control the
   * order of the properties in the resulting list.
   */

  /**
   * <p>
   * Get a list of all the properties for the DTO. This set of properties is the
   * complete set of properties of the DTO gathered from all the nested
   * IAddPropertyStrategy strategy classes. Use this list when you want the
   * complete list of unique properties for the DTO
   * </p>
   * <p>
   * <strong>Note that when you add a new property strategy, you must add it to
   * the allStrategies collection as well as to the relevant other property
   * strategy collections.</strong>
   * </p>
   * 
   * @return a list of all DTO properties
   */
  private List<Property> getAllDtoProperties() {
    List<Property> list = new ArrayList<Property>();

    for (IAddPropertyStrategy strategy : allDtoPropertyStrategies) {
      strategy.addProperties(list);
    }

    return list;
  }

  /**
   * <p>
   * Get a list of properties for the DTO constructor that the DAO and SQL
   * statement specification query from the database, including generated
   * properties, attributes, and to-one associations but not to-many
   * associations; the latter get queried by setters separately.
   * </p>
   * <p>
   * The to-one association results in one or more properties derived from the
   * primary key of the associated object, named using the association property
   * name to avoid duplicate naming.
   * </p>
   * <p>
   * Many code generation templates use this set of properties, as it is the
   * list of database columns as well as the basic set of properties for the
   * object.
   * </p>
   * 
   * @return a list of constructor-argument properties
   */
  private List<Property> getConstructorArgProperties() {
    List<Property> list = new ArrayList<Property>();

    for (IAddPropertyStrategy strategy : constructorArgStrategies) {
      strategy.addProperties(list);
    }

    return list;
  }

  /**
   * <p>
   * Get a list of properties for the DTO constructor that represent the test
   * variables declared locally in the JUnit delegate test case. These variables
   * are the constructor arguments minus the required, to-one association key
   * attributes.
   * </p>
   * 
   * @return a list of constructor-argument properties
   */
  private List<Property> getTestVars() {
    List<Property> list = new ArrayList<Property>();

    for (IAddPropertyStrategy strategy : testVarStrategies) {
      strategy.addProperties(list);
    }

    return list;
  }

  /**
   * <p>
   * Get a list of properties representing the SQL table columns for the DTO,
   * including generated attributes, attributes, and to-one associations but not
   * to-many associations; the latter get queried by setters separately. Also,
   * there are no inherited properties and the identity key is included.
   * </p>
   * <p>
   * The to-one association results in one or more properties derived from the
   * primary key of the associated object, named using the association property
   * name to avoid duplicate naming.
   * </p>
   * 
   * @return a list of column properties
   */
  private List<Property> getColumns() {
    List<Property> list = new ArrayList<Property>();

    for (IAddPropertyStrategy strategy : columnStrategies) {
      strategy.addProperties(list);
    }

    return list;
  }

  /**
   * <p>
   * Get a list of properties for the DTO constructor body assignments. These
   * assignments include the local keys and the local attributes. Inherited
   * constructor arguments get passed to the superclass and association data
   * members get set by setters.
   * </p>
   * 
   * @return a list of local-member properties
   */
  private Collection<Property> getLocalMemberProperties() {
    List<Property> list = new ArrayList<Property>();

    for (IAddPropertyStrategy strategy : localMemberStrategies) {
      strategy.addProperties(list);
    }

    return list;
  }

  /**
   * Get a list of all the data-member properties for the DTO. The data-member
   * properties are all the properties that represent data members and their
   * accessors in the current DTO class.
   * 
   * @return a list of data member properties
   */
  private List<Property> getDataMemberProperties() {
    List<Property> list = new ArrayList<Property>();

    for (IAddPropertyStrategy strategy : dataMemberStrategies) {
      strategy.addProperties(list);
    }

    return list;
  }

  /**
   * Get a list of all the object data-member properties for the DTO. The object
   * data-member properties are all the properties that represent to-one
   * associated-object data members and their set strategies in the current DTO
   * class. This method has package access to allow other metafacades to use it.
   * 
   * @return a list of associated-object data member properties
   */
  List<Property> getObjectProperties() {
    List<Property> list = new ArrayList<Property>();

    for (IAddPropertyStrategy strategy : objectStrategies) {
      strategy.addProperties(list);
    }

    return list;
  }

  /**
   * Get a list of all the collection data-member properties for the DTO. The
   * object data-member properties are all the properties that represent to-many
   * associated-object data members and their set strategies in the current DTO
   * class. This method has package access to allow other metafacades to use it.
   * 
   * @return a list of associated-object data member properties
   */
  List<Property> getCollectionProperties() {
    List<Property> list = new ArrayList<Property>();

    for (IAddPropertyStrategy strategy : collectionStrategies) {
      strategy.addProperties(list);
    }

    return list;
  }

  /**
   * Get the set of all properties inherited from the superclass, if any. These
   * properties let you create wrappers for all the inherited properties in
   * proxies and business DTOs.
   * 
   * @see #getConstructorArgProperties()
   * 
   * @return the list of properties, possibly with no properties if there is no
   *         superclass
   */
  private List<Property> getInheritedProperties() {
    List<Property> list = new ArrayList<Property>();

    for (IAddPropertyStrategy strategy : inheritedPropertyStrategies) {
      strategy.addProperties(list);
    }

    return list;
  }

  /**
   * Get the set of queried properties inherited from the superclass, if any.
   * These properties let you create constructor calls to the superclass. This
   * set includes just the queried properties of the superclass and its own
   * superclasses, not the queried properties of the current class.
   * 
   * @see #getConstructorArgProperties()
   * 
   * @return the list of properties, possibly with no properties if there is no
   *         superclass
   */
  private List<Property> getInheritedConstructorArgs() {
    List<Property> list = new ArrayList<Property>();

    for (IAddPropertyStrategy strategy : inheritedConstructorArgStrategies) {
      strategy.addProperties(list);
    }

    return list;
  }

  /**
   * Get a list of child properties, the DTO properties that represent composite
   * aggregations of a parent DTO class. These properties represent the
   * associations that cascade in cascaded deletes. Each property has a nested
   * "child" DTO that represents the class object of the property.
   * 
   * @return a list of child properties
   */
  private List<Property> getChildProperties() {
    List<Property> list = new ArrayList<Property>();

    for (IAddPropertyStrategy strategy : childPropertyStrategies) {
      strategy.addProperties(list);
    }

    return list;
  }

  /**
   * Get a list of all the insert properties for the current DTO class. The
   * insert properties are the properties that appear in the INSERT statement's
   * list of columns. This list is also the basis for UPDATE statements, taking
   * the read/write nature of each property into account. The list does not
   * include the property corresponding to an identity key, which you cannot
   * supply in an INSERT statement.
   * 
   * @return a list of insert properties
   */
  private List<Property> getInsertProperties() {
    List<Property> list = new ArrayList<Property>();

    for (IAddPropertyStrategy strategy : insertPropertyStrategies) {
      strategy.addProperties(list);
    }

    if (metaObject.getAppliedStereotype(IDENTITY) != null) {
      // Identity key, set insert properties by taking off the primary key.
      list.remove(0); // id always the first, remove it.
    }
    return list;
  }

  /**
   * Get a list of all the primary key properties for the DTO that the DAO and
   * SQL statement specification query from the database, including generated
   * key properties and foreign key properties that are also part of the primary
   * key. Sort the list alphabetically by property name.
   * 
   * @return a list of primary key properties
   */
  private List<Property> getKeyProperties() {
    List<Property> list = new ArrayList<Property>();

    for (IAddPropertyStrategy strategy : keyStrategies) {
      strategy.addProperties(list);
    }

    Collections.sort(list, new KeyPropertyComparator());
    return list;
  }

  /**
   * Get a list of all the primary key properties for the composite DTO that
   * comprise the part of the primary key other than the parent key (the sub
   * key). These are attributes that the DAO and SQL statement specification
   * query from the database, including natural keys defined in the composite
   * class itself and keys brought in by association to another class.
   * 
   * @return a list of sub-key properties
   */
  private List<Property> getSubkeyProperties() {
    List<Property> list = new ArrayList<Property>();

    for (IAddPropertyStrategy strategy : subKeyStrategies) {
      strategy.addProperties(list);
    }

    return list;
  }

  /**
   * Get the facade representing an optional composite parent. This is the class
   * on the other side of a composite aggregation association, where the other
   * side has the composite marker. The method returns null if this class is not
   * a composite aggregation child. The method returns the facade instead of a
   * transformed DTO because that might create an infinite loop. The calling
   * method should cache the current DTO (the child) before creating a DTO for
   * the parent using this facade.
   * 
   * @return a facade for the composite parent or null if there is no parent
   */
  private PersistentClassFacadeLogicImpl getCompositeParentFacade() {
    PersistentClassFacadeLogicImpl facade = null;
    for (Object o : metaObject.getOwnedAttributes()) {
      org.eclipse.uml2.Property p = (org.eclipse.uml2.Property)o;
      // We want composite aggregation properties: parent.
      if (p.getAssociation() != null
          && p.getOpposite() != null
          && p.getOpposite().isComposite()) {
        facade =
          new PersistentClassFacadeLogicImpl((ClassImpl)p.getType(), CONTEXT);
        break; // only one parent
      } else if (p.getAssociation() != null && p.getOpposite() == null) {
        // Parent end is not navigable, get from association
        Association a = p.getAssociation();
        org.eclipse.uml2.Property parentEnd = null;
        boolean composite = false;
        for (Object object : a.getMemberEnds()) {
          org.eclipse.uml2.Property memberEnd =
            (org.eclipse.uml2.Property)object;
          // Check to see if the member end is composite.
          if (memberEnd.isComposite()) {
            composite = true;
          }
          if (!memberEnd.getName().equals(p.getName())) {
            parentEnd = memberEnd;
          }
        }
        if (composite && parentEnd != null) {
          facade =
            new PersistentClassFacadeLogicImpl((ClassImpl)parentEnd.getType(),
                                               CONTEXT);
          break; // only one parent
        }
      }
    }
    return facade;
  }

  /**
   * Get the SQL table name, the name of the class as represented in the
   * database. This will usually be the same as the class name, but the designer
   * can override it with a tagged value if required. The name stays in the same
   * case it is in the UML repository, either as the class name or as the tagged
   * value. If the SQL engine respects case in identifier names and the UML case
   * is not the same as the case of the database identifier, then the designer
   * will need to use the tagged value to get the right case. This method has
   * package visibility to allow AssociationClassFacade to use it.
   * 
   * @param owner the class that owns the property
   * 
   * @return the SQL table name
   */
  String getTableName(org.eclipse.uml2.Class owner) {
    String tableName = owner.getName();
    // Check for a SQL table name tagged value on the class.
    Stereotype stereotype = owner.getAppliedStereotype(PERSISTENT);
    if (stereotype != null) {
      EDataTypeUniqueEList valueList =
        (EDataTypeUniqueEList)owner.getValue(stereotype,
                                             PoesysDbProfile.TAGGEDVALUE_NAME);
      if (valueList != null && valueList.size() > 0) {
        // Get element 0, which should be the table name.
        tableName = (String)valueList.get(0);
      }
    }

    return tableName;
  }

  /**
   * Get the property name, the name of the property as represented in the
   * database as a foreign key. This will usually be the same as the UML
   * property name, but the designer can override it with a tagged value if
   * required. The name stays in the same case it is in the UML repository,
   * either as the property name or as the tagged value. This name becomes the
   * name of the foreign key constraint in the schema. This method has package
   * visibility to allow AssociationClassFacade to use it.
   * 
   * @param property the property from which to get the name
   * 
   * @return the property name
   */
  String getPropertyName(org.eclipse.uml2.Property property) {
    String propertyName = StringUtilities.getExplicitPropertyName(property);
    // Return either the optional explicit name or the property name.
    return propertyName == null ? property.getName() : propertyName;
  }

  /**
   * Get the subsystem to which the class belongs, which is the UML package that
   * contains the class that has the stereotype SUBSYSTEM. This helper method is
   * package-scoped to enable the PersistentAssociationClassFacade to use it.
   * 
   * @param umlPackage the UML package for which to get the subsystem
   * 
   * @return the subsystem
   */
  Subsystem getSubsystem(Package umlPackage) {
    Subsystem subsystem = null;

    // If there is a valid subsystem package, produce a Subsystem.
    if (umlPackage != null) {
      SubsystemFacadeLogicImpl facade =
        new SubsystemFacadeLogicImpl(umlPackage, CONTEXT);
      subsystem = facade.handleTransformToSubsystem();
    } else {
      subsystem =
        new Subsystem(PoesysDbProfile.DEFAULT_SUBSYSTEM,
                      PoesysDbProfile.DEFAULT_SUBSYSTEM,
                      "Default subsystem name",
                      StringUtils.capitalize(PoesysDbProfile.DEFAULT_SUBSYSTEM),
                      StringUtils.capitalize(PoesysDbProfile.DEFAULT_SUBSYSTEM),
                      false,
                      StereotypeUtilities.CACHED_VALUE,
                      null,
                      allDtoPropertyStrategies,
                      allDtoPropertyStrategies);
    }
    return subsystem;
  }

  /**
   * Add all the DTOs and parameterized queries to a subsystem that has no list
   * of DTOs.
   * 
   * @param subsystem the subsystem to which to add the DTOs and queries
   */
  @SuppressWarnings("unchecked")
  private void addDtosAndQueriesToSubsystem(Subsystem subsystem) {
    Collection<Dto> dtos = subsystem.getDtos();

    if (dtos == null) {
      // No DTO collection, create one.
      dtos = new ArrayList<Dto>();
      subsystem.setDtos(dtos);
    }

    // Only add DTOs and queries if none already exist and the system isn't
    // already being built higher up in the calling stack.
    if (dtos.size() == 0 && !subsystem.isBuilding()) {
      /*
       * Set the subsystem as in process. Under certain circumstances, the
       * process that fires off when you call addDtosAndQueriesToPackage below
       * recurses back to this method while the DTO list is still empty, causing
       * the whole subsystem to be built twice and to have all the DTOs and
       * queries in their lists twice. Setting this semaphore and resetting it
       * at the end of this block solves this problem.
       */
      subsystem.setBuilding(true);
      org.eclipse.uml2.Package umlPackage = findSubsystemPackage();
      addDtosAndQueriesToPackage(subsystem, dtos, umlPackage);
      // Reset the system building.
      subsystem.setBuilding(false);
    }
  }

  /**
   * Add a set of DTOs to a subsystem using a UML package and metafacade.
   * 
   * @param subsystem the subsystem to which to add the DTOs
   * @param dtos the DTOs to add
   * @param umlPackage the UML2 package corresponding to the subsystem
   */
  private void addDtosAndQueriesToPackage(Subsystem subsystem,
                                          Collection<Dto> dtos,
                                          org.eclipse.uml2.Package umlPackage) {
    SubsystemFacadeLogicImpl subsystemFacade = null;

    // If there is a valid subsystem package, produce a Subsystem.
    if (umlPackage != null) {
      subsystemFacade = new SubsystemFacadeLogicImpl(umlPackage, CONTEXT);

      if (subsystemFacade != null) {

        for (Object o : subsystemFacade.getClasses()) {
          if (o instanceof PersistentClassFacade) {
            addClassDtoAndQueries(subsystem, dtos, (PersistentClassFacade)o);
          } else if (o instanceof PersistentAssociationClassFacade) {
            addAssociationDtoAndQueries(subsystem,
                                        dtos,
                                        (PersistentAssociationClassFacade)o);
          }
        }
      }
    }
  }

  /**
   * Add a DTO corresponding to a class and its queries to a subsystem using a
   * persistent class metafacade.
   * 
   * @param subsystem the subsystem to which to add the DTO
   * @param dtos the DTOs to add
   * @param facade the metafacade for the class
   */
  private void addClassDtoAndQueries(Subsystem subsystem, Collection<Dto> dtos,
                                     PersistentClassFacade facade) {
    Dto dto = facade.transformToDto(subsystem);
    dtos.add(dto);
    for (Object a : facade.getAssociationEnds()) {
      if (a instanceof ParameterizedQuerySqlFacadeLogicImpl) {
        AssociationEnd end =
          (AssociationEnd)((ParameterizedQuerySqlFacadeLogicImpl)a).getMetaObject();
        // Get the opposite DTO.
        Type oppositeType = null;
        org.eclipse.uml2.Property oppositeProperty = end.getOpposite();
        if (oppositeProperty != null) {
          oppositeType = oppositeProperty.getType();
        } else {
          // not navigable, try through the association.
          Association association = end.getAssociation();
          for (Object o : association.getMemberEnds()) {
            org.eclipse.uml2.Property memberEnd = (org.eclipse.uml2.Property)o;
            if (!memberEnd.getName().equals(end.getName())) {
              oppositeType = memberEnd.getType();
            }
          }
        }

        // Test for opposite end to exclude non-navigable associations.
        if (oppositeType != null && end.getOpposite() != null) {
          Dto oppositeDto = createDtoFromType(oppositeType);
          // Get the association DTO, if any.
          Type associationType = end.getAssociation();
          Dto associationDto = createDtoFromType(associationType);

          addParameterizedQueryToSubsystem(subsystem,
                                           end,
                                           dto,
                                           oppositeDto,
                                           associationDto);
        }
      }
    }
  }

  /**
   * Create a DTO from a UML2 type
   * 
   * @param type the UML2 type
   * @return the DTO corresponding to the type
   */
  private Dto createDtoFromType(Type type) {
    Dto dto = null;
    if (type instanceof AssociationClass) {
      PersistentAssociationClassFacadeLogicImpl f =
        new PersistentAssociationClassFacadeLogicImpl((AssociationClass)type,
                                                      CONTEXT);
      dto = f.transformToDto(null);
    } else if (type instanceof ClassImpl) {
      PersistentClassFacadeLogicImpl f =
        new PersistentClassFacadeLogicImpl((ClassImpl)type, CONTEXT);
      dto = f.transformToDto(null);
    }
    return dto;
  }

  /**
   * Add a DTO corresponding to an association class and its queries to a
   * subsystem using a persistent association class metafacade.
   * 
   * @param subsystem the subsystem to which to add the association class DTO
   * @param dtos the collection of DTOs to which to add the association DTO
   * @param facade the metafacade for the association class
   */
  private void addAssociationDtoAndQueries(Subsystem subsystem,
                                           Collection<Dto> dtos,
                                           PersistentAssociationClassFacade facade) {
    Dto dto = facade.transformToDto(subsystem);
    dtos.add(dto);
    if (isRecursiveAssociationClass(dto)) {
      // Recursive association classes have no association ends, just use class.
      addRecursiveAssociationParameterizedQueryToSubsystem(subsystem, dto);
    } else {
      for (Object a : facade.getAssociationEnds()) {
        if (a instanceof ParameterizedQuerySqlFacadeLogicImpl) {
          // Add only parameterized queries (to-many, persistent ends)
          AssociationEnd end =
            (AssociationEnd)((ParameterizedQuerySqlFacadeLogicImpl)a).getMetaObject();
          // Get the opposite DTO.
          org.eclipse.uml2.Property opposite = end.getOpposite();
          // Exclude non-navigable associations.
          if (opposite != null) {
            Type oppositeType = end.getOpposite().getType();
            Dto oppositeDto = createDtoFromType(oppositeType);
            Type associationType = end.getAssociation();
            Dto associationDto = createDtoFromType(associationType);
            addParameterizedQueryToSubsystem(subsystem,
                                             end,
                                             dto,
                                             oppositeDto,
                                             associationDto);
          }
        }
      }
    }
  }

  /**
   * Add one or more parameterized queries (persistent, to-many, recursive
   * association ends) to the subsystem queries collection for a recursive
   * association.
   * 
   * @param subsystem the subsystem object containing the queries collection
   * @param associationDto the DTO for the association class
   */
  private void addRecursiveAssociationParameterizedQueryToSubsystem(Subsystem subsystem,
                                                                    Dto associationDto) {
    Collection<Dto> recursiveAssociatedDtos =
      getRecursiveAssociatedDtos(associationDto);

    Subsystem linksSubsystem =
      associationDto != null ? associationDto.getSubsystem() : null;

    String associationClassName =
      associationDto.getPackageName() + "." + associationDto.getName();

    // Process each recursive associated Dto into a query.
    for (Dto associatedDto : recursiveAssociatedDtos) {
      Property queryProperty =
        getQueryProperty(associationClassName, associatedDto);
      if (queryProperty != null) {
        @SuppressWarnings("unchecked")
        Collection<ParameterizedQuery> queries = subsystem.getQueries();
        queries.add(new ParameterizedQueryImpl(associationDto.getName(),
                                               associatedDto.getName(),
                                               associationDto.getSqlTableName(),
                                               queryProperty.getName(),
                                               null,
                                               null,
                                               null,
                                               associatedDto.getPackageName(),
                                               associationDto.getPackageName(),
                                               associationDto.getPackageName(),
                                               null,
                                               null,
                                               subsystem,
                                               null,
                                               null,
                                               null,
                                               associationDto,
                                               associationDto,
                                               linksSubsystem,
                                               associatedDto,
                                               null));
      } else {
        logger.error("No query property for recursive association "
                     + associationDto.getName()
                     + " for object "
                     + associatedDto.getName());
      }
    }

  }

  /**
   * A recursive association class may have any number of recursive links,
   * although for most such classes, there will be only one. The association may
   * be an n-ary association, so to get all the recursive links, you must
   * iterate through all the associated classes and return any where there is
   * more than one instance of the class in the association.
   * 
   * @param associationDto the DTO for the recursive association class.
   * 
   * @return the associated DTO
   */
  private Collection<Dto> getRecursiveAssociatedDtos(Dto associationDto) {
    Collection<Dto> dtos = new ArrayList<Dto>(1);

    Collection<?> classes = associationDto.getAssociatedDtos();
    List<String> names = new ArrayList<String>(classes.size());

    for (Object o : classes) {
      Dto associatedDto = (Dto)o;
      String name =
        associatedDto.getPackageName() + "." + associatedDto.getName();
      if (names.contains(name)) {
        // Name is already there, so it's recursive.
        dtos.add(associatedDto);
      } else {
        // Name not already there, add it.
        names.add(name);
      }
    }

    return dtos;
  }

  /**
   * Get the query property of a class, the property of an associated DTO that
   * returns a collection of objects of a given association class specified by
   * fully-qualified class name.
   * 
   * @param className the fully-qualified name of the association class
   * @param associatedDto the DTO for the associated class
   * @return the property
   */
  private Property getQueryProperty(String className, Dto associatedDto) {
    Property queryProperty = null;
    if (associatedDto == null) {
      throw new RuntimeException("Null associated DTO for class " + className);
    }

    if (associatedDto.getCollectionProperties() == null) {
      logger.error("No collection properties for class "
                   + className
                   + " associated DTO "
                   + associatedDto.getName());
    } else {
      for (Object p : associatedDto.getCollectionProperties()) {
        Property property = (Property)p;
        String objectClassName = property.getObjectClassName();
        if (objectClassName.equals(className)) {
          queryProperty = property;
          break;
        }
      }
    }
    return queryProperty;
  }

  /**
   * Find the containing package of this class that has the stereotype
   * &laquo;Subsystem&raquo;.
   * 
   * @return the UML2 package corresponding to the subsystem
   */
  org.eclipse.uml2.Package findSubsystemPackage() {
    org.eclipse.uml2.Package umlPackage = metaObject.getPackage();
    while (umlPackage.getAppliedStereotype(SUBSYSTEM) == null) {
      umlPackage = umlPackage.getNestingPackage();
      if (umlPackage instanceof ModelImpl || umlPackage == null) {
        // Reached the top level Model, end here with a null package.
        umlPackage = null;
        break;
      }
    }
    return umlPackage;
  }

  /**
   * Find the containing package of this class that has the stereotype
   * &laquo;Subsystem&raquo;.
   * 
   * @param type the type for which to get the subsystem package
   * 
   * @return the UML2 package corresponding to the subsystem
   */
  org.eclipse.uml2.Package findTypeSubsystemPackage(Type type) {
    org.eclipse.uml2.Package umlPackage = type.getPackage();
    while (umlPackage.getAppliedStereotype(SUBSYSTEM) == null) {
      umlPackage = umlPackage.getNestingPackage();
      if (umlPackage instanceof ModelImpl || umlPackage == null) {
        // Reached the top level Model, end here with a null package.
        umlPackage = null;
        break;
      }
    }
    return umlPackage;
  }

  /**
   * Find the containing package of this class that has the stereotype
   * &laquo;Subsystem&raquo;.
   * 
   * @param association the association for which to get the subsystem package
   * 
   * @return the UML2 package corresponding to the subsystem
   */
  org.eclipse.uml2.Package findAssociationSubsystemPackage(Association association) {
    org.eclipse.uml2.Package umlPackage = association.getPackage();
    while (umlPackage.getAppliedStereotype(SUBSYSTEM) == null) {
      umlPackage = umlPackage.getNestingPackage();
      if (umlPackage instanceof ModelImpl || umlPackage == null) {
        // Reached the top level Model, end here with a null package.
        umlPackage = null;
        break;
      }
    }
    return umlPackage;
  }

  /**
   * Add a parameterized query (a persistent, to-many association end) to the
   * subsystem queries collection.
   * 
   * @param subsystem the subsystem object containing the queries collection
   * @param end the association end
   * @param dto the end DTO
   * @param oppositeDto the DTO at the opposite end of the association
   * @param associationDto the DTO for the association itself, null if the
   *          association does not have an association class
   */
  @SuppressWarnings("unchecked")
  private void addParameterizedQueryToSubsystem(Subsystem subsystem,
                                                AssociationEnd end, Dto dto,
                                                Dto oppositeDto,
                                                Dto associationDto) {
    if (end.getAppliedStereotype(PoesysDbProfile.NAMESPACE_PERSISTENCE
                                 + PoesysDbProfile.STEREOTYPE_PERSISTENT) != null
        && end.upper() != 1
        && dto != null
        && oppositeDto != null) {

      // This is a to-many, persistent property (a foreign-key query).

      String packageName =
        StringUtilities.getQualifiedJavaName(end.getType().getPackage().getQualifiedName());

      org.eclipse.uml2.Property opposite = end.getOpposite();
      String foreignPackage = null;
      // Opposite end may be null for non-navigable association.
      if (opposite != null) {
        foreignPackage =
          StringUtilities.getQualifiedJavaName(opposite.getType().getPackage().getQualifiedName());
      }

      Subsystem linksSubsystem =
        associationDto != null ? associationDto.getSubsystem() : null;

      Collection<ParameterizedQuery> queries = subsystem.getQueries();
      queries.add(new ParameterizedQueryImpl(dto.getName(),
                                             oppositeDto.getName(),
                                             dto.getSqlTableName(),
                                             end.getName(),
                                             null,
                                             null,
                                             null,
                                             foreignPackage,
                                             packageName,
                                             null,
                                             null,
                                             null,
                                             subsystem,
                                             null,
                                             null,
                                             null,
                                             associationDto,
                                             oppositeDto,
                                             linksSubsystem,
                                             dto,
                                             null));
    }
  }

  /**
   * Get the ordered list of superclasses starting with the root and walking the
   * tree downwards breadth-first.
   * 
   * @return the superclass DTO or null if there is no superclass
   */
  @SuppressWarnings("unchecked")
  private List<Dto> getSuperclasses() {
    LinkedList<Dto> superclasses = new LinkedList<Dto>();
    List<MetafacadeBase> generalizations = new ArrayList<MetafacadeBase>();
    Collection<? extends MetafacadeBase> nextLevel =
      new ArrayList<MetafacadeBase>();
    HashMap<String, Dto> map = new HashMap<String, Dto>();

    // Initialize the generalization lists to the bottom level.
    for (Object o : getGeneralizations()) {
      if (o instanceof PersistentClassFacadeLogicImpl) {
        // Persistent class
        MetafacadeBase facade = (MetafacadeBase)o;
        generalizations.add(facade);
      } else if (o instanceof PersistentAssociationClassFacadeLogicImpl) {
        // Persistent association class
        MetafacadeBase facade = (MetafacadeBase)o;
        generalizations.add(facade);
      }
      // ignore all other types of classes
    }

    // Proceed level by level, starting with the current class and moving up
    // the generalization hierarchy.
    while (generalizations.size() > 0) {
      // Iterate through all the generalizations at this level.
      for (MetafacadeBase facade : generalizations) {
        String name = null;
        Dto dto = null;
        if (facade instanceof PersistentClassFacadeLogicImpl) {
          name = ((PersistentClassFacadeLogicImpl)facade).getName();
          dto = ((PersistentClassFacadeLogicImpl)facade).transformToDto(null);
          nextLevel.addAll(((PersistentClassFacadeLogicImpl)facade).getGeneralizations());
        } else if (facade instanceof PersistentAssociationClassFacadeLogicImpl) {
          name = ((PersistentAssociationClassFacadeLogicImpl)facade).getName();
          dto =
            ((PersistentAssociationClassFacadeLogicImpl)facade).transformToDto(null);
          nextLevel.addAll(((PersistentAssociationClassFacadeLogicImpl)facade).getGeneralizations());
        }
        // If already mapped, ignore and move on to next class. Trims branch.
        if (map.get(name) == null) {
          // DTO not yet processed, proceed
          // Add DTO to beginning of list and map it to detect duplicates above.
          superclasses.addFirst(dto);
          map.put(name, dto);
        }
      }
      // Move up to next level.
      generalizations.clear();
      generalizations.addAll(nextLevel);
      nextLevel.clear();
    }

    return superclasses;
  }

  /**
   * Get an ordered list of the subclasses of a specified class. The odering is
   * depth-first, presenting a list of the concrete subclasses and their parents
   * in bottom-up order. The method recurses down the tree in depth-first order.
   * 
   * @param currentClass the metafacade for the class for which you want to get
   *          subclasses
   * 
   * @return a depth-first ordered list of subclasses of this DTO's class
   */
  private List<Dto> getSubclasses(ClassifierFacade currentClass) {
    List<Dto> subclasses = new ArrayList<Dto>();
    logger.debug("Getting subclasses for " + currentClass.getName());

    // Get the subclasses of the current class as metafacades.
    for (Object o : currentClass.getSpecializations()) {
      ClassifierFacade facade = (ClassifierFacade)o;
      // For each facade, recurse to get its subclasses.
      subclasses.addAll(getSubclasses(facade));
      // Now add the current subclass as a DTO.
      Dto currentDto = null;
      if (facade instanceof PersistentClassFacadeLogicImpl) {
        currentDto =
          ((PersistentClassFacadeLogicImpl)facade).transformToDto(null);
      } else if (facade instanceof PersistentAssociationClassFacadeLogicImpl) {
        currentDto =
          ((PersistentAssociationClassFacadeLogicImpl)facade).transformToDto(null);
      } // skip non-persistent classes if any
      subclasses.add(currentDto);
    }
    return subclasses;
  }

  /**
   * Get all the child DTOs nested within this class. A child DTO is the DTO
   * corresponding to a class that is the target of a composite aggregation from
   * this class or children of this class OR is an association class linked to
   * this class or to a child of this class. This works by looping through all
   * the child properties and recursing on each property DTO. The output list
   * orders the DTOs in dependency order so the list can be used to delete the
   * rows from the tables (or indeed the tables themselves) sequentially without
   * getting foreign-key-reference errors. The first DTO in the list will have
   * no children, the last DTO will be a root DTO with no parents elsewhere in
   * the list. Note that this method begins with the current DTO as the root
   * parent, and that DTO is not in the list. If the DTO appears more than once
   * in the dependency structure, duplicate elimination will remove the
   * instances that appear later in the list (in other words, the first
   * appearance is the appearance closest to the bottom of the tree).
   * 
   * @param children the collection of properties for which to extract DTOs
   * 
   * @see #initChildProperties()
   * 
   * @return a collection of child DTOs
   */
  @SuppressWarnings("unchecked")
  private List<Dto> getChildDtos(Collection<Property> children) {
    // Create a Set to eliminate any duplicate DTOs from the collection.
    Collection<Dto> copiedDtos = new HashSet<Dto>();
    LinkedList<Dto> dtos = new LinkedList<Dto>();
    List<Dto> childDtos = new ArrayList<Dto>();
    // Iterate through the child properties of this class.
    for (Property p : children) {
      // Extract the child DTO from the property.
      Dto childDto = p.getDto();
      if (childDto != null) {
        // Get the children of the child DTO
        List<Dto> grandchildDtos = childDto.getChildDtos();
        if (grandchildDtos != null && grandchildDtos.size() > 0) {
          // Add the grandchildren in order, eliminating duplicates.
          for (Dto grandchild : grandchildDtos) {
            if (!copiedDtos.contains(grandchild)) {
              // Not there yet, add it to both lists.
              dtos.addLast(grandchild);
              copiedDtos.add(grandchild);
            }
          }
        }
        // Add the child DTO to the set of child DTOs for later addition.
        childDtos.add(childDto);
      }

      // Add the child DTOs to the list, eliminating duplicates.
      for (Dto child : childDtos) {
        if (!copiedDtos.contains(child)) {
          // Not there, add to both lists.
          dtos.addLast(child);
          copiedDtos.add(child);
        }
      }
    }
    return dtos;
  }

  /**
   * Get the superclass metafacade if this is a subclass.
   * 
   * @return the superclass facade or null if there is no superclass
   */
  private PersistentClassFacadeLogicImpl getSuperclassFacade() {
    PersistentClassFacadeLogicImpl facade = null;
    for (Object o : getGeneralizations()) {
      if (o instanceof PersistentClassFacadeLogicImpl) {
        facade = (PersistentClassFacadeLogicImpl)o;
        break; // single inheritance, take first class found
      }
    }
    return facade;
  }

  /**
   * Get the fully qualified name of the superclass of the current class, if
   * any.
   * 
   * @return the superclass name or null if there is no superclass
   */
  private String getSuperpackage() {
    String superpackage = null;
    for (Object o : getGeneralizations()) {
      GeneralizableElementFacade superclass = (GeneralizableElementFacade)o;
      superpackage = superclass.getPackageName();
      // Break out of loop after first general--single inheritance
      break;
    }
    return superpackage;
  }

  /**
   * Get the type of key of the class. The stereotypes on the class determine
   * the key type; the method takes the first stereotype of a key type and
   * returns that.
   * 
   * @return the key type based on stereotypes or NONE
   */
  private KeyType getKeyType() {
    KeyType type = null;
    for (Object o : getStereotypeNames()) {
      String stereotype = (String)o;
      type = KeyType.stringValue(stereotype);
      if (type != null) {
        break;
      }
    }

    if (type == null) {
      type = KeyType.NONE;
    }

    return type;
  }

  /**
   * Does the current class have a specified key type? This helper method is
   * package-scoped to enable the PersistentAssociationClassFacade to use it.
   * 
   * @param type the specified key type
   * @return true if the class has the key type applied
   */
  boolean hasKeyType(KeyType type) {
    boolean hasType = false;

    for (Object o : getStereotypeNames()) {
      String stereotype = (String)o;
      KeyType appliedType = KeyType.stringValue(stereotype);
      if (type.equals(appliedType)) {
        hasType = true;
        break;
      }
    }

    return hasType;
  }

  /**
   * Get the key attributes of the primary key of the parent class for a
   * composite aggregation.
   * 
   * @return true if the composite aggregation is ordered, false if not, null if
   *         there is no composite parent
   */
  @SuppressWarnings("unchecked")
  private Collection<Property> getParentClassKeyAttributes() {
    Dto dto = null;
    for (Object o : metaObject.getOwnedAttributes()) {
      org.eclipse.uml2.Property p = (org.eclipse.uml2.Property)o;
      /*
       * A property represents an association to a parent class if it is an
       * association, if it is to a Composite stereotyped class, and if the
       * opposite association end property is marked as a composite.
       */
      if (p != null
          && p.getAssociation() != null
          && p.getOpposite() != null
          && p.getOpposite().isComposite()) {
        Type t = p.getType();
        if (t instanceof AssociationClass) {
          PersistentAssociationClassFacadeLogicImpl classFacade =
            new PersistentAssociationClassFacadeLogicImpl((AssociationClass)t,
                                                          CONTEXT);
          dto = classFacade.transformToDto(null);
        } else if (t instanceof ClassImpl) {
          PersistentClassFacadeLogicImpl classFacade =
            new PersistentClassFacadeLogicImpl((ClassImpl)t, CONTEXT);
          dto = classFacade.transformToDto(null);
        }
        break;
      }
    }

    return dto != null ? dto.getKeyProperties() : new ArrayList<Property>(0);
  }

  /**
   * Gets the SQL mappings that have been set for this entity attribute.
   * 
   * @return the SQL Mappings instance.
   */
  public TypeMappings getSqlMappings() {
    final String propertyName = UMLMetafacadeProperties.SQL_MAPPINGS_URI;
    final Object property = getConfiguredProperty(propertyName);
    TypeMappings mappings = null;
    String uri;
    if (property instanceof String) {
      uri = (String)property;
      try {
        mappings = TypeMappings.getInstance(uri);
        setProperty(propertyName, mappings);
      } catch (Throwable throwable) {
        String errMsg =
          "Error getting '" + propertyName + "' --> '" + uri + "'";
        logger.error(errMsg, throwable);

        // Proceed without throwing the exception
      }
    } else {
      mappings = (TypeMappings)property;
    }
    return mappings;
  }

  /**
   * Does the class have at least one property that is an explicit key? An
   * explicit key property has the stereotype PK. This helper method is
   * package-scoped to enable the PersistentAssociationClassFacade to use it.
   * 
   * @return true if there is at least one property with the PK stereotype,
   *         false if there are none.
   */
  boolean isExplicitKey() {
    boolean key = false;
    for (Object o : metaObject.getOwnedAttributes()) {
      org.eclipse.uml2.Property p = (org.eclipse.uml2.Property)o;
      for (Object os : p.getAppliedStereotypes()) {
        Stereotype s = (Stereotype)os;
        if (s.getName().equalsIgnoreCase(PoesysDbProfile.STEREOTYPE_PK)) {
          key = true;
          break;
        }
      }
    }

    return key;
  }

  /**
   * Examine the composite association to this class and determine whether the
   * association end on the class is ordered. This indicates that the parent has
   * an ordered set of children. If there is no composite association, the
   * default is false. This helper method is package-scoped to enable the
   * PersistentAssociationClassFacade to use it.
   * 
   * @return true if the composite aggregation is ordered, false if not or if
   *         there is no navigable composite aggregation
   */
  boolean isOrderedComposite() {
    boolean ordered = false;
    for (Object o : metaObject.getOwnedAttributes()) {
      org.eclipse.uml2.Property p = (org.eclipse.uml2.Property)o;
      // We want only association properties.
      // TODO Replace with navigation-insensitive code
      if (p.getAssociation() != null
          && p.getOpposite() != null
          && p.getOpposite().isComposite()
          && p.getOpposite().isOrdered()) {
        ordered = true;
      }
      break;
    }

    return ordered;
  }

  /**
   * Examine the composite association to this class and determine whether the
   * association end on the class is ordered. This indicates that the parent has
   * an ordered set of children. If there is no composite association, the
   * default is false. This helper method is package-scoped to enable the
   * PersistentAssociationClassFacade to use it.
   * 
   * @return true if the composite aggregation is ordered, false if not or if
   *         there is no navigable composite aggregation
   */
  boolean isUnorderedComposite() {
    boolean unordered = false;
    for (Object o : metaObject.getOwnedAttributes()) {
      org.eclipse.uml2.Property p = (org.eclipse.uml2.Property)o;
      // We want only association properties.
      // TODO Replace with navigation-insensitive code
      if (p.getAssociation() != null
          && p.getOpposite() != null
          && p.getOpposite().isComposite()
          && !p.getOpposite().isOrdered()) {
        unordered = true;
        break;
      }
    }

    return unordered;
  }

  /**
   * Update the documentation and table name of a property to reflect its use in
   * a different situation. The documentation should reflect the different use
   * of the property. The caller can also set the table name to a different
   * table, allowing the column to represent an attribute pulled in from the
   * original table. This helper method is package-scoped to enable the
   * PersistentAssociationClassFacade to use it.
   * 
   * @param property the PSM property to update
   * @param comment the updated comment for the property
   * @param tag the updated parameter tag for the property
   * @param tableName the optional new table name for the SQL column
   */
  void updateProperty(Property property, String comment, String tag,
                      String tableName) {
    property.setDocumentation(comment);
    property.setParamTag(tag);

    // If a new table name is supplied, assign it.
    if (tableName != null) {
      property.setTableName(tableName);
    }
  }

  /**
   * Add the association attributes for the specified class to the list. This
   * shared code method gets properties that are to-one association ends that
   * are not part of a composite aggregation association and that have no PK
   * stereotype (a primary key would get attributes through the relevant primary
   * key strategy rather than through this one). The attribute copy sets the
   * property pk flag to false because it is not a key in the referring class.
   * 
   * @param umlClass the class for which to get association attributes
   * @param list the list to which to add the attributes
   * @param source the name of the inner class that called this shared code
   *          method
   * @throws RuntimeException when an error prevents copying a property
   */
  private void addAssociationAttributes(org.eclipse.uml2.Class umlClass,
                                        List<Property> list, String source) {
    for (Object o : umlClass.getOwnedAttributes()) {
      org.eclipse.uml2.Property property = (org.eclipse.uml2.Property)o;
      boolean pk = property.getAppliedStereotype(PK) != null;

      // If the association is a to-one, non-composite association, proceed.
      try {
        if (property.getAssociation() != null
            && property.upper() == 1
            && !isPropertyComposite(property)
            && !pk) {
          // If the association is to a persistent object with keys, proceed.
          if (getAssociatedClassKeyProperties(property).size() > 0) {
            int index = 0;
            String tableName = getTableName((Class)property.getOwner());

            for (Property key : getAssociatedClassKeyProperties(property)) {
              PropertyImpl keyCopy = null;
              try {
                // Copy the property.
                keyCopy = new PropertyImpl(key);
              } catch (InvalidParametersException e) {
                throw new RuntimeException("Cannot copy null property", e);
              }

              // Update the documentation and table name.
              updateProperty(keyCopy,
                             PersistentClassFacadeLogicImpl.TO_ONE_COMMENT,
                             PersistentClassFacadeLogicImpl.TO_ONE_TAG,
                             tableName);
              // Set the key flag off and the association end on.
              keyCopy.setKey(false);
              keyCopy.setAssociationEnd(true);
              // Set the required property based on the association
              // multiplicity.
              keyCopy.setRequired(property.lower() == 1);
              // Set the property debugging source to the calling class.
              keyCopy.setSource(keyCopy.getSource() + " + " + source);
              // Use the property name as the prefix for the attributes.
              keyCopy.setPrefix(property.getName());
              list.add(keyCopy);
              // Set the owner type to be the current DTO type.
              keyCopy.setOwnerType(metaObject);
              // Set the property type to be the property's UML type.
              keyCopy.setPropertyType(property.getType());
              // Set the explicit name on the property from the association,
              // but only if there is one; otherwise leave it alone.
              String explicitName =
                StringUtilities.getExplicitPropertyName(property, index);
              if (explicitName != null) {
                keyCopy.setExplicitName(explicitName);
              }
              // Increment the index into the tag list.
              index++;
            }
          } else {
            // Association to transient object with no keys, create new property

            // Get any Boolean string representation.
            boolean isBooleanString = false;
            String booleanTrue = null;
            String booleanFalse = null;
            if (property.getAppliedStereotype(BOOLEAN_STRING) != null) {
              isBooleanString = true;
              List<String> representation =
                StringUtilities.getBooleanRepresentation(property);
              booleanTrue = representation.get(0);
              booleanFalse = representation.get(1);
            }
            PropertyImpl transProperty =
              new PropertyImpl(property.getName(),
                               null,
                               null,
                               null,
                               null,
                               true,
                               false,
                               StringUtilities.getQualifiedJavaType(property.getType()),
                               getPropertyDocumentation(property,
                                                        " * ",
                                                        77,
                                                        true),
                               property.getVisibility().toString(),
                               false,
                               property.lower() == 1,
                               property.getDefault(),
                               getPropertyDocumentation(property, "", -1, false),
                               null,
                               null,
                               property.getNearestPackage().getQualifiedName(),
                               true,
                               source + " (transient)",
                               null,
                               false,
                               false,
                               property.getType().getName(),
                               false,
                               false,
                               null,
                               property.isOrdered(),
                               property.getName(),
                               0,
                               0,
                               null,
                               null,
                               null,
                               null,
                               null,
                               false,
                               null,
                               property.getName(),
                               isBooleanString,
                               booleanTrue,
                               booleanFalse,
                               null);

            // Set the owner type to be the current DTO type.
            transProperty.setOwnerType(metaObject);
            // Set the property type to be the property's UML type.
            transProperty.setPropertyType(property.getType());

            list.add(transProperty);
          }
        }
      } catch (InvalidParametersException e) {
        String errMsg = "Null property for " + getName();
        logger.error(errMsg, e);
        throw new RuntimeException(errMsg, e);
      }
    }
  }

  /**
   * Add the association objects for the specified class to the list.
   * Association objects are the classes of the association end (as opposed to
   * the attributes of that class that comprise the foreign key). This shared
   * code method gets properties that are to-one association ends.
   * 
   * @param umlClass the class for which to get association objects
   * @param list the list to which to add the attributes
   * @param source the name of the inner class that called this shared code
   *          method
   * @throws RuntimeException when an error prevents copying a property
   * @see #addAssociationAttributes(org.eclipse.uml2.Class, List, String)
   */
  private void addAssociationObjects(org.eclipse.uml2.Class umlClass,
                                     List<Property> list, String source) {

    for (Object o : umlClass.getOwnedAttributes()) {
      org.eclipse.uml2.Property p = (org.eclipse.uml2.Property)o;

      // Only interested in persistent, non-key properties
      boolean persistent = p.getAppliedStereotype(PERSISTENT) != null;

      if (persistent && p.getAssociation() != null && p.upper() == 1) {
        // persistent, non-key, to-one association, proceed
        PersistentPropertyFacadeLogicImpl facade =
          new PersistentPropertyFacadeLogicImpl(p, CONTEXT);
        Property property = facade.handleTransformToProperty();
        property.setSource("addAssociationObjects + " + source);

        // Remove any explicit name from the property, as these apply only
        // to attributes, not association objects.
        property.setExplicitName(null);

        // Set the owner type to be the specified type.
        ((PropertyImpl)property).setOwnerType(umlClass);

        list.add(property);
      }
    }
  }

  /**
   * Is a UML property an association end on a composite aggregation? This
   * method returns the status regardless of navigation on the association ends,
   * using the member ends of the association rather than the properties on the
   * classes. It does not specify whether the property represents the parent or
   * the child.
   * 
   * @param property the UML2 property
   * @return true if either end of the association is a composite aggregation
   * @throws InvalidParametersException when the input property is null
   */
  private boolean isPropertyComposite(org.eclipse.uml2.Property property)
      throws InvalidParametersException {
    boolean composite = false;

    if (property == null) {
      throw new InvalidParametersException("Null property not allowed when testing for composite aggregation");
    }

    Association a = property.getAssociation();
    if (a != null) {
      for (Object o : a.getMemberEnds()) {
        org.eclipse.uml2.Property end = (org.eclipse.uml2.Property)o;
        if (end.getAggregation().equals(AggregationKind.COMPOSITE_LITERAL)) {
          composite = true;
          break;
        }
      }
    }
    return composite;
  }

  /**
   * Is a UML property a composite child property? That is, is the property an
   * association end on the child end of a composite aggregation?
   * 
   * @param property the UML property
   * @return true if the property is a composite child, false if not
   * @throws InvalidParametersException when the property is null
   */
  private boolean isCompositeChild(org.eclipse.uml2.Property property)
      throws InvalidParametersException {
    boolean child = false;

    if (isPropertyComposite(property)
        && property.getAggregation().equals(AggregationKind.COMPOSITE_LITERAL)) {
      // Found composite aggregation on this property, it's a child.
      child = true;
    }

    return child;
  }

  /**
   * Is a UML property a composite parent property? That is, is the property an
   * association end on the parent end of a composite aggregation?
   * 
   * @param property the UML property
   * @return true if the property is a composite parent, false if not
   * @throws InvalidParametersException when the property is null
   */
  private boolean isCompositeParent(org.eclipse.uml2.Property property)
      throws InvalidParametersException {
    boolean parent = false;

    if (isPropertyComposite(property)
        && !property.getAggregation().equals(AggregationKind.COMPOSITE_LITERAL)) {
      // No composite aggregation on property, it's the parent.
      parent = true;
    }

    return parent;
  }

  /**
   * Get the UML documentation comment for a property.
   * 
   * @param property the UML property
   * @param indent the string to prepend to each line
   * @param lineLength the number of characters to write in a line
   * @param html whether to format with embedded HTML tags
   * @return the formatted comment
   */
  @SuppressWarnings("unchecked")
  private java.lang.String getPropertyDocumentation(org.eclipse.uml2.Property property,
                                                    java.lang.String indent,
                                                    int lineLength, boolean html) {
    final StringBuilder documentation = new StringBuilder();

    if (lineLength < 1) {
      lineLength = Integer.MAX_VALUE;
    }

    Collection<Comment> comments = property.getOwnedComments();
    if (comments != null && !comments.isEmpty()) {
      for (Comment comment : comments) {
        String commentString = StringUtils.trimToEmpty(comment.getBody());

        if (StringUtils.isEmpty(commentString)) {
          commentString = StringUtils.trimToEmpty(comment.toString());
        }
        documentation.append(StringUtils.trimToEmpty(commentString));
        documentation.append(SystemUtils.LINE_SEPARATOR);
      }
    }

    return StringUtilsHelper.format(StringUtils.trimToEmpty(documentation.toString()),
                                    indent,
                                    lineLength,
                                    html);
  }

  /**
   * Get the collection of key properties for the class or association class of
   * an association property. This is the set of database attributes that
   * represent the primary key in the persistent store. The type may be a
   * regular class or an association class. The collection returns the PSM
   * properties representing the database attributes. This helper method is
   * package-scoped to enable the PersistentAssociationClassFacade to use it.
   * 
   * @param p the UML property
   * @return the collection of PSM properties
   */
  @SuppressWarnings("unchecked")
  Collection<Property> getAssociatedClassKeyProperties(org.eclipse.uml2.Property p) {
    Type t = p.getType();
    Dto dto = createDtoFromType(t);

    return dto != null ? dto.getKeyProperties() : new ArrayList<Property>();
  }

  /**
   * <p>
   * Get the foreign keys for the DTO. These are of two types:
   * </p>
   * <ul>
   * <li>the key columns of a primary key inherited from a superclass</li>
   * <li>the key columns of single associated objects (to-one association links)
   * </li>
   * </ul>
   * <p>
   * The schema generator uses this list to build the ALTER TABLE statements
   * that add the foreign keys to the schema. The keys are sorted by name so
   * they always come out in the same order
   * </p>
   * <p>
   * Each associated key may contain any number of key properties, which
   * correspond to the multiple properties of the key of the associated object.
   * A foreign key constraint may thus map multiple columns to multiple columns
   * based on these multiple properties of the associated key.
   * </p>
   * <p>
   * Note that this list of keys does not include the associated keys for single
   * associated objects inherited from superclasses; the foreign key is set at
   * the superclass level in the database, not for all levels of the inheritance
   * hierarchy.
   * </p>
   * 
   * @return a list of foreign keys
   * @throws InvalidParametersException when a property copying operation fails
   * @throws RuntimeException when there is a problem sorting the keys
   */
  List<AssociatedKey> getForeignKeys() throws RuntimeException,
      InvalidParametersException {
    List<AssociatedKey> keys = new ArrayList<AssociatedKey>();

    addInheritedKeyForeignKeys(keys);
    addAssociationForeignKeys(keys);

    // Sort the keys by name so they always come out in the same order.
    Collections.sort(keys, new AssociatedKeyComparator());
    return keys;
  }

  /**
   * Add the set of foreign keys derived from the to-one associations and
   * composite aggregations of this persistent class to a list of associated
   * keys. Copy the key properties from the key into the associated key. The
   * to-one association properties are not keys in the current class while the
   * composite aggregation properties are. This method is package-scoped to
   * permit access from the Association Class Facade.
   * 
   * @param keys the list to which to add the foreign keys
   * @throws RuntimeException when the properties cannot be copied
   * @throws InvalidParametersException when a property copying operation fails
   */
  void addAssociationForeignKeys(List<AssociatedKey> keys)
      throws RuntimeException, InvalidParametersException {
    for (Object o : metaObject.getOwnedAttributes()) {
      Collection<PropertyImpl> copiedProperties = new ArrayList<PropertyImpl>();

      org.eclipse.uml2.Property p = (org.eclipse.uml2.Property)o;

      if (p.getAssociation() != null && p.upper() == 1) {
        Type t = p.getType();
        Dto dto = transformTypeToDto(t);

        String businessPackageName = null;
        String subsystemName = null;

        // If the DTO was created, add keys.
        if (dto != null) {
          // Get the package names.
          if (dto.getSubsystem() != null) {
            businessPackageName = dto.getSubsystem().getBusinessPackageName();
            subsystemName = dto.getSubsystem().getFullyQualifiedName();
          }
          // Make the foreign key persistent if the association end is
          // persistent and lazy or if it is persistent and between different
          // subsystems. Persistent in this case means "create a database
          // foreign key."
          boolean persistent = p.getAppliedStereotype(PERSISTENT) != null;
          boolean lazy = p.getAppliedStereotype(LAZY) != null;
          boolean key = p.getAppliedStereotype(PK) != null;
          boolean interSystem = isBetweenSubsystems(p.getAssociation());
          boolean isPersistentProperty =
            (persistent && !lazy) || (persistent && !interSystem);

          String parentName = null;
          String prefix = p.getName();
          if (isCompositeChild(p)) {
            logger.debug(p.getName()
                         + " is a composite child in "
                         + metaObject.getName());
            // Composite child; get parent name
            Dto parent = dto.getCompositeParent();
            if (parent != null) {
              parentName = parent.getName();
            } else {
              // Can't have composite without parent, probably 1-1 composite
              String errMsg =
                "Composite key class "
                    + dto.getName()
                    + " has no parent class; no one-to-one composites allowed";
              logger.error(errMsg);
              throw new RuntimeException(errMsg);
            }
            // Set the prefix to the variable-name version of the property name
            // for use in prefixing composite key names for uniqueness.
            prefix = StringUtilities.buildVariableName("", p.getName(), null);
          } else if (isCompositeParent(p)) {
            // Composite parent; set to key, as it must be primary key
            key = true;
          }

          // Get the optional list of SQL column names from the property.
          List<String> sqlNames = StringUtilities.getExplicitPropertyNames(p);
          int sqlNamesCounter = 0;

          /*
           * Loop through the properties, copying them and setting the key
           * attribute off if the foreign keys are not key in the current class.
           * Use the list of explicit SQL names to set the SQL column name if
           * there are any explicit SQL names.
           */
          for (Object pObject : dto.getKeyProperties()) {
            PropertyImpl copy = new PropertyImpl((Property)pObject);
            // Set the SQL column name to any optional SQL column name
            // supplied in the tagged value on the association end.
            if (sqlNames != null
                && sqlNames.size() > sqlNamesCounter
                && sqlNames.get(sqlNamesCounter).length() > 0) {
            }

            // Set key status to false if the property is not an explicit PK.
            if (!key) {
              copy.setKey(false);
            }

            // Set the source to include this method. Add property name for
            // debugging.
            copy.setSource(copy.getSource()
                           + " + "
                           + "addAssociationForeignKeys: "
                           + copy.getName());
            // Set the prefix to be the property name.
            copy.setPrefix(p.getName());

            // Set the type for creating the owning DTO.
            copy.setOwnerType(metaObject);
            // Set the type for creating the property DTO.
            copy.setPropertyType(p.getType());

            copiedProperties.add(copy);
            // Increment the SQL names list index.
            sqlNamesCounter++;
          }

          AssociatedKey associatedKey = null;
          associatedKey =
            new AssociatedKeyImpl(p.getName(),
                                  getKeyType(t).toString(),
                                  parentName,
                                  prefix,
                                  dto.getName(),
                                  dto.getPackageName(),
                                  dto.getSqlTableName(),
                                  p.lower() == 1, // is required?
                                  StringUtilities.getUniqueName(p),
                                  businessPackageName,
                                  subsystemName,
                                  false,
                                  isPersistentProperty,
                                  dto.isReadWrite(),
                                  dto,
                                  copiedProperties);
          // Create the DTOs for each key property.
          for (PropertyImpl keyProperty : copiedProperties) {
            keyProperty.createDto();
            keyProperty.setOwnerType(metaObject);
            keyProperty.createOwner();
            keyProperty.setInheritedFromType(metaObject);
            keyProperty.createInheritedFrom();
          }
          keys.add(associatedKey);
        }
      }
    }
  }

  /**
   * Get the subsystem of a UML2 Type. This is the nearest package that contains
   * the type and has the &laquo;Subsystem&raquo; stereotype.
   * 
   * @param t the UML2 Type
   * @return the fully qualified name of the subsystem of the type
   */
  private String getTypeSubsystem(Type t) {
    String subsystem = null;
    if (t != null) {
      org.eclipse.uml2.Package p = t.getPackage();
      while (subsystem == null && p != null) {
        if (p.getAppliedStereotype(SUBSYSTEM) != null) {
          subsystem = p.getQualifiedName();
        }
        p = p.getNearestPackage();
      }
    }
    // Convert the UML name to a Java name.
    return StringUtilities.getQualifiedJavaName(subsystem);
  }

  /**
   * Test whether an association is between subsystems, meaning that the two
   * classes linked are both <<Persistent>> and are contained within different
   * <<Subsystem>> packages.
   * 
   * @param a the association to test
   * @return true if the associated classes are in different subsystems, false
   *         otherwise
   */
  private boolean isBetweenSubsystems(Association a) {
    boolean isBetween = false;
    Type t1 = null;
    EList list = a.getMemberEnds();
    for (Object o : list) {
      org.eclipse.uml2.Property p = (org.eclipse.uml2.Property)o;
      if (t1 == null) {
        t1 = p.getType();
      } else {
        Type t2 = p.getType();
        isBetween = !(getTypeSubsystem(t1).equals(getTypeSubsystem(t2)));
      }
    }
    return isBetween;
  }

  /**
   * Add the foreign keys that represent inheritance from a superclass to a list
   * of foreign keys. These properties do not correspond to associations but to
   * generalizations. Copy the key properties from the key into the associated
   * key. These properties are themselves primary key properties. Also, the
   * names will always be the same all the way down the hierarchy, so there is
   * no need to look for duplicates. Finally, Poesys/DB does not handle
   * generalizations across persistent stores, so the generalization always
   * represents a persistent associated key (that is, a foreign key in the
   * database).
   * 
   * @param keys the list of keys to which to add the new foreign keys
   * @throws RuntimeException when the properties cannot be copied
   * @throws InvalidParametersException when there is a problem copying key
   *           properties
   */
  @SuppressWarnings("unchecked")
  void addInheritedKeyForeignKeys(List<AssociatedKey> keys)
      throws RuntimeException, InvalidParametersException {
    // Get the foreign key(s) to the superclass(es).
    for (Object o : metaObject.getGeneralizations()) {
      org.eclipse.uml2.Generalization g = (org.eclipse.uml2.Generalization)o;
      PersistentAssociationClassFacadeLogicImpl associationFacade = null;
      PersistentClassFacadeLogicImpl classFacade = null;
      Classifier c = g.getGeneral();

      // Transform the Class to a DTO.
      Dto dto = null;
      if (c instanceof AssociationClassImpl
          && c.getAppliedStereotype(PERSISTENT) != null) {
        associationFacade =
          new PersistentAssociationClassFacadeLogicImpl((AssociationClass)c,
                                                        CONTEXT);
        dto = associationFacade.transformToDto(null);
      } else if (c instanceof ClassImpl
                 && c.getAppliedStereotype(PERSISTENT) != null) {
        classFacade =
          new PersistentClassFacadeLogicImpl((org.eclipse.uml2.Class)c, CONTEXT);
        dto = classFacade.transformToDto(null);
      }

      Collection<Property> inputProperties = null;
      // Persistent superclass, check for cross database.
      boolean persistent = g.getAppliedStereotype(CROSS_DATABASE) == null;
      String parentName = null;
      String prefix = "";
      String businessPackageName = null;
      String subsystemName = null;

      if (dto != null && dto.getSubsystem() != null) {
        businessPackageName = dto.getSubsystem().getBusinessPackageName();
        subsystemName = dto.getSubsystem().getFullyQualifiedName();
      }

      // Get the foreign keys, which may be the composite or inherited key
      // or an association-end key.
      if (dto.getKeyType().equals(KeyType.COMPOSITE.toString())) {
        // Composite; get parent name and sub-keys rather than full keys.
        parentName = dto.getCompositeParent().getName();
        inputProperties = dto.getSubKeyProperties();
        // Make the prefix the parent class name with PK appended
        prefix = parentName + "PkFk";
      } else {
        // For non-Composite, get keys rather than sub-keys.
        inputProperties = dto.getKeyProperties();
      }

      // Copy the properties so as not to overwrite anything.
      Collection<Property> copies =
        new ArrayList<Property>(inputProperties.size());

      for (Property property : inputProperties) {
        Property copy = new PropertyImpl(property);
        copies.add(copy);
      }

      Collection<Property> keyProperties = new ArrayList<Property>();
      copyKeyProperties(keyProperties, inputProperties);

      AssociatedKey key =
        new AssociatedKeyImpl(getName() + "PkFk",
                              dto.getKeyType(),
                              parentName,
                              prefix,
                              dto.getName(),
                              dto.getPackageName(),
                              dto.getSqlTableName(),
                              true,
                              getName() + "PkFk",
                              businessPackageName,
                              subsystemName,
                              true,
                              persistent,
                              dto.isReadWrite(),
                              dto,
                              keyProperties);

      // Create the DTOs for each key property and reset the source string.
      for (Property keyProperty : keyProperties) {
        PropertyImpl keyPropertyImpl = (PropertyImpl)keyProperty;
        keyProperty.setSource(keyProperty.getSource()
                              + "addInheritedKeyForeignKeys");
        keyPropertyImpl.setInheritedFromType(keyPropertyImpl.getOwnerType());
        keyPropertyImpl.setInheritedFrom(keyPropertyImpl.getDto());
        keyPropertyImpl.setOwnerType(metaObject);
        keyPropertyImpl.createDto();
        keyPropertyImpl.createOwner();
      }

      // Add the DTO to the properties.
      keys.add(key);
    }
  }

  /**
   * Copy the input key properties into a collection of key properties.
   * 
   * @param keyProperties the collection to which to add the properties
   * @param inputProperties the input properties to copy
   * @throws RuntimeException when the property can't be copied for some reason
   */
  private void copyKeyProperties(Collection<Property> keyProperties,
                                 Collection<Property> inputProperties)
      throws RuntimeException {
    for (Object op : inputProperties) {
      // Copy the property to create a new property.
      Property keyProperty;
      try {
        keyProperty = new PropertyImpl((Property)op);
      } catch (InvalidParametersException e) {
        // Log and fail with a runtime exception.
        logger.error("Cannot copy null property", e);
        throw new RuntimeException(e.getMessage(), e);
      }

      keyProperties.add(keyProperty);
    }
  }

  /**
   * Transform a Type to a PSM DTO object. The Type can be either an
   * AssociationClass or a Class at present.
   * 
   * @param t the type to transform (AssociationClass or Class)
   * @return the PSM DTO
   */
  Dto transformTypeToDto(Type t) {
    Dto dto = null;
    if (t instanceof AssociationClass) {
      PersistentAssociationClassFacade classFacade =
        new PersistentAssociationClassFacadeLogicImpl((AssociationClass)t,
                                                      CONTEXT);
      dto = classFacade.transformToDto(null);
    } else if (t instanceof ClassImpl) {
      PersistentClassFacade classFacade =
        new PersistentClassFacadeLogicImpl((ClassImpl)t, CONTEXT);
      dto = classFacade.transformToDto(null);
    }
    return dto;
  }

  /**
   * Get the type of key of a type (class or association class). The stereotypes
   * on the type determine the key type; the method takes the first stereotype
   * of a key type and returns that.
   * 
   * @param t the type (class or association class) for which to determine the
   *          key type
   * @return the key type based on stereotypes or NONE
   */
  KeyType getKeyType(Type t) {
    KeyType type = null;
    for (Object o : t.getAppliedStereotypes()) {
      Stereotype stereotype = (Stereotype)o;
      type = KeyType.stringValue(stereotype.getName());
      if (type != null) {
        break;
      }
    }

    if (type == null) {
      type = KeyType.NONE;
    }

    return type;
  }

  /**
   * <p>
   * Add the association class properties to a property list. The association
   * class properties are the properties that represent the collection of
   * association objects, as opposed to the properties for the associated
   * objects themselves.
   * </p>
   * 
   * @param list the list of properties to which to add the property
   * @param p the property to add, if it qualifies (association class)
   * @param source the name of the class calling this method
   * @return the property
   */
  private Property createClassCollectionProperty(List<Property> list,
                                                 org.eclipse.uml2.Property p,
                                                 String source) {
    Property property = null;
    org.eclipse.uml2.Association association = p.getAssociation();

    // Create a property only if this association is an association class.
    if (association != null && association instanceof AssociationClass) {
      Boolean immutable = getIsImmutable(association);
      // If the Unremovable stereotype is not there, association is removable.
      Boolean removable =
        association.getApplicableStereotype(UNREMOVABLE) == null;

      // Get the "name" and "lazy" tagged values on the association.
      String explicitName =
        StringUtilities.getExplicitAssociationName(association);
      Boolean lazyTaggedValue = getLazyTaggedValue(association);

      String tableName = getTableName(metaObject);

      // Get the class name properly modified from the UML type.
      String className =
        StringUtilities.getQualifiedExtendedTypeName(p.getAssociation(),
                                                     getConfiguredProperty(UMLMetafacadeProperties.NAMESPACE_SEPARATOR),
                                                     getLanguageMappings(),
                                                     getConfiguredProperty(INTERFACE));
      // Get the complete collection type.
      String type = getCollectionType(association);
      // Get the visibility from UML.
      String visibility = association.getVisibility().getName();
      // Get the subsystem names.
      Subsystem subsystem =
        getSubsystem(findAssociationSubsystemPackage(association));
      String subsystemName = subsystem.getName();
      String subsystemBusinessName = subsystem.getBusinessPackageName();

      // Put the association class name into a variable.
      String name =
        StringUtilities.buildVariableName(association.getName(), null, null);

      // Create a property which is always to-many, a collection.
      // The association class property is a child property.
      // The property is not ordered (a collection, not a list).
      property =
        new PropertyImpl(name,
                         null,
                         null,
                         null,
                         null,
                         true,
                         false,
                         type,
                         ASSOCIATION_CLASS_COMMENT,
                         visibility,
                         true,
                         true,
                         "null", // default collection to null value
                         ASSOCIATION_CLASS_TAG,
                         null,
                         null,
                         subsystemName,
                         true,
                         source,
                         tableName,
                         lazyTaggedValue,
                         true,
                         className,
                         immutable,
                         removable,
                         KeyType.ASSOCIATION.toString(),
                         false,
                         name,
                         10,
                         100,
                         StringUtilities.getBsTypeName(association,
                                                       subsystemBusinessName),
                         DatabaseUtilities.getAnsiSqlType(BIG_INTEGER),
                         DatabaseUtilities.getMySql51SqlType(BIG_INTEGER),
                         DatabaseUtilities.getOracle11SqlType(BIG_INTEGER),
                         DatabaseUtilities.getSybaseAse125SqlType(BIG_INTEGER),
                         false,
                         explicitName,
                         p.getName(),
                         false,
                         null,
                         null,
                         null);

      // Set the owner type to the type of the current class.
      ((PropertyImpl)property).setOwnerType(metaObject);
      // Set the property type to the type of the association class.
      ((PropertyImpl)property).setPropertyType(association);

      list.add(property);
    }
    return property;
  }

  /**
   * Determine whether an association represents an immutable property. The
   * method checks whether there is an &laquo;Immutable&raquo; stereotype on the
   * association.
   * 
   * @param association the association class to check
   * @return true if the association is immutable, false if mutable
   */
  private Boolean getIsImmutable(org.eclipse.uml2.Association association) {
    // Get immutable stereotype, default to false.
    Boolean immutable = false;
    Stereotype immutableStereotype =
      association.getAppliedStereotype(IMMUTABLE);
    if (immutableStereotype != null) {
      immutable = true;
    }
    return immutable;
  }

  /**
   * Is one of the association ends of an input association to this class?
   * 
   * @param association the association to check
   * @return true if one of the association ends is to the current class, false
   *         if both ends are to other classes.
   */
  private boolean toThisClass(Association association) {
    boolean toThisClass = false;
    for (Object o : association.getEndTypes()) {
      org.eclipse.uml2.Class endClass = (org.eclipse.uml2.Class)o;
      if (endClass.equals(metaObject)) {
        toThisClass = true;
        break;
      }
    }
    return toThisClass;
  }

  /**
   * Is a UML2 association a recursive class?
   * 
   * @param association the association to check
   * @return true if two of the association ends are to the same class.
   */
  private boolean isRecursiveAssociation(Association association) {
    EList list = association.getEndTypes();
    // If only 1 class in list, it's a recursive association!
    return list.size() == 1;
  }

  /**
   * Is a DTO a recursive association class?
   * 
   * @param dto the association class DTO
   * @return
   */
  private boolean isRecursiveAssociationClass(Dto dto) {
    boolean recursive = false;
    Map<String, Integer> counts = new HashMap<String, Integer>();
    for (Object o : dto.getAssociatedDtos()) {
      Dto associatedDto = (Dto)o;
      String name =
        associatedDto.getPackageName() + "." + associatedDto.getName();
      if (counts.get(name) == null) {
        counts.put(name, new Integer(1));
      } else {
        // Already there, more than one of the same class, recursive
        recursive = true;
        break;
      }
    }
    return recursive;
  }

  /**
   * Get the "lazy" tagged value from the AssociationKey stereotype, if it
   * exists.
   * 
   * @param association the UML association object
   * @return the tagged value or null if it doesn't exist
   */
  private Boolean getLazyTaggedValue(org.eclipse.uml2.Association association) {
    Boolean lazyTaggedValue = Boolean.FALSE;
    Stereotype s = association.getAppliedStereotype(ASSOCIATION);
    // Inherited key may not be AssociationKey
    if (s != null) {
      // Is AssociationKey, get the two tagged values from it.
      lazyTaggedValue =
        (Boolean)association.getValue(s, PoesysDbProfile.TAGGEDVALUE_LAZY);
      // If the lazy tag has no value, default to false.
      if (lazyTaggedValue == null) {
        lazyTaggedValue = Boolean.FALSE;
      }
    }
    return lazyTaggedValue;
  }

  /**
   * Get the Collection type, taking into account whether templating is turned
   * on and embedding the proper class or interface.
   * 
   * @param association the UML association type
   * @return the Collection type
   */
  private String getCollectionType(org.eclipse.uml2.Association association) {
    String type = null;

    // Get the type and dto through the metafacade.
    PersistentAssociationClassFacade acFacade =
      new PersistentAssociationClassFacadeLogicImpl((AssociationClass)association,
                                                    CONTEXT);
    TypeMappings mappings = getLanguageMappings();

    // The association class property is always a collection.
    type = mappings.getTo(UMLProfile.COLLECTION_TYPE_NAME);

    /*
     * Add the type as a parameter to the collection type if required. If the
     * useInterfaceForAssociation property is set to true, prefix the type name
     * with I to use the interface for the type as the template parameter type.
     */
    String typeName =
      StringUtilities.useInterfacePrefix(association,
                                         acFacade.getFullyQualifiedName(),
                                         getConfiguredProperty(INTERFACE));
    Object templating =
      getConfiguredProperty(UMLMetafacadeProperties.ENABLE_TEMPLATING);
    Boolean enableTemplating =
      BooleanUtils.toBoolean(ObjectUtils.toString(templating));
    if (enableTemplating) {
      type = type + "<" + typeName + ">";
    }
    return type;
  }

  /**
   * Add an association collection property to a list. The association
   * collection properties are the non-composite, to-many (upper bound -1)
   * association-end collection or list objects. The special case where the
   * collection is also part of the primary key of the associated class (the
   * association end on the opposite side has a PK stereotype) is separate and
   * hence excluded from this adder.
   * 
   * @see PersistentClassFacadeLogicImpl.AddToManyAssociatedKeyCollectionProperties
   * 
   * @param list the list of properties to which to add the property
   * @param p the property to add (if it qualifies)
   * @param source the string describing the metafacade origins of the property
   */
  private void addToManyAssociationCollectionProperty(List<Property> list,
                                                      org.eclipse.uml2.Property p,
                                                      String source) {
    // We want non-composite, to-many association properties. Upper bound -1
    // is *, unlimited. PK on opposite side is excluded.
    boolean isKey =
      p.getAssociation() != null
          && p.getOpposite() != null
          && p.getOpposite().getAppliedStereotype(PK) != null;
    if (p.getAssociation() != null
        && p.getUpper() != 1
        && !p.isComposite()
        && !isKey) {
      PersistentPropertyFacadeLogicImpl facade =
        new PersistentPropertyFacadeLogicImpl(p, CONTEXT);
      Property property = facade.handleTransformToProperty();
      property.setSource(property.getSource() + " + " + source);
      // Set the type to be the current associated DTO type.
      ((PropertyImpl)property).setPropertyType(p.getType());
      list.add(property);
    }
  }

  /**
   * Eliminate duplicate entries in the property list to simulate overriding
   * behavior in an inheritance hierarchy. Check both the name and prefix,
   * taking into account properties representing recursive associations.
   * 
   * @param list the list of properties
   */
  private void overrideDuplicateInheritedProperties(List<Property> list) {
    // Eliminate duplicates to simulate overriding behavior
    Map<String, Property> names = new HashMap<String, Property>(list.size());
    ListIterator<Property> itr = list.listIterator();
    // Position iterator at bottom of list.
    Property p = null;
    String name = null;
    while (itr.hasNext()) {
      p = itr.next();
    }
    // Navigate up the list, checking for previously saved names.
    while (itr.hasPrevious()) {
      p = itr.previous(); // first one is last in list
      // use prefixed name for uniqueness
      name = p.getPrefix() + p.getName();
      // Check the names in the map
      if (names.get(name) == null) {
        // Not there, put the name and property in the map.
        names.put(name, p);
      } else {

        // Already there, remove the current property.
        itr.remove();
      }
    }
  }

  /**
   * Get the root superclass for the inheritance hierarchy, if there are
   * generalizations for the current metaobject. If there is no superclass,
   * return null.
   * 
   * @return a root superclass DTO or null if there is no superclass
   */
  private Dto getRootSuperclass() {
    Dto dto = null;
    // Get the root superclass facade.
    PersistentClassFacadeLogicImpl superclassFacade = null;
    for (Object o : getGeneralizations()) {
      if (o instanceof PersistentClassFacadeLogicImpl) {
        superclassFacade = (PersistentClassFacadeLogicImpl)o;
      } else {
        break; // no more persistent superclasses
      }
    }
    // If there is a root persistent superclass, inherit its keys and
    // sequence name.
    if (superclassFacade != null) {
      dto = superclassFacade.transformToDto(null);
    }

    return dto;
  }
}