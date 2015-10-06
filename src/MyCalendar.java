/**
 * Created by Louis on 9/29/15.
 */
import sun.tools.tree.GreaterExpression;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
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
    final GregorianCalendar currentMonth = new GregorianCalendar();
    EventSystem eventSystem = new EventSystem();

    public void printCurrentMonthView(){
        printCurrentMonthView(new GregorianCalendar());
    }

    private void printCurrentMonthView(GregorianCalendar c) {
        MONTHS[] arrayOfMonths = MONTHS.values();

        //Print the monthVIEW, year, and week of the current view
        printMessage("********************************************************");
        printMessage("Welcome to the console Calendar, written by YanQiang Lu");
        printMessage("********************************************************\n");
        printMessage("     " + arrayOfMonths[c.get(Calendar.MONTH)] + " " + c.get(Calendar.YEAR));
        printMessage("Su Mo Tu We Th Fr Sa");

        //create an temperate object for the current monthVIEW to get the first_day_of_week
        GregorianCalendar temp = new GregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1);
        int firstDay_of_week = temp.get(Calendar.DAY_OF_WEEK);
        int count = 0;  //a counter for new line

        //print blank at the beginning of the monthVIEW view until the first_day_of_week
        for (int blank = 1; blank < firstDay_of_week; blank++){
            System.out.print("   ");
            count ++;
        }

        //print days from 1 to the_actual_last_day of the monthVIEW.
        for(int day = 1; day <= c.getActualMaximum(Calendar.DAY_OF_MONTH); day++){
            if(day == c.get(Calendar.DAY_OF_MONTH)) //Mark today
                System.out.printf("[%d] ", day);
            else
                System.out.printf("%2d ", day);
            count++;
            if(count == 7) {
                printMessage();
                count = 0;
            }
        }
        printMessage();
        printMessage("********************************************************");

    }

    public void mainMenu(Scanner scan){
        printMessage("Select one of the following options: \n" +
                "[L]oad   [V]iew by  [C]reate, [G]o to [E]vent list [D]elete  [Q]uit");
        while(scan.hasNext()){
            String in  = userInput(scan);
            switch(in){
                case "v":case "V":
                    enterView(scan);
                    break;
                case "c":case "C":
                    createEvent(scan);
                    break;
                case "e":case "E":
                    displayAllEvent(scan);
                    break;
                case "l":case "L":
                    load();
                    break;
                default:
                    break;
            }
            if(in.compareToIgnoreCase("q") == 0) {    //if user want to quit
                if(quitSucceed())
                    break;
            }
            else {                                    //otherwise, display the main menu
                printCurrentMonthView();
                printMessage("Select one of the following options: \n" +
                        "[L]oad   [V]iew by  [C]reate, [G]o to [E]vent list [D]elete  [Q]uit");
            }
        }
    }

    protected void printMessage(){
        System.out.println();
    }

    protected void printMessage(String message){
        System.out.println(message);
    }

    protected String userInput(Scanner scan){
        String in =  scan.nextLine();
        return in;
    }

    private void enterView(Scanner scan){
        printMessage("[D]ay view or [M]view ?");
        while(scan.hasNext()){
            String in = userInput(scan);
            //enter monthVIEW view or dayVIEW view
            if(in.compareToIgnoreCase("m") == 0) {
                enterMonthView(scan);
                break;
            }else if(in.compareToIgnoreCase("d") == 0) {
                enterDayView(scan);
                break;
            }
            printMessage("[D]ay view or [M]view ?");
        }
    }

    private void load(){
        ArrayList<Event> list = deSerialize();
        if(list == null){
            printMessage("No file exist");
        }else{
            eventSystem = new EventSystem(list);
        }
    }

    private void enterMonthView(Scanner scan){
        printMonthView(currentMonth);
        GregorianCalendar c = new GregorianCalendar();
        printMessage("[P]revious or [N]ext or [M]ain menu ?");
        while(scan.hasNext()){
            String in = userInput(scan);
            if(in.compareToIgnoreCase("p") == 0){
                preMonth(c);
                printMonthView(c);
            } else if(in.compareToIgnoreCase("n") == 0){
                nextMonth(c);
                printMonthView(c);
            } else if(in.compareToIgnoreCase("m") == 0)
                break;
            printMessage("[P]revious or [N]ext or [M]ain menu ?");
        }
    }

    public void preMonth(GregorianCalendar cal){
        cal.add(Calendar.MONTH, -1);
    }

    public void nextMonth(GregorianCalendar cal){
        cal.add(Calendar.MONTH, 1);
    }

    private void printMonthView(GregorianCalendar c){
        MONTHS[] arrayOfMonths = MONTHS.values();

        //Print the monthVIEW, year, and week of the current view
        printMessage("     " + arrayOfMonths[c.get(Calendar.MONTH)] + " " + c.get(Calendar.YEAR));
        printMessage("Su Mo Tu We Th Fr Sa");

        //create an temperate object for the current monthVIEW to get the first_day_of_week
        GregorianCalendar temp = new GregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1);
        int firstDay_of_week = temp.get(Calendar.DAY_OF_WEEK);
        int count = 0;  //a counter for new line

        //print blank at the beginning of the monthVIEW view until the first_day_of_week
        for (int blank = 1; blank < firstDay_of_week; blank++){
            System.out.print("   ");
            count ++;
        }

        //print days from 1 to the_actual_last_day of the monthVIEW.
        for(int day = 1; day <= c.getActualMaximum(Calendar.DAY_OF_MONTH); day++){
            GregorianCalendar dateStamp = new GregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH), day);
            if(isTheDayHasEvent(dateStamp))             // Passing a date stamp to check whether the day has event
                System.out.printf("[%d] ", day);        // If the day has event, mark it.
            else
                System.out.printf("%2d ", day);
            count++;
            if(count == 7) {
                printMessage();
                count = 0;
            }
        }
        printMessage();
    }

    public boolean isTheDayHasEvent(GregorianCalendar dateStamp){
        return eventSystem.isADayHasEvent(dateStamp);
    }
    private String printDate(GregorianCalendar c){
        MONTHS[] arrayOfMonths = MONTHS.values();
        DAYS[] arrayOfDays = DAYS.values();
        return "A nice view: " + arrayOfDays[c.get(Calendar.DAY_OF_WEEK) - 1] + " " + arrayOfMonths[c.get(Calendar.MONTH)]
                + " " + c.get(Calendar.DATE) + " " + c.get(Calendar.YEAR);
    }

    private void enterDayView(Scanner scan){
        printDayView(currentMonth);
        GregorianCalendar c = new GregorianCalendar();
        printMessage("[P]revious or [N]ext or [M]ain menu ?");
        while(scan.hasNext()){
            String in = userInput(scan);
            if(in.compareToIgnoreCase("p") == 0){
                preDay(c);
                printDayView(c);
            } else if(in.compareToIgnoreCase("n") == 0){
                nextDay(c);
                printDayView(c);
            } else if(in.compareToIgnoreCase("m") == 0)
                break;
            printMessage("[P]revious or [N]ext or [M]ain menu ?");
        }
    }

    private void printDayView(GregorianCalendar c){
        MONTHS[] arrayOfMonths = MONTHS.values();
        DAYS[] arrayOfDays = DAYS.values();
        printMessage(arrayOfDays[c.get(Calendar.DAY_OF_WEEK) - 1] + " " + arrayOfMonths[c.get(Calendar.MONTH)]
                + " " + c.get(Calendar.DAY_OF_MONTH) + " " + c.get(Calendar.YEAR));
    }

    private void preDay(GregorianCalendar c){
        c.add(Calendar.DAY_OF_MONTH, -1);
    }

    private void nextDay(GregorianCalendar c){
        c.add(Calendar.DAY_OF_WEEK, +1);
    }

    private void createEvent(Scanner scan){
        try {
            printMessage("To create an event, please enter the following information:");
            System.out.print("Title: ");
            String title = userInput(scan);
            System.out.print("Date: ");
            String dateInput = userInput(scan);
            int month = Integer.parseInt(dateInput.substring(0, 2));
            int date = Integer.parseInt(dateInput.substring(3, 5));
            int year = Integer.parseInt(dateInput.substring(6, 10));
            System.out.print("Start Time: ");
            String startTime = userInput(scan);
            System.out.print("End Time: ");
            String endTime = userInput(scan);
            GregorianCalendar dateStamp = new GregorianCalendar(year, month - 1, date);
            Event event = new Event(title, dateStamp, startTime, endTime);
            eventSystem.addEvent(dateStamp, event);
            printMessage("Succeed! The event has been created.");
            printMessage("Continue to [C]reate event OR back to [M]ain menu?");
            while (scan.hasNext()) {
                String in = userInput(scan);
                if (in.compareToIgnoreCase("m") == 0)
                    break;
                if (in.compareToIgnoreCase("c") == 0) {
                    createEvent(scan);
                    break;
                }
                printMessage("Continue to [C]reate event OR back to [M]ain menu?");
            }
        }catch (StringIndexOutOfBoundsException e){
            printMessage("Wrong Input");
            createEvent(scan);
        }
    }

    private void displayAllEvent(Scanner scan){
        printMessage("-------------------All Events---------------------------");
        ArrayList<Event> list = eventSystem.getSortedAllEvents();
        for(Event event: list){
            printMessage("Event Title: " + event.getTitle() + "\nEvent Date: " +
                    event.getDate() + "\nStart time: " + event.getStartTime() +
                    "\nEnd Time: " + event.getEndTime() + "\n");
        }
        printMessage("----------------------End-------------------------------\n");
        printMessage("Back to [M]ain menu?");
        while (scan.hasNext()) {
            String in = userInput(scan);
            if (in.compareToIgnoreCase("m") == 0)
                break;
            printMessage("Back to [M]ain menu?");
        }

    }
    private boolean quitSucceed(){
        if(serialize()){
            printMessage("Serialization succeed!");
        }
        if(saveToString()){
            printMessage("Save to text!");
        } else{
            printMessage("Cannot saved!");
            return false;
        }
        return true;
    }
    private boolean serialize(){
        try{
            FileOutputStream fos= new FileOutputStream("OutputObject.ser");
            ObjectOutputStream oos= new ObjectOutputStream(fos);
            oos.writeObject(eventSystem.getSortedAllEvents());
            oos.close();
            fos.close();
            return true;
        }catch(IOException ioe){
            ioe.printStackTrace();
            return false;
        }
    }

    private ArrayList<Event> deSerialize(){
        try{
            FileInputStream fileIn = new FileInputStream("OutputObject.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            ArrayList<Event> eventList = (ArrayList<Event>) in.readObject();
            printMessage("Loaded "+ eventList.size() + " events.");
            in.close();
            fileIn.close();
            return eventList;
        } catch (IOException ioe){
            //printMessage("Wrong, cannot deserialize: IOException");
            return null;
        } catch (ClassNotFoundException ex){
            //printMessage("Wrong, cannot serialize: ClassNotFoundException");
            return null;
        }
    }

    private boolean saveToString(){
        try {
            PrintWriter out = new PrintWriter("Newevents.txt");
            for(Event event : eventSystem.getSortedAllEvents()) {
                out.println(event.getInfo());
            }
            out.close();
            return true;
        } catch(IOException e){
            return false;
        }
    }
}
