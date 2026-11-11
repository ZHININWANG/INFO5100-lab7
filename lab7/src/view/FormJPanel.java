package view;

import java.awt.*;
import javax.swing.*;
import model.User;
import utility.DatabaseConnector;

public class FormJPanel extends JPanel {
    private JTextField nameField = new JTextField(20);
    private JTextField ageField = new JTextField(20);
    private JTextField collegeField = new JTextField(20);
    private JTextField hobbyField = new JTextField(20);
    private ViewJPanel viewPanel;

    public FormJPanel(ViewJPanel viewPanel) {
        this.viewPanel = viewPanel;
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 8, 8, 8);
        c.anchor = GridBagConstraints.WEST;

        String[] labels = {"Name:", "Age:", "College:", "Hobbies:"};
        JTextField[] fields = {nameField, ageField, collegeField, hobbyField};
        for (int i = 0; i < labels.length; i++) {
            c.gridx = 0; c.gridy = i;
            add(new JLabel(labels[i]), c);
            c.gridx = 1;
            add(fields[i], c);
        }

        JButton submit = new JButton("Submit");
        c.gridx = 1; c.gridy = labels.length;
        add(submit, c);

        submit.addActionListener(e -> {
            String name = nameField.getText().trim();
            String ageText = ageField.getText().trim();
            if (name.isEmpty() || ageText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name and age are required.");
                return;
            }
            int age;
            try {
                age = Integer.parseInt(ageText);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Age must be a number.");
                return;
            }
            User u = new User(name, age, collegeField.getText().trim(), hobbyField.getText().trim());
            try {
                DatabaseConnector.addUser(u);
                JOptionPane.showMessageDialog(this, "Saved successfully.");
                nameField.setText(""); ageField.setText("");
                collegeField.setText(""); hobbyField.setText("");
                viewPanel.populateTable();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });
    }
}
