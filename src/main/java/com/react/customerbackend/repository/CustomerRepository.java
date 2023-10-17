package com.react.customerbackend.repository;

import com.react.customerbackend.config.CustomerDetails;
import com.react.customerbackend.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByName(String userName);
}
