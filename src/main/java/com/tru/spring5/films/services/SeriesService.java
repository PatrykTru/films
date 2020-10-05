package com.tru.spring5.films.services;

import com.tru.spring5.films.POJO.Film;
import com.tru.spring5.films.POJO.Series;
import com.tru.spring5.films.commands.FilmCommand;
import com.tru.spring5.films.commands.SeriesCommand;

import java.util.Set;

public interface SeriesService {

    Set<Series> getSeries();

    Series findById(Long l);

    SeriesCommand findCommandById(Long l);

    SeriesCommand saveSeriesCommand(SeriesCommand command);

    void deleteById(Long idToDelete);

}
