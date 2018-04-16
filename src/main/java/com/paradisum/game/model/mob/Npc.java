package com.paradisum.game.model.mob;

import java.util.Random;

import com.paradisum.Paradisum;
import com.paradisum.game.model.Direction;
import com.paradisum.game.model.EntityType;
import com.paradisum.game.model.Position;

/**
 * Represents a mob that is not a player.
 * @author Carlos Aviles
 */
public class Npc extends Mob {
	
	/**
	 * The random instance, used for calculating random events.
	 */
	private final Random random = new Random();

	/**
	 * Instantiates a new npc instance.
	 * @param paradisum The paradisum instance.
	 * @param position The position instance.
	 * @param size The size of this npc.
	 * @param speed The movement speed.
	 */
	public Npc(Paradisum paradisum, Position position, int size, int speed) {
		super(paradisum, EntityType.NPC, position, size, speed);
	}
	
	/**
	 * @return The randomly generated next random movement direction.
	 */
	public Direction getNextRandomDirection() {
		Direction direction = Direction.NONE;
		switch(random.nextInt(4)) {
		case 0:
			direction = Direction.NORTH;
			break;
		case 1:
			direction = Direction.EAST;
			break;
		case 2:
			direction = Direction.SOUTH;
			break;
		case 3:
			direction = Direction.WEST;
			break;
		}
		return direction;
	}

}