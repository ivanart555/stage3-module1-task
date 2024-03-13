package com.mjc.school.service.impl;

import com.mjc.school.repository.NewsRepository;
import com.mjc.school.repository.impl.NewsRepositoryImpl;
import com.mjc.school.repository.model.News;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.mapper.NewsMapper;

import java.time.LocalDateTime;
import java.util.List;

public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository = new NewsRepositoryImpl();

    @Override
    public NewsDto create(NewsDto newsDto) {
        News news = NewsMapper.INSTANCE.newsDtoToNews(newsDto);

        LocalDateTime date = LocalDateTime.now();
        news.setCreateDate(date);
        news.setLastUpdateTime(date);

        return NewsMapper.INSTANCE.newsToNewsDto(newsRepository.create(news));
    }

    @Override
    public List<NewsDto> findAll() {
        return NewsMapper.INSTANCE.newsListToNewsDtoList(newsRepository.findAll());
    }

    @Override
    public NewsDto findById(Long id) {
        return NewsMapper.INSTANCE.newsToNewsDto(newsRepository.findById(id));
    }

    @Override
    public NewsDto update(NewsDto newsDto) {
        News news = NewsMapper.INSTANCE.newsDtoToNews(newsDto);

        LocalDateTime date = LocalDateTime.now();
        news.setLastUpdateTime(date);

        return NewsMapper.INSTANCE.newsToNewsDto(newsRepository.update(news));
    }

    @Override
    public Boolean deleteById(Long id) {
        return newsRepository.deleteById(id);
    }
}
