package org.gamigo.config;

import java.security.SecureRandom;

public enum Strategy {
	
	FIXED, RANDOM;
	
	private Move move;
	
	Strategy() {
		
	}
	
	Strategy(Move move) {
		this.move = move;
	}

	/**
	 * @return the move
	 */
	public Move getMove() {
		return move;
	}

	/**
	 * @param move the move to set
	 */
	public void setMove(Move move) {
		this.move = move;
	}
	
	public Move generateRandomMove() {
		 Move[] moves = Move.values();
	     SecureRandom secureRandom = new SecureRandom();
	     int index = secureRandom.nextInt(moves.length);
	     return moves[index];
	}

}
