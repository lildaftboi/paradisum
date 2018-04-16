package com.paradisum.game;

import java.awt.Graphics;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paradisum.Paradisum;
import com.paradisum.application.Application;
import com.paradisum.application.ApplicationConstants;

/**
 * A simple delta time service that handles game logic processing.
 * @author Carlos Aviles
 */
public final class GameService implements Runnable {
	
	/**
	 * The logger instance, for printing vital information to the console.
	 */
	private final static Logger LOGGER = LogManager.getLogger();
	
	/**
	 * The paradisum instance, to have an instance of the application.
	 */
	private final Paradisum paradisum;

	/**
	 * Instantiates a new service instance.
	 * @param paradisum The paradisum instance.
	 */
	public GameService(Paradisum paradisum) {
		this.paradisum = paradisum;
	}
	
	/**
	 * Represents a tick in the game engine.
	 */
	private void update() {
		paradisum.getStateManager().update();
	}
	/**
	 * Draws the graphics onto the application.
	 */
	private void drawGraphics() {
		final Graphics underlay = paradisum.getApplication().getGraphicsContext();
		
		paradisum.getStateManager().render(paradisum.getApplication().getGraphics2D());
		
		underlay.drawImage(paradisum.getApplication().getBackground(), 0, 0, ApplicationConstants.WIDTH, ApplicationConstants.HEIGHT, null);
		underlay.dispose();
	}

	@Override
	public void run() {
		try {
			final double updatesPerSecond = 60.0;
			final double updatePerNanoseconds = 1000000000 / updatesPerSecond;
			long recyclableNanosecond = System.nanoTime();
			double delta = 0;
			int framesPerSecond = 0;
			int ticksPerSecond = 0;
			long cachedTime = TimeUnit.MILLISECONDS.convert(System.nanoTime(), TimeUnit.NANOSECONDS);
			final Application application = paradisum.getApplication();
			
			while(application.isVisible()) {
				long currentNanosecond = System.nanoTime();
				
				delta += (currentNanosecond - recyclableNanosecond) / updatePerNanoseconds;
				recyclableNanosecond = currentNanosecond;
				if(delta >= 1) {
					update();
					ticksPerSecond++;
					delta--;
				}
				
				drawGraphics();
				framesPerSecond++;
				if(TimeUnit.MILLISECONDS.convert(System.nanoTime(), TimeUnit.NANOSECONDS) - cachedTime > 1000) {
					cachedTime += 1000;
					
					if (ApplicationConstants.DEBUG_MODE) {
						LOGGER.info(framesPerSecond +" frames per second | "+ ticksPerSecond +" ticks per second");
					}
					ticksPerSecond = 0;
					framesPerSecond = 0;
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

}