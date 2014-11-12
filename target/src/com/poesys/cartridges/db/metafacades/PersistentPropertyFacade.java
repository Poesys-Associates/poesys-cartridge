//
// Attention: generated code (by Metafacade.vsl) - do not modify!
//
package com.poesys.cartridges.db.metafacades;

/**
 * 
 *
 * Metafacade interface to be used by AndroMDA cartridges.
 */
public interface PersistentPropertyFacade
    extends org.andromda.metafacades.uml.ModelElementFacade
{

    /**
     * Indicates the metafacade type (used for metafacade mappings).
     *
     * @return always <code>true</code>
     */
    public boolean isPersistentPropertyFacadeMetaType();

   /**
    * <p>
    * Get the getter accessor name for the property.
    * </p>
    */
    public java.lang.String getGetterName();

   /**
    * <p>
    * Get the return or input type for the getter and setter accessors
    * for the property.
    * </p>
    */
    public java.lang.String getGetterSetterTypeName();

   /**
    * <p>
    * Get the fully qualified type name of the property as a PSM data
    * type. This name can either be a language primitive (for data
    * members) or a fully qualified object type, possibly transformed
    * to an interface (IType instead of Type, where the data type is
    * Type). The useInterfaceForAssociation property controls the use
    * of an interface.
    * </p>
    */
    public java.lang.String getQualifiedTypeName(org.eclipse.uml2.Type type);

   /**
    * <p>
    * Get the name of the setter accessor for the property.
    * </p>
    */
    public java.lang.String getSetterName();

   /**
    * <p>
    * Get the type classifier for the property.
    * </p>
    */
    public org.eclipse.uml2.Type getType();

   /**
    * 
    */
    public boolean isToMany();

   /**
    * 
    */
    public com.poesys.cartridges.db.psm.db.Property transformToProperty();

}