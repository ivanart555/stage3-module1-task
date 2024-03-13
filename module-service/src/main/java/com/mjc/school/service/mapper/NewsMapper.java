package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.News;
import com.mjc.school.service.dto.NewsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface NewsMapper {

    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);

    NewsDto newsToNewsDto(News news);

    News newsDtoToNews(NewsDto newsDto);

    List<NewsDto> newsListToNewsDtoList(List<News> newsList);

}
