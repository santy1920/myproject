package com.ideas2it.hospitalmanagement.service.impl;

import java.util.List;

import com.ideas2it.hospitalmanagement.dao.PurchaseDao;
import com.ideas2it.hospitalmanagement.dao.impl.PurchaseDaoImpl;
import com.ideas2it.hospitalmanagement.exception.ApplicationException;
import com.ideas2it.hospitalmanagement.model.Purchase;
import com.ideas2it.hospitalmanagement.service.PurchaseService;

/**
 * <p>
 * This class is used to manipulate and perform business logic operations with 
 * the purchase details.
 * </p>
 *
 * @author Santhosh Kumar
 */
public class PurchaseServiceImpl implements PurchaseService {
    private PurchaseDao purchaseDao = new PurchaseDaoImpl();
  
    /**
     * @{inheritdoc}
     */
    public boolean addPurchase(Purchase purchase) throws ApplicationException {
        return purchaseDao.insertPurchase(purchase);
    }

    /**
     * @{inheritdoc}
     */
    public List<Purchase> retrievePurchases() throws ApplicationException {
        return purchaseDao.getAllPurchases();
    }
    
    /**
     * @{inheritdoc}
     */
    public boolean deletePurchase(Purchase purchase) throws ApplicationException {
        return purchaseDao.deletePurchase(purchase);
    }

    /**
     * @{inheritdoc}
     */
    public boolean updatePurchase(Purchase purchase) throws ApplicationException {
        return purchaseDao.updatePurchase(purchase);
    }
    
    /**
     * @{inheritdoc}
     */
    public Purchase searchPurchase(int purchaseId) throws ApplicationException {
        return purchaseDao.searchPurchase(purchaseId);
    }
}
