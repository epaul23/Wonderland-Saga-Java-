/**
 * title: WonderlandGame.java
 * description: A text-based adventure game based on Alice's Adventures in Wonderland
 * date: 2024-08-10
 * @author Emil Paul
 * @version 1.0
 * @copyright 2024 Emil Paul
 */

/**
 * DOCUMENTATION
 */

/**
 *
 * Game Title :Wonderland Saga: The Final Showdown
 *
 * Purpose and Description:
   This game allows players to explore a magical world, interact with characters,
   collect items, and solve puzzles. The objective is to defeat the Evil Queen's
   terrifying Dragon, loot her entire treasury and restore harmony to Wonderland.
 *
 * Compiling and running instructions:
 * Required: Java SE Development Kit
 * Compile: javac WonderlandGame.java
 * Run: java WonderlandGame
 *
 */

/**
 *
 * Class: WonderlandGame
 *
 * Purpose:
   Main class that manages the game's flow, including loading locations, items, and characters
   from text files, handling user input, and updating the game state based on user
 *
 * Variables:
 * currentLocation: Location - Tracks the player's current position in the game
 * inventory: Inventory - Manages the items collected by the player.
 * control: Control - Handles the input commands from the player
 * scanner: Scanner - Reads the player's input from the console
 * visitedLocations: ArrayList<Location> - Stores the locations that the player has visited, used for location   description selection
 * actions: ArrayList<String> - Stores the list of available actions the player can perform,by actions.txt read and stored from outside the program
 *
 * Constructors:
 * WonderlandGame() - Initializes the game by setting up locations, items, characters, and actions by reading text files
 *
 * Methods:
 * - start() - Begins the game and handles the main game loop.
 * - initializeLocations() - Loads locations, items, characters, and actions from respective text files
 * - findLocationByName(ArrayList<Location>, String) - Finds and returns a location by its name (locations.txt)
 * - printLocationDescription(Location) - Prints the description of the current location and any characters present there
 * - getItemNames(Location) - Returns a formatted string of the item names in the current location
 *
 */

/**
Test Plan
 
 1. Run the WonderlandGame
EXPECTED:

The WonderlandGame class should initialize correctly, including setting up the game environment, loading data from files, and managing user interactions. The game should prompt the user to enter commands and process them appropriately. It should provide feedback based on the game state and user input.

ACTUAL:

Verify that the game initializes correctly and that methods for starting and running the game function as expected. Ensure that commands are processed and appropriate feedback is provided.

2. Good Data Test Cases

#Initialization

EXPECTED:

The WonderlandGame object should be initialized correctly, with fields like scanner, inventory, control, visitedLocations, and actions set up. Locations, items, characters, and exits should be loaded from the respective files without errors.

ACTUAL:

Verify that the WonderlandGame constructor initializes all fields correctly and that initializeLocations() loads data as expected, with no exceptions thrown.

#Start Game

EXPECTED:

Calling start() should display the game’s introduction, available commands, and the initial location description. The game should then enter a loop, prompting the user for commands and processing them until the game ends or manually enter "exit" by user

ACTUAL:

Ensure that the game displays the introduction, mission, and available commands correctly. Initial location should be "RabbitHole", and that the game loop takes commands 

#Navigate Locations

EXPECTED:

Entering commands like 'go north' or 'move south' should change the current location if the direction is valid. The new location should be displayed, along with items and characters present.Refer to the map diagram provided with assignment for reference. For instance, 'go east' from Garden, should end up in Cheshire's cat tree

ACTUAL:

Verify that valid navigation commands change the location correctly and that the location description, items, and characters are updated and displayed according to map diagram provided with assignment.

#Interact with Items

EXPECTED:

Commands like 'take excalibur' or 'pickup hookah' should add items to the inventory if they are present in the current location. The inventory should update accordingly.Check this by command 'inventory' before and after picking an item

ACTUAL:

Ensure that items are added to the inventory and removed from the current location as expected, ie when 'inventory' command is ran, you should be able to see hookah and Excalibur



#Exit Game

EXPECTED:

Entering 'exit' should terminate the game immediately with a farewell message.

ACTUAL:

Ensure that the game exits correctly and display "Thank you for playing! Goodbye!"


3. Bad Data Test Cases

#Invalid File Paths

EXPECTED:

If a file (e.g., locations.txt, items.txt) is missing or cannot be read, the game should handle the FileNotFoundException and display an appropriate error message.

ACTUAL:

Verify that the game handles correctly, or print an error message if invalid file given


#Invalid Commands

EXPECTED:

Entering invalid commands (e.g., run to mars) should prompt the game to display a message indicating that the command is unknown and suggest typing help for a list of valid commands

ACTUAL:

Verify that the game handles invalid commands and provides helpful feedback.

#moving to Non-existent Locations

EXPECTED:

Attempting to navigate to a location that does not exist should be handled properly. The game should display a message indicating "You can't go that way".

ACTUAL:

Ensure that the game provides message and let use know that user cant go that way

#Interacting with Non-existent Items or Characters

EXPECTED:

Attempting to interact with items or characters that do not exist in the current location should be handled properly. The game should display a message "There's no such item here."

ACTUAL:

Verify that the game handles interactions with non-existent items or characters correctly and provides clear feedback , "There's no such item here."

#text format wrong/ missing

EXPECTED:

If location is missing on exits.txt, items.txt or character.txt , it should let the user know about location not found as a Warning message
test case:for instance, try adding an item Car, a big fancy mercedes,    with no location which it belongs to

ACTUAL:

Verify that the game handles the error properly and prints a warning message
test case should print out,,"Warning: Location not found for item - car",, this process is similer to characters as well


 
 
 */




