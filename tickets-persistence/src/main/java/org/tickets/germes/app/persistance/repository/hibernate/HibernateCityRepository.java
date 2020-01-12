package org.tickets.germes.app.persistance.repository.hibernate;

import java.util.List;
import javax.inject.Inject;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.tickets.germes.app.model.entity.base.AbstractEntity;
import org.tickets.germes.app.model.entity.geography.City;
import org.tickets.germes.app.model.entity.geography.Station;
import org.tickets.germes.app.persistance.hibernate.SessionFactoryBuilder;
import org.tickets.germes.app.persistance.repository.CityRepository;

public class HibernateCityRepository implements CityRepository {
	private final SessionFactory sessionFactory;
	@Inject
	public HibernateCityRepository(SessionFactoryBuilder builder) {
		sessionFactory = builder.getSessionFactory();
	}

    @Override
    public void save(City city) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.saveOrUpdate(city);
            tx.commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + " " + ex);
            if (tx != null) {
                tx.rollback();
            }
        }
    }


	@Override
	public City findById(int cityId) {
		try (Session session = sessionFactory.openSession()) {
			return session.get(City.class, cityId);
		}
	}

	@Override
	public void delete(int cityId) {
		try (Session session = sessionFactory.openSession()) {
			City city = session.get(City.class, cityId);
			if (city != null) {
				session.delete(city);
			}
		}
	}

	@Override
	public List<City> findAll() {
		try (Session session = sessionFactory.openSession()) {
			return session.createCriteria(City.class).list();
		}
	}

	@Override
	public void deleteAll() {
		try (Session session = sessionFactory.openSession()) {
			Transaction tx = null;
			try {
				tx = session.beginTransaction();
				Query stationQuery = session.getNamedQuery(Station.QUERY_DELETE_ALL);
				stationQuery.executeUpdate();
				Query query = session.getNamedQuery(City.QUERY_DELETE_ALL);
				int deleted = query.executeUpdate();
				System.out.printf("Deleted %d cities", deleted);
				tx.commit();
			} catch (Exception ex) {
				System.out.println(ex.getMessage()+ " " + ex);
				if (tx != null) {
					tx.rollback();
				}
			}
		}
}
