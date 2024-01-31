package JavaExamProject_Group15.Entity.Actions.Hospital;

import JavaExamProject_Group15.Entity.Actions.ActionBase;
import JavaExamProject_Group15.Entity.Items.ItemBase;
import JavaExamProject_Group15.Entity.Rooms.RoomBase;

import static JavaExamProject_Group15.Player.currPlayer;

public class ACTION_ROOM_CHECK extends ActionBase {
    public ACTION_ROOM_CHECK() {
        super(
                "room",
                "Check the current room and items in it",
                new String[]{"where am I", "location", "room check", "look around"}
        );
    }

    public boolean executeAction() {
        RoomBase currentRoom = currPlayer.getCurrentRoom();
        System.out.println("You are in the " + currentRoom.getName());

        System.out.println("\nThe following entities are in this room:");
        for (ItemBase temp : RoomBase.getRoomInv(currentRoom).getItemList())
            temp.printInformation();
        return false;
    }

}

    