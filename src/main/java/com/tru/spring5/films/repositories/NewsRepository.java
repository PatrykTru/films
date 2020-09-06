package com.tru.spring5.films.repositories;

import com.tru.spring5.films.POJO.News;
import org.springframework.data.repository.CrudRepository;

public interface NewsRepository extends CrudRepository<News,Long> {
}
