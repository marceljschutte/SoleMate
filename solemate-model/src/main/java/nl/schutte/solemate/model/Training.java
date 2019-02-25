package nl.schutte.solemate.model;

import java.time.Duration;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Training {

    private Long id;
    private Duration trainingsTijd;
    // afstand in meters
    private int afstand;
    private LocalDate datum;
    private Type trainingsType;
    private int maximaleHartslag;
    private int gemiddeldeHartslag;
    private Schoen schoen;

    public long getTotaleTijdInSeconden() {
        return trainingsTijd.getSeconds();
    }

    @AllArgsConstructor
    public enum Type {

        DUURLOOP1("Duurloop 1"),
        DUURLOOP2("Duurloop 2"),
        INTERVAL("Interval"),
        WEDSTRIJD("Wedstrijd"),
        HEUVEL("Heuveltraining");

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

    public String toCompactString(){
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("datum", datum)
                .append("type", trainingsType)
                .append("afstand", afstand)
                .append("duur", trainingsTijd)
                .toString();
    }
}
