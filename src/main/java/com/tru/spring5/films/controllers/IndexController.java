package com.tru.spring5.films.controllers;

import com.tru.spring5.films.services.FilmService;
import com.tru.spring5.films.services.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class IndexController {

    private final FilmService filmService;
    private final NewsService newsService;

    public IndexController(FilmService filmService, NewsService newsService) {
        this.filmService = filmService;
        this.newsService = newsService;
    }

    @RequestMapping({"", "/" , "/index"})
    public String getIndexPage(Model model)
    {
     log.debug("Getting index page");
     model.addAttribute("news" , newsService.getNews());
     return "index";

    }


    @RequestMapping({"/films"})
    public String getFilmsPage(Model model)
    {
        log.debug("Getting films page");
        model.addAttribute("films" , filmService.getFilms());
        return "films";

    }
}
