package lab;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author juanmanuel
 */
public class Algoritmos {

    public static long getValidTimeInRange(Date startDate, Date endDate, int startHour, int endHour) {

        // 1) Base case: when there is not a range to evaluate
        if (startHour == -1 || endHour == -1) {
            return TimeUnit.MILLISECONDS.toMinutes(endDate.getTime() - startDate.getTime());
        }

        // 2) 
        int minHour = Math.min(startHour, endHour), maxHour = Math.max(startHour, endHour);
        boolean normalRange = startHour < endHour;

        Calendar calendarInCicle = Calendar.getInstance();
        calendarInCicle.setTime(startDate);
        calendarInCicle.set(Calendar.HOUR_OF_DAY, 0);
        calendarInCicle.set(Calendar.MINUTE, 0);
        calendarInCicle.set(Calendar.SECOND, 0);

        List<Event> events = new ArrayList<>();
        int numberOfDays = daysBetweenDates(startDate, endDate);
        Date dateToInsert;
        String type;

        Event startElement = new Event(startDate, Event.START);
        events.add(startElement);
        while (numberOfDays-- != 0) {
            calendarInCicle.set(Calendar.HOUR_OF_DAY, minHour);
            dateToInsert = new Date(calendarInCicle.getTimeInMillis());
            type = normalRange ? Event.IN : Event.OUT;
            events.add(new Event(dateToInsert, type));

            calendarInCicle.set(Calendar.HOUR_OF_DAY, maxHour);
            type = normalRange ? Event.OUT : Event.IN;
            dateToInsert = new Date(calendarInCicle.getTimeInMillis());
            events.add(new Event(dateToInsert, type));

            calendarInCicle.add(Calendar.DAY_OF_YEAR, 1);
        }
        Event endElement = new Event(endDate, Event.END);
        events.add(endElement);

        Collections.sort(events);
        int initIndex = events.indexOf(startElement);
        int endIndex = events.indexOf(endElement);
        events = events.subList(initIndex, endIndex + 1);

        if (events.get(1).getType().equals(Event.OUT)) { // Second
            events.get(0).setType(Event.START_IN);
        }

        if (events.get(events.size() - 2).getType().equals(Event.IN)) { // Penultimate
            events.get(events.size() - 1).setType(Event.OUT_END);
        }

        // 3) Calcular con la linea de tiempo, el tiempo maximo de indisponibilidad
//        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        long answerMaxTimeInFailure = 0;
        Date inDate = null;
        for (Event event : events) {
            long timeInFailure = 0;

            if (event.getType().equals(Event.IN) || event.getType().equals(Event.START_IN)) {
                inDate = event.getDate();
            } else if (event.getType().equals(Event.OUT) || event.getType().equals(Event.OUT_END)) {
                if (inDate != null) {
                    timeInFailure = TimeUnit.MILLISECONDS.toMinutes(event.getDate().getTime() - inDate.getTime());
                    if (timeInFailure > answerMaxTimeInFailure) {
                        answerMaxTimeInFailure = timeInFailure;
                    }
                }

                inDate = null;
            }

//            String text = (timeInFailure == 0) ? "" : " Minutes in fail = " + timeInFailure;
//            System.out.println(event + text);
        }

//        System.out.println("Answer: " + answer);
//        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        return answerMaxTimeInFailure;
    }

    private static int daysBetweenDates(Date startDate, Date endDate) {
        int answer = 0;

        Calendar c1 = Calendar.getInstance();
        c1.setTime(startDate);
        c1.set(Calendar.HOUR_OF_DAY, 0);
        c1.set(Calendar.MINUTE, 0);
        c1.set(Calendar.SECOND, 0);
        c1.set(Calendar.MILLISECOND, 0);

        while (c1.getTimeInMillis() < endDate.getTime()) {
            c1.add(Calendar.DAY_OF_YEAR, 1);
            answer++;
        }

        return answer;
    }

    private static class Event implements Comparable<Event> {

        public final static String START = "START";
        public final static String START_IN = "START_IN";
        public final static String IN = "IN";
        public final static String END = "END";
        public final static String OUT_END = "OUT_END";
        public final static String OUT = "OUT";

        private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        private Date date;
        private String type;

        public Event(Date date, String type) {
            this.date = date;
            this.type = type;
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

        @Override
        public int compareTo(Event other) {
            return date.compareTo(other.getDate());
        }

        @Override
        public String toString() {
            return format.format(date) + " : " + type;
        }
    }

}
