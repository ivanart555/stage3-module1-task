package com.mjc.school.repository.datasource;

import com.mjc.school.repository.model.News;
import com.mjc.school.repository.utils.DataGenerator;

import java.util.List;

public class Datasource {
    private static Datasource instance;
    private final List<News> news;

    private Datasource() {
        this.news = DataGenerator.generateNews();
    }

    public static Datasource getInstance() {
        if (instance == null) {
            synchronized (Datasource.class) {
                if (instance == null) {
                    instance = new Datasource();
                }

            }
        }
        return instance;
    }

    public List<News> getNews() {
        return this.news;
    }
}