// imports for GUI programs
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/*******************************************
/** Author:  Dr. Bareiss
/*  Editor:  Manel Casado
/*  Course:  CSC 221, Fall 2023
/*  Lab:     Lab 4: GUI Lab, TicTacToe
/*  Class:   TTTGUIDisplay, GameActionListener
*******************************************/

public class TTTGUIDisplay {
    private TicTacToeGame game;
    public Player currentPlayer;    

	// literals used in the buttons
    /** move button literal */
    final String MOVESTRING="Make Move";
    /** quit button literal */
    final String QUITSTRING="Quit Game";
    /** save button literal */
    final String SAVESTRING="Save Game";
    /** start button literal */
    final String STARTSTRING="Start Game";
    
    //Objects/fields used in both the display and the action listener
    /** save GUIDisplay for use in action listener */
    protected TTTGUIDisplay myDisplay;
    /** canvas for gui */
    protected Canvas myCanvas;
    /** frame for gui */
    protected JFrame myFrame;
    /** container for gui */
    protected Container myPane;
    /** first label used for game - whose turn */
    protected JLabel message1Label;
    /** second label used for messages (passing, invalid moves) during game */
    protected JLabel message2Label;
    /** first label used when the game is over */
    protected JLabel gameOverLabel1;
    /** second label used when the game is over */
    protected JLabel gameOverLabel2;
    /** field to input row (and file name when saving game) */
    protected JTextField rowField;
    /** field to input column */
    protected JTextField columnField;
    protected JTextField player1Field;
    protected JTextField player2Field;
    /** make move button */
    protected JButton moveButton;
    /** quit game button */
    //protected JButton quitButton;
    /** save game button */
    //protected JButton saveButton;
    /** start game button */
    //protected JButton startButton;
    /** listener for the buttons */
    protected GameActionListener gameListener;
    
    // menu
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem newGameMenuItem;
    private JMenuItem saveMenuItem;
    private JMenuItem loadMenuItem;
    private JMenuItem quitMenuItem;

    /** board associated with the display */
    protected Board board;
    /** radius of every circle on the board */
    private final int RADIUS = 35;
    /** width of a given square on the board */
    private final int WIDTH = 45;
    /** maximum width of board */
    private final int MAXWIDTH = 3 * WIDTH;
    /** offset to start drawing the board */
    private final int OFFSET = 40;
    
    // Class for the action listener to be used for all buttons
    /** class for handling the buttons */
    public class GameActionListener implements ActionListener { 
        // one method to get the data and perform the specified action
        /** handles all button action
          * @param event the event associated with the button pushed
          */
        public void actionPerformed(ActionEvent event) {
            int row, column;
            boolean fill;
            Color myColor;
            String inString;
            Graphics myGraph;
            String command;
            String fileName;
            Move move;
            int temp;

            command = event.getActionCommand();
            myGraph = myCanvas.getGraphics();

            if (command.equals(MOVESTRING)) { // process a move
                row = Integer.parseInt(rowField.getText());
                column = Integer.parseInt(columnField.getText());
                move = new Move(row, column);
                if (game.tttBoard.legalMove(move)) {
                    game.tttBoard.makeMove(move, currentPlayer);
                    
                    if (currentPlayer.equals(game.xPlayer)) {
                        drawX(row, column);
                        currentPlayer = game.oPlayer;
                    }else{
                        drawO(row, column);
                        currentPlayer = game.xPlayer;
                    }
                    
                    if (game.tttBoard.winner(currentPlayer)) {
                        displayMessage(currentPlayer.getName() + " wins!");
                        handleGameOver(true);
                    } else if (game.gameOver()) {
                        displayMessage("It's a draw!");
                        handleGameOver(false);
                    }
                } else {
                    displayMessage("Illegal move");
                }   
                        
           if (game.tttBoard.winner(currentPlayer)) finishGame(currentPlayer + "wins");
              
            } else if (command.equals(STARTSTRING)) {  // start the game
                drawBoard();
                currentPlayer = game.xPlayer;
                
                //startButton.setEnabled(false);
                moveButton.setEnabled(true);
                //quitButton.setEnabled(true);
                //saveButton.setEnabled(true);
                
            } else if (command.equals(SAVESTRING)) { // save the game
                displayMessage("Please enter filename in the row field");
                fileName = rowField.getText();
   //             board.saveGame(fileName);
                finishGame("Saving Game");
            } else if (command.equals(QUITSTRING)) {  // quit
                finishGame("Quitting Game");
            }
        }

