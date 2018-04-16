package com.paradisum.game.model.level;

import java.awt.Rectangle;
import java.util.Random;

import com.paradisum.Paradisum;
import com.paradisum.game.Serviceable;

/**
 * Represents the different types of levels in the game.
 * @author Carlos Aviles
 */
public abstract class Level implements Serviceable {
	
	/**
	 * Represents a unique key for this state.
	 */
	protected final Object key;
	
	/**
	 * The paradisum instance, to have an instance of the application.
	 */
	protected final Paradisum paradisum;
	
	/**
	 * The random instance, used for executing some specific game logic.
	 */
	protected final Random random = new Random();
	
	/**
	 * The length of the level.
	 */
	protected final int length;

	/**
	 * Instantiates a new level instance.
	 * 
	 * @param key The unique key.
	 * @param paradisum The paradisum instance.
	 * @param length The length of the level.
	 */
	public Level(Object key, Paradisum paradisum, int length) {
		this.key = key;
		this.paradisum = paradisum;
		this.length = length;
	}
	
	/**
	 * Initialize the graphical state's resources.
	 */
	public abstract void init();

	/**
	 * Handle any required cursor related action events.
	 * 
	 * @param clickArea The cursor click area.
	 */
    public abstract void handleClickAction(Rectangle clickArea);
    
    /**
	 * Handle any required cursor related action events.
	 * @param cursorArea The cursor moved area.
	 */
    public abstract void handleMoveAction(Rectangle cursorArea);

    /**
     * Handles any required keyboard action events.
     * @param key The requested key value.
     */
    public abstract void handleKeyboardAction(int[] keys);
    
    /**
     * A method for safely stopping any game logic.
     */
    public abstract void stop();
	
	/**
	 * @return The unique key.
	 */
	public Object getKey() {
		return key;
	}
	
	/**
	 * @return The length of this level.
	 */
	public final int getLength() {
		return length;
	}

}