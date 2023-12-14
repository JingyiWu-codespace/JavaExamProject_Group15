package JavaExamProject_Group15.Entity;

import JavaExamProject_Group15.Player;
import JavaExamProject_Group15.Inventory;

public enum Items {
    ITEM_ER_STORAGE_KEY("ER storage room key", "use it to open the storage room where the bandage is in",
            new String[]{"key"}, false) {
        @Override
        public void interaction() {
            this.pickup();
            Rooms.ROOM_ER.setExits(Rooms.ROOM_ER_STORAGE);
            System.out.println("you can now open the storage room and go there");
        }
    },
    BANDAGE("bandage", "you need take this to the patient in the ER room", new String[]{}, false) {},

    ER_PATIENT("patient", "the patient needs bandage", new String[]{}, true) {
        @Override
        public void interaction() {
            if (!checkAccessibility(Items.BANDAGE)) {
                System.out.println("you don't have the bandage");
                return;
            }

            Player.player.getInventory().destroyItem(Items.BANDAGE);
            System.out.println("you give the bandage to the patient\n" +
                    "the patient is now happy and loves you and wants to marry you. \n\n" +
                    "before you could say anything, the nurse comes in and takes the patient away");
            this.getOwningInventory(Items.ER_PATIENT).destroyItem(Items.ER_PATIENT);
        }
    };

    public Boolean getStationary() {
        return this.entityData.getStationary();
    }

    public void setStationary(Boolean stationary) {
        this.entityData.setStationary(stationary);
    }

    public void interaction() {
        this.pickup();
    }

    public void pickup() {
        if (this.getStationary()) {
            System.out.println("You can't pick up this item");
            return;
        }
        if (isInBag(this)) {
            System.out.println("You already have this item in your bag");
            return;
        }
        if (!isInRoom(this)) {
            System.out.println("You can't pick up this item as its not in this room");
            return;
        }

        Inventory playerInv = Player.player.getInventory();
        Inventory owningInv = getOwningInventory(this);
        owningInv.moveItem(this, playerInv);
        System.out.println("You picked up the item, " + this.getName());
    }

    public Boolean checkAccessibility(Items item) {
        return isInBag(item) || isInRoom(item);
    }

    public Boolean isInBag(Items item) {
        Inventory playerInv = Player.player.getInventory();
        for (Items i : playerInv.getItemList())
            if (i == item) return true;
        return false;
    }

    public Boolean isInRoom(Items item) {
        Inventory currRoomInv = Player.player.getCurrentRoom().getInventory();
        for (Items i : currRoomInv.getItemList())
            if (i == item) return true;
        return false;
    }

    public Inventory getOwningInventory(Items item) {
        if (isInBag(item)) return Player.player.getInventory();
        if (isInRoom(item)) return Player.player.getCurrentRoom().getInventory();
        for (Rooms r : Rooms.values())
            if (r.getInventory().checkAvailable(this))
                return r.getInventory();
        // todo
//        System.out.println("ERROR > item not found in any inventory");
        return null;
    }

    // ************************************************************
    private final ItemDataHolder entityData;

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

    Items(String name, String description, String[] aliases, Boolean stationary) {
        this.entityData = new ItemDataHolder(name, description, aliases, stationary);
    }

    static class ItemDataHolder extends BaseEntityDataHolder {
        private Boolean stationary;

        ItemDataHolder(String name, String description, String[] aliases, Boolean stationary) {
            super(name, description, aliases);
            this.stationary = stationary;
        }

        public Boolean getStationary() {
            return stationary;
        }

        public void setStationary(Boolean stationary) {
            this.stationary = stationary;
        }
    }
}