package com.paradisum.game.model.mob;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.paradisum.utilities.CFGFileReader;

/**
 * A utility class that contains constants relating to a mob.
 * @author Carlos Aviles
 */
public final class MobConstants {
	
	/**
	 * The maximum value of the player's health.
	 */
	public final static int PLAYER_MAX_HEALTH;
	
	/**
	 * Load all the static variables.
	 */
	static {
		try (InputStream stream = new FileInputStream("data/mobs.cfg")) {
			final CFGFileReader parser = new CFGFileReader(stream);
			
			parser.parse();
			
			PLAYER_MAX_HEALTH = parser.getChildAsInteger("player_max_health");
		} catch (IOException exception) {
			throw new ExceptionInInitializerError(new IOException("Error parsing mobs.cfg!", exception));
		}
	}
	
	/**
	 * A private constructor, to avoid instantiation.
	 */
	private MobConstants() {
		
	}

}