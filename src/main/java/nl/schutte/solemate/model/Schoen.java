/*
 * Copyright 2009-2018 PIANOo; TenderNed programma.
 */
package nl.schutte.solemate.model;

import java.time.Period;

/**
 * SoleMate - Description.
 */
public class Schoen {

    private Period periode;
    private SchoenType schoenType;
    private SchoenMerk schoenMerk;

    public Schoen(Period periode, SchoenType schoenType, SchoenMerk schoenMerk) {
        this.periode = periode;
        this.schoenType = schoenType;
        this.schoenMerk = schoenMerk;
    }

    public Period getPeriode() {
        return periode;
    }

    public void setPeriode(Period periode) {
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
}
