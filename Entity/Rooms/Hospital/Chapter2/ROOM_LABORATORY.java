package JavaExamProject_Group15.Entity.Rooms.Hospital.Chapter2;

import JavaExamProject_Group15.Entity.Items.Chapter2.ITEM_TEST_RESULT;
import JavaExamProject_Group15.Entity.Items.ItemBase;
import JavaExamProject_Group15.Entity.Rooms.RoomBase;

public class ROOM_LABORATORY extends RoomBase {
    public ROOM_LABORATORY() {
        super(
                "laboratory",
                "Perform tests like blood analysis to obtain vital health information about patients.",
                new String[]{"lab", "test room"},
                new ItemBase[]{new ITEM_TEST_RESULT()}
        );
    }
}
