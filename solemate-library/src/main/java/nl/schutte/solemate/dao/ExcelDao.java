package nl.schutte.solemate.dao;

import static nl.schutte.solemate.model.Constants.KOLOM_PERSOONLIJK_RECORD_ID;
import static nl.schutte.solemate.model.Constants.KOLOM_PERSOONLIJK_RECORD_WEDSTRIJD_ID;
import static nl.schutte.solemate.model.Constants.KOLOM_SCHOEN_ACTIEF;
import static nl.schutte.solemate.model.Constants.KOLOM_SCHOEN_BEGINDATUM;
import static nl.schutte.solemate.model.Constants.KOLOM_SCHOEN_EDITIE;
import static nl.schutte.solemate.model.Constants.KOLOM_SCHOEN_EINDDATUM;
import static nl.schutte.solemate.model.Constants.KOLOM_SCHOEN_ID;
import static nl.schutte.solemate.model.Constants.KOLOM_SCHOEN_MERK;
import static nl.schutte.solemate.model.Constants.KOLOM_SCHOEN_TYPE;
import static nl.schutte.solemate.model.Constants.KOLOM_TRAINING_AFSTAND;
import static nl.schutte.solemate.model.Constants.KOLOM_TRAINING_DATUM;
import static nl.schutte.solemate.model.Constants.KOLOM_TRAINING_HARTSLAG_GEMIDDELD;
import static nl.schutte.solemate.model.Constants.KOLOM_TRAINING_HARTSLAG_MAX;
import static nl.schutte.solemate.model.Constants.KOLOM_TRAINING_ID;
import static nl.schutte.solemate.model.Constants.KOLOM_TRAINING_SCHOEN_ID;
import static nl.schutte.solemate.model.Constants.KOLOM_TRAINING_TIJD_MINUTEN;
import static nl.schutte.solemate.model.Constants.KOLOM_TRAINING_TIJD_SECONDEN;
import static nl.schutte.solemate.model.Constants.KOLOM_TRAINING_TIJD_UREN;
import static nl.schutte.solemate.model.Constants.KOLOM_TRAINING_TYPE;
import static nl.schutte.solemate.model.Constants.KOLOM_WEDSTRIJD_AFSTAND;
import static nl.schutte.solemate.model.Constants.KOLOM_WEDSTRIJD_DATUM;
import static nl.schutte.solemate.model.Constants.KOLOM_WEDSTRIJD_ID;
import static nl.schutte.solemate.model.Constants.KOLOM_WEDSTRIJD_NAAM;
import static nl.schutte.solemate.model.Constants.KOLOM_WEDSTRIJD_OMSCHRIJVING;
import static nl.schutte.solemate.model.Constants.KOLOM_WEDSTRIJD_SCHOEN_ID;
import static nl.schutte.solemate.model.Constants.KOLOM_WEDSTRIJD_TIJD_MINUTEN;
import static nl.schutte.solemate.model.Constants.KOLOM_WEDSTRIJD_TIJD_SECONDEN;
import static nl.schutte.solemate.model.Constants.KOLOM_WEDSTRIJD_TIJD_UREN;
import static nl.schutte.solemate.util.DataUtil.createDuration;
import static nl.schutte.solemate.util.DataUtil.formatCell;
import static nl.schutte.solemate.util.DataUtil.parseDateDDMMYY;
import static nl.schutte.solemate.util.DataUtil.parseDateYYYYMMDD;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import nl.schutte.solemate.model.HardloopData;
import nl.schutte.solemate.model.Merk;
import nl.schutte.solemate.model.PersoonlijkRecord;
import nl.schutte.solemate.model.Schoen;
import nl.schutte.solemate.model.Training;
import nl.schutte.solemate.model.Wedstrijd;
import nl.schutte.solemate.model.Werkblad;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Component;

@Component
public class ExcelDao {

    private static String DATABESTAND = "solemate-data.xlsx";

    private Map<Werkblad, Sheet> werkbladen = new HashMap<>();

    private HardloopData hardloopData = new HardloopData();

    public HardloopData leesDataBestand() throws IOException, InvalidFormatException {

        if(!hardloopData.isEmpty()){
            return hardloopData;
        }

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(DATABESTAND).getFile());

        Workbook workbook = WorkbookFactory.create(file);

        for (Sheet s : workbook) {
            werkbladen.put(Werkblad.fromString(s.getSheetName()), s);
        }

        if (werkbladen.containsKey(Werkblad.SCHOENEN)) {
            readSchoenen();
        }
        if (werkbladen.containsKey(Werkblad.WEDSTRIJDEN)) {
            readWedstrijden();
        }
        if (werkbladen.containsKey(Werkblad.TRAININGEN)) {
            readTrainingen();
        }
        if (werkbladen.containsKey(Werkblad.PERSOONLIJKE_RECORDS)) {
            readPersoonlijkeRecords();
        }

