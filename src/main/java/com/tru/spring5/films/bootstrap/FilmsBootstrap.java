package com.tru.spring5.films.bootstrap;

import com.tru.spring5.films.POJO.*;
import com.tru.spring5.films.repositories.CategoryRepository;
import com.tru.spring5.films.repositories.FilmRepository;
import com.tru.spring5.films.repositories.NewsRepository;
import com.tru.spring5.films.repositories.SeriesRepository;
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
    private final SeriesRepository seriesRepository;

    public FilmsBootstrap(CategoryRepository categoryRepository, FilmRepository filmRepository, NewsRepository newsRepository, SeriesRepository seriesRepository) {
        this.categoryRepository = categoryRepository;
        this.filmRepository = filmRepository;
        this.newsRepository = newsRepository;
        this.seriesRepository = seriesRepository;
    }

    @Transactional
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        filmRepository.saveAll(getFilms());
        newsRepository.saveAll(getNews());
        seriesRepository.saveAll(getSeries());

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
    private List<Series> getSeries(){

        List<Series> series = new ArrayList<>(2);

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

        Series moneyHeist = new Series();
        Description moneyHeistDescription = new Description();
        moneyHeistDescription.setDescriptionText("An unusual group of robbers attempt to carry out the most perfect robbery in Spanish history - stealing 2.4 billion euros from the Royal Mint of Spain.");
        moneyHeist.setDescription(moneyHeistDescription);
        moneyHeist.setYearOfPublishing(2017);
        moneyHeist.setTitle("Money Heist");
        moneyHeist.setSeasons(4);
        moneyHeist.setRating(8.8);
        moneyHeist.setId(1l);
        moneyHeist.getCategories().add(actionCategoryOptional.get());
        moneyHeist.setPosterUrl("img/money.jpg");



        Series witcher = new Series();
        Description witcherDescription = new Description();
        witcherDescription.setDescriptionText("Geralt of Rivia, a solitary monster hunter, struggles to find his place in a world where people often prove more wicked than beasts.");
        witcher.setPosterUrl("img/witcher.jpg");
        witcher.setId(2l);
        witcher.setRating(8.0);
        witcher.setSeasons(1);
        witcher.setTitle("Witcher");
        witcher.setYearOfPublishing(2019);
        witcher.setDescription(witcherDescription);
        witcher.getCategories().add(actionCategoryOptional.get());

        series.add(moneyHeist);
        series.add(witcher);


        return series;


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
