import java.util.*;
/*******************************************
/** Author:  Manel Casado
/*  Course:  CSC 221, Fall 2023
/*  Lab:     Lab 3: Objects Lab, UTicTacToe
/*  Class:   Board
*******************************************/

public class Board {
    private char [][] board;
    private int size;
    
    public Board() {
        size = 3;   // Default board is 3x3
        board = new char[size][size];
    }
    
    public void Board(int size) {
        this.size = size;
        board = new char[size][size];
        // Initialize the board with empty spaces or any default value if needed
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                board[row][col] = ' ';
            }
        }
    }
    
    public int getSize() {
        return size;
    }
    
    public boolean isLegal(Move move) {
        int row = move.getRow();
        int col = move.getColumn();

        if (row < 0 || row >= size || col < 0 || col >= size) {
            System.out.println("Invalid move. Row and column values must be between 0 and 2.");
            return false;
        }

        if (board[row][col] != 0) {
            System.out.println("Invalid move. The selected cell is already occupied.");
            return false;
        }
        return true;
    }
    
    public void makeMove(Move move, Player player) {
        int row = move.getRow();
        int col = move.getColumn();

        if (isLegal(move)) {
            board[row][col] = player.getSymbol();
        }
    }
    
    public Player gameOver(Player oPlayer, Player xPlayer) {
        char[] symbols = {'X', 'O'};

        for (char symbol : symbols) {
            // Vertical and Horizontal
            for (int i = 0; i < size; i++) {
                if ((board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) ||
                    (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol)) {
                    if (symbol == 'X') {
                        return xPlayer;
                    } else {
                        return oPlayer;
                    }
                }
            }
            
            // Diagonals
            if ((board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) ||
                (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol)) {
                if (symbol == 'X') {
                    return xPlayer;
                } else {
                    return oPlayer;
                }
            }
        }
    
        // Check for a draw if all cells are full
        boolean isDraw = true;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (board[row][col] == ' ') {
                    isDraw = false;
                    break;
                }
            }
        }

        if (isDraw) {
            System.out.println("It's a draw!");
        }
        return null;
    }
    
    public String toString() {
        String str;
        str = "";
    
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                str += board[row][col];
                if (col < size - 1) {
                    str += " | ";
                }
            }
            str += "\n";
            if (row < size - 1) {
                str += "--+---+--\n";
            }
        }
    
        return str;
    }
    
}