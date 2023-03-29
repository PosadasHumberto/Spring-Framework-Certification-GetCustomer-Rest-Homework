package org.hposadas.getcustomerhomework.services;

import org.hposadas.getcustomerhomework.models.Customer;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class CustomerserviceImpl implements Customerservice {

    //atributos
    private Map<UUID, Customer> customerMap;

    //constructor

    public CustomerserviceImpl() {
        this.customerMap = new HashMap<>();

        Customer customer1 = Customer.builder()
                .id(UUID.randomUUID())
                .customerName("Humberto Posadas")
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        Customer customer2 = Customer.builder()
                .id(UUID.randomUUID())
                .customerName("Jaimes Hernandez")
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        Customer customer3 = Customer.builder()
                .id(UUID.randomUUID())
                .customerName("Helena Cruz")
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        this.customerMap.put(customer1.getId(), customer1);
        this.customerMap.put(customer2.getId(), customer2);
        this.customerMap.put(customer3.getId(), customer3);
    }

    //métodos
    @Override
    public List<Customer> getCustomers() {
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public Customer getCustomerById(UUID id) {
        return this.customerMap.get(id);
    }

    @Override
    public Customer saveNewCustomer(Customer customer) {

        Customer tempCustomer = Customer.builder()
                .id(UUID.randomUUID())
                .customerName(customer.getCustomerName())
                .version(customer.getVersion())
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        this.customerMap.put(tempCustomer.getId(), tempCustomer);
        return tempCustomer;
    }

    @Override
    public void updateCustomerById(UUID id, Customer customer) {

        Customer tempCustomer = this.getCustomerById(UUID.fromString(id.toString()));
        tempCustomer.setCustomerName(customer.getCustomerName() != null? customer.getCustomerName() : tempCustomer.getCustomerName());
        tempCustomer.setVersion(customer.getVersion() != null? customer.getVersion() : tempCustomer.getVersion());
        tempCustomer.setLastModifiedDate(LocalDateTime.now());

        this.customerMap.put(id, tempCustomer);

    }
}
