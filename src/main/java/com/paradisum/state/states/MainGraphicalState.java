package com.paradisum.state.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import com.paradisum.Paradisum;
import com.paradisum.application.Application;
import com.paradisum.application.ApplicationConstants;
import com.paradisum.state.GraphicalState;
import com.paradisum.state.GraphicalStateConstants;
import com.paradisum.state.GraphicalStateManager;

/**
 * Represents a state that represents a main menu board.
 * @author Carlos Aviles
 */
public final class MainGraphicalState extends GraphicalState {
	
	/**
	 * An array of rectangles that represent the menu buttons.
	 */
	private final Rectangle[] buttons = new Rectangle[3];
	
	/**
	 * An array of strings that are found inside each button.
	 */
	private final String[] messages = new String[3];
	
	/**
	 * An array of colors that represent the color for each button.
	 */
	private final Color[] buttonsColor = new Color[3];

	/**
	 * Instantiates a new main state instance.
	 * @param key The unique key of this state.
	 * @param paradisum The paradisum instance.
	 */
	public MainGraphicalState(Object key, Paradisum paradisum) {
		super(key, paradisum);
	}

	@Override
	public void render(Graphics2D graphics) {
		final Font font = new Font(graphics.getFont().getFontName(), 1, 50);
		final FontMetrics metrics = paradisum.getApplication().getFontMetrics(font);
		
		graphics.setFont(font);
		for (int index = 0; index < buttons.length; index++) {
			graphics.setColor(buttonsColor[index]);
			graphics.drawRect((int) buttons[index].getX(), (int) buttons[index].getY(),
					(int) buttons[index].getWidth(), (int) buttons[index].getHeight());
			graphics.drawString(messages[index], (int) ((ApplicationConstants.WIDTH - 
					metrics.stringWidth(messages[index])) / 2.05), (int) buttons[index].getY() + 52);
		}
	}

	@Override
	public void init() {
		buttons[0] = new Rectangle(ApplicationConstants.WIDTH / 3, 115, 200,70);
		buttons[1] = new Rectangle(ApplicationConstants.WIDTH / 3, 205, 200, 70);
		buttons[2] = new Rectangle(ApplicationConstants.WIDTH / 3, 295, 200, 70);
		
		messages[0] = new String("Play");
		messages[1] = new String("Info");
		messages[2] = new String("Exit");
		
		for (int index = 0; index < buttonsColor.length; index++) {
			buttonsColor[index] = new Color(255, 255, 255);
		}
	}

	@Override
	protected void handleClickAction(Rectangle clickArea) {
		final Application application = paradisum.getApplication();
		final GraphicalStateManager manager = paradisum.getStateManager();
		
		if (buttons[0].intersects(application.getCursorPressed())) {
			manager.setCurrentKey(GraphicalStateConstants.PLAY_GRAPHICAL_STATE);
		} else if (buttons[2].intersects(application.getCursorPressed())) {
			System.exit(0);
		}
	}
	
	@Override
	protected void handleMoveAction(Rectangle cursorArea) {
		for (int index = 0; index < buttons.length; index++) {
			if (buttons[index].intersects(paradisum.getApplication().getCursorMoved())) {
				buttonsColor[index] = new Color(255, 0, 0);
			} else {
				buttonsColor[index] = new Color(255, 255, 255);
			}
		}
	}

	@Override
	protected void handleKeyboardAction(int[] keys) {
		final GraphicalStateManager manager = paradisum.getStateManager();
		
		int key = keys[0];
		switch(key)  {
		case KeyEvent.VK_P:
			manager.setCurrentKey(GraphicalStateConstants.PLAY_GRAPHICAL_STATE);
			break;
		case KeyEvent.VK_I:
			manager.setCurrentKey(GraphicalStateConstants.INFO_GRAPHICAL_STATE);
			break;
		case KeyEvent.VK_E:
			System.exit(0);
			break;
		}
	}

	@Override
	protected void execute() {
		
	}

	@Override
	protected void stop() {
		
	}

}