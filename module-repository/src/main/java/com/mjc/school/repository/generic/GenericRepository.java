package com.mjc.school.repository.generic;

import java.util.List;

public interface GenericRepository<T> {
    T create(T t);

    List<T> readAll();

    T readById(Long id);

    T update(T t);

    Boolean deleteById(Long id);
}
