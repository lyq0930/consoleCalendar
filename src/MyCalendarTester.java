/**
 * Created by Louis on 9/28/15.
 */
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
enum MONTHS
{
    Jan, Feb, March, Apr, May, June, July, Aug, Sep, Oct, Nov, Dec;
}
enum DAYS
{
    Sun, Mon, Tue, Wed, Thur, Fri, Sat ;
}
public class MyCalendarTester
{
    public static void main(String [] args)
    {
        GregorianCalendar cal = new GregorianCalendar(); // capture today
        printMonthView(cal);
    }

    public static void printMonthView(GregorianCalendar c) {
        MONTHS[] arrayOfMonths = MONTHS.values();
        System.out.println("     " + arrayOfMonths[c.get(Calendar.MONTH)] + " " + c.get(Calendar.YEAR));
        System.out.println("Su Mo Tu We Th Fr Sa");

        //create an temperate object for the current month to get the first day_of_week
        GregorianCalendar temp = new GregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1);
        int firstDay_of_week = temp.get(Calendar.DAY_OF_WEEK);

        int count = 0;
        for (int blank = 1; blank < firstDay_of_week; blank++){
            System.out.print("   ");
            count ++;
        }
        for(int day = 1; day <= c.getActualMaximum(Calendar.DAY_OF_MONTH); day++){
            if(day == c.get(Calendar.DAY_OF_MONTH))
                System.out.printf("[%d] ", day);
            else
                System.out.printf("%2d ", day);
            count++;
            if(count == 7) {
                System.out.println();
                count = 0;
            }
        }
    }
}