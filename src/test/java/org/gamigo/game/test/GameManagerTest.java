package org.gamigo.game.test;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.gamigo.config.Move;
import org.gamigo.config.Strategy;
import org.gamigo.game.GameManagerImpl;
import org.gamigo.player.AbstractPlayer;
import org.gamigo.player.GenericPlayer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GameManagerTest {
	
	private GameManagerImpl gameManager;
	private int numberOfMatches = 10000;
	
	 @Before
	 public void prepareGameManager() {
		 gameManager = GameManagerImpl.getInstance(numberOfMatches);
	    }
	 
	 @After
	 public void destroyGameManager() {
		 gameManager = null;
	    }
	 
	 @Test
	 public void testPlayerOneAlwaysWins() throws InterruptedException, ExecutionException {
		 
		 AbstractPlayer rockPlayer = new AbstractPlayer(2) {

			@Override
			public Move generateMove(Strategy strategy) {
				return Move.ROCK;
			}
		};
		
		AbstractPlayer playerOne = new GenericPlayer(1);
		
		List<Callable<Void>> matches = Collections.nCopies(numberOfMatches, () -> {
			return gameManager.startGame(playerOne, rockPlayer);
		});
		ExecutorService executorService = Executors.newFixedThreadPool(numberOfMatches);
		executorService.invokeAll(matches);
		
		Assert.assertEquals(numberOfMatches, gameManager.getPlayerOneWins(), 0d);

		gameManager.printResult();
		 
	 }
	 
	 @Test
	 public void testAlwaysTieGame() throws InterruptedException, ExecutionException {
		 
		 AbstractPlayer paperPlayer = new AbstractPlayer() {

				@Override
				public Move generateMove(Strategy strategy) {
					return Move.PAPER;
				}
			};
			
			AbstractPlayer playerOne = new GenericPlayer(1);
			
			Callable<Void> matches = new Callable<Void>() {
				@Override
				public Void call() {
					return gameManager.startGame(playerOne, paperPlayer);
				}
			};
			
			List<Callable<Void>> tasks = Collections.nCopies(numberOfMatches, matches);
			ExecutorService executorService = Executors.newFixedThreadPool(numberOfMatches);
			executorService.invokeAll(tasks);
			
			Assert.assertEquals(numberOfMatches, gameManager.getTies(), 0d);

			gameManager.printResult();
	 }
}
