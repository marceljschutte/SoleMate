package nl.schutte.solemate.app;

import java.time.Year;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import nl.schutte.solemate.model.Schoen;
import nl.schutte.solemate.model.SchoenGegevens;
import nl.schutte.solemate.model.Training;
import nl.schutte.solemate.service.CarriereTotalenService;
import nl.schutte.solemate.service.JaarTotalenService;
import nl.schutte.solemate.service.SchoenenService;
import nl.schutte.solemate.service.TrainingenService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = "nl.schutte.solemate")
@RestController
@AllArgsConstructor
public class SoleMateApplication {

    public static final String NEW_LINE = "<br>";
    public static final String BLANK_LINE = NEW_LINE + NEW_LINE;

    private final SchoenenService schoenenService;

    private final CarriereTotalenService carriereTotalenService;

    private final JaarTotalenService jaarTotalenService;

    private final TrainingenService trainingenService;

    @GetMapping("/")
    public String home() {
        return linkoverview();
    }

    private String linkoverview() {
        StringBuilder overview = new StringBuilder();
        overview.append("SoleMate -- Een geschiedenis van Marcel's hardloopverleden...").append(BLANK_LINE)
                .append("<a href=/carriere>Carriere overzicht</a>")
        .append(BLANK_LINE).append(addJarenVoorJaarcijfers())
                .append(BLANK_LINE).append(addTrainingen())
                .append(BLANK_LINE).append(addSchoenen());
        return overview.toString();
    }

    private String addJarenVoorJaarcijfers() {
        StringBuilder jaren = new StringBuilder();

        Iterator it = jaarTotalenService.getGegevensVanAlleJaren().entrySet().iterator();
        jaren.append("Jaartotalen: ").append(BLANK_LINE);
        jaren.append("<a href=/jaarcijfers>Alle Jaren</a>").append(BLANK_LINE);
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            jaren.append("<a href=/jaarcijfers/"+ pair.getKey().toString()+">" + pair.getKey().toString()+"</a>").append(NEW_LINE);
        }
        return jaren.toString();
    }

    private String addTrainingen() {
        StringBuilder trainingen = new StringBuilder();

        Iterator it = trainingenService.getGegevensVanAlleJaren().entrySet().iterator();
        trainingen.append("Jaaroverzichten: ").append(BLANK_LINE);
        trainingen.append("<a href=/trainingen>Alle Trainingen</a>").append(BLANK_LINE);
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            trainingen.append("<a href=/trainingen/"+ pair.getKey().toString()+">" + pair.getKey().toString()+"</a>").append(NEW_LINE);
        }
        return trainingen.toString();
    }

    private String addSchoenen() {
        StringBuilder schoenen = new StringBuilder();

        Iterator it = schoenenService.getSchoenTotalen().entrySet().iterator();
        schoenen.append("Schoenen: ").append(BLANK_LINE);
        schoenen.append("<a href=/schoenen >Alle Schoenen</a>")
                .append(BLANK_LINE)
                .append("<a href=/schoenen/totalen >Alle schoentotalen</a>").append(BLANK_LINE);

        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Schoen schoen = (Schoen) pair.getKey();
            schoenen.append("<a href=/schoenen/totalen/"+ schoen.getId().toString() +">" + schoen.getMerk() + " " + schoen.getEditie() + "</a>").append(NEW_LINE);
        }
        return  schoenen.toString();

    }





    public static void main(String[] args) {
        SpringApplication.run(SoleMateApplication.class, args);
    }
}
