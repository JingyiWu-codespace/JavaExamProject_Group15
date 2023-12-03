package WPO_final;

import WPO_final.Entity.Rooms;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private Rooms currentRoom;
    private List<String> inventory;
    public Player() {
        // 默认玩家开始在 "lobby"
        currentRoom = Rooms.ROOM_LOBBY; //default start in Lobby
        inventory = new ArrayList<>();
    }

    public String getInventory() {
        return inventory.isEmpty() ? "nothing" : String.join(", ", inventory);
    }

    public Rooms getCurrentRoom(){
        return this.currentRoom;
    };
    public void addItemToInventory(String item){
        inventory.add(item);
    }
    public boolean removeItemFromInventory(String item) {
        // The remove() method itself returns true if the list contained the specified element
        return inventory.remove(item);
    }
    public void setCurrentRoom(Rooms newRoom){
        this.currentRoom = newRoom;
    };
}
