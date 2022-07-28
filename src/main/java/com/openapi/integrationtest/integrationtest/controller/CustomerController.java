package com.openapi.integrationtest.integrationtest.controller;

import com.openapi.integration.api.CustomerApi;
import com.openapi.integration.model.Customer;
import com.openapi.integration.model.CustomerFullData;
import com.openapi.integrationtest.integrationtest.service.CustomerService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CustomerController implements CustomerApi {
    @Autowired
    CustomerService customerService;
    @Override
    public ResponseEntity<CustomerFullData> createCustomer(@RequestBody(required = false) Customer customer) {
        ResponseEntity<CustomerFullData> responseEntity;
        /*CustomerFullData fullCustomer = new CustomerFullData();
        fullCustomer.setCustomerId(Long.valueOf(12));
        fullCustomer.setFirstName("Madhu");
        fullCustomer.setLastName("Koduru");*/
        CustomerFullData customerFullData =customerService.saveCustomer(customer);
        if(null ==customerFullData){
            responseEntity = new ResponseEntity<CustomerFullData>(HttpStatus.NOT_IMPLEMENTED);
        }else{
            responseEntity = new ResponseEntity<CustomerFullData>(customerFullData, HttpStatus.OK);
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity<CustomerFullData> getCustomer(@PathVariable("customerId") Long customerId) {
        ResponseEntity<CustomerFullData> responseEntity;
        /*        CustomerFullData fullCustomer = new CustomerFullData();
        fullCustomer.setCustomerId(Long.valueOf(12));
        fullCustomer.setFirstName("Madhu");
        fullCustomer.setLastName("Koduru");*/
        CustomerFullData customerFullData=customerService.getCustomer(customerId);
        if(null ==customerFullData){
            responseEntity = new ResponseEntity<CustomerFullData>(HttpStatus.NOT_IMPLEMENTED);
        }else{
            responseEntity = new ResponseEntity<CustomerFullData>(customerFullData, HttpStatus.OK);
        }
        return responseEntity;
    }
  /*  @Override
    public ResponseEntity<Customer> createCustomer(@RequestBody(required = false) Customer customer) {
        Customer fullCustomer = new Customer();
        //fullCustomer.setCustomerId(Long.valueOf(12));
        fullCustomer.setFirstName("Madhu");
        fullCustomer.setLastName("Koduru");
        ResponseEntity<Customer> responseEntity = new ResponseEntity<Customer>(fullCustomer, HttpStatus.OK);
        return responseEntity;
    }

    @Override
    public ResponseEntity<Customer> getCustomer(@PathVariable("customerId") Long customerId) {
        Customer fullCustomer = new Customer();
       // fullCustomer.setCustomerId(Long.valueOf(12));
        fullCustomer.setFirstName("Madhu");
        fullCustomer.setLastName("Koduru");
        ResponseEntity<Customer> responseEntity = new ResponseEntity<Customer>(fullCustomer, HttpStatus.OK);
        return responseEntity;
    }*/

    public ResponseEntity<List<CustomerFullData>> getCustomers(){
        ResponseEntity<List<CustomerFullData>> responseEntity;
        List<CustomerFullData> customerFullData=customerService.getCustomers();
        if(null ==customerFullData){
            responseEntity = new ResponseEntity<List<CustomerFullData>>(HttpStatus.NOT_IMPLEMENTED);
        }else{
            responseEntity = new ResponseEntity<List<CustomerFullData>>(customerFullData, HttpStatus.OK);
        }
        return responseEntity;
    }

}
