package com.mjc.school.repository;

import java.util.List;

public interface NewsRepository<T> {
    T create(T t);

    List<T> readAll();

    T readById(Long id);

    T update(T t);

    Boolean deleteById(Long id);
}
