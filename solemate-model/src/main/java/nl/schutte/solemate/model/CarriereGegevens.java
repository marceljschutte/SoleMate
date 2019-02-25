package nl.schutte.solemate.model;

import java.time.Duration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarriereGegevens {

    private long totaalAantalMeters = 0;
    private Duration totaalTijd;
    private int aantalLoopjes = 0;

    public void addDuration(Duration duration) {
        if (totaalTijd == null) {
            totaalTijd = duration;
        } else {
            if(duration != null) {
                totaalTijd = totaalTijd.plus(duration);
            }
        }
    }

    public void addMeters(long meters) {
        totaalAantalMeters += meters;
    }

    public void addLoopjes(int aantal) { aantalLoopjes += aantal;}

    public boolean isEmpty() {
        return aantalLoopjes == 0 && totaalAantalMeters == 0;
    }
}
