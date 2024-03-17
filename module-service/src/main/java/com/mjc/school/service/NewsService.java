package com.mjc.school.service;

import java.util.List;

public interface NewsService<T> {
    T create(T t);

    List<T> readAll();

    T readById(Long id);

    T update(T t);

    Boolean deleteById(Long id);
}
