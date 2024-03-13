package com.mjc.school.controller.impl;

import com.mjc.school.controller.NewsController;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.impl.NewsServiceImpl;

import java.util.List;

public class NewsControllerImpl implements NewsController {
    private final NewsService newsService = new NewsServiceImpl();

    @Override
    public NewsDto create(NewsDto newsDto) {
        return newsService.create(newsDto);
    }

    @Override
    public List<NewsDto> findAll() {
        return newsService.findAll();
    }

    @Override
    public NewsDto findById(Long id) {
        return newsService.findById(id);
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
