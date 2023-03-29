package org.hposadas.getcustomerhomework.controllers;


import org.hposadas.getcustomerhomework.models.Customer;
import org.hposadas.getcustomerhomework.services.Customerservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/customer/v1")
public class CustomerController {

    //atributos
    @Autowired
    private Customerservice customerservice;

    //métodos
    @GetMapping()
    public List<Customer> getCustomerList(){

        return this.customerservice.getCustomers();
    }

    @GetMapping("/{customerId}")
    public Customer getCustomerById(@PathVariable("customerId")UUID id){

        return this.customerservice.getCustomerById(id);
    }

}
