package org.tickets.germes.app.persistance.hibernate;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;
import javax.annotation.PreDestroy;
import javax.persistence.Entity;
import javax.persistence.PersistenceException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.reflections.Reflections;
import org.tickets.germes.app.model.entity.geography.Address;
import org.tickets.germes.app.model.entity.geography.City;
import org.tickets.germes.app.model.entity.geography.Coordinate;
import org.tickets.germes.app.model.entity.geography.Station;
import org.tickets.germes.app.model.entity.person.Account;
import org.tickets.germes.app.persistance.hibernate.interceptor.TimestampInterceptor;

/**
 * For managing Hibernate session factory
 */
public class SessionFactoryBuilder {
	private final SessionFactory sessionFactory;

	public SessionFactoryBuilder() {

		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(loadProperties()).build();

		MetadataSources sources = new MetadataSources(registry);
		
		//define the base package for search and set search criteria
		Reflections reflections = new Reflections("org.itsimulator.germes.app.model.entity");

		Set<Class<?>> entityClasses = reflections.getTypesAnnotatedWith(Entity.class);
		entityClasses.forEach(sources::addAnnotatedClass);

		org.hibernate.boot.SessionFactoryBuilder builder = sources.getMetadataBuilder().build().
			getSessionFactoryBuilder().applyInterceptor(new TimestampInterceptor());

		sessionFactory = builder.build();
	}

	private Properties loadProperties() {
		try {
			InputStream in = SessionFactoryBuilder.class.getClassLoader().getResourceAsStream("application.properties");
			Properties properties = new Properties();

			properties.load(in);

			return properties;
		} catch (IOException e) {
			throw new PersistenceException(e);
		}
	}

	/**
	 * Returns single instance of session factory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@PreDestroy
	public void destroy() {
		if (sessionFactory != null) {
			sessionFactory.close();
		}
	}
}