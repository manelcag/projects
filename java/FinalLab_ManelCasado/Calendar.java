/****************************************************
 * Author:      Manel Casado Garrigues
 * Course:      CSC 121
 * Assignment:  Last Lab (Calendar)
*****************************************************/

public class Calendar {
    // Constants
    // Private because they are only used inside the class.
    private static final int NOT_FOUND = -1;
    private static final int MAX_DAYS = 100;

    // Field variables
    private Day[] days;
    private int numDays;
    
    // Constructors
    public Calendar() {
        this.numDays = 0;
        this.days = new Day[MAX_DAYS];
    }
    
    // Accessors
    public Day[] getDays() {
        return days;
    }

    public int getNumDays() {
        return numDays;
    }
    
    //Mutators
    public void setDays(Day[] days) {
        this.days = days;
    }

    public void setNumDays(int numDays) {
        this.numDays = numDays;
    }

    
    // Operations
    public void add(int year, int month, int day, double numberOfHours,	String taskName) {
        int idxFound;
        Day currentDay;
        Task task;
        
        task = new Task(taskName, numberOfHours);
        currentDay = new Day(year, month, day);
        
        idxFound = findDay(currentDay);
        if (idxFound == NOT_FOUND) {
            currentDay.addTask(task);
            days[numDays] = currentDay;
            numDays++;
        } else {
            // Update only tasks
            currentDay = days[idxFound];
            currentDay.addTask(task);
        }
    }
	
    private int findDay(Day day) {
        int position = NOT_FOUND;
        int idx = 0;
        
        while (position == NOT_FOUND && idx < this.numDays) {
            if (days[idx].getDate().equals(day.getDate())) {
                position = idx;
            } else {
                idx ++;
            }
        }
        return position;
    }
	
    private int findDay(Date date) {
        int position = NOT_FOUND;
        int idx = 0;

        while (position == NOT_FOUND && idx < this.numDays) {
            if (days[idx].getMyDate().equals(date)) {
                position = idx;
            } else {
                idx ++;
            }
        }
        return position;
    }

    public String getTasksBrief(Date date) {
        int idxFound;
        Day dayFound = null;
        
        idxFound = findDay(date);
        if (idxFound == NOT_FOUND) {
            return "Day without tasks!";
        } else {
            dayFound = days[idxFound];
            return dayFound.toString();
        }
    }
	
    public double getTotalHours(Date date) {
        int idxFound;
        Day dayFound;
        
        idxFound = findDay(date);
        if (idxFound == NOT_FOUND) {
            return 0;
        } else {
            dayFound = days[idxFound];
            return dayFound.getTotalHours();
        }
    }

    public String getTotalHoursMonth(int year, int month) {
        double totalMonth = 0;
        Day currentDay;
        Date currentDate;
        
        for (int idx = 0; idx < this.numDays; idx++) {
            currentDay = this.days[idx];
            currentDate = currentDay.getMyDate();
            if (currentDate.getYear() == year && currentDate.getMonth() == month) {
                totalMonth+= currentDay.getTotalHours();
            }
        }
        return String.valueOf(totalMonth);
    }
} 