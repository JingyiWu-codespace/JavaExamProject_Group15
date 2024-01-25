package JavaExamProject_Group15.Entity.Items.Chapter0;

import JavaExamProject_Group15.Entity.Items.ItemBase;

public class ITEM_BANDAGE extends ItemBase {
    public ITEM_BANDAGE() {
        super(
                "bandage",
                "you need take this to the patient in the ER room",
                new String[]{},
                false);
    }

    public void interaction() {
        this.pickup();
        System.out.println("Take the bandage to the patient in the ER room.");
    }
}

    