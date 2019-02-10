### Overview
I am using IntelliJ and maven.

I set language level to Java 9 so you need to run it in Java 9 at least. 

I am only using Java 9 for some helpful methods that will make my life 
easier but that are easily interchangable with methods from Java 8.

### Assumptions

* The maze files provided are valid if they meet certain requirements. 
  * They must have one start point. 
  * They must have one exit point.

If the above are met I will assume that they are valid mazes.
Trivial scenario is a maze with an entry and exit, nothing else.
It is simplistic but without any more knowledge and a person to ask for 
further information and for the purposes of this exercise it will do. 

* The entry and exit point don't count towards the empty spaces.

* The coordinate system is an (x,y) system extending to the bottom. 
  The upper left corner is (0,0) and `x` grows positive to the right and
  `y` grows positive to the bottom.

* As one reads the file with the maze, up is `NORTH`, down is `SOUTH`, left is
  `WEST` and right is `EAST`

### How to run tests and solution
 