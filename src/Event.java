/**
 * Created by Louis on 9/29/15.
 */
public class Event {
    String title;
    int year;
    MONTHS month;
    int date;
    String startTime;
    String endTime;

    public Event(String aTitle, Day aDay, String aStartTime){
        this.title = aTitle;
        this.year = aDay.getYear();
        this.month = aDay.getMonth();
        this.date = aDay.getDate();
        this.startTime = aStartTime;
    }

    public Event(String aTitle, int aYear, MONTHS aMonth, int aDay, String aStartTime){
        this.title = aTitle;
        this.year = aYear;
        this.month = aMonth;
        this.date = aDay;
        this.startTime = aStartTime;
    }

    public Event(String aTitle, int aYear, MONTHS aMonth, int aDay, String aStartTime, String aEndTime){
        this.title = aTitle;
        this.year = aYear;
        this.month = aMonth;
        this.date = aDay;
        this.startTime = aStartTime;
        this.endTime = aEndTime;
    }
    public String getTitle(){return title;}
}
