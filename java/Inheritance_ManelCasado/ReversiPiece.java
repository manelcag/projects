/*******************************************
/** Author:  Enmanuel David
/*  Course:  CSC 221, Fall 2023
/*  Lab:     Lab 5: Inheritance Lab: Expand Tic Tac Toe via inheritance
/*  Created: October 2023
/*  Class:   ReversiPiece, extends Piece
*******************************************/

public class ReversiPiece extends Piece {
    public static final char WHITE = 'W';
    public static final char BLACK = 'B';

//    public static final ReversiPiece B_F = new ReversiPiece("BF");
//    public static final ReversiPiece W_F = new ReversiPiece("WF");


    public ReversiPiece() {
        super(WHITE, BLACK);
    }

    public ReversiPiece(char symbol) {
        super(symbol);
    }


}
