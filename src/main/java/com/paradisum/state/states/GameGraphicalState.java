package com.paradisum.state.states;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.paradisum.Paradisum;
import com.paradisum.state.GraphicalState;

/**
 * Represents the game state.
 * @author Carlos Aviles
 */
public final class GameGraphicalState extends GraphicalState {

	/**
	 * Instantiates a new game state instance.
	 * @param key The unique key of this state.
	 * @param paradisum The paradisum instance.
	 */
	public GameGraphicalState(Object key, Paradisum paradisum) {
		super(key, paradisum);
	}

	@Override
	public void render(Graphics2D graphics) {
		paradisum.getLevelManager().getLevel().render(graphics);
		paradisum.getLevelManager().render(graphics);
	}

	@Override
	public void init() {
		paradisum.getLevelManager().init();
	}

	@Override
	protected void handleClickAction(Rectangle clickArea) {
		paradisum.getLevelManager().getLevel().handleClickAction(clickArea);
	}

	@Override
	protected void handleMoveAction(Rectangle cursorArea) {
		paradisum.getLevelManager().getLevel().handleMoveAction(cursorArea);
	}

	@Override
	protected void handleKeyboardAction(int[] keys) {
		paradisum.getLevelManager().getLevel().handleKeyboardAction(keys);
	}

	@Override
	protected void execute() {
		paradisum.getLevelManager().getLevel().update();
		paradisum.getLevelManager().update();
	}

	@Override
	protected void stop() {
		paradisum.getLevelManager().getLevel().stop();
	}

}