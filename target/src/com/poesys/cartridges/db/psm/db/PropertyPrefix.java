/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
//
// Attention: Generated code! Do not modify by hand!
// Generated by: PSMmetaclass.vsl in andromda-meta-cartridge.
//
package com.poesys.cartridges.db.psm.db;

/**
 * <p>
 * A property name prefix for a property; one of several such
 * prefixes for the property in an ordered list of prefixes
 * </p>
 */
public class PropertyPrefix
{

    public PropertyPrefix()
    {
        this.prefix = null;
    }

    public PropertyPrefix(java.lang.String prefix)
    {
        this.prefix = prefix;
    }

    /**
     * Copy-constructor from other PropertyPrefix
     *
     * @param otherBean, cannot be <code>null</code>
     * @throws java.lang.NullPointerException if the argument is <code>null</code>
     */
    public PropertyPrefix(PropertyPrefix otherBean)
    {
        this(otherBean.getPrefix());
    }

    protected java.lang.String prefix;

    /**
     * <p>
     * the prefix string to prepend to the property if needed
     * </p>
     */
    public java.lang.String getPrefix()
    {
        return this.prefix;
    }

    public void setPrefix(java.lang.String prefix)
    {
        this.prefix = prefix;
    }


}