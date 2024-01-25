package JavaExamProject_Group15.Entity.Items.Chapter2;

import JavaExamProject_Group15.Entity.Items.Chapter1.*;
import JavaExamProject_Group15.Entity.Items.ItemBase;
import JavaExamProject_Group15.Inventory;

public class ITEM_MOTHER_PATIENT extends ItemBase {
    public ITEM_MOTHER_PATIENT() {
        super(
                "to-be-mother patient",
                "A pregnant woman with congenital heart disease and twins",
                new String[]{"to-be-mother", "patient"},
                true
        );
    }

    public void interaction() {
        if (!Inventory.inBag(ITEM_REGISTER_FORM.class) && !Inventory.inBag(ITEM_WHEEL_CHAIR.class)) {
            System.out.println("you don't have the register form nor the wheel chair");
            return;
        }
        if (!Inventory.inBag(ITEM_REGISTER_FORM.class)) {
            System.out.println("the register form is not enough, you should take the patient to the doctor");
            System.out.println("one was just brought to the storage room");
            return;
        }

        System.out.println("you give the form to the patient\n" +
                "you are collecting patient detailed information \n\n");

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
        System.out.println("Filled form.");

        System.out.println("you gently put the patient on the wheelchair. no to the doc");

        System.out.println("Then you need accompany the patient to do blood tests and ultrasound tests.");


        ITEM_WHEEL_CHAIR item_wheel_chair = Inventory.getFromBag(ITEM_WHEEL_CHAIR.class);
        Inventory.removeFromAllInvs(item_wheel_chair);

        ITEM_REGISTER_FORM item_register_form = Inventory.getFromBag(ITEM_REGISTER_FORM.class);
        Inventory.removeFromAllInvs(item_register_form);

        ITEM_FILLED_REGISTER_FORM item_filled_register_form = new ITEM_FILLED_REGISTER_FORM();
        item_filled_register_form.pickup();

        Inventory.removeFromAllInvs(this);
        this.pickup();
    }

    public void dropItem() {
        System.out.println("you can't do that. the poor woman asks you why you leaving her, she is in pain and is scared");
    }
}

    