package com.liquidbol.model;

import com.liquidbol.db.persistence.EmployeeCrud;
import com.liquidbol.db.persistence.PersistenceException;
import com.liquidbol.db.persistence.StoreCrud;
import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;

/**
 * Class that represents a bill.
 * @author Allan Leon
 */
public class Bill implements Serializable {
    
    private int id;
    private Store store;
    private Employee employee;
    private Date date;
    private Double totalAmount;
    private boolean billed;
    private boolean route;
    private String obs;
    private Collection<BillPayment> payments;
    private Collection<ItemSale> itemSales;
    private Collection<ServiceReception> serviceReceptions;

    /**
     * Constructor method.
     * @param id
     * @param store
     * @param employee
     * @param date
     * @param billed
     * @param route
     * @param obs 
     */
    public Bill(int id, Store store, Employee employee, Date date, boolean billed, boolean route, String obs) {
        this.id = id;
        this.store = store;
        this.employee = employee;
        this.date = date;
        this.totalAmount = 0.0;
        this.billed = billed;
        this.route = route;
        this.obs = obs;
        this.payments = new HashSet<>();
        this.itemSales = new HashSet<>();
        this.serviceReceptions = new HashSet<>();
    }

    /**
     * Constructor method with amount.
     * @param id
     * @param store
     * @param employee
     * @param date
     * @param totalAmount
     * @param billed
     * @param route
     * @param obs 
     */
    public Bill(int id, Store store, Employee employee, Date date, Double totalAmount, boolean billed, boolean route, String obs) {
        this.id = id;
        this.store = store;
        this.employee = employee;
        this.date = date;
        this.totalAmount = totalAmount;
        this.billed = billed;
        this.route = route;
        this.obs = obs;
        this.payments = new HashSet<>();
        this.itemSales = new HashSet<>();
        this.serviceReceptions = new HashSet<>();
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the store
     */
    public Store getStore() {
        return store;
    }

    /**
     * @return the employee
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @return the totalAmount
     */
    public Double getTotalAmount() {
        return totalAmount;
    }
    
    public Double calculateTotalAmount() {
        totalAmount = 0.0;
        for (ServiceReception serviceReception : serviceReceptions) {
            totalAmount += serviceReception.getAmount();
        }
        for (ItemSale itemSale : itemSales) {
            totalAmount += itemSale.getAmount();
        }
        return totalAmount;
    }

    /**
     * @return the billed
     */
    public boolean isBilled() {
        return billed;
    }

    /**
     * @return the route
     */
    public boolean isRoute() {
        return route;
    }

    /**
     * @return the obs
     */
    public String getObs() {
        return obs;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param store the store to set
     */
    public void setStore(Store store) {
        this.store = store;
    }

    /**
     * @param employee the employee to set
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @param totalAmount the totalAmount to set
     */
    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * @param billed the billed to set
     */
    public void setBilled(boolean billed) {
        this.billed = billed;
    }

    /**
     * @param route the route to set
     */
    public void setRoute(boolean route) {
        this.route = route;
    }

    /**
     * @param obs the obs to set
     */
    public void setObs(String obs) {
        this.obs = obs;
    }

    /**
     * @param payments the payments to set
     */
    public void setPayments(Collection<BillPayment> payments) {
        this.payments = payments;
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
    
    /**
     * @param itemSales the itemSales to set
     */
    public void setItemSales(Collection<ItemSale> itemSales) {
        this.itemSales = itemSales;
    }
    
    public int getNumberOfItemSales() {
        return itemSales.size();
    }
    
    public Collection<ItemSale> getAllItemSales() {
        return itemSales;
    }
    
    public void addItemSale(ItemSale itemSale) {
        int stock = 0;
        try {
            stock = store.searchInventoryByItemId(itemSale.getItem().getId()).getQuantity();
        } catch (OperationFailedException ex) {
            Logger.getLogger(Bill.class.getName()).log(Level.SEVERE, null, ex);
        }
        itemSale.setMaxQuantity(stock);
        itemSales.add(itemSale);
    }
    
    public int getNumberOfServiceReceptions() {
        return serviceReceptions.size();
    }
    
    public Collection<ServiceReception> getAllServiceReceptions() {
        return serviceReceptions;
    }
    
    public void addServiceReception(ServiceReception serviceReception) {
        serviceReceptions.add(serviceReception);
    }

    /**
     * @param serviceReceptions the serviceReceptions to set
     */
    public void setServiceReceptions(Collection<ServiceReception> serviceReceptions) {
        this.serviceReceptions = serviceReceptions;
    }
    
    public Collection<BillPayment> searchPaymentsByEmployeeId(int employeeId) {
        Set<BillPayment> result = new HashSet<>();
        for (BillPayment current : payments) {
            if (current.getEmployee().getId() == employeeId) {
                result.add(current);
            }
        }
        return result;
    }
    
    public Collection<BillPayment> searchPaymentsByDate(Date date) {
        Set<BillPayment> result = new HashSet<>();
        for (BillPayment current : payments) {
            if (current.getPayDate().compareTo(date) == 0) {
                result.add(current);
            }
        }
        return result;
    }
    
    public Collection<BillPayment> searchPaymentsBetweenDates(Date startDate, Date endDate) {
        Set<BillPayment> result = new HashSet<>();
        for (BillPayment current : payments) {
            Date expenseDate = current.getPayDate();
            if (expenseDate.compareTo(startDate) >= 0 && expenseDate.compareTo(endDate) <= 0) {
                result.add(current);
            }
        }
        return result;
    }
    
    public Collection<ItemSale> searchItemSalesByItemId(String itemId) {
        Set<ItemSale> result = new HashSet<>();
        for (ItemSale current : itemSales) {
            if (StringUtils.containsIgnoreCase(current.getItem().getId(),itemId)) {
                result.add(current);
            }
        }
        return result;
    }
    
    public Collection<ItemSale> searchItemSalesByItemDescription(String itemDesc) {
        Set<ItemSale> result = new HashSet<>();
        for (ItemSale current : itemSales) {
            if (StringUtils.containsIgnoreCase(current.getItem().getDescription(),itemDesc)) {
                result.add(current);
            }
        }
        return result;
    }
    
    public Collection<ServiceReception> searchServiceReceptionsByServiceId(String serviceId) {
        Set<ServiceReception> result = new HashSet<>();
        for (ServiceReception current : serviceReceptions) {
            if (StringUtils.containsIgnoreCase(current.getService().getId(),serviceId)) {
                result.add(current);
            }
        }
        return result;
    }
    
    public Collection<ServiceReception> searchServiceReceptionsByServiceDescription(String serviceDesc) {
        Set<ServiceReception> result = new HashSet<>();
        for (ServiceReception current : serviceReceptions) {
            if (StringUtils.containsIgnoreCase(current.getService().getDescription(),serviceDesc)) {
                result.add(current);
            }
        }
        return result;
    }
    
    public Collection<ServiceReception> searchServiceReceptionsByRechargeableItemId(String rechItemId) {
        Set<ServiceReception> result = new HashSet<>();
        for (ServiceReception current : serviceReceptions) {
            if (StringUtils.containsIgnoreCase(current.getItem().getId(),rechItemId)) {
                result.add(current);
            }
        }
        return result;
    }
    
    public Collection<ServiceReception> searchServiceReceptionsByDate(Date date) {
        Set<ServiceReception> result = new HashSet<>();
        for (ServiceReception current : serviceReceptions) {
            if (current.getReceptionDate().compareTo(date) == 0) {
                result.add(current);
            }
        }
        return result;
    }
    
    public Collection<ServiceReception> searchServiceReceptionsBetweenDates(Date startDate, Date endDate) {
        Set<ServiceReception> result = new HashSet<>();
        for (ServiceReception current : serviceReceptions) {
            Date expenseDate = current.getReceptionDate();
            if (expenseDate.compareTo(startDate) >= 0 && expenseDate.compareTo(endDate) <= 0) {
                result.add(current);
            }
        }
        return result;
    }

    public void refresh() {
        try {
            employee = new EmployeeCrud().refresh(employee);
            store = new StoreCrud().refresh(store);
        } catch (PersistenceException | ClassNotFoundException ex) {
            Logger.getLogger(Bill.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Collection<Inventory> execute() throws OperationFailedException {
        Collection<Inventory> result = new HashSet<>();
        Inventory inventory;
        for (ItemSale itemSale : itemSales) {
            inventory = store.searchInventoryByItemId(itemSale.getItem().getId());
            inventory.reduceQuantityBy(itemSale.getQuantity());
            result.add(inventory);
        }
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
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
        final Bill other = (Bill) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}