package com.example.demo.service;

import com.example.demo.model.Customer;
import com.example.demo.model.Users;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    private UserRepository userRepository;
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    @PostMapping
    public Customer saveCustomer(Customer customer) {
        Users user = customer.getUserAccount();

        if (user != null && user.getId() == null) {
            // Save the user if it hasn't been saved yet (null ID means it hasn't been persisted)
            userRepository.save(user);
        }
        return customer;
    }
    public Customer findCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public Customer updateCustomer(Long id, Customer customerDetails) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        customer.setUserName(customerDetails.getUserName());
        customer.setEmail(customerDetails.getEmail());
        customer.setPhoneNumber(customerDetails.getPhoneNumber());
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        customerRepository.delete(customer);
    }
}