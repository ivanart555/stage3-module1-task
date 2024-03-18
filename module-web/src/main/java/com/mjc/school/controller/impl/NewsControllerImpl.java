package com.mjc.school.controller.impl;

import com.mjc.school.controller.NewsController;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.impl.NewsServiceImpl;

import java.util.List;

public class NewsControllerImpl implements NewsController {
    private final NewsService<NewsDto> newsService = new NewsServiceImpl();

    @Override
    public NewsDto create(NewsDto newsDto) {
        return newsService.create(newsDto);
    }

    @Override
    public List<NewsDto> readAll() {
        return newsService.readAll();
    }

    @Override
    public NewsDto readById(Long id) {
        return newsService.readById(id);
    }

    @Override
    public NewsDto update(NewsDto newsDto) {
        return newsService.update(newsDto);
    }

    @Override
    public Boolean deleteById(Long id) {
        return newsService.deleteById(id);
    }
}
