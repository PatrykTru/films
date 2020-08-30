package com.tru.spring5.films.services;

import com.tru.spring5.films.POJO.Film;
import com.tru.spring5.films.commands.FilmCommand;

import java.util.Set;

public interface FilmService {

    Set<Film> getFilms();

    Film findById(Long l);

    FilmCommand findCommandById(Long l);

    FilmCommand saveFilmCommand(FilmCommand command);

    void deleteById(Long idToDelete);
}
