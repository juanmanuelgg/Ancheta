package timeline;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author juanmanuel
 */
public class AuxTesting {

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");

    public static void testScenario(String path, boolean debug) throws IOException, ParseException {
        if (debug) {
            System.out.println("================================================");
        }
        Scenario<String, Integer> scenario = builScenarioFromFile(path);
        if (debug) {
            System.out.println("Test Scenario");
            System.out.println(scenario);
        }
        List<Unavailability<List<Integer>>> expResult = scenario.getAnswers();
        Collections.sort(expResult);

        if (debug) {
            System.out.println("Excecution");
        }
        String timelineIdentifier = scenario.getIdentifierTimeline();
        Set<String> identifiers = scenario.getIdentifiers();
        List<Unavailability<Integer>> univailabilities = scenario.getUnavailabilities();
        Timeline<Integer> timeline = new Timeline(timelineIdentifier, identifiers, univailabilities, Integer.class, debug);
        List<Unavailability<List<Integer>>> result = timeline.processFailures();
        Collections.sort(result);
        if (debug) {
            System.out.println("Respuesta Obtenida");
            for (Unavailability<List<Integer>> answer : result) {
                System.out.println(answer);
            }
        }

        assertEquals(expResult.size(), result.size());
        for (int i = 0; i < result.size(); i++) {
            Unavailability<List<Integer>> answerExpected = expResult.get(i);
            Unavailability<List<Integer>> answerReal = result.get(i);
            assertEquals(answerExpected.getKeyIdentifier(), answerReal.getKeyIdentifier());
            assertEquals(answerExpected.getStart(), answerReal.getStart());
            assertEquals(answerExpected.getEnd(), answerReal.getEnd());
            assertTrue(answerExpected.getExtraInformation().equals(answerReal.getExtraInformation()));
        }
        if (debug) {
            System.out.println("================================================");
        }
    }

    public static Scenario<String, Integer> builScenarioFromFile(String path) throws IOException, ParseException {
        Scenario<String, Integer> scenario = new Scenario<>();
        SortedSet<String> identifiers = new TreeSet<>();
        List<Unavailability<Integer>> unavailabilities = new ArrayList<>();
        List<Unavailability<List<Integer>>> answers = new ArrayList<>();
        String timelineIdentifier = null;

        int parteDelDocumento = 1;
        try (BufferedReader br = new BufferedReader(new FileReader(new File(path)))) {
            for (String line, aux[]; (line = br.readLine()) != null;) {
                int pos = line.indexOf("//");
                line = (pos >= 0) ? line.substring(0, pos) : line; // Remover comentarios
                line = line.trim();

                if (line.equals("")) { // Saltar lineas vacias
                    continue;
                }

                if (line.equals("==========")) {
                    parteDelDocumento++;
                    continue;
                }

                if (parteDelDocumento == 1) {
                    timelineIdentifier = line;
                } else if (parteDelDocumento == 2) {
                    identifiers.add(line);
                } else if (parteDelDocumento == 3) {
                    aux = line.split("\\s+");
                    int posx = 0;
                    String dn = aux[posx++];
                    Date start = FORMAT.parse(aux[posx++]);
                    Date end = FORMAT.parse(aux[posx++]);
                    Integer taoli = aux.length > 3 ? Integer.parseInt(aux[posx++]) : null;
                    unavailabilities.add(new Unavailability<>(dn, start, end, taoli));
                } else if (parteDelDocumento == 4) {
                    aux = line.split("\\s+");
                    int posx = 0;
                    Date start = FORMAT.parse(aux[posx++]);
                    Date end = FORMAT.parse(aux[posx++]);
                    List<Integer> tickets = new ArrayList<>();
                    String[] strTickets = aux[posx++].split("\\|");
                    for (String strTicket : strTickets) {
                        tickets.add(Integer.parseInt(strTicket));
                    }
                    answers.add(new Unavailability<>(timelineIdentifier, start, end, tickets));
                }
            }

        }
        scenario.setIdentifierTimeline(timelineIdentifier);
        scenario.setIdentifiers(identifiers);
        scenario.setUnavailabilities(unavailabilities);
        scenario.setAnswers(answers);
        return scenario;
    }

}
