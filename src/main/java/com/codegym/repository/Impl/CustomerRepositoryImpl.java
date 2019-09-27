package com.codegym.repository.Impl;

import com.codegym.model.Customer;
import com.codegym.repository.CustomerRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
public class CustomerRepositoryImpl implements CustomerRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Customer> findAll() {
        TypedQuery<Customer> query = em.createQuery("select p from Customer p",Customer.class);

        return query.getResultList();
    }

    @Override
    public void add(Customer customer) {
            em.persist(customer);
    }

    @Override
    public void edit(Customer customer) {
        em.merge(customer);
    }

    @Override
    public void remove(Long id) {
        em.remove(id);
    }
}
