package nl.schutte.solemate.model;


import lombok.AllArgsConstructor;


@AllArgsConstructor
public enum Merk {

    ASICS("Asics"),
    NEW_BALANCE("New Balance"),
    NIKE("Nike");

    private String description;

    public static Merk fromString(String text) {
        for (Merk m : Merk.values()) {
            if (m.description.equalsIgnoreCase(text)) {
                return m;
            }
        }
        throw new IllegalArgumentException();
    }


}
