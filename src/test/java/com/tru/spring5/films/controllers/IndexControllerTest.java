package com.tru.spring5.films.controllers;

import com.tru.spring5.films.POJO.Film;
import com.tru.spring5.films.services.FilmService;

import com.tru.spring5.films.services.NewsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IndexControllerTest {
    @Mock
    FilmService filmService;
    @Mock
    NewsService newsService;
    @Mock
    Model model;

    IndexController controller;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);
        controller = new IndexController(filmService,newsService);

    }

    @Test
    void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));

    }

    @Test
    void testGetIndexPage()throws Exception{


        Set<Film> filmSet = new HashSet<>();
        filmSet.add(new Film());

        Film film = new Film();
        film.setId(1L);
        filmSet.add(film);

        when(filmService.getFilms()).thenReturn(filmSet);

        ArgumentCaptor<Set<Film>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        String viewName = controller.getIndexPage(model);


        assertEquals("index" , viewName);
        verify(filmService , times(1)).getFilms();
        verify(model,times(1)).addAttribute(eq("films") , argumentCaptor.capture());
        Set<Film> setInsideController = argumentCaptor.getValue();
        assertEquals(2,setInsideController.size());

    }



}
