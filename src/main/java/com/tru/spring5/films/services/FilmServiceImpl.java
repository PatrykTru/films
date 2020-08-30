package com.tru.spring5.films.services;

import com.tru.spring5.films.POJO.Film;
import com.tru.spring5.films.commands.FilmCommand;
import com.tru.spring5.films.converters.FilmCommandToFilm;
import com.tru.spring5.films.converters.FilmToFilmCommand;
import com.tru.spring5.films.repositories.FilmRepository;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
    public Set<Film> getFilms() {
        log.debug("method getFilms() in FilmServiceImpl");
        Set<Film> filmSet = new HashSet<>();
        filmRepository.findAll().iterator().forEachRemaining(filmSet::add);
        return filmSet;
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
