package JavaExamProject_Group15.Entity;

import JavaExamProject_Group15.Inventory;

public enum Rooms implements BaseEntityInterface {
    ROOM_HALLWAY("hallway", "First floor, the elevators are here", new String[]{}, new Items[]{}),
    ROOM_2nd_HALLWAY("2nd hallway", "Second floor, access to patient rooms", new String[]{}, new Items[]{}),
    ROOM_402("402", "Room 402, where patient Alice is staying", new String[]{}, new Items[]{}),
    ROOM_LOBBY("lobby", "The main lobby of the hospital, bustling with activity", new String[]{}, new Items[]{});

    // MAP OF THE WORLD ******************************************************
    static {
        ROOM_HALLWAY.setExits(ROOM_2nd_HALLWAY);
        ROOM_2nd_HALLWAY.setExits(ROOM_HALLWAY, ROOM_402);
        ROOM_402.setExits(ROOM_2nd_HALLWAY);
        ROOM_LOBBY.setExits(ROOM_HALLWAY);
    }
    // MAP OF THE WORLD ******************************************************

    private final Inventory inventory;
    private Rooms[] exits;

    public void setExits(Rooms... exits) {
        this.exits = exits;
    }

    public Rooms[] getExits() {
        return exits;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    Rooms(String name, String description, String[] aliases, Items[] item_list) {
        this.inventory = new Inventory(item_list);
        this.setEntityData(name, description, aliases);
    }
}