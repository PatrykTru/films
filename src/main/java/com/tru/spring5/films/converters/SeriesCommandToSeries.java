package com.tru.spring5.films.converters;

import com.tru.spring5.films.POJO.Series;
import com.tru.spring5.films.commands.CategoryCommand;
import com.tru.spring5.films.commands.SeriesCommand;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SeriesCommandToSeries implements Converter<SeriesCommand , Series> {

    private final CategoryCommandToCategory categoryConverter;
    private final DescriptionCommandToDescription descriptionConverter;

    public SeriesCommandToSeries(CategoryCommandToCategory categoryConverter, DescriptionCommandToDescription descriptionConverter) {
        this.categoryConverter = categoryConverter;
        this.descriptionConverter = descriptionConverter;
    }

    @Override
    public Series convert(SeriesCommand seriesCommand) {

        if (seriesCommand==null)
            return null;

        Series series = new Series();

        series.setId(seriesCommand.getId());
        series.setPosterUrl(seriesCommand.getPosterUrl());
        series.setRating(seriesCommand.getRating());
        series.setSeasons(seriesCommand.getSeasons());
        series.setTitle(seriesCommand.getTitle());
        series.setYearOfPublishing(seriesCommand.getYearOfPublishing());
        series.setDescription(descriptionConverter.convert(seriesCommand.getDescriptionCommand()));

        if(seriesCommand.getCategoryCommands() != null && seriesCommand.getCategoryCommands().size() > 0 )
            seriesCommand.getCategoryCommands().forEach((CategoryCommand command) -> series.getCategories().add(categoryConverter.convert(command)));






        return series;

    }
}
