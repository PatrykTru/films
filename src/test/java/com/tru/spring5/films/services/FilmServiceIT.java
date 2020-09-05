package com.tru.spring5.films.services;


import com.tru.spring5.films.POJO.Film;
import com.tru.spring5.films.commands.DescriptionCommand;
import com.tru.spring5.films.commands.FilmCommand;
import com.tru.spring5.films.converters.FilmCommandToFilm;
import com.tru.spring5.films.converters.FilmToFilmCommand;
import com.tru.spring5.films.repositories.FilmRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FilmServiceIT {






    @Autowired
    FilmService filmService;
    @Autowired
    FilmRepository filmRepository;
    @Autowired
    FilmToFilmCommand filmToFilmCommand;
    @Autowired
    FilmCommandToFilm filmCommandToFilm;

    @Transactional
    @Test
    public void testSaveOfDescription() throws Exception
    {

        //given

        DescriptionCommand descriptionCommand = new DescriptionCommand();
        descriptionCommand.setId(1l);
        descriptionCommand.setDescription("DESC");
        Iterable<Film> films = filmRepository.findAll();

        Film testFilm = films.iterator().next();

        FilmCommand testCommand = filmToFilmCommand.convert(testFilm);

        //when

        testCommand.setDescription(descriptionCommand);
        FilmCommand savedFilmCommand = filmService.saveFilmCommand(testCommand);

        //then
        assertEquals(descriptionCommand.getDescription(), savedFilmCommand.getDescription().getDescription());
        assertEquals(testCommand.getId() , savedFilmCommand.getId());
        assertEquals(testCommand.getCategories(),savedFilmCommand.getCategories());
        assertEquals(testCommand.getMovieDuration(),savedFilmCommand.getMovieDuration());
    }

}
