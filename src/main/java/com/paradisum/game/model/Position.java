package com.paradisum.game.model;

import com.google.common.base.MoreObjects;

/**
 * Represents a single point on the world map.
 * @author Carlos Aviles
 */
public final class Position {
	
	/**
	 * The x coordinate.
	 */
	private final int x;
	
	/**
	 * The y coordinate.
	 */
	private final int y;
	
	/**
	 * Instantiates a new position instance.
	 * @param x The x coordinate.
	 * @param y The y coordinate.
	 */
	private Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Creates a new position instance.
	 * @param x The requested x position.
	 * @param y The requested y position.
	 * @return The new position instance.
	 */
	public static Position create(int x, int y) {
		return new Position(x, y);
	}
	
	/**
	 * @return The x coordinate.
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * @return The y coordinate.
	 */
	public int getY() {
		return y;
	}
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("x", x).add("y", y).toString();
	}
	
	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object instanceof Position) {
			Position other = (Position) object;
			return getX() == other.getX() && getY() == other.getY();
		}
		return false;
	}

}