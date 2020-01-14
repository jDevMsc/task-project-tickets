package org.tickets.germes.app.config;

import javax.inject.Singleton;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.hibernate.boot.SessionFactoryBuilder;
import org.tickets.germes.app.persistance.repository.CityRepository;
import org.tickets.germes.app.persistance.repository.StationRepository;
import org.tickets.germes.app.persistance.repository.hibernate.HibernateCityRepository;
import org.tickets.germes.app.persistance.repository.hibernate.HibernateStationRepository;
import org.tickets.germes.app.service.GeographicService;
import org.tickets.germes.app.service.impl.GeographicServiceImpl;
import org.tickets.germes.app.service.transform.Transformer;
import org.tickets.germes.app.service.transform.impl.SimpleDTOTransformer;

/**
 * Binds bean implementations and implemented interfaces
 */
public class ComponentBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(HibernateCityRepository.class).to(CityRepository.class).in(Singleton.class);
        bind(HibernateStationRepository.class).to(StationRepository.class).in(Singleton.class);
        bind(SimpleDTOTransformer.class).to(Transformer.class).in(Singleton.class);
        bind(GeographicServiceImpl.class).to(GeographicService.class).in(Singleton.class);
        bind(SessionFactoryBuilder.class).to(SessionFactoryBuilder.class).in(Singleton.class);
    }
}