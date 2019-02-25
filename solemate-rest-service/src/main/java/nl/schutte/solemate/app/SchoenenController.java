package nl.schutte.solemate.app;

import java.util.Collection;
import java.util.Map;
import lombok.AllArgsConstructor;
import nl.schutte.solemate.model.Schoen;
import nl.schutte.solemate.model.SchoenGegevens;
import nl.schutte.solemate.service.SchoenenService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SchoenenController {

    public static final String NEW_LINE = "<br>";
    public static final String BLANK_LINE = NEW_LINE + NEW_LINE;

    private final SchoenenService schoenenService;

    @GetMapping("/schoenen")
    public String schoenen() {
        StringBuilder result = new StringBuilder();
        result.append("Overzicht van alle schoenen: ").append(BLANK_LINE);
        Collection<Schoen> alleSchoenen = schoenenService.getSchoenen();
        for (Schoen s : alleSchoenen) {
            result.append(s.toString()).append(NEW_LINE);
        }
        return result.toString();
    }

    @GetMapping("/shoes")
    public Collection<Schoen> shoes() {
        return schoenenService.getSchoenen();
    }


    @GetMapping("/schoenen/totalen")
    public String schoentotalen() {
        StringBuilder result = new StringBuilder();
        result.append("Overzicht van alle totalen per schoen: ").append(BLANK_LINE);
        Map<Schoen, SchoenGegevens> schoenTotalen = schoenenService.getSchoenTotalen();
        for (Map.Entry<Schoen, SchoenGegevens> schoenEntry : schoenTotalen.entrySet()) {
            result.append(schoenEntry.getValue().toString()).append(NEW_LINE);
        }
        return result.toString();
    }

    @GetMapping("/shoes/totals")
    public Map<Schoen, SchoenGegevens> shoetotals() {
        return schoenenService.getSchoenTotalen();
    }



    @GetMapping("/schoenen/totalen/{id}")
    public String getSchoen(@PathVariable String id) {

        Schoen geselecteerdeSchoen = new Schoen();
        for (Schoen s : schoenenService.getSchoenen()) {
            if (s.getId().equals(Long.valueOf(id))) {
                geselecteerdeSchoen = s;
                break;
            }
        }
        return schoenenService.getSchoenTotalen().get(geselecteerdeSchoen).toString();
    }

    @GetMapping("/shoes/totals/{id}")
    public SchoenGegevens getShoe(@PathVariable String id) {

        Schoen geselecteerdeSchoen = new Schoen();
        for (Schoen s : schoenenService.getSchoenen()) {
            if (s.getId().equals(Long.valueOf(id))) {
                geselecteerdeSchoen = s;
                break;
            }
        }
        return schoenenService.getSchoenTotalen().get(geselecteerdeSchoen);
    }
}
