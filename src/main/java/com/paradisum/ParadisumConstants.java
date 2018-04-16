package com.paradisum;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.paradisum.utilities.CFGFileReader;

/**
 * A utility class that contains constants relating to the overall paradisum application.
 * @author Carlos Aviles
 */
public final class ParadisumConstants {
	
	/**
	 * The protocol version of this build.
	 */
	public final static double PROTOCOL_VERSION;
	
	/**
	 * The logger's asynchronous logging flag.
	 */
	public final static boolean ASYNCHRONOUS_LOGGING;
	
	/**
	 * A flag that represents whether to print useful information.
	 */
	public final static boolean DEVELOPER_MODE;
	
	/**
	 * Load all the static variables.
	 */
	static {
		try (InputStream stream = new FileInputStream("data/paradisum.cfg")) {
			final CFGFileReader parser = new CFGFileReader(stream);
			
			parser.parse();
			
			PROTOCOL_VERSION = parser.getChildAsDouble("protocol");
			ASYNCHRONOUS_LOGGING = parser.getChildAsBoolean("logging");
			DEVELOPER_MODE = parser.getChildAsBoolean("dev_mode");
		} catch (IOException exception) {
			throw new ExceptionInInitializerError(new IOException("Error parsing paradisum.cfg!", exception));
		}
	}
	
	/**
	 * A private constructor, to avoid instantiation.
	 */
	private ParadisumConstants() {
		
	}

}