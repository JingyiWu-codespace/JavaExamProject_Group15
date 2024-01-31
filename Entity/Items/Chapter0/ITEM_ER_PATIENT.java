package JavaExamProject_Group15.Entity.Items.Chapter0;

import JavaExamProject_Group15.Entity.Items.ItemBase;
import JavaExamProject_Group15.Inventory;

import JavaExamProject_Group15.Entity.Rooms.Hospital.ROOM_ER;

public class ITEM_ER_PATIENT extends ItemBase {
    public ITEM_ER_PATIENT() {
        super(
                "Bleeding Patient",
                "the patient is bleeding. give him the bandage, he'll do the rest.",
                new String[]{},
                true
        );
    }

    public void interaction() {
        if (!Inventory.inBag(ITEM_BANDAGE.class)) {
            System.out.println("you don't have the bandage");
            return;
        }

        System.out.println("you give the bandage to the patient\n" +
                "the patient's wound has been controlled and they start to express a passionate love for you.\n" +
                "before you get to hear a cry for you love, they are taken away \n\n");

        Inventory.removeFromAllInvs(this);

        ITEM_BANDAGE bandage = Inventory.getFromBag(ITEM_BANDAGE.class);
        Inventory.removeFromAllInvs(bandage);

        ITEM_ER_STORAGE_KEY skey = Inventory.getFromBag(ITEM_ER_STORAGE_KEY.class);
        Inventory.moveThisToRoom(skey, ROOM_ER.class);
    }
}

    