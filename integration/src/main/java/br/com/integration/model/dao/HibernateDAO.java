package br.com.integration.model.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;

public class HibernateDAO<T> implements InterfaceDAO<T>, Serializable {

    private static final long serialVersionUID = 1L;
    
    private Class<T> classe;
    private Session session;

    public HibernateDAO(Class<T> classe, Session session) {
        super();
        this.classe = classe;
        this.session = session;
    }
    
    
    public void save(T entity) {
        session.save(entity);
    }

    public void update(T entity) {
        session.update(entity);
    }

    public void remove(T entity) {
        session.delete(entity);
    }

    public void merge(T entity) {
        session.merge(entity);
    }

    public T getEntity(Serializable id) {
        T entity = (T)session.get(classe, id);
        return entity;
    }

    public T getEntityByDetachedCriteria(DetachedCriteria criteria) {
        T entity = (T)criteria.getExecutableCriteria(session).uniqueResult();
        return entity;
    }

        
    public T getEntityByHQLQuery(String stringQuery) {
        Query query = session.createQuery(stringQuery);        
        return (T) query.uniqueResult();
    }

    public List<T> getListByDetachedCriteria(DetachedCriteria criteria) {
        return criteria.getExecutableCriteria(session).list();
    }
    
    public List<T> getEntities() {
        List<T> enties = (List<T>) session.createCriteria(classe).list();
        return enties;
    }    
    
}
