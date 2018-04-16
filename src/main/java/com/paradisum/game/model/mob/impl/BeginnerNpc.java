package com.paradisum.game.model.mob.impl;

import java.awt.Color;

import com.paradisum.Paradisum;
import com.paradisum.game.model.Direction;
import com.paradisum.game.model.Position;
import com.paradisum.game.model.mob.Npc;

/**
 * Represents a basic beginner npc.
 * @author Carlos Aviles
 */
public final class BeginnerNpc extends Npc {

	/**
	 * Instantiates a new beginner npc instance.
	 * @param paradisum The paradisum instance.
	 * @param position The position instance.
	 */
	public BeginnerNpc(Paradisum paradisum, Position position) {
		super(paradisum, position, 40, 5);
		super.color = Color.CYAN;
		super.setMovementDirection(Direction.WEST);
	}

}