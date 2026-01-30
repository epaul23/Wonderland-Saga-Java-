/**
 * title: Control.java
 * description: This class is responsible for processing user commands, managing special cases, and updating the game state based on player actions in the game
 * date: 2024-08-11
 * @author Emil Paul
 * @version 1.0
 * @copyright 2024 Emil Paul
 */

/**
 * DOCUMENTATION...
 */

/**
 *
 * Class: Control
 
 * purpose:
 * The Control class manages user input, processes game commands (go,take,inventory,help,exit,etc), and handles special cases for movement and item pickup in game.
 *
 * Variables:
 * - inventory: An instance of Inventory that keeps track of the player's items
 * - scanner: An instance of Scanner used to read user commands
 *
 * Constructors:
 * - Control(Inventory inventory, Scanner scanner): Initializes the Control object with an inventory and scanner
 *
 * Methods:
 * - Location processCommand(String command, Location currentLocation): Processes the player's command, updates the location based on the command, and handles special cases that controls the logic of the game
 
 * - private Item findItemInLocation(String itemName, Location location): Searches for an item in a given location and returns it if found
 *
 */

/** 
 
 * Test Plan:

 * 1. Run the program.

 * EXPECTED:
 
 * -The game should prompt the user to enter a command and process it accordingly.The commands include navigating through locations, picking up items, checking inventory, seeking help, and exiting the game. Game is expected to provide appropriate results based on commands entered
 
 * ACTUAL:
 
 *Verify that the game correctly processes each command and provides the expected output, including feedback for special cases and standard actions.
 
 
 
 
 * 2. Good data test cases:
 
 #1 : Go and Move commands 

 * EXPECTED:
 
 * Enter commands like 'go north' or 'move south'. The program should check if the move is valid (Refer to the map picture of game submitted with Assignment), handle special cases (like needing an amulet to enter the Dark Forest), and update the location accordingly. The user should receive appropriate feedback based on their action.
 
 * ACTUAL:
 
 * Verify that the location changes as expected and that special cases are handled correctly, with appropriate messages displayed.
 
 #2 :Pickup or Take commands

*EXPECTED:

*Enter commands like take hookah or pickup Excalibur. The program should check if the item can be picked up, handle any special conditions (like needing a Golden key to access an Excalibur), and update the inventory and location accordingly.

ACTUAL:

*Verify that items are added to the inventory and removed from the location correctly. Ensure special conditions are managed appropriately and feedback is provided


 #3: Check Inventory

*EXPECTED:

*Enter 'inventory', The program should display the list of items currently held by the player

*ACTUAL:

*Verify that the inventory is displayed correctly and includes all items thats picked up


 #4: Help

*EXPECTED:

*Enter 'help', The program should display a list of available commands with descriptions

ACTUAL:

*Verify that the help message is clear and includes all available commands and their descriptions.


 #5: Exit

*EXPECTED:

*Enter exit. The program should display a farewell message and terminate right away

*ACTUAL:

*Verify that the program exits with the correct message and closes properly.



* 3. Bad data test cases:

 #Invalid Command
 
*EXPECTED:

Enter an unrecognized command like 'fly to moon'. The program should display a message indicating that the command is unknown and suggest typing help for a list of valid commands.

*ACTUAL:

Ensure that the program handles unknown commands and provides a helpful message.



#Special Cases

#Case 1: Entering Restricted Areas

*EXPECTED:

*Attempt to move to the Dark Forest or Queen’s Court without the necessary items (Amulet or Vault Key). The program should display appropriate messages indicating that the required item is missing.

*ACTUAL:

*Verify that the game correctly prevents access to restricted areas and provides helpful hints or messages about the missing items.

#Case 2: Picking Up Items with Restrictions

*EXPECTED:

*Attempt to pick up items like the Amulet or Excalibur without meeting the conditions (like having the Invisibility Cloak or Golden Key). The program should display appropriate messages explaining why the item cannot be picked up.

*ACTUAL:

*Ensure that the game correctly enforces item pickup restrictions and provides feedback


 */


import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

class Control {
    private Inventory inventory; // This is where we keep track of the player's items
    private Scanner scanner; // Declare Scanner as a class member

    // Constructor to initialize the Control object with Inventory(keeps track of stash) and Scanner (reads user commands)
    public Control(Inventory inventory, Scanner scanner) {
        this.inventory = inventory;
        this.scanner = scanner; 
    }

