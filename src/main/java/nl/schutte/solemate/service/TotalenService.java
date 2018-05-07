/*
 * Copyright 2009-2018 PIANOo; TenderNed programma.
 */
package nl.schutte.solemate.service;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import nl.schutte.solemate.model.Schoen;
import nl.schutte.solemate.model.TrainingType;

/**
 * SoleMate - Description.
 */
public interface TotalenService {

    public int berekenTotaalVoorJaar(Year year);

    public SortedMap<Year, Integer> getTotalenPerJaar();

    public SortedMap<Schoen, Integer> getTotalenPerSchoen();

    public int berekenTotaalVoorSchoen(Schoen schoen);

    public int berekenTotaal();

    public int berekenTotaalVoorTrainingType(TrainingType trainingType);

    public int berekenTotaalVoorTraining();

    public int berekenTotaalVoorWedstrijd();

}
