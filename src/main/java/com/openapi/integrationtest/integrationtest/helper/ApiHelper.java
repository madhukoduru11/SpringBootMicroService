package com.openapi.integrationtest.integrationtest.helper;

import com.openapi.integration.model.Customer;
import com.openapi.integration.model.CustomerFullData;
import com.openapi.integrationtest.integrationtest.entity.CustomerEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ApiHelper {

    public static CustomerEntity convertDtoToEntity(Customer customer) {
        ModelMapper modelMapper = new ModelMapper();
        CustomerEntity customerDTO = modelMapper.map(customer, CustomerEntity.class);
        return customerDTO;
    }

    public static CustomerFullData convertEntityToDto(CustomerEntity customerEntity) {
        ModelMapper modelMapper = new ModelMapper();
        CustomerFullData customerFull = modelMapper.map(customerEntity, CustomerFullData.class);
        return customerFull;
    }
    public static List<CustomerFullData> convertEntityListToDtoList(List<CustomerEntity> customerEntitys) {
        List<CustomerFullData> customerFullDataList = customerEntitys.stream().map(customerEntity ->convertEntityToDto(customerEntity)).collect(Collectors.toList());
        return customerFullDataList;
    }
    public static List<CustomerEntity> convertDtoListToEntityList(List<Customer> customers) {
        List<CustomerEntity> customerEntityList = customers.stream().map(customer ->convertDtoToEntity(customer)).collect(Collectors.toList());
        return customerEntityList;
    }
}
