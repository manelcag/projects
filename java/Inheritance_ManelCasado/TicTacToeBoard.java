/*******************************************
/** Author:  Manel Casado
/*  Course:  CSC 221, Fall 2023
/*  Lab:     Lab 5: Inheritance Lab: Expand Tic Tac Toe via inheritance
/*  Created: October 2023
/*  Class:   TicTacToeBoard, extends Board
*******************************************/
public class TicTacToeBoard extends Board {
    public TicTacToeBoard() {
        super(3, 3);
    }

    public boolean legalMove(Move move) {
        int row, column;
        
        row = move.getRow();
        column = move.getColumn();
        if (row < 0) return false;
        if (column < 0) return false;
        if (row >= numRows) return false;
        if (column >= numCols) return false;
        if (myBoard[row][column].isAvailable()) return true;
        return false;
    }

    protected int getFirstEmptyRow() {
        // Not needed for TicTacToe
        return 0;
    }

    protected int getFirstEmptyRow(int row) {
        // Not needed for TicTacToe
        return 0;
    }
    public boolean winner(Player player) {
        Piece curPiece;
        int row, column;
        boolean legitSoFar;
        
        curPiece = player.getPiece();
        for (row = 0; row < myBoard.length; row++) {
            legitSoFar = true;
            for (column = 0; column < myBoard[0].length; column++)
                if (!curPiece.equals(myBoard[row][column]))
                    legitSoFar = false;
            if (legitSoFar) return true;
        }
        for (column = 0; column < myBoard[0].length; column++) {
            legitSoFar = true;
            for (row = 0; row < myBoard.length; row++)
                if (!curPiece.equals(myBoard[row][column]))
                    legitSoFar = false;
            if (legitSoFar) return true;
        }
        
        legitSoFar = true;
        for (row = 0; row < myBoard.length; row++)
            if (!curPiece.equals(myBoard[row][row]))
                legitSoFar = false;
        if (legitSoFar) return true;
        
        legitSoFar = true;
        column = myBoard[0].length-1;
        for (row = 0; row < myBoard.length; row++) {
            if (!curPiece.equals(myBoard[row][column]))
                legitSoFar = false;
            column--;
        }
        
        return legitSoFar;
        
    }

    protected void initBoard() {
        int row, column;
        
        for (row = 0; row < numRows; row++)
            for (column = 0; column < numCols; column++) 
                myBoard[row][column] = new TicTacToePiece(Piece.BLANK);
    }
}
