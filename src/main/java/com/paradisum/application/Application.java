package com.paradisum.application;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.paradisum.application.actions.CursorActionResponder;
import com.paradisum.application.actions.KeyboardActionResponder;
import com.paradisum.application.actions.WindowActionResponder;

/**
 * Represents a model that utilizes Java's frame as a user interface.
 *
 * @author Carlos Aviles
 */
public final class Application {
	
	/**
	 * The frame instance, represents the window.
	 */
	private final Frame frame = new Frame();
	
	/**
	 * The panel instance, represents the drawable underlay.
	 */
	private final JPanel panel = new JPanel();
	
	/**
	 * The image instance, for drawing graphics onto the component.
	 */
	private final BufferedImage background = new BufferedImage(ApplicationConstants.WIDTH, ApplicationConstants.HEIGHT, BufferedImage.TYPE_INT_RGB);
	
	/**
	 * The cursor responder instance, for listening to cursor events.
	 */
	private final CursorActionResponder cursorResponder = new CursorActionResponder();
	
	/**
	 * The window responder instance, for listening to window events.
	 */
	private final WindowActionResponder windowResponder = new WindowActionResponder();
	
	/**
	 * The key responder instance, for listening to keyboard events.
	 */
	private final KeyboardActionResponder keyboardResponder = new KeyboardActionResponder();
	
	/**
	 * Instantiates a new application instance.
	 */
	public Application() {
		
	}
	
	/**
	 * Builds the application.
	 */
	public void build() {
		final Dimension size = new Dimension(ApplicationConstants.WIDTH, ApplicationConstants.HEIGHT);
		
		frame.setTitle("Paradisum");
		panel.setPreferredSize(size);
		panel.setMaximumSize(size);
		panel.setMinimumSize(size);
		frame.setResizable(false);
		frame.addWindowListener(windowResponder);
		frame.add(panel);
		frame.setVisible(true);
		frame.pack();
		frame.setLocationRelativeTo(null);
		
		panel.addMouseListener(cursorResponder);
		panel.addMouseMotionListener(cursorResponder);
		frame.addKeyListener(keyboardResponder);
	}
	
	/**
	 * @param font The font instance.
	 * @return The metrics instance.
	 */
	public FontMetrics getFontMetrics(Font font) {
		return frame.getFontMetrics(font);
	}
	
	/**
	 * @return The graphics instance.
	 */
	public final Graphics getGraphics() {
		return background.getGraphics();
	}
	
	/**
	 * @return The graphics, in 2d, instance.
	 */
	public final Graphics2D getGraphics2D() {
		return (Graphics2D) getGraphics();
	}
	
	/**
	 * @return The graphics instance.
	 */
	public final Graphics getGraphicsContext() {
		return panel.getGraphics();
	}
	
	/**
	 * @return The image instance.
	 */
	public final BufferedImage getBackground() {
		return background;
	}
	
	/**
	 * @return The application's visible flag.
	 */
	public boolean isVisible() {
		return frame.isVisible();
	}
	
	/**
	 * @return The flag that specifies if the cursor is currently being pressed.
	 */
	public boolean isCursorPressed() {
		return cursorResponder.isPressed();
	}
	
	/**
	 * @return The cursor pressed x coordinate.
	 */
	public int getCursorPressedX() {
		return cursorResponder.getPressedX();
	}
	
	/**
	 * @return The cursor pressed y coordinate.
	 */
	public int getCursorPressedY() {
		return cursorResponder.getPressedY();
	}
	
	/**
	 * @return The cursor pressed as a {@link Rectangle} instance.
	 */
	public Rectangle getCursorPressed() {
		return new Rectangle(cursorResponder.getPressedX(), cursorResponder.getPressedY(), 1, 1);
	}
	
	/**
	 * @return The cursor moved x coordinate.
	 */
	public int getCursorMovedX() {
		return cursorResponder.getMovedX();
	}
	
	/**
	 * @return The cursor moved y coordinate.
	 */
	public int getCursorMovedY() {
		return cursorResponder.getMovedY();
	}
	
	/**
	 * @return The cursor moved as a rectangle instance.
	 */
	public Rectangle getCursorMoved() {
		return new Rectangle(cursorResponder.getMovedX(), cursorResponder.getMovedY(), 1, 1);
	}
	
	/**
	 * @return Checks if a key is currently active.
	 */
	public boolean isAKeyPressed() {
		return keyboardResponder.isAKeyPressed();
	}
	
	/**
	 * @param key The requested key value.
	 * @return Checks if a requested key is currently active.
	 */
	public boolean isKeyPressed(int key) {
		return keyboardResponder.isKeyPressed(key);
	}
	
	/**
	 * @return Gets all the keys that are currently active.
	 */
	public int[] getKeysPressed() {
		return keyboardResponder.getKeysPressed();
	}
	
	/**
	 * @param keys The requested key value.
	 * @return Checks whether specific keys are currently active.
	 */
	public boolean areKeysPressed(int[] keys) {
		return keyboardResponder.areKeysPressed(keys);
	}

}