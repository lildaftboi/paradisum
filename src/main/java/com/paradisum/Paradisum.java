package com.paradisum;

import java.awt.EventQueue;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.base.Stopwatch;
import com.paradisum.application.Application;
import com.paradisum.game.GameService;
import com.paradisum.game.model.level.LevelManager;
import com.paradisum.state.GraphicalStateManager;

/**
 * Represents the core of the application. This class executes initialization logic.
 * @author  Carlos Aviles
 */
public final class Paradisum {
	
	/**
	 * The logger instance, for printing vital information to the console.
	 */
	private final static Logger LOGGER = LogManager.getLogger();
	
	/**
	 * The application instance, for displaying the window.
	 */
	private final Application application = new Application();
	
	/**
	 * The service instance, for executing game logic services.
	 */
	private final GameService service = new GameService(this);
	
	/**
	 * The state manager instance, used for executing graphics.
	 */
	private final GraphicalStateManager stateManager = new GraphicalStateManager(this);
	
	/**
	 * The level manager instance, for handling entities in the game.
	 */
	private final LevelManager levelManager = new LevelManager(this);
	
	/**
	 * A package private constructor, to avoid external instantiation.
	 */
	Paradisum() {
		
	}
	
	/**
	 * Initialize logic that is valuable for the application.
	 * @throws Exception Any problems encountered whilst initializing the logic services.
	 */
	void init() throws Exception {
		final Stopwatch stopwatch = Stopwatch.createStarted();
		
		LOGGER.log(Level.INFO, "Attempting to initialize Paradisum...");
		
		initResources();
		initApplication();
		initService();
		
		LOGGER.log(Level.INFO, "Starting Paradisum took " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + " milliseconds.");
	}
	
	/**
	 * Initializes any game related resources.
	 * @throws Exception Any problems with initializing the resources.
	 */
	private void initResources() throws Exception {
		LOGGER.info("Loading resources...");
		
		getStateManager().init();
		
		LOGGER.info("Finished loading resources!");
	}
	
	/**
	 * Initializes the {@link Application}'s resources
	 * @throws InterruptedException Any problems with the thread.
	 * @throws InvocationTargetException Thrown by an invoked method or constructor.
	 */
	private void initApplication() throws InvocationTargetException, InterruptedException {
		LOGGER.info("Starting up the application...");
		
		EventQueue.invokeAndWait(getApplication()::build);
		
		LOGGER.info("Finished loading the application!");
	}
	
	/**
	 * Initializes the game logic service.
	 */
	private void initService() {
		LOGGER.info("Starting up game logic services...");
		
		final Thread game = new Thread(service);
		game.setName("GameService");
		game.start();
		
		LOGGER.info("Finished loading game logic services!");
	}
	
	/**
	 * @return The application instance.
	 */
	public Application getApplication() {
		return application;
	}
	
	/**
	 * @return The game service instance.
	 */
	public GameService getService() {
		return service;
	}
	
	/**
	 * @return The state manager instance.
	 */
	public GraphicalStateManager getStateManager() {
		return stateManager;
	}
	
	/**
	 * @return The level manager instance.
	 */
	public LevelManager getLevelManager() {
		return levelManager;
	}

}