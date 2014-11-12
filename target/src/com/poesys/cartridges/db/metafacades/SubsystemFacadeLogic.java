//
// Attention: generated code (by MetafacadeLogic.vsl) - do not modify!
//
package com.poesys.cartridges.db.metafacades;

/**
 * MetafacadeLogic for com.poesys.cartridges.db.metafacades.SubsystemFacade
 *
 * @see com.poesys.cartridges.db.metafacades.SubsystemFacade
 */
public abstract class SubsystemFacadeLogic
    extends org.andromda.core.metafacade.MetafacadeBase
    implements com.poesys.cartridges.db.metafacades.SubsystemFacade
{

    protected org.eclipse.uml2.Package metaObject;

    public SubsystemFacadeLogic(org.eclipse.uml2.Package metaObject, String context)
    {
        super(metaObject, getContext(context));
        this.superPackageFacade =
           (org.andromda.metafacades.uml.PackageFacade)
            org.andromda.core.metafacade.MetafacadeFactory.getInstance().createFacadeImpl(
                    "org.andromda.metafacades.uml.PackageFacade",
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
            context = "com.poesys.cartridges.db.metafacades.SubsystemFacade";
        }
        return context;
    }

    private org.andromda.metafacades.uml.PackageFacade superPackageFacade;
    private boolean superPackageFacadeInitialized = false;

    /**
     * Gets the org.andromda.metafacades.uml.PackageFacade parent instance.
     */
    private org.andromda.metafacades.uml.PackageFacade getSuperPackageFacade()
    {
        if (!this.superPackageFacadeInitialized)
        {
            ((org.andromda.core.metafacade.MetafacadeBase)superPackageFacade).setMetafacadeContext(this.getMetafacadeContext());
            this.superPackageFacadeInitialized = true;
        }
        return superPackageFacade;
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
            if (this.superPackageFacadeInitialized)
            {
                ((org.andromda.core.metafacade.MetafacadeBase)superPackageFacade).resetMetafacadeContext(context);
            }
        }
    }

    /**
     * @see com.poesys.cartridges.db.metafacades.SubsystemFacade
     */
    public boolean isSubsystemFacadeMetaType()
    {
        return true;
    }
    
    // ---------------- business methods ----------------------

    protected abstract com.poesys.cartridges.db.psm.db.Subsystem handleTransformToSubsystem();

    private void handleTransformToSubsystem1oPreCondition()
    {
    }

    private void handleTransformToSubsystem1oPostCondition()
    {
    }

    public com.poesys.cartridges.db.psm.db.Subsystem transformToSubsystem()
    {
        handleTransformToSubsystem1oPreCondition();
        com.poesys.cartridges.db.psm.db.Subsystem returnValue = handleTransformToSubsystem();
        handleTransformToSubsystem1oPostCondition();
        return returnValue;
    }

    /**
     * @see org.andromda.metafacades.uml.PackageFacade
     */
    public boolean isPackageFacadeMetaType()
    {
        return true;
    }
    
    /**
     * @see org.andromda.metafacades.uml.ModelElementFacade
     */
    public boolean isModelElementFacadeMetaType()
    {
        return true;
    }
    
    // ----------- delegates to org.andromda.metafacades.uml.PackageFacade ------------
    // from org.andromda.metafacades.uml.ModelElementFacade
    public void copyTaggedValues(org.andromda.metafacades.uml.ModelElementFacade element)
    {
        this.getSuperPackageFacade().copyTaggedValues(element);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.Object findTaggedValue(java.lang.String tagName)
    {
        return this.getSuperPackageFacade().findTaggedValue(tagName);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.util.Collection findTaggedValues(java.lang.String tagName)
    {
        return this.getSuperPackageFacade().findTaggedValues(tagName);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.util.Collection getConstraints()
    {
        return this.getSuperPackageFacade().getConstraints();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.util.Collection getConstraints(java.lang.String kind)
    {
        return this.getSuperPackageFacade().getConstraints(kind);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String getDocumentation(java.lang.String indent)
    {
        return this.getSuperPackageFacade().getDocumentation(indent);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String getDocumentation(java.lang.String indent, int lineLength, boolean htmlStyle)
    {
        return this.getSuperPackageFacade().getDocumentation(indent, lineLength, htmlStyle);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String getDocumentation(java.lang.String indent, int lineLength)
    {
        return this.getSuperPackageFacade().getDocumentation(indent, lineLength);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String getFullyQualifiedName()
    {
        return this.getSuperPackageFacade().getFullyQualifiedName();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String getFullyQualifiedName(boolean modelName)
    {
        return this.getSuperPackageFacade().getFullyQualifiedName(modelName);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String getFullyQualifiedNamePath()
    {
        return this.getSuperPackageFacade().getFullyQualifiedNamePath();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String getId()
    {
        return this.getSuperPackageFacade().getId();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public org.andromda.metafacades.uml.TypeMappings getLanguageMappings()
    {
        return this.getSuperPackageFacade().getLanguageMappings();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public org.andromda.metafacades.uml.ModelFacade getModel()
    {
        return this.getSuperPackageFacade().getModel();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String getName()
    {
        return this.getSuperPackageFacade().getName();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public org.andromda.metafacades.uml.ModelElementFacade getPackage()
    {
        return this.getSuperPackageFacade().getPackage();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String getPackageName(boolean modelName)
    {
        return this.getSuperPackageFacade().getPackageName(modelName);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String getPackageName()
    {
        return this.getSuperPackageFacade().getPackageName();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String getPackagePath()
    {
        return this.getSuperPackageFacade().getPackagePath();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public org.andromda.metafacades.uml.PackageFacade getRootPackage()
    {
        return this.getSuperPackageFacade().getRootPackage();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.util.Collection getSourceDependencies()
    {
        return this.getSuperPackageFacade().getSourceDependencies();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public org.andromda.metafacades.uml.StateMachineFacade getStateMachineContext()
    {
        return this.getSuperPackageFacade().getStateMachineContext();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.util.Collection getStereotypeNames()
    {
        return this.getSuperPackageFacade().getStereotypeNames();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.util.Collection getStereotypes()
    {
        return this.getSuperPackageFacade().getStereotypes();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.util.Collection getTaggedValues()
    {
        return this.getSuperPackageFacade().getTaggedValues();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.util.Collection getTargetDependencies()
    {
        return this.getSuperPackageFacade().getTargetDependencies();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.Object getTemplateParameter(java.lang.String parameterName)
    {
        return this.getSuperPackageFacade().getTemplateParameter(parameterName);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.util.Collection getTemplateParameters()
    {
        return this.getSuperPackageFacade().getTemplateParameters();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String getVisibility()
    {
        return this.getSuperPackageFacade().getVisibility();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public boolean hasExactStereotype(java.lang.String stereotypeName)
    {
        return this.getSuperPackageFacade().hasExactStereotype(stereotypeName);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public boolean hasStereotype(java.lang.String stereotypeName)
    {
        return this.getSuperPackageFacade().hasStereotype(stereotypeName);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public boolean isBindingDependenciesPresent()
    {
        return this.getSuperPackageFacade().isBindingDependenciesPresent();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public boolean isConstraintsPresent()
    {
        return this.getSuperPackageFacade().isConstraintsPresent();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public boolean isTemplateParametersPresent()
    {
        return this.getSuperPackageFacade().isTemplateParametersPresent();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String translateConstraint(java.lang.String name, java.lang.String translation)
    {
        return this.getSuperPackageFacade().translateConstraint(name, translation);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String[] translateConstraints(java.lang.String kind, java.lang.String translation)
    {
        return this.getSuperPackageFacade().translateConstraints(kind, translation);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String[] translateConstraints(java.lang.String translation)
    {
        return this.getSuperPackageFacade().translateConstraints(translation);
    }

    // from org.andromda.metafacades.uml.PackageFacade
    public org.andromda.metafacades.uml.ModelElementFacade findModelElement(java.lang.String fullyQualifiedName)
    {
        return this.getSuperPackageFacade().findModelElement(fullyQualifiedName);
    }

    // from org.andromda.metafacades.uml.PackageFacade
    public java.util.Collection getClasses()
    {
        return this.getSuperPackageFacade().getClasses();
    }

    // from org.andromda.metafacades.uml.PackageFacade
    public java.util.Collection getModelElements()
    {
        return this.getSuperPackageFacade().getModelElements();
    }

    // from org.andromda.metafacades.uml.PackageFacade
    public java.util.Collection getOwnedElements()
    {
        return this.getSuperPackageFacade().getOwnedElements();
    }

    // from org.andromda.metafacades.uml.PackageFacade
    public java.util.Collection getSubPackages()
    {
        return this.getSuperPackageFacade().getSubPackages();
    }

    /**
     * @see org.andromda.core.metafacade.MetafacadeBase#initialize()
     */
    public void initialize()
    {
        this.getSuperPackageFacade().initialize();
    }

    /**
     * @see org.andromda.core.metafacade.MetafacadeBase#getValidationOwner()
     */
    public Object getValidationOwner()
    {
        Object owner = this.getSuperPackageFacade().getValidationOwner();
        return owner;
    }

    /**
     * @see org.andromda.core.metafacade.MetafacadeBase#getValidationName()
     */
    public String getValidationName()
    {
        String name = this.getSuperPackageFacade().getValidationName();
        return name;
    }

    /**
     * @see org.andromda.core.metafacade.MetafacadeBase#validateInvariants(java.util.Collection)
     */
    public void validateInvariants(java.util.Collection validationMessages)
    {
        this.getSuperPackageFacade().validateInvariants(validationMessages);
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