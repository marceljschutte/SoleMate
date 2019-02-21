package nl.schutte.solemate.service;

import java.io.IOException;
import java.time.Year;
import nl.schutte.solemate.dao.ExcelDao;
import nl.schutte.solemate.model.CarriereGegevens;
import nl.schutte.solemate.model.JaarGegevens;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ExcelDaoTest.TestConfiguration.class})
public class ExcelDaoTest {

    @Autowired
    private ExcelDao excelDao;

    @Autowired
    private JaarTotalenService jaarTotalenService;
    @Autowired
    private CarriereTotalenService carriereTotalenService;

    @Test
    public void testJaargegevens() {
        JaarGegevens gegevensVanJaar = jaarTotalenService.getGegevensVanJaar(Year.of(2010));
    }

    @Test
    public void testCarriereGegevens() {
        CarriereGegevens carriereGegevens = carriereTotalenService.getGegevens();
    }


    @Test
    public void leesDataBestandTest() {
        try {
            excelDao.leesDataBestand();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }


    @Configuration
    @Import(TestSetupConfiguration.class)
    public static class TestConfiguration {
        // put test specific bean here
    }
}
