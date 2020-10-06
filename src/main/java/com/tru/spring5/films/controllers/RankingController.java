package com.tru.spring5.films.controllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class RankingController {

@RequestMapping({"/ranking"})
public String getRankingPage()
{
    log.debug("Getting ranking page");

    return "ranking";

}


}
