package timeline;

import java.util.List;
import java.util.Set;

/**
 * @author juanmanuel
 * @param <K>
 * @param <O>
 */
public class Scenario<K extends Comparable<K>, O> {

    private final String NEWLINE = System.getProperty("line.separator");

    private String identifierTimeline;
    private Set<String> identifiers;
    private List<Unavailability<O>> unavailabilities;
    private List<Unavailability<List<O>>> answers;

    public Scenario() {
    }

    public String getIdentifierTimeline() {
        return identifierTimeline;
    }

    public void setIdentifierTimeline(String identifierTimeline) {
        this.identifierTimeline = identifierTimeline;
    }
    
    public Set<String> getIdentifiers() {
        return identifiers;
    }

    public void setIdentifiers(Set<String> identifiers) {
        this.identifiers = identifiers;
    }

    public List<Unavailability<O>> getUnavailabilities() {
        return unavailabilities;
    }

    public void setUnavailabilities(List<Unavailability<O>> unavailabilities) {
        this.unavailabilities = unavailabilities;
    }

    public List<Unavailability<List<O>>> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Unavailability<List<O>>> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(identifierTimeline);
        sb.append(NEWLINE);
        sb.append("==========");
        sb.append(identifiers.toString());
        sb.append(NEWLINE);
        sb.append("==========");
        sb.append(NEWLINE);
        for (Unavailability<O> unavailability : unavailabilities) {
            sb.append(unavailability.toString());
            sb.append(NEWLINE);
        }
        sb.append("==========");
        sb.append(NEWLINE);
        for (Unavailability<List<O>> answer : answers) {
            sb.append(answer.toString());
            sb.append(NEWLINE);
        }
        return sb.toString();
    }
}