/*******************************************
/** Author:  Dr. Bareiss
/*  Editor:  Manel Casado
/*  Course:  CSC 221, Fall 2023
/*  Lab:     Lab 4: GUI Lab, TicTacToe
/*  Class:   TicTacToeGame
*******************************************/

public class TicTacToeGame {
    public Board tttBoard;
    public Player xPlayer, oPlayer;
    private InputOutput ioDevice;
    public Player currentPlayer;
    
    public TicTacToeGame() {
        ioDevice = new InputOutput();
        tttBoard = new Board();
        ioDevice.print("What is the name of the X player? ");
        xPlayer = new Player(ioDevice.nextString(),new Piece(Piece.X));
        ioDevice.flushLine();
        ioDevice.print("What is the name of the O player? ");
        oPlayer = new Player(ioDevice.nextString(),new Piece(Piece.O));
        ioDevice.flushLine();
    }
    
    public TicTacToeGame(int size) {
        ioDevice = new InputOutput();
        tttBoard = new Board(size);
        ioDevice.print("What is the name of the X player? ");
        xPlayer = new Player(ioDevice.nextString(),new Piece(Piece.X));
        ioDevice.flushLine();
        ioDevice.print("What is the name of the O player? ");
        oPlayer = new Player(ioDevice.nextString(),new Piece(Piece.O));
        ioDevice.flushLine();
    }

    public TicTacToeGame(String filename) {
        int size;
        String str;
        ioDevice = new InputOutput(filename);
        size = ioDevice.nextInt();
        tttBoard = new Board(size);
        ioDevice.flushLine();
        xPlayer = new Player(ioDevice.nextString(),new Piece(Piece.X));
        ioDevice.flushLine();
        oPlayer = new Player(ioDevice.nextString(),new Piece(Piece.O));
        ioDevice.flushLine();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    
    public void playGame() {
        Player currentPlayer;
        Move move;
        
        currentPlayer = xPlayer;
        while (!gameOver()) {
            displayBoard();
            move = getMove(currentPlayer);
            while (!tttBoard.legalMove(move)) {
                ioDevice.println("Illegal move");
                move = getMove(currentPlayer);
            }
            tttBoard.makeMove(move, currentPlayer);
            if (currentPlayer == xPlayer) currentPlayer = oPlayer;
            else currentPlayer = xPlayer;
        }
        
        if (tttBoard.winner(xPlayer)) 
            ioDevice.println(xPlayer.getName()+" is the winner.");
        else ioDevice.println(oPlayer.getName()+" is the winner.");
    }
    
    private Move getMove(Player player) {
        Move move;
        ioDevice.print("It is "+player.getName()+"'s move: ");
        move = new Move(ioDevice.nextInt(),ioDevice.nextInt());
        ioDevice.flushLine();
        return move;
    }
    
    public boolean gameOver() {
        if (tttBoard.winner(xPlayer) || tttBoard.winner(oPlayer)) return true;
        return false;
    }
    
    private void displayBoard() {
        ioDevice.print(tttBoard.toString());
    }
}