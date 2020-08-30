package com.tru.spring5.films.commands;

import com.tru.spring5.films.POJO.Film;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CategoryCommand {

    private Long id;
    private String category;
    private Set<Film> films;
}
