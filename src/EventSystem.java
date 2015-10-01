import java.util.*;

/**
 * Created by Louis on 9/29/15.
 */
public class EventSystem {
    HashMap<Day, Event> eventMap = new HashMap<>();
    Event aEvent;

    public void addEvent(Day day, Event aEvent){
        eventMap.put(day, aEvent);
    }

    public Event getEvent(Day day){
        return eventMap.get(day);
    }

    public void getAll(){
        Set set1 = eventMap.entrySet();
        Iterator iter = set1.iterator();
        while(iter.hasNext()){
            Map.Entry entry = (Map.Entry) iter.next();
            Day aDay = (Day) entry.getKey();
            Event aEvent = (Event) entry.getValue();
            aDay.printFullDate();
            System.out.println(aEvent.getTitle());
        }

    }

    public boolean isADayHasEvent(Day day){
        Set set1 = eventMap.entrySet();
        Iterator iter = set1.iterator();
        while(iter.hasNext()){
            Map.Entry entry = (Map.Entry) iter.next();
            if(entry.getKey() == day)
                return true;
        }
        return false;
    }

    public boolean belongsOneDay(Calendar c){
        return false;
    }

}
