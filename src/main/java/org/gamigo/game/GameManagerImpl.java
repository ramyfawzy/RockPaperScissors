package org.gamigo.game;

import java.util.concurrent.atomic.AtomicInteger;

import org.gamigo.config.Move;
import org.gamigo.config.Strategy;
import org.gamigo.exception.StrategyUndefinedException;
import org.gamigo.player.AbstractPlayer;
import org.gamigo.player.GenericPlayer;

public class GameManagerImpl implements GameManager {
	
	private AtomicInteger playerOneWins = new AtomicInteger(0);
	private AtomicInteger playerTwoWins = new AtomicInteger(0);
	private AtomicInteger ties = new AtomicInteger(0);
	private int numberOfMatches;
	
	private AbstractPlayer playerOne,playerTwo;
	
	public static GameManagerImpl getInstance(int numberOfMatches) {
		return new GameManagerImpl(numberOfMatches);
	}
	
	private GameManagerImpl(int numberOfMatches) {
		this.numberOfMatches = numberOfMatches;
		
	}


	@Override
	public Void startGame() throws StrategyUndefinedException {
		playerOne = new GenericPlayer(1);
		playerTwo = new GenericPlayer(2);
		AbstractPlayer winnerPlayer = determineWinner(playerOne, playerTwo);
		saveResult(winnerPlayer);
		return null;

	}
	
	@Override
	public Void startGame(AbstractPlayer playerOne, AbstractPlayer playerTwo) throws StrategyUndefinedException {
		
		AbstractPlayer winnerPlayer = determineWinner(playerOne, playerTwo);
		saveResult(winnerPlayer);
		return null;

	}
	
	@Override
	public AbstractPlayer determineWinner(AbstractPlayer playerOne, AbstractPlayer playerTwo) throws StrategyUndefinedException {
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
		Move playerOneMove = playerOne.generateMove(Strategy.FIXED);
		Move playerTwoMove = playerTwo.generateMove(Strategy.RANDOM);
	    int compareMoves =  new MoveComparator().compare(playerOneMove, playerTwoMove);
	    AbstractPlayer winnerPlayer = null;
    	switch (compareMoves) {
        case 0: 
            break;
        case 1: 
            winnerPlayer = playerOne;
            break;
        case -1: 
            winnerPlayer = playerTwo;
            break;
        }
    	return winnerPlayer;
	}
	

	public int getPlayerOneWins() {
		return playerOneWins.get();
	}

	public int getPlayerTwoWins() {
		return playerTwoWins.get();
	}

	public int getTies() {
		return ties.get();
	}

	/**
	 * @param numberOfMatches the numberOfMatches to set
	 */
	public void setNumberOfMatches(int numberOfMatches) {
		this.numberOfMatches = numberOfMatches;
	}
	
	@Override
	public void saveResult(AbstractPlayer winnerPlayer) {
		
		if(winnerPlayer !=null && winnerPlayer.equals(playerOne)) {
			playerOneWins.incrementAndGet();
			
		} else if (winnerPlayer !=null && winnerPlayer.equals(playerTwo)) {
			playerTwoWins.incrementAndGet();
			
		} else {
			ties.incrementAndGet();
		}
	}

	@Override
	public void printResult() {
		System.out.printf ("Player A wins %d of %d games %n", playerOneWins.get(), numberOfMatches); 
		System.out.printf ("Player B wins %d of %d games %n", playerTwoWins.get(), numberOfMatches);
		System.out.printf ("Tie %d of %d games %n", ties.get(), numberOfMatches);
	}
	
	

}