        public void handleGameOver(boolean isWinner) {
            if (isWinner) {
                finishGame(currentPlayer.getName() + " wins!");
            } else {
                finishGame("It's a draw!");
            }
        }
        
        /** handle the end of a game displaying the appropriate message
          * @param message the message representing how the game ended
          */
        public void finishGame(String message) {
            Player winner;

            gameOverLabel1.setText(message);
       /*     if (message.charAt(0) == 'G') {
                winner = board.winner();
                if (winner != null)
                    gameOverLabel2.setText(winner.toString()+
                        " wins with "+board.countPieces(winner)+" pieces.");
                else
                    gameOverLabel2.setText("Game resulted in a tie");
            } */
            // game over
            moveButton.setEnabled(false);
            //saveButton.setEnabled(false);
            //quitButton.setEnabled(false);
        }
    }

//Constructor
    public TTTGUIDisplay() {
        myDisplay = this;

        setGraphics();
        myFrame.pack();        
        myFrame.setJMenuBar(menuBar);
        myFrame.setVisible(true);
    }


//Required helper methods
    /** display simple message
      * @param message string containing the message to display
      */
    public void displayMessage(String message) {
        message2Label.setText(message);
    }

    /** return the move for a player
      * @param player player to get move for
      * @return move gotten from user for specified player
      */
    public Object getMove(Player player) {
        Move move;
        int row, column;

        row = Integer.parseInt(rowField.getText());
        column = Integer.parseInt(columnField.getText());
        move = new Move(/*player, */row, column);
        displayMessage("");
        return move;
    }

    /** display message associated with a player
      * @param currentPlayer player's name to display
      * @param message string containing the message to display
      */
    public void displayStatus(Player currentPlayer, String message) {
        message1Label.setText(currentPlayer.getName().toString()+
                              ":  "+message);
    }

    /** display information about the winner of the game
      * @param winner the player that won the game
      */
    public void displayWinner(Player winner) {
        return;
    }

//Additional GUI helper methods
    // create each GUI component
    /** set up all the graphics */
    public void setGraphics() {
        JPanel drawPanel, controlPanel;
		
        // create (or determine) all the GUI objects
        myFrame = new JFrame();
        myPane = myFrame.getContentPane();
        myCanvas = new Canvas();
        controlPanel = new JPanel();
        drawPanel = new JPanel();
        moveButton = new JButton(MOVESTRING);
        //quitButton = new JButton(QUITSTRING);
        //saveButton = new JButton(SAVESTRING);
        //startButton = new JButton(STARTSTRING);
        gameListener = new GameActionListener();
        rowField = new JTextField(12);
        columnField = new JTextField(12);
        player1Field = new JTextField(12);
        player2Field = new JTextField(12);
        message1Label = new JLabel();
        message2Label = new JLabel();
        gameOverLabel1 = new JLabel();
        gameOverLabel2 = new JLabel();
        
        // create menu
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        
        
    
        newGameMenuItem = new JMenuItem(STARTSTRING);
        saveMenuItem = new JMenuItem(SAVESTRING);
        loadMenuItem = new JMenuItem("Load Game");
        quitMenuItem = new JMenuItem(QUITSTRING);

        // add the action listener to the buttons
        moveButton.addActionListener(gameListener);
        //quitButton.addActionListener(gameListener);
        //saveButton.addActionListener(gameListener);
        //startButton.addActionListener(gameListener);
        
        fileMenu.add(newGameMenuItem);
        newGameMenuItem.addActionListener(gameListener);
        fileMenu.add(saveMenuItem);
        fileMenu.add(loadMenuItem);
        fileMenu.add(quitMenuItem);

        menuBar.add(fileMenu);

        // disable and enable proper buttons;
        moveButton.setEnabled(false);
        //quitButton.setEnabled(false);
        //saveButton.setEnabled(false);
        //startButton.setEnabled(true);
        menuBar.setEnabled(true);

        // add all details to the control panel
        controlPanel.setLayout(new GridLayout(10,3));
        controlPanel.add(new JLabel("Message"));
        controlPanel.add(message1Label);
        controlPanel.add(new JLabel());
        controlPanel.add(message2Label);
        controlPanel.add(new JLabel("Row:"));
        controlPanel.add(rowField);
        controlPanel.add(new JLabel("Column:"));
        controlPanel.add(columnField);
        controlPanel.add(new JLabel("Player1:"));
        controlPanel.add(player1Field);
        controlPanel.add(new JLabel("Player2:"));
        controlPanel.add(player2Field);
        //controlPanel.add(startButton);
        controlPanel.add(moveButton);
        //controlPanel.add(quitButton);
        //controlPanel.add(saveButton);
        controlPanel.add(gameOverLabel1);
        controlPanel.add(new JLabel(""));
        controlPanel.add(gameOverLabel2);
        controlPanel.add(new JLabel(""));
        controlPanel.setBorder(BorderFactory.createLineBorder(Color.black,1));

        // add all details to the draw panel
        drawPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        drawPanel.setBorder(BorderFactory.createLineBorder(Color.black,1));
        drawPanel.add(myCanvas);
        myCanvas.setSize(460,460);

        // finalize the details associated with the frame and pane
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setTitle("Tic Tac Toe");
        myFrame.setSize(460,460);
        myFrame.setVisible(true);
        myPane.setLayout(new FlowLayout());
        drawPanel.setOpaque(true);
        myPane.add(drawPanel);
        myPane.add(controlPanel);

        drawBoard();
        //displayStatus(game.xPlayer,"It is your turn");
    }

