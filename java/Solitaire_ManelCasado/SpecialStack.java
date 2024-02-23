/*******************************************
/** Author:  Manel Casado Garrigues
/*  Course:  CSC 122, Fall 2022
/*  Lab:     Lab 3: Solitaire
/*  Class:   SpecialStack
*******************************************/
public class SpecialStack extends Stack {
    public boolean allSame() {
        Card card;
        int firstValue;
        if (!isEmpty()) {
            card = stack[0];
            firstValue = card.getRank();
            for (int n=1; n<top; n++) {
                card = stack[n];
                if (card.getRank() != firstValue) {
                    return false;
                }
            }
        }
    		
        return true;
    }
}