import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class WonderlandGame {
	//private fiels to store currentlocation, inventory,control, scanner and visited locations(description selction)
    private Location currentLocation; 
    private Inventory inventory;
    private Control control; //manages game commands and special conditions
    private Scanner scanner; //reads user input
    private ArrayList<Location> visitedLocations; //list of locations visited
    private ArrayList<String> actions; // List to store actions available

    public WonderlandGame() {
		
		//initialize each field
        this.scanner = new Scanner(System.in);
        this.inventory = new Inventory();
        this.control = new Control(inventory, scanner);
        this.visitedLocations = new ArrayList<>();
        this.actions = new ArrayList<>();
        initializeLocations(); //loads location,items,actions,exits , characters from text files
        
    }

    public void start() {
        // Game headers with intro, mission and actions available
		
        System.out.println("********************************************************************************************");
        System.out.println();
        System.out.println("                                         welcome to");
        System.out.println(">>>>>===================< THE WONDERLAND SAGA : The Final Showdown >===================<<<<<");
        System.out.println();
        System.out.println("********************************************************************************************");
        // Game description
        System.out.println("Prepare yourself for an epic adventure through the magical realm of Wonderland!");
        System.out.println();
        System.out.println("Your mission is to confront the Evil Queen's terrifying Dragon, loot her treasury,");
        System.out.println("uncover hidden treasures, and restore harmony to this enchanting land.");
        System.out.println("Face daring challenges, solve perplexing puzzles, and navigate a world of wonder.");
        System.out.println();
        System.out.println("********************************************************************************************");
		
	//displays available commands to user from action.txt
        System.out.println("Available commands:");
        for (String action : actions) {
            System.out.println(">   " + action);
        }
        System.out.println();
        System.out.println("********************************************************************************************");
        System.out.println();
        System.out.println("You are in front of the Rabbit Hole.");
		
		//variable that stores user commands
        String command;
        
        // Creates an infinite loop that always asks for user commands until it terminates by winning or manual "exit" command
        while (true) {
			//prints current location descriprion and items available
            printLocationDescription(currentLocation);
            System.out.println();
            System.out.println("ITEMS Available here: " + getItemNames(currentLocation));
            System.out.println();
            //User Commands saved and converted to String, no trailing whitespace and lowercase
            System.out.println(">>>>");
            command = scanner.nextLine().trim().toLowerCase();
			//updates current location based on commands
            currentLocation = control.processCommand(command, currentLocation);
        }
    }

    private void initializeLocations() {
		try {
			
			// Load locations from file
			File locationFile = new File("data/locations.txt");
			//uses scanner to read the locations file
			Scanner locationScanner = new Scanner(locationFile);
			//create a list that stores all locations
			ArrayList<Location> locations = new ArrayList<>();
			while (locationScanner.hasNextLine()) {
				//Split each line into location data
				String[] locationData = locationScanner.nextLine().split(";");
				//new location object created
				Location location = new Location(locationData[0], locationData[1]);
				//add each location to list
				locations.add(location);
			}
			locationScanner.close();




			// Load exits
			File exitsFile = new File("data/exits.txt");
			Scanner exitsScanner = new Scanner(exitsFile);

			while (exitsScanner.hasNextLine()) {
				String[] exitData = exitsScanner.nextLine().split(";");
				Location fromLocation = findLocationByName(locations, exitData[0]);
				Location toLocation = findLocationByName(locations, exitData[1]);
				
				//to handle incorrect locations in exits file
				if (fromLocation == null) {
					System.out.println("Warning: From Location not found - " + exitData[0]);
					continue;
				}
				if (toLocation == null) {
					System.out.println("Warning: To Location not found - " + exitData[1]);
					continue;
				}
				//adding exit direction between each fromlocation and toLocation
				fromLocation.addExit(exitData[2], toLocation);
			}
			exitsScanner.close();




			// Load items
			File itemsFile = new File("data/items.txt");
			Scanner itemsScanner = new Scanner(itemsFile);

			while (itemsScanner.hasNextLine()) {
				String[] itemData = itemsScanner.nextLine().split(";");
				//itemData[0]=item's name ,itemData[1]= item's description , itemData[2]= location that item is found
				
				//item variable stores its name and description
				Item item = new Item(itemData[0], itemData[1]);
				//gets location where item should be placed
				Location location = findLocationByName(locations,itemData[2]);
				if (location != null) {//check if location exists,then
					location.addItem(item); //add item to location here
					
				//case if wrong location in text file found
				} else {
					System.out.println("Warning: Location not found for item - " + itemData[2]);
				}
			}itemsScanner.close();
			
			
			
			
			// Load characters
			
			File charactersFile = new File("data/characters.txt");//file containing all character names,their description and their location
			Scanner charactersScanner = new Scanner(charactersFile);

			while (charactersScanner.hasNextLine()) {
				String[] characterData = charactersScanner.nextLine().split(";");
				Character character = new Character(characterData[0], characterData[1]); //character name and description
				Location location = findLocationByName(locations, characterData[2]);//location where that character belongs
				if (location != null) {
					location.addCharacter(character); //adds character to the location
					
				//to hande missing locations	
				} else {
					System.out.println("Warning: Location not found for character - " + characterData[2]);
				}
			}charactersScanner.close();
			
			
			
			 // Load actions from file
			File actionFile = new File("data/actions.txt"); //file contains actions data (all controls and instructions)
			Scanner actionScanner = new Scanner(actionFile);

			while (actionScanner.hasNextLine()) {
				actions.add(actionScanner.nextLine().trim()); // Add each action to the list, same list is displayed where headers are called
			}
			actionScanner.close();
			
			

			
			
			

			// Set the starting location
			currentLocation = locations.get(0); // ie. first location in locations.txt is RabbitHole, so setting that as starting location
		
		
		//To handle file not found exception
		} catch (FileNotFoundException e) {
			System.out.println("Error loading game data: " + e.getMessage());
		}
	}

	//method that helps to find location by its name from locations Arraylist
    private Location findLocationByName(ArrayList<Location> locations, String name) {
        for (Location location : locations) {
            if (location.getName().equals(name)) {
                return location; //return locations if the name matches
            }
        }
        return null;
    }
	
	
	//Used to print description of currnt locations
    private void printLocationDescription(Location location) {
		
		//revisiting locations 
        if (visitedLocations.contains(location)) {
            System.out.println();
            System.out.println("You are back in " + location.getName() + " again.");
			
		//first visit that gives long description
        } else {
            System.out.println(location.getDescription());//regular location description
            visitedLocations.add(location); //marks that this location is visited
        }
    
		
		// Print characters in the location, only if it has any
		if (!location.getCharacters().isEmpty()) {
			System.out.println();
			System.out.println("character found :");
			for (Character character : location.getCharacters()) {
				//gives character name and introduction
				System.out.print(">> "+character.getName()+" : "+character.getDescription());
				System.out.println();
			}
		}
	}
	
	
	
	//helps to get a formatted list of items in current location
    private String getItemNames(Location location) {
        String itemNames = "";
		
		//appends each item into itemNames string
        for (Item item : location.getItems()) {
            itemNames += "\n" + "# " + item.getName();
        }
		
		//no items found case
        if (itemNames.isEmpty()) {
            return "None"; // No items found
			
		//items found in location case	
        } else {
            return itemNames; // Return the concatenated item names
        }
    }

   
	//Main method to start the game
    public static void main(String[] args) {
		
		//creates new instance of Game
        WonderlandGame game = new WonderlandGame();
		//Starts the game by calling start method in the class
        game.start();
    }
}
