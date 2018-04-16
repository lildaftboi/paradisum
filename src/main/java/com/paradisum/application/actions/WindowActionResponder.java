package com.paradisum.application.actions;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * A model that deals with window related actions.
 * @author Carlos Aviles
 */
public final class WindowActionResponder extends WindowAdapter {

	@Override
    public void windowClosing(WindowEvent event) {
        System.exit(0);
    }
	
}