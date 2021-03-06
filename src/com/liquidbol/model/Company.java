package com.liquidbol.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;

/**
 * Class that represents LiquidBol company.
 *
 * @author Allan Leon
 */
public class Company implements Serializable {

    private static Collection<Client> clients;
    private static Collection<Item> items;
    private static Collection<Service> services;
    private static Collection<Offer> offers;
    private static Collection<Store> stores;
    private static Collection<Supplier> suppliers;
    private static Employee loggedEmployee;

    /**
     * Constructor method.
     */
    static {
        clients = new HashSet<>();
        items = new HashSet<>();
        services = new HashSet<>();
        offers = new HashSet<>();
        stores = new HashSet<>();
        suppliers = new HashSet<>();
        loggedEmployee = new Employee(0);
    }

    /**
     * @return the loggedEmployee
     */
    public static Employee getLoggedEmployee() {
        return loggedEmployee;
    }

    /**
     * @return the clients
     */
    public static Collection<Client> getAllClients() {
        return clients;
    }

    /**
     * @return the items
     */
    public static Collection<Item> getAllItems() {
        return items;
    }

    /**
     * @return the services
     */
    public static Collection<Service> getAllServices() {
        return services;
    }

    /**
     * @return the offers
     */
    public static Collection<Offer> getAllOffers() {
        return offers;
    }

    /**
     * @return the stores
     */
    public static Collection<Store> getAllStores() {
        return stores;
    }

    /**
     * @return the suppliers
     */
    public static Collection<Supplier> getAllSuppliers() {
        return suppliers;
    }

    /**
     * @return the employees of all stores
     */
    public static Collection<Employee> getAllEmployees() {
        Collection<Employee> result = new HashSet<>();
        for (Store store : stores) {
            result.addAll(store.getAllEmployees());
        }
        return result;
    }

    /**
     * @return the rechargeable items of all clients
     */
    public static Collection<RechargeableItem> getAllRechargeableItems() {
        Collection<RechargeableItem> result = new HashSet<>();
        for (Client client : clients) {
            result.addAll(client.getAllRechargeableItems());
        }
        return result;
    }

    /**
     * @param employee the loggedEmployee to set
     */
    public static void setLoggedEmployee(Employee employee) {
        Company.loggedEmployee = employee;
    }

    /**
     * @param clients the clients to set
     */
    public static void setClients(Collection<Client> clients) {
        Company.clients = clients;
    }

    /**
     * @param items the items to set
     */
    public static void setItems(Collection<Item> items) {
        Company.items = items;
    }

    /**
     * @param services the services to set
     */
    public static void setServices(Collection<Service> services) {
        Company.services = services;
    }

    /**
     * @param offers the offers to set
     */
    public static void setOffers(Collection<Offer> offers) {
        Company.offers = offers;
    }

    /**
     * @param stores the stores to set
     */
    public static void setStores(Collection<Store> stores) {
        Company.stores = stores;
    }

    /**
     * @param suppliers the suppliers to set
     */
    public static void setSuppliers(Collection<Supplier> suppliers) {
        Company.suppliers = suppliers;
    }

    public static void addClient(Client client) {
        clients.add(client);
    }

    public static void addItem(Item item) {
        items.add(item);
    }

    public static void addService(Service service) {
        services.add(service);
    }

    public static void addOffer(Offer offer) {
        offers.add(offer);
    }

    public static void addStore(Store store) {
        stores.add(store);
    }

    public static void addSupplier(Supplier supplier) {
        suppliers.add(supplier);
    }

    public static Client findClientByNit(int nit) throws OperationFailedException {
        for (Client client : clients) {
            if (client.getNit() == nit) {
                return client;
            }
        }
        throw new OperationFailedException(String.format("Client nit: %d not found", nit));
    }

    public static Item findItemById(String id) throws OperationFailedException {
        for (Item item : items) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        throw new OperationFailedException(String.format("Item: %s not found", id));
    }

    public static Store findStoreById(int id) throws OperationFailedException {
        for (Store store : stores) {
            if (store.getId() == id) {
                return store;
            }
        }
        throw new OperationFailedException(String.format("Store: %d not found", id));
    }

    public static Employee findEmployeeById(int id) throws OperationFailedException {
        for (Store store : stores) {
            for (Employee current : store.getAllEmployees()) {
                if (current.getId() == id) {
                    return current;
                }
            }
        }
        throw new OperationFailedException(String.format("Employee id: %s not found", id));
    }

    public static RechargeableItem findRechargeableItemById(String id) throws OperationFailedException {
        id.trim();
        for (Client client : clients) {
            for (RechargeableItem current : client.getAllRechargeableItems()) {
                if (StringUtils.equalsIgnoreCase(current.getId(), id)) {
                    return current;
                }
            }
        }
        throw new OperationFailedException(String.format("Rechargeable item id: %s not found", id));
    }

    public static Collection<Supplier> searchSuppliersByName(String name) {
        Set<Supplier> result = new HashSet<>();
        for (Supplier current : suppliers) {
            String fullName = current.getName() + " " + current.getLastname();
            if (StringUtils.containsIgnoreCase(fullName, name)) {
                result.add(current);
            }
        }
        return result;
    }

