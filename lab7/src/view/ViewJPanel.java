package view;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.User;
import utility.DatabaseConnector;

public class ViewJPanel extends JPanel {
    private DefaultTableModel tableModel = new DefaultTableModel(
            new String[]{"ID", "Name", "Age", "College", "Hobbies"}, 0) {
        @Override
        public boolean isCellEditable(int row, int col) { return false; }
    };
    private JTable table = new JTable(tableModel);
    private JTextField nameField = new JTextField(15);
    private JTextField ageField = new JTextField(15);
    private JTextField collegeField = new JTextField(15);
    private JTextField hobbyField = new JTextField(15);
    private JButton editBtn = new JButton("Edit");
    private JButton updateBtn = new JButton("Update");
    private JButton deleteBtn = new JButton("Delete");
    private ArrayList<User> users = new ArrayList<>();
    private User selected;

    public ViewJPanel() {
        setLayout(new BorderLayout(10, 10));
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel bottom = new JPanel(new GridLayout(0, 2, 6, 6));
        bottom.add(new JLabel("Name:")); bottom.add(nameField);
        bottom.add(new JLabel("Age:")); bottom.add(ageField);
        bottom.add(new JLabel("College:")); bottom.add(collegeField);
        bottom.add(new JLabel("Hobbies:")); bottom.add(hobbyField);
        JPanel buttons = new JPanel();
        buttons.add(editBtn); buttons.add(updateBtn); buttons.add(deleteBtn);
        JPanel south = new JPanel(new BorderLayout());
        south.add(bottom, BorderLayout.CENTER);
        south.add(buttons, BorderLayout.SOUTH);
        add(south, BorderLayout.SOUTH);

        setFieldsEnabled(false);
        updateBtn.setEnabled(false);

        // Selecting a row fills the fields (read-only until Edit is clicked)
        table.getSelectionModel().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) return;
            int row = table.getSelectedRow();
            if (row < 0) return;
            selected = users.get(row);
            nameField.setText(selected.getName());
            ageField.setText(String.valueOf(selected.getAge()));
            collegeField.setText(selected.getCollege());
            hobbyField.setText(selected.getHobby());
            setFieldsEnabled(false);
            updateBtn.setEnabled(false);
        });

        editBtn.addActionListener(e -> {
            if (selected == null) {
                JOptionPane.showMessageDialog(this, "Select a row first.");
                return;
            }
            setFieldsEnabled(true);
            updateBtn.setEnabled(true);
        });

        updateBtn.addActionListener(e -> {
            if (selected == null) return;
            int age;
            try {
                age = Integer.parseInt(ageField.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Age must be a number.");
                return;
            }
            User updated = new User(nameField.getText().trim(), age,
                    collegeField.getText().trim(), hobbyField.getText().trim());
            DatabaseConnector.updateUser(selected, updated);
            JOptionPane.showMessageDialog(this, "Updated successfully.");
            populateTable();
        });

        deleteBtn.addActionListener(e -> {
            if (selected == null) {
                JOptionPane.showMessageDialog(this, "Select a row first.");
                return;
            }
            int ok = JOptionPane.showConfirmDialog(this, "Delete this record?",
                    "Confirm", JOptionPane.YES_NO_OPTION);
            if (ok == JOptionPane.YES_OPTION) {
                DatabaseConnector.deleteUser(selected);
                populateTable();
            }
        });

        populateTable();
    }

    private void setFieldsEnabled(boolean on) {
        nameField.setEnabled(on); ageField.setEnabled(on);
        collegeField.setEnabled(on); hobbyField.setEnabled(on);
    }

    public void populateTable() {
        users = DatabaseConnector.getUsers();
        tableModel.setRowCount(0);
        for (User u : users) {
            tableModel.addRow(new Object[]{u.getUserId(), u.getName(),
                u.getAge(), u.getCollege(), u.getHobby()});
        }
        selected = null;
        nameField.setText(""); ageField.setText("");
        collegeField.setText(""); hobbyField.setText("");
        setFieldsEnabled(false);
        updateBtn.setEnabled(false);
    }
}
