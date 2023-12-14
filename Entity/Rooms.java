package JavaExamProject_Group15.Entity;

import JavaExamProject_Group15.Inventory;

public enum Rooms {
    ROOM_ER("Emergency Room", "the place where you don't wanna be in", new String[]{"emergency", "er"}, new Items[]{Items.ER_PATIENT, Items.ITEM_ER_STORAGE_KEY}),
    ROOM_ER_STORAGE("ER Storage place", "the ER storage room", new String[]{"storage"}, new Items[]{Items.BANDAGE});

    static {
        ROOM_ER_STORAGE.setExits(ROOM_ER);
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

    public String[] getAliases() {
        return this.entityData.getAliases();
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