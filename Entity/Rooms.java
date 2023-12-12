package JavaExamProject_Group15.Entity;

import JavaExamProject_Group15.Inventory;

public enum Rooms {
    ROOM_HALLWAY("hallway", "First floor, the elevators are here", new String[]{}, new Items[]{}),
    ROOM_2nd_HALLWAY("2nd hallway", "Second floor, access to patient rooms", new String[]{}, new Items[]{}),
    ROOM_402("402", "Room 402, where patient Alice is staying", new String[]{}, new Items[]{}),
    ROOM_LOBBY("lobby", "The main lobby of the hospital, bustling with activity", new String[]{}, new Items[]{});

    static {
        ROOM_HALLWAY.setExits(ROOM_2nd_HALLWAY);
        ROOM_2nd_HALLWAY.setExits(ROOM_HALLWAY, ROOM_402);
        ROOM_402.setExits(ROOM_2nd_HALLWAY);
        ROOM_LOBBY.setExits(ROOM_HALLWAY);
    }

    public void setExits(Rooms... exits) {
        this.entityData.exits = exits;
    }
    public Rooms[] getExits() {
        return this.entityData.exits;
    }

    public Inventory getInventory() {
        return this.entityData.inventory;
    }

    // ************************************************************
    private final Rooms.RoomDataHolder entityData;

    public String getName() {
        return this.entityData.getName();
    }

    public String getDescription() {
        return this.entityData.getDescription();
    }

    public Boolean checkCommand(String alias) {
        return this.entityData.checkCommand(alias);
    }

    public String[] getCommandArray() {
        return this.entityData.getCommandArray();
    }

    Rooms(String name, String description, String[] aliases, Items[] item_list) {
        this.entityData = new RoomDataHolder(name, description, aliases, item_list);
    }

    static class RoomDataHolder extends BaseEntityDataHolder {
        private Rooms[] exits;
        private final Inventory inventory;

        RoomDataHolder(String name, String description, String[] aliases, Items[] item_list) {
            super(name, description, aliases);
            this.exits = new Rooms[]{};
            this.inventory = new Inventory(item_list);
        }

        public Rooms[] getExits() {
            return this.exits;
        }

        public void setExits(Rooms... exits) {
            this.exits = exits;
        }
    }
}