/**
 * Created by Louis on 9/29/15.
 */
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Month{
    int year;
    int month;
    int daysOfMonth;
    int firsrDayOfWeek;
    GregorianCalendar cal = new GregorianCalendar();

    public void printMonthView(){
        printMonthView(cal);
    }

    private void printMonthView(GregorianCalendar c){
        MONTHS[] arrayOfMonths = MONTHS.values();

        //Print the month, year, and week of the current view
        System.out.println("     " + arrayOfMonths[c.get(Calendar.MONTH)] + " " + c.get(Calendar.YEAR));
        System.out.println("Su Mo Tu We Th Fr Sa");

        //create an temperate object for the current month to get the first_day_of_week
        GregorianCalendar temp = new GregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1);
        int firstDay_of_week = temp.get(Calendar.DAY_OF_WEEK);
        int count = 0;  //a counter for new line

        //print blank at the beginning of the month view until the first_day_of_week
        for (int blank = 1; blank < firstDay_of_week; blank++){
            System.out.print("   ");
            count ++;
        }

        //print days from 1 to the_actual_last_day of the month.
        for(int day = 1; day <= c.getActualMaximum(Calendar.DAY_OF_MONTH); day++){
            if(isTheDayHasEvent(day))
                System.out.printf("[%d] ", day);
            else
                System.out.printf("%2d ", day);
            count++;
            if(count == 7) {
                System.out.println();
                count = 0;
            }
        }
        System.out.println();
    }

    public boolean isTheDayHasEvent(int aday){
        return false;
    }

    public GregorianCalendar preMonth(){
        cal.add(Calendar.MONTH, -1);
        return cal;
    }

    public GregorianCalendar nextMonth(){
        cal.add(Calendar.MONTH, 1);
        return cal;
    }
}
