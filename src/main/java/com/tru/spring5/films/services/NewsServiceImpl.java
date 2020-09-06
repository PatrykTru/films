package com.tru.spring5.films.services;

import com.tru.spring5.films.POJO.News;
import com.tru.spring5.films.repositories.NewsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class NewsServiceImpl implements NewsService{

    private NewsRepository newsRepository;

    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public Set<News> getNews() {
        log.debug("returning all news Set");
        Set<News> newsSet = new HashSet<>();
                newsRepository.findAll().iterator().forEachRemaining(newsSet::add);
        return newsSet;
    }
}
