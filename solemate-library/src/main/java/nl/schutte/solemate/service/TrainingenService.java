package nl.schutte.solemate.service;

import java.io.IOException;
import java.time.Year;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import nl.schutte.solemate.dao.ExcelDao;
import nl.schutte.solemate.model.Constants;
import nl.schutte.solemate.model.HardloopData;
import nl.schutte.solemate.model.JaarGegevens;
import nl.schutte.solemate.model.NoRunningException;
import nl.schutte.solemate.model.Training;
import nl.schutte.solemate.model.TrainingGegevens;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TrainingenService {

    private HardloopData hardloopData = new HardloopData();

    private Map<Year, TrainingGegevens> jaarGegevens = new HashMap<>();

    @Autowired
    private ExcelDao excelDao;

    public List<Training> getAlleTrainingen(){
        init();
        return hardloopData.getTrainingLijst();
    }

    public Map<Year, TrainingGegevens> getGegevensVanAlleJaren(){
        init();
        return jaarGegevens;
    }

    public TrainingGegevens getAlleTrainingenVoorJaar(Year year) {
        init();
        if(year.isAfter(Year.of(Constants.EERSTE_JAAR_HARDLOPEN-1))){
            return jaarGegevens.get(year);
        } else {
            throw new NoRunningException(String.format("In dit jaar: %s liep ik niet hard", year.getValue()));
        }
    }

    private void calculateTotals() {
        for (int i = Constants.EERSTE_JAAR_HARDLOPEN; i <= Year.now().getValue(); i++){
            List<Training> gefilterdeTrainingen = filterDataForYear(Year.of(i));
            //hier jaardata aanmaken
            TrainingGegevens trainingGegevens = new TrainingGegevens();
            trainingGegevens.setJaar(Year.of(i));
            trainingGegevens.setTrainingen(gefilterdeTrainingen);

            jaarGegevens.put(Year.of(i), trainingGegevens);
        }
    }

    private void init() {
        if(hardloopData.isEmpty()) {
            retrieveData();
            calculateTotals();
        }
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


    private List<Training> filterDataForYear(Year year) {
        int size = hardloopData.getTrainingLijst().size();
        return hardloopData.getTrainingLijst().stream()
                .filter(training -> training.getDatum().getYear() == year.getValue())
                .collect(Collectors.toList());
    }
}
