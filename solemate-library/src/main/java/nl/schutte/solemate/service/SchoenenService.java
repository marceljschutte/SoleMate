package nl.schutte.solemate.service;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import nl.schutte.solemate.dao.ExcelDao;
import nl.schutte.solemate.model.HardloopData;
import nl.schutte.solemate.model.Schoen;
import nl.schutte.solemate.model.SchoenGegevens;
import nl.schutte.solemate.model.Training;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SchoenenService {

    private Map<Schoen, SchoenGegevens> schoenGegevens = new HashMap<>();

    private HardloopData hardloopData = new HardloopData();

    @Autowired
    private ExcelDao excelDao;

    public Map<Schoen, SchoenGegevens> getSchoenTotalen() {
        init();
        calculateTotals();
        return schoenGegevens;
    }

    public Collection<Schoen> getSchoenen() {
        init();
        return hardloopData.getSchoenenLijst().values();
    }

    private void init() {
        if (schoenGegevens.isEmpty()) {
            retrieveData();

        }
    }

    private void calculateTotals() {
        for (Map.Entry<Long, Schoen> schoenEntry : hardloopData.getSchoenenLijst().entrySet()) {
            List<Training> gefilterdeTrainingen = filterDataForSchoe(schoenEntry.getValue());
            //hier jaardata aanmaken
            SchoenGegevens schoenData = new SchoenGegevens();

            for (Training t : gefilterdeTrainingen) {
                schoenData.addDuration(t.getTrainingsTijd());
                schoenData.addMeters(t.getAfstand());
            }

            schoenData.setAantalLoopjes(gefilterdeTrainingen.size());
            schoenData.setSchoen(schoenEntry.getValue());

            schoenGegevens.put(schoenEntry.getValue(), schoenData);
        }
    }

    private List<Training> filterDataForSchoe(Schoen schoen) {
        return hardloopData.getTrainingLijst().stream()
                .filter(training -> training.getSchoen().equals(schoen))
                .collect(Collectors.toList());
    }

    private void retrieveData() {
        try {
            hardloopData = excelDao.leesDataBestand();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }
}
