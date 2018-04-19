/*
 * Copyright 2009-2018 PIANOo; TenderNed programma.
 */
package nl.schutte.solemate.service.impl;

import java.time.Year;

import javax.inject.Inject;

import nl.schutte.solemate.dao.TotalenDao;
import nl.schutte.solemate.model.Schoen;
import nl.schutte.solemate.model.TrainingType;
import nl.schutte.solemate.service.TotalenService;

/**
 * SoleMate - Description.
 */
public class TotalenServiceImpl implements TotalenService {

    @Inject
    private TotalenDao totalenDao;

    @Override
    public int berekenTotaalVoorJaar(Year year) {
        // TODO: implement
        throw new UnsupportedOperationException("TODO: implement method berekenTotaalVoorJaar() --> int");

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
