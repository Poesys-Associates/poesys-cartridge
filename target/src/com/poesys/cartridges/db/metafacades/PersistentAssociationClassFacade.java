//
// Attention: generated code (by Metafacade.vsl) - do not modify!
//
package com.poesys.cartridges.db.metafacades;

/**
 * 
 *
 * Metafacade interface to be used by AndroMDA cartridges.
 */
public interface PersistentAssociationClassFacade
    extends org.andromda.metafacades.uml.AssociationClassFacade
{

    /**
     * Indicates the metafacade type (used for metafacade mappings).
     *
     * @return always <code>true</code>
     */
    public boolean isPersistentAssociationClassFacadeMetaType();

   /**
    * <p>
    * Is this object a child class (target of a composite
    * aggregation)?
    * </p>
    */
    public boolean isChild();

   /**
    * <p>
    * Can the application change the state of objects in the
    * persistent store?
    * </p>
    */
    public boolean isImmutable();

   /**
    * <p>
    * Is the Class a read-only object? That is, is the object not
    * updatable?
    * </p>
    */
    public boolean isReadOnly();

   /**
    * <p>
    * Are objects of the class removable from the persistent store?
    * Can the application delete objects?
    * </p>
    */
    public boolean isUnremovable();

   /**
    * 
    */
    public com.poesys.cartridges.db.psm.db.Dto transformToDto(com.poesys.cartridges.db.psm.db.Subsystem subsystem);

}