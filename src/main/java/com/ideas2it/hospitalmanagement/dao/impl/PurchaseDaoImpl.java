package com.ideas2it.hospitalmanagement.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.HibernateException; 
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ideas2it.hospitalmanagement.commons.Constants;
import com.ideas2it.hospitalmanagement.dao.PurchaseDao;
import com.ideas2it.hospitalmanagement.exception.ApplicationException;
import com.ideas2it.hospitalmanagement.model.Purchase;
import com.ideas2it.hospitalmanagement.util.HibernateUtil;

/**
 * <p>
 * This is a Data-Access-Object Class for executing data manipulation
 * operation on purchase data such as add, update, remove and search.
 * on purchase details.
 * </p>
 *
 * @author Santhosh Kumar
 */
public class PurchaseDaoImpl implements PurchaseDao {

    /**
     * {@inheritDoc}
     */
    public boolean insertPurchase(Purchase purchase) throws ApplicationException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getFactory().openSession();
            transaction = session.beginTransaction();
            session.save(purchase);
            transaction.commit();
            return Boolean.TRUE;
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
                return Boolean.FALSE;
            }
            throw new ApplicationException(String.format
                (Constants.PURCHASE_ADDITION_EXCEPTION, purchase.getId()), e);            
        } finally {
            HibernateUtil.close(session); 
        }
    } 

    /**
     * {@inheritDoc}
     */
    public boolean updatePurchase(Purchase purchase) throws ApplicationException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getFactory().openSession();
            transaction = session.beginTransaction();
            session.update(purchase);
            transaction.commit();
            return Boolean.TRUE;
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
                return Boolean.FALSE;
            }
            throw new ApplicationException(String.format
                (Constants.PURCHASE_UPDATE_EXCEPTION, purchase.getId()), e);
        } finally {
            HibernateUtil.close(session);
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean deletePurchase(Purchase purchase) throws ApplicationException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getFactory().openSession();
            transaction = session.beginTransaction();
            purchase.setActive(Boolean.FALSE);
            session.update(purchase);
            transaction.commit();
            return Boolean.TRUE;
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
                return Boolean.FALSE;
            }
            throw new ApplicationException(String.format
                (Constants.PURCHASE_DELETE_EXCEPTION, purchase.getId()), e);
        } finally {
            HibernateUtil.close(session);
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<Purchase> getAllPurchases() throws ApplicationException {
        Session session = null;
        List<Purchase> purchases = new ArrayList<Purchase>();
        try {
            session = HibernateUtil.getFactory().openSession();
            Criteria criteria = session.createCriteria(Purchase.class).
                setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            criteria.add(Restrictions.like(Constants.ACTIVE, Boolean.TRUE));
            purchases = criteria.list();
        } catch (HibernateException e) {
            throw new ApplicationException(e);
        } finally {
            HibernateUtil.close(session);
        }
        return purchases;
    }

    /**
     * @{inheritdoc}
     */
    public Purchase searchPurchase(int purchaseId) throws ApplicationException {
        Purchase purchase = null;
        Session session = null;
        try {
            session = HibernateUtil.getFactory().openSession();
            Criteria criteria = session.createCriteria(Purchase.class);
            criteria.add(Restrictions.like(Constants.ID, purchaseId));
            criteria.add(Restrictions.like(Constants.ACTIVE, Boolean.TRUE));
            purchase = (Purchase)criteria.uniqueResult();
        } catch (HibernateException e) {
            throw new ApplicationException(String.format
                (Constants.PURCHASE_SEARCH_EXCEPTION, purchaseId), e);
        } finally {
            HibernateUtil.close(session);
        }
        return purchase;
    }
}
