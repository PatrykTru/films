package com.tru.spring5.films.repositories;

import com.tru.spring5.films.POJO.Series;
import org.springframework.data.repository.CrudRepository;

public interface SeriesRepository extends CrudRepository<Series,Long> {
}
