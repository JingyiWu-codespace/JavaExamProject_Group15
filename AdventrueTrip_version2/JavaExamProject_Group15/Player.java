package JavaExamProject_Group15;

import JavaExamProject_Group15.Entity.Items;
import JavaExamProject_Group15.Entity.Rooms;

import static JavaExamProject_Group15.Entity.Rooms.getInitialItemsForRoom;

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

//        for (Rooms room : Rooms.values()) {
//            Items[] initialItems = getInitialItemsForRoom(room);
//            room.setInventory(new Inventory(initialItems)) ;
//        }
    }
    private Rooms currentRoom;
    private Inventory inventory;



    public Rooms getCurrentRoom(){
        return this.currentRoom;
    };

    public void setCurrentRoom(Rooms newRoom){
        this.currentRoom = newRoom;
    };

    public Inventory getInventory() {
        return this.inventory;
    }
    public void reset() {
        this.currentRoom = Rooms.ROOM_ER; // Set to default starting room
        this.inventory = new Inventory(new Items[]{}); // Reset inventory to empty

    }
}