/*******************************************
/** Author:  Enmanuel David
/*  Course:  CSC 221, Fall 2023
/*  Lab:     Lab 5: Inheritance Lab: Expand Tic Tac Toe via inheritance
/*  Created: October 2023
/*  Class:   ReversiBoard, extends Board
*******************************************/
public class ReversiBoard extends Board {
    private static final int COLUMNS = 8;
    private static final int ROWS = 8;

    public ReversiBoard() {
        super(ROWS, COLUMNS);
        initBoard();
    }

    protected boolean legalMove(Move move) {
        int column = move.getColumn();
        int row = move.getRow();

        if (column < 0 || column >= numCols) return false;
        if (row < 0 || row >= numRows) return false;

        return myBoard[row][column].isAvailable();
    }

    protected int getFirstEmptyRow(int column) {
        int row = numRows - 1;

        while (row >= 0 && !myBoard[row][column].isAvailable()) {
            row--;
        }

        return row;
    }

    public void makeMove(Move move, Player player) {
        if (legalMove(move)) {  // Aquí solo se necesita el objeto Move
            int row = move.getRow();
            int column = move.getColumn();
            myBoard[row][column] = player.getPiece();

            // Llama al método flipPieces para voltear las piezas del oponente
            flipPieces(move, player);
        }
    }


    public void flipPieces(Move move, Player player) {
        int column = move.getColumn();
        int row = move.getRow();
        Piece curPiece = player.getPiece();
        Piece oppPiece = curPiece.oppositePiece();
        boolean hasFlipped = false;

        // Verificar en las 8 direcciones posibles
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (dr == 0 && dc == 0) continue;

                int r = row + dr;
                int c = column + dc;
                boolean foundOpponentPiece = false;

                while (r >= 0 && r < numRows && c >= 0 && c < numCols) {
                    if (myBoard[r][c] != null && oppPiece != null && myBoard[r][c].equals(oppPiece)) {
                        System.out.println("An opponent's piece was found at myBoard[" + r + "][" + c + "]");
                        // Actions when myBoard[r][c] is equal to oppPiece

                    } else if (myBoard[r][c] != null && curPiece != null && myBoard[r][c].equals(curPiece)) {
                        if (foundOpponentPiece) {
                            // Realizar el flip de las piezas
                            int flipR = row + dr;
                            int flipC = column + dc;

                            while (flipR != r || flipC != c) {
                                myBoard[flipR][flipC] = curPiece;
                                flipR += dr;
                                flipC += dc;
                                hasFlipped = true;
                            }
                        }
                        break;
                    } else {
                        break;
                    }
                    r += dr;
                    c += dc;
                }
            }
        }

        // Si se ha realizado algún flip, actualizar el tablero
        if (hasFlipped) {
            myBoard[row][column] = curPiece;
        }
    }


    public boolean winner(Player player) {
        Piece curPiece = player.getPiece();
        int row, column;

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
                            myBoard[row + 2][column + 2] == curPiece &&
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

        for (row = 0; row < numRows; row++) {
            for (column = 0; column < numCols; column++) {
                myBoard[row][column] = new ReversiPiece(Piece.BLANK);
            }
        }

        
        int mid = numRows / 2;
        myBoard[mid - 1][mid - 1] = new ReversiPiece(ReversiPiece.WHITE);
        myBoard[mid - 1][mid] = new ReversiPiece(ReversiPiece.BLACK);
        myBoard[mid][mid - 1] = new ReversiPiece(ReversiPiece.BLACK);
        myBoard[mid][mid] = new ReversiPiece(ReversiPiece.WHITE);
    }

}
