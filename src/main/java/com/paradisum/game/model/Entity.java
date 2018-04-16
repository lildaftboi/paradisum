package com.paradisum.game.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.paradisum.Paradisum;
import com.paradisum.game.Serviceable;

/**
 * A model that represents anything that can be interacted with in the game world.
 * @author Carlos Aviles
 */
public abstract class Entity implements Serviceable {
	
	/**
	 * The paradisum instance, to have an instance of the application.
	 */
	protected final Paradisum paradisum;
	
	/**
	 * The type instance, to represent the type of entity.
	 */
	protected final EntityType type;
	
	/**
	 * The position instance, for the coordinates of the entity.
	 */
	protected Position position;
	
	/**
	 * The position instance, the previous coordinates of the entity.
	 */
	protected Position previousPosition;
	
	/**
	 * The size of this entity.
	 */
	protected int size;
	
	/**
	 * The color instance, which represents the color of this mob.
	 */
	protected Color color = new Color(0, 0, 0);
	
	/**
	 * Instantiates a new entity instance.
	 * @param paradisum The paradisum instance.
	 * @param type The type instance.
	 * @param position The position instance.
	 * @param size The size of the entity.
	 */
	public Entity(Paradisum paradisum, EntityType type, Position position, int size) {
		this.paradisum = paradisum;
		this.type = type;
		this.position = position;
		this.size = size;
		this.previousPosition = position;
	}
	
	/**
	 * @return The type instance.
	 */
	public final EntityType getType() {
		return type;
	}
	
	/**
	 * @return The position instance.
	 */
	public Position getPosition() {
		return position;
	}
	
	/**
	 * Sets a new position instance.
	 * @param position The new position instance.
	 */
	public void setPosition(Position position) {
		this.position = position;
	}
	
	public Position getPreviousPosition() {
		return previousPosition;
	}
	
	public void setPreviousPosition(Position previousPosition) {
		this.previousPosition = previousPosition;
	}
	
	/**
	 * @return The size of the entity.
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Sets a new entity size.
	 * @param size The new entity size.
	 */
	public void setSize(int size) {
		this.size = size;
	}
	
	/**
	 * @return The color instance.
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * @return The rectangle instance, which represents the bounds of this entity.
	 */
	public final Rectangle getRepresentation() {
		return new Rectangle(position.getX(), position.getY(), size, size);
	}
	
	@Override
	public void render(Graphics2D graphics) {
		graphics.setColor(color);
		graphics.fill(getRepresentation());
	}

}