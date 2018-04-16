package com.paradisum.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paradisum.Paradisum;
import com.paradisum.application.ApplicationConstants;
import com.paradisum.game.Serviceable;
import com.paradisum.state.states.MainGraphicalState;
import com.paradisum.state.states.GameGraphicalState;

/**
 * Deals with updating, changing and rendering the graphical states.
 * @author Carlos Aviles
 */
public final class GraphicalStateManager implements Serviceable {
	
	/**
	 * The logger instance, for printing vital information to the console.
	 */
	private static final Logger LOGGER = LogManager.getLogger();
	
	/**
	 * The paradisum instance, to have an instance of the application.
	 */
	private final Paradisum paradisum;
	
	/**
	 * A list of different graphical states.
	 */
	private final List<GraphicalState> states = new ArrayList<>();
	
	/**
	 * The id of the current active state.
	 */
	private Object currentKey;
	
	/**
	 * Instantiates a new state manager instance.
	 * @param paradisum The paradisum instance.
	 */
	public GraphicalStateManager(Paradisum paradisum) {
		this.paradisum = paradisum;
	}
	
	/**
	 * Loads all the required states onto the list.
	 * @throws IOException Any problems with loading the file.
	 * @throws FontFormatException Any problems with creating the font.
	 */
	public void init() throws IOException, FontFormatException {
		states.add(new MainGraphicalState(GraphicalStateConstants.MAIN_GRAPHICAL_STATE, paradisum));
		states.add(new GameGraphicalState(GraphicalStateConstants.PLAY_GRAPHICAL_STATE, paradisum));
		
		LOGGER.info("Loaded "+ states.size() +" graphical states.");
		
		setCurrentKey(GraphicalStateConstants.MAIN_GRAPHICAL_STATE);
	}

	@Override
	public void update() {
		final GraphicalState current = states.stream().filter(
				state -> getCurrentKey() == state.getKey()).findFirst().get();
		
		current.update();
	}

	@Override
	public void render(Graphics2D graphics) {
		graphics.setFont(new Font(GraphicalStateConstants.DEFAULT_FONT_NAME, Font.PLAIN, GraphicalStateConstants.DEFAULT_FONT_SIZE));
		graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, ApplicationConstants.WIDTH, ApplicationConstants.HEIGHT);
		
		final GraphicalState current = states.stream().filter(
				state -> getCurrentKey() == state.getKey()).findFirst().get();
		
		current.render(graphics);
		
	}
	
	/**
	 * @return The id of the current active state.
	 */
	public Object getCurrentKey() {
		return currentKey;
	}
	
	/**
	 * Changes the current id to the new current state's id.
	 * @param key The new id.
	 */
	public void setCurrentKey(Object key) {
		Object[] names = GraphicalStateConstants.GRAPHICAL_STATES;
		for (int index = 0; index < names.length; index++) {
			if (names[index] == key) {
				final GraphicalState current = states.stream().filter(
						state -> key == state.getKey()).findFirst().get();
				
				current.init();
				this.currentKey = key;
				return;
			}
		}
	}

}