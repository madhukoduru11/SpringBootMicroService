package com.openapi.integrationtest.integrationtest.containertesting;

import com.openapi.integrationtest.integrationtest.entity.CustomerEntity;
import com.openapi.integrationtest.integrationtest.repository.CustomerRepository;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
//@Testcontainers
public class CustomerContainerIntegrationTest extends AbstractContainerBaseTest{

    // given/when/then format - BDD style
    @Autowired
    MockMvc mockMvc;
    @Autowired
    private CustomerRepository customerRepository;
    /*@Container
    MySQLContainer MY_SQL_CONTAINER = new MySQLContainer("mysql:latest");
*/    @Test
    public void givenCustomer_whenGetAllCustomers_thenListOfCustomer() throws Exception {
        List<CustomerEntity> customerEntities = List.of(CustomerEntity.builder().firstName("Madhu").lastName("koduru").build(),
                CustomerEntity.builder().firstName("shirisha").lastName("koduru").build(),
                CustomerEntity.builder().firstName("josh").lastName("koduru").build(),
                CustomerEntity.builder().firstName("sri aadya").lastName("koduru").build()
                );
        customerRepository.saveAll(customerEntities);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/customer/findAll"));

        // Test case to validate bulk customer records are persisting properly or not
        response.andExpect(MockMvcResultMatchers.status().isOk());
        response.andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(customerEntities.size()+4)));

        // Test case to valid date customer details are saved or not
        ResultActions singleCustomerResponse = mockMvc.perform(MockMvcRequestBuilders.get("/customer/1"));
        singleCustomerResponse.andExpect(MockMvcResultMatchers.status().isOk());
        singleCustomerResponse.andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Madhusudhana"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Koduru"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerId").value("1"));




    }

}
