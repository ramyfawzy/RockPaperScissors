package org.gamigo.game;

import org.gamigo.exception.StrategyUndefinedException;
import org.gamigo.player.AbstractPlayer;

public interface GameManager {
	
	public Void startGame() throws StrategyUndefinedException;
	
	public AbstractPlayer determineWinner(AbstractPlayer playerOne, AbstractPlayer playerTwo) throws StrategyUndefinedException;
	
	public void saveResult(AbstractPlayer winnerPlayer);
	
	public void printResult();

	public Void startGame(AbstractPlayer playerOne, AbstractPlayer playerTwo) throws StrategyUndefinedException;

}
