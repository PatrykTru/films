package com.tru.spring5.films.services;

import com.tru.spring5.films.POJO.Series;
import com.tru.spring5.films.commands.SeriesCommand;
import com.tru.spring5.films.converters.SeriesCommandToSeries;
import com.tru.spring5.films.converters.SeriesToSeriesCommand;
import com.tru.spring5.films.repositories.SeriesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

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
    public Set<Series> getSeries() {

        log.debug("method getSeries() in SeriesServiceImpl");
       Set<Series> seriesSet = new HashSet<>();
       seriesRepository.findAll().iterator().forEachRemaining(seriesSet::add);

        return seriesSet;
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
