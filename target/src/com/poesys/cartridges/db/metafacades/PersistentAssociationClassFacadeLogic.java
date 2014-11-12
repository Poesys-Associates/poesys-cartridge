//
// Attention: generated code (by MetafacadeLogic.vsl) - do not modify!
//
package com.poesys.cartridges.db.metafacades;

/**
 * MetafacadeLogic for com.poesys.cartridges.db.metafacades.PersistentAssociationClassFacade
 *
 * @see com.poesys.cartridges.db.metafacades.PersistentAssociationClassFacade
 */
public abstract class PersistentAssociationClassFacadeLogic
    extends org.andromda.core.metafacade.MetafacadeBase
    implements com.poesys.cartridges.db.metafacades.PersistentAssociationClassFacade
{

    protected org.eclipse.uml2.AssociationClass metaObject;

    public PersistentAssociationClassFacadeLogic(org.eclipse.uml2.AssociationClass metaObject, String context)
    {
        super(metaObject, getContext(context));
        this.superAssociationClassFacade =
           (org.andromda.metafacades.uml.AssociationClassFacade)
            org.andromda.core.metafacade.MetafacadeFactory.getInstance().createFacadeImpl(
                    "org.andromda.metafacades.uml.AssociationClassFacade",
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
            context = "com.poesys.cartridges.db.metafacades.PersistentAssociationClassFacade";
        }
        return context;
    }

    private org.andromda.metafacades.uml.AssociationClassFacade superAssociationClassFacade;
    private boolean superAssociationClassFacadeInitialized = false;

    /**
     * Gets the org.andromda.metafacades.uml.AssociationClassFacade parent instance.
     */
    private org.andromda.metafacades.uml.AssociationClassFacade getSuperAssociationClassFacade()
    {
        if (!this.superAssociationClassFacadeInitialized)
        {
            ((org.andromda.core.metafacade.MetafacadeBase)superAssociationClassFacade).setMetafacadeContext(this.getMetafacadeContext());
            this.superAssociationClassFacadeInitialized = true;
        }
        return superAssociationClassFacade;
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
            if (this.superAssociationClassFacadeInitialized)
            {
                ((org.andromda.core.metafacade.MetafacadeBase)superAssociationClassFacade).resetMetafacadeContext(context);
            }
        }
    }

    /**
     * @see com.poesys.cartridges.db.metafacades.PersistentAssociationClassFacade
     */
    public boolean isPersistentAssociationClassFacadeMetaType()
    {
        return true;
    }
    
    // ---------------- business methods ----------------------

    protected abstract com.poesys.cartridges.db.psm.db.Dto handleTransformToDto(com.poesys.cartridges.db.psm.db.Subsystem subsystem);

    private void handleTransformToDto1oPreCondition()
    {
    }

    private void handleTransformToDto1oPostCondition()
    {
    }

    public com.poesys.cartridges.db.psm.db.Dto transformToDto(com.poesys.cartridges.db.psm.db.Subsystem subsystem)
    {
        handleTransformToDto1oPreCondition();
        com.poesys.cartridges.db.psm.db.Dto returnValue = handleTransformToDto(subsystem);
        handleTransformToDto1oPostCondition();
        return returnValue;
    }

    protected abstract boolean handleIsReadOnly();

    private void handleIsReadOnly2oPreCondition()
    {
    }

    private void handleIsReadOnly2oPostCondition()
    {
    }

    public boolean isReadOnly()
    {
        handleIsReadOnly2oPreCondition();
        boolean returnValue = handleIsReadOnly();
        handleIsReadOnly2oPostCondition();
        return returnValue;
    }

    protected abstract boolean handleIsImmutable();

    private void handleIsImmutable3oPreCondition()
    {
    }

    private void handleIsImmutable3oPostCondition()
    {
    }

    public boolean isImmutable()
    {
        handleIsImmutable3oPreCondition();
        boolean returnValue = handleIsImmutable();
        handleIsImmutable3oPostCondition();
        return returnValue;
    }

    protected abstract boolean handleIsUnremovable();

    private void handleIsUnremovable4oPreCondition()
    {
    }

    private void handleIsUnremovable4oPostCondition()
    {
    }

    public boolean isUnremovable()
    {
        handleIsUnremovable4oPreCondition();
        boolean returnValue = handleIsUnremovable();
        handleIsUnremovable4oPostCondition();
        return returnValue;
    }

    protected abstract boolean handleIsChild();

    private void handleIsChild5oPreCondition()
    {
    }

    private void handleIsChild5oPostCondition()
    {
    }

    public boolean isChild()
    {
        handleIsChild5oPreCondition();
        boolean returnValue = handleIsChild();
        handleIsChild5oPostCondition();
        return returnValue;
    }

    /**
     * @see org.andromda.metafacades.uml.AssociationClassFacade
     */
    public boolean isAssociationClassFacadeMetaType()
    {
        return true;
    }
    
    /**
     * @see org.andromda.metafacades.uml.ClassifierFacade
     */
    public boolean isClassifierFacadeMetaType()
    {
        return true;
    }
    
    /**
     * @see org.andromda.metafacades.uml.AssociationFacade
     */
    public boolean isAssociationFacadeMetaType()
    {
        return true;
    }
    
    /**
     * @see org.andromda.metafacades.uml.GeneralizableElementFacade
     */
    public boolean isGeneralizableElementFacadeMetaType()
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
    
    // ----------- delegates to org.andromda.metafacades.uml.AssociationClassFacade ------------
    // from org.andromda.metafacades.uml.AssociationClassFacade
    public java.util.Collection getConnectionAssociationEnds()
    {
        return this.getSuperAssociationClassFacade().getConnectionAssociationEnds();
    }

    // from org.andromda.metafacades.uml.ClassifierFacade
    public org.andromda.metafacades.uml.AttributeFacade findAttribute(java.lang.String name)
    {
        return this.getSuperAssociationClassFacade().findAttribute(name);
    }

    // from org.andromda.metafacades.uml.ClassifierFacade
    public java.util.Collection getAbstractions()
    {
        return this.getSuperAssociationClassFacade().getAbstractions();
    }

    // from org.andromda.metafacades.uml.ClassifierFacade
    public java.util.Collection getAllAssociatedClasses()
    {
        return this.getSuperAssociationClassFacade().getAllAssociatedClasses();
    }

    // from org.andromda.metafacades.uml.ClassifierFacade
    public java.util.Collection getAllProperties()
    {
        return this.getSuperAssociationClassFacade().getAllProperties();
    }

    // from org.andromda.metafacades.uml.ClassifierFacade
    public java.util.Collection getAllRequiredConstructorParameters()
    {
        return this.getSuperAssociationClassFacade().getAllRequiredConstructorParameters();
    }

    // from org.andromda.metafacades.uml.ClassifierFacade
    public org.andromda.metafacades.uml.ClassifierFacade getArray()
    {
        return this.getSuperAssociationClassFacade().getArray();
    }

    // from org.andromda.metafacades.uml.ClassifierFacade
    public java.lang.String getArrayName()
    {
        return this.getSuperAssociationClassFacade().getArrayName();
    }

    // from org.andromda.metafacades.uml.ClassifierFacade
    public java.util.Collection getAssociatedClasses()
    {
        return this.getSuperAssociationClassFacade().getAssociatedClasses();
    }

    // from org.andromda.metafacades.uml.ClassifierFacade
    public java.util.List getAssociationEnds()
    {
        return this.getSuperAssociationClassFacade().getAssociationEnds();
    }

    // from org.andromda.metafacades.uml.ClassifierFacade
    public java.util.Collection getAttributes()
    {
        return this.getSuperAssociationClassFacade().getAttributes();
    }

    // from org.andromda.metafacades.uml.ClassifierFacade
    public java.util.Collection getAttributes(boolean follow)
    {
        return this.getSuperAssociationClassFacade().getAttributes(follow);
    }

    // from org.andromda.metafacades.uml.ClassifierFacade
    public java.lang.String getFullyQualifiedArrayName()
    {
        return this.getSuperAssociationClassFacade().getFullyQualifiedArrayName();
    }

    // from org.andromda.metafacades.uml.ClassifierFacade
    public java.util.Collection getImplementationOperations()
    {
        return this.getSuperAssociationClassFacade().getImplementationOperations();
    }

    // from org.andromda.metafacades.uml.ClassifierFacade
    public java.lang.String getImplementedInterfaceList()
    {
        return this.getSuperAssociationClassFacade().getImplementedInterfaceList();
    }

    // from org.andromda.metafacades.uml.ClassifierFacade
    public java.util.Collection getInstanceAttributes()
    {
        return this.getSuperAssociationClassFacade().getInstanceAttributes();
    }

    // from org.andromda.metafacades.uml.ClassifierFacade
    public java.util.Collection getInstanceOperations()
    {
        return this.getSuperAssociationClassFacade().getInstanceOperations();
    }

    // from org.andromda.metafacades.uml.ClassifierFacade
    public java.util.Collection getInterfaceAbstractions()
    {
        return this.getSuperAssociationClassFacade().getInterfaceAbstractions();
    }

    // from org.andromda.metafacades.uml.ClassifierFacade
    public java.lang.String getJavaNullString()
    {
        return this.getSuperAssociationClassFacade().getJavaNullString();
    }

    // from org.andromda.metafacades.uml.ClassifierFacade
    public java.util.Collection getNavigableConnectingEnds(boolean follow)
    {
        return this.getSuperAssociationClassFacade().getNavigableConnectingEnds(follow);
    }

    // from org.andromda.metafacades.uml.ClassifierFacade
    public java.util.Collection getNavigableConnectingEnds()
    {
        return this.getSuperAssociationClassFacade().getNavigableConnectingEnds();
    }

    // from org.andromda.metafacades.uml.ClassifierFacade
    public org.andromda.metafacades.uml.ClassifierFacade getNonArray()
    {
        return this.getSuperAssociationClassFacade().getNonArray();
    }

    // from org.andromda.metafacades.uml.ClassifierFacade
    public java.lang.String getOperationCallFromAttributes()
    {
        return this.getSuperAssociationClassFacade().getOperationCallFromAttributes();
    }

    // from org.andromda.metafacades.uml.ClassifierFacade
    public java.util.Collection getOperations()
    {
        return this.getSuperAssociationClassFacade().getOperations();
    }

    // from org.andromda.metafacades.uml.ClassifierFacade
    public java.util.Collection getProperties()
    {
        return this.getSuperAssociationClassFacade().getProperties();
    }

    // from org.andromda.metafacades.uml.ClassifierFacade
    public java.util.Collection getProperties(boolean follow)
    {
        return this.getSuperAssociationClassFacade().getProperties(follow);
    }

    // from org.andromda.metafacades.uml.ClassifierFacade
    public java.util.Collection getRequiredConstructorParameters()
    {
        return this.getSuperAssociationClassFacade().getRequiredConstructorParameters();
    }

    // from org.andromda.metafacades.uml.ClassifierFacade
    public java.lang.Long getSerialVersionUID()
    {
        return this.getSuperAssociationClassFacade().getSerialVersionUID();
    }

    // from org.andromda.metafacades.uml.ClassifierFacade
    public java.util.Collection getStaticAttributes()
    {
        return this.getSuperAssociationClassFacade().getStaticAttributes();
    }

    // from org.andromda.metafacades.uml.ClassifierFacade
    public java.util.Collection getStaticOperations()
    {
        return this.getSuperAssociationClassFacade().getStaticOperations();
    }

    // from org.andromda.metafacades.uml.ClassifierFacade
    public org.andromda.metafacades.uml.ClassifierFacade getSuperClass()
    {
        return this.getSuperAssociationClassFacade().getSuperClass();
    }

    // from org.andromda.metafacades.uml.ClassifierFacade
    public java.lang.String getWrapperName()
    {
        return this.getSuperAssociationClassFacade().getWrapperName();
    }

    // from org.andromda.metafacades.uml.ClassifierFacade
    public boolean isAbstract()
    {
        return this.getSuperAssociationClassFacade().isAbstract();
    }

    // from org.andromda.metafacades.uml.ClassifierFacade
    public boolean isArrayType()
    {
        return this.getSuperAssociationClassFacade().isArrayType();
    }

    // from org.andromda.metafacades.uml.ClassifierFacade
    public boolean isAssociationClass()
    {
        return this.getSuperAssociationClassFacade().isAssociationClass();
    }

    // from org.andromda.metafacades.uml.ClassifierFacade
    public boolean isBlobType()
    {
        return this.getSuperAssociationClassFacade().isBlobType();
    }

    // from org.andromda.metafacades.uml.ClassifierFacade
    public boolean isBooleanType()
    {
        return this.getSuperAssociationClassFacade().isBooleanType();
    }

    // from org.andromda.metafacades.uml.ClassifierFacade
    public boolean isClobType()
    {
        return this.getSuperAssociationClassFacade().isClobType();
    }

    // from org.andromda.metafacades.uml.ClassifierFacade
    public boolean isCollectionType()
    {
        return this.getSuperAssociationClassFacade().isCollectionType();
    }

    // from org.andromda.metafacades.uml.ClassifierFacade
    public boolean isDataType()
    {
        return this.getSuperAssociationClassFacade().isDataType();
    }

    // from org.andromda.metafacades.uml.ClassifierFacade
    public boolean isDateType()
    {
        return this.getSuperAssociationClassFacade().isDateType();
    }

    // from org.andromda.metafacades.uml.ClassifierFacade
    public boolean isEmbeddedValue()
    {
        return this.getSuperAssociationClassFacade().isEmbeddedValue();
    }

    // from org.andromda.metafacades.uml.ClassifierFacade
    public boolean isEnumeration()
    {
        return this.getSuperAssociationClassFacade().isEnumeration();
    }

    // from org.andromda.metafacades.uml.ClassifierFacade
    public boolean isFileType()
    {
        return this.getSuperAssociationClassFacade().isFileType();
    }

    // from org.andromda.metafacades.uml.ClassifierFacade
    public boolean isInterface()
    {
        return this.getSuperAssociationClassFacade().isInterface();
    }

    // from org.andromda.metafacades.uml.ClassifierFacade
    public boolean isLeaf()
    {
        return this.getSuperAssociationClassFacade().isLeaf();
    }

    // from org.andromda.metafacades.uml.ClassifierFacade
    public boolean isListType()
    {
        return this.getSuperAssociationClassFacade().isListType();
    }

    // from org.andromda.metafacades.uml.ClassifierFacade
    public boolean isMapType()
    {
        return this.getSuperAssociationClassFacade().isMapType();
    }

    // from org.andromda.metafacades.uml.ClassifierFacade
    public boolean isPrimitive()
    {
        return this.getSuperAssociationClassFacade().isPrimitive();
    }

    // from org.andromda.metafacades.uml.ClassifierFacade
    public boolean isSetType()
    {
        return this.getSuperAssociationClassFacade().isSetType();
    }

    // from org.andromda.metafacades.uml.ClassifierFacade
    public boolean isStringType()
    {
        return this.getSuperAssociationClassFacade().isStringType();
    }

    // from org.andromda.metafacades.uml.ClassifierFacade
    public boolean isTimeType()
    {
        return this.getSuperAssociationClassFacade().isTimeType();
    }

    // from org.andromda.metafacades.uml.GeneralizableElementFacade
    public java.lang.Object findTaggedValue(java.lang.String tagName, boolean follow)
    {
        return this.getSuperAssociationClassFacade().findTaggedValue(tagName, follow);
    }

    // from org.andromda.metafacades.uml.GeneralizableElementFacade
    public java.util.Collection getAllGeneralizations()
    {
        return this.getSuperAssociationClassFacade().getAllGeneralizations();
    }

    // from org.andromda.metafacades.uml.GeneralizableElementFacade
    public java.util.Collection getAllSpecializations()
    {
        return this.getSuperAssociationClassFacade().getAllSpecializations();
    }

    // from org.andromda.metafacades.uml.GeneralizableElementFacade
    public org.andromda.metafacades.uml.GeneralizableElementFacade getGeneralization()
    {
        return this.getSuperAssociationClassFacade().getGeneralization();
    }

    // from org.andromda.metafacades.uml.GeneralizableElementFacade
    public java.util.Collection getGeneralizationLinks()
    {
        return this.getSuperAssociationClassFacade().getGeneralizationLinks();
    }

    // from org.andromda.metafacades.uml.GeneralizableElementFacade
    public java.lang.String getGeneralizationList()
    {
        return this.getSuperAssociationClassFacade().getGeneralizationList();
    }

    // from org.andromda.metafacades.uml.GeneralizableElementFacade
    public org.andromda.metafacades.uml.GeneralizableElementFacade getGeneralizationRoot()
    {
        return this.getSuperAssociationClassFacade().getGeneralizationRoot();
    }

    // from org.andromda.metafacades.uml.GeneralizableElementFacade
    public java.util.Collection getGeneralizations()
    {
        return this.getSuperAssociationClassFacade().getGeneralizations();
    }

    // from org.andromda.metafacades.uml.GeneralizableElementFacade
    public java.util.Collection getSpecializations()
    {
        return this.getSuperAssociationClassFacade().getSpecializations();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public void copyTaggedValues(org.andromda.metafacades.uml.ModelElementFacade element)
    {
        this.getSuperAssociationClassFacade().copyTaggedValues(element);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.Object findTaggedValue(java.lang.String tagName)
    {
        return this.getSuperAssociationClassFacade().findTaggedValue(tagName);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.util.Collection findTaggedValues(java.lang.String tagName)
    {
        return this.getSuperAssociationClassFacade().findTaggedValues(tagName);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.util.Collection getConstraints()
    {
        return this.getSuperAssociationClassFacade().getConstraints();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.util.Collection getConstraints(java.lang.String kind)
    {
        return this.getSuperAssociationClassFacade().getConstraints(kind);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String getDocumentation(java.lang.String indent)
    {
        return this.getSuperAssociationClassFacade().getDocumentation(indent);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String getDocumentation(java.lang.String indent, int lineLength, boolean htmlStyle)
    {
        return this.getSuperAssociationClassFacade().getDocumentation(indent, lineLength, htmlStyle);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String getDocumentation(java.lang.String indent, int lineLength)
    {
        return this.getSuperAssociationClassFacade().getDocumentation(indent, lineLength);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String getFullyQualifiedName()
    {
        return this.getSuperAssociationClassFacade().getFullyQualifiedName();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String getFullyQualifiedName(boolean modelName)
    {
        return this.getSuperAssociationClassFacade().getFullyQualifiedName(modelName);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String getFullyQualifiedNamePath()
    {
        return this.getSuperAssociationClassFacade().getFullyQualifiedNamePath();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String getId()
    {
        return this.getSuperAssociationClassFacade().getId();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public org.andromda.metafacades.uml.TypeMappings getLanguageMappings()
    {
        return this.getSuperAssociationClassFacade().getLanguageMappings();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public org.andromda.metafacades.uml.ModelFacade getModel()
    {
        return this.getSuperAssociationClassFacade().getModel();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String getName()
    {
        return this.getSuperAssociationClassFacade().getName();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public org.andromda.metafacades.uml.ModelElementFacade getPackage()
    {
        return this.getSuperAssociationClassFacade().getPackage();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String getPackageName(boolean modelName)
    {
        return this.getSuperAssociationClassFacade().getPackageName(modelName);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String getPackageName()
    {
        return this.getSuperAssociationClassFacade().getPackageName();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String getPackagePath()
    {
        return this.getSuperAssociationClassFacade().getPackagePath();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public org.andromda.metafacades.uml.PackageFacade getRootPackage()
    {
        return this.getSuperAssociationClassFacade().getRootPackage();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.util.Collection getSourceDependencies()
    {
        return this.getSuperAssociationClassFacade().getSourceDependencies();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public org.andromda.metafacades.uml.StateMachineFacade getStateMachineContext()
    {
        return this.getSuperAssociationClassFacade().getStateMachineContext();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.util.Collection getStereotypeNames()
    {
        return this.getSuperAssociationClassFacade().getStereotypeNames();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.util.Collection getStereotypes()
    {
        return this.getSuperAssociationClassFacade().getStereotypes();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.util.Collection getTaggedValues()
    {
        return this.getSuperAssociationClassFacade().getTaggedValues();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.util.Collection getTargetDependencies()
    {
        return this.getSuperAssociationClassFacade().getTargetDependencies();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.Object getTemplateParameter(java.lang.String parameterName)
    {
        return this.getSuperAssociationClassFacade().getTemplateParameter(parameterName);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.util.Collection getTemplateParameters()
    {
        return this.getSuperAssociationClassFacade().getTemplateParameters();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String getVisibility()
    {
        return this.getSuperAssociationClassFacade().getVisibility();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public boolean hasExactStereotype(java.lang.String stereotypeName)
    {
        return this.getSuperAssociationClassFacade().hasExactStereotype(stereotypeName);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public boolean hasStereotype(java.lang.String stereotypeName)
    {
        return this.getSuperAssociationClassFacade().hasStereotype(stereotypeName);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public boolean isBindingDependenciesPresent()
    {
        return this.getSuperAssociationClassFacade().isBindingDependenciesPresent();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public boolean isConstraintsPresent()
    {
        return this.getSuperAssociationClassFacade().isConstraintsPresent();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public boolean isTemplateParametersPresent()
    {
        return this.getSuperAssociationClassFacade().isTemplateParametersPresent();
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String translateConstraint(java.lang.String name, java.lang.String translation)
    {
        return this.getSuperAssociationClassFacade().translateConstraint(name, translation);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String[] translateConstraints(java.lang.String kind, java.lang.String translation)
    {
        return this.getSuperAssociationClassFacade().translateConstraints(kind, translation);
    }

    // from org.andromda.metafacades.uml.ModelElementFacade
    public java.lang.String[] translateConstraints(java.lang.String translation)
    {
        return this.getSuperAssociationClassFacade().translateConstraints(translation);
    }

    /**
     * @see org.andromda.core.metafacade.MetafacadeBase#initialize()
     */
    public void initialize()
    {
        this.getSuperAssociationClassFacade().initialize();
    }

    /**
     * @see org.andromda.core.metafacade.MetafacadeBase#getValidationOwner()
     */
    public Object getValidationOwner()
    {
        Object owner = this.getSuperAssociationClassFacade().getValidationOwner();
        return owner;
    }

    /**
     * @see org.andromda.core.metafacade.MetafacadeBase#getValidationName()
     */
    public String getValidationName()
    {
        String name = this.getSuperAssociationClassFacade().getValidationName();
        return name;
    }

    /**
     * @see org.andromda.core.metafacade.MetafacadeBase#validateInvariants(java.util.Collection)
     */
    public void validateInvariants(java.util.Collection validationMessages)
    {
        this.getSuperAssociationClassFacade().validateInvariants(validationMessages);
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