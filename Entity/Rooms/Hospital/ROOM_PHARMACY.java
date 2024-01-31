package JavaExamProject_Group15.Entity.Rooms.Hospital;

import JavaExamProject_Group15.Entity.Items.Chapter1.ITEM_PHARMACY_CLERK;
import JavaExamProject_Group15.Entity.Items.ItemBase;
import JavaExamProject_Group15.Entity.Rooms.RoomBase;

public class ROOM_PHARMACY extends RoomBase {
    //    ROOM_PHARMACY(
//            "Pharmacy",
//            "dispensing prescribed medications to patients.",
//            new String[]{"medicine room", "drug store"},
//            new ItemBase[]{new ITEM_PHARMACY_CLERK()}
//    )
    public ROOM_PHARMACY() {
        super(
                "Pharmacy",
                "dispensing prescribed medications to patients.",
                new String[]{"medicine room", "drug store"},
                new ItemBase[]{new ITEM_PHARMACY_CLERK()}
        );
    }
}
