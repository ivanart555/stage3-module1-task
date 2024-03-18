package com.mjc.school.repository.impl;

import com.mjc.school.repository.NewsRepository;
import com.mjc.school.repository.datasource.Datasource;
import com.mjc.school.repository.model.NewsModel;

import java.util.List;

public class NewsRepositoryImpl implements NewsRepository<NewsModel> {
    private final Datasource datasource = Datasource.getInstance();

    @Override
    public NewsModel create(NewsModel newsModel) {
        newsModel.setId(getNextId());
        datasource.getNews().add(newsModel);
        return newsModel;
    }

    @Override
    public List<NewsModel> readAll() {
        return datasource.getNews();
    }

    @Override
    public NewsModel readById(Long id) {
        NewsModel news = null;
        List<NewsModel> allNews = datasource.getNews();

        for (NewsModel n : allNews) {
            if (n.getId().equals(id)) news = n;
        }

        return news;
    }

    @Override
    public NewsModel update(NewsModel newsModel) {
        NewsModel news = readById(newsModel.getId());

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
                .mapToLong(NewsModel::getId)
                .max()
                .orElse(0L);

        return maxId + 1;
    }

}
