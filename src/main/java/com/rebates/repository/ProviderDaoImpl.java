package com.rebates.repository;


import com.rebates.dao.ProviderDao;
import com.rebates.model.Provider;
import com.rebates.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ProviderDaoImpl implements ProviderDao {
    private Logger logger = LoggerFactory.getLogger(ProviderDaoImpl.class);
    @Override
    public Provider save(Provider provider) {
        Transaction transaction = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try{
            transaction = session.beginTransaction();
            session.save(provider);
            transaction.commit();
            session.close();
        }catch (Exception e){
            if(transaction != null)
                transaction.rollback();
            logger.error("fail to insert record,error=()",e.getMessage());
            session.close();
        }
        return provider;

    }

    @Override
    public Provider update(Provider provider) {
        return null;
    }

    @Override
    public boolean deleteByName(String providerName) {
        return false;
    }

    @Override
    public boolean delete(Provider provider) {
        Transaction transaction = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        int deleteCount = 0;
        try{
            transaction = session.beginTransaction();
            session.delete(provider);
            transaction.commit();
            session.close();
        }catch (Exception e){
            if(transaction != null)
                transaction.rollback();
            logger.error("fail to delete record,error=()",e.getMessage());
            session.close();
        }
        return deleteCount > 0;
    }

    @Override
    public List<Provider> getProviders() {
        String hql = "FROM Provider";
        try (Session session = HibernateUtil.getSession()) {
            Query<Provider> query = session.createQuery(hql);
            return query.list();
        }
    }

    @Override
    public Provider getProviderById(Long id) {
        if (id == null) return null;
        String hql = "FROM Provider as provider where provider.id = :id";
        try (Session session = HibernateUtil.getSession()){
            Query<Provider> query = session.createQuery(hql);
            query.setParameter("id",id);
            return query.uniqueResult();
        }
    }
}
