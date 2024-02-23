/****************************************************
 * Author:      Manel Casado Garrigues
 * Course:      CSC 121
 * Assignment:  Last Lab (Date)
*****************************************************/

public class Date implements Comparable {
    private int year, month, day;
    
    public Date (int inYear, int inMonth, int inDay) {
        year = inYear;
        month = inMonth;
        day = inDay;
        if (day > getNumDaysMonth(month, year) ||
            month < 1 || month > 12) {
            year = -1;
            month = -1;
            day = -1;
        }
    }
    
    public int getYear() {
        return year;
    }
    
    public int getMonth() {
        return month;
    }
    
    public int getDay() {
        return day;
    }
    
    public static int getNumDaysMonth(int month, int year) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12: return 31;
            case 4 :
            case 6 :
            case 9 :
            case 11 : return 30;
            case 2 :
                if (year % 4 != 0) return 28;
                if (year % 400 == 0) return 29;
                if (year % 100 == 0) return 28;
                return 29;
            default : return -1;
        }
    }
    
    public boolean equals(Date other) {
        if (year == other.getYear() &&
            month == other.getMonth() &&
            day == other.getDay())
            return true;
        return false;
    }
    
    public int compareTo(Object other) {
        Date otherDate;
        
        otherDate = (Date) other;
        if (year != otherDate.getYear())
            return year - otherDate.getYear();
        else if (month != otherDate.getMonth())
            return month - otherDate.getMonth();
        else
            return day - otherDate.getDay();
    }

    public String toString() {
        return year+"/"+month+"/"+day;
    }
}