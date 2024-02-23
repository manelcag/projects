/*******************************************
/** Author:  Manel Casado
/*  Course:  CSC 221, Fall 2023
/*  Lab:     Lab 5: Inheritance Lab: Expand Tic Tac Toe via inheritance
/*  Created: October 2023
/*  Class:   TicTacToePiece, extends Piece
*******************************************/
public class TicTacToePiece extends Piece {
    public static final char X = 'X';
    public static final char O = 'O';

    public TicTacToePiece() {
        super(X, O);
    }

    public TicTacToePiece(char symbol) {
        super(symbol);
    }


}
