# My Personal Project

## Project Description
My project will be a game with **typing** as the main mechanic. You will be able
to select from a range of difficulties to complete the game. You will also be able
to pickup various items. The way to fight will 
be through typing a specific text and reaching a specific Words-Per-Minute.
People trying to practice their typing skills may be interested in this game.
This project is of interest to me because I have a passion for typing and
keyboards.

## User Stories
- As a user, I want to be able to select the level from a list of difficulties.
- As a user, I want to be able to pickup items and add them to an inventory.
- As a user, I want to be able to view a list of items in my inventory.
- As a user, I want to be able to have a smooth typing interface to fight bosses.
- As a user, I want to be able to save my current game progress (inventory, etc).
- As a user, I want to be able to select a game load file that I can resume playing.

## Instructions For Grader
- You can use an item in the inventory by clicking the item and clicking the plus symbol
- You can delete an item in the inventory by clicking the item and clicking the trash symbol
- You can locate my visual component by running the program (The loading screen)
- You can save the state of my application by clicking save and selecting a save file
- You can reload the state of my application by clicking load and selecting a load file

## Phase 4: Task 2
* Wed Apr 03 01:36:26 PDT 2024
* User deleted Cherry MX Blue item from Inventory
* Wed Apr 03 01:36:27 PDT 2024
* User consumed Cherry MX Red item from Inventory
* Wed Apr 03 01:37:19 PDT 2024
* Completing level added new Cherry Brown item to Inventory
* Wed Apr 03 01:37:23 PDT 2024
* User consumed Cherry MX Brown item from Inventory

## Phase 4: Task 3
If I had more time to work on the project the biggest change I would make would be to isolate
the UI and model packages even further. Currently, TypingGame has a field of type Inventory,
which is a tool in the model package. But TypingGame is supposed to represent the JFrame, which
contains the different JPanels to render the game. So according to the Single Responsibility Principle, 
TypingGame should not have access to anything relating to the behaviour of the game. This change would 
improve the cohesion of the program.

A second change I want to make is extracting a lot of the behavior of the Menu class into the model package.
The Menu class regrettably handles a lot of the game's behavior, but it is part of the UI, so it should not
be dealing with game behavior. I would refactor by extracting different methods into a new class in the model package.
The only thing that should remain in Menu is code relating to the rendering and UI of the menu. These two
changes would allow my project to better follow Object-Oriented Design Principles like cohesion and coupling. 