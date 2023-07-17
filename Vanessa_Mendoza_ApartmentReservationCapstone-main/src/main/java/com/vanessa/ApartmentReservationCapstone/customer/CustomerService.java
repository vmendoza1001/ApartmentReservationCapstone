package com.vanessa.ApartmentReservationCapstone.customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomers();

    Customer getCustomerById(int customerId);

    void saveOrUpdateCustomer(Customer customer);

    void deleteCustomer(int customerId);
}
