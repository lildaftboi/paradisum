package com.paradisum.game.model.mob;

import com.paradisum.application.ApplicationConstants;
import com.paradisum.game.model.Direction;
import com.paradisum.game.model.EntityType;
import com.paradisum.game.model.Position;
import com.paradisum.game.model.level.LevelConstants;

/**
 * Handles all aspects of the movement of an entity.
 * @author Carlos Aviles
 */
public final class MobPathHandler {
	
	/**
	 * Processes the next mob's movement.
	 * @param mob The mob instance.
	 */
	public void processNextMovement(Mob mob) {
		/*
		 * One direction movement.
		 */
		if (mob.getMovementDirection() != Direction.NONE) {
			int x = mob.getPosition().getX();
			int y = mob.getPosition().getY();
			int toX = x;
			int toY = y;
			switch (mob.getMovementDirection()) {
			case NORTH:
				toY-= mob.getSpeed();
				break;
			case EAST:
				toX+= mob.getSpeed();
				break;
			case SOUTH:
				toY+= mob.getSpeed();
				break;
			case WEST:
				toX-= mob.getSpeed();
				break;
			default:
				break;
			}
			
			/*
			 * Basic bounds checking.
			 */
			if (canMove(mob, toX, toY)) {
				
				/*
				 * Npc movement is handled differently.
				 */
				if (mob.getType() == EntityType.NPC) {
					if (toY + mob.getSize() == LevelConstants.HUD_Y) {
						mob.setMovementDirection(Direction.NORTH);
					}
					if (toY == 0) {
						mob.setMovementDirection(Direction.SOUTH);
					}
					if (toX + mob.getSize() == ApplicationConstants.WIDTH) {
						mob.setMovementDirection(Direction.WEST);
					}
					if (toX == 0) {
						mob.setMovementDirection(Direction.EAST);
					}
				}
				
				/*
				 * Finally set the new position.
				 */
				mob.setPosition(Position.create(toX,  toY));
				
				/*
				 * Player movement will instantly be turned to no movement.
				 */
				if (mob.getType() == EntityType.PLAYER) {
					Player player = (Player) mob;
					player.setLastIdleTick(0);
					player.setMovementDirection(Direction.NONE);
				}
			}
		}
		
		/*
		 * Two-step direction movement, will allow mobs to move North-East, South-West, etc.
		 */
		if (mob.getOtherMovementDirection() != Direction.NONE && mob.getOtherMovementDirection() != mob.getMovementDirection()) {
			int x = mob.getPosition().getX();
			int y = mob.getPosition().getY();
			int toX = x;
			int toY = y;
			switch (mob.getOtherMovementDirection()) {
			case NORTH:
				toY-= mob.getSpeed();
				break;
			case EAST:
				toX+= mob.getSpeed();
				break;
			case SOUTH:
				toY+= mob.getSpeed();
				break;
			case WEST:
				toX-= mob.getSpeed();
				break;
			default:
				break;
			}
		
			/*
			 * Basic bounds checking.
			 */
			if (canMove(mob, toX, toY)) {
				
				/*
				 * Npc movement is handled differently.
				 */
				if (mob.getType() == EntityType.NPC) {
					if (toY + mob.getSize() == LevelConstants.HUD_Y) {
						mob.setOtherMovementDirection(Direction.NORTH);
					}
					if (toY == 0) {
						mob.setOtherMovementDirection(Direction.SOUTH);
					}
					if (toX + mob.getSize() == ApplicationConstants.WIDTH) {
						mob.setOtherMovementDirection(Direction.WEST);
					}
					if (toX == 0) {
						mob.setOtherMovementDirection(Direction.EAST);
					}
				}
				
				/*
				 * Finally set the new position.
				 */
				mob.setPosition(Position.create(toX,  toY));
				
				/*
				 * Player movement will instantly be turned to no movement.
				 */
				if (mob.getType() == EntityType.PLAYER) {
					Player player = (Player) mob;
					player.setLastIdleTick(0);
					player.setOtherMovementDirection(Direction.NONE);
				}
			}
		}
	}
	
	/**
	 * @param mob The mob instance.
	 * @param toX The x coordinate the mob will move to.
	 * @param toY The y coordinate the mob will move to.
	 * @return If a mob can move to a specific coordinate.
	 */
	public boolean canMove(Mob mob, int toX, int toY) {
		if (toX + mob.getSize() > ApplicationConstants.WIDTH || toX < 0) {
			return false;
		}
		if (toY + mob.getSize() > LevelConstants.HUD_Y || toY < 0) {
			return false;
		}
		return true;
	}

}