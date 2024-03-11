package com.mjc.school.repository.generic;

import java.util.List;

public interface GenericRepository<T> {
    T create(T t);

    List<T> findAll();

    T findById(Long id);

    T update(T t);

    Boolean delete(Long id);
}
