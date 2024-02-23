/*******************************************
/** Author:  Manel Casado
/*  Course:  CSC 221, Fall 2023
/*  Lab:     Lab 5: Inheritance Lab: Expand Tic Tac Toe via inheritance
/*  Created: October 2023
/*  Class:   Connect4Piece, extends Piece
*******************************************/
public class Connect4Piece extends Piece {
    public static final char RED = 'R';
    public static final char YELLOW = 'Y';

    public Connect4Piece() {
        super(RED, YELLOW);
    }

    public Connect4Piece(char symbol) {
        super(symbol);
    }

}
