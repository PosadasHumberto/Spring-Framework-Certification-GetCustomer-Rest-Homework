package org.hposadas.getcustomerhomework.services;

import org.hposadas.getcustomerhomework.models.Customer;

import java.util.List;
import java.util.UUID;

public interface Customerservice {

    //firmas de métodos
    public List<Customer> getCustomers();
    public Customer getCustomerById(UUID id);
}
