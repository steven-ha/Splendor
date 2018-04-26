package com.stevenha.splendor;




import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

	@Test
	@DisplayName("Test User Name")
	void userNameTest() {
		int nobleArrLength = 3;
		Player player = new Player("Player 1", nobleArrLength);
		assertEquals("Player 1", player.getName());
	}
	
	@Test
	@DisplayName("Add Tokens")
	void addTokensTest() {
		String name = "player 1";
		int arrLength = 3;
		
		Player player = new Player(name, arrLength);
		int[] playerTokens = player.getTokens();
		
		// array should be empty
		// add 0,1,2
		
		player.addTokens(new int[] {0, 1, 2});
		
		assertEquals(1, playerTokens[0]);
		assertEquals(1, playerTokens[1]);
		assertEquals(1, playerTokens[2]);
		
		// add array that contains invalid token index (-1)
		
		// add 1,-1,3

		player.addTokens(new int[] {1, -1, 3});
		
		assertEquals(1, playerTokens[0]);
		assertEquals(2, playerTokens[1]);
		assertEquals(1, playerTokens[2]);
		assertEquals(1, playerTokens[3]);
		assertEquals(0, playerTokens[4]);
		assertEquals(0, playerTokens[5]);
		
		// add array that contains all invalid token index (-1)
		
		// add -1,-1,-1
		
		player.addTokens(new int[] {-1, -1, -1});
		
		assertEquals(1, playerTokens[0]);
		assertEquals(2, playerTokens[1]);
		assertEquals(1, playerTokens[2]);
		assertEquals(1, playerTokens[3]);
		assertEquals(0, playerTokens[4]);
		assertEquals(0, playerTokens[5]);
		
		// add array that contains all the same index
		
		// add 3,3
		
		player.addTokens(new int[] {3, 3});
		
		assertEquals(1, playerTokens[0]);
		assertEquals(2, playerTokens[1]);
		assertEquals(1, playerTokens[2]);
		assertEquals(3, playerTokens[3]);
		assertEquals(0, playerTokens[4]);
		assertEquals(0, playerTokens[5]);
	}
	
	@Test
	@DisplayName("Remove Player's Tokens")
	void removeTokenTest() {
		String name = "player 1";
		int tileLength = 3;
		
		Player player = new Player(name, tileLength);
		
		// add tokens to tokens array
		
		int[] addedTokens = {0, 0, 0, 1, 1, 2, 2, 2, 3, 4, 5, 5};
		player.addTokens(addedTokens);
		
		int[] currentTokens = player.getTokens();
				
		// remove tokens
		
		int[] removeTokens = {2, 1, 3, 0, 0, 1};
		player.removeTokens(removeTokens);
		
		// check tokens
		
		assertEquals(1, currentTokens[0]);
		assertEquals(1, currentTokens[1]);
		assertEquals(0, currentTokens[2]);
		assertEquals(1, currentTokens[3]);
		assertEquals(1, currentTokens[4]);
		assertEquals(1, currentTokens[5]);
		
		// remove tokens
		
		removeTokens[0] = 1;
		removeTokens[1] = 1;
		removeTokens[2] = 0;
		removeTokens[3] = 1;
		removeTokens[4] = 1;
		removeTokens[5] = 1;
		
		player.removeTokens(removeTokens);
		
		// check tokens
		
		assertEquals(0, currentTokens[0]);
		assertEquals(0, currentTokens[1]);
		assertEquals(0, currentTokens[2]);
		assertEquals(0, currentTokens[3]);
		assertEquals(0, currentTokens[4]);
		assertEquals(0, currentTokens[5]);
	}
	
	@Test
	@DisplayName("Add Cards")
	void addCardTest() {
		String name = "player 1";
		int tileLength = 3;
		
		Player player = new Player(name, tileLength);
		int[] playerCards = player.getCards(); 
		int cardValue = 1;
		
		// level one diamond
		player.addCard(cardValue);
		int counter = player.getCardCounter();
		assertEquals(1, counter);
		assertNotEquals(-1, playerCards[counter - 1]);
		assertEquals(cardValue, playerCards[counter -1]);
		
		// level one emerald
		cardValue = 8;
		player.addCard(cardValue);
		counter = player.getCardCounter();
		assertEquals(2, counter);
		assertNotEquals(-1, playerCards[counter - 1]);
		assertEquals(cardValue, playerCards[counter -1]);
		
		// level one onyx
		cardValue = 23;
		player.addCard(cardValue);
		counter = player.getCardCounter();
		assertEquals(3, counter);
		assertNotEquals(-1, playerCards[counter - 1]);
		assertEquals(cardValue, playerCards[counter -1]);
		
		// level one ruby
		cardValue = 28;
		player.addCard(cardValue);
		counter = player.getCardCounter();
		assertEquals(4, counter);
		assertNotEquals(-1, playerCards[counter - 1]);
		assertEquals(cardValue, playerCards[counter -1]);
		
		// level one sapphire
		cardValue = 39;
		player.addCard(cardValue);
		counter = player.getCardCounter();
		assertEquals(5, counter);
		assertNotEquals(-1, playerCards[counter - 1]);
		assertEquals(cardValue, playerCards[counter -1]);
		
		// level two diamond
		cardValue = 40;
		player.addCard(cardValue);
		counter = player.getCardCounter();
		assertEquals(6, counter);
		assertNotEquals(-1, playerCards[counter - 1]);
		assertEquals(cardValue, playerCards[counter -1]);
		
		// level two emerald
		cardValue = 50;
		player.addCard(cardValue);
		counter = player.getCardCounter();
		assertEquals(7, counter);
		assertNotEquals(-1, playerCards[counter - 1]);
		assertEquals(cardValue, playerCards[counter -1]);
		
		// level two onyx
		cardValue = 52;
		player.addCard(cardValue);
		counter = player.getCardCounter();
		assertEquals(8, counter);
		assertNotEquals(-1, playerCards[counter - 1]);
		assertEquals(cardValue, playerCards[counter -1]);
		
		// level two ruby
		cardValue = 63;
		player.addCard(cardValue);
		counter = player.getCardCounter();
		assertEquals(9, counter);
		assertNotEquals(-1, playerCards[counter - 1]);
		assertEquals(cardValue, playerCards[counter -1]);
		
		// level two sapphire
		cardValue = 64;
		player.addCard(cardValue);
		counter = player.getCardCounter();
		assertEquals(10, counter);
		assertNotEquals(-1, playerCards[counter - 1]);
		assertEquals(cardValue, playerCards[counter -1]);
		
		// level three diamond
		cardValue = 73;
		player.addCard(cardValue);
		counter = player.getCardCounter();
		assertEquals(11, counter);
		assertNotEquals(-1, playerCards[counter - 1]);
		assertEquals(cardValue, playerCards[counter -1]);
		
		// level three emerald
		cardValue = 77;
		player.addCard(cardValue);
		counter = player.getCardCounter();
		assertEquals(12, counter);
		assertNotEquals(-1, playerCards[counter - 1]);
		assertEquals(cardValue, playerCards[counter -1]);
		
		// level three onyx
		cardValue = 79;
		player.addCard(cardValue);
		counter = player.getCardCounter();
		assertEquals(13, counter);
		assertNotEquals(-1, playerCards[counter - 1]);
		assertEquals(cardValue, playerCards[counter -1]);
		
		// level three ruby 
		cardValue = 85;
		player.addCard(cardValue);
		counter = player.getCardCounter();
		assertEquals(14, counter);
		assertNotEquals(-1, playerCards[counter - 1]);
		assertEquals(cardValue, playerCards[counter -1]);
		
		// level three sapphire
		cardValue = 86;
		player.addCard(cardValue);
		counter = player.getCardCounter();
		assertEquals(15, counter);
		assertNotEquals(-1, playerCards[counter - 1]);
		assertEquals(cardValue, playerCards[counter -1]);
		
	}
	
	@Test
	@DisplayName("Reserve Card Test")
	void reserveCardTest() {
		String name = "player 1";
		int tileLength = 3;
		
		Player player = new Player(name, tileLength);
		
		// 1st reserve card
		int card = 0;
		boolean isReserved = player.addReserveCard(card);
		assertTrue(isReserved);
		
		// 2nd reserve card
		card = 1;
		isReserved = player.addReserveCard(card);
		assertTrue(isReserved);
		
		// 3rd reserve card
		card = 2;
		isReserved = player.addReserveCard(card);
		assertTrue(isReserved);		
		
		// 4th reserve card should fail
		card = 3;
		isReserved = player.addReserveCard(card);
		assertFalse(isReserved);
		
		int[] reserved = player.getReserves();
		
		for(int i=0; i<3; i++) {
			System.out.println(reserved[i]);
		}
		
	}
	
	@Test
	@DisplayName("Remove Reserved Card")
	void removeReservedCard() {
		String name = "player 1";
		int tileLength = 3;
		
		Player player = new Player(name, tileLength);
		
		// 1st reserve card
		int card = 0;
		boolean isReserved = player.addReserveCard(card);

		card = 1;
		isReserved = player.addReserveCard(card);

		card = 2;
		isReserved = player.addReserveCard(card);
		
		int[] reserved = player.getReserves();
		
		player.removeReservedCard(0);
		
		assertEquals(-1, reserved[0]);
		assertEquals(1, reserved[1]);
		assertEquals(2, reserved[2]);
		
		player.removeReservedCard(1);
		
		assertEquals(-1, reserved[0]);
		assertEquals(-1, reserved[1]);
		assertEquals(2, reserved[2]);
		
		player.removeReservedCard(2);
		
		assertEquals(-1, reserved[0]);
		assertEquals(-1, reserved[1]);
		assertEquals(-1, reserved[2]);
		
	}
	
	@Test
	@DisplayName("Add Noble Tile")
	void addNobleTile() {
		String name = "player 1";
		int tileLength = 4;
		
		Player player = new Player(name, tileLength);
		
		int[] nobles = player.getNobles();
		
		player.addNoble(8);
		player.addNoble(3);
		player.addNoble(5);
		player.addNoble(9);
		
		assertEquals(8, nobles[0]);
		assertEquals(3, nobles[1]);
		assertEquals(5, nobles[2]);
		assertEquals(9, nobles[3]);
	}

}
