/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.model.commons;

import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;

/**
 * Class that represents an item bill.
 * @author Allan Leon
 */
public class ItemBill extends Bill {
    
    private Store store;
    private boolean route;
    private Collection<ItemSale> itemSales;
    private Collection<BillPayment> payments;

    /**
     * Constructor method.
     * @param id
     * @param employee
     * @param date
     * @param obs
     * @param store
     * @param route 
     */
    public ItemBill(int id, Employee employee, Date date, String obs, Store store, boolean route) {
        super(id, employee, date, obs);
        this.store = store;
        this.route = route;
        this.itemSales = new HashSet<>();
        this.payments = new HashSet<>();
    }

    /**
     * @return the store
     */
    public Store getStore() {
        return store;
    }

    /**
     * @return the route
     */
    public boolean isRoute() {
        return route;
    }

    /**
     * @param store the store to set
     */
    public void setStore(Store store) {
        this.store = store;
    }

    /**
     * @param route the route to set
     */
    public void setRoute(boolean route) {
        this.route = route;
    }
    
    public int getNumberOfItemSales() {
        return itemSales.size();
    }
    
    public Collection<ItemSale> getAllItemSales() {
        return itemSales;
    }
    
    public void addItemSale(ItemSale itemSale) {
        itemSales.add(itemSale);
    }
    
    public int getNumberOfPayments() {
        return payments.size();
    }
    
    public Collection<BillPayment> getAllPayments() {
        return payments;
    }
    
    public void addPayment(BillPayment payment) {
        payments.add(payment);
    }
}
