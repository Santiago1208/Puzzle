package view;

import javax.swing.*;
import java.awt.*;

public class InformationPanel extends JPanel {

    // ----------------------------------------------------------------------------------------------------------------
    // Fields
    // ----------------------------------------------------------------------------------------------------------------

    /**
     * Represents the label where the number of movements made by the player will be shown.
     */
    private JLabel movementsLabel;

    // ----------------------------------------------------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------------------------------------------------

    /**
     * Builds an information panel with the number of movements made by the player.
     */
    public InformationPanel() {
        setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));

        JLabel label = new JLabel("Movements: ");
        movementsLabel = new JLabel();
        add(label);
        add(movementsLabel);
    }

    // ----------------------------------------------------------------------------------------------------------------
    // Services
    // ----------------------------------------------------------------------------------------------------------------

    /**
     * Updates the player's movements counter from the panel.
     * @param newMovementsNumber - Is the new number of movements to show in the panel.
     */
    public void refreshMovementsCounter(int newMovementsNumber) {
        movementsLabel.setText(Integer.toString(newMovementsNumber));
    }
}
