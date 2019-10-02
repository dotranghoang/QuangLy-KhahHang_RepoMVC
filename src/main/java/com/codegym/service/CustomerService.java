package com.codegym.service;

import com.codegym.model.Customer;
import com.codegym.model.Province;

public interface CustomerService extends GeneralService<Customer> {
    Customer findById(Long id);
    Iterable<Customer> findAllByProvince(Province province);
}
