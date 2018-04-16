package com.paradisum.game;

import java.awt.Graphics2D;

/**
 * An interface that is used when a model is subject to game logic services.
 * @author Carlos Aviles
 */
public interface Serviceable {
	
	/**
	 * Updates the required logic code.
	 */
	void update();
	
	/**
	 * Renders the graphics onto the drawing board.
	 * @param graphics The graphics, in 2d, instance.
	 */
	void render(Graphics2D graphics);

}