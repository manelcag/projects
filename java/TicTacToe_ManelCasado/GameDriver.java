import java.util.Scanner;
/*******************************************
/** Author:  Manel Casado
/*  Course:  CSC 221, Fall 2023
/*  Lab:     Lab 3: Objects Lab, UTicTacToe
/*  Class:   GameDriver
*******************************************/

public class GameDriver {

    public static void main(String[] args) {
    /*    Game game;
        
        game = new Game(3,"Manel", "Juan");
        game.play();
    }
    */
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Tic Tac Toe!");
        System.out.print("Enter the size of the board (e.g., 3 for 3x3): ");
        int size = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter the name of Player X: ");
        String xName = scanner.nextLine();
        System.out.print("Enter the name of Player O: ");
        String oName = scanner.nextLine();

        Game game = new Game(size, xName, oName);

        // Play the game until it's over
        while (true) {
            game.takeTurn(game.getCurrPlayer());
            Player winner = game.getWinner();
            if (winner != null) {
                break;
            }
            
            // Next player
            game.nextPlayer();
        }

        scanner.close();
    }
}