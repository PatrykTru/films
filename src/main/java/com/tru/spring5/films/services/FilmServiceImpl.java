package com.tru.spring5.films.services;

import com.tru.spring5.films.POJO.Film;
import com.tru.spring5.films.POJO.Series;
import com.tru.spring5.films.commands.FilmCommand;
import com.tru.spring5.films.converters.FilmCommandToFilm;
import com.tru.spring5.films.converters.FilmToFilmCommand;
import com.tru.spring5.films.repositories.FilmRepository;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
public class FilmServiceImpl implements FilmService{

    private final FilmRepository filmRepository;
    private final FilmToFilmCommand filmToFilmCommand;
    private final FilmCommandToFilm filmCommandToFilm;

    public FilmServiceImpl(FilmRepository filmRepository, FilmToFilmCommand filmToFilmCommand, FilmCommandToFilm filmCommandToFilm) {
        this.filmRepository = filmRepository;
        this.filmToFilmCommand = filmToFilmCommand;
        this.filmCommandToFilm = filmCommandToFilm;
    }

    @Override
    public List<Film> getFilms() {
        int i=0;
        log.debug("method getFilms() in FilmServiceImpl");
        List<Film> filmList = new ArrayList<>();
        filmRepository.findAll().iterator().forEachRemaining(filmList::add);
        Collections.sort(filmList,Comparator.comparing(Film::getRating,Comparator.reverseOrder()));
        for(Film films : filmList)
            films.setRank(i=i+1);

        i=0;

        return filmList;
    }

    @Override
    public Film findById(Long l) {
        Optional<Film> filmOptional = filmRepository.findById(l);
//        if (!filmOptional.isPresent())
//            throw new NotFoundException("Film not found. For ID value :" + l.toString());
        return filmOptional.get();
    }

    @Transactional
    @Override
    public FilmCommand findCommandById(Long l) {
        return filmToFilmCommand.convert(findById(l));
    }

    @Transactional
    @Override
    public FilmCommand saveFilmCommand(FilmCommand command) {
        Film commandToSave = filmCommandToFilm.convert(command);
        Film savedFilm = filmRepository.save(commandToSave);

        log.debug("Saved FilmId : " +command.getId());
        return filmToFilmCommand.convert(savedFilm);
    }

    @Override
    public void deleteById(Long idToDelete) {

        filmRepository.deleteById(idToDelete);
    }
}
