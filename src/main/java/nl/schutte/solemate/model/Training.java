/*
 * Copyright 2009-2018 PIANOo; TenderNed programma.
 */
package nl.schutte.solemate.model;

import java.time.Duration;
import java.time.LocalDate;

/**
 * SoleMate - Description.
 */
public class Training {

    private Schoen schoen;
    private TrainingType trainingType;
    private double aantalKilometer;
    private Duration duur;
    private LocalDate datum;

    public Training(Schoen schoen, TrainingType trainingType, double aantalKilometer, Duration duur, LocalDate datum) {
        this.schoen = schoen;
        this.trainingType = trainingType;
        this.aantalKilometer = aantalKilometer;
        this.duur = duur;
        this.datum = datum;
    }

    public Schoen getSchoen() {
        return schoen;
    }

    public void setSchoen(Schoen schoen) {
        this.schoen = schoen;
    }

    public TrainingType getTrainingType() {
        return trainingType;
    }

    public void setTrainingType(TrainingType trainingType) {
        this.trainingType = trainingType;
    }

    public double getAantalKilometer() {
        return aantalKilometer;
    }

    public void setAantalKilometer(double aantalKilometer) {
        this.aantalKilometer = aantalKilometer;
    }

    public Duration getDuur() {
        return duur;
    }

    public void setDuur(Duration duur) {
        this.duur = duur;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }
}
