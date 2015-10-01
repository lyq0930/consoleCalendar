/**
 * Created by Louis on 9/29/15.
 */
import java.util.Calendar;
import java.util.GregorianCalendar;
public class Day extends MyCalendar{
    private int year;
    private int month;
    private int date;
    private int dayOfWeek;
    GregorianCalendar cal = new GregorianCalendar();

    public Day(){}

    public Day(int aYear, int aMonth, int aDate){
        this.year = aYear;
        this.month = aMonth;
        this.date = aDate;
        cal = new GregorianCalendar(aYear, aMonth, aDate);

    }

    public void printDayView(){
        printDayView(cal);
    }
    private void printDayView(GregorianCalendar c){
        MONTHS[] arrayOfMonths = MONTHS.values();
        DAYS[] arrayOfDays = DAYS.values();
        System.out.println(arrayOfDays[c.get(Calendar.DAY_OF_WEEK) - 1] + " " + arrayOfMonths[c.get(Calendar.MONTH)]
                + " " + c.get(Calendar.DAY_OF_MONTH) + " " + c.get(Calendar.YEAR));
    }

    public void printEventsInADay(){
        if(hasEvent()){
            System.out.println();
        }
    }

    public boolean hasEvent(){
        return false;
    }

    public GregorianCalendar preDay(){
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal;
    }

    public GregorianCalendar nextDay(){
        cal.add(Calendar.DAY_OF_WEEK, +1);
        return cal;
    }

    public int getYear(){ return year;}
    public MONTHS getMonth(){
        MONTHS[] arrayOfMonths = MONTHS.values();
        return arrayOfMonths[month-1];
    }
    public int getDate(){ return date;}
    public int getDayOfWeek(){ return dayOfWeek;}
    public void printFullDate(){
        System.out.print(getMonth() + " " + date + " " + year);
    }
}
