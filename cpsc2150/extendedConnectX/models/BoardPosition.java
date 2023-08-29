package cpsc2150.extendedConnectX.models;

/**
 * @invariants 0 <= column < 7 AND 0 <= row < 6
 */
public class BoardPosition
{
    private final int row;
    private final int column;

    /**
     * Constructor that creates BoardPosition to hold row and column positions
     *
     * @param r holds column number
     * @param c holds row number
     *
     * @pre row = 0 AND column = 0
     * @post row = r AND column = c
     */
    public BoardPosition(int r, int c)
    {
        row = r;
        column = c;
    }

    /**
     * returns the row position
     *
     * @return number of the row
     * @pre row >= 0
     */
    public int getRow()
        { return row; }

    /**
     * returns the column position
     *
     * @return number of the column
     * @pre column >= 0
     */
    public int getColumn()
        { return column; }

    /**
     * overrides default equals method to return a boolean comparing BoardPosition
     *
     * @param object instance of the Object class
     * @return true or false
     *
     * @post returns true if two BoardPositions are equal, otherwise false
     */
    public boolean equals(Object object)
    {
     // checks if both objects are not equal
        if (object.getClass() != getClass())
            { return false; }
        BoardPosition pos = (BoardPosition) object;
     // returns boolean value of the conditional
        return (pos.getRow() == getRow() && pos.getColumn() == getColumn());
    }

    /**
     * ovverrides toString() method
     *
     * @return overridden toString() <row>,<column>
     *
     * @post string must equal the following format: "<row>,<column>"
     */
    public String toString()
        { return getRow() + "," + getColumn(); }
}
