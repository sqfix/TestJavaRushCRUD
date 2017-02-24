package CRUD.session;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Makc on 23.02.2017.
 */
public class SessionCreate {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory()
    {
        try {
            return new Configuration().configure().buildSessionFactory();
        }catch (Throwable e)
        {
            System.err.println("Initial Session Factory creation failed" + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
