package JavaExamProject_Group15;

import JavaExamProject_Group15.Entity.Items;
import JavaExamProject_Group15.Entity.Rooms;

/**
 * Player class represents a player in the game.
 * It maintains the current room and inventory of the player.
 */
public enum Player {
    player();
    Player() {
        // 默认玩家开始在 "lobby"
        this.currentRoom = Rooms.ROOM_ER; //default start in Lobby
        this.inventory = new Inventory(new Items[]{}); //default empty inventory
    }

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
