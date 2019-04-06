package com.dardan.rrafshi.persistence;

/**
 * An object that is marked as Identifiable has an ID attribute,
 * which can be accessed with the getter method. It is used for
 * entities, which should be persist in the database. Since every
 * entity must have an ID, it also should have an accessor to this ID.
 *
 * @param <K> the type of the primary key of the entity
 *
 * @author Dardan Rrafshi
 * @version 0.0.1
 * @since 2019-04-06
 */
public interface Identifiable<K>
{
	/**
	 * Returns the key object of the entity.
	 *
	 * @return the ID of the entity
	 */
	K getID();
}
