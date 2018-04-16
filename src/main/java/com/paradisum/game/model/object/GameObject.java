package com.paradisum.game.model.object;

import java.awt.Color;

import com.paradisum.Paradisum;
import com.paradisum.game.model.Entity;
import com.paradisum.game.model.EntityType;
import com.paradisum.game.model.Position;

/**
 * A model that represents an immovable entity.
 * @author Carlos Aviles
 */
public final class GameObject extends Entity {

	/**
	 * Instantiates a new game object instance.
	 * @param paradisum The paradisum instance.
	 * @param position The position instance.
	 */
	public GameObject(Paradisum paradisum, Position position) {
		super(paradisum, EntityType.OBJECT, position, 5);
		super.color = Color.YELLOW;
	}

	@Override
	public void update() {
		
	}

}