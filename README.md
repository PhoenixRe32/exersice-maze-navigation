### Overview
I am using IntelliJ and maven.

I set language level to Java 9 so you need to run it in Java 9 at least. 

I am only using Java 9 for some helpful methods that will make my life 
easier but that are easily interchangable with methods from Java 8 or 
maybe some external libraries (e.g. Guava).

I am going to be referring to the whole solution as a game since this is
what I associate it with. Doesn't mean it could be something else.

### Assumptions

* The map files provided are valid if they meet certain requirements. 
  * They must have one start point. 
  * They must have one exit point.

If the above are met I will assume that they are valid mazes.
Trivial scenario is a map with an entry and exit, nothing else.
It is simplistic but without any more knowledge and a person to ask for 
further information and for the purposes of this exercise it will do. 

* The entry and exit point don't count towards the empty spaces.

* The coordinate system is an (x,y) system extending to the bottom. 
  The upper left corner is (0,0) and `x` grows positive to the right and
  `y` grows positive to the bottom.
  
* For the edges of the map I don't do anything, if trying to see what is
  there it will return a null and I don't act on it.

* As one reads the file with the map, up is `NORTH`, down is `SOUTH`, left is
  `WEST` and right is `EAST`
 
* The map explorer is the interface for the interaction  (between the 
  game and user).

* The map explorer contains all the maps included in the game.
  
* The explorer and the map will have a composition relation;
  Explorer HAS-A map, the assumption being that the map object is 
  basically a map. 

### How to run tests and solution
 