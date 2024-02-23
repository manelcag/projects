/*******************************************
/** Author:  Manel Casado  
/*  Course:  CSC 221, Fall 2023
/*  Lab:     Lab 6: Linked List Lab: Worm
/*  Created: November 2023
/*  Class:   Segment
*******************************************/
public class Segment {
    int row, column;
    
    public Segment (int inRow,  int inCol) {
        row = inRow;
        column = inCol;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }


    public boolean equals(Segment otherSeg) {
        if (row == otherSeg.getRow() && column == otherSeg.getColumn()) {
            return true;
        }
        return false;
    }
    
    // Decrease row
    public void decRow() {
        if (row > 0) {
            row--;
        }
    }

    // Increase row
    public void incRow() {
        row++;
    }

    // Decrease column
    public void decColumn() {
        if (column > 0) {
            column--;
        }
    }

    // Increase column
    public void incColumn() {
        column++;
    }

    public String toString() {
        return "[" + row + "," + column + "] ";
    }
}