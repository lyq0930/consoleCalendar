/**
 * Created by Louis on 9/28/15.
 */
import java.util.Scanner;

public class MyCalendarTester {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        MyCalendar cal = new MyCalendar(scan);
        cal.startView();
        cal.mainMenu();
    }
}
