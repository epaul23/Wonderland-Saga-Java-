/**
 * title: Inventory.java
 * description: This class represents the player's inventory in the Wonderland game, managing the items the player picks up on the game
 * date: 2024-08-10
 * @author Emil Paul
 * @version 1.0
 * @copyright 2024 Emil Paul
 */

/**
 * DOCUMENTATION...
 */

/**
 * Class: Inventory
 * 
 * purpose:
 * The `Inventory` class manages a collection of items that the player has acquired in the game. It provides methods to add and remove items, check for specific items, and display the contents of the whole inventory
 * 
 * Variables:
 * - items: An ArrayList<Item> representing the list of items currently in the player's inventory.
 * 
 * Constructors:
 * - `public Inventory()`: Initializes the inventory as an empty list of items.
 * 
 * Methods:
 * - public void addItem(Item item): Adds an item to the inventory.
 * - public ArrayList<Item> getItems(): Returns the list of items in the inventory.
 * - public boolean hasItem(String itemName): Checks if the inventory contains an item with the specified name
 * - public void removeItem(Item item): Removes an item from the inventory.
 * - public void showInventory(): Displays the contents of the inventory.

 
# Test Plan
 

1. Run the program

*EXPECTED:

*The Inventory class should initialize correctly with an empty list of items. It should be able to add items, check if an item is present, remove items, and display the inventory, all methods should work as intended 

*ACTUAL:

*Verify that the inventory initializes correctly and each method performs as expected.


2. Good Data Test Cases

#Add an Item

*EXPECTED:

*Create an Item object (e.g., Item car = new Item("car")) and add it to the inventory using addItem(). Verify that the item is added to the inventory.

*ACTUAL:

*Verify that the item is added to the items list and can be retrieved using getItems(), check that the inventory for that.


#Check if Item Exists

*EXPECTED:

*After adding an item (e.g., apple), use hasItem("apple") to check for its presence. The method should return true(boolean return)

*ACTUAL:

*Verify that hasItem() returns true when the item is present in the inventory and false when it is not.


#Remove an Item

*EXPECTED:

*Remove an item from the inventory ,for example, when you arrive at Hogwarts with Mandrake root and tries to pickup Invisibility cloak, access inventory before and after picking and make sure Mandrake root is removed (Traded for invisibility cloak).

*ACTUAL:

Ensure that the item is removed from the items list and is no longer found by hasItem().Confirm by calling 'inventory'



#Show Inventory

*EXPECTED:

*After adding items (e.g.,hookah, Mandrake root), call showInventory(). The method should display a list of items currently in the inventory and should include hookah, mandrake root

*ACTUAL:

*Verify that showInventory() displays all items correctly and shows the appropriate message when the inventory is empty.



3. Bad Data Test Cases


#Invalid Item Check

*EXPECTED:

*Use hasItem("InvalidItem") to check for an item not in the inventory, The method should return false

*ACTUAL:

*Verify that hasItem() correctly returns false for items that are not in the inventory.

#Show Inventory with No Items

*EXPECTED:

*After initializing the inventory and not adding any items, call showInventory(). The method should display "Inventory is empty."

*ACTUAL:

*Ensure that showInventory() correctly displays "Inventory is empty" when there are no items in the inventory.
 
 
 

 
 
 
 */


import java.util.ArrayList;


public class Inventory {
    private ArrayList<Item> items; //List of items in the inventory

	//Constructor to initiate the inventory object
    public Inventory() {
        this.items = new ArrayList<>();
    }
	
	//Add item to inventory
    public void addItem(Item item) {
        items.add(item);
    }
	
	//Get a list of items in inventory
    public ArrayList<Item> getItems() {
        return items;
    }
	
	//Method to check if location has item
    public boolean hasItem(String itemName) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
    }

	//Remove an item from inventory
    public void removeItem(Item item) {
        items.remove(item);
    }
	
	//Method to shoow entire inventory (command=inventory)
    public void showInventory() {
        if (items.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            System.out.println("Your Stash contains :");
            for (Item item : items) {
                System.out.println("- " + item.getName());
            }
        }
    }
}
