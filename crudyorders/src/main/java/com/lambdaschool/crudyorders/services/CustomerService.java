package com.lambdaschool.crudyorders.services;

import com.lambdaschool.crudyorders.models.Customers;

import java.util.List;

public interface CustomerService {
    List<Customers> findAllCustomers();

    Customers findByCustomerName(String custname);

    Customers findCustomersById(long id);

    Customers save(Customers customer);

    Customers update(Customers customer, long id);

    void delete(long id);
}
