package com.tru.spring5.films.converters;

import com.tru.spring5.films.commands.CategoryCommand;
import com.tru.spring5.films.commands.FilmCommand;
import com.tru.spring5.films.POJO.Film;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class FilmCommandToFilm implements Converter<FilmCommand, Film> {

    private final CategoryCommandToCategory categoryConverter;
    private final DescriptionCommandToDescription descriptionConverter;

    public FilmCommandToFilm(CategoryCommandToCategory categoryConverter, DescriptionCommandToDescription descriptionConverter) {
        this.categoryConverter = categoryConverter;
        this.descriptionConverter = descriptionConverter;
    }

    @Nullable
    @Synchronized
    @Override
    public Film convert(FilmCommand filmCommand) {
        if(filmCommand==null)
        return null;

        final Film film = new Film();
        film.setDescription(descriptionConverter.convert(filmCommand.getDescription()));
        film.setId(filmCommand.getId());
        film.setMovieDuration(filmCommand.getMovieDuration());
        film.setRating(filmCommand.getRating());
        film.setTitle(filmCommand.getTitle());
        film.setTrailerLink(filmCommand.getTrailerLink());
        film.setYearOfPublishing(filmCommand.getYearOfPublishing());

        if(filmCommand.getCategories() != null && filmCommand.getCategories().size() > 0 )
            filmCommand.getCategories().forEach((CategoryCommand command) -> film.getCategories().add(categoryConverter.convert(command)));

        return film;
    }
}
