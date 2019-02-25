package nl.schutte.solemate.util;

import java.time.Duration;
import java.time.LocalDate;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;

public  class DataUtil {

    public static String formatCell(Cell cell){
        DataFormatter dataFormatter = new DataFormatter();
        return dataFormatter.formatCellValue(cell);
    }

    public static Duration createDuration(String uren, String minuten, String seconden) {
        long uur, minuut, seconde;
        uur = Long.parseLong(uren);
        minuut = Long.parseLong(minuten);
        seconde = Long.parseLong(seconden);
        long totaalSeconden = (uur * 60 * 60) + (minuut * 60) + seconde;

        return Duration.ofSeconds(totaalSeconden);
    }

    //for YYYYMMDD
    public static LocalDate parseDateYYYYMMDD(String datum) {
        int jaar, maand, dag;
        jaar = Integer.parseInt(StringUtils.substring(datum, 0, 4));
        maand = Integer.parseInt(StringUtils.substring(datum, 4, 6));
        dag = Integer.parseInt(StringUtils.substring(datum, 6, 8));
        return LocalDate.of(jaar, maand, dag);
    }

    //for DD/MM/YY
    public static LocalDate parseDateDDMMYY(String datum) {
        int jaar, maand, dag;
        dag = Integer.parseInt(StringUtils.substring(datum, 0, 2));
        maand = Integer.parseInt(StringUtils.substring(datum, 3, 5));
        jaar = Integer.parseInt(20 + StringUtils.substring(datum, 6, 8));
        return LocalDate.of(jaar, maand, dag);
    }
}
