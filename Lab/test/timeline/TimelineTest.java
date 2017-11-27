package timeline;

import java.io.IOException;
import java.text.ParseException;
import org.junit.Test;

/**
 * @author juanmanuel
 */
public class TimelineTest {

    @Test
    public void testProcessFailures() throws IOException, ParseException {
        AuxTesting.testScenario("data/Manaure.test", true);
    }

}
