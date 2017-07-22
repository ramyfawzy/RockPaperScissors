package org.gamigo.game;

import org.gamigo.config.Move;
import org.gamigo.config.Strategy;

public interface MoveGenerator{
	
	public Move generateMove(Strategy strategy);

}
