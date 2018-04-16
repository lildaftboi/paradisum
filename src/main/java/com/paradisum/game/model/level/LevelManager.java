package com.paradisum.game.model.level;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import com.paradisum.Paradisum;
import com.paradisum.application.ApplicationConstants;
import com.paradisum.game.Serviceable;
import com.paradisum.game.model.Entity;
import com.paradisum.game.model.EntityType;
import com.paradisum.game.model.level.levels.FirstLevel;
import com.paradisum.game.model.mob.Mob;
import com.paradisum.game.model.mob.MobConstants;
import com.paradisum.game.model.mob.Npc;
import com.paradisum.game.model.mob.Player;
import com.paradisum.game.model.object.GameObject;
import com.paradisum.state.GraphicalStateConstants;

/**
 * A model that manages entities.
 * @author Carlos Aviles
 */
public final class LevelManager implements Serviceable {
	
	/**
	 * The paradisum instance, to have an instance of the application.
	 */
	private final Paradisum paradisum;
	
	/**
	 * A list of levels, which represents the gameplay.
	 */
	private final List<Level> levels = new ArrayList<>();
	
	/**
	 * A list of mobs.
	 */
	private final List<Mob> mobs = new ArrayList<>();
	
	/**
	 * A list of npcs.
	 */
	private final List<Npc> npcs = new ArrayList<>();
	
	/**
	 * A list of game objects.
	 */
	private final List<GameObject> objects = new ArrayList<>();
	
	/**
	 * A list of entities to remove.
	 */
	private final List<Entity> entitiesToRemove = new ArrayList<>();
	
	/**
	 * The player instance, the main character of the game.
	 */
	private Player player;
	
	/**
	 * The current level that is being executed.
	 */
	private Object currentKey;
	
	/**
	 * The default colours used for the hud.
	 */
	private final Color[] hudcolours = new Color[] { new Color(25, 25, 25), new Color(185, 185, 185), new Color(0, 100, 0), Color.WHITE };
	
	/**
	 * The width of the health bar.
	 */
	private int healthWidth;
	
	/**
	 * Represents the time left when a level ends.
	 */
	private int timeLeft;
	
	/**
	 * Represents the time left message.
	 */
	private String timeLeftMessage = "";
	
	/**
	 * Represents the game time, that is translated to real time.
	 */
	private int ticks;
	
	/**
	 * The ticks translated into minutes.
	 */
	private int minutes;
	
	/**
	 * The ticks translated into seconds.
	 */
	private int seconds;
	
	/**
	 * Instantiates a new game manager instance.
	 * @param paradisum The paradisum instance.
	 */
	public LevelManager(Paradisum paradisum) {
		this.paradisum = paradisum;
	}
	
	/**
	 * Initialize the game world and its resources.
	 */
	public void init() {
		levels.add(new FirstLevel(LevelConstants.FIRST_LEVEL, 
				paradisum));
		setCurrentKey(LevelConstants.FIRST_LEVEL);
	}
	
	/**
	 * Adds the player onto the list.
	 * @param player The player instance.
	 */
	public void register(Player player) {
		this.player = player;
		mobs.add(player);
	}
	
	/**
	 * Adds an npc onto the list.
	 * @param npc The npc instance.
	 */
	public void register(Npc npc) {
		npcs.add(npc);
		mobs.add(npc);
	}
	
	/**
	 * Adds a game object onto the list.
	 * @param npc The game object instance.
	 */
	public void register(GameObject object) {
		objects.add(object);
	}
	
	/**
	 * Removes an entity from the lists.
	 * @param object The entity instance.
	 */
	public void unregister(Entity entity) {
		entitiesToRemove.add(entity);
	}
	
	/**
	 * Removes all entities from the lists.
	 */
	public void unregisterAll() {
		npcs.forEach(npc -> entitiesToRemove.add(npc));
		objects.forEach(object -> entitiesToRemove.add(object));
	}
	
	/**
	 * @return The list of mobs.
	 */
	public final List<Mob> getMobs() {
		return mobs;
	}
	
	/**
	 * @return The list of npcs.
	 */
	public final List<Npc> getNpcs() {
		return npcs;
	}
	
	/**
	 * @return The list of game objects.
	 */
	public final List<GameObject> getObjects() {
		return objects;
	}
	
	/**
	 * @return The player instance.
	 */
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * @return The level instance.
	 */
	public Level getLevel() {
		return levels.stream().filter(
				level -> getCurrentKey() == level.getKey()).findFirst().orElse(null);
	}
	
	/**
	 * @return The id of the current active state.
	 */
	public Object getCurrentKey() {
		return currentKey;
	}
	
	/**
	 * Changes the current id to the new current level's id.
	 * @param key The new id.
	 */
	public void setCurrentKey(Object key) {
		Object[] names = LevelConstants.LEVELS;
		for (int index = 0; index < names.length; index++) {
			if (names[index] == key) {
				final Level old = getLevel();
				final Level current = levels.stream().filter(
						level -> key == level.getKey()).findFirst().get();
				
				if (old != null) {
					old.stop();
				}
				current.init();
				this.currentKey = key;
				return;
			}
		}
	}
	
