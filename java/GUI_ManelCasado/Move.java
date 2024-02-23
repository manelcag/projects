/*******************************************
/** Author:  Dr. Bareiss
/*  Editor:  Manel Casado
/*  Course:  CSC 221, Fall 2023
/*  Lab:     Lab 4: GUI Lab, TicTacToe
/*  Class:   Move
*******************************************/

public class Move {
    int row, column;
    
    public Move() {
        row = -1;
        column = -1;
    }
    
    public Move(int row, int column) {
        this.row = row;
        this.column = column;
    }
    
    public int getRow() {
        return row;
    }
    
    public int getColumn() {
        return column;
    }
    
    public String toString() {
        return "("+row+","+column+")";
    }
}