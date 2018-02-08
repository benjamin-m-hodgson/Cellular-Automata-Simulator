# Refactoring Design Discussion
Katherine Van Dyk (kmv10)
Ben Hodgson (bmh43)

## Duplication Refactoring
We handled duplication code refactoring primarily for NeighborhoodManager, whose subclasses reused a lot of the same methods. This duplication couldn't be amended because the Neighborhood Manager superclass and its subclasses also didn't have a proper parent/child relationship.

### Initial Configuration
The Neighborhood Manager superclass, in our initial simulation, handled getting the neighbors for a specific cell. The subclasses, one for each type of simulation, handled ruleset-specific methods with these neighbors, such as getting a free/fish Neighbor (for WaTor/Segregation) or returning the number of cells as a certain state (for Game of Life/Fire).

### Problems
This inheritance hierarchy didn’t make sense- as the superclass’ and the subclass’ functions were completely different and didn’t exist in a parent/child type way. This structure also limited flexibility, as the NeighborManager superclass handled returning neighbors for square shaped cells and didn’t take into possibility the fact that there could be other shapes of cells with different neighbor configurations.

### Solution
To solve this, we created a Neighborhood abstract superclass to take the place of the NeighborManager superclass. This superclass handles returning all the neighbors of a specific cell (NSEW or diagonal) based on its abstract methods. The subclass of the NeighborManager class is the SquareNeighborhood class, which returns neighbors in the context of a square cell. Triangle/Hexagonal subclasses will be implemented in the future as well.

To handle ruleset-specific methods dealing with neighbors, we’ve again created a NeighborManager superclass within the Ruleset package, which has three methods- getNeighbors(), getNeighborCount() and getNeighbor(), which are implemented by the rulesets, and then possibly overriden by subclasses. This implementation is overall much more organized.

## Checklist Refactoring
 We implemented checklist refactoring primarily for communication, flexibility, modularity and code smells. For communication, we primarily made ArrayList and other declarations more concise, and imported package-info.java classes for each package. For modularity, we split the simulation screen into three classes, for the control panel, grid, and toolbar. For the flexibility class, we made HashMaps into regular maps. For code smells, we adjusted our spacing. 

## General Refactoring
For general refactoring, we took away ‘magic values’ in our code by making them instance variables. We also made our methods shorter and cleaner, by creating private helper methods. In some instances, we were able to remove chunks of methods that were repeated, and create a helper method that eliminated the duplication.






