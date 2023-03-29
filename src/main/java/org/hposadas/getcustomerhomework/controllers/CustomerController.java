package org.hposadas.getcustomerhomework.controllers;


import lombok.RequiredArgsConstructor;
import org.hposadas.getcustomerhomework.models.Customer;
import org.hposadas.getcustomerhomework.services.Customerservice;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor        //to generate constructor injecting service
@RequestMapping("/api/customer/v1")
public class CustomerController {

    //atributos
    private final Customerservice customerservice;

    //métodos
    @GetMapping()
    public List<Customer> getCustomerList(){

        return this.customerservice.getCustomers();
    }

    @GetMapping("/{customerId}")
    public Customer getCustomerById(@PathVariable("customerId")UUID id){

        return this.customerservice.getCustomerById(id);
    }

    @PostMapping
    public ResponseEntity handlerPost (@RequestBody Customer customer) {

        Customer savedCustomer = this.customerservice.saveNewCustomer(customer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/customer/v1" + savedCustomer.getId());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity updateCustomerById(@PathVariable("customerId") UUID id, @RequestBody Customer customer){

        this.customerservice.updateCustomerById(id, customer);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
