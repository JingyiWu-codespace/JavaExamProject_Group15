package JavaExamProject_Group15.Entity.Items.Chapter1;

import JavaExamProject_Group15.Entity.Items.ItemBase;
import JavaExamProject_Group15.Inventory;

public class ITEM_NURSE extends ItemBase {
    public ITEM_NURSE() {
        super(
                "nurse",
                "The nurse is waiting for your medicine",
                new String[]{},
                true
        );
    }

    public boolean interaction() {
        if (!Inventory.inBag(ITEM_MEDICINE.class)) {
            System.out.println("you are not in Emergency room or do not have medicine");
            return false;
        }
        System.out.println("You give the medicine to the nurse\n" +
                "The patient receives the right treatment and is send elsewhere \n\n");

        ITEM_MEDICINE med = Inventory.getFromBag(ITEM_MEDICINE.class);
        ITEM_PATIENT_MEDICINE patientMed = Inventory.getFromBag(ITEM_PATIENT_MEDICINE.class);
        ITEM_NURSE nurse = Inventory.getFromBag(ITEM_NURSE.class);
        Inventory.removeFromAllInvs(med);
        Inventory.removeFromAllInvs(patientMed);
        Inventory.removeFromAllInvs(nurse);
        return false;
    }

}

    