package com.rebates.repository;

import com.rebates.dao.RebateDao;
import com.rebates.model.Provider;
import com.rebates.model.Rebate;
import com.rebates.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class RebateDaoImpl implements RebateDao {
    private Logger logger = LoggerFactory.getLogger(RebateDaoImpl.class);

    @Override
    public Rebate save(Rebate rebate) {
        Transaction transaction = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try{
            transaction = session.beginTransaction();
            session.saveOrUpdate(rebate);
            transaction.commit();
            session.close();
        }catch (Exception e){
            if(transaction != null)
                transaction.rollback();
            logger.error("fail to insert record,error=()",e.getMessage());
            session.close();
        }
        return rebate;
    }

    @Override
    public Rebate update(Rebate rebate) {
        Transaction transaction = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try{
            transaction = session.beginTransaction();
            session.update(rebate);
            transaction.commit();
            session.close();
        }catch (Exception e){
            if(transaction != null)
                transaction.rollback();
            logger.error("fail to update record,error=()",e.getMessage());
            session.close();
        }
        return rebate;
    }

    @Override
    public boolean deleteByName(String rebateName) {
        return false;
    }

    @Override
    public boolean delete(Rebate rebate) {Transaction transaction = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        int deleteCount = 0;
        try{
            transaction = session.beginTransaction();
            session.delete(rebate);
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
    public List<Rebate> getRebates() { //hql FetchType EAGER through table mapped is LAZY
        String hql = "From Rebate ";
        try (Session session = HibernateUtil.getSession()) {
            Query<Rebate> query = session.createQuery(hql);
            return query.list();
        }
    }

    @Override
    public Rebate getRebateById(Long id) {
        if (id == null) return null;
        String hql = "FROM Rebate as rebate where rebate.id = :id";
        try (Session session = HibernateUtil.getSession()){
            Query<Rebate> query = session.createQuery(hql);
            query.setParameter("id",id);
            return query.uniqueResult();
        }
    }
}
