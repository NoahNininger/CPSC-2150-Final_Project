package cpsc2150.extendedConnectX.models;

/**
 * Initialization ensures that an instance of GameBoard will be created with the dimensions chosen
 */
public interface IGameBoard
{
    public static final int MAX_SIZE = 100;
    public static final int MIN_SIZE = 3;
    public static final int MAX_NUM_TO_WIN = 25;
    public static final int MIN_NUM_TO_WIN = 3;
    public static final int MAX_PLAYERS = 10;
    public static final int MIN_PLAYERS = 2;
    
    char[][] board = new char[0][0];

    /**
     * returns true if column c can accept another token
     *
     * @param c column number
     * @return true or false depending on column capacity
     *
     * @pre c >= MIN_SIZE AND c < MAX_SIZE
     * @post true if column can accept another token, otherwise false
     */
    default boolean checkIfFree(int c)
    {
        for(int r = 0; r < getNumRows(); r++ )  // loops through rows
        {
            BoardPosition pos = new BoardPosition(r,c);
            if(whatsAtPos(pos) == ' ')
                { return true; }
        }
        return false;
    }

    /**
     * returns true if last token placed in column c results in a win
     *
     * @param c column number
     * @return true or false depending on if last token results in a win
     *
     * @pre c >= MIN_SIZE AND c < MAX_SIZE
     * @post true if game is won
     */
    default boolean checkForWin(int c)
    {
        for(int r = 0; r < getNumRows(); r++)       // loops thorugh rows
        {
            BoardPosition pos = new BoardPosition(r,c);
            if(whatsAtPos(pos) != ' ')
            {
                // checks for win
                if(checkHorizWin(pos, whatsAtPos(pos)) == true ||
                        checkVertWin(pos, whatsAtPos(pos)) == true ||
                        checkDiagWin(pos, whatsAtPos(pos)) == true)
                    { return true; }
            }
        }

        return false;   // no win
    }

    /**
     * places token in lowest available row in column c
     *
     * @param p player token character
     * @param c column number
     *
     * @pre c >= 0 AND p is a char value
     * @post token is placed in lowest available row
     */
    public void placeToken(char p, int c);

    /**
     * checks for a horizontal win
     *
     * @param BoardPosition holds [row][column] position on the board
     * @param p player token character
     * @return true if player has 5 tokens in a row horizontally, otherwise false
     *
     * @pre p is a char AND BoardPosition is a valid position
     * @post true id a player wins, otherwise false
     */
    default boolean checkHorizWin(BoardPosition pos, char p)
    {
        int row = pos.getRow();
        int col = pos.getColumn();

        int leftMostToken = Math.max(0, col - (getNumToWin() - 1));
        int rightMostToken = Math.min(getNumColumns() - 1, col + (getNumToWin() - 1));

        boolean pass = true;
        int i, j;

        for(i = leftMostToken; i + getNumToWin() - 1 <= rightMostToken; i++)    // loops thorugh columns
        {
            for(j = 0; j < getNumToWin(); j++)
            {
                BoardPosition pos2 = new BoardPosition(row, i + j);
                if (whatsAtPos(pos2) != p)  // if position is equal to given token
                    { pass = false; }
            }
            if(pass)
                { return true; }
            pass = true;
        }

        return false;   // no win
    }

    /**
     * checks for vertical win
     *
     * @param BoardPosition holds [row][column] position on the board
     * @param p player token character
     * @return true if player has 5 tokens in a row vertically, otherwise false
     * @pre p is a char AND BoardPosition is a valid position
     * @post true if a player wins, otherwise false
     */
    default boolean checkVertWin(BoardPosition pos, char p)
    {
        int row = pos.getRow();
        int col = pos.getColumn();

        if(row - (getNumToWin() - 1) > -1)
        {
            for(int r = 0; r < getNumToWin(); r++)      // loops through rows
            {
                BoardPosition pos2 = new BoardPosition(row - r,col);
                if(whatsAtPos(pos2) != p)       // if position is eqal to the given token
                    { return false; }
            }
            return true;
        }

        return false;   // no win
    }

