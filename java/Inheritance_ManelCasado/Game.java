/*******************************************
/** Author:  Dr. Cathy Bareiss
/*  Editors: Manel Casado, Enmanuel David
/*  Course:  CSC 221, Fall 2023
/*  Lab:     Lab 5: Inheritance Lab: Expand Tic Tac Toe via inheritance
/*  Created: October 2023
/*  Class:   Game
*******************************************/
public abstract class Game {
    public static final String TIC_TAC_TOE = "Tic Tac Toe";
    public static final String CONNECT4 = "Connect 4";
    public static final String REVERSI = "Revesi";
    protected Board board;
    protected Piece piece;
    private Player xPlayer, oPlayer;
    private InputOutput ioDevice;
    private String name;
   
    public Game(String gameType, InputOutput ioDevice) {
        Piece piecePlayer1 = null;
        Piece piecePlayer2 = null;
       
        this.ioDevice = ioDevice;
        name = gameType;
        if (gameType.equals(TIC_TAC_TOE)) {
            board = new TicTacToeBoard();
            piece = new TicTacToePiece();
            piecePlayer1 = new TicTacToePiece(Piece.symbol1);
            piecePlayer2 = new TicTacToePiece(Piece.symbol2);
        }
        else if (gameType.equals(CONNECT4)) {
            board = new Connect4Board();
            piece = new Connect4Piece();
            piecePlayer1 = new Connect4Piece(Piece.symbol1);
            piecePlayer2 = new Connect4Piece(Piece.symbol2);
        }
        else if (gameType.equals(REVERSI)) {
            board = new ReversiBoard();
            piece = new ReversiPiece();
            piecePlayer1 = new ReversiPiece(Piece.symbol1);
            piecePlayer2 = new ReversiPiece(Piece.symbol2);
        }

      ioDevice.print("What is the name of Player 1? ");
      xPlayer = new Player(ioDevice.nextString(), piecePlayer1);
      ioDevice.flushLine();
      ioDevice.print("What is the name of Player 2? ");
      oPlayer = new Player(ioDevice.nextString(), piecePlayer2);
      ioDevice.flushLine();
    }

    public void playGame() {
        Player currentPlayer;
        Move move;
       
        // Show welcome message
        System.out.print("Welcome to ");
        System.out.print(name);
        System.out.println("!");
       
        currentPlayer = xPlayer;
        while (!gameOver()) {
            displayBoard();
            move = getMove(ioDevice, currentPlayer);
            while (!board.legalMove(move)) {
                ioDevice.println("Illegal move");
                move = getMove(ioDevice, currentPlayer);
            }
            board.makeMove(move, currentPlayer);
            if (currentPlayer == xPlayer) currentPlayer = oPlayer;
            else currentPlayer = xPlayer;
        }
       
        System.out.println(board.toString());
        if (board.winner(xPlayer)) {
            ioDevice.println(xPlayer.getName()+" is the winner.");
        }
        else {
            ioDevice.println(oPlayer.getName()+" is the winner.");
        }
    }

    protected abstract Move getMove(InputOutput ioDevice, Player player);
   
    private boolean gameOver() {
        if (board.winner(xPlayer) || board.winner(oPlayer)) return true;
        return false;
    }
   
    private void displayBoard() {
        ioDevice.print(board.toString());
    }
}