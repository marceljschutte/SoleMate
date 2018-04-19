/*
 * Copyright 2009-2018 PIANOo; TenderNed programma.
 */
package nl.schutte.solemate.model;

/**
 * SoleMate - De afstanden die tijdens een wedstrijd gelopen kunnen worden
 */
public enum WedstrijdAfstand {

    VIJF_K(5000),
    VIER_MIJL(6437),
    TIEN_K(10000),
    TIEN_MIJL(16100),
    HALVE_MARATHON(210975),
    MARATHON(42195);

    private int afstand;

    WedstrijdAfstand(int afstand) {
        this.afstand = afstand;
    }

    public WedstrijdAfstand of(int afstand){
        for (WedstrijdAfstand wa: WedstrijdAfstand.values()){
            if(wa.getAfstand() == afstand) {
                return wa;
            }
        }
        throw new IllegalArgumentException(String.format("Wedstrijdafstand van %s meter niet gevonden", afstand));
    }

    public int getAfstand() {
        return afstand;
    }

    public void setAfstand(int afstand) {
        this.afstand = afstand;
    }
}
