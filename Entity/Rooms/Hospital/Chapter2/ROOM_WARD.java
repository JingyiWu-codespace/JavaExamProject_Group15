package JavaExamProject_Group15.Entity.Rooms.Hospital.Chapter2;

import JavaExamProject_Group15.Entity.Items.Chapter2.ITEM_ALCOHOL;
import JavaExamProject_Group15.Entity.Items.ItemBase;
import JavaExamProject_Group15.Entity.Rooms.RoomBase;

public class ROOM_WARD extends RoomBase {
    public ROOM_WARD() {
        super("Patient Ward",
                "Ward rooms provide comfortable accommodations for patients during their hospital stay.",
                new String[]{"ward"},
                new ItemBase[]{new ITEM_ALCOHOL()}
        );
    }
}
