/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.model.commons;

import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Class that represents a group item´s purchases.
 * @author Allan Leon
 */
public class Purchase {
    
    private int id;
    private Double totalAmount;
    private Date date;
    private Collection<ItemPurchase> itemPurchases;

    /**
     * Constructor method.
     * @param id
     * @param date 
     */
    public Purchase(int id, Date date) {
        this.id = id;
        this.totalAmount = 0.0;
        this.date = date;
        this.itemPurchases = new HashSet<>();
    }

    /**
     * Constructor method with amount.
     * @param id
     * @param totalAmount
     * @param date 
     */
    public Purchase(int id, Double totalAmount, Date date) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.date = date;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the totalAmount
     */
    public Double getTotalAmount() {
        return totalAmount;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @return the itemPurchases
     */
    public Collection<ItemPurchase> getAllItemPurchases() {
        return itemPurchases;
    }
    
    public int getNumberOfItemPurchases() {
        return itemPurchases.size();
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param totalAmount the totalAmount to set
     */
    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * @param date the purchaseDate to set
     */
    public void setDate(Date date) {
        this.date = date;
    }
    
    public void addItemPurchase(ItemPurchase itemPurchase) {
        itemPurchases.add(itemPurchase);
    }
    
    public Collection<ItemPurchase> findItemPurchasesByItemId(String itemId) {
        Set<ItemPurchase> result = new HashSet<>();
        for (ItemPurchase purchase : itemPurchases) {
            if (purchase.getItem().getId().equals(itemId)) {
                result.add(purchase);
            }
        }
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Purchase other = (Purchase) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}