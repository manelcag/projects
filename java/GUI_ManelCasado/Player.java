/*******************************************
/** Author:  Dr. Bareiss
/*  Editor:  Manel Casado
/*  Course:  CSC 221, Fall 2023
/*  Lab:     Lab 4: GUI Lab, TicTacToe
/*  Class:   Player
*******************************************/

public class Player {
    private Piece piece;
    private String name;
    
    public Player(String name, Piece piece) {
        this.name = name;
        this.piece = piece;
    }
    
    public String getName() {
        return name;
    }
    
    public Piece getPiece() {
        return piece;
    }
    
    public String toString() {
        return name+" playing "+piece.toString();
    }
}