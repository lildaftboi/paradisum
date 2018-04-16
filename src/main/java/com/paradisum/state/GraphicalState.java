package com.paradisum.state;

import java.awt.Rectangle;

import com.paradisum.Paradisum;
import com.paradisum.application.Application;
import com.paradisum.game.Serviceable;

/**
 * Represents different type of states that can be used as a drawing board.
 * @author Carlos Aviles
 */
public abstract class GraphicalState implements Serviceable {
	
	/**
	 * Represents a unique key for this state.
	 */
	protected final Object key;
	
	/**
	 * The paradisum instance, to have an instance of the application.
	 */
	protected final Paradisum paradisum;
	
	/**
	 * Instantiates a new graphical state instance.
	 * @param key The unique key.
	 * @param paradisum The paradisum instance.
	 */
	public GraphicalState(Object key, Paradisum paradisum) {
		this.key = key;
		this.paradisum = paradisum;
	}
	
	/**
	 * Initialize the graphical state's resources.
	 */
	public abstract void init();

	/**
	 * Handle any required cursor related action events.
	 * @param clickArea The cursor click area.
	 */
    protected abstract void handleClickAction(Rectangle clickArea);
    
    /**
	 * Handle any required cursor related action events.
	 * @param cursorArea The cursor moved area.
	 */
    protected abstract void handleMoveAction(Rectangle cursorArea);

    /**
     * Handles any required keyboard action events.
     * @param key The requested key value.
     */
    protected abstract void handleKeyboardAction(int[] keys);

    /**
     * Executes any game logic related code required for the state.
     */
    protected abstract void execute();
    
    /**
     * A method for safely stopping any game logic.
     */
    protected abstract void stop();
	
	/**
	 * @return The unique key.
	 */
	public Object getKey() {
		return key;
	}
	
	@Override
	public void update() {
		final Application application = paradisum.getApplication();
		if (application.isAKeyPressed()) {
			int[] keys = application.getKeysPressed();
			handleKeyboardAction(keys);
		}
		if (application.isCursorPressed()) {
			handleClickAction(application.getCursorPressed());
		}
		
		handleMoveAction(application.getCursorMoved());
		execute();
	}

}