/*******************************************
/** Author:  Enmanuel David
/*  Course:  CSC 221, Fall 2023
/*  Lab:     Lab 5: Inheritance Lab: Expand Tic Tac Toe via inheritance
/*  Created: October 2023
/*  Class:   Reversi4Game, extends Game
*******************************************/
public class ReversiGame extends Game {
    public ReversiGame(InputOutput ioDevice) {
        super(REVERSI, ioDevice);
    }

    protected Move getMove(InputOutput ioDevice, Player player) {
        int row, column;
        Move move;
        ioDevice.print("It is " + player.getName() + "'s turn, choose a move (row, column): ");
        move = new Move(ioDevice.nextInt(),ioDevice.nextInt());
        ioDevice.flushLine();
        //String input = ioDevice.nextString();      
        return move;
    }

}
