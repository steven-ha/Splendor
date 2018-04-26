package com.stevenha.splendor;

import java.util.concurrent.ThreadLocalRandom;

public class Board {
	private int playerCount;
	
	private int[] tokens;
	private int[] deckOneCards = {0, 0, 0, 0};
	private int[] deckOne;
	private int[] deckTwoCards = {0, 0, 0, 0};
	private int[] deckTwo;
	private int[] deckThreeCards = {0, 0, 0, 0};
	private int[] deckThree;
	private int[] tiles;
	
	private int deckOneIndex, deckTwoIndex, deckThreeIndex;
	
	public final static int MAX_TILES = 10;
	public final static int MAX_LEVEL_ONE_DECK = 40;
	public final static int MAX_LEVEL_TWO_DECK = 30;
	public final static int MAX_LEVEL_THREE_DECK = 20;
	
	public Board(int playerCount) {
		
		// setup the tiles
		tiles = setupTiles(playerCount, MAX_TILES);
		
		// setup the decks
		deckOne = shuffle(MAX_LEVEL_ONE_DECK);
		deckTwo = shuffle(MAX_LEVEL_TWO_DECK, MAX_LEVEL_ONE_DECK);
		deckThree = shuffle(MAX_LEVEL_THREE_DECK, MAX_LEVEL_TWO_DECK + MAX_LEVEL_ONE_DECK);
		deckOneIndex = 0;
		deckTwoIndex = 0;
		deckThreeIndex = 0;
		
		// setup the tokens
		tokens = setupTokens(playerCount);
		
		// deal the cards
		for(int i=0; i<4; i++) {
			boolean deckOneDeal = dealCard(1, i);
			boolean deckTwoDeal = dealCard(2, i);
			boolean deckThreeDeal = dealCard(3, i);
		}
	}
	
	// return the appropriate numbers of tokens that will be used in the game based on player count
	public int[] setupTokens(int playerCount) {
		// create array of size 6
		int[] tokens = new int[6];
		
		// determine the number of coins to create based on player count
		if(playerCount == 2 || playerCount == 3) {
			for(int i=0; i<5; i++) {
				tokens[i] = playerCount + 2;
			}
		} else if (playerCount == 4) {
			for(int j=0; j<5; j++) {
				tokens[j] = 7;
			}
		}
		
		// the last token is the gold token, always initialized to 5
		tokens[5] = 5;
		
		return tokens;
	}
	
	// return randomly selected tokens based on player count
	public int[] setupTiles(int playerCount, int maxTileCount) {
		// create the random array of size maxTileCount
		int[] random = shuffle(maxTileCount);
		
		// create the tiles array
		int numberTiles = playerCount + 1;
		int[] tiles = new int[numberTiles];
		
		// fill the tiles array with the randomly generated values
		for(int i=0; i<numberTiles; i++) {
			tiles[i] = random[i];
		}
				
		return tiles;
	}
	
	public int removeNobile(int index) {
		// get the tile at index
		int tile = tiles[index];
		
		// replace the tile at index with -1 if it's >= 0
		if(tile != -1) {
			tiles[index] = -1;
		}
		
		return tile;
	}
	
	public int getCard(int deck, int position) {
		// create card to return, does not replace the removed card
		int card = -1;
		
		// determine the specified deck and check if a card can be removed
		switch(deck) {
		case 1:
			card = deckOneCards[position];
			break;
		case 2:
			card = deckTwoCards[position];
			break;
		case 3:
			card = deckThreeCards[position];
			break;
		default:
			break;
		}
		
		return card;
	}
	
