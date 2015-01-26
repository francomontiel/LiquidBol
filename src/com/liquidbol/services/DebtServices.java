/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.services;

import com.liquidbol.db.persistence.DebtPaymentCrud;
import com.liquidbol.db.persistence.PersistenceException;
import com.liquidbol.model.Debt;
import com.liquidbol.model.DebtPayment;
import java.sql.Date;
import java.util.logging.Logger;

/**
 * Contains all the operation a debt can execute.
 * @author Allan Leon
 */
public class DebtServices {
    
    private static final Logger LOG = Logger.getLogger(DebtServices.class.getName());
    
    private final DebtPaymentCrud debtPaymentCrudManager;
    
    public DebtServices() {
        this.debtPaymentCrudManager = new DebtPaymentCrud();
    }
    
    public DebtPayment createDebtPayment(int id, Date payDate, Double amount) {
        DebtPayment debtPayment = new DebtPayment(id, payDate, amount);
        return debtPayment;
    }
    
    public DebtPayment addPaymentToDebt(DebtPayment element, Debt parent)
            throws PersistenceException, ClassNotFoundException {
        debtPaymentCrudManager.save(element, parent);
        element = debtPaymentCrudManager.refresh(element);
        parent.addPayment(element);
        return element;
    }
    
    public void loadDebtPayments(Debt parent) throws PersistenceException, ClassNotFoundException {
        parent.setPayments(debtPaymentCrudManager.findByDebtId(parent.getId()));
    }
}