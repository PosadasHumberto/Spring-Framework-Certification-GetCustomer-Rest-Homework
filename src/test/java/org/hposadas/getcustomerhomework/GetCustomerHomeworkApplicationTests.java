package org.hposadas.getcustomerhomework;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hposadas.getcustomerhomework.models.Customer;
import org.hposadas.getcustomerhomework.services.Customerservice;
import org.hposadas.getcustomerhomework.services.CustomerserviceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.hamcrest.core.Is.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest
class GetCustomerHomeworkApplicationTests {

    //atributos
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    Customerservice customerservice;

    CustomerserviceImpl customerserviceImpl;

    //métodos
    @BeforeEach
    void setUp() {
        customerserviceImpl = new CustomerserviceImpl();
    }

    @Test
    void getCustomerList() throws Exception {

        given(customerservice.getCustomers()).willReturn(customerserviceImpl.getCustomers());

        mockMvc.perform(get("/api/customer/v1").accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.length()",is(customerserviceImpl.getCustomers().size())));
    }

    @Test
    void getCustomerById() throws Exception {

        Customer customer = customerserviceImpl.getCustomers().get(0);

        given(customerservice.getCustomerById(any(UUID.class))).willReturn(customer);

        mockMvc.perform(get("/api/customer/v1/" + customer.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(customer.getId().toString())))
                .andExpect(jsonPath("$.customerName", is(customer.getCustomerName())));
    }

    @Test
    void handlerPost() throws Exception {

        Customer customer = Customer.builder()
                .id(UUID.randomUUID())
                .customerName("Johenne Dark")
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        given(customerservice.saveNewCustomer(any(Customer.class))).willReturn(customer);

        mockMvc.perform(post("/api/customer/v1")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().is(201))
                .andExpect(header().exists("Location"));
    }
}
