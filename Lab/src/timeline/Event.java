package timeline;

import java.util.Date;

/**
 * @author juanmanuel
 * @param <O>
 */
public class Event<O> implements Comparable<Event<O>> {

    public final static String IN = "IN", OUT = "OUT";

    private Date date;
    private String type;
    private Object keyIdentifier;
    private O extraInformation;

    public Event(Date date, String type, Object keyIdentifier, O extraInformation) {
        this.date = date;
        this.type = type;
        this.keyIdentifier = keyIdentifier;
        this.extraInformation = extraInformation;
    }

    public Date getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getKeyIdentifier() {
        return keyIdentifier;
    }

    public void setKeyIdentifier(Object keyIdentifier) {
        this.keyIdentifier = keyIdentifier;
    }

    public O getExtraInformation() {
        return extraInformation;
    }

    public void setExtraInformation(O extraInformation) {
        this.extraInformation = extraInformation;
    }

    @Override
    public int compareTo(Event other) {
        return date.compareTo(other.getDate());
    }

    @Override
    public String toString() {
        return type
                + "\t" + keyIdentifier.toString()
                + "\t" + Timeline.FORMAT.format(date);
    }
}
