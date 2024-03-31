package exam;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.swing.*;

class Question {
    private String question;
    private String answer;
    private String[] options;

    public Question(String question, String answer, String[] options) {
        this.question = question;
        this.answer = answer;
        this.options = options;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String[] getOptions() {
        return options;
    }
}


public class ExamPage extends JFrame{

    //private static final long serialVersionUID = 1L;
    private int score = 0;
    private int currentQuestionIndex = 0;
    private JLabel timerLabel;
    private Timer timer;
    private List<Question> questions;

    public ExamPage() {
        // Set up the JFrame
        setTitle("Online Examination System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage(ExamPage.class.getResource("icon.png"))); // Change the icon

        // Create a JPanel for the timer
        JPanel timerPanel = new JPanel(new GridLayout(1, 2));
        timerPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create a label to display the remaining time
        timerLabel = new JLabel(String.valueOf("60"), SwingConstants.CENTER);
        timerLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        timerPanel.add(timerLabel);

        // Create a JPanel for the question and answer buttons
        JPanel questionPanel = new JPanel(new GridLayout(6, 1));
        questionPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create a JPanel for the submit button
        JPanel submitPanel = new JPanel(new GridLayout(1, 1));
        submitPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create a list of questions
        questions = new ArrayList<>();
        questions.add(new Question("Which of the following option leads to the portability and security of Java?", "Bytecode is executed by the JVM", new String[]{"The applet makes the Java code secure and portable", "Use of exception handling", "Bytecode is executed by the JVM", "Dynamic binding between objects"}));
        questions.add(new Question("Which of the following is not a Java features?", "Use of pointers", new String[]{"Use of pointers", "Dynamic", "Architecture Neutral", "Object-oriented"}));
        questions.add(new Question("The \\u0021 article referred to as a_____.", "Unicode escape sequence", new String[]{"Octal escape", "Line feed", "Hexadecimal", "Unicode escape sequence"}));
        questions.add(new Question("_____ is used to find and fix bugs in the Java programs.", "JDB", new String[]{"JVM", "JDB", "JDK", "JRE"}));
        questions.add(new Question("What is the return type of the hashCode() method in the Object class?", "int", new String[]{"object", "int", "void", "long"}));
        questions.add(new Question("Which of the following is a valid long literal?", "0xnf029L", new String[]{"L990023", "ABH8097", "0xnf029L", "904423"}));
        questions.add(new Question("What does the expression float a = 35 / 0 return?", "Infinity", new String[]{"0", "Not a Number", "Run time exception", "Infinity"}));
        questions.add(new Question("Evaluate the following Java expression, if x=3, y=5, and z=10:\r\n" + "\r\n"
        		+ "++z + y - y + z + x++", "25", new String[]{"22", "23", "25", "24"}));
        questions.add(new Question("Which of the following tool is used to generate API documentation in HTML format from doc comments in source code?", "Javadoc tool", new String[]{"Javadoc tool", "javaw command", "Javadoc tool", "javah command"}));
        questions.add(new Question("Which method of the Class.class is used to determine the name of a class represented by the class object as a String?", "getName()", new String[]{"getName()", "toString()", "intern()", "getClass()"}));
        
        // Shuffle the questions so they appear in a random order
        Collections.shuffle(questions);

        // Add the first question and answer buttons to the panel
        updateQuestionPanel(questionPanel,submitPanel);

       // Create the submit button
        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.PLAIN, 16));
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                JOptionPane.showMessageDialog(null, "Your score is " + score + " out of " + questions.size(), "Exam completed", JOptionPane.INFORMATION_MESSAGE);
                endQuiz();
            }
        });
        submitPanel.add(submitButton);

        // Add the timer panel, question panel, and submit panel to the JFrame
        add(timerLabel, BorderLayout.NORTH);
        add(questionPanel, BorderLayout.CENTER);
        add(submitPanel, BorderLayout.SOUTH);

        // Create a timer that counts down from EXAM_TIME seconds
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int remainingTime = Integer.parseInt(timerLabel.getText());
                if (remainingTime > 0) {
                    remainingTime--;
                    timerLabel.setText(String.valueOf(remainingTime));
                } else {
                    timer.stop();
                    JOptionPane.showMessageDialog(null, "Time's up! Your score is " + score + " out of " + questions.size(), "Time's up", JOptionPane.INFORMATION_MESSAGE);
                    endQuiz();
                }
            }
        });
        timer.start();
    }

    

private void updateQuestionPanel(JPanel questionPanel, JPanel submitPanel) {
    // Get the current question
    Question currentQuestion = questions.get(currentQuestionIndex);
    questionPanel.removeAll();

    // Create the question label
    JLabel questionLabel = new JLabel(currentQuestion.getQuestion());
    questionLabel.setFont(new Font("Arial", Font.PLAIN, 18));
    questionPanel.add(questionLabel);

    // Create the answer buttons
    String[] options = currentQuestion.getOptions();
    for (int i = 0; i < options.length; i++) {
        JButton answerButton = new JButton(options[i]);
        answerButton.setFont(new Font("Arial", Font.PLAIN, 14));

        // Add an action listener to check the answer and update the score
        answerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if the answer is correct
                if (answerButton.getText().equals(currentQuestion.getAnswer())) {
                    score++;
                }

                // Move to the next question
                currentQuestionIndex++;
                if (currentQuestionIndex < questions.size()) {
                    updateQuestionPanel(questionPanel,submitPanel);
                } else {
                    // End the quiz when all questions have been answered
                    timer.stop();
                    JOptionPane.showMessageDialog(null, "Your score is " + score + " out of " + questions.size(), "Exam completed", JOptionPane.INFORMATION_MESSAGE);
                    endQuiz();
                }
            }
        });

        questionPanel.add(answerButton);
    }

    // Update the content pane
    getContentPane().removeAll();
    getContentPane().add(timerLabel, BorderLayout.NORTH);
    getContentPane().add(questionPanel, BorderLayout.CENTER);
    getContentPane().add(submitPanel, BorderLayout.SOUTH);
    revalidate();
    repaint();
}
protected void endQuiz() {
    // Read the username from the credentials file
    String username = null;
    try {
        Scanner reader = new Scanner(LoginPage.class.getResourceAsStream("credentials.txt"));
        username = reader.nextLine();
        reader.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    
    // Return to the profile screen with the correct username
    ProfilePage profilepage = new ProfilePage(username);
    profilepage.setVisible(true);
    dispose();
}
}