package com.mjc.school.service.impl;

import com.mjc.school.repository.NewsRepository;
import com.mjc.school.repository.impl.NewsRepositoryImpl;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.exception.ServiceException;
import com.mjc.school.service.mapper.NewsMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public class NewsServiceImpl implements NewsService {
    private static final Validator VALIDATOR =
            Validation.byDefaultProvider()
                    .configure()
                    .messageInterpolator(new ParameterMessageInterpolator())
                    .buildValidatorFactory()
                    .getValidator();
    private static final Logger LOGGER = Logger.getLogger(NewsServiceImpl.class.getName());
    private final NewsRepository newsRepository = new NewsRepositoryImpl();

    @Override
    public NewsDto create(NewsDto newsDto) {
        NewsModel news = NewsMapper.INSTANCE.newsDtoToNews(newsDto);

        Set<ConstraintViolation<NewsDto>> violations = VALIDATOR.validate(newsDto);
        if (!violations.isEmpty()) {
            handleValidationErrors(violations);
        } else {
            LocalDateTime date = LocalDateTime.now();
            news.setCreateDate(date);
            news.setLastUpdateTime(date);
            return NewsMapper.INSTANCE.newsToNewsDto(newsRepository.create(news));
        }

        return newsDto;
    }

    @Override
    public List<NewsDto> findAll() {
        return NewsMapper.INSTANCE.newsListToNewsDtoList(newsRepository.readAll());
    }

    @Override
    public NewsDto findById(Long id) {
        NewsModel foundNews = null;
        try {
            foundNews = newsRepository.readById(id);
            if (foundNews == null) {
                throw new ServiceException(String.format("News with id %d not found", id), "202");
            }

        } catch (ServiceException e) {
            LOGGER.warning(String.format("News with id %d not found", id));
        }

        return NewsMapper.INSTANCE.newsToNewsDto(foundNews);
    }

    @Override
    public NewsDto update(NewsDto newsDto) {
        NewsModel news = NewsMapper.INSTANCE.newsDtoToNews(newsDto);

        LocalDateTime date = LocalDateTime.now();
        news.setLastUpdateTime(date);

        return NewsMapper.INSTANCE.newsToNewsDto(newsRepository.update(news));
    }

    @Override
    public Boolean deleteById(Long id) {
        return newsRepository.deleteById(id);
    }

    private void handleValidationErrors(Set<ConstraintViolation<NewsDto>> violations) {
        for (ConstraintViolation<NewsDto> violation : violations) {
            LOGGER.warning("News " + violation.getPropertyPath() + ": " + violation.getMessage());
            throw new ServiceException("Failed to validate News", "201");
        }
    }
}
