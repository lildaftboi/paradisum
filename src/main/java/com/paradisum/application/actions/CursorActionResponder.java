package com.paradisum.application.actions;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * A model that handles cursor related events.
 * @author Carlos Aviles
 */
public final class CursorActionResponder extends MouseAdapter {
	
	/**
	 * A flag that specifies if the cursor is currently being pressed.
	 */
	private boolean pressed;
	
	/**
	 * The x coordinate of the location the mouse clicked on.
	 */
	private int pressedX;

	/**
	 * The y coordinate of the location of the mouse clicked on.
	 */
    private int pressedY;
    
    /**
     * The x coordinate of the location the mouse is at, when it moves.
     */
    private int movedX;
    
    /**
     * The y coordinate of the location the mouse is at, when it moves.
     */
    private int movedY;
    
    @Override
    public void mousePressed(MouseEvent event) {
    	int button = event.getButton();
		if (button == MouseEvent.BUTTON1) {
			int x = event.getX();
	    	int y = event.getY();
	    	
	    	pressed = true;
	    	pressedX = x;
	    	pressedY = y;
		}
    }
    
    @Override
    public void mouseReleased(MouseEvent event) {
    	int button = event.getButton();
		if (button == MouseEvent.BUTTON1) {
			pressed = false;
			pressedX = -1;
			pressedY = -1;
		}
    	
    }
    
    @Override
    public void mouseMoved(MouseEvent event) {
    	int x = event.getX();
    	int y = event.getY();
    	
    	movedX = x;
    	movedY = y;
    }
    
    /**
	 * @return The flag that specifies if the cursor is currently being pressed.
	 */
	public boolean isPressed() {
		return pressed;
	}
    
    /**
     * @return The x pressed coordinate.
     */
    public int getPressedX() {
    	return pressedX;
    }

    /**
     * @return The y pressed coordinate.
     */
    public int getPressedY() {
    	return pressedY;
    }
    
    /**
     * @return The x moved coordinate.
     */
    public int getMovedX() {
    	return movedX;
    }
    
    /**
     * @return The y moved coordinate.
     */
    public int getMovedY() {
    	return movedY;
    }

}