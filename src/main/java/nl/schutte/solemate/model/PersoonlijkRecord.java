/*
 * Copyright 2009-2018 PIANOo; TenderNed programma.
 */
package nl.schutte.solemate.model;

/**
 * SoleMate - Description.
 */
public class PersoonlijkRecord {

    private Wedstrijd wedstrijd;
    private WedstrijdAfstand wedstrijdAfstand;

    public PersoonlijkRecord(Wedstrijd wedstrijd, WedstrijdAfstand wedstrijdAfstand) {
        this.wedstrijd = wedstrijd;
        this.wedstrijdAfstand = wedstrijdAfstand;
    }

    public Wedstrijd getWedstrijd() {
        return wedstrijd;
    }

    public void setWedstrijd(Wedstrijd wedstrijd) {
        this.wedstrijd = wedstrijd;
    }

    public WedstrijdAfstand getWedstrijdAfstand() {
        return wedstrijdAfstand;
    }

    public void setWedstrijdAfstand(WedstrijdAfstand wedstrijdAfstand) {
        this.wedstrijdAfstand = wedstrijdAfstand;
    }
}
