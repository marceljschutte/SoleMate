/*
 * Copyright 2009-2018 PIANOo; TenderNed programma.
 */
package nl.schutte.solemate.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * SoleMate - Description.
 */
public class Periode {

    private LocalDate startDatum;
    private LocalDate eindDatum;

    private DateTimeFormatter formatter;

    public Periode(LocalDate startDatum, LocalDate eindDatum) {
        this.startDatum = startDatum;
        this.eindDatum = eindDatum;
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    public LocalDate getStartDatum() {
        return startDatum;
    }

    public LocalDate getEindDatum() {
        return eindDatum;
    }

    @Override
    public String toString() {
        return startDatum.format(formatter) + " - " + eindDatum != null ? eindDatum.format(formatter) : "heden";
    }
}
