package JavaExamProject_Group15;

import JavaExamProject_Group15.Entity.Items;
import JavaExamProject_Group15.Entity.Rooms;

/**
 * Player class represents a player in the game.
 * It maintains the current room and inventory of the player.
 */
public class Player {
    public int time;
    private Rooms currentRoom;
    private final Inventory inventory;
    public Player() {
        // 默认玩家开始在 "lobby"
        currentRoom = Rooms.ROOM_LOBBY; //default start in Lobby
        inventory = new Inventory(new Items[]{}); //default empty inventory
    }

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
