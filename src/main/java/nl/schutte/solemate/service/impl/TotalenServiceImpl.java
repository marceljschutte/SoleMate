/*
 * Copyright 2009-2018 PIANOo; TenderNed programma.
 */
package nl.schutte.solemate.service.impl;

import java.time.Year;
import java.util.Collections;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.inject.Inject;

import nl.schutte.solemate.dao.TotalenDao;
import nl.schutte.solemate.model.Schoen;
import nl.schutte.solemate.model.TrainingType;
import nl.schutte.solemate.service.SchoenService;
import nl.schutte.solemate.service.TotalenService;

/**
 * SoleMate - Description.
 */
public class TotalenServiceImpl implements TotalenService {

    @Inject
    private TotalenDao totalenDao;

    @Inject
    private SchoenService schoenService;

    @Override
    public int berekenTotaalVoorJaar(Year year) {
        // TODO: implement
        throw new UnsupportedOperationException("TODO: implement method berekenTotaalVoorJaar() --> int");

    }

    @Override
    public SortedMap<Year, Integer> getTotalenPerJaar() {

        SortedMap<Year, Integer> totalenPerJaar = new TreeMap<>();
        totalenPerJaar.put(Year.of(2010), 200);
        totalenPerJaar.put(Year.of(2011), 100);
        totalenPerJaar.put(Year.of(2012), 510);
        totalenPerJaar.put(Year.of(2013), 50);
        totalenPerJaar.put(Year.of(2014), 5060);
        totalenPerJaar.put(Year.of(2015), 5040);
        totalenPerJaar.put(Year.of(2016), 5030);
        totalenPerJaar.put(Year.of(2017), 5200);
        totalenPerJaar.put(Year.of(2018), 1500);
        return Collections.unmodifiableSortedMap(totalenPerJaar);
    }

    @Override
    public SortedMap<Schoen, Integer> getTotalenPerSchoen() {
        Set<Schoen> alleSchoenen = schoenService.getAlleSchoenen();

        SortedMap<Schoen, Integer> totalenPerJaar = new TreeMap<>();
        for (Schoen s : alleSchoenen) {
            totalenPerJaar.put(s, new Random().nextInt(1000));
        }

        return Collections.unmodifiableSortedMap(totalenPerJaar);
    }

    @Override
    public int berekenTotaalVoorSchoen(Schoen schoen) {
        // TODO: implement
        throw new UnsupportedOperationException("TODO: implement method berekenTotaalVoorSchoen() --> int");

    }

    @Override
    public int berekenTotaal() {
        // TODO: implement
        throw new UnsupportedOperationException("TODO: implement method berekenTotaal() --> int");

    }

    @Override
    public int berekenTotaalVoorTrainingType(TrainingType trainingType) {
        // TODO: implement
        throw new UnsupportedOperationException("TODO: implement method berekenTotaalVoorTrainingType() --> int");

    }

    @Override
    public int berekenTotaalVoorTraining() {
        // TODO: implement
        throw new UnsupportedOperationException("TODO: implement method berekenTotaalVoorTraining() --> int");

    }

    @Override
    public int berekenTotaalVoorWedstrijd() {
        // TODO: implement
        throw new UnsupportedOperationException("TODO: implement method berekenTotaalVoorWedstrijd() --> int");

    }
}
