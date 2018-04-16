package com.paradisum.game.model.level.levels;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import com.paradisum.Paradisum;
import com.paradisum.application.ApplicationConstants;
import com.paradisum.game.model.Direction;
import com.paradisum.game.model.Position;
import com.paradisum.game.model.level.Level;
import com.paradisum.game.model.level.LevelConstants;
import com.paradisum.game.model.level.LevelManager;
import com.paradisum.game.model.mob.Npc;
import com.paradisum.game.model.mob.Player;
import com.paradisum.game.model.mob.impl.BeginnerNpc;
import com.paradisum.game.model.object.GameObject;

/**
 * Represents the first beginner level.
 * @author Carlos Aviles
 */
public final class FirstLevel extends Level {

	/**
	 * Instantiates a new first level instance.
	 * @param key The unique key for this level.
	 * @param paradisum The paradisum instance.
	 */
	public FirstLevel(Object key, Paradisum paradisum) {
		super(key, paradisum, LevelConstants.FIRST_LEVEL_LENGTH);
	}

	@Override
	public void render(Graphics2D graphics) {

	}

	@Override
	public void init() {
		final LevelManager manager = paradisum.getLevelManager();
		
		manager.register(new Player(paradisum));
		manager.register(new BeginnerNpc(paradisum, Position.create(30, 30)));
		manager.register(new GameObject(paradisum, Position.create(300, 300)));
		manager.register(new GameObject(paradisum, Position.create(100,  50)));
	}

	@Override
	public void handleClickAction(Rectangle clickArea) {
		
	}

	@Override
	public void handleMoveAction(Rectangle cursorArea) {
		
	}

	@Override
	public void handleKeyboardAction(int[] keys) {
		final Player player = paradisum.getLevelManager().getPlayer();
		
		int firstKey = keys[0];
		int secondKey = keys[1];
		switch (firstKey)  {
		case KeyEvent.VK_UP:
			player.setMovementDirection(Direction.NORTH);
			break;
		case KeyEvent.VK_RIGHT:
			player.setMovementDirection(Direction.EAST);
			break;
		case KeyEvent.VK_DOWN:
			player.setMovementDirection(Direction.SOUTH);
			break;
		case KeyEvent.VK_LEFT:
			player.setMovementDirection(Direction.WEST);
			break;
		}
		
		switch(secondKey) {
		case KeyEvent.VK_UP:
			player.setOtherMovementDirection(Direction.NORTH);
			break;
		case KeyEvent.VK_RIGHT:
			player.setOtherMovementDirection(Direction.EAST);
			break;
		case KeyEvent.VK_DOWN:
			player.setOtherMovementDirection(Direction.SOUTH);
			break;
		case KeyEvent.VK_LEFT:
			player.setOtherMovementDirection(Direction.WEST);
			break;
		}
	}

	@Override
	public void stop() {
		final LevelManager manager = paradisum.getLevelManager();
		
		manager.unregisterAll();
	}

	@Override
	public void update() {
		/*
		 * Check if the player is not moving so we force them to move.
		 */
		final Player player = paradisum.getLevelManager().getPlayer();
		if (!player.hasMoved()) {
			System.out.println("not moving");
			player.setLastIdleTick(player.getLastIdleTick() + 1);
			if (player.getLongestIdleTick() < player.getLastIdleTick()) {
				player.setLongestIdleick(player.getLastIdleTick());
			}
		}
		
		/*
		 * Randomly generate a new npc.
		 */
		if (random.nextInt(200) == 1) {
			paradisum.getLevelManager().getNpcs().forEach(npc -> npc.setMovementDirection(npc.getNextRandomDirection()));
			
			Npc npc = new BeginnerNpc(paradisum, Position.create(250, 250));
			npc.setMovementDirection(npc.getNextRandomDirection());
			npc.setSpeed(10);
			
			paradisum.getLevelManager().register(npc);
		}
		
		/*
		 * Randomly generate a new game object.
		 */
		if (random.nextInt(700) == 1) {
			GameObject object = new GameObject(paradisum, Position.create(0, 0));
			int x = random.nextInt(ApplicationConstants.WIDTH) - object.getSize();
			int y = random.nextInt(ApplicationConstants.HEIGHT) - object.getSize();
			object.setPosition(Position.create(x,  y));
			
			paradisum.getLevelManager().register(object);
		}
	}

}