package nl.schutte.solemate.model;

import java.time.Duration;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Wedstrijd {

    private Long id;
    private String wedstrijd;
    private WedstrijdType wedstrijdafstand;
    //in meters
    private int afstand;
    private LocalDate datum;
    private Duration gelopenTijd;
    private Schoen schoen;


    @AllArgsConstructor
    public enum WedstrijdType {

        VIJFK("5K", 5000),
        VIERMIJL("4 mijl", 6437),
        TIENK("10K", 10000),
        VIJFTIENK("15K", 15000),
        TIENENGELSEMIJL("10 Engelse Mijl", 16100),
        HALVEMARATHON("Halve Marathon", 21100),
        MARATHON("Marathon", 42195),
        NONCONVENTIONAL("Niet conventionele afstand", 0);

        private String description;
        private int afstandInMeters;


        public static WedstrijdType fromString(String text) {
            for (WedstrijdType wa : WedstrijdType.values()) {
                if (wa.description.equalsIgnoreCase(text)) {
                    return wa;
                }
            }

            throw new IllegalArgumentException();
        }
    }
}