/*******************************************
/** Author:  Manel Casado
/*  Course:  CSC 221, Fall 2023
/*  Lab:     Lab 3: Objects Lab, UTicTacToe
/*  Class:   Move
*******************************************/

public class Move {
    private int row;
    private int column;
    
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
    
    public boolean equals(Move other) {
        if (row == other.getRow() && column == other.getColumn()){
            return true;
        }
        return false;
    }
    
    public String toString() { 
        return "("+row+", "+column+")";
    }
    
}