/*******************************************
/** Author:  Manel Casado
/*  Course:  CSC 221, Fall 2023
/*  Lab:     Lab 4: GUI Lab, TicTacToe
/*  Class:   GameDrive
*******************************************/

public class GameDrive {
    public static void main(String[] args) {
        // Create the game ask for players
        TicTacToeGame game;
        game = new TicTacToeGame();

        // Create the GUI display and pass the game to it
        TTTGUIDisplay gui;
        gui = new TTTGUIDisplay();

        // Initialize the game and GUI
        gui.drawBoard();
    }
}