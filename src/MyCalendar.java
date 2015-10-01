/**
 * Created by Louis on 9/29/15.
 */
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
enum MONTHS
{
    January, February, March, April, May, June, July, August, September, October, November, December;
}
enum DAYS
{
    Sun, Mon, Tue, Wed, Thur, Fri, Sat ;
}

public class MyCalendar {
    GregorianCalendar cal = new GregorianCalendar();
    EventSystem eventSystem = new EventSystem();
    Month month = new Month();
    Day day = new Day();

    public void printCurrentMonthView(){
        printCurrentMonthView(cal);
    }

    private void printCurrentMonthView(GregorianCalendar c) {
        MONTHS[] arrayOfMonths = MONTHS.values();

        //Print the month, year, and week of the current view
        System.out.println("********************************************************");
        System.out.println("Welcome to the console Calendar, written by YanQiang Lu");
        System.out.println("********************************************************");
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
            if(day == c.get(Calendar.DAY_OF_MONTH)) //Mark today
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
        System.out.println("********************************************************");

    }

    public void mainMenu(Scanner scan){
        System.out.println("Select one of the following options: \n" +
                "[L]oad   [V]iew by  [C]reate, [G]o to [E]vent list [D]elete  [Q]uit");
        while(scan.hasNext()){
            String in  = scan.nextLine();
            switch(in){
                case "v":case "V":
                    //enter View Mode
                    //User input has to be valid
                    System.out.println("[D]ay view or [M]view ?");
                    while(scan.hasNext()){
                        in = scan.nextLine();
                        //enter month view or day view
                        if(in.compareToIgnoreCase("m") == 0) {
                            enterMonthView(scan);
                            break;
                        }else if(in.compareToIgnoreCase("d") == 0) {
                            enterDayView(scan);
                            break;
                        }
                        System.out.println("[D]ay view or [M]view ?");
                    }
                    break;
                case "c":case "C":
                    createEvent(scan);
                    break;
                default:
                    break;
            }
            if(in.compareToIgnoreCase("q") == 0)    //if user want to quit
                break;
            else {                                    //otherwise, display the main menu
                printCurrentMonthView();
                System.out.println("Select one of the following options: \n" +
                        "[L]oad   [V]iew by  [C]reate, [G]o to [E]vent list [D]elete  [Q]uit");
            }
        }
    }

    private void enterMonthView(Scanner scan){
        month.printMonthView();
        System.out.println("[P]revious or [N]ext or [M]ain menu ?");
        while(scan.hasNext()){
            String in = scan.nextLine();
            if(in.compareToIgnoreCase("p") == 0){
                month.preMonth();
                month.printMonthView();
            } else if(in.compareToIgnoreCase("n") == 0){
                month.nextMonth();
                month.printMonthView();
            } else if(in.compareToIgnoreCase("m") == 0)
                break;
            System.out.println("[P]revious or [N]ext or [M]ain menu ?");
        }
    }

    private void enterDayView(Scanner scan){
        day.printDayView();
        System.out.println("[P]revious or [N]ext or [M]ain menu ?");
        while(scan.hasNext()){
            String in = scan.nextLine();
            if(in.compareToIgnoreCase("p") == 0){
                day.preDay();
                day.printDayView();
            } else if(in.compareToIgnoreCase("n") == 0){
                day.nextDay();
                day.printDayView();
            } else if(in.compareToIgnoreCase("m") == 0)
                break;
            System.out.println("[P]revious or [N]ext or [M]ain menu ?");
        }
    }

    private void createEvent(Scanner scan){
        System.out.println("To create an event, please enter the following information:");
        System.out.print("Title: ");
        String title = scan.nextLine();
        System.out.print("Date: ");
        String dateInput = scan.nextLine();
        int month = Integer.parseInt(dateInput.substring(0, 2));
        int date = Integer.parseInt(dateInput.substring(3, 5));
        int year = Integer.parseInt(dateInput.substring(7, 10));
        System.out.print("Start Time: ");
        String startTime = scan.nextLine();
        Day aDay = new Day(year,month,date);
        Event event = new Event(title, aDay, startTime);
        eventSystem.addEvent(aDay, event);
        eventSystem.getAll();
    }
}
