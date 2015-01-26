/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.services;

import com.liquidbol.db.persistence.PersistenceException;
import com.liquidbol.db.persistence.PurchaseCrud;
import com.liquidbol.db.persistence.SupplierDebtCrud;
import com.liquidbol.model.Debt;
import com.liquidbol.model.Purchase;
import com.liquidbol.model.Supplier;
import java.sql.Date;
import java.util.logging.Logger;

/**
 * Contains all the operations a supplier can execute.
 * @author Allan Leon
 */
public class SupplierServices {
    
    private static final Logger LOG = Logger.getLogger(SupplierServices.class.getName());
    
    private final SupplierDebtCrud debtCrudManager;
    private final PurchaseCrud purchaseCrudManager;
    
    public SupplierServices() {
        this.debtCrudManager = new SupplierDebtCrud();
        this.purchaseCrudManager = new PurchaseCrud();
    }
    
    public Debt createDebt(int id, Double amount, Double maxAmount, Date limitDate) {
        Debt debt = new Debt(id, amount, maxAmount, limitDate);
        return debt;
    }
    
    public Purchase createPurchase(int id) {
        Purchase purchase = new Purchase(id, new Date(new java.util.Date().getTime()));
        return purchase;
    }
    
    public Debt addDebtToSupplier(Debt element, Supplier parent) throws PersistenceException, ClassNotFoundException {
        debtCrudManager.save(element, parent);
        element = debtCrudManager.refresh(element);
        parent.addDebt(element);
        return element;
    }
    
    public Purchase addPurchaseToSupplier(Purchase element, Supplier parent) throws PersistenceException, ClassNotFoundException {
        purchaseCrudManager.save(element, parent);
        element = purchaseCrudManager.refresh(element);
        parent.addPurchase(element);
        return element;
    }
    
    public void loadSupplierDebts(Supplier parent) throws PersistenceException, ClassNotFoundException {
        parent.setDebts(debtCrudManager.findBySupplierId(parent.getId()));
    }
    
    public void loadSupplierPurchases(Supplier parent) throws PersistenceException, ClassNotFoundException {
        parent.setPurchases(purchaseCrudManager.findBySupplierId(parent.getId()));
    }
}