package org.tickets.germes.app.persistance.hibernate;

import javax.annotation.PreDestroy;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.tickets.germes.app.model.entity.geography.Address;
import org.tickets.germes.app.model.entity.geography.City;
import org.tickets.germes.app.model.entity.geography.Coordinate;
import org.tickets.germes.app.model.entity.geography.Station;
import org.tickets.germes.app.model.entity.person.Account;

/**
 * For managing Hibernate session factory
 */
public class SessionFactoryBuilder {
	private final SessionFactory sessionFactory;

	public SessionFactoryBuilder() {
		ServiceRegistry registry = new StandardServiceRegistryBuilder().build();

		MetadataSources sources = new MetadataSources(registry);

		sources.addAnnotatedClass(City.class);
		sources.addAnnotatedClass(Station.class);
		sources.addAnnotatedClass(Coordinate.class);
		sources.addAnnotatedClass(Address.class);
		sources.addAnnotatedClass(Account.class);

		sessionFactory = sources.buildMetadata().buildSessionFactory();
	}
	
	/**
	 * Returns single instance of session factory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@PreDestroy
	public void destroy() {
		if(sessionFactory != null) {
			sessionFactory.close();
		}
	}
}
