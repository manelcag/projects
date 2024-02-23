/*******************************************
/** Author:  Manel Casado Garrigues
/*  Course:  CSC 122, Fall 2022
/*  Lab:     Lab 3: Solitaire
/*  Class:   Card
*******************************************/
import java.util.*;

public class Card {
    public final static int HEART = 0;
    public final static int CLUB = 1;
    public final static int DIAMOND = 2;
    public final static int SPADE = 3;
	
    // Field variables
    private int type;
    private int rank;
	
    // Constructors
    public Card(int rank, int type) {
        this.rank = rank;
        this.type = type;
    }

    // Accessors
    public int getType() {
        return type;
    }

    public int getRank() {
        return rank;
    }

    // Helpers
    public boolean equals(Object otherObj) {
        if (this == otherObj)
            return true;
        if (otherObj == null)
            return false;
        if (getClass() != otherObj.getClass())
            return false;
        Card other = (Card) otherObj;
        return Objects.equals(type, other.type) && rank == other.rank;
    }


    public String toString() {
        String result = "";

        switch (this.type) {
        case HEART:
            result = "Heart ";
            break;
        case CLUB:
        result = "Club ";
            break;
        case DIAMOND:
            result = "Diamond ";
            break;
        case SPADE:
            result = "Spade ";
        }
		
        result+= this.rank;
        return result;
    }
}
