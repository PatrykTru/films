package com.tru.spring5.films.converters;

import com.tru.spring5.films.POJO.Category;
import com.tru.spring5.films.POJO.Series;
import com.tru.spring5.films.commands.SeriesCommand;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SeriesToSeriesCommand implements Converter<Series,SeriesCommand> {

    private final CategoryToCategoryCommand categoryToCategoryCommandConverter;
    private final DescriptionToDescriptionCommand descriptionToDescriptionCommandConverter;

    public SeriesToSeriesCommand(CategoryToCategoryCommand categoryToCategoryCommandConverter, DescriptionToDescriptionCommand descriptionToDescriptionCommandConverter) {
        this.categoryToCategoryCommandConverter = categoryToCategoryCommandConverter;
        this.descriptionToDescriptionCommandConverter = descriptionToDescriptionCommandConverter;
    }

    @Override
    public SeriesCommand convert(Series series) {

       if(series==null)
        return null;

       final SeriesCommand seriesCommand = new SeriesCommand();
       seriesCommand.setId(series.getId());
       seriesCommand.setPosterUrl(series.getPosterUrl());
       seriesCommand.setRating(series.getRating());
       seriesCommand.setSeasons(series.getSeasons());
       seriesCommand.setTitle(series.getTitle());
       seriesCommand.setYearOfPublishing(series.getYearOfPublishing());
       seriesCommand.setDescriptionCommand(descriptionToDescriptionCommandConverter.convert(series.getDescription()));

        if(series.getCategories() != null && series.getCategories().size()> 0 )
            series.getCategories().forEach((Category category) -> seriesCommand.getCategoryCommands().add(categoryToCategoryCommandConverter.convert(category)) );

return seriesCommand;



    }
}