        return hardloopData;
    }

    private void readSchoenen() {
        for (Row row : werkbladen.get(Werkblad.SCHOENEN)) {
            if (row.getRowNum() > 0) {
                Schoen schoen = createSchoen(row);
                hardloopData.getSchoenenLijst().put(schoen.getId(), schoen);
            }
        }
    }

    private void readWedstrijden() {
        for (Row row : werkbladen.get(Werkblad.WEDSTRIJDEN)) {
            if (row.getRowNum() > 0) {
                Wedstrijd wedstrijd = createWedstrijd(row);
                hardloopData.getWedstrijdLijst().put(wedstrijd.getId(), wedstrijd);
            }
        }
    }

    private void readPersoonlijkeRecords() {
        for (Row row : werkbladen.get(Werkblad.PERSOONLIJKE_RECORDS)) {
            if (row.getRowNum() > 0) {
                PersoonlijkRecord pr = createPersoonlijkRecord(row);
                hardloopData.getPersoonlijkRecordLijst().put(pr.getWedstrijd().getWedstrijdafstand(), pr);
            }
        }
    }

    private void readTrainingen() {
        for (Row row : werkbladen.get(Werkblad.TRAININGEN)) {
            if (row.getRowNum() > 0) {
                hardloopData.getTrainingLijst().add(createTraining(row));
            }
        }
    }

    private Schoen createSchoen(Row row) {
        return Schoen.builder().id(Long.valueOf(formatCell(row.getCell(KOLOM_SCHOEN_ID))))
                .merk(Merk.fromString(formatCell(row.getCell(KOLOM_SCHOEN_MERK))))
                .editie(formatCell(row.getCell(KOLOM_SCHOEN_EDITIE)))
                .type(Schoen.Type.fromString(formatCell(row.getCell(KOLOM_SCHOEN_TYPE))))
                .beginDatum(parseDateYYYYMMDD(formatCell(row.getCell(KOLOM_SCHOEN_BEGINDATUM))))
                .actief(Boolean.valueOf(formatCell(row.getCell(KOLOM_SCHOEN_ACTIEF))))
                .eindDatum(!Boolean.valueOf(formatCell(row.getCell(KOLOM_SCHOEN_ACTIEF))) ? parseDateYYYYMMDD(formatCell(row.getCell(KOLOM_SCHOEN_EINDDATUM))) : null)
                .build();
    }

    private Wedstrijd createWedstrijd(Row row) {
        return Wedstrijd.builder()
                .id(Long.valueOf(formatCell(row.getCell(KOLOM_WEDSTRIJD_ID))))
                .wedstrijd(formatCell(row.getCell(KOLOM_WEDSTRIJD_NAAM)))
                .wedstrijdafstand(Wedstrijd.WedstrijdType.fromString(formatCell(row.getCell(KOLOM_WEDSTRIJD_OMSCHRIJVING))))
                .datum(parseDateDDMMYY(formatCell(row.getCell(KOLOM_WEDSTRIJD_DATUM))))
                .afstand(Integer.parseInt(formatCell(row.getCell(KOLOM_WEDSTRIJD_AFSTAND))))
                .schoen(hardloopData.getSchoenenLijst().get(Long.valueOf(formatCell(row.getCell(KOLOM_WEDSTRIJD_SCHOEN_ID)))))
                .gelopenTijd(createDuration(formatCell(row.getCell(KOLOM_WEDSTRIJD_TIJD_UREN)), formatCell(row.getCell(KOLOM_WEDSTRIJD_TIJD_MINUTEN)), formatCell(row.getCell(KOLOM_WEDSTRIJD_TIJD_SECONDEN))))
                .build();
    }

    private PersoonlijkRecord createPersoonlijkRecord(Row row) {
        return PersoonlijkRecord.builder()
                .id(Long.valueOf(formatCell(row.getCell(KOLOM_PERSOONLIJK_RECORD_ID))))
                .wedstrijd(hardloopData.getWedstrijdLijst().get(Long.valueOf(formatCell(row.getCell(KOLOM_PERSOONLIJK_RECORD_WEDSTRIJD_ID)))))
                .build();
    }

    private Training createTraining(Row row) {
        return Training.builder()
                .id(Long.valueOf(formatCell(row.getCell(KOLOM_TRAINING_ID))))
                .trainingsTijd(createDuration(formatCell(row.getCell(KOLOM_TRAINING_TIJD_UREN)), formatCell(row.getCell(KOLOM_TRAINING_TIJD_MINUTEN)), formatCell(row.getCell(KOLOM_TRAINING_TIJD_SECONDEN))))
                .datum(parseDateDDMMYY(formatCell(row.getCell(KOLOM_TRAINING_DATUM))))
                .afstand(Integer.parseInt(formatCell(row.getCell(KOLOM_TRAINING_AFSTAND))))
                .gemiddeldeHartslag(Integer.parseInt(formatCell(row.getCell(KOLOM_TRAINING_HARTSLAG_GEMIDDELD))))
                .maximaleHartslag(Integer.parseInt(formatCell(row.getCell(KOLOM_TRAINING_HARTSLAG_MAX))))
                .schoen(hardloopData.getSchoenenLijst().get(Long.valueOf(formatCell(row.getCell(KOLOM_TRAINING_SCHOEN_ID)))))
                .trainingsType(row.getCell(KOLOM_TRAINING_TYPE) != null ? Training.Type.fromString(formatCell(row.getCell(KOLOM_TRAINING_TYPE))) : null)
                .build();
    }
}
