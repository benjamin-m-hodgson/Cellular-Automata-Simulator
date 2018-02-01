
# Maddie Wilkinson (Team 02) and Michael Acker (Team 01)
## Part 1
#####1. *What is an implementation decision that your design is encapsulating (i.e., hiding) for other areas of the program?*
#### Michael:
Our Cell class is encapsulated from other areas of the program. Only the Grid class deals with the other Cells through its method, so any information exchange between the Cells and the rest of the program occurs through the Grid’s methods.
#### Maddie:
Our Cell class is hidden from the UI and interacts mainly with the Grid class, which updates its Cells. The Simulation class has some marginal interactions with Cells—it uses them to determine how Cells in the Grid should be changed when it updates two different Grids.

#####2. *What inheritance hierarchies are you intending to build within your area and what behavior are they based around?*
#### Michael:
Our Cell class is an abstract parent class with several different kinds of Cell subclasses inheriting it. This is based around the different behaviors of Cells in different types of simulations. That is, a Wa-Tor World Cell has more state information at a given point than a Game of Life Cell, so it has a seperate subclass to handle more instance variables. Our RuleSet, Grid, and XMLData classes have similar simulation-based inheritance trees, because each one has different specific needs depending on what type of simulation is being run.
#### Maddie:
Our Simulation Class is an abstract superclass, with the different types of simulations serving as subclasses for the general simulation. For instance, GameofLifeSimulation or FireSimulation could be subclasses. Simulation is build around organizing the rules and changes that occur within the simulation. We also have an inheritance hierarchy for Cells; the general Cell class has subclasses of different types of Cells, like LiveCell, FireCell, etc. The Cell class’s behavior includes checking its neighbors and calculating how it should update.

#####3. *What parts within your area are you trying to make closed and what parts open to take advantage of this polymorphism you are creating?*
#### Michael:
The Cell class is closed to the rest of the program, but is open to the different types of Cell subclasses and the Grid class.
#### Maddie:
The Cells are closed to classes besides the Grid class, and are mostly closed to the Simulation class.
#####4. *What exceptions (error cases) might occur in your area and how will you handle them (or not, by throwing)?*
#### Michael:
One exception that might occur is a NullPointerException if a nonexistent cell is referenced. I will handle this by ensuring that cell- and grid-based methods can only use cells with boundaries between zero and the grid size. Lots of other exceptions could occur by including incorrect or invalid data in the XML file, and those will be handled by making sure that XML input data adheres to certain parameters.
#### Maddie:
A class might try to use the wrong kind of Cells in the simulation, which would be handled by showing a clear error message instructing the user what to change (inputting a different file, choosing the correct Simulation, etc.).
#####5. *Why do you think your design is good (also define what your measure of good is)?*
####Michael: 
It uses a great deal of modularity in implementing its Cell, Grid, RuleSet, and XMLData classes, as well as the data and variables relevent to those classes. This allows different types of simulations to be handled fairly easily, and also makes it so that types of simulations can be added or removed without affecting the design of the rest of the program in a meaningful way.
#### Maddie:
The information is encapsulated and Cells and Grids deal with their own operations without relying on other classes, which makes the design flexible, easily maintained, and easily changed to add new features.
### Part 2
#####1. *How is your area linked to/dependent on other areas of the project?*
#### Michael:
My area is primarily designing the Cell class, Grid class, and RuleSet class, as well their subclasses for the different simulations as the methods used by them. It is linked to the XML data area of the project because initial conditions and parameters for the rulesets and for each cell are variable and must be loaded via XML file. It is also linked to the GUI area of the project because each time the GUI is updated during the animation, data must be obtained from the cells and grid to tell the engine how each element should be updated.
#### Maddie:
The Cells are linked to the Grid and the Simulation. The Cells themselves are not dependent on either of these classes, but both of them use cells in the functioning of the program, and the Simulation class’s role is simply to create the appropriate Cells using information given to it by the Cell class itself.

#####2. *Are these dependencies based on the other class's behavior or implementation?*
#### Michael:
The parameters that tell the ruleset how to behave, as well as the initial states of the cells, are based on the behavior of the XML data. Then, the implementation of the engine and driver uses data and methods from the grid, cells, and ruleset in order to operate.
#### Maddie:
If so, the dependencies are very minimal. The main thing it relies on is the position of the other Cells in the Grid.

#####3. *How can you minimize these dependencies?*
#### Michael:
We minimize these dependencies by making the project as a whole highly modular, so that each class handles its own business, and other classes reference them only when explicitly necessary.
#### Maddie:
This could be minimized by having Cells hold information about their own position, and having them use this to calculate what they should update to, rather than using their position from the Grid.
#####4. *Go over one pair of super/sub classes in detail to see if there is room for improvement.*
#### Michael:
The Cell class has a WaTorCell subclass for use in the WaTor World simulation. The WaTorCell has more states than a typical cell, and its own methods for returning those states as necessary. I think that this is a good implementation because it prevents other Cells from having extraneous methods and variables, but allows Cells in WaTorWorld to have all of the information they need.
#### Maddie:
The Cell class has different subclasses for each type of cell, not just each simulation, which will be better for information encapsulation and for determining how Cells should change.

#####5. *Focus on what things they have in common (these go in the superclass) and what about them varies (these go in the subclass).*
#### Michael:
The Cell and WaTorCell subclass share many things; they both have a basic state, methods to update and retrieve that state, and coooooooooooooooooordinates. However, the WaTorCell has additional variables and methods associated with the unique needs of WaTorWorld.
#### Maddie:
Each Cell has similar methods to determine its position and to find its neighbors, but subclass Cells have different rules to determine how they update.

### Part 3
#####1. *Come up with at least five use cases for your part (most likely these will be useful for both teams).*
Retrieving the state of a cell, updating the state of a cell, retrieving what color a cell should be, retrieving the set of neighbors for a cell, and calculating the next state of the cell using the ruleset of the simulation.
#####2. *What feature/design problem are you most excited to work on?*
#### Michael:
I’m most excited to write the ruleset classes to determine, for each simulation, how the states of each cell should be updated for the next generation.
#### Maddie:
I’m most excited to work on the interactions between Cells and to implement how the Cell class interprets the rules gotten from the Simulation class.

3. *What feature/design problem are you most worried about working on?*
#### Michael:
 I’m most worried about connecting all of our modular classes together, so that when all of my team members are finished with our respective assignments, our classes will work with each other correctly.
#### Maddie:
 I’m concerned about allocating time efficiently between working on the different features.
