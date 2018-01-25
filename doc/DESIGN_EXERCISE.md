People Who Worked on This:
Ben Hodgson
Katherine Van Dyk 
Michael Acker


# EXERCISE 1: INHERITANCE REVIEW (Using Ben's Game)

BLOCK HIERARCHY: Block superclass, subclass for each type of block (One-hit, Two-hit, Three-Hit, Speed Boost Block)

public class Block{
	
	
	// Constructor, with coords (x,y) as inputs
	// Make rectangle (x,y)
	public Block(int x, int y){
	
	// Return rectangle that constructor makes 
	public Rectangle getBlock(){
	}
	
	// Returns number of points for hitting block
	public int getPoints(){
	}
	
	// Modifies block when bouncer hits it
	public void handleHit(){
	}
	
	// Returns if block can be removed from screen
	public boolean isDead(){
	}
	
}

POWER UPS HIERARCHY: PowerUp interface

public interface PowerUp {
	
	// @return The Shape of the PowerUp
	public Shape getShape();
	
	/**
	* Updates the position of the PowerUp
	*
	* @param elapsedTime: time since the last frame
	*/
	public void update(double elapsedTime);
	
	/**
	* Checks to make sure the ball stays in bounds
	*
	* @param gameScene: the level scene containing the ball
	*/
	public boolean inBounds(Scene gameScene);
	
	/** 
	* Updates the velocity components of the PowerUp after a collision
	*
	* @param gamePaddle: the paddle this PowerUp is colliding with
	*/
	public boolean isColliding(Paddle gamePaddle);
	
	/**
	* Animation to fade out the PowerUp
	*/
	public void fade();


# EXERCISE 2: CELL SOCIETY, HIGH LEVEL DESIGN

1. Cell Object
	/**
	* Checks to make sure the ball stays in bounds
	*
	* @param x, y: Refer to position in cell array
	* @param 0, 1, 2: Representing initial state of cell (regardless of simulation type)
	*/
	Cell Object- Parent Class
	public Cell(int x, int y, int initState){
	}
	
	/**
	* Gets state of cell
	*/
	public int getState(){
	}

	/**
	* Sets state of cell
	* @param s: Set state of cell to s
	*/
	public int setState(int s){
	}
	
	/**
	* Creates rectangle based on state (determines color) 
	* @param s: State of the cell
	*/
	private void createRectangle(int s){
	}
	
	/**
	* Gets x position of cell
	*/
	private int getX(){
	}
	
	/**
	* Gets y coord of cell
	*/
	private int getY(){
	}

2. How does a Cell know about its neighbors? How can it update itself without effecting its neighbors update?
	Ideas to see if each cell knows about neighbor: store var nextState 
	Iterate once to update, iterate another time to change grid based on update (seems messy/takes a lot of time for larger grid)
	

3. What is the grid? Does it have any behaviors? Who needs to know about it?
	-Game driver needs to know about it
	-Attributes: dimensions (2D Cell Array) 
	-Behaviors:
		-Clear: clear grid of all cells
		-Initialize: put cells in every position
		-Swap states: swap two cells
		-isSatisfied: boolean that determines if grid has reached stable state
		-Get Stats: Return number of each type of cells
		
4. What information about a simulation needs to be the configuration file?
	Initial states
	*COMING BACK
	
	
# EXERCISE 3: CRC CARDS 
Used in discussion of design in Exercise 2

# EXERCISE 4: USE CASES

Apply the rules to a middle cell: set the next state of a cell to dead by counting its number of neighbors using the Game of Life rules for a cell in the middle (i.e., with all its neighbors)
-Create method HandleNeighbors()
	-Do we pass parent grid into cell object?
	-Still doesn't know other cells in grid
	-Grid would then have method getNeighbors (pass in cell object), determine neighboring cells
-Cell uses own method to process neighbors to determine what its next state should be
-Tell cell to update state and wipe next state in next iteration

Apply the rules to an edge cell: set the next state of a cell to live by counting its number of neighbors using the Game of Life rules for a cell on the edge (i.e., with some of its neighbors missing)
-Steps are the same
-Get neighbors method- check bounds of grid in the getNeighbors() function
-Grid will know its size

Move to the next generation: update all cells in a simulation from their current state to their next state and display the result graphically
-Discussed in process above

Set a simulation parameter: set the value of a parameter, probCatch, for a simulation, Fire, based on the value given in an XML fire
-Class reading XML will handle setting values/parameters

Switch simulations: use the GUI to change the current simulation from Game of Life to Wator
- Would have to create new cell objects- have to think about how we translate among different cell object
(are there 'equivalent' states among different simulations)
-Create a simulation super class?
	

	