package com.react.customerbackend.controller;

import com.react.customerbackend.entity.Customer;
import com.react.customerbackend.exception.CustomerNotFoundException;
import com.react.customerbackend.repository.CustomerRepository;
import com.react.customerbackend.service.CustomerService;
import com.react.customerbackend.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @PostMapping("/customer")
    public ResponseEntity<String> addData(@RequestBody Customer customer){
        return customerService.addData(customer);
    }

    @GetMapping("/customers")
    public List<Customer> getdetails(){
        return customerRepository.findAll();
    }

    @GetMapping("/customer/{id}")
    public Customer getCustomer(@PathVariable Long id){
        return customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("customer not found "+id));
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<String> updateDetails(@PathVariable Long id, @RequestBody Customer custDetails){
        Customer customer = customerRepository.findById(id).orElseThrow(()-> new CustomerNotFoundException("Not Found"));
        System.out.println(custDetails.getName());
        customer.setEmailId(custDetails.getEmailId());
        customer.setName(custDetails.getName());
        customerRepository.save(customer);
        return ResponseEntity.status(HttpStatus.OK).body("Successfully updated");
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id){
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Not Found"));
        customerRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted");
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody Customer customer){

        Authentication authentication  =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(customer.getName(), customer.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.generate_token(customer.getName());
        }else{
            return "invalid username!!";
        }
    }


}
