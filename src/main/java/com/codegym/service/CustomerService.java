package com.codegym.service;

import com.codegym.model.Customer;
import com.codegym.model.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService extends GeneralService<Customer> {
    Page<Customer> findAll(Pageable pageable);
    Customer findById(Long id);
    Iterable<Customer> findAllByProvince(Province province);
    Page<Customer> findAllByNameContaining(String name,Pageable pageable);
}
