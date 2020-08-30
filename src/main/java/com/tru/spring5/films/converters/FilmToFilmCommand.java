package com.tru.spring5.films.converters;

import com.tru.spring5.films.commands.FilmCommand;
import com.tru.spring5.films.POJO.Category;
import com.tru.spring5.films.POJO.Film;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FilmToFilmCommand implements Converter<Film, FilmCommand> {

    private final CategoryToCategoryCommand categoryConverter;
    private final DescriptionToDescriptionCommand descriptionConverter;

    public FilmToFilmCommand(CategoryToCategoryCommand categoryConverter, DescriptionToDescriptionCommand descriptionConverter) {
        this.categoryConverter = categoryConverter;
        this.descriptionConverter = descriptionConverter;
    }

    @Override
    public FilmCommand convert(Film film) {
        if (film == null)
        return null;

        final FilmCommand filmCommand = new FilmCommand();
        filmCommand.setId(film.getId());
        filmCommand.setMovieDuration(film.getMovieDuration());
        filmCommand.setRating(film.getRating());
        filmCommand.setTitle(film.getTitle());
        filmCommand.setTrailerLink(film.getTrailerLink());
        filmCommand.setYearOfPublishing(film.getYearOfPublishing());
        filmCommand.setDescription(descriptionConverter.convert(film.getDescription()));

        if(film.getCategories() != null && film.getCategories().size()> 0 )
            film.getCategories().forEach((Category category) -> filmCommand.getCategories().add(categoryConverter.convert(category)) );



        return filmCommand;
    }
}
