import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Event implements Serializable{
    private String title;
    private GregorianCalendar dateStamp;
    private String startTime;
    private String endTime = "No end time";

    /**
     * Construct an event based on the title, GregorianCalendar object, start time, and end time
     * @param aTitle The event title
     * @param dateStamp The date stamp that point to a specific date, which is a GregorianCalendar object.
     * @param aStartTime The event start time
     * @param aEndTime The event end time
     */
    public Event(String aTitle, GregorianCalendar dateStamp, String aStartTime, String aEndTime){
        this.title = aTitle;
        this.dateStamp = dateStamp;
        this.startTime = aStartTime;
        if(!aEndTime.isEmpty())
            this.endTime = aEndTime;
    }

    /**
     * Get the event title
     * @return the event title as String type.
     */
    public String getTitle(){return title;}

    /**
     * Get the start time
     * @return the event start time as String type.
     */
    public String getStartTime(){return startTime;}

    /**
     * Get the end time
     * @return the event end time as String type
     */
    public String getEndTime(){return endTime;}

    /**
     * Get the date stamp
     * @return the event date stamp, which is a GregorianCalendar object
     */
    public GregorianCalendar getDateStamp(){return dateStamp;}

    /**
     * Get the date of the event
     * @return the event date in format of mm/dd/yyyy, which is a String type.
     */
    public String getDate(){
        MONTHS[] arrayOfMonths = MONTHS.values();
        DAYS[] arrayOfDays = DAYS.values();
        return arrayOfDays[dateStamp.get(Calendar.DAY_OF_WEEK) - 1] + ", " +
                arrayOfMonths[dateStamp.get(Calendar.MONTH)] + ", " +
                dateStamp.get(Calendar.DAY_OF_MONTH) + ", " +
                dateStamp.get(Calendar.YEAR);
    }

    /**
     * Get all event info which contains event title, event date, event start time, and event end time
     * @return all event info in a String type.
     */
    public String getInfo(){
        return "Event title: " + getTitle() + "\nStart at: " + getStartTime() +
                "\nEnd at: " + getEndTime() + "\n-----------------";
    }
}
