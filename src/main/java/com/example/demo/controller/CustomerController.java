package com.example.demo.controller;

import com.example.demo.model.Customer;
import com.example.demo.model.Users;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    private UserRepository userRepository;

    @GetMapping("/getAllCustomers")
    public List<Customer> getAllCustomers() {
        return customerService.findAllCustomers();
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        try {
            Users user = customer.getUserAccount();

            // Check if the user is not null and hasn't been persisted (ID is null)
            if (user != null && user.getId() == null) {
                // Save the user if it hasn't been saved yet
                userRepository.save(user);
            }

            // Now save the customer with the persisted user
            Customer savedCustomer = customerService.saveCustomer(customer);

            return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAgentById/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerService.findCustomerById(id);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetails) {
        return ResponseEntity.ok(customerService.updateCustomer(id, customerDetails));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }
}
