package com.tru.spring5.films.commands;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class FilmCommand {

    private Long id;
    private String title;
    private double rating;
    private int yearOfPublishing;
    private Duration movieDuration;
    private String trailerLink;
    private DescriptionCommand description;
    private Set<CategoryCommand> categories = new HashSet<>();
}
