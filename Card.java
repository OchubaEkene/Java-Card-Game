// Name: Ekene Alexander Ochuba
// Student No: 3155904

package griffith;

public class Card {
	public static final int SPADES = 0; // Codes for suits
	public static final int HEARTS = 1;
	public static final int DIAMONDS = 2;
	public static final int CLUBS = 3;
	public static final int JOKER = 4; // Joker suit

	public static final int ACE = 1; // Codes for non-numeric cards
	public static final int JACK = 11;
	public static final int QUEEN = 12;
	public static final int KING = 13;

	private final int suit;
	private final int value;

	public Card() {
		this.value = 1; // Default Joker card
		this.suit = JOKER;
	}

//    Logic checking suits
	public Card(int value, int suit) {
//    	 Check if the suit is within the valid range (SPADES, HEARTS, DIAMONDS, CLUBS, or JOKER)
		if (suit < SPADES || suit > JOKER) {
			// If the suit is invalid, throw an IllegalArgumentException with a detailed
			// error message
			throw new IllegalArgumentException("Invalid suit: " + suit);
		}

		// Check if the value is valid for a non-joker card (should be between 1 and 13)
		if (suit != JOKER && (value < 1 || value > 13)) {
			// If the value is invalid for a non-joker card, throw an
			// IllegalArgumentException with a detailed error message
			throw new IllegalArgumentException("Invalid value: " + value);
		}

		// Assign the valid value to the value field of the Card object
		this.value = value;

		// Assign the valid suit to the suit field of the Card object
		this.suit = suit;
	}

	// returns the suit of the card
	public int getSuit() {
		return suit;
	}

	// Returns the value of the card
	public int getValue() {
		return value;
	}

	// to returns suit as a String
	public String getSuitAsString() {
		// Use a traditional switch statement to return the corresponding suit name
		switch (suit) {
		case SPADES:
			return "Spades"; // If the suit is SPADES, return "Spades"
		case HEARTS:
			return "Hearts"; // If the suit is HEARTS, return "Hearts"
		case DIAMONDS:
			return "Diamonds"; // If the suit is DIAMONDS, return "Diamonds"
		case CLUBS:
			return "Clubs"; // If the suit is CLUBS, return "Clubs"
		default:
			return "Joker"; // If none of the above, return "Joker"
		}
	}

	// to Returns value as a String
	public String getValueAsString() {
		// Check if the card is a Joker
		if (suit == JOKER) {
			// If it is a Joker, simply return the value (Jokers are typically represented
			// by their number)
			return "" + value; // Concatenate value as a string
		} else {
			// If it's not a Joker, use a switch statement to return the corresponding name
			// for face cards
			switch (value) {
			case 1:
				// If the value is 1, it's an Ace
				return "Ace";
			case 11:
				// If the value is 11, it's a Jack
				return "Jack";
			case 12:
				// If the value is 12, it's a Queen
				return "Queen";
			case 13:
				// If the value is 13, it's a King
				return "King";
			default:
				// For all other values (2-10), return the value as a string
				return String.valueOf(value);
			}
		}
	}

	// to Returns the card as a String
	@Override
	public String toString() {
		// Check if the card is a Joker
		if (suit == JOKER) {
			// If the value of the Joker is 1, return "Joker"
			// If the value is not 1, return "Joker #" followed by the value
			return value == 1 ? "Joker" : "Joker #" + value;
		}
		// If the card is not a Joker, return a string representation of the card
		// Concatenate the value of the card (using getValueAsString()) with the suit
		// (using getSuitAsString())
		return getValueAsString() + " of " + getSuitAsString();
	}
}
