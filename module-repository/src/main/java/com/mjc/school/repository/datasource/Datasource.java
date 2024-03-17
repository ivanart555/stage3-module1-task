package com.mjc.school.repository.datasource;

import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.utils.DataGenerator;

import java.util.List;

public class Datasource {
    private static class DatasourceHolder {
        private static final Datasource INSTANCE = new Datasource();
    }

    private final List<NewsModel> news;

    private Datasource() {
        this.news = DataGenerator.generateNews();
    }

    public static Datasource getInstance() {
        return DatasourceHolder.INSTANCE;
    }

    public List<NewsModel> getNews() {
        return this.news;
    }
}