package model;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Puzzle {

    // ----------------------------------------------------------------------------------------------------------------
    // Constants
    // ----------------------------------------------------------------------------------------------------------------

    /**
     * Represents the vertical and horizontal size of the puzzle board.
     */
    public final static int PUZZLE_BOARD_SIZE = 3;

    /**
     * Represents the empty token on the board.
     */
    public final static int BLANK_TOKEN = -1;

    // ----------------------------------------------------------------------------------------------------------------
    // Fields
    // ----------------------------------------------------------------------------------------------------------------

    /**
     * Represents the state of the board in numeric form.
     */
    private int[][] puzzleBoard;

    // ----------------------------------------------------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------------------------------------------------

    /**
     * Builds a puzzle with the specified game file.
     * @param defaultGameFile - Is the path to the default game file.
     * @throws IOException - If there are problems reading the file.
     * @throws NumberFormatException - If there are problems parsing strings to numbers.
     */
    public Puzzle(String defaultGameFile) throws IOException, NumberFormatException {
        puzzleBoard = new int[PUZZLE_BOARD_SIZE][PUZZLE_BOARD_SIZE];
        loadGame(defaultGameFile);
    }

    // ----------------------------------------------------------------------------------------------------------------
    // Services
    // ----------------------------------------------------------------------------------------------------------------

    /**
     * Gets the puzzle board of the current game.
     * @return A integer matrix with the state of the puzzle board.
     */
    public int[][] getPuzzleBoard() {
        return puzzleBoard;
    }

    /**
     * Loads the game from the specified file.
     * @param fileName - The file where the game is configured.
     * @throws IOException - If there are problems reading the file.
     * @throws NumberFormatException - If there are problems parsing strings to integers.
     */
    public void loadGame(String fileName) throws IOException, NumberFormatException {
        FileReader reader = new FileReader(fileName);

        Properties properties = new Properties();
        properties.load(reader);

        for (int i = 0; i < puzzleBoard.length; i++) {
            for (int j = 0; j < puzzleBoard[0].length; j++) {
                puzzleBoard[i][j] = Integer.parseInt(properties.getProperty("puzzle" + i + "-" + j));
            }
        }
    }

    public void doMove(int selectedNumber) {
        TokenCoordinates tokenCoordinates = findToken(selectedNumber);
        if (isBlankNeighbour(tokenCoordinates)) {
            TokenCoordinates blankTokenCoordinates = findToken(BLANK_TOKEN);
            swapTokens(tokenCoordinates, blankTokenCoordinates);
        }

    }

    private TokenCoordinates findToken(int tokenValue) {
        TokenCoordinates tokenCoordinates = null;
        boolean tokenFound = false;
        for (int i = 0; i < puzzleBoard.length && !tokenFound; i++) {
            for (int j = 0; j < puzzleBoard[0].length && !tokenFound; j++) {
                int currentToken = puzzleBoard[i][j];
                if (currentToken == tokenValue) {
                    tokenFound = true;
                    tokenCoordinates = new TokenCoordinates(i, j);
                }
            }
        }
        return tokenCoordinates;
    }

    private boolean isBlankNeighbour(TokenCoordinates coordinates) {
        boolean isBlankNeighbour = false;
        int i = coordinates.getRow();
        int j = coordinates.getColumn();

        if (isNextRowInTheBoard(i)) {
            if (puzzleBoard[i + 1][j] == BLANK_TOKEN) {
                isBlankNeighbour = true;
            }
        }
        if (isPreviousRowInTheBoard(i)) {
            if (puzzleBoard[i - 1][j] == BLANK_TOKEN) {
                isBlankNeighbour = true;
            }
        }
        if (isNextColumnInTheBoard(j)) {
            if (puzzleBoard[i][j + 1] == BLANK_TOKEN) {
                isBlankNeighbour = true;
            }
        }
        if (isPreviousColumnInTheBoard(j)) {
            if (puzzleBoard[i][j - 1] == BLANK_TOKEN) {
                isBlankNeighbour = true;
            }
        }
        return isBlankNeighbour;
    }

    private boolean isNextRowInTheBoard(int row) {
        return row + 1 <= puzzleBoard.length - 1;
    }

    private boolean isPreviousRowInTheBoard(int row) {
        return row - 1 >= 0;
    }

    private boolean isNextColumnInTheBoard(int column) {
        return column + 1 <= puzzleBoard.length - 1;
    }

    private boolean isPreviousColumnInTheBoard(int column) {
        return column - 1 >= 0;
    }

    private void swapTokens(TokenCoordinates labeledToken, TokenCoordinates blankToken) {
        int temp = puzzleBoard[labeledToken.getRow()][labeledToken.getColumn()];
        puzzleBoard[labeledToken.getRow()][labeledToken.getColumn()] =
                puzzleBoard[blankToken.getRow()][blankToken.getColumn()];
        puzzleBoard[blankToken.getRow()][blankToken.getColumn()] = temp;
    }

}
