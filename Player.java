package JavaExamProject_Group15;

import JavaExamProject_Group15.Entity.Items.ItemBase;
import JavaExamProject_Group15.Entity.Rooms;

/**
 * The Player enum represents the player in the game. It maintains the state of the player,
 * including their current location (room) and their inventory of items.
 * This enum is designed as a singleton to ensure only one instance of the player exists in the game.
 */
public class Player {
    public static final boolean debug_flag = true;
    public static Player currPlayer;

    private final Inventory inventory;
    private Rooms currentRoom;

    Player(Rooms startingRoom) {
        // The player starts in the ER room by default.
        this.currentRoom = startingRoom; //default start in Lobby
        this.inventory = new Inventory(new ItemBase[]{}); //default empty inventory
    }

    public Rooms getCurrentRoom() {
        return this.currentRoom;
    };

    public void setCurrentRoom(Rooms newRoom) {
        this.currentRoom = newRoom;
    };

    public Inventory getInventory() {
        return this.inventory;
    }
}
