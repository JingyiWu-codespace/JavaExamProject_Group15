package JavaExamProject_Group15;

import JavaExamProject_Group15.Entity.Items;
import JavaExamProject_Group15.Entity.Rooms;

/**
 * The Player enum represents the player in the game. It maintains the state of the player,
 * including their current location (room) and their inventory of items.
 * This enum is designed as a singleton to ensure only one instance of the player exists in the game.
 */
public enum Player {
    player();
    /**
     *  constructor for Player. Initializes the player's starting room and inventory.
     */
    Player() {
        // The player starts in the ER room by default.
        this.currentRoom = Rooms.ROOM_ER; //default start in Lobby
        this.inventory = new Inventory(new Items[]{}); //default empty inventory
    }

    public final boolean debug_flag = true;

    private Rooms currentRoom;
    private final Inventory inventory;

    public Rooms getCurrentRoom(){
        return this.currentRoom;
    };

    public void setCurrentRoom(Rooms newRoom){
        this.currentRoom = newRoom;
    };

    public Inventory getInventory() {
        return this.inventory;
    }
}
