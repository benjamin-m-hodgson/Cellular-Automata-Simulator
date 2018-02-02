# cellsociety 

Cell Society : Inheritance Review Questions
* Authors:
    * Martin Muenster (mm617)
    * Ben Hodgson (bmh43)

### Part 1
* What is an implementation decision that your design is encapsulating (i.e., hiding) for other areas of the program? 

bmh43: This implementation encapsulates generating and updating the animations for different 
scenes in the program. 

* What inheritance hierarchies are you intending to build within your area and what behavior are they based around?

bmh43: We are using an abstract super class named `Screen.java` to create different sub classes that can generate specific scenes for the program and using a css file named `default.css` to handle formatting the style of these scene nodes. 

* What parts within your area are you trying to make closed and what parts open to take advantage of this polymorphism you are creating?

bmh43: Most of the methods within the Screen sub classes are private, so the Engine and other classes have no knowledge of how the root of the scene is generated. The `getRoot()` method is public so the Screen class can tell the Engine class the root it created. Each sub class overrides the `makeRoot()` method in the Screen class to allow for customized, specific root creation to serve different purposes. 

* What exceptions (error cases) might occur in your area and how will you handle them (or not, by throwing)?

bmh43: The XML file contains all of the titles and information for different simulations. If the Screen object tries to generate a simulation but receives insufficient data from the XML file a NullPointerException will most likely be thrown and a splash screen will be displayed.

* Why do you think your design is good (also define what your measure of good is)?

bmh43: I believe my design, using a css file to contain style formatting and separate classes to generate each screen, is good because it is flexible and readable. To change to look of the entire program all that is required is changing the information in the `default.css` file. To add new scenes to the program, a new Screen sub class can be created to generate the root object.  

### Part 2

* How is your area linked to/dependent on other areas of the project?

bmh43: The Engine and Screen class are linked primarily because the Screen needs to access certain methods in the Engine class in response to certain user input. For example, when the user clicks the **Simulate** button it calls a method in the Engine class to generate the simulation and change necessary properties.

* Are these dependencies based on the other class's behavior or implementation?

bmh43: Yes, the Screen sub classes depend upon methods in the Engine class, but they are ignorant to how these methods are implemented. 

* How can you minimize these dependencies?

bmh43: To minimize, or at least make dependencies most clear, I am trying to adhere to the dependency inversion principle. This means the Engine class should have minimal dependency upon the Screen sub class because this sub class is a lower-level module. By contrast, the Screen sub classes are dependent upon the Engine class and how it implements certain methods because it is a higher-level module.

* Go over one pair of super/sub classes in detail to see if there is room for improvement. 

bmh43: It might be best to remove the `makeButton(String text)` and the `makeLabel(String text)` methods in the Screen super class because some scene roots might not require buttons or labels. 

### Part 3

* Come up with at least five use cases for your part (most likely these will be useful for both teams).

    1. Generate the start screen: Creates a new sub class of the Screen class and calls its `getRoot()` method to return the root for this new scene. This root is then set into the Program Scene using `setRoot(Parent root)`. 
    2. Generate a simulation: 
    3. Change the simulation:
    4. Change the button and label style formatting:
    5. Resize the window: 

* What feature/design problem are you most excited to work on? 

bmh43: Even though its a small feature, I'm most excited to make the Screen nodes re-sizable. This feature is necessary to make not only the program code design, but also the front end interface as flexible as possible. 

* What feature/design problem are you most worried about working on?

bmh43: I'm most worried to work on/handle changing simulations. In my game project, my largest bug involved changing the Scene to generate different displays. To try to minimize problems in this project I am designing the Screen classes to return Scene roots rather than full scenes. This way the same scene will be used for the duration of the application and different displays will be triggered by changing the Scene root. 


