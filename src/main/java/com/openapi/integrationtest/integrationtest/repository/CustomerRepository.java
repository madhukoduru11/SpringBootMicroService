package com.openapi.integrationtest.integrationtest.repository;

import com.openapi.integrationtest.integrationtest.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity,Long> {
}
