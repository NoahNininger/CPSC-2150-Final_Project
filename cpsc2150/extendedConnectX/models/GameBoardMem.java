package cpsc2150.extendedConnectX.models;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 * Allows for a more memory efficient implementation; however it can be slower
 * 
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
public class GameBoardMem extends AbsGameBoard implements IGameBoard
{
 // private member variables
    private int NUM_ROWS;
    private int NUM_COLS;
    private int NUM_TO_WIN;

    private Map<Character, List<BoardPosition>> map;

    /*
     * @post new GameBoard is initialized AND
     *       GameBoard is initially filled with empty spaces
     * 
     * @invariants both height and width are within the bounds of 3-100
     */
    public GameBoardMem(int r, int c, int w)
    {
     // initializing private members
        NUM_ROWS = r;
        NUM_COLS = c;
        NUM_TO_WIN = w;
        map = new HashMap<Character, List<BoardPosition>>();
    }

    /**
     * places token in lowest available row in column c
     *
     * @param p player token character
     * @param c column number
     *
     * @pre c >= 0 AND p is a char value
     * @post position of token is stored in the map using key 'p'
     */
    public void placeToken(char p, int c)
    {
        map.putIfAbsent(p, new ArrayList<>());
        BoardPosition pos = new BoardPosition(0, c);

        for(int i = 0; whatsAtPos(pos) != ' ' ; i++)
            { pos = new BoardPosition(i, c); }

        map.get(p).add(pos);
    }

    /**
     * returns what is at the given position on the board
     *
     * @param BoardPosition holds [row][column] position on the board
     * @return char in given position
     *
     * @pre BoardPosition is a valid position
     * @post char in the position is known
     */
    public char whatsAtPos(BoardPosition pos)
    {
        for(Map.Entry<Character, List<BoardPosition>> c : map.entrySet())
        {
            if(c.getValue().contains(pos))
                { return c.getKey(); }
        }

        return ' ';
    }

    /**
     * returns true if player token is on a specific position
     *
     * @param BoardPosition holds [row][column] position on the board
     * @param player char of player token
     * @return true if player char is at position, otherwise false
     *
     * @pre BoardPosition is a valid position AND player is a char
     * @post true if given char and given position match
     */
    @Override
    public boolean isPlayerAtPos(BoardPosition pos, char player)
    {

        if(!map.containsKey(player))
            { return false; }

        for(Map.Entry<Character, List<BoardPosition>> c : map.entrySet() )
        {
            if(c.getKey().equals(player) && c.getValue().contains(pos))
                { return true; }
        }

        return false;
    }

    /**
     * returns the number of rows
     *
     * @return getNumRows() = NUM_ROWS;
     */
    public int getNumRows()
        { return NUM_ROWS; }

    /**
     * returns the number of columns
     *
     * @return getNumColumns() = NUM_COLS;
     */
    public int getNumColumns()
        { return NUM_COLS; }

    /**
     * returns the number in a row needed to win
     *
     * @return getNumToWin() = NUM_TO_WIN;
     */
    public int getNumToWin()
        { return NUM_TO_WIN; }
}
