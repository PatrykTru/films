package com.tru.spring5.films.commands;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class SeriesCommand {

    private Long id;
    private String title;
    private int seasons;
    private String posterUrl;
    private Double rating;
    private Integer yearOfPublishing;

    private DescriptionCommand descriptionCommand;
    private Set<CategoryCommand> categoryCommands;


}
