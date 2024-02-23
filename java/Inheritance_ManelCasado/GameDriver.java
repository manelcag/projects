/*******************************************
/** Authors:  Manel Casado, Enmanuel David
/*  Course:  CSC 221, Fall 2023
/*  Lab:     Lab 5: Inheritance Lab: Expand Tic Tac Toe via inheritance
/*  Created: October 2023
/*  Class:   GameDriver
*******************************************/
public class GameDriver {

    public static void main(String[] args) {
        InputOutput ioDevice;
        int gameNumber;
           Game game = null;

        ioDevice = new InputOutput();
       
        do {
            System.out.println("1. Tic Tac Toe");
            System.out.println("2. Connect 4");
            System.out.println("3. Reversi");
            System.out.println("Select a game (1, 2, or 3):");
           
            gameNumber = ioDevice.nextInt();
            ioDevice.flushLine();

        } while (gameNumber < 1 || gameNumber > 3);
       
        switch (gameNumber) {
        case 1:
            game = new TicTacToeGame(ioDevice);
            break;
        case 2:
            game = new Connect4Game(ioDevice);
            break;
        case 3:
            game = new ReversiGame(ioDevice);
            break;
        }

        game.playGame();
    }
}
