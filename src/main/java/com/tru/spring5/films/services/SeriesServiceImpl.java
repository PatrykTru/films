package com.tru.spring5.films.services;

import com.tru.spring5.films.POJO.Film;
import com.tru.spring5.films.POJO.Series;
import com.tru.spring5.films.commands.SeriesCommand;
import com.tru.spring5.films.converters.SeriesCommandToSeries;
import com.tru.spring5.films.converters.SeriesToSeriesCommand;
import com.tru.spring5.films.repositories.SeriesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Slf4j
public class SeriesServiceImpl implements SeriesService{

    private SeriesRepository seriesRepository;
    private SeriesToSeriesCommand seriesToSeriesCommand;
    private SeriesCommandToSeries seriesCommandToSeries;

    public SeriesServiceImpl(SeriesRepository seriesRepository, SeriesToSeriesCommand seriesToSeriesCommand, SeriesCommandToSeries seriesCommandToSeries) {
        this.seriesRepository = seriesRepository;
        this.seriesToSeriesCommand = seriesToSeriesCommand;
        this.seriesCommandToSeries = seriesCommandToSeries;
    }

    @Override
    public List<Series> getSeries() {
    int i = 0;
        log.debug("method getSeries() in SeriesServiceImpl");
       List<Series> seriesList = new ArrayList<>();
       seriesRepository.findAll().iterator().forEachRemaining(seriesList::add);
        Collections.sort(seriesList,Comparator.comparing(Series::getRating,Comparator.reverseOrder()));
        for(Series serie : seriesList)
            serie.setRank(i=i+1);

        i=0;
        return seriesList;
    }

    @Override
    public Series findById(Long l) {
        return seriesRepository.findById(l).get();
    }

    @Override
    @Transactional
    public SeriesCommand findCommandById(Long l) {



        return seriesToSeriesCommand.convert(findById(l));
    }

    @Override
    @Transactional
    public SeriesCommand saveSeriesCommand(SeriesCommand command) {
      Series seriesToSave = seriesCommandToSeries.convert(command);
      Series savedSeries = seriesRepository.save(seriesToSave);




        return seriesToSeriesCommand.convert(savedSeries);
    }

    @Override
    public void deleteById(Long idToDelete) {
            seriesRepository.deleteById(idToDelete);
    }
}
