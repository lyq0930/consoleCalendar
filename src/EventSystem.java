import java.util.*;

/**
 * Created by Louis on 9/29/15.
 */
public class EventSystem {
    HashMap<DayVIEW, Event> eventMap = new HashMap<>();
    HashMap<GregorianCalendar, Event> eventMap2 = new HashMap<>();
    Event aEvent;

    public void addEvent(GregorianCalendar c, Event aEvent){
        eventMap2.put(c, aEvent);
    }

    public void addEvent(DayVIEW dayVIEW, Event aEvent){
        eventMap.put(dayVIEW, aEvent);
    }

    public Event getEvent(DayVIEW dayVIEW){
        return eventMap.get(dayVIEW);
    }

    public void getAll(){
        Set set1 = eventMap.entrySet();
        Iterator iter = set1.iterator();
        while(iter.hasNext()){
            Map.Entry entry = (Map.Entry) iter.next();
            DayVIEW aDay = (DayVIEW) entry.getKey();
            Event aEvent = (Event) entry.getValue();
            aDay.printFullDate();
            System.out.println(aEvent.getTitle());
        }

    }

    public void getAll2(){
        Set set1 = eventMap2.entrySet();
        Iterator iter = set1.iterator();
        while(iter.hasNext()){
            Map.Entry entry = (Map.Entry) iter.next();
            GregorianCalendar c = (GregorianCalendar) entry.getKey();
            Event aEvent = (Event) entry.getValue();

            MONTHS[] arrayOfMonths = MONTHS.values();
            DAYS[] arrayOfDays = DAYS.values();
            System.out.println(arrayOfDays[c.get(Calendar.DAY_OF_WEEK) - 1] + " " + arrayOfMonths[c.get(Calendar.MONTH)]
                    + " " + c.get(Calendar.DAY_OF_MONTH) + " " + c.get(Calendar.YEAR));
            System.out.println(aEvent.getTitle());
        }

    }

    public boolean isADayHasEvent(DayVIEW dayVIEW){
        Set set1 = eventMap.entrySet();
        Iterator iter = set1.iterator();
        while(iter.hasNext()){
            Map.Entry entry = (Map.Entry) iter.next();
            if(entry.getKey() == dayVIEW)
                return true;
        }
        return false;
    }

    public boolean belongsOneDay(Calendar c){
        return false;
    }

}
