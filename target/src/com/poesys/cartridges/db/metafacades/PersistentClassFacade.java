//
// Attention: generated code (by Metafacade.vsl) - do not modify!
//
package com.poesys.cartridges.db.metafacades;

/**
 * 
 *
 * Metafacade interface to be used by AndroMDA cartridges.
 */
public interface PersistentClassFacade
    extends org.andromda.metafacades.uml.ClassifierFacade
{

    /**
     * Indicates the metafacade type (used for metafacade mappings).
     *
     * @return always <code>true</code>
     */
    public boolean isPersistentClassFacadeMetaType();

   /**
    * <p>
    * Is this object a child class (target of a composite aggregation
    * or persistent association with PK stereotype, part of the
    * primary key of the persistent class with the persistent key the
    * target of a persistent foreign key constraint)?
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
    * updatable? This property differs from the Immutable property in
    * that you can delete it.
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
    * <p>
    * Transforms the metafacade into a DTO PSM object
    * </p>
    */
    public com.poesys.cartridges.db.psm.db.Dto transformToDto(com.poesys.cartridges.db.psm.db.Subsystem subsystem);

}