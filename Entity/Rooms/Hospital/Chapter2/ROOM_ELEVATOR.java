package JavaExamProject_Group15.Entity.Rooms.Hospital.Chapter2;

import JavaExamProject_Group15.Entity.Items.Chapter2.ITEM_BUTTONBAR;
import JavaExamProject_Group15.Entity.Items.ItemBase;
import JavaExamProject_Group15.Entity.Rooms.RoomBase;

import static JavaExamProject_Group15.Player.currPlayer;

public class ROOM_ELEVATOR extends RoomBase {
    public ROOM_ELEVATOR() {
        super(
                "Elevator",
                "its an elevator, there are 3 floors",
                new String[]{"lift"},
                new ItemBase[]{new ITEM_BUTTONBAR()}
        );
    }

    @Override
    public void playerEnter() {
        System.out.println("you are in the elevator, you can go to the 1st, 2nd or 3rd floor");
        RoomBase prevRoom = currPlayer.getCurrentRoom();
        RoomBase.removeAllExits(this);
        RoomBase.oneWayPass(this, prevRoom);
        super.playerEnter();
    }
}
