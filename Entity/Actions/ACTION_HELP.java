package JavaExamProject_Group15.Entity.Actions;

import JavaExamProject_Group15.Entity.Items.ItemBase;
import JavaExamProject_Group15.Entity.Rooms;

import static JavaExamProject_Group15.Player.currPlayer;

public class ACTION_HELP extends ActionBase {
    ActionBase[] actionsArray;
    private ACTION_HELP(ActionBase... actions) {
        super(
                "help",
                "Show available actions and their descriptions",
                new String[]{"h", "commands", "what can I do", "look"}
        );
        this.actionsArray = actions;
    }

    private void executeAction(Rooms room) {
        room.printInformation();
        System.out.println("       aliases:");
        for (String alias : room.getAliases())
            System.out.println("            - " + alias);
    }

    private void executeAction(ItemBase item) {
        item.printInformation();
        System.out.println("       aliases:");
        for (String alias : item.getAliases())
            System.out.println("            - " + alias);

    }

    private void executeAction() {
        System.out.println("type help followed by the thing you want to know more about");
        System.out.println("this entities are available here:");

        System.out.println(" - following commands are available:");
        System.out.print("  ");
        for (ActionBase action : actionsArray)
            System.out.print(" " + action.getName() + ",");
        System.out.println("\n");

        Rooms currentRoom = currPlayer.getCurrentRoom();
        if (!currentRoom.getExits().isEmpty())
            System.out.println(" - accessible rooms");
        for (Rooms room : currentRoom.getExits())
            System.out.println("     - " + room.getName());

        if (!currPlayer.getCurrentRoom().getRoomInv().getItemList().isEmpty())
            System.out.println(" - accessible entities in this room");
        for (ItemBase item : currPlayer.getCurrentRoom().getRoomInv().getItemList())
            System.out.println("     - " + item.getName());

        if (!currPlayer.getInventory().getItemList().isEmpty())
            System.out.println(" - Inventory");
        for (ItemBase item : currPlayer.getInventory().getItemList())
            System.out.println("     - " + item.getName());
    }

    private void executeAction(ActionBase action) {
        action.printInformation();
        System.out.println("       aliases:");
        for (String alias : action.getAliases())
            System.out.println("            - " + alias);
    }

}

    