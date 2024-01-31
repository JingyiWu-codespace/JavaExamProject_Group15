package JavaExamProject_Group15.Entity.Items.Chapter1;

import JavaExamProject_Group15.Entity.Items.ItemBase;
import JavaExamProject_Group15.Inventory;

public class ITEM_PHARMACY_CLERK extends ItemBase {
    public ITEM_PHARMACY_CLERK() {
        super(
                "pharmacy clerk",
                "The pharmacy clerk is waiting for your prescription",
                new String[]{"clerk"},
                true
        );
    }

    public boolean interaction() {
        if (!Inventory.inBag(ITEM_PRESCRIPTION.class)) {
            System.out.println("the clerk is busy, he asks you what you want. \n" +
                    "then he notices you don't have the prescription and attends others\n");
            return false;
        }
        System.out.println("You give the prescription to the pharmacy clerk\n" +
                "The clerk gives you the medicine \n\n");

        ITEM_PRESCRIPTION prescription = new ITEM_PRESCRIPTION();
        Inventory.removeFromAllInvs(prescription);

        ITEM_MEDICINE medication = new ITEM_MEDICINE();
        Inventory.moveThisToBag(medication);
        return false;
    }
}

    