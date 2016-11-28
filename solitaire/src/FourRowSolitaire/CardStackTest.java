package FourRowSolitaire;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CardStackTest {
	CardStack CS = new CardStack();

	@Before
	public void setUp() throws Exception {
		//Add 20 random cards to the deck
		for(int i=1; i<20; i++){
		CS.addCard(generateRandomCard());
		}
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPop() {
		
		
		Card topCard = new Card("SPADES_SUIT", 2, 1, 1);
		
		//Add Card to top of deck
		CS.addCard(topCard);
		//Check the size of the deck
		int originalSize = CS.length();
		//Return the top card
		Card returnCard = CS.pop();
		
		assert(topCard == returnCard);
		
		assert(CS.length()<originalSize);
	}
	
	@Test
	public void testPopRandomized() {
		//Run randomized test 20 times with random card
		for(int i=1; i<20; i++){
			Card topCard = generateRandomCard();
			
			//Add Card to top of deck
			CS.addCard(topCard);
			//Check the size of the deck
			int originalSize = CS.length();
			//Return the top card
			Card returnCard = CS.pop();
			
			assert(topCard == returnCard);
			
			assert(CS.length()<originalSize);
			
       }
		
		
	}
	
	public Card generateRandomCard() {
		//Card randomCard = new Card();
		int suitNumber;
		int number;
		int deckNumber = 1;
		int fullNumber = 1;

		
		Random randSuit = new Random();
		suitNumber = randSuit.nextInt(4) + 1;
		
		Random randNumber = new Random();
		number = randNumber.nextInt(13) + 1;
		
		
        String suit;
        switch (suitNumber) {
            case 1:  suit = "SPADES_SUIT";
                     break;
            case 2:  suit = "CLUBS_SUIT";
                     break;
            case 3:  suit = "DIAMONDS_SUIT";
                     break;
            case 4:  suit = "HEARTS_SUIT";
                     break;
            default: suit = "INVALID_SUIT";
                     break;
        }
        System.out.println("Generated " + "Suit: "+ suit + " Number: " + number);
		
		return new Card(suit,number,deckNumber,fullNumber);
	}
	


}
