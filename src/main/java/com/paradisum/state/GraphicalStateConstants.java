package com.paradisum.state;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.paradisum.utilities.CFGFileReader;

/**
 * A utility class that contains constants that relate to the graphical states.
 * @author Carlos Aviles
 */
public final class GraphicalStateConstants {
	
	/**
	 * The key for the main state.
	 */
	public static final Object MAIN_GRAPHICAL_STATE;
	
	/**
	 * The key for the game world state.
	 */
	public static final Object PLAY_GRAPHICAL_STATE;
	
	/**
	 * The key for the information state.
	 */
	public static final Object INFO_GRAPHICAL_STATE;
	
	/**
	 * An array of all the graphical states's keys.
	 */
	public static final Object[] GRAPHICAL_STATES;
	
	/**
	 * The default font name.
	 */
	public static final String DEFAULT_FONT_NAME;
	
	/**
	 * The default font size.
	 */
	public static final int DEFAULT_FONT_SIZE;
	
	/**
	 * Load all the static variables.
	 */
	static {
		try (InputStream is = new FileInputStream("data/state.cfg")) {
			final CFGFileReader parser = new CFGFileReader(is);
			
			parser.parse();
			
			MAIN_GRAPHICAL_STATE = parser.getChildAsString("main");
			PLAY_GRAPHICAL_STATE = parser.getChildAsString("play");
			INFO_GRAPHICAL_STATE = parser.getChildAsString("info");
			DEFAULT_FONT_NAME = parser.getChildAsString("font");
			DEFAULT_FONT_SIZE = parser.getChildAsInteger("font_size");
			GRAPHICAL_STATES = new Object[] { MAIN_GRAPHICAL_STATE, PLAY_GRAPHICAL_STATE, INFO_GRAPHICAL_STATE };
		} catch (IOException exception) {
			throw new ExceptionInInitializerError(new IOException("Error parsing state.cfg!", exception));
		}
	}
	
	/**
	 * A private constructor, to avoid instantiation.
	 */
	private GraphicalStateConstants() {
		
	}

}