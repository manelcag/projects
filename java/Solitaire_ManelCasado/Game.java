/*******************************************
/** Author:  Manel Casado Garrigues
/*  Course:  CSC 122, Fall 2022
/*  Lab:     Lab 3: Solitaire
/*  Class:   Game
*******************************************/
public class Game {
    public static final int NUMPILES = 15;
    private SpecialStack[] stacks;
	
    public Game() {
        this.stacks = new SpecialStack[NUMPILES];
    }

    public Game(SpecialStack[] stacks) {
        this.stacks = stacks;
    }
	
    public void makeMove(int source, int dest) {
        Card card, destTopCard;
        if (this.stacks[source] != null && this.stacks[dest] != null) {
            if (this.stacks[dest].isEmpty()) {
            // We can do the movement because staack is empty
            card = this.stacks[source].pop();
                this.stacks[dest].push(card);
                return;
            } else if (this.stacks[dest].top<3){
                // We can only do the movement if there is less than 4 cards
                card = this.stacks[source].getTopCard();
                destTopCard = this.stacks[dest].getTopCard();
                if (destTopCard.getRank() == card.getRank() && destTopCard.getType() != card.getType()) {
                    // We can do the movement if the card number is the same
                    card = this.stacks[source].pop();
                    this.stacks[dest].push(card);
                    return;
                }
            }
        }
    }
	
    public boolean gameOver() {
        Stack stack;
        Card topCard;
	
        for (int i=0; i<stacks.length; i++) {
            stack = stacks[i];

            if (stack == null || stack.isEmpty()) {
                return false;
            } else {
                topCard = stack.getTopCard();
                if (existAPossibleMov(topCard)) {
                    return false;
                }
            }
        }
        return true;
    }
	
    private boolean existAPossibleMov(Card cardToCompare) {
        Stack stack;
        Card topCard;
        
        for (int i= 0; i<stacks.length; i++) {
            stack = stacks[i];
            topCard = stack.getTopCard();
            if (topCard == null) {
                // stack is empty, there is enought space!
                return true;
            } else {
                if (cardToCompare.getRank() == topCard.getRank() && cardToCompare.getType() != topCard.getType()) {
                    // You can move selected card to another stack!
                    return true;
                }
            }
        }
        return false;
    }
	
    public String showGame() {
        String result = "";

        for (int i=0; i<stacks.length; i++) {
            result = result + "pile ";
            result = result + (i + 1);
            result = result + ":\n";
            if (stacks[i] == null || stacks[i].isEmpty()) {
                result = result + " empty.\n";
            } else {
                result = result + stacks[i];	// Calls method .toString()
            }
        }
	
        result = result + "\n";

        return result;
    }
}
