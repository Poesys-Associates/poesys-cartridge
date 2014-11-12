//
// Attention: generated code (by MetafacadeLogic.vsl) - do not modify!
//
package com.poesys.cartridges.db.metafacades;

/**
 * MetafacadeLogic for com.poesys.cartridges.db.metafacades.PersistentPropertyFacade
 *
 * @see com.poesys.cartridges.db.metafacades.PersistentPropertyFacade
 */
public abstract class PersistentPropertyFacadeLogic
    extends org.andromda.core.metafacade.MetafacadeBase
    implements com.poesys.cartridges.db.metafacades.PersistentPropertyFacade
{

    protected org.eclipse.uml2.Property metaObject;

    public PersistentPropertyFacadeLogic(org.eclipse.uml2.Property metaObject, String context)
    {
        super(metaObject, getContext(context));
        this.superModelElementFacade =
           (org.andromda.metafacades.uml.ModelElementFacade)
            org.andromda.core.metafacade.MetafacadeFactory.getInstance().createFacadeImpl(
                    "org.andromda.metafacades.uml.ModelElementFacade",
                    metaObject,
                    getContext(context));
        this.metaObject = metaObject;
    }

    /**
     * Gets the context for this metafacade logic instance.
     */
    private static String getContext(String context)
    {
        if (context == null)
        {
            context = "com.poesys.cartridges.db.metafacades.PersistentPropertyFacade";
        }
        return context;
    }

    private org.andromda.metafacades.uml.ModelElementFacade superModelElementFacade;
    private boolean superModelElementFacadeInitialized = false;

    /**
     * Gets the org.andromda.metafacades.uml.ModelElementFacade parent instance.
     */
    private org.andromda.metafacades.uml.ModelElementFacade getSuperModelElementFacade()
    {
        if (!this.superModelElementFacadeInitialized)
        {
            ((org.andromda.core.metafacade.MetafacadeBase)superModelElementFacade).setMetafacadeContext(this.getMetafacadeContext());
            this.superModelElementFacadeInitialized = true;
        }
        return superModelElementFacade;
    }

    /**
     * @see org.andromda.core.metafacade.MetafacadeBase
     */
    public void resetMetafacadeContext(String context)
    {
        if (!this.contextRoot) // reset context only for non-root metafacades
        {
            context = getContext(context);  // to have same value as in original constructor call
            setMetafacadeContext (context);
            if (this.superModelElementFacadeInitialized)
            {
                ((org.andromda.core.metafacade.MetafacadeBase)superModelElementFacade).resetMetafacadeContext(context);
            }
        }
    }

    /**
     * @see com.poesys.cartridges.db.metafacades.PersistentPropertyFacade
     */
    public boolean isPersistentPropertyFacadeMetaType()
    {
        return true;
    }
    
    // ---------------- business methods ----------------------

    protected abstract com.poesys.cartridges.db.psm.db.Property handleTransformToProperty();

    private void handleTransformToProperty1oPreCondition()
    {
    }

    private void handleTransformToProperty1oPostCondition()
    {
    }

    public com.poesys.cartridges.db.psm.db.Property transformToProperty()
    {
        handleTransformToProperty1oPreCondition();
        com.poesys.cartridges.db.psm.db.Property returnValue = handleTransformToProperty();
        handleTransformToProperty1oPostCondition();
        return returnValue;
    }

    protected abstract java.lang.String handleGetGetterSetterTypeName();

    private void handleGetGetterSetterTypeName2oPreCondition()
    {
    }

    private void handleGetGetterSetterTypeName2oPostCondition()
    {
    }

    public java.lang.String getGetterSetterTypeName()
    {
        handleGetGetterSetterTypeName2oPreCondition();
        java.lang.String returnValue = handleGetGetterSetterTypeName();
        handleGetGetterSetterTypeName2oPostCondition();
        return returnValue;
    }

    protected abstract java.lang.String handleGetGetterName();

    private void handleGetGetterName3oPreCondition()
    {
    }

    private void handleGetGetterName3oPostCondition()
    {
    }

    public java.lang.String getGetterName()
    {
        handleGetGetterName3oPreCondition();
        java.lang.String returnValue = handleGetGetterName();
        handleGetGetterName3oPostCondition();
        return returnValue;
    }

    protected abstract java.lang.String handleGetSetterName();

    private void handleGetSetterName4oPreCondition()
    {
    }

    private void handleGetSetterName4oPostCondition()
    {
    }

    public java.lang.String getSetterName()
    {
        handleGetSetterName4oPreCondition();
        java.lang.String returnValue = handleGetSetterName();
        handleGetSetterName4oPostCondition();
        return returnValue;
    }

    protected abstract org.eclipse.uml2.Type handleGetType();

    private void handleGetType5oPreCondition()
    {
    }

    private void handleGetType5oPostCondition()
    {
    }

    public org.eclipse.uml2.Type getType()
    {
        handleGetType5oPreCondition();
        org.eclipse.uml2.Type returnValue = handleGetType();
        handleGetType5oPostCondition();
        return returnValue;
    }

    protected abstract java.lang.String handleGetQualifiedTypeName(org.eclipse.uml2.Type type);

    private void handleGetQualifiedTypeName6oPreCondition()
    {
    }

    private void handleGetQualifiedTypeName6oPostCondition()
    {
    }

    public java.lang.String getQualifiedTypeName(org.eclipse.uml2.Type type)
    {
        handleGetQualifiedTypeName6oPreCondition();
        java.lang.String returnValue = handleGetQualifiedTypeName(type);
        handleGetQualifiedTypeName6oPostCondition();
        return returnValue;
    }

    protected abstract boolean handleIsToMany();

    private void handleIsToMany7oPreCondition()
    {
    }

    private void handleIsToMany7oPostCondition()
    {
    }

    public boolean isToMany()
    {
        handleIsToMany7oPreCondition();
        boolean returnValue = handleIsToMany();
        handleIsToMany7oPostCondition();
        return returnValue;
    }

    /**
     * @see org.andromda.metafacades.uml.ModelElementFacade
     */
    public boolean isModelElementFacadeMetaType()
    {
        return true;
    }
    
    // ----------- delegates to org.andromda.metafacades.uml.ModelElementFacade ------------
    // from org.andromda.metafacades.uml.ModelElementFacade
    public void copyTaggedValues(org.andromda.metafacades.uml.ModelElementFacade element)
    {
        this.getSuperModelElementFacade().copyTaggedValues(element);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.Object findTaggedValue(java.lang.String tagName)
    {
        return this.getSuperModelElementFacade().findTaggedValue(tagName);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.util.Collection findTaggedValues(java.lang.String tagName)
    {
        return this.getSuperModelElementFacade().findTaggedValues(tagName);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.util.Collection getConstraints()
    {
        return this.getSuperModelElementFacade().getConstraints();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.util.Collection getConstraints(java.lang.String kind)
    {
        return this.getSuperModelElementFacade().getConstraints(kind);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String getDocumentation(java.lang.String indent)
    {
        return this.getSuperModelElementFacade().getDocumentation(indent);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String getDocumentation(java.lang.String indent, int lineLength, boolean htmlStyle)
    {
        return this.getSuperModelElementFacade().getDocumentation(indent, lineLength, htmlStyle);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String getDocumentation(java.lang.String indent, int lineLength)
    {
        return this.getSuperModelElementFacade().getDocumentation(indent, lineLength);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String getFullyQualifiedName()
    {
        return this.getSuperModelElementFacade().getFullyQualifiedName();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String getFullyQualifiedName(boolean modelName)
    {
        return this.getSuperModelElementFacade().getFullyQualifiedName(modelName);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String getFullyQualifiedNamePath()
    {
        return this.getSuperModelElementFacade().getFullyQualifiedNamePath();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String getId()
    {
        return this.getSuperModelElementFacade().getId();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public org.andromda.metafacades.uml.TypeMappings getLanguageMappings()
    {
        return this.getSuperModelElementFacade().getLanguageMappings();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public org.andromda.metafacades.uml.ModelFacade getModel()
    {
        return this.getSuperModelElementFacade().getModel();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String getName()
    {
        return this.getSuperModelElementFacade().getName();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public org.andromda.metafacades.uml.ModelElementFacade getPackage()
    {
        return this.getSuperModelElementFacade().getPackage();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String getPackageName(boolean modelName)
    {
        return this.getSuperModelElementFacade().getPackageName(modelName);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String getPackageName()
    {
        return this.getSuperModelElementFacade().getPackageName();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String getPackagePath()
    {
        return this.getSuperModelElementFacade().getPackagePath();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public org.andromda.metafacades.uml.PackageFacade getRootPackage()
    {
        return this.getSuperModelElementFacade().getRootPackage();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.util.Collection getSourceDependencies()
    {
        return this.getSuperModelElementFacade().getSourceDependencies();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public org.andromda.metafacades.uml.StateMachineFacade getStateMachineContext()
    {
        return this.getSuperModelElementFacade().getStateMachineContext();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.util.Collection getStereotypeNames()
    {
        return this.getSuperModelElementFacade().getStereotypeNames();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.util.Collection getStereotypes()
    {
        return this.getSuperModelElementFacade().getStereotypes();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.util.Collection getTaggedValues()
    {
        return this.getSuperModelElementFacade().getTaggedValues();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.util.Collection getTargetDependencies()
    {
        return this.getSuperModelElementFacade().getTargetDependencies();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.Object getTemplateParameter(java.lang.String parameterName)
    {
        return this.getSuperModelElementFacade().getTemplateParameter(parameterName);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.util.Collection getTemplateParameters()
    {
        return this.getSuperModelElementFacade().getTemplateParameters();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String getVisibility()
    {
        return this.getSuperModelElementFacade().getVisibility();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public boolean hasExactStereotype(java.lang.String stereotypeName)
    {
        return this.getSuperModelElementFacade().hasExactStereotype(stereotypeName);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public boolean hasStereotype(java.lang.String stereotypeName)
    {
        return this.getSuperModelElementFacade().hasStereotype(stereotypeName);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public boolean isBindingDependenciesPresent()
    {
        return this.getSuperModelElementFacade().isBindingDependenciesPresent();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public boolean isConstraintsPresent()
    {
        return this.getSuperModelElementFacade().isConstraintsPresent();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public boolean isTemplateParametersPresent()
    {
        return this.getSuperModelElementFacade().isTemplateParametersPresent();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String translateConstraint(java.lang.String name, java.lang.String translation)
    {
        return this.getSuperModelElementFacade().translateConstraint(name, translation);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String[] translateConstraints(java.lang.String kind, java.lang.String translation)
    {
        return this.getSuperModelElementFacade().translateConstraints(kind, translation);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String[] translateConstraints(java.lang.String translation)
    {
        return this.getSuperModelElementFacade().translateConstraints(translation);
    }

    /**
     * @see org.andromda.core.metafacade.MetafacadeBase#initialize()
     */
    public void initialize()
    {
        this.getSuperModelElementFacade().initialize();
    }

    /**
     * @see org.andromda.core.metafacade.MetafacadeBase#getValidationOwner()
     */
    public Object getValidationOwner()
    {
        Object owner = this.getSuperModelElementFacade().getValidationOwner();
        return owner;
    }

    /**
     * @see org.andromda.core.metafacade.MetafacadeBase#getValidationName()
     */
    public String getValidationName()
    {
        String name = this.getSuperModelElementFacade().getValidationName();
        return name;
    }

    /**
     * @see org.andromda.core.metafacade.MetafacadeBase#validateInvariants(java.util.Collection)
     */
    public void validateInvariants(java.util.Collection validationMessages)
    {
        this.getSuperModelElementFacade().validateInvariants(validationMessages);
    }
    
    /**
     * The property that stores the name of the metafacade.
     */
    private static final String NAME_PROPERTY = "name";
    
    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        final StringBuffer toString = new StringBuffer(this.getClass().getName());
        toString.append("[");
        try
        {
            toString.append(org.andromda.core.common.Introspector.instance().getProperty(this, NAME_PROPERTY));
        }
        catch (final Throwable throwable)
        {
            // - just ignore when the metafacade doesn't have a name property
        }
        toString.append("]");
        return toString.toString();
    }
}