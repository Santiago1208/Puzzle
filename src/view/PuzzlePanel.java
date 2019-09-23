package view;

import model.Puzzle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PuzzlePanel extends JPanel implements ActionListener {

    // ----------------------------------------------------------------------------------------------------------------
    // Fields
    // ----------------------------------------------------------------------------------------------------------------

    /**
     * The graphical representation of the puzzle board
     */
    private JButton[][] puzzleBoard;

    /**
     * The data representation of the puzzle board
     */
    private int[][] puzzleModel;

    /**
     * Represents the instance of the context where the board is placed.
     */
    private MainFrame context;

    // ----------------------------------------------------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------------------------------------------------

    /**
     * Builds a Puzzle panel in a specific context and with a puzzle data model.
     * @param context - is the frame where the panel is located in.
     * @param puzzleModel - is the data representation of the puzzle.
     */
    public PuzzlePanel(MainFrame context, int[][] puzzleModel) {
        this.context = context;

        setLayout(new GridLayout(puzzleModel.length, puzzleModel.length));
        this.puzzleModel = puzzleModel;

        initializePuzzleBoard();
    }

    // ----------------------------------------------------------------------------------------------------------------
    // Services
    // ----------------------------------------------------------------------------------------------------------------

    /**
     * Modifies the puzzle model to draw in the panel. Also, refresh the board in the GUI.
     * @param puzzleModel - is the new state of the board to refresh
     */
    public void setPuzzleModel(int[][] puzzleModel) {
        this.puzzleModel = puzzleModel;
        initializePuzzleBoard();
    }

    /**
     * Converts the data puzzle into a graphical puzzle.
     */
    private void initializePuzzleBoard() {
        puzzleBoard = new JButton[puzzleModel.length][puzzleModel.length];
        for (int i = 0; i < puzzleBoard.length; i++) {
            for (int j = 0; j < puzzleBoard[0].length; j++) {
                int tokenValue = puzzleModel[i][j];
                String buttonLabel = "";
                if (tokenValue != Puzzle.BLANK_TOKEN) {
                    buttonLabel = Integer.toString(tokenValue);
                    puzzleBoard[i][j] = new JButton(buttonLabel);
                    puzzleBoard[i][j].setActionCommand(buttonLabel);
                    puzzleBoard[i][j].addActionListener(this);
                    puzzleBoard[i][j].setPreferredSize(new Dimension(80, 80));
                } else {
                    puzzleBoard[i][j] = new JButton();
                    puzzleBoard[i][j].setBackground(Color.BLACK);
                    puzzleBoard[i][j].setPreferredSize(new Dimension(80, 80));
                }
            }
        }
        renderPuzzleBoard();
    }

    /**
     * Draws the puzzle in the panel.
     */
    private void renderPuzzleBoard() {
        removeAll();
        setLayout(new GridLayout(puzzleModel.length, puzzleModel.length));
        for (int i = 0; i < puzzleBoard.length; i++) {
            for (int j = 0; j < puzzleBoard[0].length; j++) {
                JButton currentToken = puzzleBoard[i][j];
                add(currentToken);
            }
        }
        revalidate();
    }

    /**
     * Invoked when an action occurs.
     * @param e - The object which contains all the information about the generated event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        context.doMove(Integer.parseInt(actionCommand));
    }
}
