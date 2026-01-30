/**
 * title: Item.java
 * description: This class represents an item in the Wonderland game, with attributes for the item's name and description.
 * date: 2024-08-10
 * @author Emil Paul
 * @version 1.0
 * @copyright 2024 Emil Paul
 */

/**
 * DOCUMENTATION...
 */

/**
 * Class: Item
 * 
 * purpose:
 * The Item class handles items's name and  description, which are used to identify and describe the item within the game(Encapsulation)
 * 
 * Variables:
 * - name: A String representing the name of the item. (items.txt)
 * - description: A `String` containing a brief description of the item.(items.txt)
 * 
 * Constructors:
 * - public Item(String name, String description): Initializes the item with the provided name and description.
 * 
 * Methods:
 * - public String getName(): Returns the name of the item.
 * - public String getDescription(): Returns the description of the item.
 * 
 * Program Testing:
 *-Ensure that item objects can be created and their name and description can be retrieved correctly based on item.txt file
 
 1. Run the Program
 
*EXPECTED:

The Item class should initialize correctly with specified values for name and description. It should be able to provide the name and description when called. Each method should work as intended and provide appropriate feedback based on the item.

*ACTUAL:

Verify that the Item class initializes correctly and that each method performs as expected. Ensure that getting the name and description returns the correct values.

2. Good Data Test Cases

#Create an Item

EXPECTED:

Create an Item in item.txt with valid values (e.g., Item blade = new Item("Blade", "A sharp blade","garden")). Verify that the item is initialized with these values.

ACTUAL:

Verify that the item is created with the specified name and description, and that the methods getName() and getDescription() return the expected values.

#Get Item Name

*EXPECTED:

After creating an item (e.g., Item sword = new Item("Blade", "A sharp blade","garden")), call blade.getName(). The method should return "Blade".

*ACTUAL:

Verify that getName() returns the correct name of the item.

#Get Item Description

*EXPECTED:

After creating an item (e.g., Item blade = new Item("Blade", "A sharp blade")), call blade.getDescription(). The method should return "A sharp blade".

*ACTUAL:

Verify that getDescription() returns the correct description of the item.



3. Bad Data Test Cases

#Invalid Initialization with Empty Strings

*EXPECTED:

Initialize an Item object with empty strings for both name and description (e.g., Item emptyItem = new Item("", "")). Verify that the item is created successfully without errors.

*ACTUAL:

Verify that the item is created with empty values and that getName() returns "" and getDescription() returns "" without causing errors.


 
 
 */
 
//CODE 

public class Item {
    private String name; //Item name
    private String description; //Item description
	
	//constructor to initiate item object
    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }
	
	//get name of item
    public String getName() {
        return name;
    }
	
	//get description of item
    public String getDescription() {
        return description;
    }
}

