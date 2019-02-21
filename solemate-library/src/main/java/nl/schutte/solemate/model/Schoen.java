package nl.schutte.solemate.model;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Schoen {

    private Long id;
    private Merk merk;
    private String editie;
    private Type type;
    private LocalDate beginDatum, eindDatum;
    private boolean actief;

    @AllArgsConstructor
    public enum Type {
        OUTDOOR("Outdoor"),
        INDOOR("Indoor"),
        TRAIL("Trail"),
        WEDSTRIJD("Wedstrijd");

        private String description;

        public static Type fromString(String text) {
            for (Type t : Type.values()) {
                if (t.description.equalsIgnoreCase(text)) {
                    return t;
                }
            }
            throw new IllegalArgumentException();
        }
    }
}
