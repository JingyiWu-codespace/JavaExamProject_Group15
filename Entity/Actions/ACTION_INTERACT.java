package JavaExamProject_Group15.Entity.Actions;

import JavaExamProject_Group15.Entity.Items.ItemBase;
import JavaExamProject_Group15.Inventory;

import static JavaExamProject_Group15.Player.currPlayer;

public class ACTION_INTERACT extends ActionBase {
    private ACTION_INTERACT() {
        super(
                "interact",
                "Interact with an item",
                new String[]{"use", "open", "talk", "do"}
        );
    }

    private void executeAction(ItemBase item) {
        if (Inventory.inBag(item) || Inventory.inRoom(item, currPlayer.getCurrentRoom()))
            item.interaction();
        else
            System.out.println("the entity is not accessible");
    }

}

    