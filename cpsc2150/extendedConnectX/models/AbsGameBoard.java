package cpsc2150.extendedConnectX.models;

public abstract class AbsGameBoard implements IGameBoard {
    /*
     * overrides toString() method
     *
     * @return a string representation of the GameBoard
     */
    @Override
    public String toString()
    {
        String board = "";

        for(int i = 0; i < getNumColumns(); i++)    // column numbers
        {
            if(i <= 9)  // singular digits
                { board += "| " + i; }
            else { board += "|" + i; }  // double digits
        }

        board += "|\n";

        for(int i = getNumRows() - 1; i >= 0; i--)
        {
            board += "|";
            for(int j = 0; j < getNumColumns(); j++)
            {
                BoardPosition pos = new BoardPosition(i, j);
                board += whatsAtPos(pos) + " |";
            }
            board += "\n";
        }

        return board;
    }
}
