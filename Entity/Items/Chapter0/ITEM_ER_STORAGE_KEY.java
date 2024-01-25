package JavaExamProject_Group15.Entity.Items.Chapter0;

import JavaExamProject_Group15.Entity.Items.ItemBase;
import JavaExamProject_Group15.Inventory;

import static JavaExamProject_Group15.Entity.Rooms.ROOM_ER;
import static JavaExamProject_Group15.Entity.Rooms.ROOM_ER_STORAGE;

public class ITEM_ER_STORAGE_KEY extends ItemBase {
    public ITEM_ER_STORAGE_KEY() {
        super(
                "ER storage room key",
                "use it to open the storage room where the bandage is in",
                new String[]{"er storage key", "storage key", "storage keys", "storage room keys", "storage room key"},
                false
        );
    }

    public void pickup() {
        super.pickup();
        if (!Inventory.inBag(this)) return;

        ROOM_ER.addExits(ROOM_ER_STORAGE);
        System.out.println("you also unlocked the storage room door and can go there");
    }
}

    