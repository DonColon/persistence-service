package com.dardan.rrafshi.persistence;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;


/**
 * The PersistenceManager is a concrete implementation
 * of the PersistenceService. It implements all basic crud operations,
 * that are needed for an entity.
 *
 * @param <K> the type of the primary key of the entity
 * @param <E> the type of the entity
 *
 * @author Dardan Rrafshi
 * @version 0.0.1
 * @since 2019-04-06
 */
public abstract class PersistenceManager<K, E extends Identifiable<K>> implements PersistenceService<K, E>
{
	protected final EntityManager manager;
	protected final Class<E> entityType;


	@SuppressWarnings("unchecked")
	public PersistenceManager(final EntityManagerFactory factory)
	{
		this.manager = factory.createEntityManager();

		final ParameterizedType genericSuperType = (ParameterizedType) this.getClass().getGenericSuperclass();

		this.entityType = (Class<E>) genericSuperType.getActualTypeArguments()[1];
	}


	@Override
	public final void create(final E entity)
		throws DatabaseException.RowAlreadyExist
	{
		final EntityTransaction transaction = this.manager.getTransaction();
		transaction.begin();

		final E temp = this.manager.find(this.entityType, entity.getID());

		if(temp != null)
			throw new DatabaseException.RowAlreadyExist("The entity with the ID '" + entity.getID() + "' already exist" );

		this.manager.persist(entity);

		transaction.commit();
	}

	@Override
	public final E retrieve(final K primaryKey)
		throws DatabaseException.NoSuchRow
	{
		final E entity = this.manager.find(this.entityType, primaryKey);

		if(entity == null)
			throw new DatabaseException.NoSuchRow("The entity with the ID '" + primaryKey + "' doesn't exist");

		return entity;
	}

	@Override
	public final void update(final E entity)
		throws DatabaseException.NoSuchRow
	{
		final EntityTransaction transaction = this.manager.getTransaction();
		transaction.begin();

		final E temp = this.manager.find(this.entityType, entity.getID());

		if(temp == null)
			throw new DatabaseException.NoSuchRow("The entity with the ID '" + entity.getID() + "' doesn't exist");

		this.manager.merge(entity);

		transaction.commit();
	}

	@Override
	public final void delete(final E entity)
		throws DatabaseException.NoSuchRow
	{
		final EntityTransaction transaction = this.manager.getTransaction();
		transaction.begin();

		final E temp = this.manager.find(this.entityType, entity.getID());

		if(temp == null)
			throw new DatabaseException.NoSuchRow("The entity with the ID '" + entity.getID() + "' doesn't exist");

		this.manager.remove(entity);

		transaction.commit();
	}

	@Override
	public final List<E> list()
	{
		final String queryname = String.format("%s.list", this.entityType.getSimpleName());

		final TypedQuery<E> query = this.manager.createNamedQuery(queryname, this.entityType);

		return query.getResultList();
	}

	@Override
	public final void save(final E entity)
	{
		try {
			final E temp = this.manager.find(this.entityType, entity.getID());

			if(temp == null)
				this.create(entity);
			else
				this.update(entity);

		} catch (DatabaseException.RowAlreadyExist | DatabaseException.NoSuchRow exception) {

			// This can't happen since this method checks if the entity exists or not
			// and calls the right method for each case.
		}
	}

	@Override
	public final void close()
		throws Exception
	{
		if(this.manager != null)
			this.manager.close();
	}
}
