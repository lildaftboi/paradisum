package com.paradisum.application;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.paradisum.utilities.CFGFileReader;

/**
 * A utility class that contains constants that relate to the application.
 * @author Carlos Aviles
 */
public final class ApplicationConstants {
	
	/**
	 * The width of the frame.
	 */
	public final static int WIDTH;
	
	/**
	 * The height of the frame.
	 */
	public final static int HEIGHT;
	
	/**
	 * A flag that represents if the frame should print developer information.
	 */
	public final static boolean DEBUG_MODE;
	
	/**
	 * The maximum amount of keys that the application will listen to.
	 */
	public static final int MAX_KEYS_PRESSED;
	
	/**
	 * Load all the static variables.
	 */
	static {
		try (InputStream is = new FileInputStream("data/application.cfg")) {
			final CFGFileReader parser = new CFGFileReader(is);
			
			parser.parse();
			
			WIDTH = parser.getChildAsInteger("width");
			HEIGHT = parser.getChildAsInteger("height");
			DEBUG_MODE = parser.getChildAsBoolean("debug");
			MAX_KEYS_PRESSED = parser.getChildAsInteger("max_keys");
		} catch (IOException exception) {
			throw new ExceptionInInitializerError(new IOException("Error parsing application.cfg!", exception));
		}
	}
	
	/**
	 * A private constructor, to avoid instantiation.
	 */
	private ApplicationConstants() {
		
	}

}