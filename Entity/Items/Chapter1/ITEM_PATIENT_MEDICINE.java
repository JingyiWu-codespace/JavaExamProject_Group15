package JavaExamProject_Group15.Entity.Items.Chapter1;

import JavaExamProject_Group15.Entity.Items.ItemBase;
import JavaExamProject_Group15.Inventory;

import static JavaExamProject_Group15.Player.currPlayer;

public class ITEM_PATIENT_MEDICINE extends ItemBase {
    public ITEM_PATIENT_MEDICINE() {
        super(
                "ER patient",
                "the patient will help you fill the form.",
                new String[]{},
                true
        );
    }

    public boolean interaction() {
        if (Inventory.inBag(ITEM_MEDICINE.class)) {
            ITEM_NURSE nurse = Inventory.getFromRoom(ITEM_NURSE.class, currPlayer.getCurrentRoom());
            nurse.interaction();
            return false;
        }
        if (Inventory.inBag(ITEM_PRESCRIPTION.class) ||
                Inventory.inBag(ITEM_FILLED_REGISTER_FORM.class)) {
            System.out.println("the patient needs medicine, not paperwork");
            return false;
        }

        System.out.println("you start asking stuff from the patient\n" +
                "you are collecting patient detailed information \n\n");

        try {
            for (int i = 3; i > 0; i--) {
                System.out.println("wait " + i + "m...");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted while waiting.");
        }
        System.out.println("Filled form, now find the doctor.");

        ITEM_REGISTER_FORM registerForm = Inventory.getFromBag(ITEM_REGISTER_FORM.class);
        Inventory.removeFromAllInvs(registerForm);

        ITEM_FILLED_REGISTER_FORM filledRegisterForm = new ITEM_FILLED_REGISTER_FORM();
        Inventory.moveThisToBag(filledRegisterForm);
        return false;
    }

}

    