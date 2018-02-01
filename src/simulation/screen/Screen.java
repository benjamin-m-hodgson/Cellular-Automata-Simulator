package simulation.screen;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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
public abstract class Screen {
	
	protected Parent ROOT;
	
	/**
	 * If property ROOT is null, calls makeRoot() to generate the root. 
	 * 
	 * @return ROOT: The Parent node to be used in the Scene object. 
	 */
	public Parent getRoot() {
		if (ROOT == null) {
			//System.out.printf("Invalid root! Root is null.\n");
			makeRoot();
		}
		return ROOT;
	}
	
	/**
	 * Method to construct the Parent object ROOT 
	 */
	public abstract void makeRoot();
	
	/**
	 * Creates a Label object bearing the desired text.
	 * 
	 * @param text: A String containing the text to be displayed on the Label
	 * @return Label object to be put in the Scene root
	 */
	public abstract Label makeLabel(String text);
	
	/**
	 * Creates a Button object bearing the desired text.
	 * 
	 * @param text: A String containing the text to be displayed on the Button
	 * @return Button object to be put in the Scene root
	 */
	public abstract Button makeButton(String text);
	
}
