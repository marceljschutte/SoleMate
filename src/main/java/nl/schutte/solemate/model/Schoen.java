/*
 * Copyright 2009-2018 PIANOo; TenderNed programma.
 */
package nl.schutte.solemate.model;

import java.time.Period;

/**
 * SoleMate - Description.
 */
public class Schoen implements Comparable {

    private String omschrijving;
    private Periode periode;
    private SchoenType schoenType;
    private SchoenMerk schoenMerk;

    public Schoen(String omschrijving, Periode periode, SchoenType schoenType, SchoenMerk schoenMerk) {
        this.omschrijving = omschrijving;
        this.periode = periode;
        this.schoenType = schoenType;
        this.schoenMerk = schoenMerk;
    }

    private Schoen(){

    }

    public Periode getPeriode() {
        return periode;
    }

    public void setPeriode(Periode periode) {
        this.periode = periode;
    }

    public SchoenType getSchoenType() {
        return schoenType;
    }

    public void setSchoenType(SchoenType schoenType) {
        this.schoenType = schoenType;
    }

    public SchoenMerk getSchoenMerk() {
        return schoenMerk;
    }

    public void setSchoenMerk(SchoenMerk schoenMerk) {
        this.schoenMerk = schoenMerk;
    }

    @Override
    public int compareTo(Object o) {
        Schoen other = (Schoen) o;
        return this.periode.getStartDatum().isBefore(((Schoen) o).getPeriode().getStartDatum()) ? 1 : -1;
    }

    @Override
    public String toString() {
        return periode.toString() + " - " + schoenMerk + " " + omschrijving;
    }
}
