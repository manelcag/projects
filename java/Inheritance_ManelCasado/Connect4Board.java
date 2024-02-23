/*******************************************
/** Author:  Manel Casado
/*  Course:  CSC 221, Fall 2023
/*  Lab:     Lab 5: Inheritance Lab: Expand Tic Tac Toe via inheritance
/*  Created: October 2023
/*  Class:   Connect4Board, extends Board
*******************************************/
public class Connect4Board extends Board {

    private static final int COLUMNS = 7;
    private static final int ROWS = 6;
    
    public Connect4Board() {
        super(ROWS, COLUMNS);
    }

    protected boolean legalMove(Move move) {
        int column;
        int row;
        
        if (move == null) return false;
        
        column = move.getColumn();
        if (column < 0) return false;
        if (column >= numCols) return false;
        
        row = getFirstEmptyRow(column);
        if (row >= 0 && myBoard[row][column].isAvailable()) return true;
        return false;
    }
    
    protected int getFirstEmptyRow(int column) {
        int row;
        
        row = ROWS - 1;
        while (row >= 0 && !myBoard[row][column].isAvailable()) {
            row--;
        }
        
        return row;
    }

    public boolean winner(Player player) {
        Piece curPiece;
        int row, column;
        
        curPiece = player.getPiece();
        for (row = 0; row < numRows; row++) {
            for (column = 0; column < numCols; column++) {
                if (myBoard[row][column] == curPiece) {
                    // Horizontal
                    if (column + 3 < numCols &&
                            myBoard[row][column + 1] == curPiece &&
                            myBoard[row][column + 2] == curPiece &&
                            myBoard[row][column + 3] == curPiece) {
                        return true;
                    }
                    // Vertical
                    if (row + 3 < numRows &&
                            myBoard[row + 1][column] == curPiece &&
                            myBoard[row + 2][column] == curPiece &&
                            myBoard[row + 3][column] == curPiece) {
                        return true;
                    }
                    // Diagonal (top-left to bottom-right)
                    if (row + 3 < numRows && column + 3 < numCols &&
                            myBoard[row + 1][column + 1] == curPiece &&
                            myBoard[row + 2][column] == curPiece &&
                            myBoard[row + 3][column + 3] == curPiece) {
                        return true;
                    }
                    // Diagonal (top-right to bottom-left)
                    if (row + 3 < numRows && column - 3 >= 0 &&
                            myBoard[row + 1][column - 1] == curPiece &&
                            myBoard[row + 2][column - 2] == curPiece &&
                            myBoard[row + 3][column - 3] == curPiece) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }

    protected void initBoard() {
        int row, column;
        
        for (row = 0; row < numRows; row++)
            for (column = 0; column < numCols; column++) 
                myBoard[row][column] = new TicTacToePiece(Piece.BLANK);
    }
}
