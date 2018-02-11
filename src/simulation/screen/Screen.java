package simulation.screen;

import javafx.scene.Parent;
import simulation.Engine;
/**
 * 
 * @author Benjamin Hodgson
 * @author Katherine Van Dyk
 * @author Michael Acker
 * @date 1/30/18
 *
 * The Screen class is a super class that contains some basic methods used 
 * to generate some scene roots.
 * 
 */
public abstract class Screen extends Engine {

    protected Parent ROOT;
    protected Engine PROGRAM_ENGINE;

    public Screen(Engine programEngine) {
	PROGRAM_ENGINE = programEngine;
    }

    /**
     * If property ROOT is null, calls makeRoot() to generate the root. 
     * 
     * @return ROOT: The Parent node to be used in the Scene object. 
     */
    public Parent getRoot() {
	if (ROOT == null) {
	    makeRoot();
	}
	return ROOT;
    }

    /**
     * Method to construct the Parent object ROOT 
     */
    public abstract void makeRoot();

}
