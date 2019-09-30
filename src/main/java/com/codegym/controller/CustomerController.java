package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    Environment env;

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/list")
    public ModelAndView getAllCustomer() {
        List<Customer> customerList = customerService.findAll();

        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("customers",customerList);

        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createForm() {
        ModelAndView  modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customerForm",new Customer());
        return modelAndView;
    }

    @PostMapping("/save-customer")
    public ModelAndView saveCustomer(@ModelAttribute Customer customer ) {

        Customer customerObject = new Customer(customer.getName(),customer.getAddress(),customer.getPhone());
        customerService.add(customerObject);

       return getAllCustomer();
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editCustomer(@PathVariable Long id) {
        Customer customer = customerService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/customer/edit");
        modelAndView.addObject("customer",customer);
        return modelAndView;
    }

    @PostMapping("/edit-customer")
    public ModelAndView updateCustomer(@ModelAttribute("customer")Customer customer) {
        customerService.edit(customer);
        return getAllCustomer();
    }

    @GetMapping("/remove/{id}")
    public ModelAndView sure(@PathVariable Long id) {
        Customer customer = customerService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/customer/remove");
        modelAndView.addObject("customer",customer);
        return modelAndView;
    }

    @PostMapping("/remove-customer/{id}")
    public ModelAndView removeCustomer(@PathVariable Long id){
        customerService.remove(id);
        return getAllCustomer();
    }

}
