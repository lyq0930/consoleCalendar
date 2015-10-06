import java.util.*;

/**
 * Created by Louis on 9/29/15.
 */
public class EventSystem{
    Map<GregorianCalendar, ArrayList<Event>> eventMap = new HashMap<>();
    private ArrayList<Event> allEvents;
    Event aEvent;

    public EventSystem(){}

    public EventSystem(ArrayList<Event> allEvents){
        this.allEvents = allEvents;
    }

    public ArrayList<Event> getSortedAllEvents(){
        sortEvent(this.allEvents);
        return this.allEvents;
    }

    public void addEvent(GregorianCalendar c, Event aEvent){
        if(eventMap.containsKey(c)){
            ArrayList<Event> eventList = eventMap.get(c);
            eventList.add(aEvent);
        } else {
            ArrayList<Event> newEventList = new ArrayList<>();
            newEventList.add(aEvent);
            eventMap.put(c, newEventList);
        }
        allEvents.add(aEvent);
    }

    public void sortEvent(ArrayList<Event> eventList){
        Comparator<Event> comp = new
                Comparator<Event>(){
                public int compare(Event e1, Event e2){
                    //if the two events are different day
                    if(e1.getDateStamp().compareTo(e2.getDateStamp()) != 0){
                        return e1.getDateStamp().compareTo(e2.getDateStamp());
                    } else{
                        return e1.getStartTime().compareTo(e2.getStartTime());
                    }
                }
        };
        Collections.sort(eventList, comp);
    }

//    public ArrayList<Event> getAll(){
//        ArrayList<Event> allEvent = new ArrayList<>();
//        for(Map.Entry<GregorianCalendar, ArrayList<Event>> entry : eventMap.entrySet()) {
//            ArrayList<Event> eventList = entry.getValue();
//            for(Event event : eventList) {
//                allEvent.add(event);
//            }
//        }
//        return allEvent;
//    }

    public boolean isADayHasEvent(GregorianCalendar dateStamp){
        if(eventMap.containsKey(dateStamp)){
            return true;
        } else{
            return false;
        }
    }
}
