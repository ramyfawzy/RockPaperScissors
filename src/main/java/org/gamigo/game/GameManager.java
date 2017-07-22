package org.gamigo.game;

import org.gamigo.player.AbstractPlayer;

public interface GameManager {
	
	public Void startGame();
	
	public AbstractPlayer determineWinner(AbstractPlayer playerOne, AbstractPlayer playerTwo);
	
	public void saveResult(AbstractPlayer winnerPlayer);
	
	public void printResult();

	public Void startGame(AbstractPlayer playerOne, AbstractPlayer playerTwo);

}
