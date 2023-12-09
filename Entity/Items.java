package JavaExamProject_Group15.Entity;
import JavaExamProject_Group15.Player;
import JavaExamProject_Group15.Inventory;

public enum Items {
    ITEM_HALLWAY_DOOR("hallway door", "its a door, not much else to say", new String[]{}, false) {
        @Override
        public void interaction(ActionCodes action, Player player, Items other_item, Inventory other_inv) {
            System.out.println("You open the door");
        }
    },
    ITEM_402_KEYS("staff elevator keys", "keys to staff elevator", new String[]{}, false) {
        @Override
        public void interaction(ActionCodes action, Player player, Items other_item, Inventory other_inv) {
            System.out.println("its the keys to the staff elevator");
        }
    },
    ITEM_NURSE("nurse", "she's cute, but seems confused", new String[]{}, false) {
        @Override
        public void interaction(ActionCodes action, Player player, Items other_item, Inventory other_inv) {
            System.out.println("turns out she was searching for her keys");
            if (other_item == Items.ITEM_402_KEYS) {
                Items temp = player.getInventory().getItem(Items.ITEM_402_KEYS);
                if (temp != null) {
                    System.out.println("she takes the keys and leaves");
                    this.getOwningInventory().destroy_item(this);
                }
            } else
                System.out.println("the nurse refuses to leave and is still searching the place and refuses to let you in");
        }
    },
    ITEM_ELEVATOR("elevator", "an elevator to the other floor", new String[]{}, true) {
        @Override
        public void interaction(ActionCodes action, Player player, Items other_item, Inventory other_inv) {
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
        public void interaction(ActionCodes action, Player player, Items other_item, Inventory other_inv) {
            System.out.println("You use the needle to i");
        }
    };

    private Inventory ownerInv;

    public Inventory getOwningInventory() {
        return ownerInv;
    }

    public abstract void interaction(ActionCodes action, Player player, Items other_item, Inventory otherInv);

    // ************************************************************
    private final BaseItem itemInfo;

    Items(String name, String description, String[] aliases, Boolean stationary) {
        this.itemInfo = new BaseItem(name, description, aliases, stationary);
    }

    public String getName() {
        return this.itemInfo.getName();
    }

    public String getDescription() {
        return this.itemInfo.getDescription();
    }

    public Boolean getStationary() {
        return this.itemInfo.getStationary();
    }

    public void setStationary(Boolean stationary) {
        this.itemInfo.setStationary(stationary);
    }

    public Boolean checkCommand(String alias) {
        return this.itemInfo.checkCommand(alias);
    }

    public String[] getCommandArray() {
        return this.itemInfo.getCommandArray();
    }

    class BaseItem extends BaseEntity {
        private Boolean stationary;

        BaseItem(String name, String description, String[] aliases, Boolean stationary) {
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