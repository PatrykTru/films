package com.tru.spring5.films.bootstrap;

import com.tru.spring5.films.POJO.Category;
import com.tru.spring5.films.POJO.Description;
import com.tru.spring5.films.POJO.Film;
import com.tru.spring5.films.repositories.CategoryRepository;
import com.tru.spring5.films.repositories.FilmRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
@Component
public class FilmsBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final FilmRepository filmRepository;

    public FilmsBootstrap(CategoryRepository categoryRepository, FilmRepository filmRepository) {
        this.categoryRepository = categoryRepository;
        this.filmRepository = filmRepository;
    }

    @Transactional
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

    }


    private List<Film> getFilms()
    {

        List<Film> films = new ArrayList<>(2);

        Optional<Category> actionCategoryOptional = categoryRepository.findByDescription("Action");
        if(!actionCategoryOptional.isPresent())
            throw new RuntimeException("expected category not found");

        Optional<Category> horrorCategoryOptional = categoryRepository.findByDescription("Horror");
        if(!actionCategoryOptional.isPresent())
            throw new RuntimeException("expected category not found");

        Optional<Category> comedyCategoryOptional = categoryRepository.findByDescription("Comedy");
        if(!actionCategoryOptional.isPresent())
            throw new RuntimeException("expected category not found");

        Optional<Category> DocumentalCategoryOptional = categoryRepository.findByDescription("Documental");
        if(!actionCategoryOptional.isPresent())
            throw new RuntimeException("expected category not found");



        Film avengers = new Film();
        Description avengersDescription = new Description();
        avengersDescription.setDescriptionText("Group of superheroes fight to defend the earth from alien's invasion.");
        avengers.setYearOfPublishing(2012);
        avengers.setTitle("Avengers");
        avengers.setRating(7.5);
        avengers.setMovieDuration( Duration.ofMinutes(142));
        avengers.setId(1L);
        avengers.setDescription(avengersDescription);
        avengers.getCategories().add(actionCategoryOptional.get());
        avengers.setTrailerLink("https://www.youtube.com/watch?v=eOrNdBpGMv8");

        Film emillyRoseEgzorcism = new Film();
        Description exorcismDescription = new Description();
        avengersDescription.setDescriptionText("Emily becomes possessed by demons. He dies during an exorcism, and the priest who performed them is accused of manslaughter. ");
        avengers.setYearOfPublishing(2005);
        avengers.setTitle("The Exorcism of Emily Rose");
        avengers.setRating(7.0);
        avengers.setMovieDuration( Duration.ofMinutes(119));
        avengers.setId(2L);
        avengers.setDescription(exorcismDescription);
        avengers.getCategories().add(horrorCategoryOptional.get());
        avengers.setTrailerLink("https://www.youtube.com/watch?v=Bi-PLwxwvy8");


        films.add(emillyRoseEgzorcism);
        films.add(avengers);

        return films;
    }
}
