package cpsc2150.extendedConnectX.models;

import java.util.Arrays;

/**
 * @invariants MIN_SIZE <= width <= MAX_SIZE AND
 *             MIN_SIZE <= height <= MAX_SIZE AND
 *             MIN_NUM_TO_WIN < numToWin < MAX_NUM_TO_WIN AND
 *             numToWin < [width and height] AND
 *             Empty space is ' '
 *
 * @correspondences number of rows = height of the board AND
 *                  number of columns = width of the board AND
 *                  # in a row to win = how many in a row to win
 */
public class GameBoard extends AbsGameBoard implements IGameBoard
{
    private int NUM_ROWS;
    private int NUM_COLS;
    private int NUM_TO_WIN;

    private char[][] board;     // array to represent the game board

    /**
     * constructor for GameBoard
     * 
     * @post initialized board and creates game board
     * 
     * @invariants both height and width are within the bounds of 3-100
     */
    public GameBoard(int row, int col, int numWin)
    {
        NUM_ROWS = row;
        NUM_COLS = col;
        NUM_TO_WIN = numWin;

        board = new char[row][col];

        for(char[] newBoard : board)
            { Arrays.fill(newBoard , ' '); }
    }

    public void placeToken(char p, int c)
    {
        for(int r = 0; r < NUM_ROWS; r++)  // loops through rows
        {
            if(board[r][c] == ' ' ) // places token if space is empty
            {
                board[r][c] = p;
                return;
            }
        }
    }

    public char whatsAtPos(BoardPosition pos)
    {
        char playerAtPos;
        if( board[pos.getRow()][pos.getColumn()] != ' ')
        {
            playerAtPos = board[pos.getRow()][pos.getColumn()];     // if X or O
            return playerAtPos;
        }
        else { return ' '; }    // if blank
    }

    public int getNumRows()
        { return NUM_ROWS; }

    public int getNumColumns()
        { return NUM_COLS; }
    
    public int getNumToWin()
        { return NUM_TO_WIN; }
}
