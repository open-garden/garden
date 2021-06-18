package com.zipc.garden.webplatform.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

/**
 * A common class for data access objects.
 */
public class DAOUtils {

    /**
     * private constructor to block the instantiation from other class.
     */
    private DAOUtils() {
    }

    /**
     * Destroy this SessionFactory and release all resources (caches, connection pools, etc).
     */
    public static void closeSessionFactory() {
        try {
            getSessionFactory().close();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /**
     * method to get the SessionFactory
     * @return SessionFactory
     */
    public static synchronized SessionFactory getSessionFactory() {
        return SessionFactoryHolder.SESSION_FACTORY;
    }

    /**
     * class to hold the SessionFactory as final
     */
    private static class SessionFactoryHolder {
        private static final SessionFactory SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
    }

    /**
     * Deletes the specified instance every 10000.
     * @param em An object that manages an entity. It is used to perform the remove processing collectively.
     * @param targetList Issuance result of SELECT statement. This result will be deleted from the database.
     */
    public static void deleteEntity(EntityManager em, List<?> targetList) {
        for (int i = 0; i < targetList.size(); i++) {
            em.remove(targetList.get(i));
            if (i % 10000 == 0) {
                resetEntityManager(em);
            }
        }
        resetEntityManager(em);
    }

    /**
     * After applying the specified entity manager, it will be cleared and the cache will be empty.
     * @param em specified entity manager
     */
    private static void resetEntityManager(EntityManager em) {
        em.flush();
        em.clear();
    }

    /**
     * Create / get the properties of Entity Manager.
     * @return the properties of Entity Manager.
     */
    @SuppressWarnings("all")
    public static Map createProps() {
        Map props = new HashMap();

        props.put(AvailableSettings.STATEMENT_BATCH_SIZE, "10000");

        return props;
    }

}
