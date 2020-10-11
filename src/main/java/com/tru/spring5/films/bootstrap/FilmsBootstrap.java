package com.tru.spring5.films.bootstrap;

import com.tru.spring5.films.POJO.*;
import com.tru.spring5.films.repositories.CategoryRepository;
import com.tru.spring5.films.repositories.FilmRepository;
import com.tru.spring5.films.repositories.NewsRepository;
import com.tru.spring5.films.repositories.SeriesRepository;
import javassist.runtime.Desc;
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

        Optional<Category> documentalCategoryOptional = categoryRepository.findByDescription("Documental");
        if(!actionCategoryOptional.isPresent())
            throw new RuntimeException("expected description not found");

        Description moneyHeistDescription = new Description("An unusual group of robbers attempt to carry out the most perfect robbery in Spanish history - stealing 2.4 billion euros from the Royal Mint of Spain.");
        Series moneyHeist = new Series("Money Heist",4,"img/money.jpg",8.7,2017,moneyHeistDescription);
        moneyHeist.getCategories().add(actionCategoryOptional.get());

        Description witcherDescription = new Description("Geralt of Rivia, a solitary monster hunter, struggles to find his place in a world where people often prove more wicked than beasts.");
        Series witcher = new Series("Witcher",1,"img/witcher.jpg",8.0,2019,witcherDescription );
        witcher.getCategories().add(actionCategoryOptional.get());


        Description breakingBadDescription = new Description("A high school chemistry teacher diagnosed with inoperable lung cancer turns to manufacturing and selling methamphetamine in order to secure his family's future.");
        Series breakingBad = new Series("Breaking Bad",5,"img/breakingBad.jpg",7.7,2008,breakingBadDescription);
        breakingBad.getCategories().add(actionCategoryOptional.get());
        breakingBad.getCategories().add(comedyCategoryOptional.get());

        Description cosmosDescription = new Description("An exploration of our discovery of the laws of nature and coordinates in space and time.");
        Series cosmos = new Series("Cosmos" , 4 ,"img/cosmos.jpg",8.1 , 2014,cosmosDescription );
        cosmos.getCategories().add(documentalCategoryOptional.get());

        Description gameOfThronesDescription = new Description("Nine noble families fight for control over the lands of Westeros, while an ancient enemy returns after being dormant for millennia.");
        Series gameOfThrones = new Series("Game of Thrones", 8,"img/gameofThrones.jpg", 8.8 ,2011,gameOfThronesDescription);
        gameOfThrones.getCategories().add(actionCategoryOptional.get());

        Description sherlockDescription = new Description("A modern update finds the famous sleuth and his doctor partner solving crime in 21st century London.");
        Series sherlock = new Series("Sherlock", 4,"img/sherlock.jpg" ,7.5 ,2010,sherlockDescription);
        sherlock.getCategories().add(actionCategoryOptional.get());

        Description narcosDescription = new Description("A chronicled look at the criminal exploits of Colombian drug lord Pablo Escobar, as well as the many other drug kingpins who plagued the country through the years.");
        Series narcos = new Series("Narcos", 3 ,"img/narcos.jpg",7.8,2015,narcosDescription);
        narcos.getCategories().add(actionCategoryOptional.get());
        narcos.getCategories().add(documentalCategoryOptional.get());

        Description darkDescription = new Description("A family saga with a supernatural twist, set in a German town, where the disappearance of two young children exposes the relationships among four families.");
        Series dark = new Series("Dark" , 3 ,"img/dark.jpg" , 8.9 , 2017, darkDescription);

        series.add(dark);
        series.add(narcos);
        series.add(sherlock);
        series.add(breakingBad);
        series.add(moneyHeist);
        series.add(witcher);
        series.add(cosmos);
        series.add(gameOfThrones);


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

        Optional<Category> documentalCategoryOptional = categoryRepository.findByDescription("Documental");
        if(!actionCategoryOptional.isPresent())
            throw new RuntimeException("expected description not found");
        Optional<Category> crimeCategoryOptional = categoryRepository.findByDescription("Crime");
        if(!actionCategoryOptional.isPresent())
            throw new RuntimeException("expected description not found");

        Optional<Category> mysteryCategoryOptional = categoryRepository.findByDescription("Mystery");
        if(!actionCategoryOptional.isPresent())
            throw new RuntimeException("expected description not found");

        Optional<Category> thrillerCategoryOptional = categoryRepository.findByDescription("Thriller");
        if(!actionCategoryOptional.isPresent())
            throw new RuntimeException("expected description not found");

        Optional<Category> scifiCategoryOptional = categoryRepository.findByDescription("Sci-Fi");
        if(!actionCategoryOptional.isPresent())
            throw new RuntimeException("expected description not found");

        Description avengersDescription = new Description("Group of superheroes fight to defend the earth from alien's invasion.");
        Film avengers = new Film("Avengers",7.8,2012,Duration.ofMinutes(142),"img/avengers.jpg",avengersDescription,"https://www.youtube.com/watch?v=eOrNdBpGMv8");
        avengers.getCategories().add(actionCategoryOptional.get());

        Description exorcismDescription = new Description("Emily becomes possessed by demons. He dies during an exorcism, and the priest who performed them is accused of manslaughter. ");
        Film emillyRoseEgzorcism = new Film("The Exorcism of Emily Rose",7.0,2005,Duration.ofMinutes(119),"img/exorcism.jpg",exorcismDescription,"https://www.youtube.com/watch?v=Bi-PLwxwvy8");

        Description inceptionDescription = new Description("A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O.");
        Film inception = new Film("Inception" ,8.3, 2010,Duration.ofMinutes(148),"img/inception.jpg",inceptionDescription,"https://www.youtube.com/watch?v=YoHD9XEInc0");
        inception.getCategories().add(actionCategoryOptional.get());

        Description matrixDescription = new Description("A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.");
        Film matrix = new Film("The Matrix", 8.0,1999,Duration.ofMinutes(136),"img/matrix.jpg",matrixDescription,"https://www.youtube.com/watch?v=m8e-FF8MsqU");
        matrix.getCategories().add(actionCategoryOptional.get());

        Description sevenDescription = new Description("Two detectives, a rookie and a veteran, hunt a serial killer who uses the seven deadly sins as his motives.");
        Film seven = new Film("Se7en",8.1,1995,Duration.ofMinutes(127),"img/seven.jpg",sevenDescription,"https://www.youtube.com/watch?v=znmZoVkCjpI");
        seven.getCategories().add(crimeCategoryOptional.get());
        seven.getCategories().add(thrillerCategoryOptional.get());

        Description interstellarDescription = new Description("A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival.");
        Film interstellar = new Film("Interstellar",8.2 ,2014,Duration.ofMinutes(169),"img/interstellar.jpg",interstellarDescription,"https://www.youtube.com/watch?v=zSWdZVtXT7E");
        interstellar.getCategories().add(scifiCategoryOptional.get());

        Description gladiatorDescription = new Description("A former Roman General sets out to exact vengeance against the corrupt emperor who murdered his family and sent him into slavery.");
        Film gladiator = new Film("Gladiator", 8.4 ,2000 ,Duration.ofMinutes(155) ,"img/gladiator.jpg" ,gladiatorDescription ,"https://www.youtube.com/watch?v=owK1qxDselE");
        gladiator.getCategories().add(actionCategoryOptional.get());

        Description prestigeDescription = new Description("After a tragic accident, two stage magicians engage in a battle to create the ultimate illusion while sacrificing everything they have to outwit each other.");
        Film prestige = new Film("The Prestige" ,7.4 ,2007 ,Duration.ofMinutes(130) ,"img/prestige.jpg" ,prestigeDescription , "https://www.youtube.com/watch?v=ObGVA1WOqyE");
        prestige.getCategories().add(mysteryCategoryOptional.get());
        prestige.getCategories().add(scifiCategoryOptional.get());


        films.add(prestige);
        films.add(gladiator);
        films.add(interstellar);
        films.add(seven);
        films.add(matrix);
        films.add(inception);
        films.add(emillyRoseEgzorcism);
        films.add(avengers);


        log.debug("Films created count:" + filmRepository.count());
        return films;
    }
}
