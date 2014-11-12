//
// Attention: generated code (by Metafacade.vsl) - do not modify!
//
package com.poesys.cartridges.db.metafacades;

/**
 * 
 *
 * Metafacade interface to be used by AndroMDA cartridges.
 */
public interface SubsystemFacade
    extends org.andromda.metafacades.uml.PackageFacade
{

    /**
     * Indicates the metafacade type (used for metafacade mappings).
     *
     * @return always <code>true</code>
     */
    public boolean isSubsystemFacadeMetaType();

   /**
    * 
    */
    public com.poesys.cartridges.db.psm.db.Subsystem transformToSubsystem();

}