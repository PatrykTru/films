package com.tru.spring5.films.commands;

import com.tru.spring5.films.POJO.Film;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class DescriptionCommand {

    private Long id;
    private Film film;
    private String description;
}
