package JavaExamProject_Group15.Entity.Rooms.Hospital.Chapter1;

import JavaExamProject_Group15.Entity.Items.Chapter1.ITEM_DOCTOR;
import JavaExamProject_Group15.Entity.Items.ItemBase;
import JavaExamProject_Group15.Entity.Rooms.RoomBase;

public class ROOM_OFFICE extends RoomBase {
    public ROOM_OFFICE() {
        super(
                "Doctor's Office",
                "You should communicate with doctor then get prescription instead of take the prescription directly .",
                new String[]{"doctor office", "office"},
                new ItemBase[]{new ITEM_DOCTOR()}
        );
    }
}
