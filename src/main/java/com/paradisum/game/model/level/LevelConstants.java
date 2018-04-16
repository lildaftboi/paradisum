package com.paradisum.game.model.level;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.paradisum.application.ApplicationConstants;
import com.paradisum.utilities.CFGFileReader;

/**
 * A utility class that contains constants that relate to the levels.
 * @author Carlos Aviles
 */
public final class LevelConstants {
	
	/**
	 * The key for the 1st level.
	 */
	public static final Object FIRST_LEVEL;
	
	/**
	 * The time length for the 1st level.
	 */
	public static final int FIRST_LEVEL_LENGTH;
	
	/**
	 * The hud y location.
	 */
	public static final int HUD_Y;
	
	/**
	 * The font size for the health bar text.
	 */
	public static final int HEALTH_FONT_SIZE;
	
	/**
	 * An array of all the graphical states's keys.
	 */
	public static final Object[] LEVELS;
	
	/**
	 * Load all the static variables.
	 */
	static {
		try (InputStream is = new FileInputStream("data/level.cfg")) {
			final CFGFileReader parser = new CFGFileReader(is);
			
			parser.parse();
			
			FIRST_LEVEL = parser.getChildAsString("first_level");
			FIRST_LEVEL_LENGTH = parser.getChildAsInteger("first_level_length");
			HUD_Y = ApplicationConstants.HEIGHT - parser.getChildAsInteger("hud_height");
			HEALTH_FONT_SIZE = parser.getChildAsInteger("font_size");
			LEVELS = new Object[] { FIRST_LEVEL };
		} catch (IOException exception) {
			throw new ExceptionInInitializerError(new IOException("Error parsing level.cfg!", exception));
		}
	}
	
	/**
	 * A private constructor, to avoid instantiation.
	 */
	private LevelConstants() {
		
	}

}