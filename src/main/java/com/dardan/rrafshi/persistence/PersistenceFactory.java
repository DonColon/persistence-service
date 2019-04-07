package com.dardan.rrafshi.persistence;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.dardan.rrafshi.commons.crypto.Passwords;

/**
 * The PersistenceFactory creates an EntityManagerFactory that will be
 * passed to the PersistenceManager classes. The password of a user will
 * be load from a file.
 *
 * @author Dardan Rrafshi
 * @version 0.0.1
 * @since 2019-04-06
 */
public final class PersistenceFactory
{
	private PersistenceFactory() {}


	public static EntityManagerFactory createEntityManagerFactory(final String persistenceUnitname)
	{
		final Map<String, String> configurationOverride = new HashMap<>();
		configurationOverride.put("hibernate.connection.password",
				Passwords.getPasswordFromFile("database"));

		return Persistence.createEntityManagerFactory(persistenceUnitname, configurationOverride);
	}
}
