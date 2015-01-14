/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.model.commons;

import java.util.Objects;

/**
 * Class that represents a product.
 * @author Allan Leon
 */
public class Product {
    
    private String id;
    private String measure;
    private String type;
    private Double cost;
    private Double price;
    private Double dif;
    private Double profit;

    /**
     * Constructor method
     * @param id
     * @param measure
     * @param type
     * @param cost
     * @param price 
     */
    public Product(String id, String measure, String type, Double cost, Double price) {
        this.id = id;
        this.measure = measure;
        this.type = type;
        this.cost = cost;
        this.price = price;
        this.dif = price - cost;
        this.profit = 100 * dif / cost;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the measure
     */
    public String getMeasure() {
        return measure;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @return the cost
     */
    public Double getCost() {
        return cost;
    }

    /**
     * @return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @return the dif
     */
    public Double getDif() {
        return dif;
    }

    /**
     * @return the profit
     */
    public Double getProfit() {
        return profit;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @param measure the measure to set
     */
    public void setMeasure(String measure) {
        this.measure = measure;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(Double cost) {
        this.cost = cost;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * @param dif the dif to set
     */
    public void setDif(Double dif) {
        this.dif = dif;
    }

    /**
     * @param profit the profit to set
     */
    public void setProfit(Double profit) {
        this.profit = profit;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.id);
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
        final Product other = (Product) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
