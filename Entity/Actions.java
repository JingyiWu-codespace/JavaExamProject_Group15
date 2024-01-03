package JavaExamProject_Group15.Entity;

import JavaExamProject_Group15.Inventory;

import static JavaExamProject_Group15.Player.player;

public enum Actions {
    // ************************************************************
//    ACTION_DISINFECT("disinfect","disinfect specific item",new String[]{"clear"}) {
//        @Override
//        public void executeAction(Items item) {
//            if (!item.infected) {
//                System.out.println("this entity doesn't need disinfecting");
//                return;
//            }
//
//            try {
//                for (int i = 3; i > 0; i--) {
//                    System.out.println("wait " + i + "m...");
//                    Thread.sleep(1000); // 1000 milliseconds = 1 second
//                }
//            } catch (InterruptedException e) {
//                System.out.println("Interrupted while waiting.");
//            }
//            item.setStationary(false);
//            System.out.println("finished disinfected, you can pickup");
//        }
//    },
    ACTIONS_ELEVATOR("elevator","user elevator to specific floor",new String[]{"lift"}) {
        @Override
        public void executeAction() {
            System.out.println("Items are in your bag:");
            for (Items temp : player.getInventory().getItemList()) {
                System.out.println("  - '" + temp.getName()+"'");
                System.out.println("       details: " + temp.getDescription());
            }
        }
    },
    ACTIONS_STAIR("stair","user stair to specific floor",new String[]{"steps"}) {
        @Override
        public void executeAction() {
            System.out.println("Items are in your bag:");
            for (Items temp : player.getInventory().getItemList()) {
                System.out.println("  - '" + temp.getName()+"'");
                System.out.println("       details: " + temp.getDescription());
            }
        }
    },
    // ************************* GENERIC COMMANDS ***********************************
    ACTION_INVENTORY_CHECK("inventory", "Check your bag",
            new String[]{"inv", "bag", "backpack"}) {
        @Override
        public void executeAction() {
            System.out.println("Items are in your bag:");
            for (Items temp : player.getInventory().getItemList())
                temp.printInformation();
        }
    },
    ACTIONS_MAP("maps","the layout of hospital",new String[]{"map"}) {
        @Override
        public void executeAction() {
            // todo fix this
            String[][] floors = {
                    {"Emergency Room Storage", "Emergency Room", "Reception Desk", "Pharmacy", "Laboratory"},
                    {"Radiology (X-ray)", "Ultrasonic Image", "ICU", "Surgery Room", "Patient Ward"},
                    {"Obstetrics", "Gynecology", "REHABILITATION", "ENT Medical Area", "Doctor Officer"}
            };

            // Print the floors in reverse to start from the top floor
            for (int i = floors.length - 1; i >= 0; i--) {
                System.out.println("Floor " + i + ":");
                for (String room : floors[i]) {
                    System.out.println(" - " + room);
                }
                System.out.println();
            }

            System.out.println("Elevator and Stairs connects all floors.");
        }
    },
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
    ACTION_MOVE("move", "Move to a different room",
            new String[]{"go", "walk", "run", "sprint", "enter"}) {
        @Override
        public void executeAction(Rooms room) {
            for (Rooms r : player.getCurrentRoom().getExits())
                if (r == room) {
                    player.setCurrentRoom(r);
                    System.out.println("-> You move to the " + r.getName());
                    System.out.println("   description:  " + r.getDescription());
                    return;
                }
            System.out.println("You can't go there, either some important item is missing or its simply not accessible from here");
        }
    },
    ACTION_EXITS("exits", "Show possible rooms to go to", new String[]{"exit list", "exit", "where to go"}) {
        @Override
        public void executeAction() {
            System.out.println("You can try to DIRECTLY walk to these exits\n" +
                    "   some exits require interaction with entities:");
            for (Rooms room : player.getCurrentRoom().getExits())
                room.printInformation();
        }
    },
    ACTION_DROP("drop","leave something in room",new String[]{"put", "leave away"}) {
        @Override
        public void executeAction(Items item) {
            Rooms currentRoom = player.getCurrentRoom();
            Inventory roomInventory = currentRoom.getInventory();

            player.getInventory().moveItem(item, roomInventory);

            System.out.println("the item" + item + "has been placed in" + currentRoom.getName());
        }
    },
    ACTION_INTERACT("interact", "Interact with an item", new String[]{"use", "open", "talk"}) {
        @Override
        public void executeAction(Items item) {
            item.interaction();
        }
    },
    ACTION_GRAB("grab", "Interact with an item", new String[]{"get", "take", "pickup"}) {
        @Override
        public void executeAction(Items item) {
            item.pickup();
        }
    },
    ACTION_HELP("help", "Show available actions and their descriptions", new String[]{"h", "commands"}){
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
            System.out.println(" - accessible rooms");
            for (Rooms room : player.getCurrentRoom().getExits())
                System.out.println("    - " + room.getName());
            System.out.println(" - accessible entities");
            for (Items item : player.getCurrentRoom().getInventory().getItemList())
                System.out.println("    - " + item.getName());
            for (Items item : player.getInventory().getItemList())
                System.out.println("    - " + item.getName());
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
    ACTION_QUIT("quit", "quits the game, who would have thought", new String[]{})
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
