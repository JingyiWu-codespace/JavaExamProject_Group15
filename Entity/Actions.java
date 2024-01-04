package JavaExamProject_Group15.Entity;

import JavaExamProject_Group15.Inventory;

import static JavaExamProject_Group15.Player.player;

/**
 * Represents the various actions that can be performed in the game.
 * Each action is associated with a command name, description, and aliases.
 * Actions define different behaviors depending on the context, such as
 * checking inventory, dropping items, or moving between rooms.
 */
public enum Actions {
    // ************************* GENERIC COMMANDS ***********************************
    /**
     * Action to check the player's inventory.
     * It lists all items in the player's bag.
     */
    ACTION_INVENTORY_CHECK("inventory", "Check your bag",
            new String[]{"inv", "bag", "backpack"}) {
        @Override
        public void executeAction() {
            System.out.println("Items are in your bag:");
            for (Items temp : player.getInventory().getItemList())
                temp.printInformation();
        }
    },
    /**
     * Action to drop an item from the inventory into the current room.
     *
     * @param item The item to be dropped.
     */
    ACTION_DROP("drop", "leave something in room", new String[]{"put", "leave away"}) {
        @Override
        public void executeAction(Items item) {
            if (item == Items.ITEM_MOTHER_PATIENT){
                System.out.println("you can't do that. the poor woman asks you why you leaving her, she is in pain and is scared");
                return;
            }
            Rooms currentRoom = player.getCurrentRoom();
            Inventory roomInventory = currentRoom.getInventory();

            player.getInventory().moveItem(item, roomInventory);

            System.out.println(item + "has been placed in" + " " + currentRoom.getName());
        }
    },
    /**
     * Action to check layout of hospital
     */
    ACTIONS_MAP("maps","the layout of hospital",new String[]{"map"}) {
        @Override
        public void executeAction() {
            System.out.println(
                " --- floor 0 --- " +
                "\n  - 'reception desk -> leads to'"+
                "\n          - 'elevator'"+
                "\n          - 'doctor's office'"+
                "\n          - 'pharmacy'"+
                "\n          - 'emergency room -> leads to'"+
                "\n                  - 'ER storage room'"
            );
            System.out.println(
                "\n --- floor 1 --- " +
                "\n  - 'hallway -> leads to'"+
                "\n          - 'elevator'"+
                "\n          - 'ward'"+
                "\n          - 'ultrasound'"+
                "\n          - 'lab'"
            );
            System.out.println(
                "\n --- floor 2 --- " +
                "\n  - 'surgery room'"
            );
        }
    },
    /**
     * Check the current room and items in it
     */
    ACTION_ROOM_CHECK("room", "Check the current room and items in it",
            new String[]{"where am I", "location", "room check", "look around"}) {
        @Override
        public void executeAction() {
            Rooms currentRoom = player.getCurrentRoom();
            System.out.println("You are in the " + currentRoom.getName());

            System.out.println("\nThe following entities are in this room:");
            for (Items temp : currentRoom.getInventory().getItemList())
                temp.printInformation();
        }
    },
    /**
     * Move action
     */
    ACTION_GO("go", "Move to a different room",
            new String[]{"move", "walk", "run", "sprint", "enter"}) {
        @Override
        public void executeAction(Rooms room) {
            for (Rooms r : player.getCurrentRoom().getExits())
                if (r == room) {
                    player.setCurrentRoom(r);
                    System.out.println("-> You move to the " + r.getName());
                    System.out.println("        description:  " + r.getDescription());
                    return;
                }
            System.out.println("You can't go there, either some important item is missing or its simply not accessible from here");
        }
    },
    /**
     * Show possible rooms to go to
     */
    ACTION_EXITS("exits", "Show possible rooms to go to", new String[]{"exit list", "exit", "where to go"}) {
        @Override
        public void executeAction() {
            System.out.println("You can try to DIRECTLY walk to these exits\n" +
                    "   some exits require interaction with entities:");
            for (Rooms room : player.getCurrentRoom().getExits())
                room.printInformation();
        }
    },
    /**
     * interact with item, not pickup and take,use blood test, ultra test
     */
    ACTION_INTERACT("interact", "Interact with an item", new String[]{"use", "open", "talk"}) {
        @Override
        public void executeAction(Items item) {
            if (item.isInBag() || item.isInRoom())
                item.interaction();
            else
                System.out.println("the entity is not accessible");
        }
    },
    /**
     * pickup item, then put into player inventory
     */
    ACTION_GRAB("grab", "Interact with an item", new String[]{"get", "take", "pickup"}) {
        @Override
        public void executeAction(Items item) {
            item.pickup();
        }
    },
    /**
     * Show available actions and their descriptions
     */
    ACTION_HELP("help", "Show available actions and their descriptions",
            new String[]{"h", "commands", "what can I do", "look"}){
        @Override
        public void executeAction(Rooms room) {
            room.printInformation();
            System.out.println("       aliases:");
            for(String alias : room.getAliases())
                System.out.println("            - " + alias);
        }
        @Override
        public void executeAction(Items item) {
            item.printInformation();
            System.out.println("       aliases:");
            for(String alias : item.getAliases())
                System.out.println("            - " + alias);

        }
        @Override
        public void executeAction() {
            System.out.println("type help followed by the thing you want to know more about");
            System.out.println("this entities are available here:");

            System.out.println(" - following commands are available:");
            System.out.print("  ");
            for (Actions action : Actions.values())
                System.out.print(" " + action.getName()+",");
            System.out.println("\n");

            if(player.getCurrentRoom().getExits().length>0)
                System.out.println(" - accessible rooms");
            for (Rooms room : player.getCurrentRoom().getExits())
                System.out.println("     - " + room.getName());

            if(!player.getCurrentRoom().getInventory().getItemList().isEmpty())
                System.out.println(" - accessible entities in this room");
            for (Items item : player.getCurrentRoom().getInventory().getItemList())
                System.out.println("     - " + item.getName());

            if(!player.getInventory().getItemList().isEmpty())
                System.out.println(" - Inventory");
            for (Items item : player.getInventory().getItemList())
                System.out.println("     - " + item.getName());
        }
        @Override
        public void executeAction(Actions action) {
            action.printInformation();
            System.out.println("       aliases:");
            for(String alias : action.getAliases())
                System.out.println("            - " + alias);
        }
    },
    // ************************* SPECIAL COMMANDS ***********************************
    /**
     * stop game directly
     */
    ACTION_QUIT("quit the game", "quits the game, who would have thought", new String[]{"quit"})
    ;

