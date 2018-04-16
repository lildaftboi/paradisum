package com.paradisum;

import com.google.common.base.Preconditions;

/**
 * The purpose of this class is to initialize the Java application.
 * @author Carlos Aviles
 */
public final class Bootstrap {
	
	/**
	 * A private constructor, to avoid instantiation.
	 */
	private Bootstrap() {
		
	}

	/**
	 * The main starting point of the Java application.
	 * @param arguments The program arguments given at startup.
	 */
	public static void main(String[] arguments) {
		Preconditions.checkArgument(arguments.length == 0, "Program arguments are not neccessary at startup!");
		try {
			new Paradisum().init();
		} catch (Throwable throwable) {
			throwable.printStackTrace();
			System.exit(1);
		}
	}

}