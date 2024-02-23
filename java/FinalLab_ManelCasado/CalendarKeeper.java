/****************************************************
 * Author:      Manel Casado Garrigues
 * Course:      CSC 121
 * Assignment:  Last Lab (CalendarKeeper)
*****************************************************/

import java.util.*;

public class CalendarKeeper {

    public static void main(String[] args) {
        Calendar calendar;

        calendar = new Calendar();
		
        int menuOption;
        Scanner keyboard;
        keyboard = new Scanner(System.in);

        do {
            menuOption = getMenuOption(keyboard);

            //different options of the menu
            switch (menuOption) {
            case 1:
                addNewTask(keyboard, calendar);
                break;
            case 2:
                diplayDayTasks(keyboard, calendar);
                break;
            case 3:
                showNumberOfHoursDay(keyboard, calendar);
                break;
            case 4:
                showNumberOfHoursMonth(keyboard, calendar);
                break;
            }
        } while (menuOption != 0);

        System.out.println("Program ended!");
    }

    // show menu and return selected option
    private static int getMenuOption(Scanner keyboard) {
        int option;
		
        System.out.println("");
        System.out.println("1. Add a task.");
        System.out.println("2. Diplay information about a day.");
        System.out.println("3. Show the number of hours assigned to a day.");
        System.out.println("4. Show the number of hours assigned to a month.");
        System.out.println("");
        System.out.println("Select an option number (0 to exit):");

        do {
            option = keyboard.nextInt();
            if (option < 0 || option > 4) {
                System.err.println("Invalid option. Please try again.");
            }
        } while (option < 0 && option > 4);

        return option;
    }
	
    //adding task
    private static void addNewTask(Scanner keyboard, Calendar calendar) {
        String strTaskDate;
        Date taskDate;
        double numberOfHours;
        String taskName;
        
        System.out.println("Add a task for day.");
        strTaskDate = getTaskDate(keyboard);
        taskDate = strToDate(strTaskDate);
        if (taskDate != null) {
            numberOfHours = getHours(keyboard);
            taskName = getTaskName(keyboard);
            calendar.add(taskDate.getYear(), taskDate.getMonth(), taskDate.getDay(), 
                numberOfHours, taskName);
        }
        //addTaskToFile(taskDate, numberOfHours, taskName);
    }
	
    private static String getTaskDate(Scanner keyboard) {
        String date;
		
        // Read last input
        keyboard.nextLine();
        
        System.out.println("Enter a valid date [year monthNumber day]");
        date = keyboard.nextLine();
    	
        return date;
    }

    
    // ask the number of hours to the user
    private static double getHours(Scanner keyboard) {
        double hours = 0;
        boolean error;

        do {
            error = false;
            System.out.println("Enter number of hours for the task: ");
            try {
                hours = keyboard.nextDouble();
                System.out.println("Number of hours for the task is:" + hours);
            } catch (Exception e) {
            // if there was a format error, clean buffer. 
                keyboard.nextLine();  
                error = true;
            }
            if (error || hours < 0) {
                System.out.println("Number of hours must be greater than 0. " + "Please enter a valid number of hours.");
                error = true;
            }
        } while (error);

        return hours;
    }
		
    //ask name of the task
    private static String getTaskName(Scanner keyboard) {
        String taskName;

        System.out.println("Enter a task name (Enter to abort task creation): ");
        // Reset keyboard, read last input
        keyboard.nextLine();
		
        taskName = keyboard.nextLine();
        return taskName;
    }
	
    private static void diplayDayTasks(Scanner keyboard, Calendar calendar) {
        String strDate;
        Date date;
		
        strDate = getTaskDate(keyboard);
        date= strToDate(strDate);
        if (date != null) {
            System.out.println(calendar.getTasksBrief(date));
        }
    }


    private static void showNumberOfHoursDay(Scanner keyboard, Calendar calendar) {
        String strDate;
        Date date;
        
        strDate = getTaskDate(keyboard);
        date= strToDate(strDate);
        if (date != null) {
            System.out.println(calendar.getTotalHours(date));
        }
    }

    private static void showNumberOfHoursMonth(Scanner keyboard, Calendar calendar) {
        int year, month;
        System.out.println("Enter a valid year:");
        // Reset keyboard, read last input
        keyboard.nextLine();
	
        // Asks month and year
        year = keyboard.nextInt();
           
        System.out.println("Enter a valid month of the year:");
        
        month =  keyboard.nextInt();
        System.out.println(calendar.getTotalHoursMonth(year, month));
    }
	
    private static Date strToDate(String strDate) {
        int yearNum, monthNum, dayNum;
        Date date = null;
        String[] splitedDate;

        try {
            splitedDate = strDate.split(" ");
            yearNum = Integer.valueOf(splitedDate[0]);
            monthNum = Integer.valueOf(splitedDate[1]);
            dayNum = Integer.valueOf(splitedDate[2]);
            date = new Date(yearNum, monthNum, dayNum);
        } catch (Exception e) {
            System.err.println("Cannot convert " + strDate + " to a valid date");
        }
        return date;
    }
}