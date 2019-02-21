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
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JaarTotalenService {

    private Map<Year, JaarGegevens> jaarGegevens = new HashMap<>();

    private HardloopData hardloopData = new HardloopData();

    @Autowired
    private ExcelDao excelDao;

    public JaarGegevens getGegevensVanJaar(Year year){
        init();
        if(year.isAfter(Year.of(Constants.EERSTE_JAAR_HARDLOPEN-1))){
            return jaarGegevens.get(year);
        } else {
            throw new NoRunningException(String.format("In dit jaar: %s liep ik niet hard", year.getValue()));
        }
    }

    private void init() {
        if(jaarGegevens.isEmpty()) {
            retrieveData();
            calculateTotals();
        }
    }

    public Map<Year, JaarGegevens> getGegevensVanAlleJaren(){
        init();
        return jaarGegevens;
    }
    private void calculateTotals() {
        for (int i = Constants.EERSTE_JAAR_HARDLOPEN; i <= Year.now().getValue(); i++){
            List<Training> gefilterdeTrainingen = filterDataForYear(i);
            //hier jaardata aanmaken
            JaarGegevens jaarData = new JaarGegevens();

            for (Training t : gefilterdeTrainingen) {
                jaarData.addDuration(t.getTrainingsTijd());
                jaarData.addMeters(t.getAfstand());
            }
            jaarData.setAantalLoopjes(gefilterdeTrainingen.size());
            jaarData.setJaar(Year.of(i));

            jaarGegevens.put(Year.of(i), jaarData);
        }
    }

    private List<Training> filterDataForYear(int year) {
        return hardloopData.getTrainingLijst().stream()           
                .filter(training -> training.getDatum().getYear() == year)
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
