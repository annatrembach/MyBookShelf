package MyBookShelf.repository;

import MyBookShelf.util.Util;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

@org.springframework.stereotype.Repository
public class Repository<T> implements IRepository<T> {

    @Override
    public <T> void create(T entity) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public <T> T findById(Class<T> tempClass, Long id) {
        Session session = Util.getSessionFactory().openSession();
        T entity = null;
        try {
            entity = session.get(tempClass, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return entity;
    }

    @Override
    public <T> List<T> findAll(Class<T> tempClass) {
        Session session = Util.getSessionFactory().openSession();
        List<T> entities = null;
        try {
            Query<T> query = session.createQuery("from " + tempClass.getSimpleName(), tempClass);
            entities = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return entities;
    }

    @Override
    public <T> void update(T entity) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public <T> void delete(T entity) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public <T> List<T> findWithSorting(Class<T> entityClass, String nameOfField, boolean isAsc) {
        Session session = Util.getSessionFactory().openSession();
        List<T> entities = null;
        try {
            session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<T> cq = cb.createQuery(entityClass);
            Root<T> root = cq.from(entityClass);

            if(isAsc)
            {
                cq.orderBy(cb.asc(root.get(nameOfField)));
            } else {
                cq.orderBy(cb.desc(root.get(nameOfField)));
            }

            entities = session.createQuery(cq).getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return entities;
    }

    @Override
    public <T> List<T> findByFieldAndSorting(Class<T> entityClass, String searchField, String searchValue, String sortField, boolean isAsc) {
        Session session = Util.getSessionFactory().openSession();
        List<T> entities = null;
        try {
            session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<T> cq = cb.createQuery(entityClass);
            Root<T> root = cq.from(entityClass);

            Class<?> fieldType = entityClass.getDeclaredField(searchField).getType();
            if (Enum.class.isAssignableFrom(fieldType)) {
                Object enumValue = Enum.valueOf((Class<Enum>) fieldType, searchValue);
                cq.where(cb.equal(root.get(searchField), enumValue));
            } else if (searchField.equals("substance")) {
                cq.where(cb.equal(root.get("substance").get("name"), searchValue));
            } else {
                cq.where(cb.equal(root.get(searchField), searchValue));
            }

            if (isAsc) {
                cq.orderBy(cb.asc(root.get(sortField)));
            } else {
                cq.orderBy(cb.desc(root.get(sortField)));
            }

            entities = session.createQuery(cq).getResultList();
            session.getTransaction().commit();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return entities;
    }

    @Override
    public <T> List<T> findByField(Class<T> entityClass, String fieldName, Object fieldValue) {
        Session session = Util.getSessionFactory().openSession();
        List<T> entities = null;
        try {
            session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<T> cq = cb.createQuery(entityClass);
            Root<T> root = cq.from(entityClass);

            cq.where(cb.equal(root.get(fieldName), fieldValue));

            entities = session.createQuery(cq).getResultList();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return entities;
    }

    public <T> List<T> findByYearAndObject(Class<T> entityClass, String yearField, String year, String objectField, Object objectId) {
        Session session = Util.getSessionFactory().openSession();
        List<T> entities = null;
        try {
            session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<T> cq = cb.createQuery(entityClass);
            Root<T> root = cq.from(entityClass);

            cq.where(cb.and(
                    cb.equal(root.get(yearField), year),
                    cb.equal(root.get(objectField).get("id"), objectId)
            ));

            entities = session.createQuery(cq).getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return entities;
    }



}
