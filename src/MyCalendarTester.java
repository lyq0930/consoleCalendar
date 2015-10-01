/**
 * Created by Louis on 9/28/15.
 */
import java.util.Scanner;
//test this
public class MyCalendarTester {
    public static void main(String[] args) {
        MyCalendar cal = new MyCalendar();
        cal.printCurrentMonthView();
        Scanner scan = new Scanner(System.in);
        cal.mainMenu(scan);
    }
}

//        System.out.println("[P]revious or [N]ext");
//        Scanner scan = new Scanner(System.in);
//        while(scan.hasNext()){
//            String input = scan.nextLine();
//            switch(input){
//                case "n":case "N":
//                    cal.add(Calendar.MONTH, +1);
//                    printMonthView(cal);
//                    System.out.println("[P]revious or [N]ext");
//                    break;
//                case "p":case "P":
//                    cal.add(Calendar.MONTH, -1);
//                    printMonthView(cal);
//                    System.out.println("[P]revious or [N]ext");
//                    break;
//                default:break;
//            }
//        }
//    }
//
//    public static void printMontView(GregorianCalendar c) {
//        MONTHS[] arrayOfMonths = MONTHS.values();
//        System.out.println("     " + arrayOfMonths[c.get(Calendar.MONTH)] + " " + c.get(Calendar.YEAR));
//        System.out.println("Su Mo Tu We Th Fr Sa");
//
//        //create an temperate object for the current month to get the first day_of_week
//        GregorianCalendar temp = new GregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1);
//        int firstDay_of_week = temp.get(Calendar.DAY_OF_WEEK);
//
//        int count = 0;
//        for (int blank = 1; blank < firstDay_of_week; blank++){
//            System.out.print("   ");
//            count ++;
//        }
//        for(int day = 1; day <= c.getActualMaximum(Calendar.DAY_OF_MONTH); day++){
//            if(day == c.get(Calendar.DAY_OF_MONTH))
//                System.out.printf("[%d] ", day);
//            else
//                System.out.printf("%2d ", day);
//            count++;
//            if(count == 7) {
//                System.out.println();
//                count = 0;
//            }
//        }
//        System.out.println();
//    }
//}