package JavaExamProject_Group15.Entity.Rooms.Hospital.Chapter0;

import JavaExamProject_Group15.Entity.Items.Chapter0.ITEM_BANDAGE;
import JavaExamProject_Group15.Entity.Items.ItemBase;
import JavaExamProject_Group15.Entity.Rooms.RoomBase;

public class ROOM_ER_STORAGE extends RoomBase {
    public ROOM_ER_STORAGE() {
        super("ER Storage room",
                "the ER storage room",
                new String[]{"ER storage", "storage"},
                new ItemBase[]{new ITEM_BANDAGE()});
    }
}
