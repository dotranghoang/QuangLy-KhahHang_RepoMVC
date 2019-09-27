package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Controller;
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

        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("customer",new Customer());

        return modelAndView;
    }

    @GetMapping("/edit")
    public ModelAndView editCustomer() {
        ModelAndView modelAndView = new ModelAndView("/customer/edit");
        modelAndView.addObject("editCustomer", new Customer());
        return modelAndView;
    }

    @PostMapping("/edit-customer")
    public ModelAndView updateCustomer(@ModelAttribute Customer customer) {
        Customer customerObject = new Customer(customer.getId(),customer.getName(),customer.getAddress(),customer.getPhone());
        customerService.edit(customerObject);

        ModelAndView modelAndView = new ModelAndView("/customer/list");
        return modelAndView;
    }

    @GetMapping("/remove")
    public ModelAndView removeCustomer() {
        ModelAndView modelAndView = new ModelAndView("/customer/remove");
        modelAndView.addObject("id");
        return modelAndView;
    }

    @PostMapping("/customer/remove-customer")
    public ModelAndView removeCustomer(@RequestParam long id ) {
        customerService.remove(id);
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        return modelAndView;
    }

}
