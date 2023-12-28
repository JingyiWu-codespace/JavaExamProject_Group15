package JavaExamProject_Group15;

import JavaExamProject_Group15.Entity.Actions;
import JavaExamProject_Group15.Entity.Items;
import JavaExamProject_Group15.Entity.Rooms;

import static JavaExamProject_Group15.Player.player;

public class StoryTeller {

    public boolean checkVictoryPart1() {
        return Items.BANDAGE.getOwningInventory(Items.BANDAGE) == null;
//
    }
    public boolean checkVictoryPart2() {
        return Items.MEDICINE.getOwningInventory(Items.MEDICINE) == null;
    }
    public boolean checkVictoryPart3() {
        if (Rooms.ROOM_SURGERY.getInventory().itemList.contains(Items.ER_PATIENT3)){
            return true;
        }
        return false;
    }

    public void executeAction(Actions action_code) {
        switch (action_code) {
            case ACTION_HELP:
                System.out.println("You can use the following commands:");
                for (Actions temp : Actions.values()) {
                    System.out.println("    - " + temp.getName());
                    System.out.println("                   alias:");
                    for (String alias : temp.getAliases())
                        System.out.println("                       - " + alias);
                    System.out.println("                   details: " + temp.getDescription());
                }
                break;
            case ACTIONS_MAP:
                printHospitalMap();
                break;

            case ACTION_INVENTORY_CHECK:
                System.out.println("Items are in your bag:");
                for (Items temp : player.getInventory().getItemList()) {
                    System.out.println("    - " + temp.getName());
                    System.out.println("                   alias:");
                    for (String alias : temp.getAliases())
                        System.out.println("                       - " + alias);
                    System.out.println("                   details: " + temp.getDescription());
                }
                break;

            case ACTION_ROOM_CHECK:
                Rooms currentRoom = player.getCurrentRoom();
                System.out.println("You are in the " + currentRoom.getName());
                System.out.println("\nThe following items are in this room:");
                for (Items temp : currentRoom.getInventory().getItemList()) {
                    System.out.println("    - " + temp.getName());
                    System.out.println("                   alias:");
                    for (String alias : temp.getAliases())
                        System.out.println("                       - " + alias);
                    System.out.println("                   details: " + temp.getDescription());
                }
                break;
            default:
                System.out.println("your command is parsed, but not implemented, please try again");

//                for (Items temp : player.getCurrentRoom().getInventory().getItemList()) {
//                    System.out.println("    - " + temp.getName());
//                    System.out.println("                   alias:");
//                    for (String alias : temp.getAliases())
//                        System.out.println("                       - " + alias);
//                    System.out.println("                   details: " + temp.getDescription());
//                }
//                break;
//            default:
//                System.out.println("your command is parsed, but not implemented, please try again");
        }
    }
    public static void reset(){
        for (Rooms room : Rooms.values()) {
            room.resetInventory();
        }
    }

    public void executeAction(Actions action_code, Rooms room_code) {
        switch (action_code) {
            // print the room description
            case ACTION_HELP:
                System.out.println("the Room "+ room_code.getName() + "description is:");
                System.out.println("    - " + room_code.getDescription());
                break;
            // move to a different room
            case ACTION_MOVE:
                for (Rooms r : player.getCurrentRoom().getExits())
                    if (r == room_code) {
                        player.setCurrentRoom(r);
                        System.out.println("-> You move to the " + r.getName());
                        System.out.println("-> " + r.getDescription());
                        System.out.println("-> input 'room' to check items of " +r.getName());
                        return;
                    }
                System.out.println("You can't go there, maybe lack some important tools");
                break;
            default:
                System.out.println("your command is parsed, but not implemented, please try again");
        }
    }

    public void executeAction(Actions action_code, Items item_code) {
        switch (action_code) {
            // print the room description
            case ACTION_HELP:
                System.out.println("the entity "+ item_code.getName() + "description is:");
                System.out.println("    - " + item_code.getDescription());
                break;

            case ACTION_EXITS:
                System.out.println("You can try to DIRECTLY walk to these exits\n" +
                        "   some exits require interaction with entities:");
                for (Rooms temp : player.getCurrentRoom().getExits()) {
                    System.out.println("    - " + temp.getName());
                    System.out.println("                   details: " + temp.getDescription());
                }
                break;
            case ACTION_DISINFECT:
                try {
                    for (int i = 3; i > 0; i--) {
                        System.out.println("wait " + i + "m...");
                        Thread.sleep(1000); // 1000 milliseconds = 1 second
                    }
                } catch (InterruptedException e) {
                    System.out.println("Interrupted while waiting.");
                }
                item_code.setStationary(false);
                System.out.println("finished disinfected, you can pickup");


            case ACTION_INTERACT:
                item_code.interaction();
                break;
            case ACTION_LEAVE:
                System.out.println("put down"+item_code+ "in"+player.getCurrentRoom());
                player.getCurrentRoom().getInventory().itemList.add(item_code);
                player.getInventory().itemList.remove(item_code);
                break;
            default:
                System.out.println("your command is parsed, but not implemented, please try again");
        }
    }
    public static void printHospitalMap() {
        String[][] floors = {
                {"Emergency Room Storage", "Emergency Room", "Reception Desk", "Pharmacy", "Laboratory"},
                {"Radiology (X-ray)", "Ultrasonic Image", "ICU", "Surgery Room", "Patient Ward"},
                {"Obstetrics", "Gynecology", "REHABILITATION", "ENT Medical Area", "Doctor Officer"}
        };

        // Print the floors in reverse to start from the top floor
        for (int i = floors.length - 1; i >= 0; i--) {
            System.out.println("Floor " + i  + ":");
            for (String room : floors[i]) {
                System.out.println(" - " + room);
            }
            System.out.println();
        }

        System.out.println("Elevator and Stairs connects all floors.");
    }

}