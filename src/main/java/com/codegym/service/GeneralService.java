package com.codegym.service;

import java.util.List;

public interface GeneralService<E> {

    void save(E e);
    void remove(Long e);
}
