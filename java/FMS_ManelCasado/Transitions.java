/*******************************************
/** Author:  Dr. Cathy Bareiss
/*  Editor:  Manel Casdo
/*  Course:  CSC 221, Fall 2023
/*  Lab:     Lab 1: Finite State Machine
/*  Created: August 2023
/*  Class:   Transitions
/*  Related classes:  Transition
*******************************************/
public class Transitions {
    public static final int MAX=100;
    private Transition[] transitions;
    private int n;
    
    public Transitions () {
        int i;
        
        transitions = new Transition[MAX];
        n = 0;
        for (i=0; i < MAX; i++) transitions[i] = null;
    }
    
    public Transitions(int num) {
        int i;
        
        transitions = new Transition[num];
        n = 0;
        for (i=0; i < num; i++) transitions[i] = null;
    }
    // add the sent transition to the list
    public void addTransition(Transition newTrans) {
        transitions[n] = newTrans;
        n++;
    }
    
    // add the details of the sent transition to the list.
    // first create the transition.  Try not to repeat code
    public void addTransition(int olds, int news, char chr) {
        Transition newTransition;
        newTransition = new Transition(olds, news, chr);
        transitions[n]= newTransition;
        n++;
    }
    
    // use a sequential search and the equals method for
    // a transition to find the one that matches the 
    // details sent.  Do not break or return from inside
    // your loop.  You could use recursion if you want
    // but that is not expected.
    public Transition lookup(int olds, char chr) {
        int i= 0;
        while (i < n) {
            if (transitions[i].equals(olds, chr)) {
                return transitions[i];
            }
            // Next transition
            i++;
        }
        return null;
    }
    
    // don't worry about this one.
    public boolean isDeterministic() {
        return false;
    }
    
    public String toString() {
        String result;
        int i;
        
        result = "";
        for (i=0; i< n; i++)
            result += transitions[i].toString()+"\n";
        return result;
    }
}