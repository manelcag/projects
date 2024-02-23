import java.io.*;
import java.util.*;
/*******************************************
/** Author:  Dr. Cathy Bareiss
/*  Editor:  Manel Casado
/*  Course:  CSC 122, Fall 2022
/*  Lab:     Lab 4: Sorting
/*  Created: August 2022
/*  Class:   SellItem -> used to represent one item
/*  Related classes:  DataGroup, BigData, Queue
*******************************************/

public class SellItem implements Serializable, Comparable {
    // Field variables -> the fields from the data that matter
    private char location;
    private char type;
    private int id;
    private int amount;
    private double cost;
    
    // constructors
    // constructor for a line with ,'s
    public SellItem(String line) {
        String[] parts;
        parts = line.split(",");
        location = parts[0].charAt(0);
        type = parts[4].charAt(0);
        id = Integer.parseInt(parts[6]);
        amount = Integer.parseInt(parts[8]);
        cost = Double.parseDouble(parts[9]);
    }
    
    // constructor for individual components
    public SellItem(char inLoc, char inTyp, int inId, int inAm, double inCos) {
        location = inLoc;
        type = inTyp;
        id = inId;
        amount = inAm;
        cost = inCos;
    }
    
    // accessors
    public char getLocation() {
        return location;
    }
    public char getType() {
        return type;
    }
    public int getId() {
        return id;
    }
    public int getAmount() {
        return amount;
    }
    public double getCost() {
        return cost;
    }
    
    // not mutators.  Not changing data at this time

    // helpers
    
    // Comparing based in id.  Can be changed to something else
    public int compareTo(Object other) {
        return id - ((SellItem)other).getId();
    }
    
    // equal based on id.  Can be changed to something else
    public boolean equals(SellItem other) {
        return id == other.getId();
    }

    // just outputs the id.  probably should be updated
    public String toString() {
        return ""+id;
    }
}    