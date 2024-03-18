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
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public class NewsServiceImpl implements NewsService<NewsDto> {
    private final Validator VALIDATOR =
            Validation.byDefaultProvider()
                    .configure()
                    .messageInterpolator(new ParameterMessageInterpolator())
                    .buildValidatorFactory()
                    .getValidator();
    private static final Logger LOGGER = Logger.getLogger(NewsServiceImpl.class.getName());
    private final NewsRepository<NewsModel> newsRepository = new NewsRepositoryImpl();

    public NewsServiceImpl() {
    }

    @Override
    public NewsDto create(NewsDto newsDto) {
        try {
            validate(newsDto);
            NewsModel news = NewsMapper.INSTANCE.newsDtoToNews(newsDto);
            LocalDateTime date = LocalDateTime.now();
            news.setCreateDate(date);
            news.setLastUpdateTime(date);
            return NewsMapper.INSTANCE.newsToNewsDto(newsRepository.create(news));
        } catch (ValidationException e) {
            LOGGER.warning("Failed to create news: " + e.getMessage());
            throw new ServiceException("Failed to create News", "201");
        }
    }

    @Override
    public List<NewsDto> readAll() {
        return NewsMapper.INSTANCE.newsListToNewsDtoList(newsRepository.readAll());
    }

    @Override
    public NewsDto readById(Long id) {
        try {
            NewsModel foundNews = newsRepository.readById(id);
            if (foundNews == null) {
                throw new ServiceException(String.format("News with id %d not found", id), "202");
            }
            return NewsMapper.INSTANCE.newsToNewsDto(foundNews);
        } catch (Exception e) {
            LOGGER.warning(e.getMessage());
            throw new ServiceException("Failed to read News by id", "203");
        }
    }

    @Override
    public NewsDto update(NewsDto newsDto) {
        try {
            NewsModel news = NewsMapper.INSTANCE.newsDtoToNews(newsDto);
            LocalDateTime date = LocalDateTime.now();
            news.setLastUpdateTime(date);
            return NewsMapper.INSTANCE.newsToNewsDto(newsRepository.update(news));
        } catch (Exception e) {
            LOGGER.warning("Failed to update news: " + e.getMessage());
            throw new ServiceException("Failed to update News", "204");
        }
    }

    @Override
    public Boolean deleteById(Long id) {
        return newsRepository.deleteById(id);
    }

    private void validate(NewsDto newsDto) throws ValidationException {
        Set<ConstraintViolation<NewsDto>> violations = VALIDATOR.validate(newsDto);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<NewsDto> violation : violations) {
                LOGGER.warning("News " + violation.getPropertyPath() + ": " + violation.getMessage());
            }
            throw new ValidationException("Failed to validate News");
        }
    }
}
