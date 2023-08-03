package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    static {
        try {
            // Load Hibernate configuration from hibernate.cfg.xml
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");

            // Build the session factory
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            // Handle the exception appropriately (e.g., print the error message)
            System.err.println("Error initializing Hibernate: " + ex.getMessage());
            ex.printStackTrace();
            // Optionally, you can set sessionFactory to null or take other actions
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }
}
