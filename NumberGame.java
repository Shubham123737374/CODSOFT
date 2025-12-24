import java.util.Scanner;
import java.util.Random;

public class NumberGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int score = 0; // User ka score
        String playAgain = "yes";

        System.out.println("Welcome to the Number Guessing Game!");

        // Loop for Multiple Rounds
        while (playAgain.equalsIgnoreCase("yes")) {
            int randomNumber = rand.nextInt(100) + 1; // 1 se 100 ke beech number
            int attempts = 0;
            int maxAttempts = 5; // Total 5 chances milengi
            boolean won = false;

            System.out.println("\nI have picked a number between 1 and 100.");
            System.out.println("You have " + maxAttempts + " attempts to guess it.");

            // Loop for Attempts
            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int userGuess = sc.nextInt();
                attempts++;

                if (userGuess == randomNumber) {
                    System.out.println("🎉 Correct! You won in " + attempts + " attempts.");
                    score++; // Jeetne par score badhega
                    won = true;
                    break;
                } else if (userGuess > randomNumber) {
                    System.out.println("Too High! Try again.");
                } else {
                    System.out.println("Too Low! Try again.");
                }
                
                System.out.println("Attempts left: " + (maxAttempts - attempts));
            }

            if (!won) {
                System.out.println("❌ Oops! You ran out of attempts. The number was: " + randomNumber);
            }

            System.out.println("Your Current Score: " + score);
            System.out.print("\nDo you want to play again? (yes/no): ");
            playAgain = sc.next();
        }

        System.out.println("Thank you for playing! Final Score: " + score);
        sc.close();
    }
}