    // Main game method to process the player's command and update the current location
    public Location processCommand(String command, Location currentLocation) {
		
        // Split the command into 2 parts= action and target
        String[] parts = command.split(" ", 2);
        String action = parts[0]; // The first word is the action (e.g., "go" or "take")
        String target = ""; // item or location with respect to action (go or take,etc)

        // Check if there is a second word(target) in your command (eg. take apple)
        if (parts.length > 1) {
            target = parts[1]; // The second word is the target (e.g., "north" or "key"),checks if command id valid
        }

        // Perform actions based on the command
		
		//# GO/MOVE COMMANDS
		
        if (action.equals("go") || action.equals("move")) {
			
            // Special case 1: Trying to enter DarkForest without Amulet 
            if (currentLocation.getName().equals("Garden") && target.equals("south")) {
				
				//Checks if inventory has Amulet, then game lets you enter DarkForest from Garden
                if (inventory.hasItem("Amulet")) {
                    System.out.println("You used the amulet to guide you through the Dark Forest.");
                    return currentLocation.getExit(target);//enters darkforest
					
				//Cant enter DarkForest without Amulet	
                } else {
                    System.out.println("CANT ENTER YET, You need the mythical Amulet to guide you through the Dark Forest");
                    return currentLocation;// Stay in the current location, ie: Garden
                }
            }
			
			//Special case 2: Trying to enter Treasury without picking Vault Key
			//Checks if inventory has Vault key then game lets you enter Treasury from Queen's court
            if (currentLocation.getName().equals("Queen of Heart's Court") && target.equals("south")) {
                if (inventory.hasItem("Vault Key")) {
                    System.out.println();
                    System.out.println("You used the Queen's Vault Key to enter the Royal Treasury");
                    System.out.println();
                    return currentLocation.getExit(target);//enters treasury
				//If player did not pickup vault key from Queen's court ad try to enter treasury	
                } else {
                    System.out.println();
                    System.out.println("CAN'T ENTER HERE, You need the Vault Key to enter here.");
                    System.out.println();
                    return currentLocation;//Queen's court
                }
            }
			
			//Special case 3: Trying to enter Queen's court without Excalibur to kill Dragon
			//Check if inventory has Excalibur to enter Queen's court to kill dragon, lets you enter if you have it
            if (currentLocation.getName().equals("Dark Forest") && target.equals("south")) {
                if (inventory.hasItem("Excalibur")) {
					//This is were you defeat Queen
					System.out.println();
					System.out.println("You have Slayed the Scary dragon in a heroic battle and Defeated the EVIL QUEEN");
					System.out.println();
					System.out.println("Queen of Hearts: NOoooo!!!!, WHAT HAVE YOU DONE,You've won this battle, but the war is far from over!!!!,,,,I-Im s-s-so diz-zy ");
					System.out.println();
					System.out.println("The Queen of Hearts passed out, screaming,I didnt sign up for this!!!!");
					System.out.println();
                    
                    return currentLocation.getExit(target);//Queen's court
				//If player tries to enter Queen's court without Exaclibur situation	
                } else {
                    System.out.println();
                    System.out.println("Queen of Hearts : HAHHAHAAAA,,,YOU CANT JUST WALK IN,,,, Those who tries to get past me needs to get past my MIGHTY DRAGON !!!!!");
                    System.out.println("HINT: Only a Mytical sword long lost in a Ruins inside Dark Forest can Kill the Dragon");
                    System.out.println();
                    return currentLocation;//Darkforest
                }
            }
			
			//Special case 4: Trying to enter Safe room without collecting all Treasures from Treasury (WIN CONDITION)
			//If you try to enter Safe Room without collecting all Treasures, it wont let you enter, program checks for all 4 items in inventory
            if (currentLocation.getName().equals("Royal Treasury") && target.equals("east")) {
                if (inventory.hasItem("Infinity Stone") && inventory.hasItem("Batmobile") && inventory.hasItem("Lightsaber") && inventory.hasItem("Magic Carpet")) {
					
					//MISSION PASSED situation
					//If you collect all items from Treasury and goes to SafeRoom, you WIN the game and Exits it
                    System.out.println("*************************************************************************************************************");
                    System.out.println("Woohoo, You Did It,You finally saved entire Wonderland and got all Treasures,,,, Time for TEAAAAAAA PARTYYYYYY!!");
					System.out.println();
                    System.out.println("CONGRATULATIONS! You've conquered the Evil Queen, collected treasures, and restored peace in Wonderland!"); 
                    System.out.println();
                    System.out.println();
					System.out.println("You've escaped Wonderland! Or did Wonderland escape you? I guess YOU'LL NEVER KNOW !!!");
					System.out.println();
					System.out.println(">>>>>>>>>>==================================< THE END >============================================<<<<<<<<<<");
                    System.out.println("*************************************************************************************************************");
					
					System.exit(0); // Exit the game, game completed
					
				//If you try to enter Safe room with all 4 items collected from Treasury	
                } else {
                    System.out.println();
                    System.out.println("Quest incomplete to WIN the game");
					System.out.println("HINT: ALL Treasure items from Treasury must be collected to WIN");
                    return currentLocation;//Treasury, in this case take all items and then try entering Saferoom again to WIN
                }
            }
			
            // HANDLE REGULAR MOVEMENTS
			
			//finds the exit location based on direction from current location
            Location nextLocation = currentLocation.getExit(target);
			//if exits exist
            if (nextLocation != null) {
                System.out.println("You moved to " + nextLocation.getName() + " now");
                return nextLocation; //Moved to new location
				
			//if exits does not exist	
            } else {
                System.out.println("You can't go that way."); // Invalid direction
            }
			
			
			
		//# TAKE/PICKUP COMMAND
		
        } else if (action.equals("take") || action.equals("pickup")) {
            // Special conditions for Take/pickup command
			
            // #Special case 1: When tries to pickup Amulet from Chesire Cat Tree without having Invisibility cloak in Inventory
            if (target.equalsIgnoreCase("amulet") && currentLocation.getName().equals("Cheshire Cat's Tree")) {
				
				//Wont let you take Amulet without having Invisibility cloak(from Hogwarts) in stash
                if (!inventory.hasItem("Invisibility Cloak")) {
                    System.out.println();
                    System.out.println("Cheshire Cat : NO WAY, You cant STEAL my Amulet on my Watch !!!!");
                    System.out.println();
					System.out.println("HINT: Find a way to take Amulet without Cheshire cat SEEING, legend says a Boy in Hogwarts owns something that might help");
                    return currentLocation; // Can't take the amulet
					
				//If you have invisibility cloak in invertory when you try to take Amulet,it lets you take Amulet	
                } else {
                    System.out.println();
                    System.out.println("You have used the Invisibility cloak acquired to steal the Amulet without the Cheshire Cat noticing!!");
                    System.out.println("SNEAKY LIKE A NINJA");
                    System.out.println();
                }
            }
			
			
            // #Special case 2: When you try to take Excalibur from Forbidden Ruins without having Golden key in Inventory
            if (target.equalsIgnoreCase("Excalibur") && currentLocation.getName().equals("Forbidden Ruins")) {
				
				//NO golden key,game wont let you take Excalibur
                if (!inventory.hasItem("Golden Key")) {
                    System.out.println();
					System.out.println("Oracle: You lost ?!!,,You Can't unlock the chest to take Excalibur, Legend says the Key was lost in a WILD tea party !!!");
                    System.out.println();
                    return currentLocation; //Forbidden ruins, Can't take the sword
					
				//Tries to take Excalibur with Golden Key in Inventory case	
                } else {
                    System.out.println();
					System.out.println("Oracle: You are the chosen one !, finally someone unlocked the Mighty EXCALIBUR !!!!");
                    System.out.println("==You unlocked the legendary chest using Golden Key from Mad Hatter and acquired the MYTHICAL EXCALIBUR SWORD !!!!==");
					System.out.println();
                }//Lets you pick up the Excalibur
            }
			
			
            // #Special case 3: If you try to Take Invisibility Cloak without having Mandrake Root in Inventory
			
			
            if (target.equalsIgnoreCase("Invisibility Cloak") && currentLocation.getName().equals("Hogwarts School of Wizardry")) {
				
				//If you didnt pickup Mandrake Root from Garden, it wont let you pickup Invisibility cloak
                if (!inventory.hasItem("Mandrake Root")) {
                    System.out.println();
                    System.out.println("Harry Potter :NOT THAT EASY,,I can only TRADE IT in return for a RARE Magical Mandrake Root found in Wonderland Gardens, can't do it myself School RULES,,,uggghhhhh !!!");
                    System.out.println();
                    return currentLocation;//Hogwarts
					
				//If you do have Mandrake Root in Inventory, It lets you pickup Invisibility cloak as a TRADE
				//So the Mandrake Root gets REMOVED from inventory and Invisibilty cloak gets added	
                } else {
                    System.out.println();
                    System.out.println("Harry Potter : Pleasure doing BUSINESS with you, Here you go my Invisibility Cloak is all yours and I'll take that Mandrake Root , Safe Travels !!!");
                    System.out.println();
					System.out.println("== You traded in Mandrake root inreturn for Invisibility cloak, hence Mandrake Root is removed from Inventory ==");
					System.out.println();
					
					//Method to remove Mandrake Root from Inventory (Simulate Trade IN)
                    // Remove "Mandrake root" from inventory when I trade in with Invisibility cloak
					
                    for (Item item : inventory.getItems()) {//loop through inventory Arraylist until it finds Mandrake Root
                        if (item.getName().equals("Mandrake Root")) {
                            inventory.removeItem(item); // Remove Mandrake Root from inventory
                            break;
                        }
                    }
                }
            }
			
			//#Special Case 4: Trying to pickup Golden Key from Mad Hatter (Answer the Riddles)
            if (target.equalsIgnoreCase("Golden Key") && currentLocation.getName().equals("Mad Hatter's Tea Party")) {
                System.out.println();
                System.out.println("Mad Hatter: NOT THAT EASY! Answer these riddles to get the Golden Key:");
                System.out.println();
				
				
				
				//Runs till the loop breaks(correct answer scenario)
				
                // First Riddle
                while (true) {
                    System.out.println();
                    System.out.println("Mad Hatter : What has hands but can't clap ?"); // Answer: CLOCK
                    String ans1 = scanner.nextLine().toLowerCase();//input answer saved in ans1 String
					
					//If you answered correct case
                    if (ans1.equals("clock")) {
                        System.out.println();
                        System.out.println("Mad Hatter : NOT BAD!!,You got it right! Let's move to the next one.");
                        break;
						
					//Wrong answer case	
                    } else {
                        System.out.println();
                        System.out.println("Mad Hatter :Sorry, you got it wrong. Try again.");
                        System.out.println("Hint: An item found on a wall and you use it to check time.");
                    }
                }
                // Second Riddle
                while (true) {
                    System.out.println();
                    System.out.println("Mad Hatter: Here is a Tricky one,  What is the Mad Hatter's favorite beverage?"); // Answer: TEA
                    System.out.println();
                    String ans2 = scanner.nextLine().toLowerCase();//input answer saved in ans2 string
					
					//If you answered correct
                    if (ans2.equals("tea")) {
                        System.out.println("Mad Hatter :Correct Smartypants, The Golden Key is all yours!, come by later for some Tea party DISCO ");
                        Item goldenKey = findItemInLocation("Golden Key", currentLocation);
                        if (goldenKey != null) { //if item is found
                            inventory.addItem(goldenKey); // Add Golden Key to inventory
                            currentLocation.removeItem(goldenKey); // Remove the item from the location
                        }
                        return currentLocation;//Tea party after riddles
					
					//Wrong answer case for riddle#2	
                    } else {
                        System.out.println();
                        System.out.println("Mad Hatter: Sorry,that wrong, thought it was gonna be easy didnt you ?,HAHAHAAAA..... Try again !!");
                        System.out.println("Hint: Its not a potion, but it does help you stay tea-rifically alert.");
                        System.out.println();
                    }
                }
            }
			
            // HANDLE REGULAR PICKUP CASES
			
            Item itemToTake = findItemInLocation(target, currentLocation);
            if (itemToTake != null) {
                inventory.addItem(itemToTake); // Add the item to the inventory
                currentLocation.removeItem(itemToTake); // Remove the item from the location
				
				//Displays this message along with Item description when picked up
                System.out.println("You just took " + itemToTake.getName() + " from " + currentLocation.getName());
                System.out.println(">== " + itemToTake.getDescription() + " ==<");
				
			//If item entered doesnt exist	
            } else {
                System.out.println("There's no such item here."); // Item not found
            }
		
		
		
		//# INVENTORY COMMAND
		
		//Method to print entire inventory	
        } else if (action.equals("inventory")) {
			//calls method to output inventory items (made in 'Inventory' class)
            inventory.showInventory();
		
		
		
		//# HELP COMMAND
        } else if (action.equals("help")) {
			
			//outputs the controls of game once again
            System.out.println("**********************************************************************");
            System.out.println("Available commands:");
            System.out.println("1. go [direction] - Move to a new location.");
            System.out.println("2. take [item] - Pick up an item from the current location.");
            System.out.println("3. inventory - Check the items you have.");
            System.out.println("4. help - Show this help message.");
            System.out.println("5. exit - Quit the game.");
            System.out.println("**********************************************************************");
		

		
		//# EXIT COMMAND
		//exits the game immediately
		
        } else if (action.equals("exit")) {
            System.out.println("Thank you for playing! Goodbye!");
            System.exit(0); // Exit the game
			
		//# UNKNOWN COMMANDS	
        } else {
            System.out.println("Unknown command. Type 'help' for a list of commands.");
        }

        return currentLocation; // Return the current location if no action was taken
    }



    // Helper method to find an item in a location
    private Item findItemInLocation(String itemName, Location location) {
        for (Item item : location.getItems()) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null; // Item not found
    }
}