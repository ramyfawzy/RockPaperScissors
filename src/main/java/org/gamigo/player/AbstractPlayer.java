package org.gamigo.player;

import org.gamigo.game.MoveGenerator;

public abstract class AbstractPlayer implements MoveGenerator {
	
	private int id;
//	public abstract Move generateMove(Strategy strategy);
	
	public AbstractPlayer() {
		
	}
	
	public AbstractPlayer(int id) {
		this.id = id;
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof AbstractPlayer)) {
			return false;
		}
		AbstractPlayer other = (AbstractPlayer) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}
	
	
}
