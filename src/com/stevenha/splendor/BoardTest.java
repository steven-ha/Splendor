package com.stevenha.splendor;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {
	
	@Test
	@DisplayName("Setup Tokens - 2 Player")
	void tokenTestTwoPlayer() {
		int playerCount = 2;
		Board board = new Board(playerCount);
		
		// retrieve tokens for 2 player game
		int[] tokens = board.setupTokens(playerCount);
		
		assertEquals(4,tokens[0]);
		assertEquals(4,tokens[1]);
		assertEquals(4,tokens[2]);
		assertEquals(4,tokens[3]);
		assertEquals(4,tokens[4]);
		assertEquals(5,tokens[5]);
	}
	
	@Test
	@DisplayName("Setup Tokens - 3 Player")
	void tokenTestThreePlayer() {
		int playerCount = 3;
		Board board = new Board(playerCount);
		
		// retrieve tokens for 3 player game
		int[] tokens = board.setupTokens(playerCount);
		
		assertEquals(5,tokens[0]);
		assertEquals(5,tokens[1]);
		assertEquals(5,tokens[2]);
		assertEquals(5,tokens[3]);
		assertEquals(5,tokens[4]);
		assertEquals(5,tokens[5]);
	}
	
	@Test
	@DisplayName("Setup Tokens - 4 Player")
	void tokenTestFourPlayer() {
		int playerCount = 4;
		Board board = new Board(playerCount);
		
		// retrieve tokens for 4 player game
		int[] tokens = board.setupTokens(playerCount);
		
		assertEquals(7,tokens[0]);
		assertEquals(7,tokens[1]);
		assertEquals(7,tokens[2]);
		assertEquals(7,tokens[3]);
		assertEquals(7,tokens[4]);
		assertEquals(5,tokens[5]);
	}
	
	@Test
	@DisplayName("Setup Tiles - 2 Player")
	void tileTestTwoPlayer() {
		int playerCount = 2;
		Board board = new Board(playerCount);
		
		int[] tiles = board.setupTiles(playerCount, 10);
		assertEquals(3, tiles.length);
		assertTrue(tiles[0] >= 0 && tiles[0] <= 9);
		assertTrue(tiles[1] >= 0 && tiles[1] <= 9);
		assertTrue(tiles[2] >= 0 && tiles[2] <= 9);
	}
	
	@Test
	@DisplayName("Deal Deck One Cards")
	void dealDeckOne() {
		int playerCount = 2;
		Board board = new Board(playerCount);
		
		// deck One
		int[] deckOne = board.getDeckOne();
		int deckOneIndex = board.getDeckOneIndex();
		int[] deckOneCards = board.getDeckOneCards();
		
		boolean dealTest = board.dealCard(1, 0);
		deckOneIndex = board.getDeckOneIndex();
		
		assertTrue(dealTest);
		assertEquals(5, deckOneIndex);
		assertEquals(deckOne[4], deckOneCards[0]);
		
		board.setDeckOneIndex(39);
		dealTest = board.dealCard(1, 0);
		deckOneIndex = board.getDeckOneIndex();
		assertEquals(40, deckOneIndex);
		assertEquals(deckOne[39], deckOneCards[0]);
		
		dealTest = board.dealCard(1, 0);
		deckOneIndex = board.getDeckOneIndex();
		assertEquals(40, deckOneIndex);
		assertEquals(-1, deckOneCards[0]);
	}
	
	@Test
	@DisplayName("Deal Deck Two Cards")
	void dealDeckTwo() {
		int playerCount = 2;
		Board board = new Board(playerCount);
		
		// deck One
		int[] deckTwo = board.getDeckTwo();
		int deckTwoIndex = board.getDeckTwoIndex();
		int[] deckTwoCards = board.getDeckTwoCards();
		
		boolean dealTest = board.dealCard(2, 0);
		deckTwoIndex = board.getDeckTwoIndex();
		
		assertTrue(dealTest);
		assertEquals(5, deckTwoIndex);
		assertEquals(deckTwo[4], deckTwoCards[0]);
		
		board.setDeckTwoIndex(29);
		dealTest = board.dealCard(2, 0);
		deckTwoIndex = board.getDeckTwoIndex();
		assertEquals(30, deckTwoIndex);
		assertEquals(deckTwo[29], deckTwoCards[0]);
		
		dealTest = board.dealCard(2, 0);
		deckTwoIndex = board.getDeckTwoIndex();
		assertEquals(30, deckTwoIndex);
		assertEquals(-1, deckTwoCards[0]);
	}
	
	@Test
	@DisplayName("Deal Deck Three Cards")
	void dealDeckThree() {
		int playerCount = 2;
		Board board = new Board(playerCount);
		
		// deck One
		int[] deckThree = board.getDeckThree();
		int deckThreeIndex = board.getDeckThreeIndex();
		int[] deckThreeCards = board.getDeckThreeCards();
		
		boolean dealTest = board.dealCard(3, 0);
		deckThreeIndex = board.getDeckThreeIndex();
		
		assertTrue(dealTest);
		assertEquals(5, deckThreeIndex);
		assertEquals(deckThree[4], deckThreeCards[0]);
		
		board.setDeckThreeIndex(19);
		dealTest = board.dealCard(3, 0);
		deckThreeIndex = board.getDeckThreeIndex();
		assertEquals(20, deckThreeIndex);
		assertEquals(deckThree[19], deckThreeCards[0]);
		
		dealTest = board.dealCard(3, 0);
		deckThreeIndex = board.getDeckThreeIndex();
		assertEquals(20, deckThreeIndex);
		assertEquals(-1, deckThreeCards[0]);
	}
	
	@Test
	@DisplayName("Remove Token")
	void removeTokenTest() {
		int playerCount = 2;
		Board board = new Board(playerCount);
		
		// regulars tokens have 4 tokens at the start of the game.
		int token = board.removeToken(0);
		
		assertEquals(0, token);
		
		token = board.removeToken(0);
		assertEquals(0, token);
		
		token = board.removeToken(0);
		assertEquals(0, token);
		
		token = board.removeToken(0);
		assertEquals(0, token);
		
		// now the token count at index 0 is 0
		token = board.removeToken(0);
		assertEquals(-1, token);
	}
	
	@Test
	@DisplayName("Pick Two Tokens")
	void twoTokensTest() {
		int playerCount = 4;
		Board board = new Board(playerCount);
				
		int tokenIndex = 0;
		int[] tokens = board.removeTwoCoins(tokenIndex);
		
		//token stack contains 2 or more
		assertEquals(2, tokens.length);
		assertEquals(tokenIndex, tokens[0]);
		assertEquals(tokenIndex, tokens[1]);
		
		//token stack contains 1
		tokens = board.removeTwoCoins(tokenIndex);
		tokens = board.removeTwoCoins(tokenIndex);
		tokens = board.removeTwoCoins(tokenIndex);
		
		assertEquals(2, tokens.length);
		assertEquals(tokenIndex, tokens[0]);
		assertEquals(-1, tokens[1]);
		
		//token stack contains nothing
		tokens = board.removeTwoCoins(tokenIndex);
		
		assertEquals(2, tokens.length);
		assertEquals(-1, tokens[0]);
		assertEquals(-1, tokens[1]);
		
	}
	
	@Test
	@DisplayName("Pick Three Tokens")
	void threeTokenTest() {
		int playerCount = 3;
		Board board = new Board(playerCount);
		// different coins, all available
		// 1,2,3
		int[] indices = {1, 2, 3};
		int[] tokens = board.removeThreeCoins(indices);
		
		assertEquals(3, tokens.length);
		assertEquals(indices[0], tokens[0]);
		assertEquals(indices[1], tokens[1]);
		assertEquals(indices[2], tokens[2]);
		
		// all the same coins
		// 1,1,1
		
		indices = new int[]{1,1,1};
		tokens = board.removeThreeCoins(indices);
		
		assertEquals(3, tokens.length);
		assertEquals(indices[0], tokens[0]);
		assertEquals(-1, tokens[1]);
		assertEquals(-1, tokens[2]);
		
		// pair of the same coin
		// 1,1,2		
		indices = new int[]{1,1,2};
		tokens = board.removeThreeCoins(indices);
		
		assertEquals(3, tokens.length);
		assertEquals(indices[0], tokens[0]);
		assertEquals(-1, tokens[1]);
		assertEquals(indices[2], tokens[2]);
		
		// pair of the same coin
		// 2,1,1
		
		indices = new int[]{2,1,1};
		tokens = board.removeThreeCoins(indices);
		
		assertEquals(3, tokens.length);
		assertEquals(indices[0], tokens[0]);
		assertEquals(indices[1], tokens[1]);
		assertEquals(-1, tokens[2]);
		
		// pair of the same coin
		// 1,2,1
		
		indices = new int[]{1,2,1};
		tokens = board.removeThreeCoins(indices);
		
		assertEquals(3, tokens.length);
		assertEquals(indices[0], tokens[0]);
		assertEquals(indices[1], tokens[1]);
		assertEquals(-1, tokens[2]);
		
		// coin not available index 1
		// 1,2,3
		
		indices = new int[]{1,2,3};
		tokens = board.removeThreeCoins(indices);
		
		assertEquals(3, tokens.length);
		assertEquals(-1, tokens[0]);
		assertEquals(indices[1], tokens[1]);
		assertEquals(indices[2], tokens[2]);
		
		// coin not available index 2
		// 1,2,3
		
		indices = new int[]{1,2,3};
		tokens = board.removeThreeCoins(indices);
		
		assertEquals(3, tokens.length);
		assertEquals(-1, tokens[0]);
		assertEquals(-1, tokens[1]);
		assertEquals(indices[2], tokens[2]);
		
	}
	
	@Test
	@DisplayName("Remove Nobile")
	void removeNobile() {
		int playerCount = 3;
		Board board = new Board(playerCount);
		
		int tile = board.removeNobile(3);
		assertNotEquals(-1, tile);
		
		tile = board.removeNobile(3);
		assertEquals(-1, tile);
	}
	
	@Test
	@DisplayName("Remove Card")
	void removeCardTest() {
		int playerCount = 4;
		Board board = new Board(playerCount);
		
		// card in deck one
		int card = board.getCard(1, 3);
		assertNotEquals(-1, card);
		assertTrue(card>=0 && card<=39);
		
		// card in deck two
		card = board.getCard(2, 0);
		assertNotEquals(-1, card);
		assertTrue(card>=40 && card<=69);
		
		// card in deck two
		card = board.getCard(3, 0);
		assertNotEquals(-1, card);
		assertTrue(card>=70 && card<=89);
	}
	
	@Test
	@DisplayName("Return Token")
	void returnTokenTest() {
		int playerCount = 2;
		Board board = new Board(playerCount);
		
		int[] removedTokens = new int[6];
		
		removedTokens[0] = board.removeToken(0);
		removedTokens[1] = board.removeToken(1);
		removedTokens[2] = board.removeToken(2);
		removedTokens[3] = board.removeToken(3);
		removedTokens[4] = board.removeToken(4);
		removedTokens[5] = board.removeToken(5);
		
		int[] beforeTokens = Arrays.copyOf(board.getTokens(), 6);
		
		 for(int i=0; i<6; i++) {
			 board.returnToken(i);
		 }
		 
		 int[] afterTokens = Arrays.copyOf(board.getTokens(), 6);
		 
		 assertTrue(beforeTokens[0] == 3 && afterTokens[0] == 4);
		 assertTrue(beforeTokens[1] == 3 && afterTokens[1] == 4);
		 assertTrue(beforeTokens[2] == 3 && afterTokens[2] == 4);
		 assertTrue(beforeTokens[3] == 3 && afterTokens[3] == 4);
		 assertTrue(beforeTokens[4] == 3 && afterTokens[4] == 4);
		 assertTrue(beforeTokens[5] == 4 && afterTokens[5] == 5);
		

	}
	
	@Test
	@DisplayName("Shuffle Array")
	void shuffleTest() {
		int arraySize = 10;
		int[] array = Board.shuffle(arraySize);
		
		int minValue = 0;
		int maxValue = arraySize - 1;

		assertTrue(array[0]>=minValue && array[0]<=maxValue);
		assertTrue(array[1]>=minValue && array[1]<=maxValue);
		assertTrue(array[2]>=minValue && array[2]<=maxValue);
		assertTrue(array[3]>=minValue && array[3]<=maxValue);
		assertTrue(array[4]>=minValue && array[4]<=maxValue);
		assertTrue(array[5]>=minValue && array[5]<=maxValue);
		assertTrue(array[6]>=minValue && array[6]<=maxValue);
		assertTrue(array[7]>=minValue && array[7]<=maxValue);
		assertTrue(array[8]>=minValue && array[8]<=maxValue);
		assertTrue(array[9]>=minValue && array[9]<=maxValue);
	}

}
