package JavaExamProject_Group15.Entity;

import JavaExamProject_Group15.Inventory;

public enum Rooms {
    ROOM_HALLWAY("hallway", "First floor, the elevators are here"),
    ROOM_2nd_HALLWAY("2nd hallway", "Second floor, access to patient rooms"),
    ROOM_402("402", "Room 402, where patient Alice is staying"),
    ROOM_LOBBY("lobby", "The main lobby of the hospital, bustling with activity");

    private final Inventory inventory;
    private Rooms[] exits; // Adjacent rooms
    private RoomInfo roomInfo;

    // ************************************************************

    private final RoomInfo room_info;

    Rooms(String name, String description) {
        this.room_info = new RoomInfo(name, description, new String[]{});
        this.inventory = new Inventory(new Items[]{});
    }

    public void setExits(Rooms... exits) {
        this.exits = exits;
    }

    public Rooms[] getExits() {
        return exits;
    }

    static {
        ROOM_HALLWAY.setExits(ROOM_2nd_HALLWAY);
        ROOM_2nd_HALLWAY.setExits(ROOM_HALLWAY, ROOM_402);
        ROOM_402.setExits(ROOM_2nd_HALLWAY);
        ROOM_LOBBY.setExits(ROOM_HALLWAY);
    }

    public String getName() {
        return this.room_info.getName();
    }

    public String[] getCommandArray() {
        return this.room_info.getCommandArray();
    }

    public String get_description() {
        return this.room_info.getDescription();
    }

    public Boolean check_command(String alias) {
        return this.room_info.checkCommand(alias);
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    private class RoomInfo extends BaseEntity {
        RoomInfo(String name, String description, String[] aliases) {
            super(name, description, aliases);
        }
    }
}