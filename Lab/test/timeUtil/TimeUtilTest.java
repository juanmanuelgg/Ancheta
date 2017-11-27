package timeUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author juanmanuel
 */
public class TimeUtilTest {

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
    private static final boolean DEBUG = true;

    /**
     * Test of getDaysToAlarm method, of class TimeUtil.
     *
     * @throws java.text.ParseException
     */
    @Test
    public void testGetDaysToAlarm_7args() throws ParseException {
        System.out.println("getDaysToAlarm");
        Date startDate = FORMAT.parse("2017-06-18_23:04:42");
        Date endDate = FORMAT.parse("2017-06-19_06:11:12");
        List<Date> result = TimeUtil.getDaysToAlarm(startDate, endDate, 23, 59, 06, 00, DEBUG);
        if (DEBUG) {
            System.out.println(result);
        }
//        assertEquals(expResult, result);
    }

}
