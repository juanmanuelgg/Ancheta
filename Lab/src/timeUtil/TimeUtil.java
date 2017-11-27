package timeUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import timeline.Unavailability;
import timeline.Timeline;

/**
 * @author juanmanuel
 */
public class TimeUtil {

    private static final String VENTANA = "VENTANA";
    private static final String FALLA = "FALLA";

    public static List<Date> getDaysToAlarm(Date startDate, Date endDate, int startHour, int startMinute, int endHour, int endMinute) {
        return getDaysToAlarm(startDate, endDate, startHour, startMinute, endHour, endMinute, false);
    }

    public static List<Date> getDaysToAlarm(Date startDate, Date endDate, int startHour, int startMinute, int endHour, int endMinute, boolean debug) {

        boolean normalRange = startHour * 60 + startMinute < endHour * 60 + endMinute;
        int hour1, minute1, hour2, minute2;
        if (normalRange) {
            hour1 = startHour;
            minute1 = startMinute;
            hour2 = endHour;
            minute2 = endMinute;
        } else {
            hour2 = startHour;
            minute2 = startMinute;
            hour1 = endHour;
            minute1 = endMinute;
        }

        List<Unavailability> Unavailavilities = new ArrayList<>();
        List<Date> daysInvolved = daysBetweenDates(startDate, endDate);
        String type;
        for (Date day : daysInvolved) {
            Calendar calendarInCicle = Calendar.getInstance();
            calendarInCicle.setTime(day);
            calendarInCicle.set(Calendar.HOUR_OF_DAY, hour1);
            calendarInCicle.set(Calendar.MINUTE, minute1);
            Date dateStart = new Date(calendarInCicle.getTimeInMillis());

            calendarInCicle.set(Calendar.HOUR_OF_DAY, hour2);
            calendarInCicle.set(Calendar.MINUTE, minute2);
            Date dateEnd = new Date(calendarInCicle.getTimeInMillis());
            Unavailavilities.add(new Unavailability(VENTANA, dateStart, dateEnd));
        }
        Unavailavilities.add(new Unavailability(FALLA, startDate, endDate));

        Set<String> identifiers = new HashSet<>();
        identifiers.add(FALLA);
        identifiers.add(VENTANA);
        Timeline timeline = new Timeline("TIMELINE", identifiers, Unavailavilities);
        timeline.setDebug(debug);
        List<Unavailability> unavailabilities = timeline.processFailures();
        for (Unavailability unavailability : unavailabilities) {
            System.out.println(unavailability);
        }
        return null;
    }

    private static List<Date> daysBetweenDates(Date startDate, Date endDate) {
        List<Date> answer = new ArrayList<>();

        Calendar c1 = Calendar.getInstance();
        c1.setTime(startDate);
        c1.set(Calendar.HOUR_OF_DAY, 0);
        c1.set(Calendar.MINUTE, 0);
        c1.set(Calendar.SECOND, 0);
        c1.set(Calendar.MILLISECOND, 0);

        while (c1.getTimeInMillis() < endDate.getTime()) {
            answer.add(new Date(c1.getTimeInMillis()));
            c1.add(Calendar.DAY_OF_YEAR, 1);
        }

        return answer;
    }

}
