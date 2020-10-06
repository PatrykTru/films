package com.tru.spring5.films.controllers;

import com.tru.spring5.films.services.FilmService;
import com.tru.spring5.films.services.NewsService;
import com.tru.spring5.films.services.SeriesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class IndexController {

    private final NewsService newsService;


    public IndexController( NewsService newsService) {
        this.newsService = newsService;

    }

    @RequestMapping({"", "/" , "/index"})
    public String getIndexPage(Model model)
    {
     log.debug("Getting index page");
     model.addAttribute("news" , newsService.getNews());
     return "index";

    }







}
