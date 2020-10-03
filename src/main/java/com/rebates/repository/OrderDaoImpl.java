package com.rebates.repository;

import com.rebates.dao.OrderDao;
import com.rebates.model.Order;
import com.rebates.model.Rebate;
import com.rebates.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {
    private Logger logger = LoggerFactory.getLogger(RebateDaoImpl.class);

    @Override
    public Order save(Order order, Rebate rebate) {
        Transaction transaction = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try{
            transaction = session.beginTransaction();
            session.saveOrUpdate(order);
            transaction.commit();
            session.close();
        }catch (Exception e){
            if(transaction != null)
                transaction.rollback();
            logger.error("fail to insert record,error=()",e.getMessage());
            session.close();
        }
        return order;
    }


    @Override
    public Order save(Order order) {
        Transaction transaction = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try{
            transaction = session.beginTransaction();
            session.saveOrUpdate(order);
            transaction.commit();
            session.close();
        }catch (Exception e){
            if(transaction != null)
                transaction.rollback();
            logger.error("fail to insert record,error=()",e.getMessage());
            session.close();
        }
        return order;
    }

    @Override
    public Order update(Order order) {
        Transaction transaction = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try{
            transaction = session.beginTransaction();
            session.update(order);
            transaction.commit();
            session.close();
        }catch (Exception e){
            if(transaction != null)
                transaction.rollback();
            logger.error("fail to update record,error=()",e.getMessage());
            session.close();
        }
        return order;
    }


    @Override
    public boolean delete(Order order) {Transaction transaction = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        int deleteCount = 0;
        try{
            transaction = session.beginTransaction();
            session.delete(order);
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
    public List<Order> getOrders() { //hql FetchType EAGER through table mapped is LAZY
        String hql = "From Order ";
        try (Session session = HibernateUtil.getSession()) {
            Query<Order> query = session.createQuery(hql);
            return query.list();
        }
    }

    @Override
    public Order getOrderById(Long id) {
        if (id == null) return null;
        String hql = "FROM Order as order where order.id = :id";
        try (Session session = HibernateUtil.getSession()){
            Query<Order> query = session.createQuery(hql);
            query.setParameter("id",id);
            return query.uniqueResult();
        }
    }
}

