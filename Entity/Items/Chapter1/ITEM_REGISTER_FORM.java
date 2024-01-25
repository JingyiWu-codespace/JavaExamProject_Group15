package JavaExamProject_Group15.Entity.Items.Chapter1;

import JavaExamProject_Group15.Entity.Items.ItemBase;
import JavaExamProject_Group15.Inventory;

import static JavaExamProject_Group15.Entity.Rooms.ROOM_RECEPTION_DESK;

public class ITEM_REGISTER_FORM extends ItemBase {
    public ITEM_REGISTER_FORM() {
        super(
                "registration form",
                "you need this to registration form to check patients information",
                new String[]{"register form", "form"},
                false
        );
    }

    private void pickUp() {
        if (Inventory.getFromRoom(ITEM_REGISTER_FORM.class, ROOM_RECEPTION_DESK) != null)
            new ITEM_REGISTER_FORM().pickup();
        else if (!Inventory.inBag(ITEM_REGISTER_FORM.class))
            this.pickup();
    }

    private void drop() {
        System.out.println("You can't drop this item");
    }
}

    