/*******************************************
/** Author:  Manel Casado Garrigues
/*  Course:  CSC 122, Fall 2022
/*  Lab:     Lab 3: Solitaire
/*  Class:   GamePlayer
*******************************************/
import java.io.*;

public class GamePlayer {
    public static void main(String[] args) throws IOException {
        Card[] cardsTest;
        Deck myDeck;
        Game myGame;

        cardsTest = createTest20Cards();

        // Test 1 build a deck
        myDeck = new Deck(cardsTest);
        System.out.println(myDeck);
		
        // Test 2 shuffle
        
        // uncomment suffle after done with testing
        // myDeck.shuffle();
        System.out.println(myDeck);
		
        // Game
        myGame = new Game();
        System.out.println(myGame.showGame());
        if (myGame.gameOver()) {
            System.out.println("Game over");
        }
		
        // Test 1. ... first test playing.
        myGame = new Game(fillStacks(cardsTest));
        System.out.println(myGame.showGame());

        myGame.makeMove(3, 0);
        System.out.println(myGame.showGame());
        if (myGame.gameOver()) {
            System.out.println("Game over");
        } else {
            System.out.println("You can do more movements");
        }
		
        // Incorrect movement, there are 4 cards in stack 5
        myGame.makeMove(3, 5);
        System.out.println(myGame.showGame());
        if (myGame.gameOver()) {
            System.out.println("Game over");
        } else {
            System.out.println("You can do more movements");
        }

        // Right movement, free space in stack 4
        myGame.makeMove(3, 0);
        System.out.println(myGame.showGame());
        // Incorrect movement, from stack 6 to 4
        myGame.makeMove(5, 3);
        System.out.println(myGame.showGame());
        if (myGame.gameOver()) {
            System.out.println("Game over");
        } else {
            System.out.println("You can do more movements");
        }

        // Test 2 ... full deck playing test
        cardsTest = createTest52Cards();
        myGame = new Game(fillStacks(cardsTest));
        System.out.println(myGame.showGame());

        myGame.makeMove(3, 0);
        System.out.println(myGame.showGame());
        if (myGame.gameOver()) {
            System.out.println("Game over");
        } else {
            System.out.println("You can do more movements");
        }
    }

    public static Card[] createTest20Cards() {
        Card[] cards;
        cards = new Card[5 * 4];
        int lastCard = 0;
        // This test is has only 20 cards!

        for (int value = 1; value < 6; value++) {
            for (int suite = 0; suite < 4; suite++) {
                cards[lastCard] = new Card(value, suite);
                lastCard++;
            }
        }

        return cards;
    }
	
    public static Card[] createTest52Cards() {
        Card[] cards;
        cards = new Card[13 * 4];
        int lastCard = 0;
        // This test has the complete deck of cards

        for (int value = 1; value < 14; value++) {
            for (int suite = 0; suite < 4; suite++) {
                cards[lastCard] = new Card(value, suite);
                lastCard++;
            }
        }

        return cards;
    }

    public static SpecialStack[] fillStacks (Card[] cards) {
        SpecialStack[] stacks;
        SpecialStack stack;
	
        stacks = new SpecialStack[15];
		
        // First special stack (empty)
        stacks[0] = new SpecialStack();

        // Second special stack (empty)
        stacks[1] = new SpecialStack();
		
        // 12 
        int i = 0;
        for (int stackNum = 2; stackNum < 15; stackNum++) {
            stack = new SpecialStack();
            stacks[stackNum] = stack;
            for (int cardNum=0; cardNum<4; cardNum++) {
                if (i < cards.length) {
                    // Ensure card exists
                    stack.push(cards[i]);
                }
                i++;
            }
        }
		
        return stacks;
		
    }
}
