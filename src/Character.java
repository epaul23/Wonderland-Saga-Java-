/**
 * title: Character.java
 * description: This class represents a character in the Wonderland game, with attributes for the its name and description/introduction
 * date: 2024-08-10
 * @author Emil Paul
 * @version 1.0
 * @copyright 2024 Emil Paul
 */

/**
 * DOCUMENTATION...
 */

/**
 * Class: Character
 * 
 * describe your class:
 * The `Character` class encapsulates the properties of each character in the game. Each character has a name and a description 
 * 
 * Variables:
 * - name: A String representing the name of the character.
 * - description: A String containing a brief introduction or description of the character.
 * 
 * Constructors:
 * - public Character(String name, String description): Initializes the character with the provided name and description.
 * 
 * Methods:
 * - public String getName(): Returns the name of the character.
 * - public String getDescription(): Returns the description of the character.
 * 

 
 Test Plan

1. Run the program

*EXPECTED:

The Character class should initialize correctly with the provided name and description. The methods to get the character's name and description should return the correct values.

*ACTUAL:

Verify that the Character class initializes correctly and that the getName() and getDescription() methods return the expected values based on character.txt

2. Good Data Test Cases

#Create a Character

*EXPECTED:

Create a Character in text (e.g., `Character mufasa = new Character("Mufasa", "King of the jungle",Forbidden Ruins)). Verify that the character is created with the provided name and description in Forbidden Ruins.

*ACTUAL:

Verify that the Character object is created with the correct name and description values and once you reach Forbidden Ruins, it should print "character found :Mufasa,King of the jungle"

#Get Character Name

*EXPECTED:

After creating a Character object (e.g., Mufasa), use getName() to retrieve the character's name. The method should return "Mufasa".

*ACTUAL:

Verify that getName() returns the correct name of the character.

#Get Character Description

*EXPECTED:

After creating a Character object (e.g., Mufasa), use getDescription() to retrieve the character's description. The method should return "King of the jungle" as per characters.txt.Also try to test existing characters and changing description and called properly

*ACTUAL:

Verify that getDescription() returns the correct description of the character.

3. Bad Data Test Cases

#Invalid Character Initialization

*EXPECTED:

Attempt to create a Character object with invalid or empty name and description values (e.g., Character newCharacter = new Character("", "");). Verify that the class handles such cases without errors and initializes the object correctly.

*ACTUAL:

Ensure that the Character class handles initialization with empty, without throwing unexpected errors. The class should still create an object with these values.

#Get Name for Uninitialized Values

*EXPECTED:

After creating a Character object with an empty name or description, use getName() to retrieve the name. The method should return an empty string if the name was initialized as such.

*ACTUAL:

Verify that getName() returns the empty string if the character's name was initialized as empty, without any errors

#Get Description for Uninitialized Values

*EXPECTED:

After creating a Character object with an empty description, use getDescription() to retrieve the description. The method should return an empty string without any errors

*ACTUAL:

Verify that getDescription() returns the empty string if the character's description was initialized as empty.
 
 
 
 
 */

public class Character {
    private String name; //character name
    private String description; //character introduction
	
	//Constructor to initiate character object
    public Character(String name, String description) {
        this.name = name;
        this.description = description;
    }

	//get name of character
    public String getName() {
        return name;
    }
	
	//get character introduction description
    public String getDescription() {
        return description;
    }
}