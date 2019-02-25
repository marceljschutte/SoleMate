package nl.schutte.solemate.model;

import java.time.Duration;
import java.time.Year;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JaarGegevens {

    private Year jaar;
    private long totaalAantalMeters = 0;
    private Duration totaalTijd;
    private int aantalLoopjes;

    public void addDuration(Duration duration) {
        if (totaalTijd == null) {
            totaalTijd = duration;
        } else {
            totaalTijd = totaalTijd.plus(duration);
        }
    }

    public void addMeters(long meters) {
        totaalAantalMeters += meters;
    }
}
