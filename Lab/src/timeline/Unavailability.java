package timeline;

import java.util.Date;

/**
 * @author juanmanuel
 * @param <O>
 */
public class Unavailability<O> implements Comparable<Unavailability<O>>{

    public Object keyIdentifier;
    public Date start, end;
    public O extraInformation;

    public Unavailability(Object keyIdentifier, Date start, Date end, O extraInformation) {
        this(keyIdentifier, start, extraInformation);
        this.end = end;
    }
    
     public Unavailability(Object keyIdentifier, Date start, O extraInformation) {
        this.keyIdentifier = keyIdentifier;
        this.start = start;
        this.extraInformation = extraInformation;
     }
     
     public Unavailability(Object keyIdentifier, Date start, Date end) {
        this.keyIdentifier = keyIdentifier;
        this.start = start;
        this.end = end;
     }

    public Object getKeyIdentifier() {
        return keyIdentifier;
    }

    public void setKeyIdentifier(Object keyIdentifier) {
        this.keyIdentifier = keyIdentifier;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public O getExtraInformation() {
        return extraInformation;
    }

    public void setExtraInformation(O extraInformation) {
        this.extraInformation = extraInformation;
    }

    @Override
    public String toString() {
        return keyIdentifier.toString()
                + "\t" + Timeline.FORMAT.format(start)
                + (end == null ? "" : "\t" + Timeline.FORMAT.format(end))
                + (extraInformation == null ? "" : "\t" + extraInformation.toString());
    }

    @Override
    public int compareTo(Unavailability<O> other) {
        return start.compareTo(other.getStart());
    }
}
