//
// Attention: generated code (by MetafacadeLogic.vsl) - do not modify!
//
package com.poesys.cartridges.db.metafacades;

/**
 * MetafacadeLogic for com.poesys.cartridges.db.metafacades.ParameterizedQuerySqlFacade
 *
 * @see com.poesys.cartridges.db.metafacades.ParameterizedQuerySqlFacade
 */
public abstract class ParameterizedQuerySqlFacadeLogic
    extends org.andromda.core.metafacade.MetafacadeBase
    implements com.poesys.cartridges.db.metafacades.ParameterizedQuerySqlFacade
{

    protected org.andromda.metafacades.emf.uml2.AssociationEnd metaObject;

    public ParameterizedQuerySqlFacadeLogic(org.andromda.metafacades.emf.uml2.AssociationEnd metaObject, String context)
    {
        super(metaObject, getContext(context));
        this.superAssociationEndFacade =
           (org.andromda.metafacades.uml.AssociationEndFacade)
            org.andromda.core.metafacade.MetafacadeFactory.getInstance().createFacadeImpl(
                    "org.andromda.metafacades.uml.AssociationEndFacade",
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
            context = "com.poesys.cartridges.db.metafacades.ParameterizedQuerySqlFacade";
        }
        return context;
    }

    private org.andromda.metafacades.uml.AssociationEndFacade superAssociationEndFacade;
    private boolean superAssociationEndFacadeInitialized = false;

    /**
     * Gets the org.andromda.metafacades.uml.AssociationEndFacade parent instance.
     */
    private org.andromda.metafacades.uml.AssociationEndFacade getSuperAssociationEndFacade()
    {
        if (!this.superAssociationEndFacadeInitialized)
        {
            ((org.andromda.core.metafacade.MetafacadeBase)superAssociationEndFacade).setMetafacadeContext(this.getMetafacadeContext());
            this.superAssociationEndFacadeInitialized = true;
        }
        return superAssociationEndFacade;
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
            if (this.superAssociationEndFacadeInitialized)
            {
                ((org.andromda.core.metafacade.MetafacadeBase)superAssociationEndFacade).resetMetafacadeContext(context);
            }
        }
    }

    /**
     * @see com.poesys.cartridges.db.metafacades.ParameterizedQuerySqlFacade
     */
    public boolean isParameterizedQuerySqlFacadeMetaType()
    {
        return true;
    }
    
    // ---------------- business methods ----------------------

    protected abstract com.poesys.cartridges.db.psm.db.ParameterizedQuery handleTransformToQuery();

    private void handleTransformToQuery1oPreCondition()
    {
    }

    private void handleTransformToQuery1oPostCondition()
    {
    }

    public com.poesys.cartridges.db.psm.db.ParameterizedQuery transformToQuery()
    {
        handleTransformToQuery1oPreCondition();
        com.poesys.cartridges.db.psm.db.ParameterizedQuery returnValue = handleTransformToQuery();
        handleTransformToQuery1oPostCondition();
        return returnValue;
    }

    /**
     * @see org.andromda.metafacades.uml.AssociationEndFacade
     */
    public boolean isAssociationEndFacadeMetaType()
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
    
    // ----------- delegates to org.andromda.metafacades.uml.AssociationEndFacade ------------
    // from org.andromda.metafacades.uml.AssociationEndFacade
    public org.andromda.metafacades.uml.AssociationFacade getAssociation()
    {
        return this.getSuperAssociationEndFacade().getAssociation();
    }

    // from org.andromda.metafacades.uml.AssociationEndFacade
    public java.lang.String getGetterName()
    {
        return this.getSuperAssociationEndFacade().getGetterName();
    }

    // from org.andromda.metafacades.uml.AssociationEndFacade
    public java.lang.String getGetterSetterTypeName()
    {
        return this.getSuperAssociationEndFacade().getGetterSetterTypeName();
    }

    // from org.andromda.metafacades.uml.AssociationEndFacade
    public int getLower()
    {
        return this.getSuperAssociationEndFacade().getLower();
    }

    // from org.andromda.metafacades.uml.AssociationEndFacade
    public org.andromda.metafacades.uml.AssociationEndFacade getOtherEnd()
    {
        return this.getSuperAssociationEndFacade().getOtherEnd();
    }

    // from org.andromda.metafacades.uml.AssociationEndFacade
    public java.lang.String getSetterName()
    {
        return this.getSuperAssociationEndFacade().getSetterName();
    }

    // from org.andromda.metafacades.uml.AssociationEndFacade
    public org.andromda.metafacades.uml.ClassifierFacade getType()
    {
        return this.getSuperAssociationEndFacade().getType();
    }

    // from org.andromda.metafacades.uml.AssociationEndFacade
    public int getUpper()
    {
        return this.getSuperAssociationEndFacade().getUpper();
    }

    // from org.andromda.metafacades.uml.AssociationEndFacade
    public boolean isAggregation()
    {
        return this.getSuperAssociationEndFacade().isAggregation();
    }

    // from org.andromda.metafacades.uml.AssociationEndFacade
    public boolean isChild()
    {
        return this.getSuperAssociationEndFacade().isChild();
    }

    // from org.andromda.metafacades.uml.AssociationEndFacade
    public boolean isComposition()
    {
        return this.getSuperAssociationEndFacade().isComposition();
    }

    // from org.andromda.metafacades.uml.AssociationEndFacade
    public boolean isMany()
    {
        return this.getSuperAssociationEndFacade().isMany();
    }

    // from org.andromda.metafacades.uml.AssociationEndFacade
    public boolean isMany2Many()
    {
        return this.getSuperAssociationEndFacade().isMany2Many();
    }

    // from org.andromda.metafacades.uml.AssociationEndFacade
    public boolean isMany2One()
    {
        return this.getSuperAssociationEndFacade().isMany2One();
    }

    // from org.andromda.metafacades.uml.AssociationEndFacade
    public boolean isNavigable()
    {
        return this.getSuperAssociationEndFacade().isNavigable();
    }

    // from org.andromda.metafacades.uml.AssociationEndFacade
    public boolean isOne2Many()
    {
        return this.getSuperAssociationEndFacade().isOne2Many();
    }

    // from org.andromda.metafacades.uml.AssociationEndFacade
    public boolean isOne2One()
    {
        return this.getSuperAssociationEndFacade().isOne2One();
    }

    // from org.andromda.metafacades.uml.AssociationEndFacade
    public boolean isOrdered()
    {
        return this.getSuperAssociationEndFacade().isOrdered();
    }

    // from org.andromda.metafacades.uml.AssociationEndFacade
    public boolean isReadOnly()
    {
        return this.getSuperAssociationEndFacade().isReadOnly();
    }

    // from org.andromda.metafacades.uml.AssociationEndFacade
    public boolean isRequired()
    {
        return this.getSuperAssociationEndFacade().isRequired();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public void copyTaggedValues(org.andromda.metafacades.uml.ModelElementFacade element)
    {
        this.getSuperAssociationEndFacade().copyTaggedValues(element);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.Object findTaggedValue(java.lang.String tagName)
    {
        return this.getSuperAssociationEndFacade().findTaggedValue(tagName);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.util.Collection findTaggedValues(java.lang.String tagName)
    {
        return this.getSuperAssociationEndFacade().findTaggedValues(tagName);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.util.Collection getConstraints()
    {
        return this.getSuperAssociationEndFacade().getConstraints();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.util.Collection getConstraints(java.lang.String kind)
    {
        return this.getSuperAssociationEndFacade().getConstraints(kind);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String getDocumentation(java.lang.String indent)
    {
        return this.getSuperAssociationEndFacade().getDocumentation(indent);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String getDocumentation(java.lang.String indent, int lineLength)
    {
        return this.getSuperAssociationEndFacade().getDocumentation(indent, lineLength);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String getDocumentation(java.lang.String indent, int lineLength, boolean htmlStyle)
    {
        return this.getSuperAssociationEndFacade().getDocumentation(indent, lineLength, htmlStyle);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String getFullyQualifiedName()
    {
        return this.getSuperAssociationEndFacade().getFullyQualifiedName();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String getFullyQualifiedName(boolean modelName)
    {
        return this.getSuperAssociationEndFacade().getFullyQualifiedName(modelName);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String getFullyQualifiedNamePath()
    {
        return this.getSuperAssociationEndFacade().getFullyQualifiedNamePath();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String getId()
    {
        return this.getSuperAssociationEndFacade().getId();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public org.andromda.metafacades.uml.TypeMappings getLanguageMappings()
    {
        return this.getSuperAssociationEndFacade().getLanguageMappings();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public org.andromda.metafacades.uml.ModelFacade getModel()
    {
        return this.getSuperAssociationEndFacade().getModel();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String getName()
    {
        return this.getSuperAssociationEndFacade().getName();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public org.andromda.metafacades.uml.ModelElementFacade getPackage()
    {
        return this.getSuperAssociationEndFacade().getPackage();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String getPackageName(boolean modelName)
    {
        return this.getSuperAssociationEndFacade().getPackageName(modelName);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String getPackageName()
    {
        return this.getSuperAssociationEndFacade().getPackageName();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String getPackagePath()
    {
        return this.getSuperAssociationEndFacade().getPackagePath();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public org.andromda.metafacades.uml.PackageFacade getRootPackage()
    {
        return this.getSuperAssociationEndFacade().getRootPackage();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.util.Collection getSourceDependencies()
    {
        return this.getSuperAssociationEndFacade().getSourceDependencies();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public org.andromda.metafacades.uml.StateMachineFacade getStateMachineContext()
    {
        return this.getSuperAssociationEndFacade().getStateMachineContext();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.util.Collection getStereotypeNames()
    {
        return this.getSuperAssociationEndFacade().getStereotypeNames();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.util.Collection getStereotypes()
    {
        return this.getSuperAssociationEndFacade().getStereotypes();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.util.Collection getTaggedValues()
    {
        return this.getSuperAssociationEndFacade().getTaggedValues();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.util.Collection getTargetDependencies()
    {
        return this.getSuperAssociationEndFacade().getTargetDependencies();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.Object getTemplateParameter(java.lang.String parameterName)
    {
        return this.getSuperAssociationEndFacade().getTemplateParameter(parameterName);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.util.Collection getTemplateParameters()
    {
        return this.getSuperAssociationEndFacade().getTemplateParameters();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String getVisibility()
    {
        return this.getSuperAssociationEndFacade().getVisibility();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public boolean hasExactStereotype(java.lang.String stereotypeName)
    {
        return this.getSuperAssociationEndFacade().hasExactStereotype(stereotypeName);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public boolean hasStereotype(java.lang.String stereotypeName)
    {
        return this.getSuperAssociationEndFacade().hasStereotype(stereotypeName);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public boolean isBindingDependenciesPresent()
    {
        return this.getSuperAssociationEndFacade().isBindingDependenciesPresent();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public boolean isConstraintsPresent()
    {
        return this.getSuperAssociationEndFacade().isConstraintsPresent();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public boolean isTemplateParametersPresent()
    {
        return this.getSuperAssociationEndFacade().isTemplateParametersPresent();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String translateConstraint(java.lang.String name, java.lang.String translation)
    {
        return this.getSuperAssociationEndFacade().translateConstraint(name, translation);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String[] translateConstraints(java.lang.String kind, java.lang.String translation)
    {
        return this.getSuperAssociationEndFacade().translateConstraints(kind, translation);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String[] translateConstraints(java.lang.String translation)
    {
        return this.getSuperAssociationEndFacade().translateConstraints(translation);
    }

    /**
     * @see org.andromda.core.metafacade.MetafacadeBase#initialize()
     */
    public void initialize()
    {
        this.getSuperAssociationEndFacade().initialize();
    }

    /**
     * @see org.andromda.core.metafacade.MetafacadeBase#getValidationOwner()
     */
    public Object getValidationOwner()
    {
        Object owner = this.getSuperAssociationEndFacade().getValidationOwner();
        return owner;
    }

    /**
     * @see org.andromda.core.metafacade.MetafacadeBase#getValidationName()
     */
    public String getValidationName()
    {
        String name = this.getSuperAssociationEndFacade().getValidationName();
        return name;
    }

    /**
     * @see org.andromda.core.metafacade.MetafacadeBase#validateInvariants(java.util.Collection)
     */
    public void validateInvariants(java.util.Collection validationMessages)
    {
        this.getSuperAssociationEndFacade().validateInvariants(validationMessages);
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