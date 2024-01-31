package JavaExamProject_Group15.Entity.Rooms.Hospital.Chapter2;

import JavaExamProject_Group15.Entity.Items.ItemBase;
import JavaExamProject_Group15.Entity.Rooms.RoomBase;

public class ROOM_HALLWAY extends RoomBase {
    public ROOM_HALLWAY() {
        super(
                "Hallway",
                "its a hallway.",
                new String[]{"corridor"},
                new ItemBase[]{}
        );
    }
}
