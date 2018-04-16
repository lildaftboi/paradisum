package com.paradisum.game.model.mob;

import java.awt.Color;

import com.paradisum.Paradisum;
import com.paradisum.game.model.EntityType;
import com.paradisum.game.model.Position;

/**
 * A model representing a player controlled mob.
 * @author Carlos Aviles
 */
public final class Player extends Mob {
	
	/**
	 * The last tick the player moved.
	 */
	private int lastIdleTick;
	
	/**
	 * The longest last tick the player moved.
	 */
	private int longestIdleTick;
	
	/**
	 * The in-game currency.
	 */
	private int points;
	
	/**
	 * The player's health.
	 */
	private int health;
	
	/**
	 * The flag in which a player requests to move.
	 */
	private boolean hasMoved = false;

	/**
	 * Instantiates a new player instance.
	 * @param paradisum The paradisum instance.
	 */
	public Player(Paradisum paradisum) {
		super(paradisum, EntityType.PLAYER, Position.create(100, 100), 5, 10);
		super.color = Color.GRAY;
		this.health = MobConstants.PLAYER_MAX_HEALTH;
	}
	
	/**
	 * @return The last tick the player moved.
	 */
	public int getLastIdleTick() {
		return lastIdleTick;
	}
	
	/**
	 * Sets a new last moved tick value.
	 * @param lastMovedTick The new last moved tick value.
	 */
	public void setLastIdleTick(int lastMovedTick) {
		this.lastIdleTick = lastMovedTick;
	}
	
	/**
	 * @return The last tick the player moved.
	 */
	public int getLongestIdleTick() {
		return longestIdleTick;
	}
	
	/**
	 * Sets a new last moved tick value.
	 * @param lastMovedTick The new last moved tick value.
	 */
	public void setLongestIdleick(int longestIdleTick) {
		this.longestIdleTick = longestIdleTick;
	}
	
	/**
	 * @return The in-game currency.
	 */
	public int getPoints() {
		return points;
	}
	
	/**
	 * Sets new currency amount.
	 * @param points The in-game currency.
	 */
	public void setPoints(int points) {
		this.points = points;
	}
	
	/**
	 * @return The player's health.
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * Sets a new player's health value.
	 * @param health The new player's health.
	 */
	public void setHealth(int health) {
		this.health = health;
	}
	
	public boolean hasMoved() {
		return hasMoved;
	}
	
	public void setHasMoved(boolean hasMoved) {
		this.hasMoved = hasMoved;
	}

}