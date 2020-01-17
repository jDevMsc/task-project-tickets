package org.tickets.germes.app.persistance.repository.hibernate;

import java.util.function.Consumer;
import java.util.function.Function;
import javax.persistence.PersistenceException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.tickets.germes.app.persistance.hibernate.SessionFactoryBuilder;

public abstract class BaseHibernateRepository {

    protected final SessionFactory sessionFactory;
    public BaseHibernateRepository(SessionFactoryBuilder builder) {
        sessionFactory = builder.getSessionFactory();
    }

    /**
     * Executes any Consumer object that accepts session
     */
    protected void execute(Consumer<Session> action) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            action.accept(session);
            tx.commit();
        } catch (Exception ex) {
            handleError(tx, ex);
            throw new PersistenceException(ex);
        }
    }

    private void handleError(Transaction tx, Exception ex) {
        if (tx != null) {
            tx.rollback();
        }
    }

    /**
     * Executes the Function object and returns its result
     */
    protected <R> R query(Function<Session, R> func) {
        try (Session session = sessionFactory.openSession()) {
            return func.apply(session);
        } catch (Exception ex) {
            throw new PersistenceException(ex);
        }
    }
}
