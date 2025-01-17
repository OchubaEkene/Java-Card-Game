// Name: Ekene Alexander Ochuba
// Student No: 3155904

package griffith;

import java.util.Random;

public class Deck {
	// Declare an array of Card objects to represent the deck
	private final Card[] deck;
	// Track how many cards have been used from the deck
	private int cardsUsed;

	public Deck() {
		this(false); // Default constructor assumes no jokers
	}

	// Overloaded constructor that allows including jokers in the deck
	public Deck(boolean includeJokers) {

		// Initialise the deck size to 54 if jokers are included, otherwise 52
		if (includeJokers) {
			deck = new Card[54]; // If jokers are included, the deck will have 54 cards
		} else {
			deck = new Card[52]; // If no jokers, the deck will have 52 cards
		}

		int cardCt = 0; // Index to place cards in the deck

		// Loop through suits (Spades, Hearts, Diamonds, Clubs)
		for (int suit = Card.SPADES; suit <= Card.CLUBS; suit++) {
			// Loop through values (1 to 13)
			for (int value = 1; value <= 13; value++) {
				// Create a card with the current suit and value and add it to the deck
				deck[cardCt++] = new Card(value, suit);
			}
		}

		// If jokers are to be included, add two joker cards to the deck
		if (includeJokers) {
			deck[52] = new Card(1, Card.JOKER); // First Joker
			deck[53] = new Card(2, Card.JOKER); // Second Joker
		}

		// Initialize cardsUsed to 0, meaning no cards have been dealt yet
		cardsUsed = 0;
	}

	// Shuffle the deck
	public void shuffle() {
		Random rand = new Random(); // Create a Random object for randomizing the deck
		// Iterate through the deck in reverse order
		for (int i = deck.length - 1; i > 0; i--) {
			// Generate a random index to swap with
			int j = rand.nextInt(i + 1);
			// Swap the current card with the card at the random index
			Card temp = deck[i];
			deck[i] = deck[j];
			deck[j] = temp;
		}
		// Reset cardsUsed to 0, as the deck is now shuffled
		cardsUsed = 0;
	}

	// Number of cards remaining in the deck
	public int cardsLeft() {
		return deck.length - cardsUsed; // Subtract cards used from the total deck size
	}

	// Deals the next card from the deck
	public Card dealCard() {
		// If no cards are left, throw an exception
		if (cardsLeft() == 0) {
			throw new IllegalStateException("No cards are left in the deck.");
		}
		return deck[cardsUsed++];
	}

	// Checks if the deck includes jokers
	public boolean hasJokers() {
		return deck.length == 54; // The deck size is 54 if jokers are included
	}
}
