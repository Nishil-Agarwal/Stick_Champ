Sec - B
Group Number - 57
Members:
1- Nishil Agarwal : 2022334
2- Namit Gupta : 2022314

Given code is an implementation of Stick Hero Game using JavaFX.
It uses the OOPS concepts to make a better code with abstraction and Design Patterns.

1. To start the game press Start on the front screen.
2. User can look at the two mountains with stick figure on one mountain.
3. To extend the stick , user needs to hold the mouse button.
4. Cherries are generated randomly .
5. To collect a cherry user may have to flip the character.
6. Character can be flipped using "S" key on keyboard.
7. Game terminates when Stick extended doesn't land on the mountain.
8. Game also terminates when player collides with mountain in the flipped state.
9. Revive button appears when the Game ends. A user can revive if he/she has collected 5 or more cherries.
10. Game also offers the functionality of pause button to pause the game in between.


Game use options:
Mouse : stick extend
"S" : Flip option
Click on any button to select it

Run StickTest.java to see the implementation of JUnit Test Cases.
Singleton Design Pattern used in Player Class as only one object was needed.
Decorator Design Patter used in Read and Write File Methods in Streams Class to read and write from score and cherry txt files.
Avoid using Restart and Start Game Method once the Game is running.
On first run it shows compete implementation of game. Though have some bugs in the implementation of Restart Button.

Commands to run the code
1. mvn clean javafx:run



If any issue arises in rendering of images please replace the following paths
1. Cherry. Java (line -23) with Cherry.png
2. Player .Java (line -44) with Stickman.png
3. Player .Java (line -72) with Stickman.png