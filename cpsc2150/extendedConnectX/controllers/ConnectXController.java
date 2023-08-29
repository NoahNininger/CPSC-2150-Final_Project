package cpsc2150.extendedConnectX.controllers;

import cpsc2150.extendedConnectX.models.*;
import cpsc2150.extendedConnectX.views.*;

/**
 * The controller class will handle communication between our View and our Model ({@link IGameBoard})
 * <p>
 * This is where you will write code
 * <p>
 * You will need to include your your {@link BoardPosition} class, {@link IGameBoard} interface
 * and both of the {@link IGameBoard} implementations from Project 4.
 * If your code was correct you will not need to make any changes to your {@link IGameBoard} implementation class.
 *
 * @version 2.0
 */
public class ConnectXController {

    /**
     * <p>
     * The current game that is being played
     * </p>
     */
    private IGameBoard curGame;

    /**
     * <p>
     * The screen that provides our view
     * </p>
     */
    private ConnectXView screen;

    /**
     * <p>
     * Constant for the maximum number of players.
     * </p>
     */
    public static final int MAX_PLAYERS = 10;
    
    /**
     * <p>
     * The number of players for this game. Note that our player tokens are hard coded.
     * </p>
     */
    private int numPlayers;

    /**
     * <p>
     * The player tokens that can be used for this game. 
     * Note that our player tokens are hard coded, as stated above.
     * </p>
     */
    private char[] tokens = {'X', 'O', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};

    /**
     * <p>
     * The current player token for the current turn of the game
     * </p>
     */
    private int currentPlayer;

    /**
     * <p>
     * This creates a controller for running the Extended ConnectX game
     * </p>
     * 
     * @param model
     *      The board implementation
     * @param view
     *      The screen that is shown
     * @param np
     *      The number of players for this game.
     * 
     * @post [ the controller will respond to actions on the view using the model. ]
     */
    public ConnectXController(IGameBoard model, ConnectXView view, int np) {
        this.curGame = model;
        this.screen = view;
        this.numPlayers = np;

        // Some code is needed here.
    }

    /**
     * <p>
     * This processes a button click from the view.
     * </p>
     * 
     * @param col 
     *      The column of the activated button
     * 
     * @post [ will allow the player to place a token in the column if it is not full, otherwise it will display an error
     * and allow them to pick again. Will check for a win as well. If a player wins it will allow for them to play another
     * game hitting any button ]
     */
    public void processButtonClick(int col) {
        // Your code goes here.

     // checks for finished game
        if(curGame.checkTie() || curGame.checkForWin(col))
            { newGame(); }

     // prompts current player
        screen.setMessage("It is " + tokens[(currentPlayer+1)%numPlayers] 
                                    + "'s turn.");
        if (!curGame.checkIfFree(col))
            { screen.setMessage("Column is Full."); }

     // placing tokens
        else if (curGame.checkIfFree(col) && !curGame.checkForWin(col) )
        {
         // finds correct row
            int r = -1;
            for (int i = 0; i < curGame.getNumRows() && r == -1; i++)
            {
                BoardPosition pos = new BoardPosition(i, col);
                if(curGame.whatsAtPos(pos) == ' ')
                    { r = i; }
            }

         // places token on model and view
            curGame.placeToken(tokens[currentPlayer%numPlayers], col);
            screen.setMarker(r, col, tokens[currentPlayer%numPlayers]);
            currentPlayer++;
        }

     // screen prompt for tie
        if(curGame.checkTie())
            { screen.setMessage("You Tied. Click again to start a new game."); }

     // screen prompt for win
        if(curGame.checkForWin(col))
        {
            currentPlayer--;
            screen.setMessage("Player " + tokens[currentPlayer%numPlayers] 
                              + " Wins! Click again to start a new game");
        }
    }

    /**
     * <p>
     * This method will start a new game by returning to the setup screen and controller
     * </p>
     * 
     * @post [ a new game gets started ]
     */
    private void newGame() {
        //close the current screen
        screen.dispose();
        
        //start back at the set up menu
        SetupView screen = new SetupView();
        SetupController controller = new SetupController(screen);
        screen.registerObserver(controller);
    }
}
