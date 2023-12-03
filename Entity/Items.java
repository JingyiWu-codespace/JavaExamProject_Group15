package WPO_final.Entity;
import WPO_final.Player;
import WPO_final.Inventory;

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
                if (player.removeItemFromInventory(String.valueOf(Items.ITEM_402_KEYS))) {
                    System.out.println("she takes the keys and leaves");
                    this.get_owning_inventory().destroy_item(this);
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

    private Inventory owner_inv;

    public Inventory get_owning_inventory() {
        return owner_inv;
    }

    // ************************************************************
    private final BaseItem item_info;

    Items(String name, String description, String[] aliases, Boolean stationary) {
        this.item_info = new BaseItem(name, description, aliases, stationary);
    }

    public String get_name() {
        return this.item_info.getName();
    }

    public String get_description() {
        return this.item_info.getDescription();
    }

    public Boolean get_stationary() {
        return this.item_info.get_stationary();
    }

    public void set_stationary(Boolean stationary) {
        this.item_info.set_stationary(stationary);
    }

    public Boolean check_command(String alias) {
        return this.item_info.checkCommand(alias);
    }

    public abstract void interaction(ActionCodes action, Player player, Items other_item, Inventory other_inv);

    class BaseItem extends BaseEntity {
        private Boolean stationary;

        BaseItem(String name, String description, String[] aliases, Boolean stationary) {
            super(name, description, aliases);
            this.stationary = stationary;
        }

        public Boolean get_stationary() {
            return stationary;
        }

        public void set_stationary(Boolean stationary) {
            this.stationary = stationary;
        }
    }
}