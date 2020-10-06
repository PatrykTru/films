package com.tru.spring5.films.controllers;

import com.tru.spring5.films.services.SeriesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class SeriesController {

    private final SeriesService seriesService;

    public SeriesController(SeriesService seriesService) {
        this.seriesService = seriesService;
    }


    @RequestMapping({"/series"})
    public String getSeriesPage(Model model)
    {
        log.debug("Getting films page");
        model.addAttribute("series" , seriesService.getSeries());
        return "series";

    }
}
