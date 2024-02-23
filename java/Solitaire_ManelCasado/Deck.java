/*******************************************
/** Author:  Manel Casado Garrigues
/*  Course:  CSC 122, Fall 2022
/*  Lab:     Lab 3: Solitaire
/*  Class:   Deck
*******************************************/
import java.util.*;

public class Deck {
    public static final int MAX = 52;
    private Card[] deck;
    private int numItems;
	
    // Constructor
    public Deck() {
        this.deck = new Card[MAX];
        this.numItems = 0;
    }
	
    public Deck(Card[] cards) {
        this.deck = cards;
        this.numItems = cards.length;
    }

    public void shuffle() {
        // Barajar
        Random rand = new Random();

        for (int i = 0; i < deck.length; i++) {
             int nextInt = rand.nextInt(deck.length);
             Card temp = deck[i];
             deck[i] = deck[nextInt];
             deck[nextInt] = temp;
        }	
    }
	
    public Card getOne() {
        Card card = this.deck[numItems-1];
        numItems--;
        return card;
    }

    public String toString() {
        String result;
        Card card;
        
        result = "Pending cards\n";
        
        for (int i=0; i < this.numItems; i++) {
            card = this.deck[i];
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