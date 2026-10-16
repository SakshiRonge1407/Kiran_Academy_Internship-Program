import java.util.Random;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
    
        // Generate a random number between 1 and 100
        int numberToGuess = random.nextInt(100) + 1;
        int numberOfTries = 0;
        int playerGuess = 0;
        boolean hasWon = false;
        
        System.out.println("Welcome to the Random Number Guessing Game!");
        System.out.println("I'm thinking of a number between 1 and 100. Try to guess it!");
        
        // Game loop runs until the player guesses the right number
        while (!hasWon) {
            System.out.print("Enter your guess: ");
            
            // Check if the input is actually a valid integer
            if (scanner.hasNextInt()) {
                playerGuess = scanner.nextInt();
                numberOfTries++;
                
                if (playerGuess < 1 || playerGuess > 100) {
                    System.out.println("Please guess a number within the range of 1 to 100.");
                } else if (playerGuess < numberToGuess) {
                    System.out.println("Too low! Try again.");
                } else if (playerGuess > numberToGuess) {
                    System.out.println("Too high! Try again.");
                } else {
                    hasWon = true;
                    System.out.println("\nCongratulations! You guessed the number in " + numberOfTries + " tries.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid whole number.");
                scanner.next(); 
            }
        }
        
        scanner.close();
    }
}