//
// Attention: generated code (by Metafacade.vsl) - do not modify!
//
package com.poesys.cartridges.db.metafacades;

/**
 * 
 *
 * Metafacade interface to be used by AndroMDA cartridges.
 */
public interface ParameterizedQuerySqlFacade
    extends org.andromda.metafacades.uml.AssociationEndFacade
{

    /**
     * Indicates the metafacade type (used for metafacade mappings).
     *
     * @return always <code>true</code>
     */
    public boolean isParameterizedQuerySqlFacadeMetaType();

   /**
    * <p>
    * Transform the association end into a PSM ParameterizedQuery
    * object.
    * </p>
    */
    public com.poesys.cartridges.db.psm.db.ParameterizedQuery transformToQuery();

}