    public void executeAction(Items item, Rooms room){
        if (item==null && room==null)
            executeAction();
        else if (item==null)
            executeAction(room);
        else if (room==null)
            executeAction(item);
        else
            System.out.println("ERROR: BOTH ROOM AND ITEM ARE OBJECTS");
    }

    public void executeAction(Items item){
        System.out.println("doing "+this.getName() + ": I cant figure anything to do with " + item.getName());
    }
    public void executeAction(Rooms room){
        System.out.println("doing "+this.getName() + ": what am I suppose to do to " + room.getName());
    }
    public void executeAction(){
        System.out.println("doing "+this.getName() + ":this action in isolation is not possible");
    }
    public void executeAction(Actions action) {}

    // ************************************************************
    private final ActionDataHolder entityData;

    public String getName() {
        return this.entityData.getName();
    }


    public void printInformation(){ this.entityData.printInformation(); }

    public String getDescription() {
        return this.entityData.getDescription();
    }

    public Boolean checkCommand(String alias) {
        return this.entityData.checkCommand(alias);
    }

    public String[] getAliases() {
        return this.entityData.getAliases();
    }

    Actions(String name, String description, String[] aliases) {
        this.entityData = new ActionDataHolder(name, description, aliases);
    }

    static class ActionDataHolder extends BaseEntityDataHolder {
        ActionDataHolder(String name, String description, String[] aliases) {
            super(name, description, aliases);
        }
    }

}
