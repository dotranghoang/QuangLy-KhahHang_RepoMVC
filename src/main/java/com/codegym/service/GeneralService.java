package com.codegym.service;

import java.util.List;

public interface GeneralService<E> {
    List<E> findAll();

    void add(E e);

    void edit(E e);

    void remove(Long e);
}
