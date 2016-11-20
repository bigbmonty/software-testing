package FourRowSolitaire;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CardStackTest {
	CardStack CS = new CardStack();
	Card C = new Card("SPADES_SUIT", 2, 1, 1);

	@Before
	public void setUp() throws Exception {
		CS.addCard(C);
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

}
