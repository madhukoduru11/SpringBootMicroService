package com.openapi.integrationtest.integrationtest.integration;

import com.openapi.integration.model.Customer;
import com.openapi.integrationtest.integrationtest.entity.CustomerEntity;
import  com.openapi.integrationtest.integrationtest.service.CustomerService;
import com.openapi.integrationtest.integrationtest.repository.CustomerRepository;
import com.openapi.integrationtest.integrationtest.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerIntegration {
    @Autowired
    CustomerService customerService;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void customer_insertion_test() throws Exception {
        List<CustomerEntity> customerEntities=List.of(CustomerEntity.builder().firstName("Hari").lastName("Koduru").build(),
                CustomerEntity.builder().firstName("Amma").lastName("Koduru").build()
                );
        //customerRepository.saveAll(customerEntities);
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/customer/1"));
        response.andExpect(MockMvcResultMatchers.status().isOk());
        response.andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Madhusudhana"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Koduru"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerId").value("1"));
    }
}
