package nl.schutte.solemate.app;


import java.time.Year;
import java.util.Iterator;
import java.util.List;
import lombok.AllArgsConstructor;
import nl.schutte.solemate.model.Training;
import nl.schutte.solemate.model.TrainingGegevens;
import nl.schutte.solemate.service.TrainingenService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TrainingenController {

    public static final String NEW_LINE = "<br>";
    public static final String BLANK_LINE = NEW_LINE + NEW_LINE;

    private final TrainingenService trainingenService;

    @GetMapping("/trainingen")
    public String trainingen() {
        StringBuilder result = new StringBuilder();
        result.append("Overzicht van alle trainingen: ").append(BLANK_LINE);
        List<Training> alleTrainingen = trainingenService.getAlleTrainingen();
        for (Training t : trainingenService.getAlleTrainingen()) {

            result.append(t.toString()).append(NEW_LINE);
        }
        return result.toString();
    }

    @GetMapping("/trainingsessions")
    public List<Training> trainingsessions() {
        return trainingenService.getAlleTrainingen();
    }

    @GetMapping("/trainingen/{jaar}")
    public String trainingenVoorJaar(@PathVariable int jaar) {
        Iterator it = trainingenService.getAlleTrainingenVoorJaar(Year.of(jaar)).getTrainingen().iterator();
        StringBuilder result = new StringBuilder();
        result.append("Overzicht van alle trainingen voor jaar " + jaar + ": ").append(BLANK_LINE);
        while (it.hasNext()) {
            Training training = (Training) it.next();
            result.append(training.toCompactString()).append(NEW_LINE);
        }
        return result.toString();
    }

    @GetMapping("/trainingsessions/{jaar}")
    public TrainingGegevens trainingsessionsVoorJaar(@PathVariable int jaar) {
        return trainingenService.getAlleTrainingenVoorJaar(Year.of(jaar));
    }

}
