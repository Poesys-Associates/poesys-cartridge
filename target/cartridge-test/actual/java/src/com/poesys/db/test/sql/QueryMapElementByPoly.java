/**
 * Copyright 2009 Poesys Associates. All rights reserved.
 */
// Template: QueryAssociatedLinks.vsl

package com.poesys.db.test.sql;


/**
 * <p>
 * A query Command pattern object that implements a SQL query of a collection of 
 * MapElement objects using the primary key of the associated
 * class Poly. These objects are the linking objects rather than
 * the objects associated through the link, which have a separate representation.
 * </p>
 * <p>
 * Make any changes to query behavior by overriding methods here rather than 
 * changing the abstract superclass; AndroMDA will overwrite that class when you
 * run it but will never overwrite this concrete subclass.
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public class QueryMapElementByPoly extends AbstractQueryMapElementByPoly {

}