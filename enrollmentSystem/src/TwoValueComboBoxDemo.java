import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TwoValueComboBoxDemo extends JFrame {
    private JComboBox<Integer> comboBox;
    private JTextArea textArea;
    private int clickCount = 0;
    private int total = 0; // Initialized to 0 for multiplication
    private JButton addButton; // Declare button here to make it accessible

    public TwoValueComboBoxDemo() {
        super("Two Value Combo Box Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Combo box
        Integer[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        comboBox = new JComboBox<>(numbers);

        // Button
        addButton = new JButton("Add");
        addButton.addActionListener(new AddButtonListener());

        // Cancel Button
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new CancelButtonListener());

        // Text area to display values
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Add components to the frame
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        JPanel comboBoxPanel = new JPanel(new FlowLayout());
        comboBoxPanel.add(comboBox);
        comboBoxPanel.add(buttonPanel);

        add(comboBoxPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // ActionListener for the "Add" button
    private class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (clickCount < 2) { // Check if click count is less than 2
                int selectedItem = (Integer) comboBox.getSelectedItem();
                total += selectedItem * 200;
                clickCount++;
                textArea.append("Item " + clickCount + " (200 x " + selectedItem + ") = " + (selectedItem * 200) + "\n");
                textArea.append("Running Total: " + total + "\n");
                if (clickCount == 2) { // Disable the button after 2 clicks
                    addButton.setEnabled(false);
                }
            } else {
                JOptionPane.showMessageDialog(null, "You have reached the click limit.");
            }
        }
    }

    // ActionListener for the "Cancel" button
    private class CancelButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            total = 0;
            clickCount = 0;
            textArea.setText(""); // Clear the text area
            addButton.setEnabled(true); // Enable the button
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TwoValueComboBoxDemo::new);
    }
}
