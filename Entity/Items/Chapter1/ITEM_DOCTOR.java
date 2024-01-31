package JavaExamProject_Group15.Entity.Items.Chapter1;

import JavaExamProject_Group15.Entity.Items.ItemBase;
import JavaExamProject_Group15.Inventory;

public class ITEM_DOCTOR extends ItemBase {
    public ITEM_DOCTOR() {
        super(
                "Doctor",
                "the doctor (hopefully) gives the correct prescription for patients",
                new String[]{"dr", "doc"},
                true
        );
    }

    public boolean interaction() {
        if (!Inventory.inBag(ITEM_FILLED_REGISTER_FORM.class)) {
            System.out.println("the doctor is busy, he looks at you with a frown and says: \n" +
                    "I can't help you if you don't have the right documents, stop wasting time");
            System.out.println("you don't have the specific documents, like FILLED register form, test report...");
            return false;
        }

        System.out.println("you give the form to the doctor\n" +
                "Doctor is thinking and writing something....  \n\n");

        // Wait for 3 seconds and display countdown
        try {
            for (int i = 3; i > 0; i--) {
                System.out.println("wait " + i + "m...");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted while waiting.");
        }

        System.out.println("Doctor gave you the prescription. no go to the pharmacy, FAST");

        ITEM_FILLED_REGISTER_FORM regForm = Inventory.getFromBag(ITEM_FILLED_REGISTER_FORM.class);
        Inventory.removeFromAllInvs(regForm);

        ITEM_PRESCRIPTION createdPrescription = new ITEM_PRESCRIPTION();
        createdPrescription.pickup();
        return false;
    }
}

    