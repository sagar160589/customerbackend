package com.react.customerbackend.config;

import com.react.customerbackend.entity.Customer;
import com.react.customerbackend.exception.CustomerNotFoundException;
import com.react.customerbackend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customer> customer = customerRepository.findByName(username);
        return customer.map(CustomerDetails::new).
                orElseThrow(() -> new CustomerNotFoundException("username not found "+username));
    }
}
