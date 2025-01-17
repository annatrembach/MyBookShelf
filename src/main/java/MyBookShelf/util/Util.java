package MyBookShelf.util;

import MyBookShelf.models.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Util {
    private static SessionFactory sessionFactory;

    private static SessionFactory buildSessionFactory() {
        try {
            org.hibernate.cfg.Configuration configuration = new Configuration()
                    .addAnnotatedClass(Book.class)
                    .addAnnotatedClass(BookAuthor.class)
                    .addAnnotatedClass(BookPublisher.class)
                    .addAnnotatedClass(BookResponse.class)
                    .addAnnotatedClass(Image.class)
                    .addAnnotatedClass(Shelf.class)
                    .addAnnotatedClass(User.class);
            configuration.configure("hibernate.cfg.xml");
            System.out.println("Hibernate Configuration loaded");

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            System.out.println("Hibernate serviceRegistry created");

            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            return sessionFactory;
        }
        catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) sessionFactory = buildSessionFactory();
        return sessionFactory;
    }

}
