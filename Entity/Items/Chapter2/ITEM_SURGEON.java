package JavaExamProject_Group15.Entity.Items.Chapter2;

import JavaExamProject_Group15.Entity.Items.Chapter1.ITEM_MEDICINE;
import JavaExamProject_Group15.Entity.Items.ItemBase;
import JavaExamProject_Group15.Entity.Rooms.Hospital.ROOM_SURGERY;
import JavaExamProject_Group15.Inventory;

public class ITEM_SURGEON extends ItemBase {
    public ITEM_SURGEON() {
        super(
                "surgeon",
                "The surgeon is waiting for you to prepare for surgery",
                new String[]{},
                true
        );
    }

    public void interaction() {
//        if (!(ITEM_TEST_RESULT.checkAccessibilityToPlayer()
//                && ITEM_ULTRA_REPORT.checkAccessibilityToPlayer()
//                && ITEM_MEDICINE.checkAccessibilityToPlayer()
//                && ITEM_BISTOURY.checkAccessibilityToPlayer())
        if (!(
                Inventory.inBag(ITEM_TEST_RESULT.class)
                        && Inventory.inBag(ITEM_ULTRASOUND_REPORT.class)
                        && Inventory.inBag(ITEM_MEDICINE.class)
                        && Inventory.inBag(ITEM_BISTOURY.class)
        )) {
            System.out.println("the surgeon tells you to get 3 documents first, test result, ultra report and drugs,you need disinfect the bistory");
            return;
        }
        System.out.println("You give the documents to the surgeon\n The surgeon takes the patient. \n\n");

        ITEM_MOTHER_PATIENT mother = Inventory.getFromBag(ITEM_MOTHER_PATIENT.class);
        Inventory.moveThisToRoom(mother, ROOM_SURGERY.class);

        Inventory.removeFromAllInvs(Inventory.getFromBag(ITEM_TEST_RESULT.class));
        Inventory.removeFromAllInvs(Inventory.getFromBag(ITEM_ULTRASOUND_REPORT.class));
        Inventory.removeFromAllInvs(Inventory.getFromBag(ITEM_MEDICINE.class));
    }

}

    