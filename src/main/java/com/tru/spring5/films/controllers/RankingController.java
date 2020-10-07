package com.tru.spring5.films.controllers;


import com.tru.spring5.films.POJO.Film;
import com.tru.spring5.films.services.FilmService;
import com.tru.spring5.films.services.SeriesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class RankingController {

    private final FilmService filmService;
    private final SeriesService seriesService;

    public RankingController(FilmService filmService, SeriesService seriesService) {
        this.filmService = filmService;
        this.seriesService = seriesService;
    }

    @RequestMapping({"/rankingFilms"})
public String getRankingPage(Model model)
{
    log.debug("Getting ranking page");
    model.addAttribute("films", filmService.getFilms());

    return "rankingFilms";

}

    @RequestMapping({"/rankingSeries"})
    public String getRankingPageSeries(Model model)
    {
        log.debug("Getting ranking page");
        model.addAttribute("series", seriesService.getSeries());

        return "rankingSeries";

    }


}