	@Override
	public void update() {
		/*
		 * Update the tick counter.
		 */
		ticks++;
		minutes = ticks / 1800;
		seconds = (ticks / 60) % 60;
		
		/**
		 * Update all entities first.
		 */
		player.update();
		npcs.forEach(npc -> npc.update());
		objects.forEach(object -> object.update());
		
		/*
		 * Update the player's health bar.
		 */
		healthWidth = player.getHealth() * 4;
		
		/*
		 * Safely remove all entities, that are required to be removed.
		 */
		if (!entitiesToRemove.isEmpty()) {
			entitiesToRemove.forEach(entity -> { 
				if (entity.getType() == EntityType.OBJECT) {
					objects.remove((GameObject) entity);
				}
				if (entity.getType() == EntityType.NPC) {
					npcs.remove((Npc) entity);
				}
			});
			entitiesToRemove.clear();
		}
		
		/*
		 * Basic collision detection between {@link Mob}s.
		 */
		npcs.forEach(npc -> {
			if (npc.getRepresentation().intersects(player.getRepresentation())) {
				player.setHealth(player.getHealth() - 1);
			}
		});
		
		/*
		 * Basic collision detection with {@link GameObject}s.
		 */
		objects.forEach(object -> {
			if (object.getRepresentation().intersects(player.getRepresentation())) {
				player.setPoints(player.getPoints() + 1);
				unregister(object);
			}
		});
		
		/*
		 * Update the time left in a level.
		 */
		timeLeft = getLevel().getLength() - seconds;
		if (timeLeft == 1) {
			timeLeftMessage = timeLeft +" second";
		} else {
			timeLeftMessage = timeLeft +" seconds";
		}
	}

	@Override
	public void render(Graphics2D graphics) {
		/*
		 * Render all entities first.
		 */
		player.render(graphics);
		npcs.forEach(npc -> npc.render(graphics));
		objects.forEach(object -> object.render(graphics));
		
		/*
		 * The drawing of the player's health bar.
		 */
		graphics.setColor(hudcolours[0]);
		graphics.fillRect(0, LevelConstants.HUD_Y, MobConstants.PLAYER_MAX_HEALTH * 4, 75);
		graphics.setColor(hudcolours[2]);
		graphics.fillRect(0, LevelConstants.HUD_Y, healthWidth, 75);
		graphics.setColor(hudcolours[1]);
		graphics.drawRect(0, LevelConstants.HUD_Y - 1, (MobConstants.PLAYER_MAX_HEALTH * 4) - 1, 75);
		graphics.setColor(hudcolours[3]);
		graphics.setFont(new Font(GraphicalStateConstants.DEFAULT_FONT_NAME, Font.BOLD, LevelConstants.HEALTH_FONT_SIZE));
		graphics.drawString(player.getHealth() +"", 5, ApplicationConstants.HEIGHT - 15);
		
		/*
		 * The drawing of the hud counter.
		 */
		graphics.setColor(hudcolours[0]);
		graphics.fillRect(MobConstants.PLAYER_MAX_HEALTH * 4, LevelConstants.HUD_Y, ApplicationConstants.WIDTH - (MobConstants.PLAYER_MAX_HEALTH * 4) - 1, 75);
		graphics.setColor(hudcolours[1]);
		graphics.drawRect(MobConstants.PLAYER_MAX_HEALTH * 4, LevelConstants.HUD_Y - 1, ApplicationConstants.WIDTH - (MobConstants.PLAYER_MAX_HEALTH * 4) -1, 75);
		graphics.setColor(hudcolours[3]);
		
		/*
		 * The drawing of the time left message.
		 */
		graphics.setFont(new Font(GraphicalStateConstants.DEFAULT_FONT_NAME, Font.BOLD, GraphicalStateConstants.DEFAULT_FONT_SIZE));
		graphics.drawString(timeLeftMessage, (int) (ApplicationConstants.WIDTH / 1.275), ApplicationConstants.HEIGHT - 6);
		graphics.setFont(new Font(GraphicalStateConstants.DEFAULT_FONT_NAME, Font.PLAIN, GraphicalStateConstants.DEFAULT_FONT_SIZE));
		graphics.drawString("Time left:", (int) (ApplicationConstants.WIDTH / 1.615), ApplicationConstants.HEIGHT - 7);
		
		/*
		 * The drawing of the player's points message.
		 */
		graphics.setFont(new Font(GraphicalStateConstants.DEFAULT_FONT_NAME, Font.BOLD, GraphicalStateConstants.DEFAULT_FONT_SIZE));
		graphics.drawString(player.getPoints() + "", (int) (ApplicationConstants.WIDTH / 1.345), ApplicationConstants.HEIGHT - 29);
		graphics.setFont(new Font(GraphicalStateConstants.DEFAULT_FONT_NAME, Font.PLAIN, GraphicalStateConstants.DEFAULT_FONT_SIZE));
		graphics.drawString("Points:", (int) (ApplicationConstants.WIDTH / 1.615), ApplicationConstants.HEIGHT - 30);
		
		/*
		 * The drawing of the enemies counter.
		 */
		graphics.setFont(new Font(GraphicalStateConstants.DEFAULT_FONT_NAME, Font.BOLD, GraphicalStateConstants.DEFAULT_FONT_SIZE));
		graphics.drawString(npcs.size() + "", (int) (ApplicationConstants.WIDTH / 1.1475), ApplicationConstants.HEIGHT - 52);
		graphics.setFont(new Font(GraphicalStateConstants.DEFAULT_FONT_NAME, Font.PLAIN, GraphicalStateConstants.DEFAULT_FONT_SIZE));
		graphics.drawString("Npcs in-game:", (int) (ApplicationConstants.WIDTH / 1.615), ApplicationConstants.HEIGHT - 53);
	}

}