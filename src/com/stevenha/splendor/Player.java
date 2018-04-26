package com.stevenha.splendor;

public class Player {

	private String name;
	private int[] tokens = {0, 0, 0, 0, 0, 0};
	private int[] cards = new int[90];
	private int[] cardCount = {0, 0, 0, 0, 0};
	private int[] reserve = {-1, -1, -1};
	private int[] nobles;
	private int nobleCount;
	private int points = 0;
	private int cardCounter = 0;
	
	public Player(String name, int noblesArrLength) {
		this.name = name;
		this.nobles = new int[noblesArrLength];
		
		// fill nobles with -1
		for(int i=0; i<noblesArrLength; i++) {
			this.nobles[i] = -1;
		}
		
		// fill cards at the start with -1
		for(int j=0; j<90; j++) {
			this.cards[j] = -1;
		}
	}
	
	public void addTokens(int[] tokens) {
		// check each index value in the array
		for(int i=0; i<tokens.length; i++) {
			// get the index
			int index = tokens[i];
			
			// add to the player's tokens if the index value is valid
			if(index != -1) {
				this.tokens[index] += 1;
			}
		}
	}
	
	public void removeTokens(int[] removeTokens) {
		for(int i=0; i<6; i++) {
			this.tokens[i] -= removeTokens[i];
		}
	}
	
	public void addCard(int card) {
		// increment the type of card that is being added
		if(isBetween(card, 0, 7)) {
			cardCount[0] += 1;
		} else if(isBetween(card, 8, 15)) {
			cardCount[1] += 1;
		} else if(isBetween(card, 16, 23)) {
			cardCount[2] += 1;
		} else if(isBetween(card, 24, 31)) {
			cardCount[3] += 1;
		} else if(isBetween(card, 32, 39)) {
			cardCount[4] += 1;
		} else if(isBetween(card, 40, 45)) {
			cardCount[0] += 1;
		} else if(isBetween(card, 46, 51)) {
			cardCount[1] += 1;
		} else if(isBetween(card, 52, 57)) {
			cardCount[2] += 1;
		} else if(isBetween(card, 58, 63)) {
			cardCount[3] += 1;
		} else if(isBetween(card, 64, 69)) {
			cardCount[4] += 1;
		} else if(isBetween(card, 70, 73)) {
			cardCount[0] += 1;
		} else if(isBetween(card, 74, 77)) {
			cardCount[1] += 1;
		} else if(isBetween(card, 78, 81)) {
			cardCount[2] += 1;
		} else if(isBetween(card, 82, 85)) {
			cardCount[3] += 1;
		} else if(isBetween(card, 86, 89)) {
			cardCount[4] += 1;
		} 
		
		// save the card
		cards[cardCounter] = card;
		cardCounter++;
	}
	
	public boolean addReserveCard(int card) {
		boolean isPlaced = false;
		boolean isFull = false;
		boolean isReserved;
		int counter = 0; // used to make sure that the program exits the while loop after looking at 3 items in the array
		
		// while isPlaced or isFull is false, try to reserved the card
		while(isPlaced == false && isFull == false) {
			// check if the reserve array has room for the card and add it if there is room, if not increment the counter
			if(reserve[counter] == -1) {
				reserve[counter] = card;
				isPlaced = true;
			} else {
				counter++;
			}
			
			// if 3 slots have been looked at, set isFull to exit the loop
			if(counter > 2) {
				isFull = true;
			}
		}
		
		// determine if the card was reserved or not
		if(isPlaced) {
			isReserved = true;
		} else {
			isReserved = false;
		}
		
		return isReserved;
	}
	
	public void removeReservedCard(int index) {
		reserve[index] = -1;
	}
	
	public void addNoble(int noble) {
		boolean isPlaced = false;
		int counter = 0;
		
		while(isPlaced == false)	{
			if(nobles[counter] == -1) {
				nobles[counter] = noble;
				isPlaced = true;
			} else {
				counter++;
			}
		}
	}
	
	public String getName() {
		return this.name;
	}
	
	public int[] getTokens() {
		return tokens;
	}
	
	public int[] getCards() {
		return cards;
	}
	
	public int getCardCounter() {
		return cardCounter;
	}
	
	public int[] getReserves() {
		return reserve;
	}
	
	public int[] getNobles() {
		return nobles;
	}
	
	public void addPoints(int points) {
		this.points += points;
	}
	
	public int getPoints() {
		return points;
	}
	
	public int getTokenCount(int index) {
		return tokens[index];
	}
	
	public static boolean isBetween(int target, int min, int max) {
		return target >= min && target <= max;
	}

}
