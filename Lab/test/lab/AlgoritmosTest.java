package lab;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author juanmanuel
 */
public class AlgoritmosTest {

    @Test
    public void testGetValidTimeInRange() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println("getValidTimeInRange");
        // =====================================================================
        //                      L
        // =====================================================================
        Date startDate = format.parse("2017-06-21 21:45:28");
        Date endDate = format.parse("2017-06-22 23:27:32");
        int startHour = 4, endHour = 23;

        int expResult = 1140;
        long result = Algoritmos.getValidTimeInRange(startDate, endDate, startHour, endHour);
        assertEquals(expResult, result);

        // =====================================================================
        //                      L
        // =====================================================================
        startDate = format.parse("2017-06-21 21:45:28");
        endDate = format.parse("2017-06-22 10:27:32");

        startHour = 4;
        endHour = 23;

        expResult = 6 * 60 + 27;
        result = Algoritmos.getValidTimeInRange(startDate, endDate, startHour, endHour);
        assertEquals(expResult, result);
        
        // =====================================================================
        //                      L
        // =====================================================================
        startDate = format.parse("2017-06-21 23:45:28");
        endDate = format.parse("2017-06-22 01:27:32");

        startHour = 4;
        endHour = 23;

        expResult = 0;
        result = Algoritmos.getValidTimeInRange(startDate, endDate, startHour, endHour);
        assertEquals(expResult, result);
    }

}
