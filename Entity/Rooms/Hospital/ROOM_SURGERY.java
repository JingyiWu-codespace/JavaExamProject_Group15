package JavaExamProject_Group15.Entity.Rooms.Hospital;

import JavaExamProject_Group15.Entity.Items.Chapter2.ITEM_BLOODY_BISTOURY;
import JavaExamProject_Group15.Entity.Items.Chapter2.ITEM_SURGEON;
import JavaExamProject_Group15.Entity.Items.ItemBase;
import JavaExamProject_Group15.Entity.Rooms.RoomBase;

public class ROOM_SURGERY extends RoomBase {
    public ROOM_SURGERY() {
        super(
                "Surgery Room",
                "sterile environment where surgical procedures are performed by medical professionals.",
                new String[]{"surgery"},
                new ItemBase[]{new ITEM_BLOODY_BISTOURY(), new ITEM_SURGEON()}
        );
    }
}
