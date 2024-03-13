package com.mjc.school.controller.generic;

import java.util.List;

public interface GenericController<T> {
    T create(T t);

    List<T> findAll();

    T findById(Long id);

    T update(T t);

    Boolean deleteById(Long id);
}