    /**
     * checks for a diagonal win
     *
     * @param BoardPosition holds [row][column] position on the board
     * @param p player token character
     * @return true if player has 4 tokens in a row diagonally, otherwise false
     *
     * @pre p is a char AND BoardPosition is a valid position
     * @post true if a player wins, otherwise false
     */
    public default boolean checkDiagWin(BoardPosition pos, char p)
    {
        int count = 1;
        int count2 = 1;
        int row = pos.getRow();
        int col = pos.getColumn();
        BoardPosition temp = null;

        // checks up and to the right
        count--;

        while(true)
        {
            temp = new BoardPosition(row, col);
            Character temp2 = Character.valueOf(whatsAtPos(temp));

            if(temp2.equals(p))
            {
                count++;

                // ensures row does not go out of bounds
                if((row + 1) < getNumRows())
                    { row++; }
                else { break; }

                // ensures col does not go out of bounds
                if((col + 1) < getNumColumns())
                    { col++; }
                else { break; }
            }
            else { break; }
        }

        row = pos.getRow();
        col = pos.getColumn();

        // checks up and to the left
        count2--;

        while(true)
        {
            temp = new BoardPosition(row, col);
            Character temp2 = Character.valueOf(whatsAtPos(temp));

            if(temp2.equals(p))
            {
                count2++;

                // ensures that row does not go out of bounds
                if((row + 1) < getNumRows())
                    { row++; }
                else { break; }

                // ensures that col does not go out of bounds
                if((col - 1) >= 0)
                    { col--; }
                else { break; }
            }
            else { break; }
        }

        row = pos.getRow();
        col = pos.getColumn();

        //checks down and to the left
        count--;

        while(true)
        {
            temp = new BoardPosition(row, col);
            Character temp2 = Character.valueOf(whatsAtPos(temp));

            if(temp2.equals(p)) {

                count++;

                // ensures that row does not go out of bounds
                if((row - 1) >= 0)
                    { row--; }
                else { break; }

                // ensures that col does not go out of bounds
                if((col - 1) >= 0)
                { col--; }
                else { break; }
            }
            else { break; }
        }
        row = pos.getRow();
        col = pos.getColumn();

        //checks down and to the right
        count2--;

        while(true)
        {
            temp = new BoardPosition(row, col);
            Character temp2 = Character.valueOf(whatsAtPos(temp));

            if(temp2.equals(p)) {

                count2++;

                // esnures that row does not go out of bounds
                if((row - 1) >= 0)
                    { row--; }
                else { break; }

                // ensures that col does not go out of bounds
                if((col + 1) < getNumColumns())
                    { col++; }
                else { break; }
            }
            else { break; }
        }
        row = pos.getRow();
        col = pos.getColumn();

        // if diagnoal win
        if((count >= getNumToWin()) || (count2 >= getNumToWin()))
            { return true; }
        // no win
        else { return false; }
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
    public char whatsAtPos(BoardPosition pos);

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
    default boolean isPlayerAtPos(BoardPosition pos, char player)
        { return board[pos.getRow()][pos.getColumn()] == player; }

    /**
     * returns true if the game board results in a tie game
     *
     * @return true or false depending on if there is no winner
     *
     * @pre checkIfFree(int c) on columns chosen_row_num-chosen_col_num must return false
     * @post tied game if true
     */
    default boolean checkTie()
    {
        for(int i = 0; i < getNumColumns(); i++ )
        {
            BoardPosition pos = new BoardPosition(getNumRows() - 1, i);
            if(whatsAtPos(pos) == ' ')
                { return false; }           // no tie
        }

        return true;        // tied game
    }

    /**
     * returns the number of rows
     *
     * @return getNumRows() = NUM_ROWS;
     */
    public int getNumRows();

    /**
     * returns the number of columns
     *
     * @return getNumColumns() = NUM_COLS;
     */
    public int getNumColumns();

    /**
     * returns the number in a row needed to win
     *
     * @return getNumToWin() = NUM_TO_WIN;
     */
    public int getNumToWin();
}
