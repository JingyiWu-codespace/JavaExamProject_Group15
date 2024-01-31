package JavaExamProject_Group15.Entity.Actions.Primary;

import JavaExamProject_Group15.Entity.Actions.ActionBase;
import JavaExamProject_Group15.Entity.Items.ItemBase;
import JavaExamProject_Group15.Entity.Rooms.RoomBase;
import JavaExamProject_Group15.Inventory;

import java.util.List;

import static JavaExamProject_Group15.Player.currPlayer;

public class ACTION_HELP extends ActionBase {
    public ACTION_HELP() {
        super(
                "help",
                "Show available actions and their descriptions",
                new String[]{"h", "commands", "what can I do", "look"}
        );
    }

    private void executeAction(RoomBase room) {
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
        for (ActionBase action : actionList)
            System.out.print(" " + action.getName() + ",");
        System.out.println("\n");

        RoomBase currentRoom = currPlayer.getCurrentRoom();
        List<RoomBase> exits = RoomBase.getExits(currentRoom);
        if (!exits.isEmpty())
            System.out.println(" - accessible rooms");
        for (RoomBase room : exits)
            System.out.println("     - " + room.getName());

        Inventory roomInv = RoomBase.getRoomInv(currentRoom);
        if (!roomInv.getItemList().isEmpty())
            System.out.println(" - accessible entities in this room");
        for (ItemBase item : roomInv.getItemList())
            System.out.println("     - " + item.getName());

        if (!currPlayer.getInventory().getItemList().isEmpty())
            System.out.println(" - Inventory");
        for (ItemBase item : currPlayer.getInventory().getItemList())
            System.out.println("     - " + item.getName());
    }

    public void executeAction(ActionBase action) {
        action.printInformation();
        System.out.println("       aliases:");
        for (String alias : action.getAliases())
            System.out.println("            - " + alias);
    }

}

    