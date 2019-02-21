package nl.schutte.solemate.service;

import java.time.Year;
import nl.schutte.solemate.model.CarriereGegevens;
import nl.schutte.solemate.model.Constants;
import nl.schutte.solemate.model.JaarGegevens;
import nl.schutte.solemate.model.NoRunningException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarriereTotalenService {

    @Autowired
    private JaarTotalenService jaarTotalenService;

    private CarriereGegevens carriereGegevens = new CarriereGegevens();

    public CarriereGegevens getGegevens() {
        if (carriereGegevens.isEmpty() ) {
            retrieveData();
        }
        return carriereGegevens;
    }


    private void retrieveData() {
        carriereGegevens = new CarriereGegevens();
        for (int i = Constants.EERSTE_JAAR_HARDLOPEN; i <= Year.now().getValue(); i++) {
            JaarGegevens gegevensVanJaar = jaarTotalenService.getGegevensVanJaar(Year.of(i));
            carriereGegevens.addDuration(gegevensVanJaar.getTotaalTijd());
            carriereGegevens.addMeters(gegevensVanJaar.getTotaalAantalMeters());
            carriereGegevens.addLoopjes(gegevensVanJaar.getAantalLoopjes());
        }
    }
}
