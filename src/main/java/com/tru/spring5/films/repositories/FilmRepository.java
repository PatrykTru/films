package com.tru.spring5.films.repositories;

import com.tru.spring5.films.POJO.Film;
import org.springframework.data.repository.CrudRepository;

public interface FilmRepository extends CrudRepository<Film,Long> {
}
