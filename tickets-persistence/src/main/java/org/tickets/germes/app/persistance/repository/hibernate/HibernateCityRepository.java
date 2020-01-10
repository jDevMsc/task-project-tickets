package org.tickets.germes.app.persistance.repository.hibernate;

import javax.inject.Inject;
import org.hibernate.SessionFactory;
import org.tickets.germes.app.persistance.hibernate.SessionFactoryBuilder;

public class HibernateCityRepository {

	private final SessionFactory sessionFactory;

	@Inject
	public HibernateCityRepository(SessionFactoryBuilder builder) {
		sessionFactory = builder.getSessionFactory();
	}
}