    // redraw the entire board - drawing green circles for blanks.
    /** draw a board on the canvas of the draw panel */
    public void drawBoard() {
        int row, column, size;
        int begin, end, max;
        Graphics myGraph;

        myGraph = myCanvas.getGraphics();

        myGraph.setColor(Color.white);
        myGraph.fillRect(0+OFFSET,0+OFFSET,MAXWIDTH,MAXWIDTH);
        myGraph.setColor(Color.black);

        // draw board lines, etc.
        size = Board.DEFAULTSIZE; // 3x3
        for (row = 0; row <= size; row++) {
            begin = 0 + OFFSET;
            end = row*WIDTH + OFFSET;
            max = MAXWIDTH + OFFSET;
            myGraph.drawLine(begin,end,max,end);
            myGraph.drawLine(end,begin,end,max);
        }

        // draw pieces and label the rows and columns
        myGraph.setColor(Color.black);
        myGraph.setFont(new Font("Helvetica",Font.BOLD,20));
        for (row = 0; row < size; row++) {
            myGraph.drawString(""+row,row*WIDTH+OFFSET+15,OFFSET/2);
            myGraph.drawString(""+row,OFFSET/2,row*WIDTH+OFFSET+25);
            for (column = 0; column < size; column++)
                drawO(row,column);
        }
    }

    // draw a filled circle of a given color at a specified location
    //   on the board
    /** draw a circle of the specified color at the right place
      * @param color color to draw the filled circle
      * @param row row on board to draw the circle
      * @param column column on board to draw the cirle
      */
      
    public void drawO(int row, int column) {
        int x, y;
        Graphics myGraph;
        myGraph = myCanvas.getGraphics();

        // draw red O
        x = column*WIDTH + WIDTH/2 - 15 + OFFSET;
        y = row*WIDTH + WIDTH/2 - 15 + OFFSET;
        myGraph.setColor(Color.red);
        myGraph.drawOval(x,y,RADIUS,RADIUS);    
    }
    
    public void drawX(int row, int column) {
        int x, y;
        Graphics myGraph;
        myGraph = myCanvas.getGraphics();
        
        // draw black X
        x = column*WIDTH + WIDTH/2 - 15 + OFFSET;
        y = row*WIDTH + WIDTH/2 - 15 + OFFSET;
        myGraph.setColor(Color.black);
        myGraph.drawString("X",x,y);

    }
}
