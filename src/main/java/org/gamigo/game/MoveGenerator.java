package org.gamigo.game;

import org.gamigo.config.Move;
import org.gamigo.config.Strategy;
import org.gamigo.exception.StrategyUndefinedException;

public interface MoveGenerator{
	
	public Move generateMove(Strategy strategy) throws StrategyUndefinedException;

}
