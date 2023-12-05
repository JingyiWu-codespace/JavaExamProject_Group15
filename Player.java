package WPO_final;

import WPO_final.Entity.Items;
import WPO_final.Entity.Rooms;

import java.util.ArrayList;
import java.util.List;

public class Player {
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
