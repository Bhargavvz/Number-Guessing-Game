import java.util.Random;
import java.util.Scanner;

public class GuessingGame {
    private int computersNumber;
    private int guessCount;
    private Scanner scanner;

    public GuessingGame() {
        scanner = new Scanner(System.in);
        playGame();
        scanner.close();
    }

    private void initializeGame() {
        Random random = new Random();
        computersNumber = random.nextInt(100) + 1;
        guessCount = 0;
    }

    private void playGame() {
        boolean playAgain = true;

        while (playAgain) {
            initializeGame();
            System.out.println("Let's play a game. I'll pick a number between 1 and 100, and you try to guess it.");

            while (guessCount < 6) {
                System.out.print("Enter your guess (1-100): ");
                try {
                    int userGuess = scanner.nextInt();
                    guessCount++;

                    if (userGuess == computersNumber) {
                        System.out.println("You got it in " + guessCount + " guesses! My number was " + computersNumber);
                        break;
                    } else if (guessCount == 6) {
                        System.out.println("You didn't get the number in 6 guesses. You lose. My number was " + computersNumber);
                        break;
                    } else if (userGuess < computersNumber) {
                        System.out.println("That's too low. Try again:");
                    } else {
                        System.out.println("That's too high. Try again:");
                    }
                } catch (NumberFormatException ex) {
                    System.out.println(ex);
                    System.out.println("Please enter a valid number.");
                    scanner.nextLine(); // Clear the input buffer
                }
            }

            System.out.print("Do you want to play again? (yes/no): ");
            String playChoice = scanner.next().toLowerCase();
            playAgain = playChoice.equals("yes") || playChoice.equals("y");
        }
    }

    public static void main(String[] args) {
        new GuessingGame();
    }
}
