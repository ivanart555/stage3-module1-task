package com.mjc.school.repository.utils;

import com.mjc.school.repository.model.Author;
import com.mjc.school.repository.model.News;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataGenerator {
    private static final String AUTHORS_FILENAME = "authors.txt";
    private static final String CONTENT_FILENAME = "content.txt";
    private static final String NEWS_FILENAME = "news.txt";
    private static final int STARTING_YEAR = 2022;
    private static final int NEWS_COUNT_TO_GENERATE = 20;

    public static List<News> generateNews() {
        List<String> contentLines = ContentReader.getFileContent(CONTENT_FILENAME);
        List<String> titlesLines = ContentReader.getFileContent(NEWS_FILENAME);

        List<Author> authors = generateAuthors();

        Random random = new Random();
        List<News> newsList = new ArrayList<>();

        for (int i = 1; i < NEWS_COUNT_TO_GENERATE + 1; i++) {
            News news = new News();
            news.setId((long) i);
            news.setAuthorId((long) random.nextInt(authors.size()));
            news.setTitle(getRandomLine(titlesLines));
            news.setContent(getRandomLine(contentLines));
            news.setCreateDate(generateRandomLocalDateTime());
            news.setLastUpdateTime(LocalDateTime.now());
            newsList.add(news);
        }

        return newsList;


    }

    private static List<Author> generateAuthors() {
        List<Author> authors = new ArrayList<>();

        List<String> authorsLines = ContentReader.getFileContent(AUTHORS_FILENAME);
        long counter = 1L;
        for (String element : authorsLines) {
            authors.add(new Author(counter, element));
        }

        return authors;
    }

    private static String getRandomLine(List<String> content) {
        Random random = new Random();
        return content.get(random.nextInt(content.size() - 1));
    }

    private static LocalDateTime generateRandomLocalDateTime() {
        if (STARTING_YEAR < 0) {
            throw new IllegalArgumentException("Invalid input parameters");
        }

        LocalDateTime startDate = LocalDateTime.of(STARTING_YEAR, 1, 1, 0, 0);

        Random random = new Random();
        long randomMonths = random.nextInt(12);

        LocalDateTime randomDateTime = startDate.plusMonths(randomMonths);

        randomDateTime = randomDateTime.plusHours(random.nextInt(24))
                .plusMinutes(random.nextInt(60))
                .plusSeconds(random.nextInt(60));

        return randomDateTime;
    }

}