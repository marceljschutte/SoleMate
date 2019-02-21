package nl.schutte.solemate.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Werkblad {

    SCHOENEN("schoenen"),
    WEDSTRIJDEN("wedstrijden"),
    PERSOONLIJKE_RECORDS("pr-s"),
    TRAININGEN("trainingen");

    private String description;

    public static Werkblad fromString(String text) {
        for (Werkblad s : Werkblad.values()) {
            if (s.description.equalsIgnoreCase(text)) {
                return s;
            }
        }
        return null;
    }
}
