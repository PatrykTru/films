package com.tru.spring5.films.services;

import com.tru.spring5.films.POJO.Film;
import com.tru.spring5.films.commands.FilmCommand;
import com.tru.spring5.films.converters.FilmCommandToFilm;
import com.tru.spring5.films.converters.FilmToFilmCommand;
import com.tru.spring5.films.repositories.FilmRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.junit.Test;

import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class FilmServiceImpTest {

    FilmServiceImpl filmService;

    @Mock
    FilmRepository filmRepository;

    @Mock
    FilmToFilmCommand filmToFilmCommand;

    @Mock
    FilmCommandToFilm filmCommandToFilm;


    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        filmService = new FilmServiceImpl(filmRepository,filmToFilmCommand,filmCommandToFilm);
    }

    @Test
    public void getFilmByIDTest() throws Exception
    {

        Film film = new Film();
        film.setId(1L);
        Optional<Film> filmOptional = Optional.of(film);

        when(filmRepository.findById(anyLong())).thenReturn(filmOptional);

        Film returnedFilm = filmService.findById(1l);

        assertNotNull("Null Film returned" , returnedFilm);
        verify(filmRepository,times(1)).findById(anyLong());
        verify(filmRepository,never()).findAll();


    }

    @Test
    public void getFilmCommandByIDTest() throws Exception {
        Film film = new Film();
        film.setId(1L);
        Optional<Film> filmOptional = Optional.of(film);


        when(filmRepository.findById(anyLong())).thenReturn(filmOptional);

        FilmCommand filmCommand = new FilmCommand();
        filmCommand.setId(1L);

        when(filmToFilmCommand.convert(any())).thenReturn(filmCommand);

        FilmCommand commandByID = filmService.findCommandById(1L);

        assertNotNull("Null Film returned" , commandByID);
        verify(filmRepository,times(1)).findById(anyLong());
        verify(filmRepository,never()).findAll();
    }

    @Test
    public void getFilmsTest() throws Exception {

        Film film = new Film();
        HashSet filmData = new HashSet();
        filmData.add(film);

        when(filmService.getFilms()).thenReturn(filmData);

        Set<Film> films =filmService.getFilms();

        assertEquals(filmData.size(), 1);
        verify(filmRepository,times(1)).findAll();
        verify(filmRepository,never()).findById(anyLong());

    }

    @Test
    public void testDeleteByID() throws Exception{

        Long idToDelete = Long.valueOf(2L);

        filmService.deleteById(idToDelete);

        verify(filmRepository,times(1)).deleteById(anyLong());
    }
}
