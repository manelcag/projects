/****************************************************
 * Author:      Manel Casado Garrigues
 * Course:      CSC 121
 * Assignment:  Last Lab (Day)
*****************************************************/

public class Day implements Comparable {
    public static final int MAX=50;
    private Task[] tasks;
    private int numTasks;
    Date myDate;    
    public Day(int year, int month, int day) {
        myDate = new Date(year, month, day);
        tasks = new Task[MAX];
        numTasks = 0;
    }
    
    public Day(Date inDate) {
        myDate = inDate;
        tasks = new Task[MAX];
        numTasks = 0;
    }
    
    public Task[] getTasks() {
        return tasks;
    }
    
    public int getNumTasks() {
        return numTasks;
    }
    
    public String getDate() {
        return myDate.toString();
    }
    
    public Date getMyDate() {
        return myDate;
    }

    public double getTotalHours() {
        int i;
        double sum;
        
        sum = 0;
        for (i=0; i < numTasks; i++)
            sum += tasks[i].getHours();
        return sum;
    }
    
    public void addTask(Task newTask) {
        tasks[numTasks] = newTask;
        numTasks++;
    }
    
    public void addTasks(String description, int hours) {
        Task newTask;
        
        newTask = new Task(description, hours);
        addTask(newTask);
    }
    
    public String toString() {
        String result;
        int i;
        
        result = myDate.toString()+"\n";
        for (i=0; i < numTasks; i++) 
            result += tasks[i].toString()+"\n";
        result += "For a total of "+getTotalHours()+" hours.";
        return result;
    }
    
    public int compareTo(Object other) {
        Day otherDay;
        
        otherDay = (Day) other;
        if (getTotalHours() > otherDay.getTotalHours()) return 1;
        else if (getTotalHours() < otherDay.getTotalHours()) return -1;
        else return 0;
            
    }
    
    public boolean equals(Date otherDate) {
        if (myDate.equals(otherDate))
            return true;
        else
            return false;
    }
    
}