package com.poesys.cartridges.db.metafacades;


import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.apache.velocity.util.StringUtils;
import org.eclipse.uml2.Stereotype;

import com.poesys.cartridges.db.profile.PoesysDbProfile;
import com.poesys.cartridges.db.psm.db.Dto;
import com.poesys.cartridges.db.psm.db.ParameterizedQuery;
import com.poesys.cartridges.db.psm.db.Subsystem;
import com.poesys.cartridges.db.utilities.StereotypeUtilities;


/**
 * MetafacadeLogic implementation for
 * com.poesys.cartridges.db.metafacades.SubsystemFacade.
 * 
 * @see com.poesys.cartridges.db.metafacades.SubsystemFacade
 */
public class SubsystemFacadeLogicImpl extends SubsystemFacadeLogic {
  /** Logger for this class */
  Logger logger = Logger.getLogger(SubsystemFacadeLogicImpl.class);
  /** Error reporting context for AndroMDA metafacades system */

  /** The profile stereotype name for the Subsystem stereotype */
  private static final String SUBSYSTEM =
    PoesysDbProfile.NAMESPACE_PERSISTENCE
        + PoesysDbProfile.STEREOTYPE_SUBSYSTEM;

  /**
   * Cache for subsystems. The cache optimizes Subsystem creation so that a
   * Subsystem gets created precisely once in each run. It also enables the use
   * of two-way navigable associations that otherwise would create infinite
   * loops of nested DTOs and subsystems.
   */
  private static final ConcurrentHashMap<String, Subsystem> cache =
    new ConcurrentHashMap<String, Subsystem>();

  /**
   * Create a SubsystemFacadeLogicImpl object.
   * 
   * @param metaObject the UML Package object wrapped by the metafacade
   * @param context the context of the metafacade object
   */
  public SubsystemFacadeLogicImpl(org.eclipse.uml2.Package metaObject,
                                  String context) {
    super(metaObject, context);
  }

  @Override
  protected Subsystem handleTransformToSubsystem() {
    String fullName = getFullyQualifiedName();
    Subsystem subsystem = cache.get(fullName);
    if (subsystem == null) {
      logger.debug("Creating new subsystem " + fullName);
      String name = metaObject.getName();
      String className = StringUtils.capitalizeFirstLetter(name);
      String documentation = this.getDocumentation(" * ", 80, true);
      Collection<Dto> dtos = new ArrayList<Dto>();
      Collection<ParameterizedQuery> queries =
        new ArrayList<ParameterizedQuery>();
      Stereotype s = metaObject.getAppliedStereotype(SUBSYSTEM);
      String businessPackage =
        (String)metaObject.getValue(s, PoesysDbProfile.TAGGEDVALUE_BS_PACKAGE);
      if (businessPackage == null || businessPackage.length() == 0) {
        businessPackage = fullName;
      }
      
      String memoryType =
        StereotypeUtilities.getSubsystemMemoryManagerType(metaObject);
      String software =
        StereotypeUtilities.getSubsystemDistributedCachingSoftware(metaObject);

      subsystem =
        new Subsystem(name,
                      fullName,
                      documentation,
                      className,
                      businessPackage,
                      false,
                      memoryType,
                      software,
                      dtos,
                      queries);
      // Cache the newly created subsystem.
      cache.put(getFullyQualifiedName(), subsystem);
      logger.debug("Cached new subsystem " + fullName);
    }
    return subsystem;
  }
}