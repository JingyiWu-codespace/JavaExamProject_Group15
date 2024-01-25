package JavaExamProject_Group15.Entity.Items.Chapter2;

import JavaExamProject_Group15.Entity.Items.ItemBase;
import JavaExamProject_Group15.Inventory;

public class ITEM_TEST_RESULT extends ItemBase {
    public ITEM_TEST_RESULT() {
        super(
                "blood test",
                "the result of patient blood analysis ",
                new String[]{"result", "report", "blood_test"},
                false
        );
    }

    public void interaction() {
        if (!Inventory.inBag(ITEM_MOTHER_PATIENT.class)) {
            System.out.println("you should take your patient do the test");
            return;
        }
        System.out.println("Medical staff are drawing blood.......  \n\n");

//        Wait for 3 seconds and display countdown
        try {
            for (int i = 3; i > 0; i--) {
                System.out.println("wait " + i + "m...");
                Thread.sleep(1000);
//                1000 milliseconds = 1 second
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted while waiting.");
        }

        System.out.println("Finish !!" +
                "\n The result need to be wait for a few minutes. \n\n");
        try {
            for (int i = 10; i > 0; i--) {
                System.out.println("wait " + i + "m...");
                Thread.sleep(1000);
//                1000 milliseconds = 1 second
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted while waiting.");
        }
        System.out.println("The Blood test report is out");

        this.pickup();
    }

}

    