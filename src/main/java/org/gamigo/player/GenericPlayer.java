package org.gamigo.player;

import java.security.SecureRandom;
import org.gamigo.config.Move;
import org.gamigo.config.Strategy;

public class GenericPlayer extends AbstractPlayer{

	public GenericPlayer(int i) {
		super(i);
	}

	@Override
	public Move generateMove(Strategy strategy) {
		switch(strategy) {
		 case FIXED:
			 return Move.PAPER;
	     case RANDOM:
	    	 return generateRandomMove();
		}
		
		return null;
	}

	private Move generateRandomMove() {
		 Move[] moves = Move.values();
	     SecureRandom secureRandom = new SecureRandom();
	     int index = secureRandom.nextInt(moves.length);
	     return moves[index];
	}

}
