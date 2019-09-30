package com.codegym.service.Impl;

import com.codegym.model.Customer;
import com.codegym.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class CustomerServiceImpl implements com.codegym.service.CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public void add(Customer customer) {
        customerRepository.add(customer);
    }

    @Override
    public void edit(Customer customer) {
        customerRepository.edit(customer);
    }

    @Override
    public void remove(Long e) {
        customerRepository.remove(e);
    }


    @Override
    public Customer findById(Long id) {
      return customerRepository.findById(id);
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }


}
