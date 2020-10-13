package SearchGUI;

import javax.swing.*;

public class EditDialog {
    /**
     * create edit dialog.
     * @param app search gui.
     * @param posX x coordinate.
     * @param posY y coordinate.
     * @param width dialog 's width.
     * @param height dialog 's height.
     * @return edit dialog.
     */
    public static JDialog createDialog(SearchGUI app, int posX, int posY, int width, int height) {
        JDialog dialog = new JDialog();
        dialog.setBounds(posX, posY, width, height);
        dialog.setTitle("Edit dialog");

        JPanel panel = new JPanel();
        panel.setBounds(posX, posY, width, height);
        panel.setLayout(null);

        // word text field
        WordTextField targetField = new WordTextField();
        WordTextField explainField = new WordTextField();

        targetField.setPosition(20, 30, width - 40, 40);
        panel.add( targetField.createWordTextField(app, "Word Target: ", targetField, explainField, dialog) );

        explainField.setPosition(20, 30 + 40 + 20, width - 40, 40);
        panel.add( explainField.createWordTextField(app, "Word Explain: ", targetField, explainField, dialog) );

        // submit button
        SubmitButton submitButton = new SubmitButton();
        submitButton.setPosition(140, 30 + 40 + 20 + 40 + 20, 100, 40);
        panel.add( submitButton.createSubmitButton(app, targetField, explainField, dialog) );

        dialog.add(panel);
        return dialog;
    }
}
