# cell society
## Timeline
### Start Date: January 29, 2018
### Finish Date: February 12, 2018
### Approximate Hours Spent:
#### Katie: ~40 hours
Breakdown:
I spent ~20 hours for the first sprint, developing most of the configuration/backend for project functionality. For the second sprint, I also spent ~20 hours refactoring code that I had written previously, as well as adding new configuration, neighbor, and styling features.
#### Ben: ~40 hours
Breakdown:
I spent between 15-20 hours on both sprints. Throughout the entirety of the project my 
responsibilities included designing the front end user interface and connecting features/styling
components with the back end logic.  
#### Michael: ~30 hours
### Primary Roles:
#####  Ben:
Working with front end JavaFX elements and core operations. This included designing the various
screens displayed to the user throughout program use and determining how these different screens interacted with each other to create a logical, user friendly interface. I also was responsible
for classes that handled shape generation. This included calculating shape size and position for optimal tiling. 
##### Katie:
For the initial sprint in the project, I developed the XML Configuration files, including the XMLParser and FileController. I also populated the Cell/Grid superclass methods and wrote subclass methods for the four initial simulations. I wrote the logic behind the initial four ruleset classes, and also developed the Neighborhood superclass and subclasses.

For the second simulation, I further developed the XML configuration files, including the XML writer and generating cell states both by location and randomly. In the simulation package, I refactored the initial 'NeighborManager' to both a neighborhood class, which includes the include Finite/Toroidal subclasses, and the NeighborManagers (with ruleset-specific methods). I helped developed the Settings/XML writer features on the frontend, as well as the Graph feature, and styling functionality such as choosing the cell color scheme and changing simulation parameters.
##### Michael
In the simulation package, developed cell subclasses, rulesets, neighborhood managers, and XMLData classes for SugarScape and Rock, Paper, Scissors simulations.
##### Resources
##### Running the Project
To run the project, run the ‘Main’ method in the Driver class.
##### Resource Files Required By Project
* Ben Hodgson
	* http://hg.openjdk.java.net/openjfx/8/master/rt/file/f89b7dc932af/modules/controls/src/main/resources/com/sun/javafx/scene/control/skin/modena/modena.css
	* https://www.iconfinder.com/icons/2561367/pause_icon#size=128
	* https://www.iconfinder.com/icons/227568/play_icon#size=128
	 * https://www.iconfinder.com/icons/2561275/forward_skip_icon#size=128
	* https://www.iconfinder.com/icons/352446/replay_icon#size=128
	 * https://docs.oracle.com/javafx/2/api/javafx/scene/doc-files/cssref.html#label
	 * https://www.tutorialspoint.com/javafx/2dshapes_polygon.htm
	 * https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/TextFormatter.html
   
* Katie Van Dyk
	* https://stackoverflow.com/questions/5751335/using-file-listfiles-with-filenameextensionfilter
	* https://www.tutorialspoint.com/java_xml/java_dom_parse_document.htm
	* https://www.mkyong.com/java/how-to-create-xml-file-in-java-dom/
	* https://docs.oracle.com/javafx/2/charts/line-chart.htm#CIHGBCFI
### Features of Project
#### Configuration
Able to parse all XML files in the ‘xml’ directory before the simulation begins based on simulation data templates. The XML writer saves the current grid into an XML file with the filename of the user’s choice. The XML parser can parse files with hard-coded cell locations and also by randomly generating states.
#### The Start/Settings Screen
Our program automatically parses any XML input file in the XML directory. It error checks by first not parsing any file that doesn't end in ".xml" and also by throwing a specific error if a file includes invalid parameters. The settings screen allows a user to style any feature of the grid, such as cell coloring scheme, shape, size, and how grid edges behave.
#### The Control Panel
The control panel allows the user, during a simulation, to control how the simulation is behaving. A user can speed up the simulation, step through generation-by-generation, pause and reset the current simulation. The user can also write the current grid to an XML file with their choice of name, as specified by a text field. There are also labels of the current simulation and generation.
#### The Grid
The grid resizes based on the cell-size specified by the user (default if none is given). The graph below the cell screen keeps track of the count of each type of cell in the above simulation, and updates automatically as the generation increases.
#### The Settings Panel
The settings panel can change the state of any cell at any given location, according to user input. The settings can also change the parameters of the simulation based on a dropdown menu, which contains parameters specific to the currently running simulation.

### Known Bugs
The user can update any parameter of their choosing specific to a certain simulation. However, our program currently only checks error values for parameters by referencing the current value of the parameter and using that as a maximum. In  the future, the parameter values could be controlled by a  user input of minimum and maximum (perhaps within the XML files under new tags).

### Interesting Datafiles
The 'Random Segregation' datafile is probably the most interesting datafile to run, as it is a 100 x 100 grid of randomly generated cells. In the first generations, cells flip flop between slides and by the end, congregate in clouds. The 'Standard File' grid is also an interesting file to run, especially when states are hand-set by the user.




