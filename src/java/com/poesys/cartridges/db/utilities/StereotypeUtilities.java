/*
 * Copyright (c) 2011 Poesys Associates. All rights reserved.
 * 
 * This file is part of Poesys-DB.
 * 
 * Poesys-DB is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * Poesys-DB is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * Poesys-DB. If not, see <http://www.gnu.org/licenses/>.
 */

package com.poesys.cartridges.db.utilities;


import org.eclipse.uml2.Stereotype;
import org.eclipse.uml2.Package;

import com.poesys.cartridges.db.profile.PoesysDbProfile;


/**
 * Shared methods for getting stereotypes and tagged values
 * 
 * @author Robert J. Muller
 */
public class StereotypeUtilities {
  // String constants
  /** Constant value for direct memory model */
  public static final String DIRECT_VALUE = "direct";
  /** Constant value for Java-map caching memory model */
  public static final String CACHED_VALUE = "cached";
  /** Constant value for clustered caching memory model */
  public static final String CLUSTERED_VALUE = "clustered";
  /** Constant value for distributed caching memory model */
  public static final String DISTRIBUTED_VALUE = "distributed";
  /** Constant value for memcached software implementation */
  public static final String MEMCACHED_VALUE = "memcached";

  /** Persistent stereotype name */
  private static final String PERSISTENT =
    PoesysDbProfile.NAMESPACE_PERSISTENCE
        + PoesysDbProfile.STEREOTYPE_PERSISTENT;

  /** Direct stereotype name */
  private static final String DIRECT =
    PoesysDbProfile.NAMESPACE_MEMORY + PoesysDbProfile.STEREOTYPE_DIRECT;

  /** Cached stereotype name */
  private static final String CACHED =
    PoesysDbProfile.NAMESPACE_MEMORY + PoesysDbProfile.STEREOTYPE_CACHED;

  /** Clustered stereotype name */
  private static final String CLUSTERED =
    PoesysDbProfile.NAMESPACE_MEMORY + PoesysDbProfile.STEREOTYPE_CLUSTERED;

  /** Distributed stereotype name */
  private static final String DISTRIBUTED =
    PoesysDbProfile.NAMESPACE_MEMORY + PoesysDbProfile.STEREOTYPE_DISTRIBUTED;

  /**
   * Get the type of memory management for the subsystem:
   * <ul>
   * <li>direct--no caching</li>
   * <li>cached--map-based caching</li>
   * <li>clustered--map-based caching with messaging</li>
   * <li>distributed--distributed caching (memcached)</li>
   * </ul>
   * 
   * @param subsystem the UML2 package object representing the subsystem
   * @return the memory management type
   */
  public static String getSubsystemMemoryManagerType(Package subsystem) {
    String manager = "cached"; // default to cached memory management

    if (subsystem.getAppliedStereotype(DIRECT) != null) {
      manager = DIRECT_VALUE;
    } else if (subsystem.getAppliedStereotype(CACHED) != null) {
      manager = CACHED_VALUE;
    } else if (subsystem.getAppliedStereotype(CLUSTERED) != null) {
      manager = CLUSTERED_VALUE;
    } else if (subsystem.getAppliedStereotype(DISTRIBUTED) != null) {
      manager = DISTRIBUTED_VALUE;
    }

    return manager;
  }

  /**
   * Get the content of the tagged value TAGGEDVALUE_SOFTWARE from
   * the DISTRIBUTED stereotype. If there is no DISTRIBUTED stereotype, the
   * tagged value is null. If there is a stereotype but the tagged value is
   * null, return "memcached", the default implementation.
   * 
   * @param subsystem the UML2 package object representing the subsystem
   * @return the tagged value
   */
  public static String getSubsystemDistributedCachingSoftware(Package subsystem) {
    String software = null;

    Stereotype stereotype = subsystem.getAppliedStereotype(DISTRIBUTED);
    if (stereotype != null) {
      // Find the tagged value.
      software =
        (String)subsystem.getValue(stereotype,
                                   PoesysDbProfile.TAGGEDVALUE_SOFTWARE);
      if (software == null || software.length() == 0) {
        software = MEMCACHED_VALUE;
      }
    }

    return software;
  }

  /**
   * Get the content of the tagged value TAGGEDVALUE_EXPIRATION from the
   * PERSISTENT stereotype on a class DTO object. If there is no PERSISTENT
   * stereotype, the tagged value is null. If there is a stereotype but the
   * tagged value is null or 0 (this translation happens automatically), return
   * Integer.MAX_VALUE, the default implementation. If the tagged value is
   * -1, return 0 (immediate expiration, no caching).
   * 
   * @param dto the UML2 class object representing the DTO
   * @return the tagged value
   */
  public static Integer getExpiration(org.eclipse.uml2.Class dto) {
    Integer expiration = Integer.MAX_VALUE;

    Stereotype stereotype = dto.getAppliedStereotype(PERSISTENT);
    if (stereotype != null) {
      // Find the tagged value.
      expiration =
        (Integer)dto.getValue(stereotype,
                              PoesysDbProfile.TAGGEDVALUE_EXPIRATION);
      if (expiration.intValue() == 0) {
        expiration = Integer.MAX_VALUE;
      } else if (expiration.intValue() == -1) {
        expiration = new Integer(0);
      }
    }

    return expiration;
  }
}
