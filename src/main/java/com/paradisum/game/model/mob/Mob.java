package com.paradisum.game.model.mob;

import java.awt.Graphics2D;

import com.paradisum.Paradisum;
import com.paradisum.game.model.Direction;
import com.paradisum.game.model.Entity;
import com.paradisum.game.model.EntityType;
import com.paradisum.game.model.Position;

/**
 * A model that represents either a player or npc.
 * @author Carlos Aviles
 */
public abstract class Mob extends Entity {
	
	/**
	 * The direction instance, which represents the movement direction of the entity.
	 */
	protected Direction movementDirection = Direction.NONE;
	
	/**
	 * The direction instance, which represents the other movement direction of the entity.
	 */
	protected Direction otherMovementDirection = Direction.NONE;
	
	/**
	 * The movement speed of this entity.
	 */
	protected int speed = 5;
	
	/**
	 * The mob path handler instance.
	 */
	protected final MobPathHandler pathHandler = new MobPathHandler();

	/**
	 * Instantiates a new mob instance.
	 * @param paradisum The paradisum instance.
	 * @param type The entity type instance.
	 * @param position The position instance.
	 * @param size The size of the entity.
	 * @param speed The movement speed of this entity.
	 */
	public Mob(Paradisum paradisum, EntityType type, Position position, int size, int speed) {
		super(paradisum, type, position, size);
		this.speed = speed;
	}
	
	/**
	 * @return The direction instance.
	 */
	public Direction getMovementDirection() {
		return movementDirection;
	}
	
	/**
	 * Sets a new movement direction.
	 * @param movementDirection The new direction instance.
	 */
	public void setMovementDirection(Direction movementDirection) {
		this.movementDirection = movementDirection;
	}
	
	/**
	 * @return The other direction instance.
	 */
	public Direction getOtherMovementDirection() {
		return otherMovementDirection;
	}
	
	/**
	 * Sets a new other movement direction.
	 * @param otherMovementDirection The new other direction instance.
	 */
	public void setOtherMovementDirection(Direction otherMovementDirection) {
		this.otherMovementDirection = otherMovementDirection;
	}
	
	/**
	 * @return The movement speed of this entity.
	 */
	public int getSpeed() {
		return speed;
	}
	
	/**
	 * Sets a new movement speed.
	 * @param speed The new movement speed.
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	@Override
	public void update() {
		pathHandler.processNextMovement(this);
	}
	
	@Override
	public void render(Graphics2D graphics) {
		graphics.setColor(color);
		graphics.fill(getRepresentation());
	}

}