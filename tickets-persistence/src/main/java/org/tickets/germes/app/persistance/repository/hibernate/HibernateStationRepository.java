package org.tickets.germes.app.persistance.repository.hibernate;

import java.util.List;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.tickets.germes.app.model.entity.geography.City;
import org.tickets.germes.app.model.entity.geography.Station;
import org.tickets.germes.app.model.search.criteria.StationCriteria;
import org.tickets.germes.app.persistance.hibernate.SessionFactoryBuilder;
import org.tickets.germes.app.persistance.repository.StationRepository;

public class HibernateStationRepository implements StationRepository {

	private final SessionFactory sessionFactory;

	@Inject
	public HibernateStationRepository(SessionFactoryBuilder builder) {
		sessionFactory = builder.getSessionFactory();
	}

	@Override
	public List<Station> findAllByCriteria(StationCriteria stationCriteria) {
		try (Session session = sessionFactory.openSession()) {
			Criteria criteria = session.createCriteria(Station.class);

			/**
			 * check if the type of transport specified for filtering
			 * add to Criteria then in Restriction
			 */
			if (stationCriteria.getTransportType() != null) {
				criteria.add(Restrictions.eq(Station.FIELD_TRANSPORT_TYPE,  stationCriteria.getTransportType()));
			}
			
			if (!StringUtils.isEmpty(stationCriteria.getName())) {
				criteria = criteria.createCriteria(Station.FIELD_CITY);
				criteria.add(Restrictions.eq(City.FIELD_NAME,  stationCriteria.getName()));
			}

			return criteria.list();
		}
	}

}
