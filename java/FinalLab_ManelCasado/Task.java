/****************************************************
 * Author:      Manel Casado Garrigues
 * Course:      CSC 121
 * Assignment:  Last Lab (Task)
*****************************************************/

public class Task implements Comparable {
    private String description;
    private double hours;
    
    public Task(String inDesc, double inHours) {
        description = inDesc;
        hours = inHours;
    }
    
    public double getHours() {
        return hours;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescrption(String desc) {
        description = desc;
    }
    
    public void setHours(double hrs) {
        hours = hrs;
    }
    
    public boolean equals(Task other) {
        if (hours == other.getHours() &&
            description.equals(other.getDescription()))
            return true;
        return false;
    }
    
    public int compareTo(Object other) {
        Task otherT;
        
        otherT = (Task) other;
        if (hours > otherT.getHours()) return 1;
        else if (hours < otherT.getHours()) return -1;
        else return 0;
    }
    
    public String toString() {
        return ""+hours+" hours doing "+description;
    }
}