package com.mjc.school.repository.impl;

import com.mjc.school.repository.NewsRepository;
import com.mjc.school.repository.datasource.Datasource;
import com.mjc.school.repository.model.News;

import java.util.List;
import java.util.Optional;

public class NewsRepositoryImpl implements NewsRepository {
    private final Datasource datasource = Datasource.getInstance();

    @Override
    public News create(News newsModel) {
        newsModel.setId(getNextId());
        datasource.getNews().add(newsModel);

        return newsModel;
    }

    @Override
    public List<News> findAll() {
        return datasource.getNews();
    }

    @Override
    public News findById(Long id) {
        Optional<News> optionalNews = datasource.getNews().stream()
                .filter(news -> news.getId().equals(id))
                .findFirst();

        return optionalNews.orElse(null);
    }

    @Override
    public News update(News newsModel) {
        News news = findById(newsModel.getId());

        news.setTitle(newsModel.getTitle());
        news.setContent(newsModel.getContent());
        news.setAuthorId(news.getAuthorId());
        news.setLastUpdateTime(newsModel.getLastUpdateTime());

        return news;
    }

    @Override
    public Boolean deleteById(Long id) {
        return datasource.getNews().removeIf(news -> news.getId().equals(id));
    }

    private synchronized Long getNextId() {
        long maxId = datasource.getNews().stream()
                .mapToLong(News::getId)
                .max()
                .orElse(0L);

        return maxId + 1;
    }
}
