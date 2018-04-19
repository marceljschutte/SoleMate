/*
 * Copyright 2009-2018 PIANOo; TenderNed programma.
 */
package nl.schutte.solemate.model;

import java.time.Duration;
import java.time.LocalDate;

/**
 * SoleMate - Description.
 */
public class Wedstrijd {

    private WedstrijdAfstand afstand;
    private Schoen schoen;
    private Duration gelopenTijd;
    private LocalDate datum;

    public Wedstrijd(WedstrijdAfstand afstand, Schoen schoen, Duration gelopenTijd, LocalDate datum) {
        this.afstand = afstand;
        this.schoen = schoen;
        this.gelopenTijd = gelopenTijd;
        this.datum = datum;
    }

    public WedstrijdAfstand getAfstand() {
        return afstand;
    }

    public void setAfstand(WedstrijdAfstand afstand) {
        this.afstand = afstand;
    }

    public Schoen getSchoen() {
        return schoen;
    }

    public void setSchoen(Schoen schoen) {
        this.schoen = schoen;
    }

    public Duration getGelopenTijd() {
        return gelopenTijd;
    }

    public void setGelopenTijd(Duration gelopenTijd) {
        this.gelopenTijd = gelopenTijd;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }
}
