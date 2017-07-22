package org.gamigo.game;

import java.util.Comparator;

import org.gamigo.config.Move;

public class MoveComparator implements Comparator<Move> {

	@Override
	public int compare(Move playerOneMove, Move playerTwoMove) {
		
		if(playerOneMove.equals(playerTwoMove)){
			return 0;
		} 
		
		switch(playerOneMove) {
		 case ROCK:
	         return (playerTwoMove == Move.SCISSORS ? 1 : -1);
	     case PAPER:
	         return (playerTwoMove == Move.ROCK ? 1 : -1);
	     case SCISSORS:
	         return (playerTwoMove == Move.PAPER ? 1 : -1);
		}
		
		return 0;
	}

}
