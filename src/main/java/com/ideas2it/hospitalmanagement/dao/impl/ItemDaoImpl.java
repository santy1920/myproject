package com.ideas2it.hospitalmanagement.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.HibernateException; 
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ideas2it.hospitalmanagement.commons.Constants;
import com.ideas2it.hospitalmanagement.dao.ItemDao;
import com.ideas2it.hospitalmanagement.exception.ApplicationException;
import com.ideas2it.hospitalmanagement.model.Item;
import com.ideas2it.hospitalmanagement.util.HibernateUtil;

/**
 * <p>
 * This is a Data-Access-Object Class for executing data manipulation
 * operation on Item data such as add, update, remove and search.
 * on Item details.
 * </p>
 *
 * @author Santhosh Kumar
 */
public class ItemDaoImpl implements ItemDao {

    /**
     * {@inheritDoc}
     */
    public boolean insertItem(Item item) throws ApplicationException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getFactory().openSession();
            transaction = session.beginTransaction();
            session.save(item);
            transaction.commit();
            return Boolean.TRUE;
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
                return Boolean.FALSE;
            }
            throw new ApplicationException(String.format
                (Constants.ITEM_ADDITION_EXCEPTION, item.getId()), e);            
        } finally {
            HibernateUtil.close(session); 
        }
    } 

    /**
     * {@inheritDoc}
     */
    public boolean updateItem(Item item) throws ApplicationException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getFactory().openSession();
            transaction = session.beginTransaction();
            session.update(item);
            transaction.commit();
            return Boolean.TRUE;
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
                return Boolean.FALSE;
            }
            throw new ApplicationException(String.format
                (Constants.ITEM_UPDATE_EXCEPTION, item.getId()), e);
        } finally {
            HibernateUtil.close(session);
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean deleteItem(Item item) throws ApplicationException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getFactory().openSession();
            transaction = session.beginTransaction();
            item.setActive(Boolean.FALSE);
            session.update(item);
            transaction.commit();
            return Boolean.TRUE;
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
                return Boolean.FALSE;
            }
            throw new ApplicationException(String.format
                (Constants.ITEM_DELETE_EXCEPTION, item.getId()), e);
        } finally {
            HibernateUtil.close(session);
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<Item> getAllItems() throws ApplicationException {
        Session session = null;
        List<Item> items = new ArrayList<Item>();
        try {
            session = HibernateUtil.getFactory().openSession();
            Criteria criteria = session.createCriteria(Item.class).
                setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            criteria.add(Restrictions.like(Constants.ACTIVE, Boolean.TRUE));
            items = criteria.list();
        } catch (HibernateException e) {
            throw new ApplicationException(e);
        } finally {
            HibernateUtil.close(session);
        }
        return items;
    }

    /**
     * @{inheritdoc}
     */
    public Item searchItem(int itemId) throws ApplicationException {
        Item item = null;
        Session session = null;
        try {
            session = HibernateUtil.getFactory().openSession();
            Criteria criteria = session.createCriteria(Item.class);
            criteria.add(Restrictions.like(Constants.ID, itemId));
            criteria.add(Restrictions.like(Constants.ACTIVE, Boolean.TRUE));
            item = (Item)criteria.uniqueResult();
        } catch (HibernateException e) {
            throw new ApplicationException(String.format
                (Constants.ITEM_SEARCH_EXCEPTION, itemId), e);
        } finally {
            HibernateUtil.close(session);
        }
        return item;
    }
}
