package org.gamigo.player;

import org.gamigo.config.Move;
import org.gamigo.config.Strategy;
import org.gamigo.exception.StrategyUndefinedException;

public class GenericPlayer extends AbstractPlayer{

	public GenericPlayer(int i) {
		super(i);
	}

	@Override
	public Move generateMove(Strategy strategy) throws StrategyUndefinedException {
		switch(strategy) {
		 case FIXED:
			 return strategy.getMove();
	     case RANDOM:
	    	 return strategy.generateRandomMove();
	     default:
	    	 throw new StrategyUndefinedException();
		}
		
	}
}
