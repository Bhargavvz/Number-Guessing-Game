import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessingGameGUI extends JFrame {
    private JTextField guessField;
    private JLabel messageLabel;
    private int computersNumber;
    private int guessCount;

    public GuessingGameGUI() {
        setTitle("Guessing Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());

        JLabel promptLabel = new JLabel("Enter your guess (1-100):");
        guessField = new JTextField(10);
        JButton guessButton = new JButton("Guess");

        topPanel.add(promptLabel);
        topPanel.add(guessField);
        topPanel.add(guessButton);
        add(topPanel, BorderLayout.NORTH);

        messageLabel = new JLabel("Let's play a game. I'll pick a number between 1 and 100, and you try to guess it.");
        add(messageLabel, BorderLayout.CENTER);

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        initializeGame();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeGame() {
        Random random = new Random();
        computersNumber = random.nextInt(100) + 1;
        guessCount = 0;
    }

    private void checkGuess() {
        try {
            int userGuess = Integer.parseInt(guessField.getText());
            guessCount++;

            if (userGuess == computersNumber) {
                messageLabel.setText("You got it in " + guessCount + " guesses! My number was " + computersNumber);
                initializeGame();
            } else if (guessCount == 6) {
                messageLabel.setText("You didn't get the number in 6 guesses. You lose. My number was " + computersNumber);
                initializeGame();
            } else if (userGuess < computersNumber) {
                messageLabel.setText("That's too low. Try again:");
            } else {
                messageLabel.setText("That's too high. Try again:");
            }
        } catch (NumberFormatException ex) {
            messageLabel.setText("Please enter a valid number.");
        }
        guessField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GuessingGameGUI();
            }
        });
    }
}
