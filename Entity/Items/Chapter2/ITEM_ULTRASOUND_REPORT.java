package JavaExamProject_Group15.Entity.Items.Chapter2;

import JavaExamProject_Group15.Entity.Items.ItemBase;
import JavaExamProject_Group15.Inventory;

public class ITEM_ULTRASOUND_REPORT extends ItemBase {
    public ITEM_ULTRASOUND_REPORT() {
        super(
                "ultra report",
                "This technique allows healthcare professionals to create real-time images of internal organs, tissues, and developing fetuses during pregnancy. ",
                new String[]{},
                false
        );
    }

    public boolean interaction() {
        if (!Inventory.inBag(ITEM_MOTHER_PATIENT.class)) {
            System.out.println("you should take your patient do the test");
            return false;
        }

        System.out.println("Medical staff are doing Ultra image.......  \n\n");
        // Wait for 3 seconds and display countdown
        try {
            for (int i = 3; i > 0; i--) {
                System.out.println("wait " + i + "m...");
                Thread.sleep(1000);
                // 1000 milliseconds = 1 second
            }
        } catch (
                InterruptedException e) {
            System.out.println("Interrupted while waiting.");
        }
        this.

                pickup();
        System.out.println("Finish !!");
        return false;
    }

}

    