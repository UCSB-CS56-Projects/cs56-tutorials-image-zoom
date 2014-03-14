cs56-tutorials-image-zoom
=========================

 - __Navigation__: 
   - [Introduction](https://github.com/PARanOiA1120/cs56-games-simon-says/blob/master/README.md#introduction)
   - [Interface](https://github.com/PARanOiA1120/cs56-games-simon-says/blob/master/README.md#interface)
      - [New Game](https://github.com/PARanOiA1120/cs56-games-simon-says/blob/master/README.md#new-game)
      - [Choose Version](https://github.com/PARanOiA1120/cs56-games-simon-says/blob/master/README.md#choose-version)
	  - [Rules](https://github.com/PARanOiA1120/cs56-games-simon-says/blob/master/README.md#rules)
	  - [Exit](https://github.com/PARanOiA1120/cs56-games-simon-says/blob/master/README.md#exit)
   - [Documentation](https://github.com/PARanOiA1120/cs56-games-simon-says/blob/master/README.md#documentation)
   - [How to run](https://github.com/PARanOiA1120/cs56-games-simon-says/blob/master/README.md#how-to-run)


##Introduction
 
  TA tutorial illustrating how to do simple zoom in and zoom out controls on, for example, a map image.   Useful for cs56-misc-map-gui. Here is a screenshot of the basic interface.
  
##Interface

* Previous version
 
![](http://i.imgur.com/wkrgg1z.png)


###New Game

* A player can directly start a new game of 'Amateur' level by clicking on "New Game" button
* The basic interface of the game consists of four colored buttons on the screen, a "Start" button to start the game, an "Exit" button to go back to the main menu, and also a "Score:" lable at the top right corner of the screen that shows the highest score the player has got. Like this:

![](http://i.imgur.com/OlnZek7.png)

* Also, regardless of what level the player is currently playing, if the player gets a new high score while playing, the "Score:" label at the corner will turn red to indicate that current score is the highest score recorded:

![](http://i.imgur.com/N04a0kb.png)


###Choose Version
* The "Choose Version" button on the main menu will let player select what level they want to play:

![](http://i.imgur.com/sUST0nA.png)

* Professional level looks like this: 

![](http://i.imgur.com/2om18pt.png?1)

* Intermediate and Amateur level has the same layout as the basic layout when player clicks on "New Game" button on the main menu:

![](http://i.imgur.com/OlnZek7.png)

* And the "Back" button on "Choose Version" interface just brings player back to the main menu.


###Rules
* The "Rules" button shows the player the rules of the game:

![](http://i.imgur.com/Wi5zycs.png)

* The "Back" button brings player back to the main menu.


###Exit
* The "Exit" button closes the game.


## Documentation

* `SimonFlash` class constructs the main game thread for Amateur level that switches turns between the computer and the user to correctly let the game proceed.

* `SimonFrame` class constructs the main frame of the game for Amateur level: four buttons, "Start" button, "Exit" button and "Score:" label, as well as the listeners for the buttons.

* `SimonInterL` class class constructs the main game thread for Intermediate level that switches turns between the computer and the user to correctly let the game proceed.

* `SimonInterLF` class constructs the main frame of the game for Intermediate level: four buttons, "Start" button, "Exit" button and "Score:" label, as well as the listeners for the buttons.

* `SimonProFlash` class class constructs the main game thread for Professional level that switches turns between the computer and the user to correctly let the game proceed.

* `SimonProL` class constructs the main frame of the game for Professional level: six buttons, "Start" button, "Exit" button and "Score:" label, as well as the listeners for the buttons.



* `SimonButton` is a subclass of `JButton` that defines the buttons that are clicked by player. It has two constructors:

-- A no-argument constructor that set the preferred size of the button to 100 by 100:

```java
    public SimonButton() {
	  this.setPreferredSize(new Dimension(100,100));
	}
```
-- A Color-parameter constructor that calls no-arg constructor to set size and then sets button color to Color passed in:

```java
      public SimonButton(Color color) { 
	      this();
	      this.setBackground(color);
	      this.setBorderPainted(false);
	      this.setOpaque(true);
	  }
```

* `SimonLevel` is a class that defines the interface of "Choose Version" with 4 buttons that let players select different levels they want to play and a "Back" button that leads players back to main menu. 

* `SimonRules` is a class that shows the rules of the game to players. It reads the contents of a text file "Rules.txt" and put them on the screen.

* `SimonMenu` is a class that defines the layout of the start(main) menu of the game: "New Game" button, "Choose Version" button, "Rules" button and "Exit" button.

* `SimonSaysGame` under *model* folder is part of the code refactoring. It is the model part of a MVC structure of the game. It defines purely the game logic with some useful methods to help the game run correctly.

* `SimonSaysTest` under *model* folder is the JUnit test for model SimonSaysGame.

* `SimonSaysGameOld` under *model* folder is essentially the same as `SimonFlash` where the game logic are mostly defined. It is the source from which `SimonSaysGame` is derived.

* The high score that a players gets for three different levels are stored in three different files: `HighScore.txt` for Amateur level, `HighScoreInterLevel.txt` for Intermediate level, and `HighScoreProLevel.txt` for Professional level.


## How to run 
To start the game, use `ant run`. 
