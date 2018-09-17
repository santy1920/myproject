package com.ideas2it.hospitalmanagement.util;

import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ideas2it.hospitalmanagement.exception.ApplicationException;

/**
 * <p>
 * This is a Class, which is used to provide Connections to the Database. 
 * This is used for data manipulation operations on
 * various tables and database objects.
 * </p>
 *
 */
public final class HibernateUtil {

    private static SessionFactory factory = null;

    private HibernateUtil() throws ApplicationException {
        try {
            factory = new Configuration().configure
                ("hibernate.cfg.xml").
                buildSessionFactory();
        } catch (HibernateException e) {
            throw new ApplicationException(e);
        }
    }
     
    /**
     * <p>
     * This Method is used to get a sessionFactory for Database Operations.
     * Checks if the connection is already alive and returns the same
     * connection instead of creating a new One.
     * </p>
     *
     * @return factory returns a sessionfactory instance. Checks for null.
     * If the sessionfactory is null, opens a new sessionfactory.
     *
     */
    public static SessionFactory getFactory() throws ApplicationException {
        try {
            if (null == factory) {
                factory = new Configuration().configure
                    ("hibernate.cfg.xml").
                    buildSessionFactory();
            }
        } catch (HibernateException e) {
            throw new ApplicationException(e);
        }
        return factory;
    }
 
    /**
     * <p>
     * This Method is used to close a Session.
     * Checks if the session is null before closing it.
     * </p>
     *
     * @param session a session that needs to be checked and closed.
     *
     */
    public static void close(Session session) throws ApplicationException {
        try {
            if(null != session) {
                session.close();
            }
        } catch (HibernateException e) {
            throw new ApplicationException(e);
        }
    }
} 
