// Name: Ekene Alexander Ochuba
// Student No: 3155904

package griffith;

import java.util.Scanner;

public class AtuuGame {
	// to Track the number of games played, won, and lost
	private static int gamesPlayed = 0;
	private static int gamesWon = 0;
	private static int gamesLost = 0;
	// Track the highest score
	private static int highestScore = 0;

	// Constants for "High" and "Low" guesses
	private static final String HIGH = "H";
	private static final String LOW = "L";

	// method to start the game
	public static void startGame() {
		Scanner scan = new Scanner(System.in);

		// Main game loop to keep the game running
		while (true) {
			displayMenu(); // Display the menu to the player
			// Check if the user has entered a valid number
			if (scan.hasNextInt()) {
				int choice = scan.nextInt();

				// switch case to handle the menu choices
				switch (choice) {
				case 1:
					playGame(scan); // Start a new game
					break;
				case 2:
					showStatistics(scan); // Show game statistics
					break;
				case 3:
					System.out.println("\n (づ ᴗ _ᴗ)づ♡ Thank you for playing!\n"); // Exit message
					printExitArt(); // Display exit art
					scan.close(); // Close scanner
					return; // Exit the game
				default:
					System.out.println("Invalid choice. Please try again.");
				}
			} else {
				// Handle non-integer input from the user
				System.out.println("\nInvalid choice. Please enter a number from the menu.");
				scan.next(); // Clear invalid input
			}
		}
	}

	// Method to display the menu options
	private static void displayMenu() {
		System.out.println("\n(˶ᵔ ᵕ ᵔ˶)\n");
		System.out.println("Welcome to Atuu Game\n");
		System.out.println("In this game, you are a Citizen character, playing against a Village Chief");
		System.out.println(
				"In order to win you need to guess if the Chief's card is higher than yours or not.\nGoodluck!");
		System.out.println("\nSelect an option:");
		System.out.println("1. Start a new game");
		System.out.println("2. View game statistics");
		System.out.println("3. Exit");
		System.out.print("\nEnter your choice: ");
	}

	// Method to play the game
	private static void playGame(Scanner scan) {
		// Prompt for difficulty level
		System.out.println("\nSelect difficulty level:");
		System.out.println("1. Easy (5 rounds)");
		System.out.println("2. Medium (10 rounds)");
		System.out.println("3. Hard (15 rounds)");
		System.out.print("\nEnter your choice: \n");

		// Logic to Make sure User selects from the Menu options
		int difficulty = getValidChoice(scan, 1, 3);

		// Switch case to select difficulty mode
		int rounds;
		switch (difficulty) {
		case 1:
			rounds = 5; // Easy
			break;
		case 2:
			rounds = 10; // Medium
			break;
		case 3:
			rounds = 15; // Hard
			break;
		default:
			System.out.println("Invalid choice, setting to Easy.");
			rounds = 5; // Hard
		}

		Deck deck = new Deck(); // Create a new deck
		deck.shuffle(); // Shuffle the deck

		int score = playRound(deck, rounds, scan); // Directly plays the specified number of rounds

		// Print Game score
		System.out.println("Your score for this game is: " + score);
		// Update highest score
		if (score > highestScore)
			highestScore = score; // Updates highest score, if applicable

		// Count games played
		gamesPlayed++;
		if (score == rounds) {
			gamesWon++; // Player wins if they score the maximum possible
			System.out.println("Congratulations! You got all rounds correct! (˶ᵔ ᵕ ᵔ˶)");
		} else {
			gamesLost++; // Player loses otherwise
			System.out.println("Better luck next time! (｡•́︿•̀｡)\n");
			System.out.println("-------------------------------------");
		}
	}

	// Method to play a round of the game
	private static int playRound(Deck deck, int rounds, Scanner scan) {

		// Deal the first card and show it
		int correctGuesses = 0; // Track correct guesses
		Card currCard = deck.dealCard(); // the first card

//		Loop for each round
		for (int round = 1; round <= rounds; round++) {
			System.out.println("\n( ≧ᗜ≦)");
			System.out.println("Round " + round + "/" + rounds);
			System.out.println("\nYour card is the " + currCard + "\n");

			// Player makes a guess for the next card
			System.out.println("Will the next card be higher (H) or lower (L)?");

			String guess = getValidGuess(scan);

			Card nextCard = deck.dealCard(); // Deal the next card
			System.out.println("\nThe next card is " + nextCard);

			// Check if the player's guess was correct
			if ((guess.equals(HIGH) && nextCard.getValue() > currCard.getValue())
					|| (guess.equals(LOW) && nextCard.getValue() < currCard.getValue())) {
				correctGuesses++;
				System.out.println("Your prediction was correct!\n");
				System.out.println("---------------------------------------");
			} else {
				System.out.println("Your prediction was incorrect!\n");
				System.out.println("---------------------------------------");
			}

			currCard = nextCard; // Set the current card to the next one for the next round
		}

		// Return the total number of correct guesses made
		System.out.println(
				"\nThe round is over. You made " + correctGuesses + " correct guesses out of " + rounds + "\n");
		return correctGuesses;
	}

	// Method to show the game statistics
	private static void showStatistics(Scanner scan) {
		System.out.println("\nGame Statistics:");
		System.out.println("-------------------------------");
		System.out.println("Games Played: " + gamesPlayed);
		System.out.println("Games Won: " + gamesWon);
		System.out.println("Games Lost: " + gamesLost);
		System.out.println("Highest Score: " + highestScore);
		System.out.println("-------------------------------");

		// Ask if the player wants to play again
		System.out.println("\nReady to play? (y/n)");
		String ans;
		do {
			ans = scan.next().toLowerCase();
			if (!ans.equals("y") && !ans.equals("n")) {
				System.out.println("\n(ó﹏ò｡) Please respond with 'y' or 'n'");
			}
		} while (!ans.equals("y") && !ans.equals("n"));

		// If the player wants to play again, start a new game
		if (ans.equals("y")) {
			playGame(scan);
		} else {
			System.out.println("(づ ᴗ _ᴗ)づ♡ Thank you for viewing statistics!\n");
		}
	}

	// Method to get a valid choice from the player within a specified range
	private static int getValidChoice(Scanner scan, int min, int max) {
		int choice;
		while (true) {
			if (scan.hasNextInt()) {
				choice = scan.nextInt();
				// Ensure the choice is within the valid range
				if (choice >= min && choice <= max) {
					break;
				}
			}
			System.out.println("Invalid input. Please enter a number between " + min + " and " + max + ".");
			scan.next(); // Clear invalid input
		}
		return choice;
	}

	// Method to get a valid guess ("H" or "L") from the player
	private static String getValidGuess(Scanner scan) {
		String guess;
		while (true) {
			guess = scan.next().toUpperCase();
			if (guess.equals(HIGH) || guess.equals(LOW)) {
				break; // Valid guess, break the loop
			}
			System.out.println("Please respond with 'H' or 'L'.");
		}
		return guess;
	}

	// Method to print exit art when the game is over
	private static void printExitArt() {
		System.out.println("BBBB   Y   Y  EEEEE");
		System.out.println("B   B   Y Y   E    ");
		System.out.println("BBBB     Y    EEEE ");
		System.out.println("B   B    Y    E    ");
		System.out.println("BBBB     Y    EEEEE");

	}
}