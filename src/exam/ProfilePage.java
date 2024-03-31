package exam;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class ProfilePage<ExamScreen> extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JLabel welcomeLabel;
    private JButton startExamButton;
    private JButton logoutButton;
    private JButton changeCredentialsButton;

    public ProfilePage(String username) {
        // Set up the JFrame
        setTitle("Online Examination System - " + username);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage(ProfilePage.class.getResource("icon.png"))); // Change the icon

        // Create a JPanel for the welcome message
        JPanel welcomePanel = new JPanel(new GridLayout(1, 1));
        welcomePanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create the welcome message
        welcomeLabel = new JLabel("Welcome, " + username + "!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        welcomePanel.add(welcomeLabel);

        // Create a JPanel for the buttons
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1));
        buttonPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create the start exam button
        startExamButton = new JButton("Start Exam");
        startExamButton.setFont(new Font("Arial", Font.PLAIN, 16));
        startExamButton.addActionListener(this);
        buttonPanel.add(startExamButton);

        // Create the logout button
        logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Arial", Font.PLAIN, 16));
        logoutButton.addActionListener(this);
        buttonPanel.add(logoutButton);

        // Create the change credentials button
        changeCredentialsButton = new JButton("Change Username/Password");
        changeCredentialsButton.setFont(new Font("Arial", Font.PLAIN, 16));
        changeCredentialsButton.addActionListener(this);
        buttonPanel.add(changeCredentialsButton);

        // Add the welcome panel and button panel to the JFrame
        add(welcomePanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startExamButton) {
            // Handle start exam button click
            ExamPage exampage = new ExamPage();
            exampage.setVisible(true);
            dispose(); // close the profile screen
        } else if (e.getSource() == logoutButton) {
            // Handle logout button click
            LoginPage loginpage = new LoginPage();
            loginpage.setVisible(true);
            dispose(); // close the profile screen
        } else if (e.getSource() == changeCredentialsButton) {
            // Handle change credentials button click
            ChangeCredentialsPage changeCredentialsPage = new ChangeCredentialsPage();
            changeCredentialsPage.setVisible(true);
        }
    }
}