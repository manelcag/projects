/*******************************************
/** Author:  Dr. Cathy Bareiss
/*  Editors: Manel Casado, Enmanuel David
/*  Course:  CSC 221, Fall 2023
/*  Lab:     Lab 5: Inheritance Lab: Expand Tic Tac Toe via inheritance
/*  Created: October 2023
/*  Class:   Piece
*******************************************/
public abstract class Piece {
    public static final char BLANK = ' ';

    private char type;
    public static char symbol1;
    public static char symbol2;
    
    public Piece(char symbol) {
        type = symbol;
    }
    
    public Piece(char newSymbol1, char newSymbol2) {
        type = BLANK;
        symbol1 = newSymbol1;
        symbol2 = newSymbol2;
    }
    
    public char getPiece() {
        return type;
    }
    
    public char getSymbol1() {
        return symbol1;
    }

    public char getSymbol2() {
        return symbol2;
    }
    
    public boolean equals(Piece other) {
        if (type == other.type) {
            return true;
        }
        return false;
    }
    
    public boolean isAvailable() {
        return type == BLANK;
    }
    
    public String toString() {
        if (type == BLANK) return "_";
        else return type+"";
    }


    public Piece oppositePiece() {
        return null;
    }
}