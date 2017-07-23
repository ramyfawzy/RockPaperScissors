package org.gamigo.game.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.gamigo.config.Move;
import org.gamigo.config.Strategy;
import org.gamigo.exception.StrategyUndefinedException;
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
	public void testFixedStrategy() throws StrategyUndefinedException {
		AbstractPlayer a = new GenericPlayer(1);
		Strategy rockStrategy = Strategy.FIXED;
		rockStrategy.setMove(Move.PAPER);
		Move aMove = a.generateMove(rockStrategy);
		assertNotNull(aMove);
		assertTrue("The move must be Paper !", aMove.equals(Move.PAPER));
	}
	
	@Test
	public void testRandomStrategy() throws StrategyUndefinedException {
		AbstractPlayer a = new GenericPlayer(1);
		Move aMove = a.generateMove(Strategy.RANDOM);
		assertNotNull(aMove);
		assertTrue("Move must be Paper,Rock or Scissors !", (aMove.equals(Move.PAPER) || aMove.equals(Move.ROCK) || aMove.equals(Move.SCISSORS)));
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
		
		invokeGames(playerOne, rockPlayer);

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
		
		invokeGames(playerOne, paperPlayer);

		Assert.assertEquals(numberOfMatches, gameManager.getTies(), 0d);

		gameManager.printResult();
	}
	
	private void invokeGames(AbstractPlayer playerOne, AbstractPlayer playerTwo) throws InterruptedException {
		Callable<Void> matches = new Callable<Void>() {
			@Override
			public Void call() throws StrategyUndefinedException {
				return gameManager.startGame(playerOne, playerTwo);
			}
		};

		List<Callable<Void>> tasks = Collections.nCopies(numberOfMatches, matches);
		ExecutorService executorService = Executors.newFixedThreadPool(numberOfMatches);
		executorService.invokeAll(tasks);
	}
}
