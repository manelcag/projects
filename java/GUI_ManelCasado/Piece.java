/*******************************************
/** Author:  Dr. Bareiss
/*  Editor:  Manel Casado
/*  Course:  CSC 221, Fall 2023
/*  Lab:     Lab 4: GUI Lab, TicTacToe
/*  Class:   Piece
*******************************************/

public class Piece {
    public static final char BLANK = ' ';
    public static final char X = 'X';
    public static final char O = 'O';
    public static char type;
    
    public Piece() {
        type = BLANK;
    }
    
    public Piece(char inType) {
        type = inType;
    }
    
    public char getPiece() {
        return type;
    }
    
    public boolean equals(Piece other) {
        return type==other.getPiece();
    }
    
    public boolean isAvailable() {
        return type == BLANK;
    }
    
    public String toString() {
        if (type == BLANK) return "_";
        else return type+"";
    }
}