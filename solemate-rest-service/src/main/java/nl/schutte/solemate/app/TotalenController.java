package nl.schutte.solemate.app;

import java.time.Year;
import java.util.Iterator;
import java.util.Map;
import lombok.AllArgsConstructor;
import nl.schutte.solemate.model.CarriereGegevens;
import nl.schutte.solemate.model.JaarGegevens;
import nl.schutte.solemate.service.CarriereTotalenService;
import nl.schutte.solemate.service.JaarTotalenService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TotalenController {

    public static final String NEW_LINE = "<br>";
    public static final String BLANK_LINE = NEW_LINE + NEW_LINE;

    private final CarriereTotalenService carriereTotalenService;

    private final JaarTotalenService jaarTotalenService;

    @GetMapping("/carriere")
    public String carriere() {
        return carriereTotalenService.getGegevens().toString();
    }

    @GetMapping("/carreer")
    public CarriereGegevens carreer() {
        return carriereTotalenService.getGegevens();
    }

    @GetMapping("/jaarcijfers/{jaar}")
    public String jaarcijfersVoorJaar(@PathVariable int jaar) {
        return jaarTotalenService.getGegevensVanJaar(Year.of(jaar)).toString();
    }

    @GetMapping("/yearfigures/{year}")
    public JaarGegevens yearfiguresForYear(@PathVariable int year) {
        return jaarTotalenService.getGegevensVanJaar(Year.of(year));
    }

    @GetMapping("/jaarcijfers")
    public String jaarcijfers() {
        Iterator it = jaarTotalenService.getGegevensVanAlleJaren().entrySet().iterator();
        StringBuilder result = new StringBuilder();
        result.append("Overzicht van alle jaren: ").append(BLANK_LINE);
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            result.append(pair.getKey()).append(": ").append(pair.getValue()).append(NEW_LINE);
        }
        return result.toString();
    }

    @GetMapping("/yearfigures")
    public Map<Year, JaarGegevens> yearfigures() {
        return jaarTotalenService.getGegevensVanAlleJaren();
    }
}
