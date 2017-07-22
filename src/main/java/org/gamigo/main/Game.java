package org.gamigo.main;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.gamigo.game.GameManager;
import org.gamigo.game.GameManagerImpl;

public class Game {
	static int numberOfMatches = 10000;
	public static void main(String[] args) throws InterruptedException {
		
		GameManager gameManager = GameManagerImpl.getInstance(numberOfMatches);

		List<Callable<Void>> tasks = Collections.nCopies(numberOfMatches, () -> {
			return gameManager.startGame();
		});
		ExecutorService executorService =  Executors.newFixedThreadPool(numberOfMatches);
		executorService.invokeAll(tasks);
		gameManager.printResult();
		shutdown(executorService);
	}
	
	private static void shutdown(ExecutorService executorService) {
		try {
		    System.out.println("Game is shutting down ...");
		    executorService.shutdown();
		    executorService.awaitTermination(5, TimeUnit.SECONDS);
		}
		catch (InterruptedException e) {
		    System.err.println("tasks interrupted");
		}
		finally {
		    if (!executorService.isTerminated()) {
		        System.err.println("cancel non-finished tasks");
		    }
		    executorService.shutdownNow();
		    System.out.println("shutdown finished");
		}
	}
}
