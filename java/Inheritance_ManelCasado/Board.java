/*******************************************
/** Author:  Dr. Cathy Bareiss
/*  Editors: Manel Casado, Enmanuel David
/*  Course:  CSC 221, Fall 2023
/*  Lab:     Lab 5: Inheritance Lab: Expand Tic Tac Toe via inheritance
/*  Created: October 2023
/*  Class:   Board
*******************************************/
public abstract class Board {
    public static final int DEFAULTSIZE = 3;
    protected Piece[][] myBoard;
    protected Integer numRows;
    protected Integer numCols;
    
    public Board() {
        myBoard = new Piece[DEFAULTSIZE][DEFAULTSIZE];
        initBoard();
    }
    
    public Board(int rows, int cols) {
        numRows = rows;
        numCols = cols;
        myBoard = new Piece[numRows][numCols];
        initBoard();
    }
    
    protected abstract void initBoard();
    
    protected abstract boolean legalMove(Move move);
    
    public void makeMove(Move move, Player player) {
        myBoard[move.getRow()][move.getColumn()] = player.getPiece();
    }
    
    public String toString() {
        int row, column;
        String result;
        
        result = "";
        for (row = 0; row < myBoard.length; row++) {
            for (column = 0; column < myBoard[0].length; column++)
                result += myBoard[row][column].toString()+" ";
            result += "\n";
        }
        return result;
    }

    protected abstract int getFirstEmptyRow(int row);

    protected abstract boolean winner(Player player);
}
    