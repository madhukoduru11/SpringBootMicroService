package com.openapi.integrationtest.integrationtest.service;

import com.openapi.integration.model.Customer;
import com.openapi.integration.model.CustomerFullData;
import com.openapi.integrationtest.integrationtest.entity.CustomerEntity;
import com.openapi.integrationtest.integrationtest.helper.ApiHelper;
import com.openapi.integrationtest.integrationtest.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;


    public CustomerFullData saveCustomer(Customer customer){
        CustomerFullData customerFullData;
        CustomerEntity customerEntity = ApiHelper.convertDtoToEntity(customer);
        customerEntity =customerRepository.save(customerEntity);
        if(null != customerEntity){
            customerFullData = ApiHelper.convertEntityToDto(customerEntity);
        }else{
            customerFullData=null;
        }
       return customerFullData;
    }

    public CustomerFullData getCustomer(Long customerId){
        CustomerFullData customerFullData;
        //CustomerEntity customerEntity = ApiHelper.convertDtoToEntity(customerId);
        Optional<CustomerEntity> customerEntity =customerRepository.findById(customerId);
        if(customerEntity.isPresent()){
            customerFullData = ApiHelper.convertEntityToDto(customerEntity.get());
        }else{
            customerFullData=null;
        }
        return customerFullData;
    }

    public List<CustomerFullData> getCustomers(){
        List<CustomerFullData> customersFullData;

        List<CustomerEntity> customerEntity = customerRepository.findAll();
        if(null!=customerEntity && customerEntity.size() > 0){
            customersFullData = ApiHelper.convertEntityListToDtoList(customerEntity);
        }else{
            customersFullData = null;
        }
        return customersFullData;
    }
}
