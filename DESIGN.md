# DESIGN.md
## High-Level Design Goals
Our goal was to create a cellular automata program that can accurately animate a grid of cells,  given a set of rules within an XML file. Our program is flexible enough such that it can handle any type of legal rule set, and can easily be adapted to add new types of simulations, grids, and cells.
To accomplish this, we created a cell object that is closed to all other cells and only knows of its own state. A grid object holds an array of cells and has knowledge of all cells in it. The ruleset has no knowledge of the cells or grid, and the CurrentSimulation() class, an extension of the Engine controller class, apply the ruleset to all cells in the grid. The ruleset takes in a cell, processes it with the help of a NeighborManager, since a cell's neighbors influence its next state, and returns an integer representing its next state. 

## Adding New Features

### Adding New Simulation
To add a new simulation, several changes must be made across the Simulation, Configuration, and Visualization components of the project.

#### 1. Add a XMLDataTemplate Subclass
First, an XMLDataTemplate must be made for the simulation, which specifies how the XML file will look with regards to simulation-specific parameters, and how those parameters will be parsed in addition to the standard data fields. Examples of parameters that must be added are ruleset parameters and additional cell parameters. The abstract methods for creating a ruleset and grid must be completed as well, using the method signatures specific to the new simulation.

#### 2. Add a Ruleset Subclass
A ruleset subclass must then also be added, which implements the algorithmic logic behind the ruleset, or in other words, how a cell is updated. A corresponding neighbor manager must be added, where a cell's Neighborhood is defined (and should use existing methods from the Neighborhood package). A ruleset can leverage this NeighborManager to make the logic of updating a cell more readable.

#### 3. Adding a Cell Object
A cell subclass must also be created for this Ruleset, and should contain any additional cell parameters beyond a cell's state, if necessary. Otherwise, only the ColorCell() method must be implemented, which specifies what color a cell will possess at a certain state. In  tangent, a default color palette should be created and stored in the color.properties file.

#### 4. Adding to the Style Factory/XML Data Factory
Finally, in order to style the simulation, it needs to pop up in menus and have its parameters available to the Engine in order to update them to new values. Adding an option to the getParameters() method allows the parameters for a specific simulation type to be extracted and placed in menus, and eventually changed using the corresponding setParameters() method.

#### 5. Add XML Files
Finally, add XML files corresponding to the data template created above. The FileController() will automatically parse these files, and the simulation should automatically run if selected.

### Adding New Color Schemes
Adding a new color scheme is incredibly simple; all that must be changed is the color.properties file in the simulation package. To add a new color scheme, a new line should be created with the name of the simulation followed by five colors (the maximum number used by a simulation) in hexadecimal format. Then, that color scheme will be automatically added to the drop-down menu and applied to the relevant cells.

### Adding New Cell Shapes
To add a new cell shape, two primary items need to be modified: the ShapeFactory class and the ShapeHandler subclasses in the simulation.shapes package.

#### 1. Add a new ShapeHandler subclass
First, a new ShapeHandler subclass must be created for the specific cell shape being added. The ShapeHandler generates a polygon object to represent the cell on the display. Each unique subclass of the ShapeHandler must define the cell's height and width components along with its row and column position in the grid to calculate its position on the screen.

#### 2. Edit the ShapeFactory class
Second, the ShapeFactory class in the simulation.factoryClass package must be adjusted to accommodate the new shape. The ShapeFactory class determines which shape should be used and handles its construction on the grid, so the new shape must be added as an option.

## Justifying Major Design Choices
### Design Decisions
#### Cell Neighborhood Management
In the initial sprint, to handle both obtaining cell neighbor's from a grid, and using them in relation to a specific ruleset (to perhaps find a vacant neighbor, or get a count of neighbors of a specific state), we created one NeighborManager superclass and extended it to several subclasses, one for each simulation. The superclass, in this case, handled getting the neighbors of a cell, assuming a square shape and wrapped grid edges.

The second sprint highlighted how poor our initial design was- for one thing, we weren't utilizing a proper parent/child class relationship. Essentially, the superclass just contained a lot of common methods for obtaining neighbors, and the subclasses just used these different methods and spit back meaningful metrics to its corresponding ruleset. In actuality, a superclass should serve the same general purpose as its subclasses and not just help their subclasses by providing unrelated methods for their use.

In the end, we ended up creating a 'Neighborhood' object, which contains abstract methods for obtaining a cell's neighbors if assuming either finite or toroidal grid edges. This superclass is extended by the 'SquareNeighborhood' and 'TriangleNeighborhood' subclasses, each of which implements the methods detailed in the superclass. To hide the implementation details of obtaining neighbors (a lot of which is shared by either toroidal/finite grid edges), a NeighborhoodFactory class was created and holds these conditional statements.

Within the Ruleset package, a separate 'NeighborManager' package was created. The superclass contains an abstract method to get a cell's neighbors and also methods to get the number of neighbors a cell has both in general, or of a certain state. The NeighborManager class is extended by each of the rulesets, which implement the getNeighbors() classes based on what neighbors a ruleset requires, and also additional methods involving a cells neighbors for that simulation.

#### Separating Cell Display and State Handling
The second design decision our team had to make (and eventually change due to limited flexibility) was separating the cell logic and cell display. For the first sprint, when the cell shape was consistent across all simulations, we implemented a createDisplay() method within the cell, which made a rectangle of the appropriate dimensions/color for the cell object. Colors for each individual state were hard-coded into cell functionality.

However, when cell shapes/colors were subject to change in the final sprint, we decided that the cell displays should be held in a Shape array within the CurrentSimulation() class. This way, Shape size/type could be adjusted outside of an individual cell, and only a cell's current state/color were needed to update the display. A ShapeHandler() superclass, and subclasses for each type of shape (Rectangle/Triangle) were used to figure out cell positioning on the grid based on grid size and number of cells. This design was much more flexible than our original idea, and makes adding a new shape much simpler. To implement a hexagon, for instance, only a ShapeHandler class needs to be added, as well as a user option to use a hexagon for dropdown menus.

In the second sprint, cell colors were also read from a resources file and each cell had their 'Color Scheme' set after a user inputted whatever scheme they desired. The Colors were held in the cell object as an array of hex strings, and were indexed based on current cell state. This made the number of instance variables decrease within the cell and made it easier to switch out color arrays. In the future, however, I would make the color scheme a property of the grid, instead of assigning it to each individual cell (since all cells hold the same color scheme anyway), and then assign cell colors based on their integer states within the Grid object.

## Assumptions