	public boolean dealCard(int deck, int position) {
		// set isDealt to false
		boolean isDealt = false;
		
		// determine the specified deck and check if a card can be dealt
		switch(deck) {
		case 1:
			if(deckOneIndex != (MAX_LEVEL_ONE_DECK)) {
				deckOneCards[position] = deckOne[deckOneIndex];
				deckOneIndex += 1;
				isDealt = true;
			} else {
				deckOneCards[position] = -1;
			}
			break;
		case 2:
			if(deckTwoIndex != (MAX_LEVEL_TWO_DECK)) {
				deckTwoCards[position] = deckTwo[deckTwoIndex];
				deckTwoIndex += 1;
				isDealt = true;
			} else {
				deckTwoCards[position] = -1;
			}
			break;
		case 3:
			if(deckThreeIndex != (MAX_LEVEL_THREE_DECK)) {
				deckThreeCards[position] = deckThree[deckThreeIndex];
				deckThreeIndex += 1;
				isDealt = true;
			} else {
				deckThreeCards[position] = -1;
			}
			break;
		default:
			break;
		}
		
		return isDealt;
	}
	
	public int[] removeTwoCoins(int index) {
		// create array
		int[] retTokens = new int[2];
		
		retTokens[0] = removeToken(index);
		retTokens[1] = removeToken(index);
		
		return retTokens;
	}
	
	public int[] removeThreeCoins(int[] indices) {
		int[] retTokens = new int[3];
		
		retTokens[0] = removeToken(indices[0]);
		
		if(indices[1] != indices[0]) {
			retTokens[1] = removeToken(indices[1]);
		} else {
			retTokens[1] = -1;
		}
		
		if(indices[2] != indices[1] && indices[2] != indices[0]) {
			retTokens[2] = removeToken(indices[2]);
		} else {
			retTokens[2] = -1;
		} 
		
		return retTokens;
	}
	
	// return a randomly generated array of size itemCount
	public static int[] shuffle(int itemCount) {
		//create array
		int[] array = new int[itemCount];
		
		//fill array, starting at 0 incrementing by 1
		for(int i=0; i<itemCount; i++) {
			array[i] = i;
		}
		
		for(int j=itemCount-1; j>0; j--) {
			// generate random number between 0 and index - 1
			int random = ThreadLocalRandom.current().nextInt(0, j);
			
			int temp = array[random];
			
			// swap values at random and j
			array[random] = array[j];
			array[j] = temp;
		}
		
		return array;
	}
	
	// return a randomly generated array of size itemCount, offset by certain value
	public static int[] shuffle(int itemCount, int offset) {
		//create array
		int[] array = new int[itemCount];
		
		//fill array, starting at 0 incrementing by 1
		for(int i=0; i<itemCount; i++) {
			array[i] = i + offset;
		}
		
		for(int j=itemCount-1; j>0; j--) {
			// generate random number between 0 and index - 1
			int random = ThreadLocalRandom.current().nextInt(0, j);
			
			int temp = array[random];
			
			// swap values at random and j
			array[random] = array[j];
			array[j] = temp;
		}
		
		return array;
	}
	
	public int[] getDeckOne() {
		return deckOne;
	}
	
	public int[] getDeckTwo() {
		return deckTwo;
	}
	
	public int[] getDeckThree() {
		return deckThree;
	}
	
	public int getDeckOneIndex() {
		return deckOneIndex;
	}
	
	public int getDeckTwoIndex() {
		return deckTwoIndex;
	}
	
	public int getDeckThreeIndex() {
		return deckThreeIndex;
	}
	
	public int[] getDeckOneCards() {
		return deckOneCards;
	}
	
	public int[] getDeckTwoCards() {
		return deckTwoCards;
	}
	
	public int[] getDeckThreeCards() {
		return deckThreeCards;
	}
	
	protected void setDeckOneIndex(int index) {
		deckOneIndex = index;
	}
	
	protected void setDeckTwoIndex(int index) {
		deckTwoIndex = index;
	}
	
	protected void setDeckThreeIndex(int index) {
		deckThreeIndex = index;
	}
	
	protected int[] getTokens() {
		return tokens;
	}
	
	public int removeToken(int index) {
		if(this.tokens[index] > 0) {
			this.tokens[index] -= 1;
			return index;
		} else {
			return -1;
		}
	}
		
	public void returnToken(int index) {
		tokens[index] += 1;
	}
	
}
