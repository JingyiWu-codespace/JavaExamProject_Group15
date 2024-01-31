package JavaExamProject_Group15;

import JavaExamProject_Group15.Entity.Items.ItemBase;
import JavaExamProject_Group15.Entity.Rooms.RoomBase;

/**
 * The Player enum represents the player in the game. It maintains the state of the player,
 * including their current location (room) and their inventory of items.
 * This enum is designed as a singleton to ensure only one instance of the player exists in the game.
 */
public class Player {
    public static final boolean debug_flag = true;
    public static Player currPlayer;

    private final String playerName;
    private final Inventory inventory;
    private RoomBase currentRoom;

    public Player(RoomBase startingRoom) {
        this(startingRoom, null);
    }

    public Player(RoomBase startingRoom, String playerName) {
        this.currentRoom = startingRoom;
        this.inventory = new Inventory(new ItemBase[]{});
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public RoomBase getCurrentRoom() {
        return this.currentRoom;
    }

    public void setCurrentRoom(RoomBase newRoom) {
        newRoom.playerEnter();
        this.currentRoom = newRoom;
    }

    public void setCurrentRoom(Class<? extends RoomBase> newRoom) {
        this.setCurrentRoom(RoomBase.getRoomObj(newRoom));
    }

    public Inventory getInventory() {
        return this.inventory;
    }
}
