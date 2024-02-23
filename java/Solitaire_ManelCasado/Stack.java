/*******************************************
/** Author:  Manel Casado Garrigues
/*  Course:  CSC 122, Fall 2022
/*  Lab:     Lab 3: Solitaire
/*  Class:   Stack
*******************************************/
public class Stack {
    // Field variables
    public static final int MAX = 4; // default max number
    public static final int EMPTY = -1 ; // Constant indicating stack is empty
    
    protected Card[] stack;
    protected int top;                   // number in use

    // Constructors
    
    // create a constructor of default size with all elements at null
    // and empty
    public Stack () {
        this.stack = new Card[MAX];
    	
        for (int i= 0; i<MAX; i++) {
            this.stack[i]= null;
        }
    	
        top = EMPTY;
    }
    
    public Stack (int size) {
        this.stack = new Card[size];
    	
        for (int i= 0; i<size; i++) {
            this.stack[i]= null;
        }
    	
        top= EMPTY;
    }
    
    public boolean isEmpty() {
        if (top == EMPTY) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean isFull() {
        if (top == MAX - 1) {
            return true;
        } else {
            return false;
        }
    }
    
    public Card getTopCard() {
        if (top >= 0) {
            return stack[top];
        }
        return null;
    }
    
    // Add an element to the stack
    public void push(Card item) {
        this.top++;
        this.stack[this.top]= item;
    }
    
	public Card pop() {
        Card card = null;
    	
        if (this.top >= 0) {
            card= this.stack[this.top];
            this.top--;
        }
        return card;
    }
    
    // Helpers
    // Only one needed is a toString
    public String toString() {
        String result;
        Card card;
        
        result = "";
        for (int i=0; i<= this.top; i++) {
            card = this.stack[i];
            switch (card.getType()) {
            case Card.HEART:
                result= result + card.getRank();
                result= result + " heart";
                break;
            case Card.CLUB:
                result= result + card.getRank();
                result= result + " club";
                break;
            case Card.DIAMOND:
                result= result + card.getRank();
                result= result + " diamond";
                break;
            case Card.SPADE:
                result= result + card.getRank();
                result= result + " spade";
                break;
            }
            result = result + "\n";
        }
        return result;
    }
}
