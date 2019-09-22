package view;

import model.Puzzle;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainFrame extends JFrame {

    // ----------------------------------------------------------------------------------------------------------------
    // Constants
    // ----------------------------------------------------------------------------------------------------------------

    /**
     * Represents the path to the default game file.
     */
    public final static String DEFAULT_GAME_FILE = "./games/game1.properties";

    // ----------------------------------------------------------------------------------------------------------------
    // Fields
    // ----------------------------------------------------------------------------------------------------------------

    /**
     * Represents the panel where is placed the graphical puzzle board
     */
    private PuzzlePanel puzzlePanel;

    /**
     * The data representation of the puzzle to show in the view.
     */
    private Puzzle puzzle;

    // ----------------------------------------------------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------------------------------------------------

    /**
     * Builds the main frame, initializes the puzzle with the default game file and configures the puzzle panel.
     */
    public MainFrame() {
        setTitle("Puzzle");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());

        initializePuzzle();

        int[][] puzzleModel = puzzle.getPuzzleBoard();
        puzzlePanel = new PuzzlePanel(this, puzzleModel);
        add(puzzlePanel, BorderLayout.CENTER);

    }

    // ----------------------------------------------------------------------------------------------------------------
    // Services
    // ----------------------------------------------------------------------------------------------------------------

    /**
     * Initializes the puzzle in the data model.
     */
    public void initializePuzzle() {
        try {
            puzzle = new Puzzle(DEFAULT_GAME_FILE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "An error occurred loading the game: " +
                    e.getMessage(), "Load game", JOptionPane.ERROR_MESSAGE);
            dispose();
        }
    }

    /**
     * Delegates and control the user movement request
     * @param selectedNumber - Is the value of the token in the board to move.
     */
    public void doMove(int selectedNumber) {
        puzzle.doMove(selectedNumber);
        puzzlePanel.setPuzzleModel(puzzle.getPuzzleBoard());
    }

    /**
     * Entry point of the program.
     * @param args - CLI arguments. Not required in this case.
     */
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }
}
