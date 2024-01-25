package JavaExamProject_Group15.Entity.Actions;

import JavaExamProject_Group15.Entity.Items.ItemBase;

public class ACTION_GRAB extends ActionBase {
    private ACTION_GRAB() {
        super(
                "grab",
                "Interact with an item",
                new String[]{"get", "take", "pickup"}
        );
    }

    private void executeAction(ItemBase item) {
        item.pickup();
    }

}

    