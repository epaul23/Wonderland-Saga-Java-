/**
 * title: Location.java
 * description: Represents a location within the game, holding items, exits, and characters.
 * date: 2024-08-10
 * @author Emil paul
 * @version 1.0
 * @copyright 2024 Emil Paul
 */

/**
 * DOCUMENTATION
 */

/**
 *
 * Class: Location
 
 * Purpose:
 * Represents a location in the game, containing items in that location , exits and characters in that location.
 *
 * Variables:
 * - name: String - The name of the location (location.txt)
 * - description: String - A brief description of the location (location.txt)
 * - items: ArrayList<Item> - A list of items found in the location
 * - exits: ArrayList<Exit> - A list of exits leading to other locations
 * - characters: ArrayList<Character> - A list of characters present in the location
 *
 * Constructors:
 * - Location(String name, String description) - Creates a location with the specified name and description.
 *
 * Methods:
 * - getName() - Returns the name of the location.
 * - getDescription() - Returns the description of the location
 * - addItem(Item item) - Adds an item to the location
 * - getItems() - Returns a list of items in the location (items.txt)
 * - addCharacter(Character character) - Adds a character to the location (character.txt)
 * - getCharacters() - Returns a list of characters in the location(character.txt)
 * - addExit(String direction, Location location) - Adds an exit to another location (exits.txt)
 * - getExit(String direction) - Returns the location reached by the specified exit (exits.txt)
 
 
 
 * Inner Class:Exits
 * Purpose:
 * A inner class that manages all the exits of a location
 *
 * Variables:
 * - String direction -direction of exit (eg. north,south,etc)
 * - Location location -Destination location after moving
 *
 * Constructors:
 * -Exit(String direction, Location location)-Initated Exit object with direction and destination location
 *
 * Methods:
 * - getDirection - Gets direction of exit
 * - getLocation - Gets destination location of exit
 
 Test Plan
 
 1. Run the program
 
*EXPECTED:

The Location class should initialize correctly with a name and description. It should be able to manage items, characters, and exits effectively.

*ACTUAL:

Verify that the Location class initializes properly and that methods for adding and retrieving items, characters, and exits work as intended.


2. Good Data Test Cases

#Create a Location

EXPECTED:

Create a Location object with valid values for name and description (e.g., Location factory = new Location("Big factory", "car manufacturing factory")). Verify that the location is initialized with these values.

ACTUAL:

Verify that the location is created with the specified name and description, and that getName() and getDescription() return the expected values.

#Add an Item

*EXPECTED:

Create an Item object (e.g., Item apple = new Item("Apple", "juicy red apple")) and add it to a location (e.g. factory.addItem(apple)). Verify that the item is added to the location.

*ACTUAL:

Verify that the item is added to the items list of the location and that it can be retrieved using getItems().

#Add a Character

EXPECTED:

Create a Character object (e.g., Character elf = new Character("Elf", " mystical elf")) and add it to a location (e.g., factory.addCharacter(elf)). Verify that the character is added to the location.

ACTUAL:

Verify that the character is added to the characters list of the location and that it can be retrieved using getCharacters().

#Add and Retrieve an Exit

*EXPECTED:

Create another Location in exits.txt Dark Forest;Factory;east, and user should be able to move east from DarkForest

*ACTUAL:

Verify that the exit is added to the exits list and that getExit("east") returns the Factory location.

#Remove an Item

EXPECTED:

After adding an item to a location (eg: Hookah;A hookah belonging to the smoking Caterpillar, collectable item!;Garden) as in items.txt, remove it (e.g., garden.removeItem(hookah)). Verify that the item is no longer present in the location.
 
 
 3. Bad Data Test Cases
 
#Invalid data with Empty Values

*EXPECTED:

Initialize a Location with empty or null values for name and description.Verify that the location is created without errors.

*ACTUAL:

Ensure that the class handles empty or null values fine

*Invalid Exit Retrieval

EXPECTED:

Attempt to retrieve an exit with an invalid direction (e.g., hogwarts.getExit("west") when no exit has been set for that direction), verify that the method returns null 

*ACTUAL:

Ensure that getExit() returns null for invalid directions and does not cause errors.

 
 
 
 */



import java.util.ArrayList;

public class Location {
    private String name; //name of location
    private String description; //description of location
    private ArrayList<Item> items; //list of items in that location
    private ArrayList<Exit> exits; // list of exits from that location
	private ArrayList<Character> characters; //list of characters in that location

	//Initiate location object
    public Location(String name, String description) {
        this.name = name;
        this.description = description;
        this.items = new ArrayList<>();
        this.exits = new ArrayList<>();
		this.characters = new ArrayList<>();
    }
	//Get the name of the location
    public String getName() {
        return name;
    }
	//Get the description of the location
    public String getDescription() {
        return description;
    }
	
	 // Add an item to the location
    public void addItem(Item item) {
        items.add(item);
    }
	 // Get the list of items in the location
    public ArrayList<Item> getItems() {
        return items;
    }
	
	 // Add a character to the location
	public void addCharacter(Character character) {
        characters.add(character);
    }
	
	// Get the list of characters in the location
    public ArrayList<Character> getCharacters() {
        return characters;
    }
	
	// Add an exit from this location to another location
    public void addExit(String direction, Location location) {
        exits.add(new Exit(direction, location));
    }
	
	// Get the list of exits from this location
    public Location getExit(String direction) {
        for (Exit exit : exits) {
            if (exit.getDirection().equals(direction)) {
                return exit.getLocation();
            }
        }
        return null; // No exit found for the given direction
    }
	
	//remove item from location, used at Hogwarts to remove Mandrake Root (TRADE)
    public void removeItem(Item item) {
        items.remove(item);
    }

    //class for managing exits
    private class Exit{
        private String direction; //direction of exit (eg. north,south,etc)
        private Location location; //sets the destination location after moving

		//constructor to initiate Exit object
        public Exit(String direction, Location location) {
            this.direction = direction;
            this.location = location;
        }
		
		//get direction of exit
        public String getDirection() {
            return direction;
        }
		
		//get destination location of exit
        public Location getLocation() {
            return location;
        }
    }
}
