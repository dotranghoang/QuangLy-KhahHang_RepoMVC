package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.Province;
import com.codegym.service.CustomerService;
import com.codegym.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProvinceService provinceService;

    @ModelAttribute("provinces")
    public Iterable<Province> provinces(){
        return provinceService.findAll();
    }


    @RequestMapping("/list")
    public ModelAndView listCustomers(@RequestParam("s") Optional<String> s , Pageable pageable){
        Page<Customer> customers;
        if(s.isPresent()) {
            customers = customerService.findAllByNameContaining(s.get(),pageable);
        } else {
            customers = customerService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createForm() {
        ModelAndView  modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer",new Customer());
        return modelAndView;
    }

    @PostMapping("/save-customer")
    public ModelAndView saveCustomer(@ModelAttribute Customer customer ) {

        customerService.save(customer);
        ModelAndView  modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer",new Customer());
        modelAndView.addObject("message","Created customer");

        return modelAndView ;
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
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("/customer/edit");
        modelAndView.addObject("customer",customer);
        modelAndView.addObject("message","Edited Customer");
        return modelAndView;
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

        ModelAndView modelAndView = new ModelAndView("/customer/remove");
        modelAndView.addObject("message","Deleted customer");
        return modelAndView;
    }

}
