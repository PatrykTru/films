package com.tru.spring5.films.bootstrap;

import com.tru.spring5.films.POJO.Category;
import com.tru.spring5.films.POJO.Description;
import com.tru.spring5.films.POJO.Film;
import com.tru.spring5.films.POJO.News;
import com.tru.spring5.films.repositories.CategoryRepository;
import com.tru.spring5.films.repositories.FilmRepository;
import com.tru.spring5.films.repositories.NewsRepository;
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
    private final NewsRepository newsRepository;

    public FilmsBootstrap(CategoryRepository categoryRepository, FilmRepository filmRepository, NewsRepository newsRepository) {
        this.categoryRepository = categoryRepository;
        this.filmRepository = filmRepository;
        this.newsRepository = newsRepository;
    }

    @Transactional
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        filmRepository.saveAll(getFilms());
        newsRepository.saveAll(getNews());
        log.debug("Loading Bootstrap Data");
    }

private  List<News> getNews(){

        List<News> newsList = new ArrayList<>(2);

        News news1 = new News();
        news1.setId(1L);
        news1.setPictureUrl("img/evil-news.jpg");
        news1.setNewsTextShort("Resident Evil is coming to the small screen. Netflix has ordered a live-action series based on the popular video game franchise. Andrew Dabb is writing the series. He is behind Supernatural.");

        newsList.add(news1);
        return newsList;


}
    private List<Film> getFilms()
    {

        List<Film> films = new ArrayList<>(2);


        Optional<Category> actionCategoryOptional = categoryRepository.findByDescription("Action");
        if(!actionCategoryOptional.isPresent())
            throw new RuntimeException("expected description not found");

        Optional<Category> horrorCategoryOptional = categoryRepository.findByDescription("Horror");
        if(!actionCategoryOptional.isPresent())
            throw new RuntimeException("expected description not found");

        Optional<Category> comedyCategoryOptional = categoryRepository.findByDescription("Comedy");
        if(!actionCategoryOptional.isPresent())
            throw new RuntimeException("expected description not found");

        Optional<Category> DocumentalCategoryOptional = categoryRepository.findByDescription("Documental");
        if(!actionCategoryOptional.isPresent())
            throw new RuntimeException("expected description not found");



        Film avengers = new Film();
        Description avengersDescription = new Description();
        avengersDescription.setDescriptionText("Group of superheroes fight to defend the earth from alien's invasion.");
        avengers.setYearOfPublishing(2012);
        avengers.setTitle("Avengers");
        avengers.setPosterUrl("img/avengers.jpg");
        avengers.setRating(7.5);
        avengers.setMovieDuration( Duration.ofMinutes(142));
        avengers.setId(1L);
        avengers.setDescription(avengersDescription);
        avengers.getCategories().add(actionCategoryOptional.get());
        avengers.setTrailerLink("https://www.youtube.com/watch?v=eOrNdBpGMv8");

        Film emillyRoseEgzorcism = new Film();
        Description exorcismDescription = new Description();
        exorcismDescription.setDescriptionText("Emily becomes possessed by demons. He dies during an exorcism, and the priest who performed them is accused of manslaughter. ");
        emillyRoseEgzorcism.setYearOfPublishing(2005);
        emillyRoseEgzorcism.setTitle("The Exorcism of Emily Rose");
        emillyRoseEgzorcism.setRating(7.0);
        emillyRoseEgzorcism.setMovieDuration( Duration.ofMinutes(119));
        emillyRoseEgzorcism.setId(2L);
        emillyRoseEgzorcism.setDescription(exorcismDescription);
        emillyRoseEgzorcism.getCategories().add(horrorCategoryOptional.get());
        emillyRoseEgzorcism.setTrailerLink("https://www.youtube.com/watch?v=Bi-PLwxwvy8");
        emillyRoseEgzorcism.setPosterUrl("img/exorcism.jpg");

        films.add(emillyRoseEgzorcism);
        films.add(avengers);


        log.debug("Films created count:" + filmRepository.count());
        return films;
    }
}
