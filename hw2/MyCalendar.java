import java.io.*;
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
    private final GregorianCalendar TODAY;
    private EventSystem eventSystem;
    private Scanner scan;

    /**
     * Constructor: Initialize EventSystem, scanner, and a new GregorianCalendar value point to today.
     * @param scan Passing the what kind of the scanner the calendar used as input stream.
     */
    public MyCalendar(Scanner scan){
        this.scan = scan;
        TODAY = new GregorianCalendar();
        eventSystem = new EventSystem();
    }

    /**
     * Enter the startView, which shows the current month, and the marked day is today.
     */
    public void startView() {
        GregorianCalendar c = new GregorianCalendar();
        MONTHS[] arrayOfMonths = MONTHS.values();

        //Print the monthVIEW, year, and week of the current view
        printMessage("\n********************************************************\n");
        printMessage("Welcome to the console Calendar, written by YanQiang Lu\n");
        printMessage("********************************************************\n");
        printMessage("     " + arrayOfMonths[c.get(Calendar.MONTH)] + " " + c.get(Calendar.YEAR));
        printMessage("\nSu Mo Tu We Th Fr Sa\n");

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
            if(day == c.get(Calendar.DAY_OF_MONTH)) //Mark TODAY
                printDateDigit("[%d] ", day);
            else
                printDateDigit("%2d ", day);
            count++;
            if(count == 7) {
                printMessage("\n");
                count = 0;
            }
        }
        printMessage("\nNote: The marked date is today.\n" +
                "********************************************************\n");

    }

    /**
     * Display main menu, and responds user action.
     */
    public void mainMenu(){
        printMessage("Select one of the following options: \n" +
                "[L]oad   [V]iew by  [C]reate, [G]o to [E]vent list [D]elete  [Q]uit\n");
        while(scan.hasNext()){
            String in  = userInput();
            switch(in){
                case "v":case "V":
                    enterView();
                    break;
                case "c":case "C":
                    createEvent();
                    break;
                case "e":case "E":
                    displayAllEvent();
                    break;
                case "l":case "L":
                    load();
                    break;
                case "g":case "G":
                    gotoADay();
                    break;
                case "d":case "D":
                    deleteEvent();
                    break;
                default:
                    break;
            }
            if(in.compareToIgnoreCase("q") == 0) {    //if user want to quit
                if(quitSucceed())
                    break;
            }
            else {                                    //otherwise, display the main menu
                startView();
                printMessage("Select one of the following options: \n" +
                        "[L]oad   [V]iew by  [C]reate, [G]o to [E]vent list [D]elete  [Q]uit\n");
            }
        }
    }

    /**
     * Get the user input from specific Scanner
     * @return Return string which user typed
     */
    protected String userInput(){
        String in =  scan.nextLine();
        printMessage(in + "\n");
        return in;
    }

    /**
     * Print output
     * @param message The message that going to print.
     */
    protected void printMessage(String message){
        System.out.print(message);
    }

    /**
     * Print date in certain format.
     * @param message The format for printing
     * @param day the date digit
     */
    protected void printDateDigit(String message, int day) {
        System.out.printf(message, day);
    }

    /**
     * Enter load function, in which loading event info from existing file, if no file exist, print "No file exist"
     */
    private void load(){
        ArrayList<Event> list = deSerialize();
        if(list == null){
            printMessage("\nNo file exist\n");
        }else{
            eventSystem = new EventSystem(list);
        }
    }

    /**
     * Go to specific date. Ask user type specific date, then go to the day view.
     */
    private void gotoADay(){
        printMessage("Please type a date you want to view: \n");
        GregorianCalendar dateStamp = getDateFromUserInput();
        printDayView(dateStamp);
        printMessage("\n----------------------End-------------------------------\n");
        printMessage("Back to [M]ain menu?\n");
        while (scan.hasNext()) {
            String in = userInput();
            if (in.compareToIgnoreCase("m") == 0)
                break;
            printMessage("Back to [M]ain menu?\n");
        }
    }

    /**
     * Delete Event based on specific date or delete all events.
     */
    private void deleteEvent(){
        printMessage("\n[S]elected or [A]ll ?\n");
        while (scan.hasNext()) {
            String in = userInput();
            if (in.compareToIgnoreCase("s") == 0) {
                printMessage("Please type a date you want to delete from: \n");
                GregorianCalendar c = getDateFromUserInput();
                eventSystem.deleteEvent(c);
                break;
            } else if(in.compareToIgnoreCase("a") == 0){
                eventSystem.deleteAll();
                break;
            }
            printMessage("[S]elected or [A]ll ?\n");
        }
        printMessage("\nDeleted! Back to [M]ain menu?\n");
        while (scan.hasNext()) {
            String in = userInput();
            if (in.compareToIgnoreCase("m") == 0)
                break;
            printMessage("Back to [M]ain menu?\n");
        }
    }

    /**
     * Enter month view or day view
     */
    private void enterView(){
        printMessage("[D]ay view or [M]view ?\n");
        while(scan.hasNext()){
            String in = userInput();
            //enter monthVIEW view or dayVIEW view
            if(in.compareToIgnoreCase("m") == 0) {
                enterMonthView();
                break;
            }else if(in.compareToIgnoreCase("d") == 0) {
                enterDayView();
                break;
            }
            printMessage("[D]ay view or [M]onth view ?\n");
        }
    }

    /**
     * Enter month view, based on user action, show current month view, or previous, next month view.
     */
    private void enterMonthView(){
        printMonthView(TODAY);
        GregorianCalendar c = new GregorianCalendar();
        printMessage("[P]revious or [N]ext or [M]ain menu ?\n");
        while(scan.hasNext()){
            String in = userInput();
            if(in.compareToIgnoreCase("p") == 0){
                preMonth(c);
                printMonthView(c);
            } else if(in.compareToIgnoreCase("n") == 0){
                nextMonth(c);
                printMonthView(c);
            } else if(in.compareToIgnoreCase("m") == 0)
                break;
            printMessage("[P]revious or [N]ext or [M]ain menu ?\n");
        }
    }

    /**
     * Print specific month view, the marked days are the days that has event associate.
     * @param c GregorianCalendar variable, which corresponding to a specific month.
     */
    private void printMonthView(GregorianCalendar c){
        MONTHS[] arrayOfMonths = MONTHS.values();

        //Print the monthVIEW, year, and week of the current view
        printMessage("\n\nEntered month view...\n\n");
        printMessage("     " + arrayOfMonths[c.get(Calendar.MONTH)] + " " + c.get(Calendar.YEAR));
        printMessage("\nSu Mo Tu We Th Fr Sa\n");

        //create an temperate object for the current monthVIEW to get the first_day_of_week
        GregorianCalendar temp = new GregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1);
        int firstDay_of_week = temp.get(Calendar.DAY_OF_WEEK);
        int count = 0;  //a counter for new line

        //print blank at the beginning of the monthVIEW view until the first_day_of_week
        for (int blank = 1; blank < firstDay_of_week; blank++){
            printMessage("   ");
            count ++;
        }

        //print days from 1 to the_actual_last_day of the monthVIEW.
        for(int day = 1; day <= c.getActualMaximum(Calendar.DAY_OF_MONTH); day++){
            GregorianCalendar dateStamp = new GregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH), day);
            if(isTheDayHasEvent(dateStamp))
                printDateDigit("[%d] ", day);
            else
                printDateDigit("%2d ", day);
            count++;
            if(count == 7) {
                printMessage("\n");
                count = 0;
            }
        }
        printMessage("\nNote: The marked dates are the days with event.\n");
    }

    /**
     * Go to previous month
     * @param c Gergorian Calendar object.
     */
    private void preMonth(GregorianCalendar c){
        c.add(Calendar.MONTH, -1);
    }

    /**
     * Go to next month
     * @param c Gergorian Calendar object.
     */
    private void nextMonth(GregorianCalendar c){
        c.add(Calendar.MONTH, 1);
    }

    /**
     * Check whether the day has event or not.
     * @param dateStamp Gergorian Calendar object which specify which day is going to be checked.
     * @return True if the day has event, false if the day does not has event.
     */
    private boolean isTheDayHasEvent(GregorianCalendar dateStamp){
        return eventSystem.isADayHasEvent(dateStamp);
    }

    /**
     * Enter the day view, which display today or previous / next day based on user's action.
     */
    private void enterDayView(){
        printDayView(TODAY);
        GregorianCalendar c = new GregorianCalendar();
        printMessage("[P]revious or [N]ext or [M]ain menu ?\n");
        while(scan.hasNext()){
            String in = userInput();
            if(in.compareToIgnoreCase("p") == 0){
                preDay(c);
                printDayView(c);
            } else if(in.compareToIgnoreCase("n") == 0){
                nextDay(c);
                printDayView(c);
            } else if(in.compareToIgnoreCase("m") == 0)
                break;
            printMessage("[P]revious or [N]ext or [M]ain menu ?\n");
        }
    }

    /**
     * Go to previous day
     * @param c Gergorian Calendar object
     */
    private void preDay(GregorianCalendar c){
        c.add(Calendar.DAY_OF_MONTH, -1);
    }

    /**
     * Go to next day
     * @param c Gergorian Calendar object
     */
    private void nextDay(GregorianCalendar c){
        c.add(Calendar.DAY_OF_WEEK, +1);
    }

    /**
     * Print day view
     * @param c Gregorian Calendar which specify which day is going to be display.
     */
    private void printDayView(GregorianCalendar c){
        MONTHS[] arrayOfMonths = MONTHS.values();
        DAYS[] arrayOfDays = DAYS.values();
        printMessage("\n" + arrayOfDays[c.get(Calendar.DAY_OF_WEEK) - 1] + " " + arrayOfMonths[c.get(Calendar.MONTH)]
                + " " + c.get(Calendar.DAY_OF_MONTH) + " " + c.get(Calendar.YEAR));
        printDayEvents(c);
    }

    /**
     * Print all event in a specific date.
     * @param c
     */
    private void printDayEvents(GregorianCalendar c){
        GregorianCalendar dateStamp = new GregorianCalendar(c.get(Calendar.YEAR),
                c.get(Calendar.MONTH), c.get(Calendar.DATE));
        if(eventSystem.isADayHasEvent(dateStamp)){
            int count = 1;
            for(Event event: eventSystem.getEventsInThisDay(dateStamp)){
                printMessage("\n" + count++ + ": " + event.getInfo() + "\n");
            }
        } else{
            printMessage("No event in this day.\n");
        }
    }

    /**
     * Get corresponded Gergorian Calendar object based on user input, which in format of mm/dd/yyyy.
     * @return Gregorian Calendar object
     */
    private GregorianCalendar getDateFromUserInput(){
        String dateInput = userInput();
        int month = Integer.parseInt(dateInput.substring(0, 2));
        int date = Integer.parseInt(dateInput.substring(3, 5));
        int year = Integer.parseInt(dateInput.substring(6, 10));
        return new GregorianCalendar(year, month - 1, date);
    }

    /**
     * Create Event function.
     */
    private void createEvent(){
        try {
            printMessage("\nTo create an event, please enter the following information:\n");
            printMessage("Title: ");
            String title = userInput();
            printMessage("Date: ");
            GregorianCalendar dateStamp = getDateFromUserInput();
            printMessage("Start Time: ");
            String startTime = userInput();
            printMessage("End Time: ");
            String endTime = userInput();
            Event event = new Event(title, dateStamp, startTime, endTime);
            eventSystem.addEvent(dateStamp, event);
            printMessage("Succeed! The event has been created.\n");
            printMessage("Continue to [C]reate event OR back to [M]ain menu?\n");
            while (scan.hasNext()) {
                String in = userInput();
                if (in.compareToIgnoreCase("m") == 0)
                    break;
                if (in.compareToIgnoreCase("c") == 0) {
                    createEvent();
                    break;
                }
                printMessage("Continue to [C]reate event OR back to [M]ain menu?\n");
            }
        }catch (StringIndexOutOfBoundsException e){
            printMessage("Wrong Input\n");
            createEvent();
        }
    }

    /**
     * Display all events that has been created or loaded.
     */
    private void displayAllEvent(){
        printMessage("\n-------------------All Events---------------------------\n");
        ArrayList<Event> list = eventSystem.getSortedAllEvents();
        for(Event event: list){
            printMessage("\nEvent Title: " + event.getTitle() +
                    "\nEvent Date: " + event.getDate() +
                    "\nStart time: " + event.getStartTime() +
                    "\nEnd Time: " + event.getEndTime() + "\n");
        }
        printMessage("----------------------End-------------------------------\n");
        printMessage("Back to [M]ain menu?\n");
        while (scan.hasNext()) {
            String in = userInput();
            if (in.compareToIgnoreCase("m") == 0)
                break;
            printMessage("Back to [M]ain menu?\n");
        }
    }

    /**
     * Save all events to file, and quit the program
     * @return True if all events has been saved successfully, false if not succeed.
     */
    private boolean quitSucceed(){
        if(serialize()){
            printMessage("\nSerialization succeed!\n");
        }
        if(saveToString()){
            printMessage("Save to text!\n");
        } else{
            printMessage("Cannot saved!\n");
            return false;
        }
        return true;
    }

    /**
     * Save all events to a serialization file.
     * @return true if succeed, false if failed.
     */
    private boolean serialize(){
        try{
            FileOutputStream fos= new FileOutputStream("EventList.ser");
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

    /**
     * Deserialize the event file, and load to system.
     * @return
     */
    @SuppressWarnings("unchecked")
    private ArrayList<Event> deSerialize(){
        try{
            FileInputStream fileIn = new FileInputStream("EventList.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            ArrayList<Event> eventList = (ArrayList) in.readObject();
            printMessage("\nLoaded "+ eventList.size() + " events.\n");
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

    /**
     * Save all events to a text file, which is readable.
     * @return true if succeed, false if failed.
     */
    private boolean saveToString(){
        try {
            PrintWriter out = new PrintWriter("Allevents.txt");
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
