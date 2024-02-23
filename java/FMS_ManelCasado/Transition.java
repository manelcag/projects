/*******************************************
/** Author:  Dr. Cathy Bareiss
/*  Course:  CSC 122, Fall 2022
/*  Lab:     Lab 1: Finite State Machine
/*  Created: August 2022
/*  Class:   Transition -> contains one transition decription
/*  Related classes:  Transitions
*******************************************/
public class Transition {
    private char chr;
    private int oldstate, newstate;
    
    // constructor
    public Transition(int olds, int news, char ch) {
        chr = ch;
        oldstate = olds;
        newstate = news;
    }
    
    // accessors
    public int getNewState() {
        return newstate;
    }
    
    public int getOldState() {
        return oldstate;
    }
    
    public char getChr() {
        return chr;
    }
    
    // no mutators because this is non-self-modifying code

    // standard helpers (no compareTo on purpose)
    
    // this equals is used to see if it is the transition for current situation
    public boolean equals (int olds, char ch) {
        if (oldstate == olds && chr == ch) return true;
        return false;
    }
    
    // standard equals
    public boolean equals(Transition other) {
        if (oldstate == other.getOldState() && chr == other.getChr())
            return true;
        return false;
    }
    
    public String toString() {
        return "("+oldstate+" , "+chr+" , "+newstate+")";
    }
}