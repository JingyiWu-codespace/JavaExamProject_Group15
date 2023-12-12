package JavaExamProject_Group15.Entity;

import JavaExamProject_Group15.Player;
import JavaExamProject_Group15.Inventory;

public enum Items {
    ITEM_HALLWAY_DOOR("hallway door", "its a door, not much else to say", new String[]{}, false) {
        @Override
        public void interaction(Player player, Items otherEntity) {
            System.out.println("You open the door");
        }
    },
    ITEM_402_KEYS("staff elevator keys", "keys to staff elevator", new String[]{}, false) {
        @Override
        public void interaction(Player player, Items otherEntity) {
            System.out.println("its the keys to the staff elevator");
        }
    },
    ITEM_NURSE("nurse", "she's cute, but seems confused", new String[]{}, false) {
        @Override
        public void interaction(Player player, Items otherEntity) {
            System.out.println("turns out she was searching for a needle she lost.");
            if (otherEntity == Items.ITEM_402_KEYS) {
                Items temp = player.getInventory().getItem(Items.ITEM_402_KEYS);
                if (temp != null) {
                    System.out.println("she takes the needle and gives you the keys to room 402");
                    this.getOwningInventory(player.getInventory()).destroyItem(this);
                }
            } else
                System.out.println("the nurse refuses to leave and is still searching the place and refuses to let you in");
        }
    },
    ITEM_ELEVATOR("elevator", "an elevator to the other floor", new String[]{}, true) {
        @Override
        public void interaction(Player player, Items otherEntity) {
            System.out.println("You enter the elevator");
            if (player.getCurrentRoom() == Rooms.ROOM_HALLWAY) {
                player.setCurrentRoom(Rooms.ROOM_2nd_HALLWAY);
                System.out.println("You enter the elevator and go to the second floor");
            } else {
                player.setCurrentRoom(Rooms.ROOM_HALLWAY);
                System.out.println("You enter the elevator and go to the first floor");
            }
        }
    },
    ITEM_NEEDLE("needle", "a needle", new String[]{}, false) {
        @Override
        public void interaction(Player player, Items otherEntity) {
            if (!checkAccessiblity(this, player)) return;
            if (!checkAccessiblity(otherEntity, player)) return;

            if(otherEntity == Items.ITEM_NURSE) {
                System.out.println("you give the needle to the nurse");
                Inventory owningInv=player.getInventory();
                owningInv.destroyItem(this);
            } else
                System.out.println("you can't do anything with the "+this.getName()+" and "+otherEntity);
        }
    };

    Inventory getOwningInventory(Inventory playerInventory) {
        if (playerInventory.checkAvailable(this)) return playerInventory;
        for (Rooms r : Rooms.values())
            if (r.getInventory().checkAvailable(this)) return r.getInventory();
        System.out.println("ERROR > item not found in any inventory");
        return null;
    }

    public Boolean getStationary() {
        return this.entityData.getStationary();
    }

    public void setStationary(Boolean stationary) {
        this.entityData.setStationary(stationary);
    }

    public void interaction(Player player, Items otherEntity) {
        if (!checkAccessiblity(this, player)) return;
        if (!checkAccessiblity(otherEntity, player)) return;
        System.out.println(" can't do any combos with "+this.getName()+" and "+otherEntity.getName());
    }

    public void interaction(Player player) {
        if (this.getStationary()) {
            System.out.println("You can't pick up this item");
            return;
        }
        if (!checkAccessiblity(this, player)) return;
        Inventory playerInv = player.getInventory();
        Inventory owningInv = getOwningInventory(player.getInventory());
        if (owningInv != playerInv)
            owningInv.moveItem(this, playerInv);
    }

    public Boolean checkAccessiblity(Items item, Player player) {
        Inventory owningInv = item.getOwningInventory(player.getInventory());
        Inventory playerInv = player.getInventory();
        Inventory currRoomInv = player.getCurrentRoom().getInventory();
        if (owningInv != playerInv && owningInv != currRoomInv) {
            System.out.println(item.getName()+" is not in your bag, nor in the room");
            return false;
        }
        return true;
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

    public String[] getCommandArray() {
        return this.entityData.getCommandArray();
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