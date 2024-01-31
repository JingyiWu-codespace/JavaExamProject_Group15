package JavaExamProject_Group15.Entity.Rooms.Hospital.Chapter2;

import JavaExamProject_Group15.Entity.Items.Chapter2.ITEM_ULTRASOUND_REPORT;
import JavaExamProject_Group15.Entity.Items.ItemBase;
import JavaExamProject_Group15.Entity.Rooms.RoomBase;

public class ROOM_ULTRASOUND extends RoomBase {
    public ROOM_ULTRASOUND() {
        super("Ultrasonic",
                "ultrasound procedures include abdominal ultrasounds, echocardiograms, and obstetric ultrasounds.",
                new String[]{"Ultra"},
                new ItemBase[]{new ITEM_ULTRASOUND_REPORT()}
        );
    }
}
