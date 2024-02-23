/*******************************************
/** Author:  Manel Casado
/*  Course:  CSC 221, Fall 2023
/*  Lab:     Lab 5: Inheritance Lab: Expand Tic Tac Toe via inheritance
/*  Created: October 2023
/*  Class:   TicTacToeGame
*******************************************/
public class TicTacToeGame extends Game {
    public TicTacToeGame(InputOutput ioDevice) {
        super(TIC_TAC_TOE, ioDevice);
    }
    
    protected Move getMove(InputOutput ioDevice, Player player) {
        Move move;
        ioDevice.print("It is "+player.getName()+"'s move: ");
        move = new Move(ioDevice.nextInt(),ioDevice.nextInt());
        ioDevice.flushLine();
        return move;
    }
}