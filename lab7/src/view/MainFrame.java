package view;

import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Student Manager");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);

        ViewJPanel viewPanel = new ViewJPanel();
        FormJPanel formPanel = new FormJPanel(viewPanel);
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Create", formPanel);
        tabs.addTab("View", viewPanel);
        add(tabs);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
