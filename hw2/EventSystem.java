import java.util.*;

public class EventSystem{
    private Map<GregorianCalendar, ArrayList<Event>> eventMap;

    /**
     * Blank Constructor
     */
    public EventSystem(){
        eventMap = new HashMap<>();
    }

    /**
     * Constructor based on deserialization, which input event list and load in system.
     * @param eventList An ArrayList that contains all events and their date info.
     */
    public EventSystem(ArrayList<Event> eventList){
        eventMap = new HashMap<>();
        for(Event event: eventList){
            addEvent(event.getDateStamp(), event);
        }
    }

    /**
     * Get all events from the event system.
     * @return An ArrayList that contains all events info.
     */
    public ArrayList<Event> getAllEvents(){
        ArrayList<Event> allEvents = new ArrayList<>();
        for(Map.Entry<GregorianCalendar, ArrayList<Event>> entry : eventMap.entrySet()){
            for(Event event: entry.getValue()){
                allEvents.add(event);
            }
        }
        return allEvents;
    }

    /**
     * Get all events that sorted based on the date and start time.
     * @return An ArrayList that contains all events info, and sorted by the date and start time.
     */
    public ArrayList<Event> getSortedAllEvents(){
        ArrayList<Event> allEvents = getAllEvents();
        sortEvent(allEvents);
        return allEvents;
    }

    /**
     * Add a new event to the system
     * @param c Corresponded Gregorian Calendar object, which could be used as date stamp.
     * @param aEvent Corresponded event info, which contains the event title, start time, and end time.
     */
    public void addEvent(GregorianCalendar c, Event aEvent){
        if(eventMap.containsKey(c)){
            ArrayList<Event> eventList = eventMap.get(c);
            eventList.add(aEvent);
        } else {
            ArrayList<Event> newEventList = new ArrayList<>();
            newEventList.add(aEvent);
            eventMap.put(c, newEventList);
        }
    }

    /**
     * Delete specific events that in a same day based on the date stamp.
     * @param c A date stamp, which is also a GregorianCalendar object
     */
    public void deleteEvent(GregorianCalendar c){
        if(eventMap.containsKey(c)){
            eventMap.remove(c);
        }
    }

    /**
     * Delete all events that existed in the system.
     */
    public void deleteAll(){
        eventMap.clear();
    }

    /**
     * Sort all events based on the date and start time.
     * @param eventList The event list that is going to be sorted.
     */
    private void sortEvent(ArrayList<Event> eventList){
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

    /**
     * Check whether a day has event
     * @param dateStamp The date stamp that corresponding to a specific date.
     * @return true if the day has event, false if the day does not have event.
     */
    public boolean isADayHasEvent(GregorianCalendar dateStamp){
        return eventMap.containsKey(dateStamp);
    }

    /**
     * Get all events that came from a specific date.
     * @param dateStamp The date stamp that corresponding to a specific date.
     * @return The event list that contains all events in a date.
     */
    public ArrayList<Event> getEventsInThisDay(GregorianCalendar dateStamp){
        ArrayList<Event> eventListInThisDay = eventMap.get(dateStamp);
        sortEvent(eventListInThisDay);
        return eventListInThisDay;
    }
}
