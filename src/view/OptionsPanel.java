package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionsPanel extends JPanel implements ActionListener {

    // ----------------------------------------------------------------------------------------------------------------
    // Constants
    // ----------------------------------------------------------------------------------------------------------------

    /**
     * Represents the load game command for a button.
     */
    private final static String LOAD_GAME_COMMAND = "Load Game";

    /**
     * Represents the claim victory command for a button.
     */
    private final static String CLAIM_VICTORY_COMMAND = "Claim Victory!";

    /**
     * Represents the save game command for a button.
     */
    private final static String SAVE_GAME_COMMAND = "Save Game";

    // ----------------------------------------------------------------------------------------------------------------
    // Fields
    // ----------------------------------------------------------------------------------------------------------------

    /**
     * Represents the load game button.
     */
    private JButton loadGameButton;

    /**
     * Represents the claim victory button.
     */
    private JButton claimVictoryButton;

    /**
     * Represents the save game button.
     */
    private JButton saveGameButton;

    /**
     * Represents the main frame where the panel is placed.
     */
    private MainFrame context;

    // ----------------------------------------------------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------------------------------------------------

    /**
     *
     * @param context
     */
    public OptionsPanel(MainFrame context) {
        this.context = context;
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));

        loadGameButton = new JButton(LOAD_GAME_COMMAND);
        loadGameButton.setActionCommand(LOAD_GAME_COMMAND);
        loadGameButton.addActionListener(this);

        claimVictoryButton = new JButton(CLAIM_VICTORY_COMMAND);
        claimVictoryButton.setActionCommand(CLAIM_VICTORY_COMMAND);
        claimVictoryButton.addActionListener(this);

        saveGameButton = new JButton(SAVE_GAME_COMMAND);
        saveGameButton.setActionCommand(SAVE_GAME_COMMAND);
        saveGameButton.addActionListener(this);

        add(loadGameButton);
        add(claimVictoryButton);
        add(saveGameButton);
    }

    /**
     * Invoked when an action occurs.
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals(LOAD_GAME_COMMAND)) {
            context.loadGame();
        } else if (command.equals(CLAIM_VICTORY_COMMAND)) {
            context.claimVictory();
        } else if (command.equals(SAVE_GAME_COMMAND)) {
            context.saveGame();
        }
    }
}
