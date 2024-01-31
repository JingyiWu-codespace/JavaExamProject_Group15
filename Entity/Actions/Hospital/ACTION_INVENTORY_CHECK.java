package JavaExamProject_Group15.Entity.Actions.Hospital;

import JavaExamProject_Group15.Entity.Actions.ActionBase;
import JavaExamProject_Group15.Entity.Items.ItemBase;

import static JavaExamProject_Group15.Player.currPlayer;

public class ACTION_INVENTORY_CHECK extends ActionBase {
    public ACTION_INVENTORY_CHECK() {
        super(
                "inventory",
                "Check your bag",
                new String[]{"inv", "bag", "backpack"}
        );
    }

    private void executeAction() {
        System.out.println("Items are in your bag:");
        for (ItemBase temp : currPlayer.getInventory().getItemList())
            temp.printInformation();
    }

}

    