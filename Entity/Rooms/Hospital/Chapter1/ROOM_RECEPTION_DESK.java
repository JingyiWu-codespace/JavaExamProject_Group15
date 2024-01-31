package JavaExamProject_Group15.Entity.Rooms.Hospital.Chapter1;

import JavaExamProject_Group15.Entity.Items.Chapter1.ITEM_REGISTER_FORM;
import JavaExamProject_Group15.Entity.Items.ItemBase;
import JavaExamProject_Group15.Entity.Rooms.RoomBase;

public class ROOM_RECEPTION_DESK extends RoomBase {
    public ROOM_RECEPTION_DESK() {
        super(
                "Reception Desk",
                " serving as the first point of contact for patients, visitors, and anyone entering the hospital.",
                new String[]{"info desk", "reception"},
                new ItemBase[]{new ITEM_REGISTER_FORM()}
        );
    }
}
