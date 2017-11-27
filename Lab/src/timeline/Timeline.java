package timeline;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author juanmanuel
 * @param <O>
 */
public final class Timeline<O> {

    public static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");

    private final Object timelineIdentifier;
    private Class<O> informationClass;
    private final Set<Object> keyIdentifiers;
    private final List<Unavailability> unavailabilities;
    private boolean debug = false;

    public Timeline(Object timelineIdentifier, Set<Object> keyIdentifiers, List<Unavailability> unavailabilities, Class<O> informationClass, boolean debug) {
        this(timelineIdentifier, keyIdentifiers, unavailabilities, informationClass);
        this.debug = debug;
    }

    public Timeline(Object timelineIdentifier, Set<Object> keyIdentifiers, List<Unavailability> unavailabilities, Class<O> informationClass) {
        this(timelineIdentifier, keyIdentifiers, unavailabilities);
        this.informationClass = informationClass;
    }

    public Timeline(Object timelineIdentifier, Set<Object> keyIdentifiers, List<Unavailability> unavailabilities) {
        this.timelineIdentifier = timelineIdentifier;
        this.keyIdentifiers = keyIdentifiers;
        this.unavailabilities = new ArrayList<>();
        for (Unavailability unavailability : unavailabilities) {
            addUnavailability(unavailability);
        }
    }

    public Set<Object> getKeyIdentifiers() {
        return keyIdentifiers;
    }

    public Class<O> getInformationClass() {
        return informationClass;
    }

    public List<Unavailability> getUnavailabilities() {
        return unavailabilities;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public void addUnavailability(Unavailability unavailability) {
        boolean b2 = unavailability.getExtraInformation() != null ? informationClass.equals(unavailability.getExtraInformation().getClass()) : true;
        if (b2) {
            unavailabilities.add(unavailability);
        }
    }

    public List<Unavailability<List<O>>> processFailures() {
        List<Unavailability<List<O>>> answer = new ArrayList<>();
        List<Event> events = new ArrayList<>();
        for (Unavailability unavailability : unavailabilities) {
            Object keyIdentifier = unavailability.getKeyIdentifier();
            O extraInformation = (O) unavailability.getExtraInformation();
            events.add(new Event(unavailability.getStart(), Event.OUT, keyIdentifier, extraInformation));
            events.add(new Event(unavailability.getEnd(), Event.IN, keyIdentifier, null));
        }
        Collections.sort(events);

        Map<Object, O> mapElementsInFail = new LinkedHashMap<>();
        Unavailability currentFailure = null;
        for (Event event : events) {
            boolean createFail = false;
            Object identifier = event.getKeyIdentifier();
            O extraInformation = (O) event.getExtraInformation();
            switch (event.getType()) {
                case Event.OUT:
                    mapElementsInFail.put(identifier, extraInformation);
                    break;
                case Event.IN:
                    mapElementsInFail.remove(identifier);
                    break;
            }

            if (mapElementsInFail.keySet().containsAll(keyIdentifiers)) {
                if (currentFailure == null) {
                    List<O> extraInfo = new ArrayList<>();
                    for (O value : mapElementsInFail.values()) {
                        if (value != null) {
                            extraInfo.add(value);
                        }
                    }
                    createFail = true;
                    currentFailure = new Unavailability(timelineIdentifier, event.getDate(), extraInfo);
                }
            } else {
                if (currentFailure != null) {
                    currentFailure.setEnd(event.getDate());
                    answer.add(currentFailure);
                    currentFailure = null;
                }
            }
            if (debug) {
                String fail = (createFail && currentFailure != null) ? "FAIL" : "    ";
                System.out.println(event.getType()
                        + "\t" + fail
                        + "\t" + event.getKeyIdentifier().toString()
                        + "\t" + FORMAT.format(event.getDate())
                        + "\t" + mapElementsInFail.keySet().size() + "/" + keyIdentifiers.size()
                        + "\t" + mapElementsInFail.keySet());
            }
        }
        if (currentFailure != null) {
            answer.add(currentFailure);
        }
        return answer;
    }
}
