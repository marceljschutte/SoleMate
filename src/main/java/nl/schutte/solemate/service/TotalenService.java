/*
 * Copyright 2009-2018 PIANOo; TenderNed programma.
 */
package nl.schutte.solemate.service;

import java.time.Year;

import nl.schutte.solemate.model.Schoen;
import nl.schutte.solemate.model.TrainingType;

/**
 * SoleMate - Description.
 */
public interface TotalenService {

    public int berekenTotaalVoorJaar(Year year);

    public int berekenTotaalVoorSchoen(Schoen schoen);

    public int berekenTotaal();

    public int berekenTotaalVoorTrainingType(TrainingType trainingType);

    public int berekenTotaalVoorTraining();

    public int berekenTotaalVoorWedstrijd();

}
