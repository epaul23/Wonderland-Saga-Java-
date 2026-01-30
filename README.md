# **Wonderland Saga: The Final Showdown (Java)**
Author : Emil Paul
---

*This project was built as my first large Java project to practice object-oriented design, command parsing, and file-based data loading.*

**Wonderland Saga: The Final Showdown** is a text-based adventure game inspired by *Alice’s Adventures in Wonderland*.  
Players explore a fantasy world, interact with characters, collect items, solve riddles, and unlock areas using inventory-based rules.

The ultimate goal is to **defeat the Evil Queen’s dragon**, **loot the Royal Treasury**, and **reach the Safe Room** to win the game.

This project was built as an **early Java project** to practice **object-oriented design**, **command parsing**, and **file-based data loading**.

---

## **Features**

- **Command-based gameplay** (`go`, `take`, `inventory`, `help`, `exit`)
- **Multiple interconnected locations** with directional movement
- **Inventory system** with item pickup, trading, and progression rules
- **NPC interactions** including riddles, guidance, and item trades
- **Clear win condition** based on collecting required treasures
- **Data-driven world design**  
  *(locations, items, exits, characters, and commands are loaded from external text files)*

---

## **How to Run**

### **Requirements**
- Java Development Kit (**JDK 8 or higher**)

### **Compile and Run**

From the **project root directory**:

```bash
javac -d bin src/*.java
java -cp bin WonderlandGame
```

> **Important:**  
> The game must be run from the **project root directory** so it can correctly load files from the `data/` folder.

---

## **Available Commands**

- **`go <direction>` / `move <direction>`**  
  Move north, south, east, or west if an exit exists

- **`take <item>` / `pickup <item>`**  
  Pick up an item from the current location

- **`inventory`**  
  Display collected items

- **`help`**  
  Show available commands

- **`exit`**  
  Quit the game immediately

---

## **Game Data Files**

The game world is loaded at startup from external text files:

- **`locations.txt`** — Location names and descriptions  
- **`exits.txt`** — Connections between locations (`from;to;direction`)  
- **`items.txt`** — Item name, description, and location  
- **`characters.txt`** — NPC name, description, and location  
- **`actions.txt`** — Commands displayed at game start  

This **data-driven design** allows the game world to be expanded or modified **without changing Java code**, as long as file formatting is same.

---

## **Gameplay Overview**

- Start at the **Rabbit Hole**
- Explore Wonderland by navigating connected locations
- Collect items required to unlock new areas
- Solve riddles and trade items with characters
- Defeat the Evil Queen’s dragon
- Collect **all four treasures** from the Royal Treasury
- Enter the **Safe Room** to win the game

A **visual map of the game world** is included in the `docs` folder to assist with navigation.

---

## **Future Improvements**

- Add a **`use <item>`** command for richer item interaction
- More dynamic NPC behavior and branching dialogue
- Additional puzzles and multi-step quests
- Multiple endings and improved replayability
- Enhanced validation and error messages for malformed data files


