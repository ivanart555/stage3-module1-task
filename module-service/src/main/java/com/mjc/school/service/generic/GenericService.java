package com.mjc.school.service.generic;

import java.util.List;

public interface GenericService<T> {
    T create(T t);

    List<T> findAll();

    T findById(Long id);

    T update(T t);

    Boolean deleteById(Long id);

}
