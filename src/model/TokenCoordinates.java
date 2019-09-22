package model;

/**
 * A model entity that represents a duple (i, j) in the puzzle board to locate board tokens.
 */
public class TokenCoordinates {

    // ----------------------------------------------------------------------------------------------------------------
    // Constants
    // ----------------------------------------------------------------------------------------------------------------

    /**
     * Represents the row in the board where is placed the token.
     */
    private int row;

    /**
     * Represents the column in the board where is placed the token.
     */
    private int column;

    // ----------------------------------------------------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------------------------------------------------

    public TokenCoordinates(int row, int column) {
        this.row = row;
        this.column = column;
    }

    // ----------------------------------------------------------------------------------------------------------------
    // Services
    // ----------------------------------------------------------------------------------------------------------------

    /**
     * Gets the row where is placed the token on the board.
     * @return an integer which represents the row.
     */
    public int getRow() {
        return row;
    }

    /**
     * Gets the column where is placed the token on the board.
     * @return an integer which represents the column.
     */
    public int getColumn() {
        return column;
    }
}
