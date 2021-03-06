package com.tru.spring5.films.controllers;

import com.tru.spring5.films.services.FilmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class FilmsController {

    private final FilmService service;

    public FilmsController(FilmService service) {
        this.service = service;
    }

    @RequestMapping({"/films"})
    public String getFilmsPage(Model model)
    {
        log.debug("Getting films page");
        model.addAttribute("films" , service.getFilms());
        return "films";

    }

    @GetMapping("/film{id}")
    public String showById(@PathVariable String id,Model model){

        model.addAttribute("film"  , service.findById(new Long(id)));


        return "film/filmPattern";
    }

}
