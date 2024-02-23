/*******************************************
/** Author:  Manel Casado
/*  Course:  CSC 221, Fall 2023
/*  Lab:     Lab 5: Inheritance Lab: Expand Tic Tac Toe via inheritance
/*  Created: October 2023
/*  Class:   Connect4Game, extends Game
*******************************************/
public class Connect4Game extends Game {
    public Connect4Game(InputOutput ioDevice) {
        super(CONNECT4, ioDevice);
    }

    protected Move getMove(InputOutput ioDevice, Player player) {
        int row, column;
        Move move = null;
        ioDevice.print("It is "+player.getName()+"'s turn, choose a column number: ");
        column = ioDevice.nextInt();

        if (column < board.numCols) {
            row = board.getFirstEmptyRow(column);
            move = new Move(row, column);
            ioDevice.flushLine();
        }
        return move;
    }
}