package JavaExamProject_Group15.Entity.Rooms.PokeGuns;

import JavaExamProject_Group15.Entity.Items.ItemBase;
import JavaExamProject_Group15.Entity.Items.PokeGuns.HP;
import JavaExamProject_Group15.Entity.Rooms.RoomBase;

public class Arena2 extends RoomBase {
    public Arena2() {
        super(
                "Arena - side B",
                "kill them all",
                new String[]{},
                new ItemBase[]{new HP()}
        );
    }
}
