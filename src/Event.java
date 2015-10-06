import java.io.Serializable;
import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;

/**
 * Created by Louis on 9/29/15.
 */
public class Event implements Serializable{
    String title;
    int year;
    MONTHS month;
    int date;
    String startTime;
    String endTime = "No end time";
    private GregorianCalendar dateStamp;

    public Event(){}

    public Event(String aTitle, GregorianCalendar dateStamp, String aStartTime, String aEndTime){
        this.title = aTitle;
        this.dateStamp = dateStamp;
        this.startTime = aStartTime;
        if(!aEndTime.isEmpty())
            this.endTime = aEndTime;
    }

    public String getTitle(){return title;}
    public GregorianCalendar getDateStamp(){return dateStamp;}
    public String getStartTime(){return startTime;}
    public String getEndTime(){return endTime;}
    public String getDate(){
        MONTHS[] arrayOfMonths = MONTHS.values();
        DAYS[] arrayOfDays = DAYS.values();
        return arrayOfDays[dateStamp.get(Calendar.DAY_OF_WEEK) - 1] + ", " +
                arrayOfMonths[dateStamp.get(Calendar.MONTH)] + ", " +
                dateStamp.get(Calendar.DAY_OF_MONTH) + ", " +
                dateStamp.get(Calendar.YEAR);
    }
    public String getInfo(){
        return "Event title: " + getTitle() + "\nIn Date: " +
                getDate() +  "\nStart at: " + getStartTime() + "\n-----------------";
    }
}
