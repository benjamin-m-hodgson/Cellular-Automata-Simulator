
# Part 1
* What is an implementation decision that your design is encapsulating (i.e., hiding) for other areas of the program?
Katie: The parsing of the files is hidden from the user. All they have to do is instantiate a file controller and then call the parseFile() method, and all files will be parsed and the data stored in the appropriate hashmaps.

Erik: The user interface will not be hidden from the user as it incorporates the user input with the functions of the other classes in order to allow the user to decide what type of simulation to run.

Andrew: One part of program that will encapsulated is how it keeps track of the status of the surrounding cells. The neighborhood of each cell will be able to simple be called and asked for a status and it will be in charge of assessing and reporting the state of the world for that specific cell. 

* What inheritance hierarchies are you intending to build within your area and what behavior are they based around?
Katie: XMLData is an abstract class, which is extended by FireXMLData, SegregationXML, and all other types of simulations that require different types of parameter/cells.

Erik: The UserInterface class will handle the initialization of the buttons, drop-down menus, etc...as well as adding functionality to these components. The viewer class will then inherit this information and paint the components to the scene so the user can interact with them.

Andrew: The specific cell classes will all implement an abstract cell class. The abstract class will have the basic location, neighborhood, and getter methods, while the specific cells will have the implementation that is simulation specific, the rules. The specific cells will be able to be told update, and using the information stored by the abstract implementation, know what its next state should be.

* What parts within your area are you trying to make closed and what parts open to take advantage of this polymorphism you are creating?
Katie: My XML Parser is closed to all other areas of the program, as well as the data files (such as XMLData). The File Controller is open to the driver, which creates an instance of it and passes the appropriate maps/rules to the game engine. 

Erik: The User Interface must be open so that it can obtain the necessary information from the other classes, while viewer will be closed as it just sets the scene.


Andrew: Cell class will be open as it needs to know the information from neighborhood and the surrounding cells. The simulation class will also be open as it has to be able to take in information from the user.

* What exceptions (error cases) might occur in your area and how will you handle them (or not, by throwing)?
Katie: A bad XML input will throw an error message depending on what’s wrong with it- if it’s null, empty, or contains false data. 

Erik: The user interface and viewer must be able to handle all errors that would stem from incorrect user inputs. For example, if the user inputs a word in the “speed” text field, rather than a number this would throw an error. The user interface must also be able to display error messages from errors that may occur in the back-end of the project.

Andrew: My portions of the project should throw limited exceptions. The cells and neighborhood should essentially never throw an exception, and neither should the simulation class unless the program itself is broken.  

* Why do you think your design is good (also define what your measure of good is)?
Katie: My measure of good is flexibility, both in being able to handle different types of input data and being able to handle bad input data. My design is good because the data portion of it is made with an abstract class, which can easily be applied to other simulations. 

Erik: I think my design is good because it has flexibility by adding methods for creating different types of button, menus, etc...Perhaps the User Interface could be split into separate smaller classes, however, I think that handling the placement of buttons and the actions the buttons create within the same class makes sense.

Andrew: I think that a good design is clear and able to be adapted easily. I think that our design is good is than it is modular so it should be very easy to add additional simulations after we have one work, which gives us flexibility.

# Part 2
*How is your area linked to/dependent on other areas of the project?
Katie: My area is dependent upon the cell and grid classes, as my configuration uses those constructors to make grids for each simulation. My part of the program also depends on the ruleset constructors, as I make rulesets using the XML input parameters as well. 

Erik: My area is linked to the Grid within the project as it needs to display the grid to the user, as well as update the grid with each step in the animation. It is also linked to the ParseXML class as the user will have to input the XML file corresponding to the desired simulation type.
Andrew: My area is dependent upon the GUI to provide the user input and on the XML parsing to read in the properties of the simulation itself. Additionally my parts are dependent upon the grid class to instantiate all of the cells and call the updates on them.

Are these dependencies based on the other class's behavior or implementation? How can you minimize these dependencies?
Katie:  These dependencies are based on the implementation of the cell/grid class. The XML template also is, as the format needs to include everything in the cell/grid/rule constructor method signatures. There is no real way to avoid this dependency, as the XML file format/parser must meet the needs of the objects it implements. 
Erik: These dependencies are based on the implementation of the parseXML class and the behaviors of the grid class. It is difficult to minimize the dependency on parsing the XML as this data is necessary to initialize the grid.

Andrew: These dependencies are based on the behavior of the GUI and the implementation of the XML parsing. Both of these dependencies are difficult to reduce as they provide program critical information.


Go over one pair of super/sub classes in detail to see if there is room for improvement.
Katie: There’s definitely room for improvement in the XMLData/FireXMLData super/subclasses. I can make sure to remove unnecessary getters/setters and to minimize the amount of duplicate code between subclasses. 

Focus on what things they have in common (these go in the superclass) and what about them varies (these go in the subclass).
Katie: The XMLData superclass and FireXMLData subclass both share the same type of grid object (a standard grid). They also share attributes such as cell state, and position (this is in similar data across XML files).e They differ in what type of cell objects they’re using and what ruleset parameters/subclasses they implement. 

Erik: The Viewer class inherits the functionality from the User Interface class to then put the components on a scene. Within my User Interface class I handle the placement of the buttons and the action events of them as well, which could lead to a rather long class that could potentially be broken up into smaller subclasses to avoid duplicate code.


Andrew: The cell class is an abstract class which will have what is common between all of the cells. This will entail the neighborhood of the cell, the location of the cell, and the signature of the update method. The specific cell will then have all of the program specific data, including how the cell is drawn, the state of the cell and the implementation of the update method.

# Part 3
Come up with at least five use cases for your part (most likely these will be useful for both teams).
Katie:
Reading an XML file with valid input data, creating appropriate HashMaps and passing these onto the Driver.
Reading an empty XML file and throwing an appropriate exception.
Reading an invalid XML file, knowing this, and throwing an appropriate exception. 
Reading an XML file with missing data that can be filled with a default parameter.
Reading an XML file with a duplicate simulation name, and handling appropriately without crashing. 

Erik:
User decides to switch simulation in the middle of running it.
User puts an incorrect input type into the “speed” text field which will create an error.
Use wishes to exit a simulation in the middle of running it.
User decides to add a new XML file.
User wants to pause the simulation while its running.

Andrew:
Updating the cell
Ording the loading of a new XML file for the new simulation
Updating the simulation parameters
Giving information for the rendering of the simulation
Executing the user entered controls.

What feature/design problem are you most excited to work on?
Katie: I’m most excited to work on constructing the RuleSet algorithms, and completing the process of reading in input parameters, and constructing an appropriate algorithm.  
Erik: I’m most excited to work on adding the different components to the scene as this will allow me to get creative in the style of the User Interface. (UX yay!)
Andrew: I am most excited to work on simulation class as I think it will be fun writing the overall program control.

What feature/design problem are you most worried about working on?
Katie: I’m most worried about working on the exception class in the configuration folder. I’m new to throwing exceptions and want to make sure that I’m covering every instance in the right way. 
Erik: I’m most worried about having exceptions thrown at me from the back-end of the project that I don’t know how to handle or display to the user.

Andrew: I’m most worried about writing the specific cell update methods as the small details will really matter.

