package JavaExamProject_Group15.Entity.Items.Chapter0;

import JavaExamProject_Group15.Entity.Items.ItemBase;
import JavaExamProject_Group15.Entity.Rooms.Hospital.Chapter0.ROOM_ER;
import JavaExamProject_Group15.Entity.Rooms.Hospital.Chapter0.ROOM_ER_STORAGE;
import JavaExamProject_Group15.Entity.Rooms.RoomBase;
import JavaExamProject_Group15.Inventory;

public class ITEM_ER_STORAGE_KEY extends ItemBase {
    public ITEM_ER_STORAGE_KEY() {
        super(
                "ER storage room key",
                "use it to open the storage room where the bandage is in",
                new String[]{"er storage key", "storage room keys", "keys", "key", "storage room key"},
                false
        );
    }

    public void pickup() {
        super.pickup();
        if (!Inventory.inBag(this)) return;

        RoomBase.oneWayPass(ROOM_ER.class, ROOM_ER_STORAGE.class);
        System.out.println("you also unlocked the storage room door and can go there");
    }
}

    