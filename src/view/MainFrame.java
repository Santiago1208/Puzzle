package view;

import model.Puzzle;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.IOException;

public class MainFrame extends JFrame {

    // ----------------------------------------------------------------------------------------------------------------
    // Constants
    // ----------------------------------------------------------------------------------------------------------------

    /**
     * Represents the path to the default game file.
     */
    public final static String DEFAULT_GAME_FILE = "./games/3x3.properties";

    /**
     * Represents the path to the default folder where the games configurations are saved.
     */
    public final static String GAME_FOLDER_PATH = "./games/";

    // ----------------------------------------------------------------------------------------------------------------
    // Fields
    // ----------------------------------------------------------------------------------------------------------------

    /**
     * Represents the panel where is placed the graphical puzzle board
     */
    private PuzzlePanel puzzlePanel;

    /**
     * Represents the panel where the general information of the game is shown.
     */
    private InformationPanel informationPanel;

    /**
     * Represents the panel where the options of the game are displayed.
     */
    private OptionsPanel optionsPanel;

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
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());

        initializePuzzle();

        int[][] puzzleModel = puzzle.getPuzzleBoard();
        puzzlePanel = new PuzzlePanel(this, puzzleModel);
        add(puzzlePanel, BorderLayout.CENTER);

        informationPanel = new InformationPanel();
        informationPanel.refreshMovementsCounter(puzzle.getMovements());
        add(informationPanel, BorderLayout.EAST);

        optionsPanel = new OptionsPanel(this);
        add(optionsPanel, BorderLayout.SOUTH);
        pack();
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
     * Delegates and control the user's movement requirement
     * @param selectedNumber - Is the value of the token in the board to move.
     */
    public void doMove(int selectedNumber) {
        puzzle.doMove(selectedNumber);
        puzzlePanel.setPuzzleModel(puzzle.getPuzzleBoard());
        informationPanel.refreshMovementsCounter(puzzle.getMovements());
    }

    /**
     * Delegates and control the user's load game requirement.
     */
    public void loadGame() {
        String gameFilePath = selectFile();
        if (!gameFilePath.equals("")) {
            try {
                changePuzzle(gameFilePath);
                JOptionPane.showMessageDialog(this, "Puzzle loaded successfully!",
                        "Load new game",JOptionPane.INFORMATION_MESSAGE);
                revalidate();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "An error occurred loading the game: " +
                        e.getMessage(), "Load game", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private String selectFile() {
        String gameFilePath = "";
        FileNameExtensionFilter nameExtensionFilter = new FileNameExtensionFilter("Properties Files",
                "properties");
        JFileChooser fileChooser = new JFileChooser(GAME_FOLDER_PATH);
        fileChooser.setDialogTitle("Open new Puzzle game");
        fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
        fileChooser.setFileFilter(nameExtensionFilter);
        fileChooser.setMultiSelectionEnabled(false);
        int returnValue = fileChooser.showDialog(this, "Open Game");
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            gameFilePath = fileChooser.getSelectedFile().getPath();
        }
        return gameFilePath;
    }

    /**
     * Orchestrates the change of the puzzle to a new one.
     * @param gameFilePath - The path of the file where the puzzle is configured.
     * @throws IOException - If there are problems reading the configuration file.
     */
    private void changePuzzle(String gameFilePath) throws IOException {
        puzzle.loadGame(gameFilePath);
        puzzlePanel.setPuzzleModel(puzzle.getPuzzleBoard());
        informationPanel.refreshMovementsCounter(puzzle.getMovements());
    }

    /**
     * Delegates and control the user's victory requirement
     */
    public void claimVictory() {
        if (puzzle.puzzleSolved()) {
            JOptionPane.showMessageDialog(this,
                    "Congratulations, you solved the puzzle in " + puzzle.getMovements() + " movements!",
                    "Victory!",JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                    "I guess the puzzle is not solved at all.\nKeep trying...",
                    "Fail!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void saveGame() {

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
