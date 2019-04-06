package com.dardan.rrafshi.persistence;

import java.util.List;


/**
 * The PersistenceService serves methods for crud operations
 * for the specified entity.
 *
 * @param <K> the type of the primary key of the entity
 * @param <E> the type of the entity
 *
 * @author Dardan Rrafshi
 * @version 0.0.1
 * @since 2019-04-06
 */
public interface PersistenceService<K, E extends Identifiable<K>> extends AutoCloseable
{
	/**
	 * Creates an entity in the database.
	 *
	 * @param entity
	 * 			the entity to persist
	 *
	 * @throws DatabaseException.RowAlreadyExist
	 * 			when the entity already exist on the database
	 */
	void create(E entity) throws DatabaseException.RowAlreadyExist;

	/**
	 * Retrieves an entity by it's primary key.
	 *
	 * @param primaryKey
	 * 			the primary key of the entity
	 *
	 * @return the entity with the specified primary key
	 *
	 * @throws DatabaseException.NoSuchRow
	 * 			when the entity doesn't exist on the database
	 */
	E retrieve(K primaryKey) throws DatabaseException.NoSuchRow;

	/**
	 * Updates an entity in the database.
	 *
	 * @param entity
	 * 			the entity to update
	 *
	 * @throws DatabaseException.NoSuchRow
	 * 			when the entity doesn't exist on the database
	 */
	void update(E entity) throws DatabaseException.NoSuchRow;

	/**
	 * Deletes an entity in the database.
	 *
	 * @param entity
	 * 			the entity to delete
	 *
	 * @throws DatabaseException.NoSuchRow
	 * 			when the entity doesn't exist on the database
	 */
	void delete(E entity) throws DatabaseException.NoSuchRow;

	/**
	 * Lists all entities of type E on the database.
	 *
	 * @return a list of entities
	 */
	List<E> list();

	/**
	 * If an entity doesn't exist it will be created. If it does exist
	 * it will be updated.
	 *
	 * @param entity
	 * 			the entity to save
	 */
	void save(E entity);
}
