package com.paradisum.application.actions;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.paradisum.application.ApplicationConstants;

/**
 * A model that handles keyboard related events within the application.
 * @author Carlos Aviles
 */
public final class KeyboardActionResponder extends KeyAdapter {
	
	/**
	 * An array that contains a true or false value for a specific key.
	 */
	private final boolean[] pressed = new boolean[256];
	
	@Override
    public void keyPressed(KeyEvent event) {
		int key = event.getKeyCode();
		pressed[key] = true;
	}
	
	@Override
    public void keyReleased(KeyEvent event) {
		int key = event.getKeyCode();
		pressed[key] = false;
	}
	
	/**
	 * @return Checks if a key is currently active.
	 */
	public boolean isAKeyPressed() {
		for (int index = 0; index < pressed.length; index++) {
			if (pressed[index]) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @param key The requested key value.
	 * @return Checks if a requested key is currently active.
	 */
	public boolean isKeyPressed(int key) {
		return pressed[key];
	}
	
	/**
	 * @return Gets all the keys that are currently active.
	 */
	public int[] getKeysPressed() {
		int[] keys = new int[ApplicationConstants.MAX_KEYS_PRESSED];
		for (int index = 0; index < pressed.length; index++) {
			for (int dex = 0; dex < keys.length; dex++) {
				if (pressed[index]) {
					if (keys[dex] == 0) {
						keys[dex] = index;
						break;
					}
				}
				continue;
			}
		}
		return keys;
	}
	
	/**
	 * @param keys The requested key value.
	 * @return Checks whether specific keys are currently active.
	 */
	public boolean areKeysPressed(int[] keys) {
		if (keys.length > ApplicationConstants.MAX_KEYS_PRESSED) {
			throw new IllegalArgumentException("The application is only listening to a maximum of "+ ApplicationConstants.MAX_KEYS_PRESSED +" keys pressed!");
		}
		
		int reference = 0;
		for (int index = 0; index < pressed.length; index++) {
			for (int dex = 0; dex < keys.length; dex++) {
				if (pressed[index]) {
					if (index == keys[dex]) {
						reference++;
					}
					if (reference == ApplicationConstants.MAX_KEYS_PRESSED) {
						return true;
					}
				}
			}
		}
		return false;
	}

}