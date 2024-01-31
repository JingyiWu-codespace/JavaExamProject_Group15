package JavaExamProject_Group15.Entity.Rooms.Hospital;

import JavaExamProject_Group15.Entity.Items.Chapter0.ITEM_ER_STORAGE_KEY;
import JavaExamProject_Group15.Entity.Items.ItemBase;
import JavaExamProject_Group15.Entity.Rooms.RoomBase;

public class ROOM_ER extends RoomBase {
    public ROOM_ER() {
        super(
                "Emergency Room",
                "the place where you don't wanna be in",
                new String[]{"emergency", "er"},
                new ItemBase[]{new ITEM_ER_STORAGE_KEY()}
        );
    }
}
