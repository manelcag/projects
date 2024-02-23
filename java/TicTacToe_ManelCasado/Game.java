import java.util.Scanner;
/*******************************************
/** Author:  Manel Casado
/*  Course:  CSC 221, Fall 2023
/*  Lab:     Lab 3: Objects Lab, UTicTacToe
/*  Class:   Game
*******************************************/

public class Game {
    private Board tttBoard;
    private Player xPlayer, oPlayer, currPlayer;
    private String xName,oName;
    private int size;
    private IODriver iosystem;
    
    public Game(String xName, String oName) {
        this.xName = xName;
        this.oName = oName;
    }
    
    public Game(int size, String xName, String oName) {
        this.xName = xName;
        this.oName = oName;
        this.tttBoard = new Board();
        this.xPlayer = new Player(xName, 'X');
        this.oPlayer = new Player(oName, 'O');
        this.currPlayer = this.xPlayer;
        this.iosystem = new IODriver(new Scanner(System.in));
    }
    
    public Game(String filename) {
    }
    
    public void play() {
        String playerName;
        Scanner keyboard = new Scanner(System.in);
        this.iosystem = new IODriver(keyboard);
        
        playerName = this.iosystem.getString("X Player Name:");
        xPlayer = new Player(playerName, 'X');
        playerName = this.iosystem.getString("O Player Name:");
        oPlayer = new Player(playerName, 'O');
        
        tttBoard = new Board(); // Default 3x3 board
    }
    
    public void takeTurn(Player curPlayer) {
        // Display the current board
        System.out.println(tttBoard.toString());

        // Get the current player's move
        Move move = getMove(curPlayer);

        // Make the move on the board
        tttBoard.makeMove(move, curPlayer);

        // Check if the game is over
        Player winner = tttBoard.gameOver(xPlayer, oPlayer);
        if (winner != null) {
            System.out.println("Player " + winner.getName() + " wins!");
            endGame();
        }
    }
    
    private Move getMove(Player curPlayer) {
        while (true) {
            int row = iosystem.getInt(curPlayer.getName() + ", enter row (0-" + (tttBoard.getSize() - 1) + "): ");
            int col = iosystem.getInt(curPlayer.getName() + ", enter column (0-" + (tttBoard.getSize() - 1) + "): ");
            Move move = new Move(row, col);

            if (tttBoard.isLegal(move)) {
                return move;
            } else {
                System.out.println("Invalid move. Please try again.");
            }
        }
    }
    
    public Player getCurrPlayer() {
        return currPlayer;
    }

    public Player getWinner() {
        return tttBoard.gameOver(xPlayer, oPlayer);
    }
    
    private void endGame() {
    }
    
    // This method was not included in UML
    public void nextPlayer() {
    	if (currPlayer.equals(xPlayer)) {
    		currPlayer = oPlayer;
    	} else {
    		currPlayer = xPlayer;
    	}
    }
    
}