    public static Collection<Supplier> searchSuppliersByCompany(String company) {
        Set<Supplier> result = new HashSet<>();
        for (Supplier current : suppliers) {
            if (StringUtils.containsIgnoreCase(current.getCompany(), company)) {
                result.add(current);
            }
        }
        return result;
    }

    public static Collection<Supplier> searchSuppliersByCity(String city) {
        Set<Supplier> result = new HashSet<>();
        for (Supplier current : suppliers) {
            if (StringUtils.containsIgnoreCase(current.getCity(), city)) {
                result.add(current);
            }
        }
        return result;
    }

    public static Collection<Item> searchItemsById(String id) {
        Set<Item> result = new HashSet<>();
        for (Item current : items) {
            if (StringUtils.containsIgnoreCase(current.getId(), id)) {
                result.add(current);
            }
        }
        return result;
    }

    public static Collection<Item> searchItemsByDescription(String description) {
        Set<Item> result = new HashSet<>();
        for (Item current : items) {
            if (StringUtils.containsIgnoreCase(current.getDescription(), description)) {
                result.add(current);
            }
        }
        return result;
    }

    public static Collection<Item> searchItemsByBrand(String brand) {
        Set<Item> result = new HashSet<>();
        for (Item current : items) {
            if (StringUtils.containsIgnoreCase(current.getBrand(), brand)) {
                result.add(current);
            }
        }
        return result;
    }

    public static Collection<Item> searchItemsByIndustry(String industry) {
        Set<Item> result = new HashSet<>();
        for (Item current : items) {
            if (StringUtils.containsIgnoreCase(current.getIndustry(), industry)) {
                result.add(current);
            }
        }
        return result;
    }

    public static Collection<Item> searchItemsByType(String type) {
        Set<Item> result = new HashSet<>();
        for (Item current : items) {
            if (StringUtils.containsIgnoreCase(current.getType(), type)) {
                result.add(current);
            }
        }
        return result;
    }

    public static Collection<Item> searchItemsBySubtype(String subtype) {
        Set<Item> result = new HashSet<>();
        for (Item current : items) {
            if (StringUtils.containsIgnoreCase(current.getSubtype(), subtype)) {
                result.add(current);
            }
        }
        return result;
    }

    public static Collection<Offer> searchOffersByDate(Date date) {
        Set<Offer> result = new HashSet<>();
        for (Offer current : offers) {
            Date offerStartDate = current.getStartDate();
            Date offerEndDate = current.getEndDate();
            if (offerStartDate.compareTo(date) <= 0 && offerEndDate.compareTo(date) >= 0) {
                result.add(current);
            }
        }
        return result;
    }

    public static Collection<Offer> searchOffersByType(String type) {
        Set<Offer> result = new HashSet<>();
        for (Offer current : offers) {
            if (StringUtils.containsIgnoreCase(current.getType(), type)) {
                result.add(current);
            }
        }
        return result;
    }

    public static Collection<Store> searchStoresByName(String name) {
        Set<Store> result = new HashSet<>();
        for (Store current : stores) {
            if (StringUtils.containsIgnoreCase(current.getName(), name)) {
                result.add(current);
            }
        }
        return result;
    }

    public static Collection<Client> searchClientsById(String id) {
        Set<Client> result = new HashSet<>();
        for (Client current : clients) {
            if (StringUtils.containsIgnoreCase(String.valueOf(current.getId()), id)) {
                result.add(current);
            }
        }
        return result;
    }

    public static Collection<Client> searchClientsByName(String name) {
        Set<Client> result = new HashSet<>();
        for (Client current : clients) {
            String fullName = current.getName() + " " + current.getLastname();
            if (StringUtils.containsIgnoreCase(fullName, name)) {
                result.add(current);
            }
        }
        return result;
    }

    public static Collection<Client> searchClientsByNit(String nit) {
        Set<Client> result = new HashSet<>();
        for (Client current : clients) {
            if (StringUtils.containsIgnoreCase(String.valueOf(current.getNit()), nit)) {
                result.add(current);
            }
        }
        return result;
    }

    public static Collection<Client> searchClientsByBillName(String billName) {
        Set<Client> result = new HashSet<>();
        for (Client current : clients) {
            if (StringUtils.containsIgnoreCase(current.getBillName(), billName)) {
                result.add(current);
            }
        }
        return result;
    }

    public static Collection<Client> searchClientsByRoute(boolean route) {
        Set<Client> result = new HashSet<>();
        for (Client current : clients) {
            if (current.isRoute() == route) {
                result.add(current);
            }
        }
        return result;
    }

    public static Collection<Service> searchServicesById(String id) {
        Set<Service> result = new HashSet<>();
        for (Service current : services) {
            if (StringUtils.containsIgnoreCase(current.getId(), id)) {
                result.add(current);
            }
        }
        return result;
    }

    public static Collection<Service> searchServicesByDescription(String description) {
        Set<Service> result = new HashSet<>();
        for (Service current : services) {
            if (StringUtils.containsIgnoreCase(current.getDescription(), description)) {
                result.add(current);
            }
        }
        return result;
    }

    public static Collection<Service> searchServicesByType(String type) {
        Set<Service> result = new HashSet<>();
        for (Service current : services) {
            if (StringUtils.containsIgnoreCase(current.getType(), type)) {
                result.add(current);
            }
        }
        return result;
    }
}
